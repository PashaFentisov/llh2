CREATE TABLE application_form_micro_last_version
(
    id           uuid PRIMARY KEY,
    application_form_micro_id uuid NOT NULL,
    phone_number                                  VARCHAR(20)   NOT NULL,
    conflict_damages                              VARCHAR(1000) NOT NULL,
    home_leaving_reasons                          VARCHAR(1000) NOT NULL,
    vulnerabilities                               VARCHAR(1000) NOT NULL,
    negative_survival_strategies                  VARCHAR(1000) NOT NULL,
    email                                         VARCHAR(50)   NOT NULL,
    first_name                                    VARCHAR(50)   NOT NULL,
    middle_name                                   VARCHAR(50)   NOT NULL,
    last_name                                     VARCHAR(50)   NOT NULL,
    gender                                        VARCHAR(30)   NOT NULL,
    ipn                                           VARCHAR(50)   NOT NULL,
    date_of_birth                                 timestamp     NOT NULL,
    document_type                                 VARCHAR(50)   NOT NULL,
    document_series                               VARCHAR(50)   NOT NULL,
    document_number                               bigint        NOT NULL,
    document_issued_by                            VARCHAR(100)  NOT NULL,
    document_issued_date                          timestamp     NOT NULL,
    region                                        VARCHAR(100)  NOT NULL,
    district                                      VARCHAR(100)  NOT NULL,
    hromada                                       VARCHAR(100)  NOT NULL,
    city                                          VARCHAR(100)  NOT NULL,
    street_type                                   VARCHAR(100)  NOT NULL,
    street_name                                   VARCHAR(100)  NOT NULL,
    building_number                               VARCHAR(50)   NOT NULL,
    apartment_number                              bigint        NOT NULL,
    pavilion_number                               VARCHAR(50)   NOT NULL,
    region_before_moving                          VARCHAR(100)  NOT NULL,
    full_address_before_moving                    VARCHAR(500)  NOT NULL,
    short_business_idea_description               VARCHAR(1000) NOT NULL,
    business_manufacturing_place                  VARCHAR(500)  NOT NULL,
    business_manufacturing_way                    VARCHAR(1000) NOT NULL,
    business_consumers                            VARCHAR(1000) NOT NULL,
    needed_grant_amount                           VARCHAR(500)  NOT NULL,
    grant_expenses                                VARCHAR(1000) NOT NULL,
    invested_money_amount                         VARCHAR(500)  NOT NULL,
    employees_have                                boolean       NOT NULL,
    number_of_employees                           bigint        NOT NULL,
    business_monthly_income                       bigint        NOT NULL,
    is_vpo                                        boolean       NOT NULL,
    vpo_reference_number                          bigint        NOT NULL,
    vpo_reference_issued_date                     timestamp     NOT NULL,
    grand_funding                                 VARCHAR(500)  NOT NULL,
    people_leaving_with_you                       int           NOT NULL,
    three_month_moving_plans                      VARCHAR(100)  NOT NULL,
    family_average_monthly_income                 VARCHAR(500)  NOT NULL,
    family_average_monthly_income_before_conflict VARCHAR(500)  NOT NULL,
    self_sufficiency                              VARCHAR(100)  NOT NULL,
    took_part_in_such_programs                    boolean       NOT NULL,
    about_program                                 VARCHAR(100)  NOT NULL,
    is_deleted                                    boolean       NOT NULL
);

CREATE INDEX idx_last_version_main_form_id ON application_form_micro_last_version (application_form_micro_id);
