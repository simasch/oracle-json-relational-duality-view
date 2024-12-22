-- Clear existing data
TRUNCATE TABLE order_item;
TRUNCATE TABLE purchase_order;
TRUNCATE TABLE customer;
TRUNCATE TABLE product;

-- Insert test customers
INSERT INTO customer (id, first_name, last_name, street, postal_code, city)
VALUES (1, 'Peter', 'Meier', 'Bahnhofstrasse 1', '8000', 'Zürich');

INSERT INTO customer (id, first_name, last_name, street, postal_code, city)
VALUES (2, 'Anna', 'Müller', 'Hauptstrasse 10', '3011', 'Bern');

INSERT INTO customer (id, first_name, last_name, street, postal_code, city)
VALUES (3, 'Hans', 'Weber', 'Dorfstrasse 5', '6300', 'Zug');

-- Insert test products
INSERT INTO product (id, name, price)
VALUES (1, 'Laptop Pro', 1299.99);

INSERT INTO product (id, name, price)
VALUES (2, 'Office Chair', 249.90);

INSERT INTO product (id, name, price)
VALUES (3, 'Desk Lamp', 59.90);

-- Insert test orders
INSERT INTO purchase_order (id, order_date, customer_id)
VALUES (1,
        TIMESTAMP '2024-03-19 10:30:00',
        1);

INSERT INTO purchase_order (id, order_date, customer_id)
VALUES (2,
        TIMESTAMP '2024-03-19 14:45:00',
        2);

-- Insert test order items
INSERT INTO order_item (id, quantity, purchase_order_id, product_id)
VALUES (1,
        1,
        1,
        1);

INSERT INTO order_item (id, quantity, purchase_order_id, product_id)
VALUES (2,
        2,
        1,
        2);

INSERT INTO order_item (id, quantity, purchase_order_id, product_id)
VALUES (3,
        1,
        2,
        3);

COMMIT;