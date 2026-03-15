ALTER TABLE accommodations
    ADD COLUMN created_at TIMESTAMP,
ADD COLUMN updated_at TIMESTAMP;

ALTER TABLE hosts
    ADD COLUMN created_at TIMESTAMP,
ADD COLUMN updated_at TIMESTAMP;