create table if not exists skydiver
(
    id                    bigint primary key,
    gender                int         not null,
    birth_date            timestamp   not null,
    place_of_birth        varchar(40) not null,
    place_of_work         varchar(40) not null,
    education             varchar(40) not null,
    phone_number          varchar(20) not null,
    couch_name            varchar(100),
    height                float,
    weight                float,
    shoe_size             int,
    jacket_size           int,
    pants_size            int,
    begin_of_sport_career timestamp,
    sport_specialization  varchar(50),
    sport_degree          int,
    jumping_amount        int         not null default 0
);
