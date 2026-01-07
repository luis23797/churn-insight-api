CREATE TABLE IF NOT EXISTS customer_session (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  session_id VARCHAR(50) NOT NULL,
  customer_id BIGINT NOT NULL,
  session_date DATETIME NOT NULL,
  duration_min DOUBLE NOT NULL,
  used_transfer INT NOT NULL,
  used_payment INT NOT NULL,
  used_invest INT NOT NULL,
  opened_push INT NOT NULL,
  failed_login INT NOT NULL,
  CONSTRAINT fk_customer_session_customer
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE INDEX idx_customer_session_customer_id
  ON customer_session(customer_id);

