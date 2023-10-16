CREATE TABLE IF NOT EXISTS launch_details
(
    id integer NOT NULL,
    access_token varchar(255),
    aa_id varchar(255),
    auth_url varchar(255),
    auth_code varchar(255),
    client_id varchar(255),
    client_secret varchar(255),
    debug_fhir_query_and_eicr bit DEFAULT B'0' NOT NULL,
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
    rrprocessing_both bit DEFAULT B'0' NOT NULL,
    is_covid19 bit DEFAULT B'0' NOT NULL,
    rrprocessing_createdocref bit DEFAULT B'0' NOT NULL,
    is_emergent_reporting_enabled bit DEFAULT B'0' NOT NULL,
    
    rrprocessing_invokerestapi bit DEFAULT B'0' NOT NULL,
    is_multi_tenant_system_launch bit DEFAULT B'0' NOT NULL,
    is_system_launch bit DEFAULT B'0' NOT NULL,
    is_user_account_launch bit DEFAULT B'0' NOT NULL,
    last_updated_ts timestamp NOT NULL,
    launch_id varchar(255),
    launch_patient_id varchar(255),
    launch_state integer,
    launch_type varchar(255),
    processing_status varchar(255),
    provider_uuid varchar(255),
    redirect_uri varchar(255),
    refresh_token varchar(255),
    require_aud bit DEFAULT B'0' NOT NULL,
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
    validation_mode bit DEFAULT B'0' NOT NULL,
    ver_number integer,
    x_request_id varchar(255),
    UNIQUE (ehr_server_url, launch_patient_id, encounter_id)
);