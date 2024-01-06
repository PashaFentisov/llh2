create table conflict_damages
(
    application_form_micro_id uuid not null,
    conflict_damages varchar(255)
);

alter table if exists conflict_damages
    add constraint conflict_damage_name_foreign foreign key (application_form_micro_id) references application_form_micro (id);
CREATE INDEX idx_conflict_damages_main_form_id ON conflict_damages (application_form_micro_id);