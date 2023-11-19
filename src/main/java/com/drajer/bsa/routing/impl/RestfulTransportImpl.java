package com.drajer.bsa.routing.impl;

import static org.apache.commons.text.StringEscapeUtils.escapeJson;

import com.drajer.bsa.auth.RestApiAuthorizationHeaderIf;
import com.drajer.bsa.model.KarProcessingData;
import com.drajer.bsa.model.TriggerMatchExecution;
import com.drajer.bsa.routing.DataTransportInterface;
import com.drajer.bsa.utils.BsaServiceUtils;
import java.util.stream.Collectors;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// here
@Service
public class RestfulTransportImpl implements DataTransportInterface {

  private static final Logger logger = LoggerFactory.getLogger(RestfulTransportImpl.class);

  @Autowired RestApiAuthorizationHeaderIf authorizationService;

  @Autowired private RestTemplate restTemplate;

  @Value("${isMatchedPathsAndEicrDocIdRequired:false}")
  private boolean isMatchedPathsAndEicrDocIdRequired;

  private static final String MATCHED_PATHS_HEADER = "matchedPaths";
  private static final String EICR_DOC_ID_HEADER = "eicrDocId";

  @Override
  public void sendEicrDataUsingDirect(KarProcessingData data) {

    String error = " Direct Transport method invoked on Restful Transport which is not supported ";
    logger.error(error);
  }

  @Override
  public void receiveRrDataUsingDirect(KarProcessingData data) {

    String error =
        "Direct Transport method invoked on Restful  Transport API which is not supported ";
    logger.error(error);
  }

  @Override
  public JSONObject sendEicrDataUsingRestfulApi(KarProcessingData data) {

    logger.info(" **** START Executing RESTful Transmission **** ");

    String payload = data.getSubmittedCdaData();

    JSONObject bundleResponse = null;

    URIBuilder ub = null;
    try {

      HttpHeaders headers = new HttpHeaders();
      logger.info("In Initialization");

      if (authorizationService != null) {
        headers = authorizationService.getAuthorizationHeader(data);
      }

      // Set Content-Type Header
      headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

      // Add X-Request-ID and X-Correlation-ID
      String newXReqId = java.util.UUID.randomUUID().toString();
      headers.add("X-Request-ID", newXReqId);
      headers.add("X-Correlation-ID", data.getPhm().getxCorrelationId());

      logger.info(
          " Launch ReqId: {} X-Request-ID for Eicr Submission: {} X-Correlation-ID for Eicr Submission: {}",
          data.getxRequestId(),
          newXReqId,
          data.getPhm().getxCorrelationId());

      final String json = constructJson(payload, data);

      if (logger.isDebugEnabled()) {
        logger.debug("Eicr Trigger request: {}", json);
      }

      if (isMatchedPathsAndEicrDocIdRequired) {
        String matchedPaths = getTriggerMatchedCodes(data);
        headers.add(MATCHED_PATHS_HEADER, matchedPaths);
        headers.add(EICR_DOC_ID_HEADER, data.getPhm().getSubmittedDataId());
      }

      final HttpEntity<String> request = new HttpEntity<>(json, headers);

      logger.info(data.getHealthcareSetting().getRestApiUrl());

      ub = new URIBuilder(data.getHealthcareSetting().getRestApiUrl());

      if (logger.isInfoEnabled()) {
        logger.info("Sending Eicr to Endpoint::::: {}", ub);
      }

      final ResponseEntity<String> response =
          restTemplate.exchange(ub.toString(), HttpMethod.POST, request, String.class);

      bundleResponse = new JSONObject(response.getBody());
      bundleResponse.put("status", response.getStatusCodeValue());

      if (logger.isInfoEnabled()) {
        logger.info("Received response: {}", bundleResponse);
      }

    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        if (ub != null) {
          logger.error("RestApi Error in sending Eicr XML to Endpoint {}:", ub, e);
        } else {
          logger.error("RestApi Error in preparing to send Eicr XML:", e);
        }
      }

      throw new RuntimeException(e.getMessage());
    }

    return bundleResponse;
  }

  private String getTriggerMatchedCodes(KarProcessingData data) {

    String matchedPaths = "";
    TriggerMatchExecution state = BsaServiceUtils.getDetailStatus(data);
    if (state != null && state.getStatuses() != null) {
      matchedPaths =
          state
              .getStatuses()
              .stream()
              .flatMap(ctcs -> ctcs.getMatchedCodes().stream())
              .map(mtc -> mtc.getMatchedPath())
              .collect(Collectors.joining(","));
    }
    return matchedPaths;
  }

  /**
   * Escape EICR Trgger JSON.
   *
   * @param eicrXml EICR XML document
   * @param ecr EICR object
   * @return EICR Trigger JSON
   */
  private static String constructJson(final String payload, final KarProcessingData data) {
    return "{\"fhirServerURL\":\""
        + escapeJson(data.getPhm().getFhirServerBaseUrl())
        + "\",\"patientId\":\""
        + escapeJson(data.getPhm().getPatientId())
        + "\",\"encounterId\":\""
        + escapeJson(data.getPhm().getEncounterId())
        + "\",\"submittedMessageVersionId\":\""
        + escapeJson(data.getPhm().getSubmittedVersionNumber().toString())
        + "\",\"payload\":\""
        + escapeJson(payload)
        + "\"}";
  }
}
