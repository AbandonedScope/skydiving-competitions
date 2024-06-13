alter table if exists competition_collegium
    rename constraint competition_stage_pkey to competition_collegium_pk;
alter table if exists competition_collegium
    rename constraint competition_stage_competition_id_fkey to competition_collegium_competition_id_fk;


alter table competition_collegium_referee_trans
    rename constraint competition_stage_referee_trans_pkey to competition_collegium_referee_trans_pk;
alter table competition_collegium_referee_trans
    rename constraint competition_stage_referee_trans_competition_stage_id_fkey to competition_collegium_referee_trans_competition_collegium_id_fk;
alter table competition_collegium_referee_trans
    rename constraint competition_stage_referee_trans_referee_id_fkey to competition_collegium_referee_trans_referee_id_fk;

alter table competition_member_detail
    add constraint competition_member_detail_skydiver_id_fk
        foreign key (skydiver_id) references skydiver
            on update cascade on delete cascade;
alter table competition_member_detail
    add constraint competition_member_detail_competition_id_fk
        foreign key (competition_id) references competition
            on update cascade on delete cascade;

alter table passport_info
    add constraint passport_info_skydiver_id_fk
        foreign key (skydiver_id) references skydiver
            on update cascade on delete cascade;

alter table referee
    add constraint referee_user_info_id_fk
        foreign key (id) references user_info
            on update cascade on delete cascade;

alter table skydiver
    add constraint skydiver_user_info_id_fk
        foreign key (id) references user_info
            on update cascade on delete cascade;

alter sequence competition_stage_id_seq rename to competition_collegium_id_seq;

alter sequence competition_stage_referee_trans_id_seq rename to competition_collegium_referee_trans_id_seq;
