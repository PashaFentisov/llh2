CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
insert into role(id, name)
values (uuid_generate_v4(), 'ROLE_ADMIN');

insert into role(id, name)
values (uuid_generate_v4(), 'ROLE_OPERATOR');

insert into users(id, email, password, is_verified, is_deleted, role_id, phone_number)
values (uuid_generate_v4(), 'pasha16.ua@gmail.com', '$2a$12$oeY8us79pTvATEZBAWoh4Oz4iPlfNc6G5h5jj8g.WTNRt.Q5D86Py',
        true, false, (select id from role where name = 'ROLE_ADMIN'),
        '0974232829');
