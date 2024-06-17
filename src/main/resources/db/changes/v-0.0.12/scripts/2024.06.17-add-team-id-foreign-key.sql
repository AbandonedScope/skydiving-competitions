alter table competition_member_detail
    add constraint competition_member_detail_team_id_fk
        foreign key (team_id) references team (id)
            on update cascade on delete cascade
            deferrable;