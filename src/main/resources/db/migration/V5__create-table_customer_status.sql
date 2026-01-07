CREATE TABLE customer_status (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    credit_score INT NOT NULL,
    is_active_member BOOLEAN NOT NULL,
    has_cr_card BOOLEAN NOT NULL DEFAULT 0,

    CONSTRAINT fk_customer_status_customer
        FOREIGN KEY(customer_id)
        REFERENCES customer (id)
        ON DELETE CASCADE
);