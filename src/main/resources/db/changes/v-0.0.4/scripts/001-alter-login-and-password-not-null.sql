alter table if exists user_info
alter
column login set not null,
    alter
column password set not null;