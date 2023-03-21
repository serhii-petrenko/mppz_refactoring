INSERT INTO customers VALUES (1, 'Serhii', 'P', 'serhii.p@gmail.com');
INSERT INTO customers VALUES (2, 'John', 'Deer', 'jdeer@test.com');
INSERT INTO customers VALUES (3, 'Samanta', 'Cruz', 'samanta.cruz@gmail.com');

INSERT INTO products VALUES (1, 'Product 1', 100.10);
INSERT INTO products VALUES (2, 'Product 2', 99);
INSERT INTO products VALUES (3, 'Product 3', 30.5);

INSERT INTO orders VALUES (default, 1, 1000);
INSERT INTO orders VALUES (default, 3, 100);
INSERT INTO orders VALUES (default, 1, 10);

INSERT INTO order_product_map VALUES (default, 1, 1);
INSERT INTO order_product_map VALUES (default, 1, 3);
INSERT INTO order_product_map VALUES (default, 2, 3);
INSERT INTO order_product_map VALUES (default, 3, 2);