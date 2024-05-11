create table if not exists passport_info
(
    id                bigserial primary key,
    skydiver_id       bigint       not null,
    series            char(2)      not null,
    number            char(7)      not null,
    personal_number   char(14)     not null,
    issuing_authority varchar(100) not null,
    issued_date       timestamp    not null
);
