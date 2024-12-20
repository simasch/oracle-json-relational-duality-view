-- Clear existing data
TRUNCATE TABLE order_item;
TRUNCATE TABLE purchase_order;
TRUNCATE TABLE customer;
TRUNCATE TABLE product;

-- Insert test customers
INSERT INTO customer (id, first_name, last_name, street, postal_code, city)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'Peter', 'Meier', 'Bahnhofstrasse 1', '8000', 'Zürich');

INSERT INTO customer (id, first_name, last_name, street, postal_code, city)
VALUES ('550e8400-e29b-41d4-a716-446655440001', 'Anna', 'Müller', 'Hauptstrasse 10', '3011', 'Bern');

INSERT INTO customer (id, first_name, last_name, street, postal_code, city)
VALUES ('550e8400-e29b-41d4-a716-446655440002', 'Hans', 'Weber', 'Dorfstrasse 5', '6300', 'Zug');

-- Insert test products
INSERT INTO product (id, name, price)
VALUES ('550e8400-e29b-41d4-a716-446655440003', 'Laptop Pro', 1299.99);

INSERT INTO product (id, name, price)
VALUES ('550e8400-e29b-41d4-a716-446655440004', 'Office Chair', 249.90);

INSERT INTO product (id, name, price)
VALUES ('550e8400-e29b-41d4-a716-446655440005', 'Desk Lamp', 59.90);

-- Insert test orders
INSERT INTO purchase_order (id, order_date, customer_id)
VALUES ('550e8400-e29b-41d4-a716-446655440006',
        TIMESTAMP '2024-03-19 10:30:00',
        '550e8400-e29b-41d4-a716-446655440000');

INSERT INTO purchase_order (id, order_date, customer_id)
VALUES ('550e8400-e29b-41d4-a716-446655440007',
        TIMESTAMP '2024-03-19 14:45:00',
        '550e8400-e29b-41d4-a716-446655440001');

-- Insert test order items
INSERT INTO order_item (id, quantity, purchase_order_id, product_id)
VALUES ('550e8400-e29b-41d4-a716-446655440008',
        1,
        '550e8400-e29b-41d4-a716-446655440006',
        '550e8400-e29b-41d4-a716-446655440003');

INSERT INTO order_item (id, quantity, purchase_order_id, product_id)
VALUES ('550e8400-e29b-41d4-a716-446655440009',
        2,
        '550e8400-e29b-41d4-a716-446655440006',
        '550e8400-e29b-41d4-a716-446655440004');

INSERT INTO order_item (id, quantity, purchase_order_id, product_id)
VALUES ('550e8400-e29b-41d4-a716-446655440010',
        1,
        '550e8400-e29b-41d4-a716-446655440007',
        '550e8400-e29b-41d4-a716-446655440005');

COMMIT;