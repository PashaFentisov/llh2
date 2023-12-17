create table home_leaving_reasons
(
    main_form_id         bigint not null,
    home_leaving_reasons varchar(255)
);

alter table if exists home_leaving_reasons
    add constraint home_leaving_reason_foreign foreign key (main_form_id) references main_form (id);
CREATE INDEX idx_home_leaving_reasons_main_form_id ON home_leaving_reasons (main_form_id);