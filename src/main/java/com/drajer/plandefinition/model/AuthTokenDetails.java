package com.drajer.plandefinition.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "auth_token_details")
@DynamicUpdate
public class AuthTokenDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "client_id", nullable = true)
	private String clientId;

	@Column(name = "ehr_server_url", nullable = true, columnDefinition = "TEXT")
	private String ehrServerURL;

	@Column(name = "auth_url", nullable = true, columnDefinition = "TEXT")
	private String authUrl;

	@Column(name = "token_url", nullable = true, columnDefinition = "TEXT")
	private String tokenUrl;

	@Column(name = "access_token", nullable = true, columnDefinition = "TEXT")
	private String accessToken;

	@Column(name = "user_id", nullable = true)
	private String userId;

	@Column(name = "expiry", nullable = true)
	private int expiry;

	@Column(name = "scope", nullable = true, columnDefinition = "TEXT")
	private String scope;

	@Column(name = "last_updated_ts", nullable = false)
	//@UpdateTimestamp
	@CreationTimestamp
	private Date lastUpdated;

	@Column(name = "refresh_token", nullable = true, columnDefinition = "TEXT")
	private String refreshToken;

	@Column(name = "launch_patient_id", nullable = true)
	private String launchPatientId;

	@Column(name = "fhir_version", nullable = true)
	private String fhirVersion;

	@Column(name = "encounter_id", nullable = true)
	private String encounterId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getEhrServerURL() {
		return ehrServerURL;
	}

	public void setEhrServerURL(String ehrServerURL) {
		this.ehrServerURL = ehrServerURL;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public String getTokenUrl() {
		return tokenUrl;
	}

	public void setTokenUrl(String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getExpiry() {
		return expiry;
	}

	public void setExpiry(int expiry) {
		this.expiry = expiry;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getLaunchPatientId() {
		return launchPatientId;
	}

	public void setLaunchPatientId(String launchPatientId) {
		this.launchPatientId = launchPatientId;
	}

	public String getFhirVersion() {
		return fhirVersion;
	}

	public void setFhirVersion(String fhirVersion) {
		this.fhirVersion = fhirVersion;
	}

	public String getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(String encounterId) {
		this.encounterId = encounterId;
	}

}
