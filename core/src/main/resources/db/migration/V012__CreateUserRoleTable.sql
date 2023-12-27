CREATE TABLE IF NOT EXISTS role
(
    id   uuid PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users
(
    id           uuid PRIMARY KEY,
    email        VARCHAR(50)  NOT NULL UNIQUE,
    first_name   VARCHAR(50),
    last_name    VARCHAR(50),
    username     VARCHAR(50),
    password     VARCHAR(256) NOT NULL,
    phone_number VARCHAR(50) UNIQUE,
    role_id      uuid,
    is_verified  BOOLEAN,
    is_deleted   BOOLEAN,
    photo_url    VARCHAR(500),
    FOREIGN KEY (role_id) references role (id)
);

