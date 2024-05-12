create
or replace view user_info_view
as
select id,
       login,
       password,
       first_name,
       second_name,
       patronymic,
       role
from user_info
where user_info.is_deleted is false;