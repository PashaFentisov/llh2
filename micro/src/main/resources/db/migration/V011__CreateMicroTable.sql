CREATE TABLE micro
(
    id           BIGSERIAL PRIMARY KEY,
    main_form_id BIGINT NOT NULL,
    foreign key (main_form_id) references main_form (id)
);