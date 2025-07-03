CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users (
                                     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100),
    email VARCHAR(100)
    );