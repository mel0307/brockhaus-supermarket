CREATE SCHEMA IF NOT EXISTS supermarket;

CREATE SEQUENCE IF NOT EXISTS supermarket.product_seq INCREMENT 1 START 1;

CREATE TABLE supermarket.product
(
    product_id          BIGINT        NOT NULL DEFAULT nextval('supermarket.product_seq'),
    product_name        VARCHAR       NOT NULL,
    product_type        VARCHAR       NOT NULL,
    product_quality     INTEGER       NOT NULL,
    product_base_price  NUMERIC(10,2) NOT NULL,
    product_expiry_date DATE,
    insertion_date      DATE          NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY (product_id)
);