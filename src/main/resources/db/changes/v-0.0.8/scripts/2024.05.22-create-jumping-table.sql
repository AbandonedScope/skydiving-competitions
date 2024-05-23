create table if not exists jumping
(
    id                               bigserial   primary key,
    competition_member_detail_id     bigint      not null,
    referee_id                       bigint      not null,
    attempt_number                   int         null,
    height                           float       not null,
    speed                            float       not null,
    accurancy                        float       not null,
    time_delay_of_parachut_opening   float       null,
    jumping_number                   int         not null default 0,
    jumping_number_during_year       int         not null default 0,
    jumping_number_billable          int         not null default 0,
    performance_date                 date    not null,
    aircraft                         varchar(70) null,
    aviation                         varchar(70) null,
    parachut_id                      bigint      null,
    foreign key (competition_member_detail_id) references competition_member_detail(id),
    foreign key (referee_id )                  references referee(id)
);