create table home_leaving_reasons
(
    application_form_micro_id uuid not null,
    home_leaving_reasons varchar(255)
);

alter table if exists home_leaving_reasons
    add constraint home_leaving_reason_foreign foreign key (application_form_micro_id) references application_form_micro (id);
CREATE INDEX idx_home_leaving_reasons_main_form_id ON home_leaving_reasons (application_form_micro_id);