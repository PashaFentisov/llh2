create table negative_survival_strategies
(
    application_form_micro_id uuid not null,
    negative_survival_strategies varchar(255)
);

alter table if exists negative_survival_strategies
    add constraint negative_survival_strategies_foreign foreign key (application_form_micro_id) references application_form_micro (id);
CREATE INDEX idx_negative_survival_strategies_main_form_id ON negative_survival_strategies (application_form_micro_id);
