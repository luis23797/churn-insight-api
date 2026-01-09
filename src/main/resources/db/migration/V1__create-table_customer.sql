CREATE TABLE customer (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id VARCHAR(100) NOT NULL UNIQUE,
    surname VARCHAR(100) NULL,
    geography VARCHAR(100) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    birth_date DATE NOT NULL,
    created_at DATE NOT NULL,
    estimated_salary DOUBLE NULL
);