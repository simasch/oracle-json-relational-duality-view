CREATE TABLE customer
(
    id          VARCHAR2(36)  NOT NULL,
    first_name  VARCHAR2(100) NOT NULL,
    last_name   VARCHAR2(100) NOT NULL,
    street      VARCHAR2(100) NOT NULL,
    postal_code VARCHAR2(20)  NOT NULL,
    city        VARCHAR2(100) NOT NULL,

    CONSTRAINT pk_customer PRIMARY KEY (id)
);

CREATE TABLE product
(
    id    VARCHAR2(36)  NOT NULL,
    name  VARCHAR2(100) NOT NULL,
    price NUMBER(9, 2)  NOT NULL,

    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE purchase_order
(
    id          VARCHAR2(36) NOT NULL,
    order_date  TIMESTAMP    NOT NULL,
    customer_id VARCHAR2(36) NOT NULL,

    CONSTRAINT pk_purchase_order PRIMARY KEY (id),
    CONSTRAINT fk_purchase_order_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE order_item
(
    id                VARCHAR2(36) NOT NULL,
    quantity          NUMBER(3)    NOT NULL,
    purchase_order_id VARCHAR2(36) NOT NULL,
    product_id        VARCHAR2(36) NOT NULL,

    CONSTRAINT pk_order_item PRIMARY KEY (id),
    CONSTRAINT fk_order_item_purchase_order FOREIGN KEY (purchase_order_id) REFERENCES purchase_order (id),
    CONSTRAINT fk_order_item_product FOREIGN KEY (product_id) REFERENCES product (id)
);