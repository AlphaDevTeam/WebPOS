CREATE DATABASE  IF NOT EXISTS `simplepos` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `simplepos`;
-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: simplepos
-- ------------------------------------------------------
-- Server version	5.6.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--


--
-- Dumping data for table `cashbook`
--

LOCK TABLES `cashbook` WRITE;
/*!40000 ALTER TABLE `cashbook` DISABLE KEYS */;
INSERT INTO `cashbook` (`ID`, `BALANCE`, `CR`, `DR`, `DESCRIPTION`, `BILLSTAT`, `LOGGER_ID`, `LOCATION_ID`) VALUES (1,9120,9120,0,'Purchase - GRN-001',NULL,21,1),(2,9120,9120,0,'Purchase - A-001',NULL,22,1),(3,9120,9120,0,'Purchase - ',NULL,23,1),(4,5000,5000,0,'Purchase - LO',NULL,24,1),(5,123,123,0,'Purchase - ',NULL,25,1),(6,234,234,0,'Purchase - ',NULL,26,2),(7,3500,3500,0,'Purchase - IN-2.0',NULL,27,1),(8,30000.5,30000.5,0,'Purchase - IN-3.0',NULL,28,1);
/*!40000 ALTER TABLE `cashbook` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `cashbookbalance`
--

LOCK TABLES `cashbookbalance` WRITE;
/*!40000 ALTER TABLE `cashbookbalance` DISABLE KEYS */;
INSERT INTO `cashbookbalance` (`ID`, `BANKBALANCE`, `BILLSTATUS`, `CASHBALANCE`, `LOCATION_ID`) VALUES (1,0,0,0,3),(2,0,1,0,3);
/*!40000 ALTER TABLE `cashbookbalance` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`ID`, `COMPANYDESCRIPTION`, `COMPANYNAME`) VALUES (1,'M','MComp');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `contactinfo`
--

LOCK TABLES `contactinfo` WRITE;
/*!40000 ALTER TABLE `contactinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `contactinfo` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `customerbalance`
--

LOCK TABLES `customerbalance` WRITE;
/*!40000 ALTER TABLE `customerbalance` DISABLE KEYS */;
INSERT INTO `customerbalance` (`ID`, `BALANCE`, `SUPPLIER_ID`) VALUES (1,58530,1);
/*!40000 ALTER TABLE `customerbalance` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `customercategory`
--

LOCK TABLES `customercategory` WRITE;
/*!40000 ALTER TABLE `customercategory` DISABLE KEYS */;
INSERT INTO `customercategory` (`ID`, `DESCRIPTION`, `LOGGER_ID`) VALUES (1,'Customer & Supplier',13);
/*!40000 ALTER TABLE `customercategory` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `customertransaction`
--

LOCK TABLES `customertransaction` WRITE;
/*!40000 ALTER TABLE `customertransaction` DISABLE KEYS */;
INSERT INTO `customertransaction` (`ID`, `BALANCE`, `CR`, `DR`, `DESCRIPTION`, `BILLSTAT`, `LOGGER_ID`, `SUPPLIER_ID`) VALUES (1,9120,0,9120,'GRN - GRN-001',NULL,21,1),(2,0,9120,0,'PAID - GRN-001',NULL,21,1),(3,9120,0,9120,'GRN - A-001',NULL,22,1),(4,0,9120,0,'PAID - A-001',NULL,22,1),(5,9120,0,9120,'GRN - ',NULL,23,1),(6,0,9120,0,'PAID - ',NULL,23,1),(7,23250,0,23250,'GRN - LO',NULL,24,1),(8,18250,5000,0,'PAID - LO',NULL,24,1),(9,57546,0,39296,'GRN - ',NULL,25,1),(10,57423,123,0,'PAID - ',NULL,25,1),(11,57542.5,0,119.5,'GRN - ',NULL,26,1),(12,57308.5,234,0,'PAID - ',NULL,26,1),(13,61146,0,3837.5,'GRN - IN-2.0',NULL,27,1),(14,57646,3500,0,'PAID - IN-2.0',NULL,27,1),(15,88530.5,0,30884.5,'GRN - IN-3.0',NULL,28,1),(16,58530,30000.5,0,'PAID - IN-3.0',NULL,28,1);
/*!40000 ALTER TABLE `customertransaction` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `design`
--

LOCK TABLES `design` WRITE;
/*!40000 ALTER TABLE `design` DISABLE KEYS */;
INSERT INTO `design` (`ID`, `DESCRIPTION`, `DESIGNCODE`, `DESIGNPREFIX`, `DESIGNPROFITPERC`, `LOGGER_ID`, `PRODUCT_ID`) VALUES (1,'Octain-90','OC-90','OC-90',2.5,NULL,1),(2,'Octain-95','OC-95','OC-95',2.5,NULL,1),(3,'Octain-93','OC-93','OC-93',2.5,NULL,1),(4,'Normal Deisel','LPG-N','LPG-N',2.5,NULL,3),(5,'Super Deisel','LPG-S','LPG-S',2.5,NULL,3);
/*!40000 ALTER TABLE `design` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `extras`
--

LOCK TABLES `extras` WRITE;
/*!40000 ALTER TABLE `extras` DISABLE KEYS */;
/*!40000 ALTER TABLE `extras` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `grn`
--

LOCK TABLES `grn` WRITE;
/*!40000 ALTER TABLE `grn` DISABLE KEYS */;
INSERT INTO `grn` (`ID`, `CREDITPERIOD`, `TOTALAMOUNT`, `BILLSTATUS`, `GRNDATE`, `GRNFLAGS`, `GRNNO`, `INVNO`, `PURCHASETYPE`, `SETTLEDAMOUNT`, `LOCATION_ID`, `LOGGER_ID`, `SUPPLIER_ID`) VALUES (1,0,9120,0,'2013-12-22',NULL,'GRN-001','GRN-001',0,0,1,21,1),(2,1,9120,0,'2013-12-22',NULL,'A-001','GRN-001',0,0,1,22,1),(3,1,9120,0,'2013-12-22',NULL,'','GRN-001',0,0,1,23,1),(4,0,23250,0,'2013-12-23',NULL,'LO','A-INV-001f',0,0,1,24,1),(5,0,39296,0,'2013-12-16',NULL,'','g',0,0,1,25,1),(6,0,119.5,0,'2013-12-19',NULL,'','g',NULL,0,2,26,1),(7,0,3837.5,0,'2013-12-02',NULL,'IN-2.0','A-INV-001',0,0,1,27,1),(8,0,30884.5,0,'2013-12-19',NULL,'IN-3.0','A-INV-001f',0,0,1,28,1);
/*!40000 ALTER TABLE `grn` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `grndetails`
--

LOCK TABLES `grndetails` WRITE;
/*!40000 ALTER TABLE `grndetails` DISABLE KEYS */;
INSERT INTO `grndetails` (`ID`, `GRNITEMCOST`, `GRNITEMDISCOUNTPERC`, `GRNQTY`, `RELATEDGRN_ID`, `GRNITEM_ID`) VALUES (1,152,0,60,1,1),(2,152,0,60,2,1),(3,152,0,60,3,1),(4,155,0,150,4,2),(5,153.5,0,256,5,3),(6,119.5,0,1,6,4),(7,153.5,0,25,7,3),(8,153.5,0,100,8,3),(9,121.5,0,78,8,5),(10,119.5,0,25,8,4),(11,155,0,10,8,2),(12,152,0,10,8,1);
/*!40000 ALTER TABLE `grndetails` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `grnpaymentdetails`
--

LOCK TABLES `grnpaymentdetails` WRITE;
/*!40000 ALTER TABLE `grnpaymentdetails` DISABLE KEYS */;
INSERT INTO `grnpaymentdetails` (`ID`, `CASHAMOUNT`, `CHEQUEAMOUNT`, `CREDITCARDAMOUNT`, `TOTALAMOUNT`, `LOGGER_ID`, `RELATEDGRN_ID`) VALUES (1,9120,0,0,9120,21,1),(2,9120,0,0,9120,22,2),(3,9120,0,0,9120,23,3),(4,5000,0,0,23250,24,4),(5,123,0,0,39296,25,5),(6,234,0,0,119.5,26,6),(7,3500,0,0,3837.5,27,7),(8,30000.5,0,0,30884.5,28,8);
/*!40000 ALTER TABLE `grnpaymentdetails` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `invoicedetails`
--

LOCK TABLES `invoicedetails` WRITE;
/*!40000 ALTER TABLE `invoicedetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoicedetails` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `itembincard`
--

LOCK TABLES `itembincard` WRITE;
/*!40000 ALTER TABLE `itembincard` DISABLE KEYS */;
INSERT INTO `itembincard` (`ID`, `BALANCE`, `DESCRIPTION`, `QTY`, `TRNNUMBER`, `BILLSTAT`, `ITEM_ID`, `LOG_ID`) VALUES (1,60,'Good Received Note - GRN-001',60,'GRN-001',NULL,1,21),(2,120,'Good Received Note - A-001',60,'GRN-001',NULL,1,22),(3,180,'Good Received Note - ',60,'GRN-001',NULL,1,23),(4,150,'Good Received Note - LO',150,'A-INV-001f',NULL,2,24),(5,256,'Good Received Note - ',256,'g',NULL,3,25),(6,1,'Good Received Note - ',1,'g',NULL,4,26),(7,281,'Good Received Note - IN-2.0',25,'A-INV-001',NULL,3,27),(8,190,'Good Received Note - IN-3.0',10,'A-INV-001f',NULL,1,28),(9,160,'Good Received Note - IN-3.0',10,'A-INV-001f',NULL,2,28),(10,26,'Good Received Note - IN-3.0',25,'A-INV-001f',NULL,4,28),(11,78,'Good Received Note - IN-3.0',78,'A-INV-001f',NULL,5,28),(12,381,'Good Received Note - IN-3.0',100,'A-INV-001f',NULL,3,28);
/*!40000 ALTER TABLE `itembincard` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` (`ID`, `ITEMBARCODE`, `ITEMCODE`, `ITEMCOST`, `ITEMDESCRIPTION`, `ITEMNAME`, `REORDERLEVEL`, `UNITPRICE`, `ITEMDESIGN_ID`, `ITEMLOCATION_ID`, `ITEMPRODUCT_ID`, `ITEMSUPPLIER_ID`, `LOGGER_ID`) VALUES (1,'P-1012545','P-TANK-90',152,'Petrol 90 Oct','Petrol 90 Oct',10,162,1,1,1,1,16),(2,'P-1012546','P-TANK-95',155,'Petrol 95 Oct','Petrol 95 Oct',10,170,2,1,1,1,17),(3,'P-1012547','P-TANK-93',153.5,'Petrol 93 Oct - Extra Premium','Petrol 93 Oct - Extra Premium',10,167,3,1,1,1,18),(4,'D-1012001','D-TANK-N',119.5,'Deisel Normal','Deisel Normal',10,125,4,1,3,1,19),(5,'D-1012002','D-TANK-S',121.5,'Deisel Super','Deisel Super',10,130,5,1,3,1,20);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` (`ID`, `CODE`, `DESCRIPTION`, `LOGGER_ID`) VALUES (1,'J','Ja Ela',5),(2,'C','Colombo',9),(3,'B','Batharamulla',15);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `logger`
--

LOCK TABLES `logger` WRITE;
/*!40000 ALTER TABLE `logger` DISABLE KEYS */;
INSERT INTO `logger` (`ID`, `DESCRIPTION`, `TRNNUMBER`, `TRNTIMESTAMP`, `TRNTYPE`, `TERMINAL_ID`, `USER_ID`) VALUES (1,'USer Created','USer','2012-01-01 00:00:00','USER',1,1),(2,'Create Customer Category','','2013-12-02 09:45:45','CUSTOMERCAT',1,1),(3,'Create Customer Category','','2013-12-02 09:45:46','CUSTOMERCAT',1,1),(4,'Create Supplier','dfsdf','2013-12-02 09:46:22','SUPPLIER',1,1),(5,'Create Location','M','2013-12-02 09:46:39','LOCATION',1,1),(6,'Create Item','m!02','2013-12-02 09:48:04','ITEM',1,1),(7,'Good Received Note','A-001','2013-12-02 09:53:21','GRN',1,1),(8,'Good Received Note','','2013-12-02 09:53:53','GRN',1,1),(9,'Create Location','sd','2013-12-02 10:04:48','LOCATION',1,1),(10,'Create Item','34234','2013-12-02 10:05:50','ITEM',1,1),(11,'Good Received Note','A-002','2013-12-02 10:06:20','GRN',1,1),(12,'Create Employee ','','2013-12-02 10:11:45','EMPLOYEE',1,1),(13,'Create Customer Category','','2013-12-22 11:14:20','CUSTOMERCAT',1,1),(14,'Create Supplier','Petrolium Company','2013-12-22 11:17:21','SUPPLIER',1,1),(15,'Create Location','B','2013-12-22 11:20:50','LOCATION',1,1),(16,'Create Item','P-TANK-90','2013-12-22 11:44:03','ITEM',1,1),(17,'Create Item','P-TANK-95','2013-12-22 11:44:25','ITEM',1,1),(18,'Create Item','P-TANK-93','2013-12-22 11:45:17','ITEM',1,1),(19,'Create Item','D-TANK-N','2013-12-22 11:46:30','ITEM',1,1),(20,'Create Item','D-TANK-S','2013-12-22 11:46:56','ITEM',1,1),(21,'Good Received Note','GRN-001','2013-12-22 11:49:21','GRN',1,1),(22,'Good Received Note','A-001','2013-12-22 11:52:52','GRN',1,1),(23,'Good Received Note','','2013-12-22 11:53:00','GRN',1,1),(24,'Good Received Note','LO','2013-12-22 12:00:53','GRN',1,1),(25,'Good Received Note','','2013-12-22 12:10:45','GRN',1,1),(26,'Good Received Note','','2013-12-22 12:14:51','GRN',1,1),(27,'Good Received Note','IN-2.0','2013-12-24 03:58:12','GRN',1,1),(28,'Good Received Note','IN-3.0','2013-12-26 22:43:42','GRN',1,1);
/*!40000 ALTER TABLE `logger` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `paymentdetails`
--

LOCK TABLES `paymentdetails` WRITE;
/*!40000 ALTER TABLE `paymentdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `paymentdetails` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`ID`, `PRODCODE`, `PRODUCTDESCRIPTION`, `PRODUCTPREFIX`, `PROFITPERC`, `LOGGER_ID`) VALUES (1,'PET01','Petrol','PETROL',25,NULL),(2,'PET01','Petrol','DEI',25,NULL),(3,'LPG01','Deisel','DEISEL',15,NULL),(4,'OIL01','Oil','OIL',10,NULL),(5,'O01','Other','OTHER',2.5,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `properties`
--

LOCK TABLES `properties` WRITE;
/*!40000 ALTER TABLE `properties` DISABLE KEYS */;
/*!40000 ALTER TABLE `properties` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `propertymanager`
--

LOCK TABLES `propertymanager` WRITE;
/*!40000 ALTER TABLE `propertymanager` DISABLE KEYS */;
/*!40000 ALTER TABLE `propertymanager` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `propertyvalues`
--

LOCK TABLES `propertyvalues` WRITE;
/*!40000 ALTER TABLE `propertyvalues` DISABLE KEYS */;
/*!40000 ALTER TABLE `propertyvalues` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `purchasereturn`
--

LOCK TABLES `purchasereturn` WRITE;
/*!40000 ALTER TABLE `purchasereturn` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchasereturn` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `purchasereturndetails`
--

LOCK TABLES `purchasereturndetails` WRITE;
/*!40000 ALTER TABLE `purchasereturndetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchasereturndetails` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `rules`
--

LOCK TABLES `rules` WRITE;
/*!40000 ALTER TABLE `rules` DISABLE KEYS */;
/*!40000 ALTER TABLE `rules` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES ('SEQ_GEN',0);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` (`ID`, `STOCKQTY`, `SOCKITEM_ID`, `STOCKLOCATION_ID`) VALUES (1,190,1,1),(2,160,2,1),(3,381,3,1),(4,26,4,1),(5,78,5,1);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` (`ID`, `ADDRESSSTATE`, `CITY`, `EMAILADDRESS`, `FAX`, `MOBILE`, `PHONE`, `STREET`, `URL`, `ZIPCODE`, `CREDITLIMIT`, `CUSTCODE`, `CUSTNIC`, `FIRSTNAME`, `INACTIVE`, `LASTNAME`, `LOGGER_ID`, `CUSTCATEGORY_ID`, `EXTRA_ID`) VALUES (1,'','','','','0777117477','','','','',75000,'C-001','1','Petrolium Company',0,'CEPETCO',14,1,NULL);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `systemnumbers`
--

LOCK TABLES `systemnumbers` WRITE;
/*!40000 ALTER TABLE `systemnumbers` DISABLE KEYS */;
INSERT INTO `systemnumbers` (`ID`, `DOCUMENT`, `DOCUMENTPREFIX`, `SYSTEMNUMBER`, `RELATEDLOCATION_ID`, `RELATEDCOMPANY_ID`) VALUES (1,'INVOICE','AH',1,1,1),(2,'GOOD_RECEIPT_NOTE','IN',3,1,1),(3,'GOOD_RECEIPT_NOTE','wr',2,2,1);
/*!40000 ALTER TABLE `systemnumbers` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `systems`
--

LOCK TABLES `systems` WRITE;
/*!40000 ALTER TABLE `systems` DISABLE KEYS */;
INSERT INTO `systems` (`ID`, `HEADER`, `ISGRNNUMBERAUTOGENARATED`, `RELATEDLOCATION_ID`, `RELATEDCOMPANY_ID`) VALUES (1,'ALPHA',1,1,1),(2,'asfsaf',1,2,1);
/*!40000 ALTER TABLE `systems` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `terminal`
--

LOCK TABLES `terminal` WRITE;
/*!40000 ALTER TABLE `terminal` DISABLE KEYS */;
INSERT INTO `terminal` (`ID`, `TERMINALCODE`, `TERMINALIP`, `TERMINALNAME`, `LOGGER_ID`) VALUES (1,'1','127.0.0.1','d',1);
/*!40000 ALTER TABLE `terminal` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `userx`
--

LOCK TABLES `userx` WRITE;
/*!40000 ALTER TABLE `userx` DISABLE KEYS */;
INSERT INTO `userx` (`ID`, `PASSWORD`, `USERLEVEL`, `USERNAME`, `LOGGER_ID`, `ASSOCIATEDCOMPANY_ID`) VALUES (1,'123','ADMIN','mihindu',1,1);
/*!40000 ALTER TABLE `userx` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-12-26 23:31:17
