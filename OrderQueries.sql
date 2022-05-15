DROP TABLE IF EXISTS `User_Orders`;
DROP TABLE IF EXISTS `Branch_Workers`;
DROP TABLE IF EXISTS `Discounts_Products`;
DROP TABLE IF EXISTS `CatalogItemInBranch`;
DROP TABLE IF EXISTS `Orders_Discounts`;
DROP TABLE IF EXISTS `Discounts`;
DROP TABLE IF EXISTS `Branches`;
DROP TABLE IF EXISTS `Survey_Answers`;
DROP TABLE IF EXISTS `Surveys`;
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
        
create table `Catalog`(`catalogId` int primary key AUTO_INCREMENT, `productName` varchar(256) NOT NULL, `price` float NOT NULL, `image` BLOB,
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
						 `discountName` varchar(128), `discountValue` float NOT NULL, `discountType` varchar(1) NOT NULL);
                         
create table `Discounts_Products`(`catalogId` int NOT NULL, `branchId` int NOT NULL, `discountId` int NOT NULL, PRIMARY KEY(catalogId, branchId, discountId), 
							  FOREIGN KEY (catalogId, branchId) REFERENCES CatalogItemInBranch(catalogId, branchId), FOREIGN KEY (discountId) REFERENCES Discounts(discountId));
                              
create table `Orders_Discounts`(`orderId` int NOT NULL, `discountId` int NOT NULL, PRIMARY KEY(orderId, discountId), 
							  FOREIGN KEY (orderId) REFERENCES Orders(orderId), FOREIGN KEY (discountId) REFERENCES Discounts(discountId));                              
                              
                           
create table `Surveys`(`surveyId` int PRIMARY KEY AUTO_INCREMENT, `specialistId` int, `surveyDate` timestamp NOT NULL, `analysisResults` BLOB,
						 `q1` varchar(1024),`q2` varchar(1024),`q3` varchar(1024),`q4` varchar(1024),`q5` varchar(1024),`q6` varchar(1024));
                                                   
create table `Survey_Answers`(`answerId` int PRIMARY KEY AUTO_INCREMENT, `customerId` int NOT NULL, `orderId` int NOT NULL, `surveyId` int NOT NULL,
							  `a1` TINYINT NOT NULL, 
                              `a2` TINYINT NOT NULL,
                              `a3` TINYINT NOT NULL,
                              `a4` TINYINT NOT NULL,
                              `a5` TINYINT NOT NULL, 
                              `a6` TINYINT NOT NULL, 
                              CONSTRAINT val_range CHECK(a1>=-0 AND a1<=10 AND a2>=-0 AND a2<=10 AND a3>=-0 AND a3<=10 AND a4>=-0 AND a4<=10 AND a5>=-0 AND a5<=10 AND a6>=-0 AND a6<=10),
							  FOREIGN KEY (surveyId) REFERENCES Surveys(surveyId), 
                              FOREIGN KEY (customerId) REFERENCES Users(userId), 
                              FOREIGN KEY (orderId) REFERENCES Orders(orderId));                              
                              
                                           

              
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
insert into Users (username, password, firstName, lastName, emailAddress, phoneNumber, role, status) values ('Yosef','bestpassword456','Yosef','Awad','yosef@gmail.com','052231132','BranchManager','Active');

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
INSERT INTO Surveys (surveyDate, q1, q2, q3, q4, q5, q6) VALUES (now(), 'q1','q2','q3','q4','q5','q6');
INSERT INTO Surveys (surveyDate, q1, q2, q3, q4, q5, q6) VALUES (now(), 'q7','q8','q9','q10','q11','q12');
INSERT INTO Surveys (surveyDate, q1, q2, q3, q4, q5, q6) VALUES (now(), 'q13','q14','q15','q16','q17','q18');
INSERT INTO Survey_Answers (customerId, orderId, surveyId, a1, a2, a3, a4, a5, a6) VALUES (1,1,1,1,2,3,4,5,10);
INSERT INTO Survey_Answers (customerId, orderId, surveyId, a1, a2, a3, a4, a5, a6) VALUES (1,1,2,5,4,8,1,0,1);
INSERT INTO Survey_Answers (customerId, orderId, surveyId, a1, a2, a3, a4, a5, a6) VALUES (1,1,3,6,5,3,4,9,7);

SELECT * FROM catalog join catalogiteminbranch WHERE catalog.catalogId = catalogiteminbranch.catalogId AND catalogiteminbranch.branchId = 1 AND catalogiteminbranch.quantityInStock > 6;
-- SELECT * FROM Orders;
