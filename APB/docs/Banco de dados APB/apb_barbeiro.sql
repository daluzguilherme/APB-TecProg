CREATE DATABASE  IF NOT EXISTS `apb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `apb`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: apb
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `barbeiro`
--

DROP TABLE IF EXISTS `barbeiro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `barbeiro` (
  `idbarbeiro` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `rg` varchar(45) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `telefone` varchar(13) NOT NULL,
  `cadeira` varchar(2) NOT NULL,
  PRIMARY KEY (`idbarbeiro`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barbeiro`
--

LOCK TABLES `barbeiro` WRITE;
/*!40000 ALTER TABLE `barbeiro` DISABLE KEYS */;
INSERT INTO `barbeiro` VALUES (3,'Henrique','2.536.681','73224383100','(22)2222-2222','14'),(4,'Guilherme','5401249','03370322110','(61)9294-5178','11'),(5,'Luciano','418757896','02919594150','3389-9085','5'),(6,'Luciano','418757896','02919594150','3389-9085','5'),(7,'Luciano','418757896','02919594150','3389-9085','5'),(8,'Luciano','418757896','02919594150','3389-9085','5'),(9,'Luciano','418757896','02919594150','3389-9085','5'),(10,'Luciano','418757896','02919594150','3389-9085','5'),(18,'Luciano','418757896','02919594150','3389-9085','5'),(19,'Luciano','418757896','02919594150','3389-9085','5'),(20,'Luciano','418757896','02919594150','3389-9085','5'),(21,'Luciano','418757896','02919594150','3389-9085','5'),(22,'Luciano','418757896','02919594150','3389-9085','5'),(23,'Luciano','418757896','02919594150','3389-9085','5'),(28,'Alessandro','418757896','02919594150','3389-9085','5'),(29,'Alessandro','418757896','02919594150','3389-9085','5'),(30,'Alessandro','418757896','02919594150','3389-9085','5');
/*!40000 ALTER TABLE `barbeiro` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-09-01 15:16:06
