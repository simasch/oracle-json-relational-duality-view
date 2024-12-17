CREATE SEQUENCE customer_seq start with 100000 increment by 50;

CREATE TABLE customer
(
    id          number(11)      not null primary key,
    first_name  varchar2(100)   not null,
    last_name   varchar2(100)   not null,
    street      varchar2(100)   not null,
    postal_code varchar2(20)    not null,
    city        varchar2(100)   not null
);

CREATE SEQUENCE product_seq start with 100000 increment by 50;

CREATE TABLE product
(
    id    number(11)      not null primary key,
    name  varchar2(100)   not null,
    price number(9,2)     not null
);

CREATE SEQUENCE purchase_order_seq start with 100000 increment by 50;

CREATE TABLE purchase_order
(
    id          number(11)  not null primary key,
    order_date  timestamp not null,

    customer_id number(11)    not null references customer (id)
);

CREATE SEQUENCE order_item_seq start with 100000 increment by 50;

CREATE TABLE order_item
(
    id                number(11)    not null primary key,
    quantity          number(3)     not null,

    purchase_order_id number(11)    not null references purchase_order (id),
    product_id        number(11)    not null references product (id)
);
