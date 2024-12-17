CREATE
JSON RELATIONAL DUALITY VIEW purchase_order_view AS
SELECT JSON {'_id'       : o.id,
             'orderDate' : o.order_date}
FROM purchase_order o;
