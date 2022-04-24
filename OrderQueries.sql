DROP TABLE IF EXISTS `Orders`;
create table `Orders`(`orderNumber` int primary key AUTO_INCREMENT, `price` float, `greetingCard` varchar(256), `color` varchar(32), 
					  `dOrder` varchar(256), `shop` varchar(32), `date` timestamp, `orderDate` timestamp);
                      
insert into Orders (orderNumber, price, greetingCard, color, dOrder, shop, date, orderDate) values (default, 50, 'Hello', 'Red', 'Valentines roses', 'Haifa', now(), DATE_ADD(NOW(), INTERVAL 30 MINUTE));
insert into Orders (orderNumber, price, greetingCard, color, dOrder, shop, date, orderDate) values (default, 20, 'Greetings', 'Yellow', 'Yellow', 'Karmiel', now(), DATE_ADD(NOW(), INTERVAL 7 DAY));
insert into Orders (orderNumber, price, greetingCard, color, dOrder, shop, date, orderDate) values (default, 9.99, 'DEAL', 'Purple', 'Royal', 'Haifa', now(), DATE_ADD(NOW(), INTERVAL 3 DAY));
insert into Orders (orderNumber, price, greetingCard, color, dOrder, shop, date, orderDate) values (default, 35, 'Happy birthday!', 'Mixed', 'Birthday flowers', 'Karmiel', now(), DATE_ADD(NOW(), INTERVAL 5 DAY));
insert into Orders (orderNumber, price, greetingCard, color, dOrder, shop, date, orderDate) values (default, 999, 'WEDDING!!!', 'Bridal', 'Bridal Bouquet', 'Karmiel', now(), DATE_ADD(NOW(), INTERVAL 30 DAY));


insert into Orders (price, greetingCard, color, dOrder, shop, date, orderDate) values (94.9, 'refresh', 'Yellow', 'blaaaa', 'nazareth', now(), now());

SELECT * FROM Orders;