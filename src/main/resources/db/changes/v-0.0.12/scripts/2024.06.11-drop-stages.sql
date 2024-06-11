alter table if exists competition_stage
    drop column number;

alter table if exists competition_stage
    rename to competition_collegium;

alter table if exists competition_stage_referee_trans
    rename to competition_collegium_referee_trans;

alter table if exists competition_collegium_referee_trans
    add column if not exists referee_number int not null default 1;

alter table if exists competition_collegium_referee_trans
    alter column referee_number drop default;

alter table if exists competition_collegium_referee_trans
    rename column competition_stage_id to competition_collegium_id;

drop view competition_view;

alter table if exists competition
    drop column number_of_stages;

create or replace view competition_view
as
select id,
       name,
       begin_date,
       end_date,
       address,
       status
from competition
where is_deleted = false;