create table if not exists referee
(
    id           bigserial primary key,
    user_info_id bigint      not null,
    category     varchar(50) not null,
    foreign key (user_info_id) references user_info (id) on delete cascade
);
