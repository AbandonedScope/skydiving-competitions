alter table if exists user_info
    alter column login drop not null,
    alter column password drop not null;