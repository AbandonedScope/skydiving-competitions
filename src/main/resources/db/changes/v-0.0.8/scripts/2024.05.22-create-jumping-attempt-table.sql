create table if not exists jumping_attempt
(
    id                               bigserial   primary key,
    jumping_id                       bigint      not null,
    attempt_number                   int         not null,
    height                           float       not null,
    speed                            float       not null,
    accurancy                        float       not null
);