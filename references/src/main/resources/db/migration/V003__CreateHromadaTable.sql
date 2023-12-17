CREATE TABLE hromada
(
    id            BIGSERIAL PRIMARY KEY,
    code          VARCHAR(255),
    district_code VARCHAR(255),
    name_en       VARCHAR(255),
    name_ua       VARCHAR(255)
);

CREATE INDEX idx_district_code ON hromada (district_code);
