CREATE TABLE IF NOT EXISTS healthcare_setting
(
    id INT PRIMARY KEY,
    aa_id varchar(255),
    auth_type varchar(255) not null,
    backend_auth_key_alias varchar(255),
    clientId varchar(255) not null,
    clientSecret varchar(255) not null,
    create_doc_ref_response INTEGER,
    default_provider_id varchar(255),
    direct_host varchar(255),
    direct_pwd varchar(255),
    direct_recipient_address varchar(255),
    direct_user varchar(255),
    doc_ref_mime_type varchar(255),
    ehr_access_token varchar(255),
    token_expiry_date timestamp,
    ehr_access_token_expiry_duration INTEGER,
    ehr_supports_subscriptions INTEGER,
    encounter_end_time varchar(255),
    encounter_start_time varchar(255),
    is_fhir INTEGER NOT NULL,
    fhir_server_base_url varchar(255) not null,
    fhir_version varchar(255),
    response_rest_api_url varchar(255),
    imap_port varchar(255),
    imap_url varchar(255),
    is_direct bit NOT NULL,
    is_restapi bit NOT NULL,
    is_xdr bit NOT NULL,
    kars_active varchar(255),
    last_updated_ts timestamp NOT NULL,
    off_hours_end INTEGER,
    off_hours_end_min INTEGER,
    off_hours_start INTEGER,
    off_hours_start_min INTEGER,
    off_hours_timezone varchar(255),
    off_hours_enabled bit,
    org_id varchar(255),
    org_id_system varchar(255),
    org_name varchar(255),
    password varchar(255),
    pha_url varchar(255),
    pop_port varchar(255),
    pop_url varchar(255),
    require_aud bit NOT NULL,
    rest_api_url varchar(255),
    scopes varchar(255) NOT NULL,
    smtp_port varchar(255),
    smtp_url varchar(255),
    token_url varchar(255),
    trusted_third_party varchar(255),
    username varchar(255),
    xdr_recipient_address varchar(255),
    UNIQUE (id),
    UNIQUE (fhir_server_base_url)
);