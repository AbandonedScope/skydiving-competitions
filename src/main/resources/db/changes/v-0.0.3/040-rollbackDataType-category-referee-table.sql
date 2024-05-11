ALTER TABLE referee
    DROP COLUMN category;

ALTER TABLE referee
    ADD COLUMN category VARCHAR(50);