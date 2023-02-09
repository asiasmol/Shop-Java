DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS categories;



CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     text,
    surname  text,
    email    text,
    password text,
    address  text
);

CREATE TABLE orders
(
    id          serial PRIMARY KEY,
    user_id     integer REFERENCES users (id),
    products    text,
    data        date,
    final_price integer,
    is_paid     bit
);

CREATE TABLE categories
(
    id          serial PRIMARY KEY,
    name        text,
    department  text,
    description text
);

CREATE TABLE supplier
(
    id          serial PRIMARY KEY,
    name        text,
    description text
);
CREATE TABLE products
(
    id              SERIAL PRIMARY KEY,
    name            text,
    price           text,
    currency_string text,
    description     VARCHAR(200),
    category_id     integer REFERENCES categories (id),
    supplier_id     integer REFERENCES supplier (id)
);

INSERT INTO categories VALUES (0,'Tablet','Hardware','A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO categories VALUES (1,'Phone','Software','A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO supplier VALUES (0,'Amazon','Digital content and services');
INSERT INTO supplier VALUES (1,'Lenovo','Computers');
INSERT INTO products VALUES (0,'Amazon Fire','49.9','USD','Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.',0,0);
INSERT INTO products VALUES (1,'Lenovo IdeaPad Miix 700','479','USD','Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.',1,1);
INSERT INTO products VALUES (2,'Amazon Fire HD 8','89','USD','Amazon''s latest Fire HD 8 tablet is a great value for media consumption.',0,0);


