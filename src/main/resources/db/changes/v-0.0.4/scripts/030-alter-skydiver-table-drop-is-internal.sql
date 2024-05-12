alter table if exists skydiver
    drop column is_internal,
    alter column birth_date set not null,
    alter column place_of_birth set not null,
    alter column place_of_work set not null,
    alter column education set not null,
    alter column phone_number set not null;

