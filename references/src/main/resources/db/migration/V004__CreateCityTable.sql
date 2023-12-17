CREATE TABLE city
(
    id           BIGSERIAL PRIMARY KEY,
    code         VARCHAR(255),
    hromada_code VARCHAR(255),
    name_en      VARCHAR(255),
    name_ua      VARCHAR(255)
);

CREATE INDEX idx_hromada_code ON city (hromada_code);
