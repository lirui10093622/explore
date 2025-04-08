DROP SCHEMA IF EXISTS demo_db_0;
DROP SCHEMA IF EXISTS demo_db_1;

CREATE SCHEMA IF NOT EXISTS demo_db_0;
CREATE SCHEMA IF NOT EXISTS demo_db_1;

DROP TABLE IF EXISTS demo_db_0.t_order_0;
DROP TABLE IF EXISTS demo_db_0.t_order_1;
DROP TABLE IF EXISTS demo_db_1.t_order_0;
DROP TABLE IF EXISTS demo_db_1.t_order_1;

DROP TABLE IF EXISTS demo_db_0.t_order_item_0;
DROP TABLE IF EXISTS demo_db_0.t_order_item_1;
DROP TABLE IF EXISTS demo_db_1.t_order_item_0;
DROP TABLE IF EXISTS demo_db_1.t_order_item_1;

DROP TABLE IF EXISTS demo_db_0.t_passenger_0;
DROP TABLE IF EXISTS demo_db_0.t_passenger_1;
DROP TABLE IF EXISTS demo_db_1.t_passenger_0;
DROP TABLE IF EXISTS demo_db_1.t_passenger_1;

DROP TABLE IF EXISTS demo_db_0.t_voucher_0;
DROP TABLE IF EXISTS demo_db_0.t_voucher_1;
DROP TABLE IF EXISTS demo_db_1.t_voucher_0;
DROP TABLE IF EXISTS demo_db_1.t_voucher_1;

CREATE TABLE demo_db_0.t_order_0 (
    order_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    use_date VARCHAR(8) NOT NULL,
    contact_name VARCHAR(200) NOT NULL,
    contact_mobile VARCHAR(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE demo_db_0.t_order_1 (
    order_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    use_date VARCHAR(8) NOT NULL,
    contact_name VARCHAR(200) NOT NULL,
    contact_mobile VARCHAR(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE demo_db_1.t_order_0 (
    order_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    use_date VARCHAR(8) NOT NULL,
    contact_name VARCHAR(200) NOT NULL,
    contact_mobile VARCHAR(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE demo_db_1.t_order_1 (
    order_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    use_date VARCHAR(8) NOT NULL,
    contact_name VARCHAR(200) NOT NULL,
    contact_mobile VARCHAR(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE demo_db_0.t_order_item_0 (
    order_item_id BIGINT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    resource_id BIGINT NOT NULL,
    sale_price DECIMAL(10, 4) NOT NULL,
    base_price DECIMAL(10, 4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE demo_db_0.t_order_item_1 (
    order_item_id BIGINT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    resource_id BIGINT NOT NULL,
    sale_price DECIMAL(10, 4) NOT NULL,
    base_price DECIMAL(10, 4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE demo_db_1.t_order_item_0 (
    order_item_id BIGINT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    resource_id BIGINT NOT NULL,
    sale_price DECIMAL(10, 4) NOT NULL,
    base_price DECIMAL(10, 4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE demo_db_1.t_order_item_1 (
    order_item_id BIGINT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    resource_id BIGINT NOT NULL,
    sale_price DECIMAL(10, 4) NOT NULL,
    base_price DECIMAL(10, 4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;