DROP TABLE IF EXISTS `User_Orders`;
DROP TABLE IF EXISTS `Branch_Workers`;
DROP TABLE IF EXISTS `Discounts_Products`;
DROP TABLE IF EXISTS `CatalogItemInBranch`;
DROP TABLE IF EXISTS `Orders_Discounts`;
DROP TABLE IF EXISTS `Discounts`;
DROP TABLE IF EXISTS `Branches`;
DROP TABLE IF EXISTS `Users`;
DROP TABLE IF EXISTS `Locations`;
DROP TABLE IF EXISTS `Items_In_Product`;
DROP TABLE IF EXISTS `Catalog`;
DROP TABLE IF EXISTS `Orders`;

create table `Orders`(`orderId` int primary key AUTO_INCREMENT, `price` float, `greetingCard` varchar(256), `color` varchar(32), 
					  `dOrder` varchar(256), `shop` varchar(32), `date` timestamp, `orderDate` timestamp);
                      
create table `Users`(`userId` int primary key AUTO_INCREMENT, `username` varchar(20) NOT NULL UNIQUE, `password` varchar(64) NOT NULL, 
					  `firstName` varchar(20) NOT NULL,`lastName` varchar(20) NOT NULL, `emailAddress` varchar(32) NOT NULL, `phoneNumber` varchar(20) NOT NULL,
                      `role` varchar(30) NOT NULL, `status` varchar(20) NOT NULL, `credit` float default 0, `isLoggedIn` Boolean, `lastLoginDate` timestamp);
                      
Update Users SET isLoggedIn=true, lastLoginDate=now() WHERE username='Hadi';

create table `User_Orders`(`userId` int NOT NULL, `orderId` int NOT NULL, PRIMARY KEY(userId, orderId),
		FOREIGN KEY (userId) REFERENCES Users(userId) ON DELETE CASCADE, 
        FOREIGN KEY (orderId) REFERENCES Orders(orderId) ON DELETE CASCADE) ;
        
create table `Catalog`(`catalogId` int primary key AUTO_INCREMENT, `productName` varchar(256) NOT NULL, `price` float NOT NULL, `image` BLOB NOT NULL,
                       `type` varchar(50) NOT NULL, `primaryColor` varchar(20), `productOrItem` varchar(1) NOT NULL DEFAULT 'P');
                       
create table `Items_In_Product`(`itemId` int NOT NULL, `productId` int NOT NULL, `itemQuantityInProduct` float NOT NULL, PRIMARY KEY (itemId, productId),
								FOREIGN KEY (itemId) REFERENCES Catalog(catalogId), FOREIGN KEY (productId) REFERENCES Catalog(catalogId));
                                
create table `Locations`(`locationId` int PRIMARY KEY AUTO_INCREMENT, `city` varchar(30) NOT NULL, `zipCode` int, 
						 `street` varchar(30), `building` varchar(30), `notes` varchar(256));
                         
create table `Branches`(`branchId` int PRIMARY KEY AUTO_INCREMENT, `managerId` int, `branchName` varchar(50), `locationId` int NOT NULL, 
						FOREIGN KEY (locationId) REFERENCES Locations(locationId), FOREIGN KEY (managerId) REFERENCES Users(userId));
                        
create table `Branch_Workers`(`branchId` int, `workerId` int, PRIMARY KEY(branchId, workerId), 
							  FOREIGN KEY (branchId) REFERENCES Branches(branchId), FOREIGN KEY (workerId) REFERENCES Users(userId));
                              
create table `CatalogItemInBranch`(`catalogId` int NOT NULL, `branchId` int NOT NULL, `quantityInStock` int NOT NULL default 0, PRIMARY KEY(catalogId, branchId),
									FOREIGN KEY (catalogId) REFERENCES Catalog(catalogId), FOREIGN KEY (branchId) REFERENCES Branches(branchId));
                       
create table `Discounts`(`discountId` int PRIMARY KEY AUTO_INCREMENT, `discountStartDate` timestamp NOT NULL, `discountEndDate` timestamp NOT NULL,
						 `discountName` varchar(128), `discountValue` float);
                         
create table `Discounts_Products`(`catalogId` int NOT NULL, `branchId` int NOT NULL, `discountId` int NOT NULL, PRIMARY KEY(catalogId, branchId, discountId), 
							  FOREIGN KEY (catalogId, branchId) REFERENCES CatalogItemInBranch(catalogId, branchId), FOREIGN KEY (discountId) REFERENCES Discounts(discountId));
                              
