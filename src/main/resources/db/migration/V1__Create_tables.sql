CREATE TABLE categories (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE states (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE countries (
                           id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE hosts (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       surname VARCHAR(255) NOT NULL,
                       country_id BIGINT REFERENCES countries(id)
);

CREATE TABLE accommodations (
                                id BIGSERIAL PRIMARY KEY,
                                name VARCHAR(255) NOT NULL,
                                num_rooms INT NOT NULL,
                                category_id BIGINT REFERENCES categories(id),
                                state_id BIGINT REFERENCES states(id)
);

CREATE TABLE accommodations_hosts (
                                      accommodation_id BIGINT REFERENCES accommodations(id),
                                      host_id BIGINT REFERENCES hosts(id),
                                      PRIMARY KEY (accommodation_id, host_id)
);