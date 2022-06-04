DROP TABLE IF EXISTS `User_Orders`;
DROP TABLE IF EXISTS `Branch_Workers`;
DROP TABLE IF EXISTS `Discounts_Products`;
DROP TABLE IF EXISTS `Orders_Products`;
DROP TABLE IF EXISTS `CatalogItemInBranch`;
DROP TABLE IF EXISTS `Orders_Discounts`;
DROP TABLE IF EXISTS `Deliveries`;
DROP TABLE IF EXISTS `Survey_Answers`;
DROP TABLE IF EXISTS `Surveys`;
DROP TABLE IF EXISTS `Orders`;
DROP TABLE IF EXISTS `Discounts`;
DROP TABLE IF EXISTS `Branches`;
DROP TABLE IF EXISTS `Credit_Cards`;
DROP TABLE IF EXISTS `Complaints`;
DROP TABLE IF EXISTS `Users`;
DROP TABLE IF EXISTS `Locations`;
DROP TABLE IF EXISTS `Items_In_Product`;
DROP TABLE IF EXISTS `Catalog`;


CREATE TABLE `Users`(`userId` INT primary key, `username` varchar(20) NOT NULL UNIQUE, `password` varchar(64) NOT NULL, 
					  `firstName` varchar(20) NOT NULL,`lastName` varchar(20) NOT NULL, `emailAddress` varchar(32) NOT NULL, `phoneNumber` varchar(20) NOT NULL,
                      `role` varchar(30) NOT NULL, `status` varchar(20) NOT NULL, `credit` float default 0, `isLoggedIn` Boolean, `lastLoginDate` timestamp);
                      
Update Users SET isLoggedIn=true, lastLoginDate=now() WHERE username='Hadi';

        
CREATE TABLE `Catalog`(`catalogId` INT primary key AUTO_INCREMENT, `productName` varchar(256) NOT NULL, `price` float NOT NULL, `image` MEDIUMBLOB,
                       `type` varchar(50) NOT NULL, `primaryColor` varchar(20), `productOrItem` varchar(1) NOT NULL DEFAULT 'P');
                       
CREATE TABLE `Items_In_Product`(`itemId` INT NOT NULL, `productId` INT NOT NULL, `itemQuantityInProduct` float NOT NULL, PRIMARY KEY (itemId, productId),
								FOREIGN KEY (itemId) REFERENCES Catalog(catalogId), FOREIGN KEY (productId) REFERENCES Catalog(catalogId));
                                
CREATE TABLE `Locations`(`locationId` INT PRIMARY KEY AUTO_INCREMENT, `city` varchar(30) NOT NULL, `zipCode` INT, 
						 `street` varchar(30), `notes` varchar(256));
                         
CREATE TABLE `Branches`(`branchId` INT PRIMARY KEY AUTO_INCREMENT, `managerId` INT, `branchName` varchar(50), `locationId` INT NOT NULL, 
						FOREIGN KEY (locationId) REFERENCES Locations(locationId), FOREIGN KEY (managerId) REFERENCES Users(userId));
                        
CREATE TABLE `Branch_Workers`(`branchId` INT, `workerId` INT, PRIMARY KEY(branchId, workerId), 
							  FOREIGN KEY (branchId) REFERENCES Branches(branchId), FOREIGN KEY (workerId) REFERENCES Users(userId));
                              
CREATE TABLE `CatalogItemInBranch`(`catalogId` INT NOT NULL, `branchId` INT NOT NULL, `quantityInStock` INT NOT NULL default 0, PRIMARY KEY(catalogId, branchId),
									FOREIGN KEY (catalogId) REFERENCES Catalog(catalogId), FOREIGN KEY (branchId) REFERENCES Branches(branchId));
                
CREATE TABLE `Orders`(`orderId` INT primary key AUTO_INCREMENT, `userId` INT NOT NULL, `branchId` INT NOT NULL,
					  FOREIGN KEY (userId) REFERENCES Users(userId), FOREIGN KEY (branchId) REFERENCES Branches(branchId),
                      `orderStatus` varchar(32),
					  `totalCost` float, `greetingCard` varchar(256), `color` varchar(32), 
					  `details` varchar(256), `orderDate` timestamp, `deliveryDate` timestamp);

CREATE TABLE `Orders_Products` (`orderId` INT NOT NULL, `catalogId` INT NOT NULL, PRIMARY KEY (orderId, catalogId), `quantity` INT NOT NULL,
								FOREIGN KEY (orderId) REFERENCES Orders(orderId), FOREIGN KEY (catalogId) REFERENCES catalogiteminbranch(catalogId));

CREATE TABLE `User_Orders`(`userId` INT NOT NULL, `orderId` INT NOT NULL, PRIMARY KEY(userId, orderId),
		FOREIGN KEY (userId) REFERENCES Users(userId) ON DELETE CASCADE, 
        FOREIGN KEY (orderId) REFERENCES Orders(orderId) ON DELETE CASCADE) ;
        
