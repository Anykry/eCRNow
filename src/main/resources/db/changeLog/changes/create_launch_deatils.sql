CREATE TABLE IF NOT EXISTS launch_details
(
    id integer NOT NULL,
    access_token varchar(255),
    aa_id varchar(255),
    auth_url varchar(255),
    auth_code varchar(255),
    client_id varchar(255),
    client_secret varchar(255),
    debug_fhir_query_and_eicr bit NOT NULL DEFAULT 0,
    direct_host varchar(255),
    direct_pwd varchar(255),
    direct_recipient varchar(255),
    direct_user varchar(255),
    ehr_server_url varchar(255),
    encounter_id varchar(255),
    end_date timestamp ,
    expiry integer DEFAULT 0,
    fhir_version varchar(255),
    imap_port varchar(255),
    imap_url varchar(255),
    rrprocessing_both bit NOT NULL DEFAULT 0,
    is_covid19 bit NOT NULL DEFAULT 0,
    rrprocessing_createdocref bit NOT NULL DEFAULT 0,
    is_emergent_reporting_enabled bit NOT NULL DEFAULT 0,
    is_full_ecr bit NOT NULL DEFAULT TRUE,
    rrprocessing_invokerestapi bit NOT NULL DEFAULT 0,
    is_multi_tenant_system_launch bit NOT NULL DEFAULT 0,
    is_system_launch bit NOT NULL DEFAULT TRUE,
    is_user_account_launch bit NOT NULL DEFAULT 0,
    last_updated_ts timestamp NOT NULL,
    launch_id varchar(255),
    launch_patient_id varchar(255),
    launch_state integer,
    launch_type varchar(255),
    processing_status varchar(255),
    provider_uuid varchar(255),
    redirect_uri varchar(255),
    refresh_token varchar(255),
    require_aud bit NOT NULL DEFAULT 0,
    rest_api_url varchar(255),
    rr_doc_ref_mime_type varchar(255),
    rr_rest_api_url varchar(255),
    scope varchar(255),
    set_id varchar(255),
    smtp_port varchar(255),
    smtp_url varchar(255),
    start_date timestamp,
    status varchar(255),
    token_expiry_date timestamp,
    token_url varchar(255),
    user_id varchar(255),
    validation_mode bit NOT NULL DEFAULT 0,
    ver_number integer,
    x_request_id varchar(255),
    UNIQUE (ehr_server_url, launch_patient_id, encounter_id)
);