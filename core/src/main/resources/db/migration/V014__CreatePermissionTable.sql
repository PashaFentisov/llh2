CREATE TABLE IF NOT EXISTS permission
(
    id         uuid PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    is_deleted BOOLEAN,
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS role_permission
(
    role_id       uuid,
    permission_id uuid,
    FOREIGN KEY (role_id) REFERENCES role (id),
    FOREIGN KEY (permission_id) REFERENCES permission (id),
    PRIMARY KEY (role_id, permission_id)
);
--
-- insert into permission (name, is_deleted)
-- values ('DELETE_ACCESS', false);
-- insert into role_permission(role_id, permission_id)
-- values (1, 1);
