DROP TABLE IF EXISTS `Orders`;
create table `Orders`(`orderNumber` int primary key AUTO_INCREMENT, `price` float, `greetingCard` varchar(256), `color` varchar(32), 
					  `dOrder` varchar(256), `shop` varchar(32), `date` timestamp, `orderDate` timestamp);
                      
insert into Orders (orderNumber, price, greetingCard, color, dOrder, shop, date, orderDate) values (default, 50, 'Hello', 'Red', 'Valentine\'s roses', 'Haifa', now(), now());

insert into Orders (price, greetingCard, color, dOrder, shop, date, orderDate) values (94.9, 'refresh', 'Yellow', 'blaaaa', 'nazareth', now(), now());

SELECT * FROM Orders;