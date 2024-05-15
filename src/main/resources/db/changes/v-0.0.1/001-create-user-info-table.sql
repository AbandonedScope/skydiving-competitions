create table if not exists user_info
(
    id
    bigserial
    primary
    key,
    login
    varchar
(
    40
) not null,
    password varchar
(
    500
) not null,
    first_name varchar
(
    50
) not null,
    second_name varchar
(
    50
) not null,
    patronymic varchar
(
    50
),
    role smallint not null
    );
