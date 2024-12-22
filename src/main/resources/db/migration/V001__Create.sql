CREATE SEQUENCE customer_seq START WITH 1000;

CREATE TABLE customer
(
    id          NUMBER(19) DEFAULT customer_seq.NEXTVAL NOT NULL,
    first_name  VARCHAR2(100)                           NOT NULL,
    last_name   VARCHAR2(100)                           NOT NULL,
    street      VARCHAR2(100)                           NOT NULL,
    postal_code VARCHAR2(20)                            NOT NULL,
    city        VARCHAR2(100)                           NOT NULL,

    CONSTRAINT pk_customer PRIMARY KEY (id)
);

CREATE SEQUENCE product_seq START WITH 1000;

CREATE TABLE product
(
    id    NUMBER(19) DEFAULT product_seq.NEXTVAL NOT NULL,
    name  VARCHAR2(100)                          NOT NULL,
    price NUMBER(9, 2)                           NOT NULL,

    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE SEQUENCE purchase_order_seq START WITH 1000;

CREATE TABLE purchase_order
(
    id          NUMBER(19) DEFAULT purchase_order_seq.NEXTVAL NOT NULL,
    order_date  TIMESTAMP                                     NOT NULL,
    customer_id NUMBER(19)                                    NOT NULL,

    CONSTRAINT pk_purchase_order PRIMARY KEY (id),
    CONSTRAINT fk_purchase_order_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE SEQUENCE order_item_seq START WITH 1000;

CREATE TABLE order_item
(
    id                NUMBER(19) DEFAULT order_item_seq.NEXTVAL NOT NULL,
    quantity          NUMBER(3)                                 NOT NULL,
    purchase_order_id NUMBER(19)                                NOT NULL,
    product_id        NUMBER(19)                                NOT NULL,

    CONSTRAINT pk_order_item PRIMARY KEY (id),
    CONSTRAINT fk_order_item_purchase_order FOREIGN KEY (purchase_order_id) REFERENCES purchase_order (id),
    CONSTRAINT fk_order_item_product FOREIGN KEY (product_id) REFERENCES product (id)
);