INSERT INTO restaurant (name) VALUES ('Restaurant 1');
INSERT INTO restaurant (name) VALUES ('Restaurant 2');
INSERT INTO restaurant (name) VALUES ('Restaurant 3');
INSERT INTO customer (name, email) VALUES ('anna', 'anna@gmail.com');
INSERT INTO customer (name, email) VALUES ('bela', 'bela@gmail.com');
INSERT INTO customer (name, email) VALUES ('lali', 'lali@gmail.com');
INSERT INTO item (name, description, price, restaurant_id) VALUES
('Pizza Margherita', 'Classic pizza with tomato sauce and mozzarella cheese', 12, 1),
('Spaghetti Carbonara', 'Pasta dish with bacon, egg, and parmesan cheese', 10, 1),
('Caesar Salad', 'Romaine lettuce, croutons, and parmesan cheese', 8, 2),
('Steak Frites', 'Grilled steak with French fries', 20, 2),
('Fish and Chips', 'Battered fish and French fries', 15, 2);
INSERT INTO "order" (customer, restaurant, status) VALUES (1, 2, 'RECEIVED');

