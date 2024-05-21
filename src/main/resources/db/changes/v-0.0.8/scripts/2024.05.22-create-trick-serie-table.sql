create table if not exists trick_serie
(
    id                              bigserial   primary key,
    competition_member_detail_id    bigint      not null,
    referee_id                      bigint      not null,
    score                           int         not null,
    total_penalty                   int         not null,
    serie_number                    int         not null,
    time                            float       not null
);