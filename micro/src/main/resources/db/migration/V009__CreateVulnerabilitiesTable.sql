create table vulnerabilities
(
    application_form_micro_id uuid not null,
    vulnerabilities varchar(255)
);

alter table if exists vulnerabilities
    add constraint vulnerabilities_foreign foreign key (application_form_micro_id) references application_form_micro (id);
CREATE INDEX idx_vulnerabilities_main_form_id ON vulnerabilities (application_form_micro_id);
