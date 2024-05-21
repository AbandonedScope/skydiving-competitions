create table if not exists jumping
(
    id                               bigserial   primary key,
    competition_member_detail_id     bigint      not null,
    referee_id                       bigint      not null,
    time                             float         not null,
);