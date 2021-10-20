drop table if exists users_roles cascade;
drop table if exists users cascade;
drop table if exists roles cascade;

CREATE TABLE  IF NOT EXISTS users (
    id bigserial PRIMARY KEY,
    login character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    email character varying(255),
    client_id bigint,
    enabled boolean
    );

CREATE TABLE  IF NOT EXISTS roles (
    id bigserial PRIMARY KEY,
    role_name character varying(40) NOT NULL
    );

CREATE TABLE  IF NOT EXISTS users_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
    );

INSERT INTO roles (role_name) VALUES ('READER');
INSERT INTO roles (role_name) VALUES ('EDITOR');
INSERT INTO roles (role_name) VALUES ('ADMIN');

INSERT INTO users (login, password, client_id, email, enabled)
VALUES ('admin', '$2a$10$73dQALAYAHPjOZdIqObi4u5jUZ2Tqg2Mzr4pPk5AHRaKFm/jBEhwK', 1, 'sakharukaliaksandr@gmail.com', true);
INSERT INTO users (login, password, client_id, email, enabled)
VALUES ('editor', '$2a$10$Y4uw8pVXJ19lvYKUHehyuunVjPBdaU7OEOqgxKBKgVGbWSb.rTYrC', 2, 'olejnik@gmail.com', true);
INSERT INTO users (login, password, client_id, email, enabled)
VALUES ('sasha', '$2a$10$dOSLyd3hnCQTqEn1qOxxCudCzFWw9Mmrw7wqwYuPRHVGXvCwCp5NK', 3, 'sakharukaliaksandr@gmail.com', true);
INSERT INTO users (login, password, client_id, email, enabled)
VALUES ('roma', '$2a$10$a.CfLLy931ppzWZi0IDxKO5DbEPMYZAwTtQhm4Ezy9MVBIGUHiVDW', 4, 'salapura@gmail.com', true);

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 3);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (4, 1);
