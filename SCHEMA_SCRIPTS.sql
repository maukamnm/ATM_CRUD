DROP TABLE IF EXISTS `User`.`CfmAtm`;
DROP TABLE IF EXISTS `Bank_accnt`.`CfmAtm`;
DROP TABLE IF EXISTS `Transaction`.`CfmAtm`;
DROP TABLE IF EXISTS `Bill`.`CfmAtm`; -- Add transaction when you pay the bill. 

-- If I need to change something after I have created tables, 
-- this allows it to be recreated

DROP DATABASE IF EXISTS `CfmAtm`;

SET FOREIGN_KEY_CHECKS = 0;
CREATE DATABASE `CfmAtm` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `CfmAtm`;

CREATE TABLE `User` (
`User_ID` int unsigned NOT NULL AUTO_INCREMENT,
`CC_number` varchar(16) NOT NULL,
`Pin` varchar(4) NOT NULL,
`Fist_name` varchar(25) NOT NULL,
`Last_name` varchar(25) NOT NULL,
`Phone` varchar(10) NOT NULL,
`Email` varchar(50) NOT NULL,
`U_date_of_creation`timestamp,
  PRIMARY KEY (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Bank_accnt` (
`Accnt_ID` int unsigned NOT NULL AUTO_INCREMENT,
`User_ID` int unsigned not null,
`Accnt_type` varchar(25) NOT NULL,
`Accnt_balance` varchar(10),
`BA_date_of_creation`timestamp,
  PRIMARY KEY (`Accnt_ID`),
   KEY `fk_User_ID_idx` (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Transaction` (
`Transaction_ID` int unsigned NOT NULL AUTO_INCREMENT,
`User_ID`int unsigned NOT NULL,
`Account_ID` int unsigned NOT NULL,
`sign` varchar(1) NOT NULL, -- + or -
`Amount_trans` varchar(10) NOT NULL,
`T_date_of_creation`timestamp,
PRIMARY KEY (`Transaction_ID`),
KEY `fk_User_ID_idx` (`User_ID`),
KEY `fk_Account_ID_idx` (`Account_ID`),
  -- constraints for stopping deletion of an entity
 CONSTRAINT `fk_User_User_ID` FOREIGN KEY (`User_ID`) REFERENCES `User` (`User_ID`),
 CONSTRAINT `fk_Account_ID` FOREIGN KEY (`Account_ID`) REFERENCES `Bank_accnt` (`Accnt_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Bill` (
`Bill_ID` int unsigned NOT NULL AUTO_INCREMENT,
`User_ID` int unsigned not null,
`Accnt_type` varchar(25) NOT NULL,
`Accnt_balance` varchar(10),
`BA_date_of_creation`timestamp,
  PRIMARY KEY (`Accnt_ID`),
   KEY `fk_User_ID_idx` (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table Student
--
SET AUTOCOMMIT=0;
INSERT INTO User VALUES
(1, '1111111111111111', '1111', 'Mara', 'Munoz', '1112223333', 'maramunozdev@gmail.com','2020-11-19 15:34:00'),
(2, '2222222222222222', '2222', 'Teresa', 'May', '4445556666', 'tmay@cfms4.com','2020-11-19 15:34:00'),
(3, '3333333333333333', '3333', 'Matthew', 'DePaul', '7778889999', 'mdpaul@cfms4.com', '2020-11-19 15:34:00'),
(4, '4444444444444444', '4444', 'Arun', 'Vishwanatha', '9991112222', 'avishwanatha@cfms4.com','2020-11-19 15:34:00');

COMMIT;
--
-- Dumping data for table Bank_accnt
--
-- what is autocommit?
SET AUTOCOMMIT=0;
INSERT INTO Bank_accnt VALUES
-- account user accnt_type accnt_balance timestamp
(1, '1', 'c', 13000, '2020-11-19 15:34:00'),
(2, '1', 's', 15000, '2020-11-19 15:34:00'),
(3, '2', 'c', 2000, '2020-11-19 15:34:00'),
(4, '2', 's', 20000, '2020-11-19 15:34:00'),
(5, '3', 'c', 1000, '2020-11-19 15:34:00'),
(6, '3', 's', 8000, '2020-11-19 15:34:00'),
(7, '4', 'c', 2000, '2020-11-19 15:34:00'),
(8, '4', 's', 11000, '2020-11-19 15:34:00');
COMMIT;
--
-- Dumping data for table Appointment
--
SET AUTOCOMMIT=0;
INSERT INTO Transaction VALUES

(1,1,1, 'm', '50','2020-11-20 15:34:00'),
(2, 1, 1, 'm', 125,'2020-11-19 15:34:00'),
(3, 1, 1, 'a', 90,'2020-11-19 15:34:00'),
(4, 1, 1, 'a', 345,'2020-11-19 15:34:00'),
(5, 1, 2, 'a', 207,'2020-11-19 15:34:00'),
(6, 1, 2, 'm', 103, '2020-11-19 15:34:00'),
(7, 1, 2, 'a', 90,'2020-11-19 15:34:00'),
(8, 1, 2, 'm', 10,'2020-11-19 15:34:00'),
 -- next users transactions
 (9, 2, 1, 'a', 50, '2020-11-19 15:34:00'),
(10, 2, 1, 'm', 190, '2020-11-19 15:34:00'),
(11, 2, 1, 'a', 902, '2020-11-19 15:34:00'),
(12, 2, 1, 'm', 10, '2020-11-19 15:34:00'),
(13, 2, 2, 'a', 540, '2020-11-19 15:34:00'),
(14, 2, 2, 'm', 102, '2020-11-19 15:34:00'),
(15, 2, 2, 'a', 90, '2020-11-19 15:34:00'),

(16, 3, 1, 'a', 50, '2020-11-19 15:34:00'),
(17, 3, 1, 'm', 100, '2020-11-19 15:34:00'),
(18, 3, 1, 'a', 90, '2020-11-19 15:34:00'),
(19, 3, 1, 'm', 310, '2020-11-19 15:34:00'),
(20, 3, 2, 'a', 54, '2020-11-19 15:34:00'),
(21, 3, 2, 'm', 160, '2020-11-19 15:34:00'),
(22, 3, 2, 'm', 908, '2020-11-19 15:34:00'),
(23, 3, 2, 'm', 103, '2020-11-19 15:34:00'),

(24, 4, 1, 'a', 50, '2020-11-19 15:34:00'),
(25, 4, 1, 'm', 170, '2020-11-19 15:34:00'),
(26, 4, 1, 'm', 50, '2020-11-19 15:34:00'),
(27, 4, 1, 'a', 120, '2020-11-19 15:34:00'),
(28, 4, 2, 'm', 280, '2020-11-19 15:34:00'),
(29, 4, 2, 'm', 500, '2020-11-19 15:34:00'),
(30, 4, 2, 'a', 620, '2020-11-19 15:34:00'),
(31, 4, 2, 'm', 870, '2020-11-19 15:34:00'),

(32, 2, 2, 'm', 10, '2020-11-19 15:34:00');
COMMIT;
