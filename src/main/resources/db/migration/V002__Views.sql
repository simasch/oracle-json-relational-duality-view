CREATE JSON RELATIONAL DUALITY VIEW purchase_order_view AS
SELECT JSON {'_id'       : o.id,
             'orderDate' : o.order_date,
             'customer'  : ( SELECT JSON {'_id'         : c.id,
                                          'firstName'   : c.first_name,
                                          'lastName'    : c.last_name,
                                           'street'     : c.street,
                                           'postalCode' : c.postal_code,
                                           'city'       : c.city }
                             FROM customer c
                             WHERE c.id = o.customer_id ),
             'items'     : [ SELECT JSON {'_id'      : i.id,
                                          'quantity' : i.quantity,
                                          'product'  : ( SELECT JSON {'_id' : p.id,
                                                                    'name'  : p.name,
                                                                    'price' : p.price }
                                                         FROM product p
                                                         WHERE p.id = i.product_id ) }
                             FROM order_item i
                             WHERE i.purchase_order_id = o.id ]
             }
FROM purchase_order o;
