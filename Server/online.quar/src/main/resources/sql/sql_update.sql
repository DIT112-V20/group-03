ALTER TABLE users ADD COLUMN IF NOT EXISTS fullname varchar(100);
ALTER TABLE car ADD COLUMN IF NOT EXISTS description varchar(255);