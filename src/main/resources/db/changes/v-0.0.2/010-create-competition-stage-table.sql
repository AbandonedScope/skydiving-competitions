create table if not exists competition_stage
(
    id             bigserial primary key,
    competition_id bigint not null,
    number         int    not null,
    foreign key (competition_id) references competition (id) on delete cascade
);
