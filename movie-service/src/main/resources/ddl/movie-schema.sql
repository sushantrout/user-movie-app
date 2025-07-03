CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS movies (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(100),
    genre VARCHAR(50),
    release_year INT
    );
