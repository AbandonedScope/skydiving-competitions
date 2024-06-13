alter table if exists jumping
    drop column if exists height;
alter table if exists jumping
    drop column if exists speed;
alter table if exists jumping
    drop column if exists time_delay_of_parachut_opening;
alter table if exists jumping
    drop column if exists jumping_number;
alter table if exists jumping
    drop column if exists jumping_number_during_year;
alter table if exists jumping
    drop column if exists jumping_number_billable;
alter table if exists jumping
    drop column if exists aircraft;
alter table if exists jumping
    drop column if exists aviation;
alter table if exists jumping
    drop column if exists parachut_id;

drop view if exists skydiver_view;

alter table if exists skydiver
    drop column if exists height;
alter table if exists skydiver
    drop column if exists weight;
alter table if exists skydiver
    drop column if exists shoe_size;
alter table if exists skydiver
    drop column if exists jacket_size;
alter table if exists skydiver
    drop column if exists pants_size;

create or replace view skydiver_view
as
select id,
       gender,
       birth_date,
       place_of_birth,
       place_of_work,
       education,
       phone_number,
       couch_name,
       begin_of_sport_career,
       sport_rank,
       jumping_amount,
       is_internal
from skydiver
where skydiver.is_deleted is false;