create or replace view team_view as
select id,
       name
from team
where team.is_deleted is false;