CREATE TABLE district
(
    id          BIGSERIAL PRIMARY KEY,
    code        VARCHAR(255),
    region_code VARCHAR(255),
    name_en     VARCHAR(400),
    name_ua     VARCHAR(400)
);

CREATE INDEX idx_region_code ON district (region_code);
