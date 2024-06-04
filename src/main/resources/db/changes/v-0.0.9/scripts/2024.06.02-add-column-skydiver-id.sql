alter table if exists jumping
    add column skydiver_id bigint not null references skydiver (id) default 3;
alter table if exists jumping
    alter column skydiver_id drop default;