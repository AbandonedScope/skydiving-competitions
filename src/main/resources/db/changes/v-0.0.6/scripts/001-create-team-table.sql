create table if not exists team
(
    id         bigserial primary key,
    name       varchar(100) unique not null,
    is_deleted boolean             not null default false
);