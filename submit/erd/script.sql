-- MySQL dump 10.13  Distrib 8.1.0, for macos12.6 (x86_64)
--
-- Host: 133.186.241.167    Database: nhn_academy_33
-- ------------------------------------------------------
-- Server version	8.0.25-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Address` (
                           `Address_ID` int NOT NULL AUTO_INCREMENT,
                           `User_ID` varchar(20) DEFAULT NULL,
                           `State` varchar(255) DEFAULT NULL,
                           `City` varchar(255) DEFAULT NULL,
                           `Address_line` varchar(255) DEFAULT NULL,
                           `Postal_code` varchar(20) DEFAULT NULL,
                           PRIMARY KEY (`Address_ID`),
                           KEY `User_ID` (`User_ID`),
                           CONSTRAINT `Address_ibfk_1` FOREIGN KEY (`User_ID`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Address`
--

LOCK TABLES `Address` WRITE;
/*!40000 ALTER TABLE `Address` DISABLE KEYS */;
INSERT INTO `Address` VALUES (7,'testtt','d','d','d','d'),(8,'user','광주광역시','동구','조선대학교','6165-34'),(9,'first','rhkdwn','rhkdwn','rhkdwn','2323');
/*!40000 ALTER TABLE `Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Categories`
--

DROP TABLE IF EXISTS `Categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Categories` (
                              `CategoryID` int NOT NULL AUTO_INCREMENT,
                              `CategoryName` varchar(50) DEFAULT NULL,
                              PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Categories`
--

LOCK TABLES `Categories` WRITE;
/*!40000 ALTER TABLE `Categories` DISABLE KEYS */;
INSERT INTO `Categories` VALUES (1,'조미료'),(2,'낙농물'),(3,'면류'),(4,'스낵류'),(5,'음료'),(6,'피자'),(7,'조미료'),(8,'수프'),(9,'야채');
/*!40000 ALTER TABLE `Categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderDetails`
--

DROP TABLE IF EXISTS `OrderDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `OrderDetails` (
                                `OrderID` int NOT NULL,
                                `ProductID` int NOT NULL,
                                `Quantity` int DEFAULT NULL,
                                `UnitCost` decimal(15,0) DEFAULT NULL,
                                PRIMARY KEY (`OrderID`,`ProductID`),
                                KEY `fk_name` (`ProductID`),
                                CONSTRAINT `fk_name` FOREIGN KEY (`ProductID`) REFERENCES `Products` (`ProductID`) ON DELETE CASCADE,
                                CONSTRAINT `OrderDetails_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `Orders` (`OrderID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderDetails`
--

LOCK TABLES `OrderDetails` WRITE;
/*!40000 ALTER TABLE `OrderDetails` DISABLE KEYS */;
INSERT INTO `OrderDetails` VALUES (14,2,1,19000),(14,7,7,2380),(15,1,2,348000),(17,1,1,348000),(17,2,1,19000),(18,2,3,19000),(19,3,1,9100),(20,3,1,9100),(21,6,1,5680),(22,8,1,2290),(23,8,1,2290),(24,8,1,2290),(25,8,1,2290),(26,2,1,19000),(26,8,1,2290),(27,4,1,5400),(27,8,1,2290),(28,78,1,1000),(29,7,2,2380),(29,8,1,2290),(30,1,1,348000),(30,2,3,19000),(30,55,1,4840);
/*!40000 ALTER TABLE `OrderDetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Orders` (
                          `OrderID` int NOT NULL AUTO_INCREMENT,
                          `UserID` varchar(20) DEFAULT NULL,
                          `OrderDate` datetime DEFAULT NULL,
                          `ShipDate` datetime DEFAULT NULL,
                          `Address` varchar(100) DEFAULT NULL,
                          PRIMARY KEY (`OrderID`),
                          KEY `UserID` (`UserID`),
                          CONSTRAINT `Orders_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (11,'user','2023-12-07 23:13:33','2023-12-09 23:13:33',''),(12,'user','2023-12-07 23:34:14','2023-12-09 23:34:14',''),(13,'user','2023-12-07 23:38:25','2023-12-09 23:38:25',''),(14,'user','2023-12-08 00:07:00','2023-12-10 00:07:00',''),(15,'user','2023-12-08 00:14:20','2023-12-10 00:14:20',''),(17,'testtt','2023-12-08 01:52:30','2023-12-10 01:52:30',''),(18,'testtt','2023-12-08 02:01:24','2023-12-10 02:01:24',''),(19,'testtt','2023-12-08 02:35:21','2023-12-10 02:35:21',NULL),(20,'testtt','2023-12-08 02:36:16','2023-12-10 02:36:16',NULL),(21,'testtt','2023-12-08 02:56:50','2023-12-10 02:56:50',NULL),(22,'testtt','2023-12-08 02:58:21','2023-12-10 02:58:21',''),(23,'testtt','2023-12-08 03:02:42','2023-12-10 03:02:42',''),(24,'testtt','2023-12-08 03:10:17','2023-12-10 03:10:17',''),(25,'testtt','2023-12-08 03:17:13','2023-12-10 03:17:13','d d d'),(26,'testtt','2023-12-08 04:24:09','2023-12-10 04:24:09','d d d'),(27,'testtt','2023-12-08 10:22:18','2023-12-10 10:22:18','d d d'),(28,'minseo','2023-12-08 10:36:58','2023-12-10 10:36:58',NULL),(29,'user','2023-12-08 14:54:59','2023-12-10 14:54:59','광주광역시 동구 조선대학교'),(30,'user','2023-12-08 17:13:55','2023-12-10 17:13:55','광주광역시 동구 조선대학교');
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PointHistory`
--

DROP TABLE IF EXISTS `PointHistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PointHistory` (
                                `historyId` int NOT NULL AUTO_INCREMENT,
                                `userId` varchar(50) DEFAULT NULL,
                                `transactionType` varchar(255) DEFAULT NULL,
                                `transactionAmount` decimal(15,0) DEFAULT NULL,
                                `transactionDate` timestamp NULL DEFAULT NULL,
                                PRIMARY KEY (`historyId`),
                                KEY `userId` (`userId`),
                                CONSTRAINT `PointHistory_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
                                CONSTRAINT `PointHistory_chk_1` CHECK ((`transactionType` in (_utf8mb4'입금',_utf8mb4'출금',_utf8mb4'적립')))
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PointHistory`
--

LOCK TABLES `PointHistory` WRITE;
/*!40000 ALTER TABLE `PointHistory` DISABLE KEYS */;
INSERT INTO `PointHistory` VALUES (4,'user','출금',20610,'2023-12-07 14:13:34'),(5,'user','출금',34890,'2023-12-07 14:34:14'),(6,'user','출금',34890,'2023-12-07 14:38:25'),(7,'user','출금',35660,'2023-12-07 15:07:00'),(8,'user','출금',696000,'2023-12-07 15:14:20'),(10,'testtt','출금',367000,'2023-12-07 16:52:30'),(11,'testtt','출금',57000,'2023-12-07 17:01:24'),(12,'testtt','출금',9100,'2023-12-07 17:35:21'),(13,'testtt','출금',9100,'2023-12-07 17:36:16'),(14,'testtt','출금',5680,'2023-12-07 17:56:50'),(15,'testtt','출금',2290,'2023-12-07 17:58:21'),(16,'testtt','출금',2290,'2023-12-07 18:02:42'),(17,'testtt','출금',2290,'2023-12-07 18:10:17'),(18,'testtt','출금',2290,'2023-12-07 18:17:13'),(19,'testtt','출금',2290,'2023-12-07 19:24:09'),(20,'testtt','출금',7690,'2023-12-08 01:22:18'),(21,'testtt','적립',769,'2023-12-08 01:22:18'),(22,'minseo','출금',1000,'2023-12-08 01:36:58'),(23,'minseo','적립',100,'2023-12-08 01:36:58'),(24,'test99','적립',10000,'2023-12-08 02:31:22'),(25,'pointcheck','적립',1000,'2023-12-08 04:30:36'),(26,'admin','적립',1000,'2023-12-08 04:31:30'),(28,'first','적립',10000,'2023-12-08 05:36:07'),(29,'user','출금',7050,'2023-12-08 05:55:00'),(30,'user','적립',705,'2023-12-08 05:55:00'),(31,'test999','적립',10000,'2023-12-08 05:55:58'),(32,'user','출금',409840,'2023-12-08 08:13:55'),(33,'user','적립',40984,'2023-12-08 08:13:55');
/*!40000 ALTER TABLE `PointHistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Products`
--

DROP TABLE IF EXISTS `Products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Products` (
                            `ProductID` int NOT NULL AUTO_INCREMENT,
                            `CategoryID` int DEFAULT NULL,
                            `ModelNumber` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                            `ModelName` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                            `ProductImage` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                            `UnitCost` decimal(15,0) DEFAULT NULL,
                            `Description` text,
                            PRIMARY KEY (`ProductID`),
                            KEY `fk_Products_Categories` (`CategoryID`),
                            CONSTRAINT `fk_Products_Categories` FOREIGN KEY (`CategoryID`) REFERENCES `Categories` (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Products`
--

LOCK TABLES `Products` WRITE;
/*!40000 ALTER TABLE `Products` DISABLE KEYS */;
INSERT INTO `Products` VALUES (1,1,'A0000','고춧가루','http://snongga.com/web/product/big/201901/c191b56cd27b387a6ede9c8c71dd39a5.jpg',348000,'태양초'),(2,1,'A0001','고추장','https://img.danawa.com/prod_img/500000/816/635/img/3635816_1.jpg?shrink=330:*&_v=20180725150426',19000,'청정원 순창 우리쌀 찰고추장'),(3,1,'A0002','된장','https://www.costco.co.kr/medias/sys_master/images/h65/h40/134176464797726.jpg',9100,'해찬들 재래식'),(4,1,'A0003','쌈장','https://img.danawa.com/prod_img/500000/409/043/img/10043409_1.jpg?_v=20191202114905',5400,'청정원 순창 양념듬뿍쌈장'),(5,1,'A0004','양조간장','https://www.sempio.com/image/RA/XF/202109151458433494b38bff3-9c02-45ce-b3df-a6f080bce175.png',7900,'양조간장501'),(6,1,'A0005','진간장','https://www.sempio.com/image/BZ/SN/202110271626594363855ef2d-ae92-4c3f-a855-572d6878b306.png',5680,'청정원 햇살담은 진간장 골드'),(7,1,'A0006','설탕','https://sitem.ssgcdn.com/44/02/66/item/1000050660244_i1_1100.jpg',2380,'백설 백설탕'),(8,1,'A0007','소금','http://www.thessan.com/shopimages/thessancom/0060060001492.jpg?1480644434',2290,'해표 꽃소금'),(9,1,'A0008','미원','https://www.miwon.co.kr/wp-content/uploads/2022/05/prdt_003.jpg',13900,'발효 미원'),(10,1,'A0009','물엿','src/main/webapp/resources/no-image.png',4680,'옛날 물엿'),(11,1,'A0010','올리고당','src/main/webapp/resources/no-image.png',6480,'청정원 요리올리고당'),(12,1,'A0011','케찹','src/main/webapp/resources/no-image.png',3180,'토마토케챂'),(13,1,'A0012','마요네즈','src/main/webapp/resources/no-image.png',8680,'청정원 고소한 마요네즈'),(14,1,'A0013','마아가린','src/main/webapp/resources/no-image.png',2290,'옥수수 마아가린'),(15,1,'A0014','식초','src/main/webapp/resources/no-image.png',2880,'사과식초'),(16,1,'A0015','식용유','src/main/webapp/resources/no-image.png',5200,'해표 식용유'),(17,1,'A0016','참기름','src/main/webapp/resources/no-image.png',8500,'백설 진한 참기름'),(18,1,'A0017','고추씨기름','src/main/webapp/resources/no-image.png',2180,'옛날 고추맛기름'),(19,1,'A0018','멸치액젓','src/main/webapp/resources/no-image.png',5280,'658ml 하선정'),(20,1,'A0019','조미료','src/main/webapp/resources/no-image.png',12580,'백설 쇠고기 다시다'),(21,2,'B0000','쇠고기','src/main/webapp/resources/no-image.png',12990,'정육(등심)상등급'),(22,2,'B0001','돼지고기','src/main/webapp/resources/no-image.png',3590,'정육(삼겹살)상등급'),(23,2,'B0002','계란','src/main/webapp/resources/no-image.png',7990,'신선한 목초란'),(24,2,'B0003','닭고기','src/main/webapp/resources/no-image.png',8990,'육계'),(25,2,'B0004','우유','src/main/webapp/resources/no-image.png',2990,'흰 우유(나 100%)'),(26,2,'B0005','버터','src/main/webapp/resources/no-image.png',9990,'프리미엄 홀버터'),(27,2,'B0006','치즈','src/main/webapp/resources/no-image.png',9980,'체다치즈(20매입)'),(28,2,'B0007','햄','src/main/webapp/resources/no-image.png',10490,'주부9단 살코기햄'),(29,2,'B0008','조제분유','src/main/webapp/resources/no-image.png',27800,'남양분유 임페리얼 XO1'),(30,2,'B0009','영유야식','src/main/webapp/resources/no-image.png',15900,'임페리얼 XO 닥터'),(31,3,'C0000','밀가루','src/main/webapp/resources/no-image.png',1840,'1kg 곰표 중력'),(32,3,'C0001','삼양라면','src/main/webapp/resources/no-image.png',3680,'120g'),(33,3,'C0002','불닭볶음면','src/main/webapp/resources/no-image.png',5100,'140g'),(34,3,'C0003','맛있는라면','src/main/webapp/resources/no-image.png',4730,'115g'),(35,3,'C0004','신라면','src/main/webapp/resources/no-image.png',3900,'120g'),(36,3,'C0005','너구리','src/main/webapp/resources/no-image.png',4500,'120g'),(37,3,'C0006','오징어짬뽕','src/main/webapp/resources/no-image.png',4880,'124g'),(38,3,'C0007','사리곰탕면','src/main/webapp/resources/no-image.png',4880,'110g'),(39,3,'C0008','무파마탕면','src/main/webapp/resources/no-image.png',4580,'122g'),(40,3,'C0009','안성탕면','src/main/webapp/resources/no-image.png',3700,'125g'),(41,3,'C0010','짜파게티','src/main/webapp/resources/no-image.png',4880,'140g'),(42,3,'C0011','감자면','src/main/webapp/resources/no-image.png',5680,'117g'),(43,3,'C0012','생생우동','src/main/webapp/resources/no-image.png',4880,'140g'),(44,3,'C0013','사발면','src/main/webapp/resources/no-image.png',1160,'114g'),(45,3,'C0014','컵누들','src/main/webapp/resources/no-image.png',1380,'37.8g 매콤한맛'),(46,3,'C0015','열라면','src/main/webapp/resources/no-image.png',3580,'120g'),(47,3,'C0016','진라면','src/main/webapp/resources/no-image.png',3580,'120g'),(48,3,'C0017','진짬뽕','src/main/webapp/resources/no-image.png',3580,'130g'),(49,3,'C0018','참깨라면','src/main/webapp/resources/no-image.png',4480,'115g'),(50,3,'C0019','스낵면','src/main/webapp/resources/no-image.png',3180,'108g'),(51,3,'C0020','라면사리','src/main/webapp/resources/no-image.png',1750,'120g'),(52,3,'C0021','옛날자른당면','src/main/webapp/resources/no-image.png',4780,'300g'),(53,3,'C0022','옛날국수(소면)','src/main/webapp/resources/no-image.png',3550,'900g'),(54,3,'C0023','팔도비빔면','src/main/webapp/resources/no-image.png',3700,'130g'),(55,3,'C0024','틈새라면','src/main/webapp/resources/no-image.png',4840,'120g'),(56,3,'C0025','육개장칼국수','src/main/webapp/resources/no-image.png',5450,'120.9g'),(57,3,'C0026','물냉면','src/main/webapp/resources/no-image.png',4790,'540g'),(58,3,'C0027','비빔냉면','src/main/webapp/resources/no-image.png',4790,'540g'),(59,4,'D0000','새우깡','src/main/webapp/resources/no-image.png',1100,'90g'),(60,4,'D0001','감자깡','src/main/webapp/resources/no-image.png',1360,'75g'),(61,4,'D0002','오징어집','src/main/webapp/resources/no-image.png',1360,'78g'),(62,4,'D0003','고구마깡','src/main/webapp/resources/no-image.png',1360,'83g'),(63,4,'D0004','감자깡','src/main/webapp/resources/no-image.png',1360,'83g'),(64,4,'D0005','양파깡','src/main/webapp/resources/no-image.png',1360,'83g'),(65,4,'D0006','양파링','src/main/webapp/resources/no-image.png',1360,'80g'),(66,4,'D0007','포스틱','src/main/webapp/resources/no-image.png',1360,'84g'),(67,4,'D0008','꿀꽈배기','src/main/webapp/resources/no-image.png',1360,'90g'),(68,4,'D0009','조청유과','src/main/webapp/resources/no-image.png',1360,'96g'),(69,4,'D0010','바나나킥','src/main/webapp/resources/no-image.png',2580,'145g'),(70,4,'D0011','콘칩','src/main/webapp/resources/no-image.png',2390,'140g'),(71,4,'D0012','오감자','src/main/webapp/resources/no-image.png',2390,'115g'),(72,4,'D0013','포카칩','src/main/webapp/resources/no-image.png',1360,'66g'),(73,4,'D0014','스윙칩','src/main/webapp/resources/no-image.png',1360,'60g'),(74,4,'D0015','오징어땅콩','src/main/webapp/resources/no-image.png',2390,'202g'),(75,4,'D0016','맛동산','src/main/webapp/resources/no-image.png',4390,'300g'),(76,4,'D0017','꼬칼콘','src/main/webapp/resources/no-image.png',1000,'52g 고소한맛'),(77,4,'D0018','짱구','src/main/webapp/resources/no-image.png',2980,'272g'),(78,4,'D0019','죠리퐁','src/main/webapp/resources/no-image.png',1000,'74g'),(79,4,'D0020','도도한나쵸','src/main/webapp/resources/no-image.png',1190,'95g 치즈맛'),(80,4,'D0021','꼬북칩','src/main/webapp/resources/no-image.png',1000,'64g 콘스프맛'),(81,4,'D0022','치토스','src/main/webapp/resources/no-image.png',1360,'82g 매콤달콤한맛'),(82,4,'D0023','도리토스','src/main/webapp/resources/no-image.png',1360,'92g 나쵸치즈맛'),(83,4,'D0024','자갈치','src/main/webapp/resources/no-image.png',2580,'174g'),(84,4,'D0025','인디안밥','src/main/webapp/resources/no-image.png',1360,'83g'),(85,4,'D0026','벌집핏자','src/main/webapp/resources/no-image.png',1360,'90g'),(86,4,'D0027','허니버터칩','src/main/webapp/resources/no-image.png',1000,'44g'),(87,4,'D0028','카라멜콘과땅콩','src/main/webapp/resources/no-image.png',1000,'72g'),(88,4,'D0029','꽃게랑','src/main/webapp/resources/no-image.png',2880,'143g'),(89,4,'D0030','썬칩','src/main/webapp/resources/no-image.png',1000,'66g 핫스파이시맛'),(90,4,'D0031','빼빼로','src/main/webapp/resources/no-image.png',1360,'54g'),(91,5,'E0000','칠성사이다','src/main/webapp/resources/no-image.png',3380,'1.8L'),(92,5,'E0001','칠성사이다제로','src/main/webapp/resources/no-image.png',2880,'1.5L'),(93,5,'E0002','밀키스','src/main/webapp/resources/no-image.png',2580,'1.5L'),(94,5,'E0003','제주사랑 감귤사랑','src/main/webapp/resources/no-image.png',2980,'1.5L'),(95,5,'E0004','아침햇살','src/main/webapp/resources/no-image.png',2200,'1.35L'),(96,5,'E0005','자연은알로에','src/main/webapp/resources/no-image.png',2350,'1.5L'),(97,5,'E0006','옥수수수염차','src/main/webapp/resources/no-image.png',2390,'1.5L'),(98,5,'E0007','헛개차','src/main/webapp/resources/no-image.png',2390,'1.5L'),(99,5,'E0008','제주삼다수','src/main/webapp/resources/no-image.png',1080,'2L'),(100,5,'E0009','코카콜라','src/main/webapp/resources/no-image.png',3730,'1.8L'),(101,5,'E0010','코카콜라제로','src/main/webapp/resources/no-image.png',3150,'1.5L'),(103,5,'E0012','암바사','src/main/webapp/resources/no-image.png',2500,'1.5L'),(104,5,'E0013','환타','src/main/webapp/resources/no-image.png',2460,'1.5L 오렌지'),(105,5,'E0014','펩시콜라','src/main/webapp/resources/no-image.png',2580,'1.5L'),(106,5,'E0015','게토레이','src/main/webapp/resources/no-image.png',3480,'1.5L'),(107,5,'E0016','포카리스웨트','src/main/webapp/resources/no-image.png',3780,'1.5L');
/*!40000 ALTER TABLE `Products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reviews`
--

DROP TABLE IF EXISTS `Reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Reviews` (
                           `ReviewID` int NOT NULL AUTO_INCREMENT,
                           `ProductID` int DEFAULT NULL,
                           `UserID` varchar(20) DEFAULT NULL,
                           `Rating` int DEFAULT NULL,
                           `Comments` text,
                           PRIMARY KEY (`ReviewID`),
                           KEY `UserID` (`UserID`),
                           KEY `fk_reviews_products` (`ProductID`),
                           CONSTRAINT `fk_reviews_products` FOREIGN KEY (`ProductID`) REFERENCES `Products` (`ProductID`) ON DELETE CASCADE,
                           CONSTRAINT `Reviews_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reviews`
--

LOCK TABLES `Reviews` WRITE;
/*!40000 ALTER TABLE `Reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ShoppingCart`
--

DROP TABLE IF EXISTS `ShoppingCart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ShoppingCart` (
                                `RecordID` int NOT NULL AUTO_INCREMENT,
                                `UserID` varchar(50) DEFAULT NULL,
                                `Quantity` int DEFAULT NULL,
                                `ProductID` int DEFAULT NULL,
                                `DateCreated` datetime DEFAULT CURRENT_TIMESTAMP,
                                PRIMARY KEY (`RecordID`),
                                KEY `UserID` (`UserID`),
                                KEY `fk_shoppingcart_products` (`ProductID`),
                                CONSTRAINT `fk_shoppingcart_products` FOREIGN KEY (`ProductID`) REFERENCES `Products` (`ProductID`) ON DELETE CASCADE,
                                CONSTRAINT `ShoppingCart_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ShoppingCart`
--

LOCK TABLES `ShoppingCart` WRITE;
/*!40000 ALTER TABLE `ShoppingCart` DISABLE KEYS */;
INSERT INTO `ShoppingCart` VALUES (21,NULL,1,4,'2023-12-06 11:23:27'),(58,'testtt',1,3,'2023-12-08 10:42:13'),(60,'first',4,1,'2023-12-08 14:36:32'),(63,'test999',1,4,'2023-12-08 14:56:11'),(64,'test999',8,2,'2023-12-08 14:56:13'),(65,'test999',1,3,'2023-12-08 14:56:14'),(66,'test999',4,6,'2023-12-08 14:56:16'),(69,'user',10,1,'2023-12-08 17:14:54'),(70,'admin',1,2,'2023-12-08 17:16:16'),(75,'user',1,2,'2023-12-08 17:37:57');
/*!40000 ALTER TABLE `ShoppingCart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `user_id` varchar(50) NOT NULL COMMENT '아이디',
                         `user_name` varchar(50) NOT NULL COMMENT '이름',
                         `user_password` varchar(200) NOT NULL COMMENT 'mysql password 사용',
                         `user_birth` varchar(8) NOT NULL COMMENT '생년월일 : 19840503',
                         `user_auth` varchar(10) NOT NULL COMMENT '권한: ROLE_ADMIN,ROLE_USER',
                         `user_point` int NOT NULL COMMENT 'default : 1000000',
                         `created_at` datetime NOT NULL COMMENT '가입일자',
                         `latest_login_at` datetime DEFAULT NULL COMMENT '마지막 로그인 일자',
                         PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('a1000','minseo','1234','19990704','ROLE_ADMIN',1000000,'2023-11-30 22:43:42','2023-12-04 16:53:19'),('a1001','student1','1234','19990704','ROLE_USER',1000000,'2023-11-30 23:29:56',NULL),('a1002','student2','1234','19990704','ROLE_ADMIN',1000000,'2023-12-01 00:17:58',NULL),('a1003','asdf','1234','19990704','ROLE_ADMIN',1000000,'2023-12-01 01:06:51',NULL),('a1004','sdf','1234','19990704','ROLE_USER',1000000,'2023-12-01 01:12:23',NULL),('a1005','minmin','1234','19990704','ROLE_USER',1000000,'2023-12-01 01:14:25','2023-12-01 01:14:37'),('admin','MINSEO','12345','19990704','ROLE_ADMIN',1001000,'2023-11-30 22:43:29','2023-12-08 17:18:56'),('first','asdf','1234','19990704','ROLE_USER',1010000,'2023-12-08 14:36:03','2023-12-08 14:36:23'),('minseo','minseo','1234','19990704','ROLE_USER',999100,'2023-12-08 10:35:59','2023-12-08 10:36:09'),('pointcheck','point','1234','19990704','ROLE_USER',1001000,'2023-12-08 13:30:29','2023-12-08 13:30:59'),('test1','minseo','12345','19990704','ROLE_USER',1000000,'2023-12-03 18:27:17','2023-12-03 19:22:59'),('test12','minseo','12345','19990704','ROLE_USER',1000000,'2023-12-03 19:22:41',NULL),('test33','asdf','12345','19990704','ROLE_USER',1000000,'2023-12-04 13:35:00',NULL),('test99','min','1234','19990704','ROLE_USER',1010000,'2023-12-08 11:31:17','2023-12-08 11:31:22'),('test999','999','999','19990704','ROLE_USER',1010000,'2023-12-08 14:55:54','2023-12-08 14:55:58'),('testtt','minseo','1234','19990704','ROLE_USER',541439,'2023-12-08 01:51:55','2023-12-08 10:42:11'),('tk','임태경','1234','19991125','ROLE_ADMIN',1000000,'2023-12-03 00:08:56','2023-12-04 00:17:24'),('user','minseo','12345','19900102','ROLE_USER',2631144,'2023-12-07 21:10:08','2023-12-08 17:37:53'),('ym','염진영','1234','20001014','ROLE_ADMIN',1000000,'2023-12-03 19:53:11','2023-12-03 19:53:15');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-08 18:01:14