CREATE TABLE `Credit_Cards`(`creditCardId` INT PRIMARY KEY AUTO_INCREMENT, `customerId` INT NOT NULL, 
							`creditCardNumber` INT NOT NULL, `cvv` INT NOT NULL, `expirationDate` timestamp, 
							`cardHolderName` varchar(50) NOT NULL,
		FOREIGN KEY (customerId) REFERENCES Users(userId) ON DELETE CASCADE);

CREATE TABLE `Discounts`(`discountId` INT PRIMARY KEY AUTO_INCREMENT, `discountStartDate` timestamp NOT NULL, `discountEndDate` timestamp NOT NULL,
						 `discountName` varchar(128), `discountValue` float NOT NULL, `discountType` varchar(1) NOT NULL);
                         
CREATE TABLE `Discounts_Products`(`catalogId` INT NOT NULL, `branchId` INT NOT NULL, `discountId` INT NOT NULL, PRIMARY KEY(catalogId, branchId, discountId), 
							  FOREIGN KEY (catalogId, branchId) REFERENCES CatalogItemInBranch(catalogId, branchId), FOREIGN KEY (discountId) REFERENCES Discounts(discountId));
                              
CREATE TABLE `Orders_Discounts`(`orderId` INT NOT NULL, `discountId` INT NOT NULL, PRIMARY KEY(orderId, discountId), 
							  FOREIGN KEY (orderId) REFERENCES Orders(orderId), FOREIGN KEY (discountId) REFERENCES Discounts(discountId));                              
                              
CREATE TABLE `Deliveries`(`orderId` INT NOT NULL, `recipientName` varchar(50), `recipientPhoneNumber` varchar(20), `locationId` INT NOT NULL, `delivered` BOOLEAN,
							PRIMARY KEY(orderId), FOREIGN KEY (orderId) REFERENCES Orders(orderId), FOREIGN KEY (locationId) REFERENCES Locations(locationId));  
                            
CREATE TABLE `Surveys`(`surveyId` INT PRIMARY KEY AUTO_INCREMENT, `specialistId` INT, `surveyDate` timestamp NOT NULL, `analysisResults` BLOB,
						 `q1` varchar(1024),`q2` varchar(1024),`q3` varchar(1024),`q4` varchar(1024),`q5` varchar(1024),`q6` varchar(1024));
                                                   
CREATE TABLE `Survey_Answers`(`answerId` INT PRIMARY KEY AUTO_INCREMENT, `customerId` INT NOT NULL, `orderId` INT NOT NULL, `surveyId` INT NOT NULL,
							  `a1` TINYINT NOT NULL, 
                              `a2` TINYINT NOT NULL,
                              `a3` TINYINT NOT NULL,
                              `a4` TINYINT NOT NULL,
                              `a5` TINYINT NOT NULL, 
                              `a6` TINYINT NOT NULL, 
                              CONSTRAINT val_range CHECK(a1>=1 AND a1<=10 AND a2>=1 AND a2<=10 AND a3>=1 AND a3<=10 AND a4>=1 AND a4<=10 AND a5>=1 AND a5<=10 AND a6>=1 AND a6<=10),
							  FOREIGN KEY (surveyId) REFERENCES Surveys(surveyId), 
                              FOREIGN KEY (customerId) REFERENCES Users(userId), 
                              FOREIGN KEY (orderId) REFERENCES Orders(orderId));                              
                              
CREATE TABLE `Complaints`(`complaintId` INT PRIMARY KEY AUTO_INCREMENT, `customerId` INT NOT NULL,`customerServiceEmployeeId` INT NOT NULL,
						  `complaintDetails` varchar(2048), `complaintResult` varchar(2048), `submissionTime` timestamp NOT NULL, `wasHandled` Boolean,
						FOREIGN KEY (customerId) REFERENCES Users(userId), FOREIGN KEY (customerServiceEmployeeId) REFERENCES Users(userId));
                                                  


INSERT INTO `zlig13`.`catalog` (`productName`, `price`, `type`, `primaryColor`, `productOrItem`) VALUES ('Rose', '5', 'Flower', 'Red', 'I');
INSERT INTO `zlig13`.`catalog` (`productName`, `price`, `type`, `primaryColor`, `productOrItem`) VALUES ('Cactus', '199', 'FlowerPot', 'Green', 'P');
INSERT INTO `zlig13`.`catalog` (`productName`, `price`, `type`, `primaryColor`, `productOrItem`) VALUES ('Bridal Flowers', '500', 'Bouquet', 'Red', 'P');
INSERT INTO `zlig13`.`catalog` (`productName`, `price`, `type`, `primaryColor`, `productOrItem`) VALUES ('Lemon Sapling', '120', 'Seedling', 'None', 'I');
INSERT INTO Users (userId, username, password, firstName, lastName, emailAddress, phoneNumber, role, status) values ('123456789', 'Hadi','123','Hadi','Danial','hadi@gmail.com','05223113','Customer','Frozen');
INSERT INTO Users (userId, username, password, firstName, lastName, emailAddress, phoneNumber, role, status) values ('987654321', 'Yosef','bestpassword456','Yosef','Awad','yosef@gmail.com','052231132','BranchManager','Active');
INSERT INTO Users (userId, username, password, firstName, lastName, emailAddress, phoneNumber, role, status) values ('1', 'amr','123','Amr','Kalany','AmrKal@gmail.com','0504707027','CEO','Active');

