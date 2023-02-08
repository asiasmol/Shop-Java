DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS categories;





CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(20),
                       surname VARCHAR(20),
                       email VARCHAR(20),
                       password VARCHAR(20),
                       address VARCHAR(20)
);

CREATE TABLE orders (
                        id serial PRIMARY KEY,
                        user_id integer REFERENCES users(id),
                        products VARCHAR(20),
                        data date,
                        final_price integer,
                        is_paid bit
);

CREATE TABLE categories (
                            id serial PRIMARY KEY,
                            name VARCHAR(20),
                            department VARCHAR(20),
                            description VARCHAR(200)
);

CREATE TABLE supplier (
                          id serial PRIMARY KEY,
                          name VARCHAR(20),
                          description VARCHAR(200)
);
CREATE TABLE  products(
                          id SERIAL PRIMARY KEY,
                          picture_name VARCHAR(20),
                          name VARCHAR(20),
                          price integer,
                          currency_string VARCHAR(20),
                          description VARCHAR(200),
                          category_id integer REFERENCES categories(id),
                          supplier_id integer REFERENCES supplier(id)
);


