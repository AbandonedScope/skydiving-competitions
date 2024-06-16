alter table if exists competition
    add column next_member_number int not null default 1;

create or replace view competition_view
as
select id,
       name,
       begin_date,
       end_date,
       address,
       status,
       next_member_number
from competition
where is_deleted = false;