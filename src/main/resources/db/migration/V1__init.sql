CREATE TABLE categories
(
    id   BINARY(16) NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE prices
(
    id       BINARY(16) NOT NULL,
    price    INT NOT NULL,
    currency VARCHAR(255) NULL,
    CONSTRAINT pk_prices PRIMARY KEY (id)
);

CREATE TABLE products
(
    id            BINARY(16) NOT NULL,
    title         VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    image         VARCHAR(255) NULL,
    category_id   BINARY(16) NOT NULL,
    price_id      BINARY(16) NOT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE products
    ADD CONSTRAINT uc_products_price UNIQUE (price_id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_PRICE FOREIGN KEY (price_id) REFERENCES prices (id);