CREATE DATABASE  IF NOT EXISTS `javalx` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `javalx`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: javalx
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_cidade`
--

DROP TABLE IF EXISTS `tb_cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_cidade` (
  `cod_cidade` int(11) NOT NULL AUTO_INCREMENT,
  `nome_cidade` varchar(50) NOT NULL,
  `cep_cidade` varchar(10) NOT NULL,
  `cod_estado` int(11) NOT NULL,
  `ind_situacao` char(1) DEFAULT 'A',
  PRIMARY KEY (`cod_cidade`),
  KEY `fk_estado_cidade_idx` (`cod_estado`),
  CONSTRAINT `fk_estado_cidade` FOREIGN KEY (`cod_estado`) REFERENCES `tb_estado` (`cod_estado`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cidade`
--

LOCK TABLES `tb_cidade` WRITE;
/*!40000 ALTER TABLE `tb_cidade` DISABLE KEYS */;
INSERT INTO `tb_cidade` VALUES (1,'SORRISO','7889000',1,'A');
/*!40000 ALTER TABLE `tb_cidade` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`Daniel`@`%`*/ /*!50003 TRIGGER `tb_cidade_AFTER_INSERT` AFTER INSERT ON `tb_cidade` FOR EACH ROW BEGIN
insert into tb_log_cidade(data_log_cidade,operacao_log , nome_log_cidade ,cep_log_cidade , cod_estado_log_cidade) 
    values(sysdate() ,'I',new.nome_cidade , new.cep_cidade , new.cod_estado);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`Daniel`@`%`*/ /*!50003 TRIGGER `tb_cidade_BEFORE_UPDATE` BEFORE UPDATE ON `tb_cidade` FOR EACH ROW BEGIN
insert into tb_log_cidade(data_log_cidade,operacao_log , nome_log_cidade ,cep_log_cidade , cod_estado_log_cidade) 
    values(sysdate() ,'U',new.nome_cidade , new.cep_cidade , new.cod_estado);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`Daniel`@`%`*/ /*!50003 TRIGGER `tb_cidade_BEFORE_DELETE` BEFORE DELETE ON `tb_cidade` FOR EACH ROW BEGIN
insert into tb_log_cidade(data_log_cidade,operacao_log , nome_log_cidade ,cep_log_cidade , cod_estado_log_cidade) 
    values(sysdate() ,'D',old.nome_cidade , old.cep_cidade , old.cod_estado);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `tb_estado`
--

DROP TABLE IF EXISTS `tb_estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_estado` (
  `cod_estado` int(11) NOT NULL AUTO_INCREMENT,
  `nome_estado` varchar(45) NOT NULL,
  `uf_estado` varchar(2) NOT NULL,
  `ind_situacao` char(1) DEFAULT 'A',
  PRIMARY KEY (`cod_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_estado`
--

LOCK TABLES `tb_estado` WRITE;
/*!40000 ALTER TABLE `tb_estado` DISABLE KEYS */;
INSERT INTO `tb_estado` VALUES (1,'MATO GROSSO','MT','A');
/*!40000 ALTER TABLE `tb_estado` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`Daniel`@`%`*/ /*!50003 TRIGGER `tb_estado_AFTER_INSERT` AFTER INSERT ON `tb_estado` FOR EACH ROW BEGIN
insert into tb_log_estado(`data_log`,nome_log_estado , uf_log_estado,operacao_log) 
    values(sysdate() ,new.nome_estado , new.uf_estado ,'I');
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`Daniel`@`%`*/ /*!50003 TRIGGER `tb_estado_BEFORE_UPDATE` BEFORE UPDATE ON `tb_estado` FOR EACH ROW BEGIN
insert into tb_log_estado(`data_log`,nome_log_estado , uf_log_estado,operacao_log) 
    values(sysdate() ,new.nome_estado , new.uf_estado ,'U');
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`Daniel`@`%`*/ /*!50003 TRIGGER `tb_estado_BEFORE_DELETE` BEFORE DELETE ON `tb_estado` FOR EACH ROW BEGIN
insert into tb_log_estado(`data_log`,nome_log_estado , uf_log_estado,operacao_log) 
    values(sysdate() ,old.nome_estado , old.uf_estado ,'D');
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `tb_log_cidade`
--

DROP TABLE IF EXISTS `tb_log_cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_log_cidade` (
  `cod_log_cidade` int(11) NOT NULL AUTO_INCREMENT,
  `data_log_cidade` datetime NOT NULL,
  `operacao_log` char(1) NOT NULL,
  `nome_log_cidade` varchar(45) NOT NULL,
  `cep_log_cidade` varchar(10) NOT NULL,
  `cod_estado_log_cidade` int(11) NOT NULL,
  PRIMARY KEY (`cod_log_cidade`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_log_cidade`
--

LOCK TABLES `tb_log_cidade` WRITE;
/*!40000 ALTER TABLE `tb_log_cidade` DISABLE KEYS */;
INSERT INTO `tb_log_cidade` VALUES (1,'2018-12-01 17:36:47','I','CUIABA','1234',1),(2,'2018-12-01 17:36:50','D','CUIABA','1234',1);
/*!40000 ALTER TABLE `tb_log_cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_log_estado`
--

DROP TABLE IF EXISTS `tb_log_estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_log_estado` (
  `cod_log_estado` int(11) NOT NULL AUTO_INCREMENT,
  `data_log` datetime NOT NULL,
  `nome_log_estado` varchar(45) NOT NULL,
  `uf_log_estado` varchar(2) NOT NULL,
  `operacao_log` char(1) NOT NULL,
  PRIMARY KEY (`cod_log_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_log_estado`
--

LOCK TABLES `tb_log_estado` WRITE;
/*!40000 ALTER TABLE `tb_log_estado` DISABLE KEYS */;
INSERT INTO `tb_log_estado` VALUES (1,'2018-12-01 17:36:08','PARANÁ','PR','I'),(2,'2018-12-01 17:36:12','PARANÁ','PR','D');
/*!40000 ALTER TABLE `tb_log_estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_log_produto`
--

DROP TABLE IF EXISTS `tb_log_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_log_produto` (
  `cod_log_produto` int(11) NOT NULL AUTO_INCREMENT,
  `data_log_produto` datetime NOT NULL,
  `desc_log_produto` varchar(255) NOT NULL,
  `tipo_log_produto` int(11) NOT NULL,
  `usuario_log_produto` int(11) NOT NULL,
  `preco_log_produto` double NOT NULL,
  `operacao_log` char(1) NOT NULL,
  `nome_log_produto` varchar(60) NOT NULL,
  `quantidade_log_produto` int(11) NOT NULL,
  PRIMARY KEY (`cod_log_produto`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_log_produto`
--

LOCK TABLES `tb_log_produto` WRITE;
/*!40000 ALTER TABLE `tb_log_produto` DISABLE KEYS */;
INSERT INTO `tb_log_produto` VALUES (1,'2018-11-27 19:04:38','AAAA',1,2,1,'I','AAAA',0),(2,'2018-11-29 18:28:43','TESTE',1,4,123213,'I','PRODUTO TESTE ANA',0),(3,'2018-12-01 16:50:39','SUCH UAU',1,5,10,'I','TESTE DANIEL',0),(4,'2018-12-01 17:09:11','1',1,5,1,'I','1',1),(5,'2018-12-01 17:13:36','SUCH UAU',1,5,10,'U','TESTE DANIEL',1),(6,'2018-12-01 17:14:59','SUCH UAU',1,5,10,'U','TESTE DANIEL',10),(7,'2018-12-01 17:15:27','SUCH UAU',1,5,10,'U','TESTE DANIEL',9),(8,'2018-12-01 17:15:30','SUCH UAU',1,5,10,'U','TESTE DANIEL',8),(9,'2018-12-01 17:21:01','TESTE',1,4,123213,'U','PRODUTO TESTE ANA',1);
/*!40000 ALTER TABLE `tb_log_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_log_tipo`
--

DROP TABLE IF EXISTS `tb_log_tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_log_tipo` (
  `cod_log_tipo` int(11) NOT NULL AUTO_INCREMENT,
  `data_log_tipo` datetime NOT NULL,
  `desc_log_tipo` varchar(45) NOT NULL,
  `operacao_log` char(1) NOT NULL,
  PRIMARY KEY (`cod_log_tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_log_tipo`
--

LOCK TABLES `tb_log_tipo` WRITE;
/*!40000 ALTER TABLE `tb_log_tipo` DISABLE KEYS */;
INSERT INTO `tb_log_tipo` VALUES (1,'2018-12-01 17:36:24','ARMAS','I'),(2,'2018-12-01 17:36:26','ARMAS','D');
/*!40000 ALTER TABLE `tb_log_tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_log_usuario`
--

DROP TABLE IF EXISTS `tb_log_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_log_usuario` (
  `cod_log_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `data_log_usuario` datetime NOT NULL,
  `email_log_usuario` varchar(100) NOT NULL,
  `celular_log_usuario` varchar(13) NOT NULL,
  `senha_log_usuario` varchar(20) NOT NULL,
  `saldo_log_usuario` float NOT NULL,
  `cidade_log_usuario` int(11) NOT NULL,
  `operacao_log` char(1) NOT NULL,
  `nome_log_usuario` varchar(50) NOT NULL,
  `nota_log_usuario` int(11) NOT NULL,
  PRIMARY KEY (`cod_log_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_log_usuario`
--

LOCK TABLES `tb_log_usuario` WRITE;
/*!40000 ALTER TABLE `tb_log_usuario` DISABLE KEYS */;
INSERT INTO `tb_log_usuario` VALUES (1,'2018-11-28 13:58:23','daniel@hotmail.com','123456','123',0,1,'D','DANIEL',0),(2,'2018-11-28 13:59:11','peixoto@email','1234123','123',0,1,'I','DANIEL PEIXOTO',0),(3,'2018-11-28 14:30:38','email','123123','123',20,1,'U','TESTE',0),(4,'2018-11-29 17:10:35','ana@email','12143','ana',0,1,'I','ANA',0),(5,'2018-11-30 16:30:10','ana@email','12143','ana',1,1,'U','ANA',0),(6,'2018-11-30 16:30:12','ana@email','12143','ana',2,1,'U','ANA',0),(7,'2018-11-30 16:39:07','ana@email','12143','ana',3,1,'U','ANA',0),(8,'2018-11-30 16:44:05','ana@email','12143','ana',2,1,'U','ANA',0),(9,'2018-11-30 16:44:10','ana@email','12143','ana',1,1,'U','ANA',0),(10,'2018-11-30 16:44:11','ana@email','12143','ana',0,1,'U','ANA',0),(11,'2018-11-30 16:44:11','ana@email','12143','ana',-1,1,'U','ANA',0),(12,'2018-11-30 16:45:27','ana@email','12143','ana',-2,1,'U','ANA',0),(13,'2018-11-30 17:00:35','ana@email','12143','ana',8,1,'U','ANA',0),(14,'2018-12-01 00:30:10','ana@email','12143','ana',8,1,'U','ANA',1),(15,'2018-12-01 00:30:12','ana@email','12143','ana',8,1,'U','ANA',2),(16,'2018-12-01 00:30:13','ana@email','12143','ana',8,1,'U','ANA',3),(17,'2018-12-01 00:30:13','ana@email','12143','ana',8,1,'U','ANA',4),(18,'2018-12-01 00:30:13','ana@email','12143','ana',8,1,'U','ANA',5),(19,'2018-12-01 00:48:18','ana@email','12143','ana',8,1,'U','ANA',6),(20,'2018-12-01 00:48:32','ana@email','12143','ana',8,1,'U','ANA',7),(21,'2018-12-01 00:50:45','ana@email','12143','ana',8,1,'U','ANA',8),(22,'2018-12-01 14:15:24','ana@email','12143','ana',8,1,'U','ANA',9),(23,'2018-12-01 14:15:31','ana@email','12143','ana',8,1,'U','ANA',8),(24,'2018-12-01 14:15:32','ana@email','12143','ana',8,1,'U','ANA',9),(25,'2018-12-01 14:15:55','ana@email','12143','ana',8,1,'U','ANA',8),(26,'2018-12-01 14:16:21','ana@email','12143','ana',8,1,'U','ANA',9),(27,'2018-12-01 14:16:23','ana@email','12143','ana',8,1,'U','ANA',8),(28,'2018-12-01 14:17:32','ana@email','12143','ana',8,1,'U','ANA',9),(29,'2018-12-01 14:17:33','ana@email','12143','ana',8,1,'U','ANA',8),(30,'2018-12-01 14:18:07','ana@email','12143','ana',8,1,'U','ANA',9),(31,'2018-12-01 15:12:02','email','123123','123',20,1,'U','TESTE',1),(32,'2018-12-01 15:12:03','email','123123','123',20,1,'U','TESTE',0),(33,'2018-12-01 15:12:04','email','123123','123',20,1,'U','TESTE',1),(34,'2018-12-01 15:12:07','email','123123','123',21,1,'U','TESTE',1),(35,'2018-12-01 15:12:07','ana@email','12143','ana',9,1,'U','ANA',9),(36,'2018-12-01 15:15:01','email','123123','123',22,1,'U','TESTE',1),(37,'2018-12-01 15:15:55','email','123123','123',22,1,'U','TESTE',2),(38,'2018-12-01 15:15:56','email','123123','123',23,1,'U','TESTE',2),(39,'2018-12-01 15:15:56','ana@email','12143','ana',8,1,'U','ANA',9),(40,'2018-12-01 15:16:02','email','123123','123',24,1,'U','TESTE',2),(41,'2018-12-01 15:16:02','ana@email','12143','ana',7,1,'U','ANA',9),(42,'2018-12-01 15:16:09','email','123123','123',25,1,'U','TESTE',2),(43,'2018-12-01 15:16:09','ana@email','12143','ana',6,1,'U','ANA',9),(44,'2018-12-01 15:17:25','email','123123','123',26,1,'U','TESTE',2),(45,'2018-12-01 15:17:25','ana@email','12143','ana',5,1,'U','ANA',9),(46,'2018-12-01 15:17:27','email','123123','123',27,1,'U','TESTE',2),(47,'2018-12-01 15:17:27','ana@email','12143','ana',4,1,'U','ANA',9),(48,'2018-12-01 15:18:31','email','123123','123',28,1,'U','TESTE',2),(49,'2018-12-01 15:18:31','ana@email','12143','ana',3,1,'U','ANA',9),(50,'2018-12-01 15:19:25','email','123123','123',29,1,'U','TESTE',2),(51,'2018-12-01 15:19:25','ana@email','12143','ana',2,1,'U','ANA',9),(52,'2018-12-01 15:23:52','email','123123','123',30,1,'U','TESTE',2),(53,'2018-12-01 15:23:52','ana@email','12143','ana',1,1,'U','ANA',9),(54,'2018-12-01 15:24:06','email','123123','123',30,1,'U','TESTE',3),(55,'2018-12-01 15:24:51','email','123123','123',31,1,'U','TESTE',3),(56,'2018-12-01 15:24:51','ana@email','12143','ana',0,1,'U','ANA',9),(57,'2018-12-01 15:25:10','ana@email','12143','ana',100,1,'U','ANA',9),(58,'2018-12-01 16:49:51','1','123124','1',0,1,'I','DANIEL',0),(59,'2018-12-01 16:51:01','1','123124','1',1000,1,'U','DANIEL',0),(60,'2018-12-01 17:15:27','1','123124','1',1010,1,'U','DANIEL',0),(61,'2018-12-01 17:15:27','ana@email','12143','ana',90,1,'U','ANA',9),(62,'2018-12-01 17:15:30','1','123124','1',1020,1,'U','DANIEL',0),(63,'2018-12-01 17:15:30','ana@email','12143','ana',80,1,'U','ANA',9),(64,'2018-12-01 17:21:08','1','123124','1',1020,1,'U','DANIEL',1),(65,'2018-12-01 17:37:05','email','123123','123',31,1,'D','TESTE',3);
/*!40000 ALTER TABLE `tb_log_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_produto`
--

DROP TABLE IF EXISTS `tb_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_produto` (
  `cod_produto` int(11) NOT NULL AUTO_INCREMENT,
  `cod_usuario` int(11) NOT NULL,
  `cod_tipo_produto` int(11) NOT NULL,
  `nome_produto` varchar(60) NOT NULL,
  `desc_produto` varchar(255) NOT NULL,
  `val_preco` double NOT NULL,
  `quantidade_produto` int(11) DEFAULT '0',
  PRIMARY KEY (`cod_produto`),
  KEY `fk_usuario_produto_idx` (`cod_usuario`),
  KEY `fk_tipo_produto_idx` (`cod_tipo_produto`),
  CONSTRAINT `fk_tipo_produto` FOREIGN KEY (`cod_tipo_produto`) REFERENCES `tb_tipo_produto` (`cod_tipo`) ON DELETE CASCADE,
  CONSTRAINT `fk_usuario_produto` FOREIGN KEY (`cod_usuario`) REFERENCES `tb_usuario` (`cod_usuario`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_produto`
--

LOCK TABLES `tb_produto` WRITE;
/*!40000 ALTER TABLE `tb_produto` DISABLE KEYS */;
INSERT INTO `tb_produto` VALUES (6,4,1,'PRODUTO TESTE ANA','TESTE',123213,1),(7,5,1,'TESTE DANIEL','SUCH UAU',10,8),(8,5,1,'1','1',1,1);
/*!40000 ALTER TABLE `tb_produto` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`Daniel`@`%`*/ /*!50003 TRIGGER `tb_log_produto` AFTER INSERT ON `tb_produto` FOR EACH ROW BEGIN
	insert into tb_log_produto(data_log_produto , desc_log_produto , tipo_log_produto , usuario_log_produto , preco_log_produto,operacao_log , nome_log_produto , quantidade_log_produto) 
    values(sysdate() , new.desc_produto , new.cod_tipo_produto , new.cod_usuario , new.val_preco , 'I',new.`nome_produto` , new.quantidade_produto);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`Daniel`@`%`*/ /*!50003 TRIGGER `tb_produto_BEFORE_UPDATE` BEFORE UPDATE ON `tb_produto` FOR EACH ROW BEGIN
insert into tb_log_produto(data_log_produto , desc_log_produto , tipo_log_produto , usuario_log_produto , preco_log_produto,operacao_log , nome_log_produto , quantidade_log_produto) 
    values(sysdate() , new.desc_produto , new.cod_tipo_produto , new.cod_usuario , new.val_preco , 'U',new.`nome_produto` , new.quantidade_produto);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`Daniel`@`%`*/ /*!50003 TRIGGER `tb_produto_BEFORE_DELETE` BEFORE DELETE ON `tb_produto` FOR EACH ROW BEGIN
insert into tb_log_produto(data_log_produto , desc_log_produto , tipo_log_produto , usuario_log_produto , preco_log_produto,operacao_log , nome_log_produto , quantidade_log_produto) 
    values(sysdate() , old.desc_produto , old.cod_tipo_produto , old.cod_usuario , old.val_preco , 'D',old.`nome_produto` , old.quantidade_produto);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `tb_tipo_produto`
--

DROP TABLE IF EXISTS `tb_tipo_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_tipo_produto` (
  `cod_tipo` int(11) NOT NULL AUTO_INCREMENT,
  `desc_tipo` varchar(45) NOT NULL,
  `ind_situacao` char(1) DEFAULT 'A',
  PRIMARY KEY (`cod_tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tipo_produto`
--

LOCK TABLES `tb_tipo_produto` WRITE;
/*!40000 ALTER TABLE `tb_tipo_produto` DISABLE KEYS */;
INSERT INTO `tb_tipo_produto` VALUES (1,'ELETRÔNICO','A'),(2,'AUTOMÓVEIS','A');
/*!40000 ALTER TABLE `tb_tipo_produto` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`Daniel`@`%`*/ /*!50003 TRIGGER `tb_tipo_produto_AFTER_INSERT` AFTER INSERT ON `tb_tipo_produto` FOR EACH ROW BEGIN
	insert into tb_log_tipo(data_log_tipo , desc_log_tipo , operacao_log) values(sysdate() , new.desc_tipo , 'I');
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`Daniel`@`%`*/ /*!50003 TRIGGER `tb_tipo_produto_BEFORE_UPDATE` BEFORE UPDATE ON `tb_tipo_produto` FOR EACH ROW BEGIN
	insert into tb_log_tipo(data_log_tipo , desc_log_tipo , operacao_log) values(sysdate() , new.desc_tipo , 'U');

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`Daniel`@`%`*/ /*!50003 TRIGGER `tb_tipo_produto_BEFORE_DELETE` BEFORE DELETE ON `tb_tipo_produto` FOR EACH ROW BEGIN
	insert into tb_log_tipo(data_log_tipo , desc_log_tipo , operacao_log) values(sysdate() , old.desc_tipo , 'D');
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_usuario` (
  `cod_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome_usuario` varchar(50) NOT NULL,
  `email_usuario` varchar(100) NOT NULL,
  `celular_usuario` varchar(13) NOT NULL,
  `senha_usuario` varchar(20) NOT NULL,
  `ind_situacao` char(1) DEFAULT 'A',
  `cod_cidade` int(11) NOT NULL,
  `val_saldo` float DEFAULT '0',
  `nota_usuario` int(11) DEFAULT '0',
  PRIMARY KEY (`cod_usuario`),
  KEY `fk_cidade_usuario_idx` (`cod_cidade`),
  CONSTRAINT `fk_cidade_usuario` FOREIGN KEY (`cod_cidade`) REFERENCES `tb_cidade` (`cod_cidade`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario`
--

LOCK TABLES `tb_usuario` WRITE;
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
INSERT INTO `tb_usuario` VALUES (3,'DANIEL PEIXOTO','peixoto@email','1234123','123','A',1,0,0),(4,'ANA','ana@email','12143','ana','A',1,80,9),(5,'DANIEL','1','123124','1','A',1,1020,1);
/*!40000 ALTER TABLE `tb_usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`Daniel`@`%`*/ /*!50003 TRIGGER `tb_usuario_AFTER_INSERT` AFTER INSERT ON `tb_usuario` FOR EACH ROW BEGIN
	insert into tb_log_usuario(data_log_usuario , email_log_usuario , celular_log_usuario , senha_log_usuario , saldo_log_usuario ,
								cidade_log_usuario , operacao_log , nome_log_usuario , nota_log_usuario) 
	values(sysdate() , new.email_usuario , new.celular_usuario ,new.senha_usuario , new.val_saldo ,new.cod_cidade , 'I' , new.nome_usuario ,new.nota_usuario);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`Daniel`@`%`*/ /*!50003 TRIGGER `tb_usuario_BEFORE_UPDATE` BEFORE UPDATE ON `tb_usuario` FOR EACH ROW BEGIN
	insert into tb_log_usuario(data_log_usuario , email_log_usuario , celular_log_usuario , senha_log_usuario , saldo_log_usuario ,
								cidade_log_usuario , operacao_log , nome_log_usuario , nota_log_usuario) 
	values(sysdate() , new.email_usuario , new.celular_usuario ,new.senha_usuario , new.val_saldo ,new.cod_cidade , 'U' , new.nome_usuario ,new.nota_usuario);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`Daniel`@`%`*/ /*!50003 TRIGGER `tb_usuario_BEFORE_DELETE` BEFORE DELETE ON `tb_usuario` FOR EACH ROW BEGIN
	insert into tb_log_usuario(data_log_usuario , email_log_usuario , celular_log_usuario , senha_log_usuario , saldo_log_usuario ,
								cidade_log_usuario , operacao_log , nome_log_usuario , nota_log_usuario) 
	values(sysdate() , old.email_usuario , old.celular_usuario ,old.senha_usuario , old.val_saldo ,old.cod_cidade , 'D' , old.nome_usuario ,old.nota_usuario);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'javalx'
--

--
-- Dumping routines for database 'javalx'
--
/*!50003 DROP FUNCTION IF EXISTS `Dislike_Usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`Daniel`@`%` FUNCTION `Dislike_Usuario`( id_usuario INT) RETURNS int(11)
    READS SQL DATA
    DETERMINISTIC
BEGIN
	DECLARE nota_atual INT ;
    SELECT nota_usuario INTO nota_atual FROM tb_usuario WHERE cod_usuario = id_usuario;
    SET nota_atual = nota_atual-1;
    call Gerenciar_Nota_Usuario(nota_atual , id_usuario);
RETURN nota_atual;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `Like_Usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`Daniel`@`%` FUNCTION `Like_Usuario`( id_usuario INT) RETURNS int(11)
    READS SQL DATA
    DETERMINISTIC
BEGIN
	DECLARE `nota_atual` INT ;
    SELECT nota_usuario INTO `nota_atual` FROM tb_usuario WHERE cod_usuario = id_usuario;
    SET `nota_atual` = nota_atual+1;
    call Gerenciar_Nota_Usuario(`nota_atual` , id_usuario);
RETURN `nota_atual`;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Creditar_Conta_Usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`Daniel`@`%` PROCEDURE `Creditar_Conta_Usuario`(IN valor FLOAT , IN id_usuario INT )
BEGIN
	UPDATE tb_usuario SET val_saldo= val_saldo+valor WHERE cod_usuario = id_usuario;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Debitar_Conta_Usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`Daniel`@`%` PROCEDURE `Debitar_Conta_Usuario`(IN Valor Float , IN id_usuario INT)
BEGIN
	UPDATE tb_usuario SET val_saldo = val_saldo-Valor WHERE cod_usuario = id_usuario;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Gerenciar_Nota_Usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`Daniel`@`%` PROCEDURE `Gerenciar_Nota_Usuario`(IN nota_nova INT , IN id_usuario INT)
BEGIN
	UPDATE tb_usuario SET nota_usuario = nota_nova WHERE cod_usuario = id_usuario;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-01 17:39:43
