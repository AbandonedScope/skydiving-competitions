create table if not exists trick_attempt
(
    id                               bigserial   primary key,
    trick_id                         bigint      not null,
    trick_type                       int         not null,
    arrow_penalty                    int         not null,
    d_penalty                        int         not null,
    s_penalty                        int         not null,
    minus_penalty                    int         not null,
    plus_minus_penalty               int         not null
);