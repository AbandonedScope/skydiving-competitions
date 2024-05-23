create table if not exists trick_attempt
(
    id                               bigserial   primary key,
    trick_serie_id                   bigint      not null,
    trick_type                       int         not null,
    arrow_penalty                    int         null,
    d_penalty                        int         null,
    s_penalty                        int         null,
    minus_penalty                    int         null,
    plus_minus_penalty               int         null,
    foreign key  (trick_serie_id) references trick_serie(id)
);