drop view if exists skydiver_view;

alter table if exists skydiver
    drop column if exists sport_title;

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
       height,
       weight,
       shoe_size,
       jacket_size,
       pants_size,
       begin_of_sport_career,
       sport_rank,
       jumping_amount,
       is_internal
from skydiver
where skydiver.is_deleted is false;