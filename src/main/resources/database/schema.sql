-- Connect to PostgreSQL as superuser and run these commands:

-- 1. Create database
CREATE DATABASE cinema_db;

-- 2. Create user (optional, if you want a specific user)
CREATE USER cinema_user WITH PASSWORD 'your_secure_password';

-- 3. Grant privileges
GRANT ALL PRIVILEGES ON DATABASE cinema_db TO cinema_user;

-- 4. Connect to the cinema_db database
\c cinema_db;

-- 5. Grant schema privileges (if using a specific user)
GRANT ALL ON SCHEMA public TO cinema_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO cinema_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO cinema_user;

-- Optional: Set default privileges for future tables
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO cinema_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO cinema_user;

-- Verify connection (optional test query)
SELECT version();