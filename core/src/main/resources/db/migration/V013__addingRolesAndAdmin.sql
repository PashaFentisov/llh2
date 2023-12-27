CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
insert into role(id, name)
values (uuid_generate_v4(), 'ROLE_ADMIN');

insert into role(id, name)
values (uuid_generate_v4(), 'ROLE_USER');

-- insert into users(email, password, is_verified, is_deleted, role_id, phone_number)
-- values ('pasha16.ua@gmail.com', '$2a$12$Yii6uCkb.NTj9v84OaInceOCQYLtgLPrs4xnv13CgF05Jg.VlUL/.', true, false, 1,
--         '0974232829');
