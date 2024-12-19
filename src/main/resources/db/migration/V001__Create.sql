CREATE TABLE customer
(
    id          varchar2(36)  not null primary key,
    first_name  varchar2(100) not null,
    last_name   varchar2(100) not null,
    street      varchar2(100) not null,
    postal_code varchar2(20)  not null,
    city        varchar2(100) not null
);

CREATE TABLE product
(
    id    varchar2(36)  not null primary key,
    name  varchar2(100) not null,
    price number(9, 2)  not null
);

CREATE TABLE purchase_order
(
    id          varchar2(36) not null primary key,
    order_date  timestamp    not null,

    customer_id varchar2(36) not null references customer (id)
);

CREATE TABLE order_item
(
    id                varchar2(36) not null primary key,
    quantity          number(3)    not null,

    purchase_order_id varchar2(36) not null references purchase_order (id),
    product_id        varchar2(36) not null references product (id)
);
