create
or replace view skydiver_view
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
       sport_specialization,
       sport_degree,
       jumping_amount,
       is_internal
from skydiver
where skydiver.is_deleted is false;