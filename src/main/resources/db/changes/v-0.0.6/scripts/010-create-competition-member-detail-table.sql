create table if not exists competition_member_detail
(
    id             bigserial primary key,
    skydiver_id    bigint not null,
    competition_id bigint not null,
    team_id        bigint not null,
    member_number   int    not null,
    is_junior      boolean default false
);