create table `Orders_Discounts`(`orderId` int NOT NULL, `discountId` int NOT NULL, PRIMARY KEY(orderId, discountId), 
							  FOREIGN KEY (orderId) REFERENCES Orders(orderId), FOREIGN KEY (discountId) REFERENCES Discounts(discountId));                              
                              
                              
                              

              
insert into Orders (orderId, price, greetingCard, color, dOrder, shop, date, orderDate) values (default, 50, 'Hello', 'Red', 'Valentines roses', 'Haifa', now(), DATE_ADD(NOW(), INTERVAL 30 MINUTE));
insert into Orders (orderId, price, greetingCard, color, dOrder, shop, date, orderDate) values (default, 20, 'Greetings', 'Yellow', 'Yellow', 'Karmiel', now(), DATE_ADD(NOW(), INTERVAL 7 DAY));
insert into Orders (orderId, price, greetingCard, color, dOrder, shop, date, orderDate) values (default, 9.99, 'DEAL', 'Purple', 'Royal', 'Haifa', now(), DATE_ADD(NOW(), INTERVAL 3 DAY));
insert into Orders (orderId, price, greetingCard, color, dOrder, shop, date, orderDate) values (default, 35, 'Happy birthday!', 'Mixed', 'Birthday flowers', 'Karmiel', now(), DATE_ADD(NOW(), INTERVAL 5 DAY));
insert into Orders (orderId, price, greetingCard, color, dOrder, shop, date, orderDate) values (default, 999, 'WEDDING!!!', 'Bridal', 'Bridal Bouquet', 'Karmiel', now(), DATE_ADD(NOW(), INTERVAL 30 DAY));
INSERT INTO `zlig13`.`catalog` (`productName`, `price`, `type`, `primaryColor`, `productOrItem`) VALUES ('Rose', '5', 'Flower', 'Red', 'I');
INSERT INTO `zlig13`.`catalog` (`productName`, `price`, `type`, `primaryColor`, `productOrItem`) VALUES ('Cactus', '199', 'FlowerPot', 'Green', 'P');
INSERT INTO `zlig13`.`catalog` (`productName`, `price`, `type`, `primaryColor`, `productOrItem`) VALUES ('Bridal Flowers', '500', 'Bouquet', 'Red', 'P');
INSERT INTO `zlig13`.`catalog` (`productName`, `price`, `type`, `primaryColor`, `productOrItem`) VALUES ('Lemon Sapling', '120', 'Seedling', 'None', 'I');
insert into Users (username, password, firstName, lastName, emailAddress, phoneNumber, role, status) values ('Hadi','bestpassword123','Hadi','Danial','hadi@gmail.com','05223113','CEO','Frozen');
insert into Users (username, password, firstName, lastName, emailAddress, phoneNumber, role, status) values ('Yosef','bestpassword456','Yosef','Awad','yosef@gmail.com','052231132','Manager','Active');

INSERT INTO Locations (city, zipCode, street) VALUES ('Haifa', 1234, '123 Street');
INSERT INTO Locations (city, zipCode, street) VALUES ('Karmiel', 5678, 'Braude Street');
INSERT INTO Locations (city, zipCode, street) VALUES ('Tel-Aviv', 9012, 'Dizengoff');
INSERT INTO Branches (managerId, branchName, locationId) VALUES (1, 'Haifa', 1);
INSERT INTO Branches (managerId, branchName, locationId) VALUES (2, 'Karmiel', 2);
INSERT INTO catalogiteminbranch (catalogId, branchId, quantityInStock) VALUES (1,1,5);
INSERT INTO catalogiteminbranch (catalogId, branchId, quantityInStock) VALUES (3,1,9);
INSERT INTO catalogiteminbranch (catalogId, branchId, quantityInStock) VALUES (4,1,17);
INSERT INTO catalogiteminbranch (catalogId, branchId, quantityInStock) VALUES (1,2,3);
insert into Orders (price, greetingCard, color, dOrder, shop, date, orderDate) values (94.9, 'refresh', 'Yellow', 'blaaaa', 'nazareth', now(), now());
insert into User_Orders (userId, orderId) values (1, 5);

SELECT * FROM catalog join catalogiteminbranch WHERE catalog.catalogId = catalogiteminbranch.catalogId AND catalogiteminbranch.branchId = 1 AND catalogiteminbranch.quantityInStock > 6;
-- SELECT * FROM Orders;
