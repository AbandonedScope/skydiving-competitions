create table if not exists competition
(
    id               bigserial primary key,
    name             varchar(50)  not null,
    begin_date       timestamp    not null,
    end_date         timestamp    not null,
    address          varchar(150) not null,
    status           int          not null,
    number_of_stages int          not null
);
