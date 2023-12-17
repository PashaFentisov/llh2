create table conflict_damages
(
    main_form_id         bigint not null,
    conflict_damage_name varchar(255)
);

alter table if exists conflict_damages
    add constraint conflict_damage_name_foreign foreign key (main_form_id) references main_form (id);
CREATE INDEX idx_main_form_id ON conflict_damages (main_form_id);