CREATE TABLE IF NOT EXISTS users(
    id       serial primary key ,
    username VARCHAR(100) not null ,
    password bytea not null
);

CREATE TABLE IF NOT EXISTS car(
    id       serial primary key
);

