create
or replace view referee_view
as select
       id,
       category
    from referee
   where referee.is_deleted = false;