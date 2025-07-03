CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE IF NOT EXISTS ratings (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID,
    movie_id UUID,
    rating INT
    );
