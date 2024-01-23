CREATE TABLE IF NOT EXISTS currency_rate
(
    id            uuid PRIMARY KEY,
    rate          numeric   NOT NULL,
    creation_date timestamp NOT NULL
);