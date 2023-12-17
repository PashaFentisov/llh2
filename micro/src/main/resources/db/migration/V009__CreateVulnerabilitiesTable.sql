create table vulnerabilities
(
    main_form_id    bigint not null,
    vulnerabilities varchar(255)
);

alter table if exists vulnerabilities
    add constraint vulnerabilities_foreign foreign key (main_form_id) references main_form (id);
CREATE INDEX idx_vulnerabilities_main_form_id ON vulnerabilities (main_form_id);
