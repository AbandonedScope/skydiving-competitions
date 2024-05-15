alter table if exists skydiver
    add column is_internal boolean default true,
alter
column birth_date drop
not null,
    alter
column place_of_birth drop
not null,
    alter
column place_of_work drop
not null,
    alter
column education drop
not null,
    alter
column phone_number drop
not null;
