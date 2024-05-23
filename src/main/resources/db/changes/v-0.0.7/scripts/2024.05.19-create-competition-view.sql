create or replace view competition_view
as select
       id,
       name,
       begin_date,
       end_date,
       address,
       status,
       number_of_stages
from competition
where is_deleted = false;