--liquibase formatted sql

--changeset k1mb:1
CREATE TABLE IF NOT EXISTS users  (
    created_at timestamp(6) NOT NULL,
    updated_at timestamp(6) NOT NULL,
    user_id UUID NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
    username varchar(255) NOT NULL,
    PRIMARY KEY (user_id)
)
--rollback DROP TABLE users
