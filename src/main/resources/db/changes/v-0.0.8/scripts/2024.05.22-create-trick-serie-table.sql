create table if not exists trick_serie
(
    id                              bigserial   primary key,
    competition_member_detail_id    bigint      not null,
    referee_id                      bigint      not null,
    score                           int         not null,
    serie_number                    int         not null,
    time_without_penalty            float       not null,
    foreign key  (competition_member_detail_id) references competition_member_detail(id),
    foreign key  (referee_id)                   references referee(id)
);