INSERT INTO Locations (city, zipCode, street) VALUES ('Haifa', 1234, '123 Street');
INSERT INTO Locations (city, zipCode, street) VALUES ('Karmiel', 5678, 'Braude Street');
INSERT INTO Locations (city, zipCode, street) VALUES ('Tel-Aviv', 9012, 'Dizengoff');
INSERT INTO Branches (managerId, branchName, locationId) VALUES (123456789, 'Haifa', 1);
INSERT INTO Branches (managerId, branchName, locationId) VALUES (987654321, 'Karmiel', 2);

             
INSERT INTO Orders (orderId, userId, branchId, orderStatus, totalCost, greetingCard, color, details, orderDate, deliveryDate) values (default, 123456789, 1, 'Pending', 15, 'Hello', 'Red', 'Valentines roses', now(), DATE_ADD(NOW(), INTERVAL 30 MINUTE));
INSERT INTO Orders (orderId, userId, branchId, orderStatus, totalCost, greetingCard, color, details, orderDate, deliveryDate) values (default, 123456789, 1, 'Pending', 15, 'Greetings', 'Yellow', 'Yellow', now(), DATE_ADD(NOW(), INTERVAL 7 DAY));
INSERT INTO Orders (orderId, userId, branchId, orderStatus, totalCost, greetingCard, color, details, orderDate, deliveryDate) values (default, 123456789, 1, 'Pending', 15, 'DEAL', 'Purple', 'Royal', now(), DATE_ADD(NOW(), INTERVAL 3 DAY));
INSERT INTO Orders (orderId, userId, branchId, orderStatus, totalCost, greetingCard, color, details, orderDate, deliveryDate) values (default, 987654321, 1, 'Pending', 15, 'Happy birthday!', 'Mixed', 'Birthday flowers', now(), DATE_ADD(NOW(), INTERVAL 5 DAY));
INSERT INTO Orders (orderId, userId, branchId, orderStatus, totalCost, greetingCard, color, details, orderDate, deliveryDate) values (default, 987654321, 1, 'Pending', 15, 'WEDDING!!!', 'Bridal', 'Bridal Bouquet', now(), DATE_ADD(NOW(), INTERVAL 30 DAY));

INSERT INTO catalogiteminbranch (catalogId, branchId, quantityInStock) VALUES (1,1,5);
INSERT INTO catalogiteminbranch (catalogId, branchId, quantityInStock) VALUES (3,1,9);
INSERT INTO catalogiteminbranch (catalogId, branchId, quantityInStock) VALUES (4,1,17);
INSERT INTO catalogiteminbranch (catalogId, branchId, quantityInStock) VALUES (1,2,3);
INSERT INTO User_Orders (userId, orderId) values (123456789, 5);
INSERT INTO Surveys (surveyDate, q1, q2, q3, q4, q5, q6) VALUES (now(), 'q1','q2','q3','q4','q5','q6');
INSERT INTO Surveys (surveyDate, q1, q2, q3, q4, q5, q6) VALUES (now(), 'q7','q8','q9','q10','q11','q12');
INSERT INTO Surveys (surveyDate, q1, q2, q3, q4, q5, q6) VALUES (now(), 'q13','q14','q15','q16','q17','q18');
INSERT INTO Survey_Answers (customerId, orderId, surveyId, a1, a2, a3, a4, a5, a6) VALUES (1,1,1,1,2,3,4,5,10);
INSERT INTO Survey_Answers (customerId, orderId, surveyId, a1, a2, a3, a4, a5, a6) VALUES (1,1,2,5,4,8,1,1,1);
INSERT INTO Survey_Answers (customerId, orderId, surveyId, a1, a2, a3, a4, a5, a6) VALUES (1,1,3,6,5,3,4,9,7);
INSERT INTO Complaints (customerId, customerServiceEmployeeId, complaintDetails, complaintResult, submissionTime, wasHandled) VALUES (123456789,987654321, 'YOUR SERVICE SUCKS', 'GO HOME', now(), true);
INSERT INTO Complaints (customerId, customerServiceEmployeeId, complaintDetails, complaintResult, submissionTime, wasHandled) VALUES (987654321,123456789, 'help me pls', 'nope', now(), false);
SELECT * FROM catalog join catalogiteminbranch WHERE catalog.catalogId = catalogiteminbranch.catalogId AND catalogiteminbranch.branchId = 1 AND catalogiteminbranch.quantityInStock > 6;
-- SELECT * FROM Orders;
