create table if not exists competition_stage_referee_trans
(
    id                   bigserial primary key,
    competition_stage_id bigint      not null,
    referee_id           bigint      not null,
    work_performed       varchar(50) not null,
    is_main_collegium    boolean     not null,
    foreign key (competition_stage_id) references competition_stage (id),
    foreign key (referee_id) references referee (id)
);
