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

DROP TABLE IF EXISTS `Users`;
create table `Users`(`userId` int primary key AUTO_INCREMENT, `username` varchar(20) NOT NULL UNIQUE, `password` varchar(64) NOT NULL, 
					  `firstName` varchar(20) NOT NULL,`lastName` varchar(20) NOT NULL, `emailAddress` varchar(32) NOT NULL, `phoneNumber` varchar(20) NOT NULL,
                      `role` varchar(30) NOT NULL, `status` varchar(20) NOT NULL, `credit` float default 0, `isLoggedIn` Boolean, `lastLoginDate` timestamp);
                      
insert into Users (username, password, firstName, lastName, emailAddress, phoneNumber, role, status) values ('Hadi','bestpassword123','Hadi','Danial','hadi@gmail.com','05223113','CEO','Frozen');
Update Users SET isLoggedIn=true, lastLoginDate=now() WHERE username='Hadi';

DROP TABLE IF EXISTS `User_Orders`;
create table `User_Orders`(`userId` int NOT NULL, `orderNumber` int NOT NULL, PRIMARY KEY(userId, orderNumber),
		FOREIGN KEY (userId) REFERENCES Users(userId) ON DELETE CASCADE, 
        FOREIGN KEY (orderNumber) REFERENCES Orders(orderNumber) ON DELETE CASCADE) ;
        
insert into User_Orders (userId, orderNumber) values (1, 5);