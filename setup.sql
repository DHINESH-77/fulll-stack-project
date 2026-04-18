-- Run this in psql or pgAdmin

CREATE DATABASE complaint_db;

-- After Spring Boot creates the tables via ddl-auto=update,
-- run this to create an admin user (password: admin123)
INSERT INTO users (username, email, password, role)
VALUES (
  'admin',
  'admin@example.com',
  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
  'ADMIN'
) ON CONFLICT (username) DO NOTHING;
