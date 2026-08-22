-- UGMC Smart Operations System relational schema.
-- The current FileDatabase mirrors these entities as durable data files.

CREATE TABLE IF NOT EXISTS locations (
    location_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(160) NOT NULL,
    area VARCHAR(120) NOT NULL,
    location_type VARCHAR(80) NOT NULL,
    x_coord DECIMAL(10,6) NOT NULL,
    y_coord DECIMAL(10,6) NOT NULL
);

CREATE TABLE IF NOT EXISTS roads (
    road_id VARCHAR(20) PRIMARY KEY,
    from_location_id VARCHAR(20) NOT NULL,
    to_location_id VARCHAR(20) NOT NULL,
    distance_km DECIMAL(10,3) NOT NULL,
    travel_time_min DECIMAL(10,3) NOT NULL,
    condition_weight DECIMAL(10,3) NOT NULL,
    FOREIGN KEY (from_location_id) REFERENCES locations(location_id),
    FOREIGN KEY (to_location_id) REFERENCES locations(location_id)
);

CREATE TABLE IF NOT EXISTS service_requests (
    request_id VARCHAR(30) PRIMARY KEY,
    source_location_id VARCHAR(20) NOT NULL,
    destination_location_id VARCHAR(20) NOT NULL,
    category VARCHAR(160) NOT NULL,
    urgency INTEGER NOT NULL CHECK (urgency BETWEEN 1 AND 5),
    time_submitted VARCHAR(40) NOT NULL,
    deadline VARCHAR(40) NOT NULL,
    status VARCHAR(30) NOT NULL,
    FOREIGN KEY (source_location_id) REFERENCES locations(location_id),
    FOREIGN KEY (destination_location_id) REFERENCES locations(location_id)
);

CREATE TABLE IF NOT EXISTS resources (
    resource_id VARCHAR(30) PRIMARY KEY,
    resource_type VARCHAR(120) NOT NULL,
    home_location_id VARCHAR(20) NOT NULL,
    capacity INTEGER NOT NULL,
    availability_status VARCHAR(30) NOT NULL,
    FOREIGN KEY (home_location_id) REFERENCES locations(location_id)
);

CREATE TABLE IF NOT EXISTS algorithm_runs (
    run_id VARCHAR(30) PRIMARY KEY,
    algorithm_name VARCHAR(80) NOT NULL,
    input_size INTEGER NOT NULL,
    time_ns BIGINT NOT NULL,
    memory_kb BIGINT NOT NULL,
    date_run VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS audit_events (
    event_id VARCHAR(30) PRIMARY KEY,
    event_type VARCHAR(60) NOT NULL,
    description VARCHAR(255) NOT NULL,
    event_time VARCHAR(40) NOT NULL
);
