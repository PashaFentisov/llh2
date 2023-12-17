CREATE TABLE IF NOT EXISTS batch_init_version
(
    id      BIGSERIAL PRIMARY KEY,
    version BIGINT default 0
);