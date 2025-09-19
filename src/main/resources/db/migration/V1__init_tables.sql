CREATE TABLE heritage_locations
(
    id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    longitude FLOAT        NOT NULL,
    latitude  FLOAT        NOT NULL,
    state     VARCHAR(255) NOT NULL,
    region    VARCHAR(255) NOT NULL
);

CREATE TABLE categories
(
    id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE heritages
(
    id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    location_id    BIGINT       NOT NULL REFERENCES heritage_locations (id),
    name           VARCHAR(255) NOT NULL,
    description    TEXT         NOT NULL,
    category_id    BIGINT       NOT NULL REFERENCES categories (id),
    inscribed_date VARCHAR(16)  NOT NULL
);

CREATE TABLE heritage_images
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    heritage_id   BIGINT       NOT NULL REFERENCES heritages (id),
    date          VARCHAR(20),
    date_accessed VARCHAR(20)  NOT NULL,
    author        VARCHAR(128) NOT NULL,
    copyright     VARCHAR(128) NOT NULL,
    source        TEXT,
    link          TEXT         NOT NULL,
    license       VARCHAR(128) NOT NULL,
    hash          VARCHAR(255) NOT NULL,
    file_path     VARCHAR(255)
);

CREATE TABLE users
(
    id       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(32) NOT NULL UNIQUE
);

CREATE TABLE user_scores
(
    id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id        BIGINT NOT NULL REFERENCES users (id),
    points         BIGINT NOT NULL,
    current_streak BIGINT NOT NULL
);