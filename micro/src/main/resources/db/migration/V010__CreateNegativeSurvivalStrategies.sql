create table negative_survival_strategies
(
    main_form_id                 bigint not null,
    negative_survival_strategies varchar(255)
);

alter table if exists negative_survival_strategies
    add constraint negative_survival_strategies_foreign foreign key (main_form_id) references main_form (id);
CREATE INDEX idx_main_form_id ON negative_survival_strategies (main_form_id);
