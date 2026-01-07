CREATE TABLE IF NOT EXISTS customer_transaction (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  transaction_id VARCHAR(50) NOT NULL,
  customer_id BIGINT NOT NULL,
  transaction_date DATETIME NOT NULL,
  amount DOUBLE NOT NULL,
  transaction_type VARCHAR(30) NOT NULL,
  CONSTRAINT fk_customer_transaction_customer
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE INDEX idx_customer_transaction_customer_id
  ON customer_transaction(customer_id);

