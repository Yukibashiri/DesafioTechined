CREATE DATABASE  IF NOT EXISTS `desafiotechned` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `desafiotechned`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: desafiotechned
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `tbl_contas`
--

DROP TABLE IF EXISTS `tbl_contas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_contas` (
  `id_contas` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tbl_tipoDeConta_id_tipoDeConta` int(10) unsigned NOT NULL,
  `cln_titular` varchar(100) NOT NULL,
  `cln_numero` int(10) unsigned NOT NULL,
  `cln_agencia` int(10) unsigned NOT NULL,
  `cln_dataAbertura` date NOT NULL,
  `cln_saldo` float DEFAULT NULL,
  `cln_senha` varchar(20) DEFAULT NULL,
  `cln_limiteEspecial` double DEFAULT '0',
  `cln_especialUtilizado` double DEFAULT '0',
  PRIMARY KEY (`id_contas`),
  UNIQUE KEY `cln_numero` (`cln_numero`),
  KEY `tbl_contas_FKIndex1` (`tbl_tipoDeConta_id_tipoDeConta`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_contas`
--

LOCK TABLES `tbl_contas` WRITE;
/*!40000 ALTER TABLE `tbl_contas` DISABLE KEYS */;
INSERT INTO `tbl_contas` VALUES (1,2,'João Almeida',1234,81,'2009-12-12',396.56,'123456',500,0),(2,1,'Pedro Batista',2222,79,'2011-11-08',771.6,'123456',0,0),(3,2,'Carlos Pimentel',3333,81,'2004-07-21',-20,'123456',250,100),(4,2,'Pablo Escobar',1212,81,'2010-09-14',0,'123456',300,250),(5,1,'Carla Oliveira',1111,81,'2017-01-06',1205,'123456',0,0),(6,1,'Fernanda Passos',5555,81,'2017-01-06',1000,'123456',0,0),(7,1,'Lissandra Garcia',2871,81,'2017-03-07',900,'123456',0,0),(8,2,'Felipe Santos',2338,80,'2017-03-07',600,'123456',210,0),(9,1,'Getulio Vargas',2971,100,'2017-03-07',1000,'123456',0,0);
/*!40000 ALTER TABLE `tbl_contas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_extrato`
--

DROP TABLE IF EXISTS `tbl_extrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_extrato` (
  `id_extrato` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tbl_operacao_id_operacao` int(10) unsigned NOT NULL,
  `tbl_contas_id_contas` int(10) unsigned NOT NULL,
  `cln_data` datetime NOT NULL,
  `cln_comentario` varchar(100) DEFAULT NULL,
  `cln_valor` float DEFAULT NULL,
  PRIMARY KEY (`id_extrato`),
  KEY `tbl_extrato_FKIndex1` (`tbl_contas_id_contas`),
  KEY `tbl_extrato_FKIndex2` (`tbl_operacao_id_operacao`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_extrato`
--

LOCK TABLES `tbl_extrato` WRITE;
/*!40000 ALTER TABLE `tbl_extrato` DISABLE KEYS */;
INSERT INTO `tbl_extrato` VALUES (1,1,1,'2017-02-13 16:35:44','',250),(2,1,1,'2017-02-13 16:35:44','',250),(3,2,1,'2017-02-17 22:37:18','',240),(4,1,1,'2017-02-21 11:45:21','',230),(5,2,1,'2017-03-02 06:22:04','',220),(6,1,1,'2017-03-04 16:35:44','',210),(7,1,1,'2017-03-04 16:35:48','',225),(8,3,1,'2017-03-05 00:00:00','Transferido para: Conta: 2222, AG: 79',125),(9,4,2,'2017-03-05 00:00:00','Transferido por: Conta: 1234, AG: 81',125),(10,3,1,'2017-03-05 23:50:42','Transferido para: Conta: 2222, AG: 79',35),(11,4,2,'2017-03-05 23:50:42','Transferido por: Conta: 1234, AG: 81',35),(12,3,2,'2017-03-05 23:52:18','Transferido para: Conta: 3333, AG: 81',112),(13,4,3,'2017-03-05 23:52:18','Transferido por: Conta: 2222, AG: 79',112),(14,1,3,'2017-03-05 23:52:57','',112),(15,1,1,'2017-03-05 23:54:12','',15),(16,1,1,'2017-03-06 15:07:12','',50),(18,4,2,'2017-03-06 15:07:58','Transferido por: Conta: 1234, AG: 81',150),(19,5,5,'2017-03-06 16:10:58','',1005),(20,5,5,'2017-03-06 16:19:14','',1010.03),(21,5,5,'2017-03-06 16:22:52','',1010.14),(22,5,5,'2017-03-06 16:24:44','',5.05068),(23,5,5,'2017-03-06 16:25:58','',5),(24,5,5,'2017-03-06 16:27:06','',5),(25,5,5,'2017-03-06 16:46:44','',5.02502),(26,5,5,'2017-03-06 16:47:51','',5.05017),(27,1,5,'2017-03-06 16:47:59','',15.005),(28,1,5,'2017-03-06 16:48:32','',0.8),(29,1,5,'2017-03-06 16:48:46','',0.28),(30,2,5,'2017-03-06 16:49:00','',1),(31,5,5,'2017-03-06 16:49:13','',5),(33,4,3,'2017-03-06 16:49:51','Transferido por: Conta: 1234, AG: 81',105.79),(37,5,5,'2017-03-01 00:00:00','Rendimento referente a: 02-2017',5),(40,4,3,'2017-03-06 17:49:19','Transferido por: Conta: 1234, AG: 81',100),(43,4,5,'2017-03-06 17:51:15','Transferido por: Conta: 1234, AG: 81',100),(44,2,1,'2017-03-06 17:53:50','',350),(45,2,1,'2017-03-06 17:54:04','',0.14),(46,2,1,'2017-03-06 17:54:19','',0.14),(47,2,1,'2017-03-06 17:54:47','',0.014),(48,2,1,'2017-03-06 17:55:07','',0.17),(49,6,1,'2017-03-06 17:55:25','',0.435),(50,3,1,'2017-03-06 17:55:25','Transferido para: Conta: 3333, AG: 81',14.5),(51,4,3,'2017-03-06 17:55:25','Transferido por: Conta: 1234, AG: 81',14.5),(52,6,1,'2017-03-06 17:55:39','',3),(53,3,1,'2017-03-06 17:55:39','Transferido para: Conta: 1111, AG: 81',100),(54,4,5,'2017-03-06 17:55:39','Transferido por: Conta: 1234, AG: 81',100),(55,2,7,'2017-03-01 00:00:00','Saque para validação.',0),(56,2,7,'2017-03-07 00:38:19','Depósito para Abertura de Conta.',900),(57,2,8,'2017-03-01 00:00:00','Saque para validação.',0),(58,2,8,'2017-03-07 00:41:08','Depósito para Abertura de Conta.',600),(59,2,9,'2017-03-01 00:00:00','Saque para validação.',0),(60,2,9,'2017-03-07 00:44:41','Depósito para Abertura de Conta.',1000);
/*!40000 ALTER TABLE `tbl_extrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_operacao`
--

DROP TABLE IF EXISTS `tbl_operacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_operacao` (
  `id_operacao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cln_tipoDeOperacao` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id_operacao`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_operacao`
--

LOCK TABLES `tbl_operacao` WRITE;
/*!40000 ALTER TABLE `tbl_operacao` DISABLE KEYS */;
INSERT INTO `tbl_operacao` VALUES (1,'Saque'),(2,'Deposito'),(3,'Transferência Realizada'),(4,'Transferência Recebida'),(5,'Rendimento'),(6,'Taxa de Transfêrencia');
/*!40000 ALTER TABLE `tbl_operacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_tipodeconta`
--

DROP TABLE IF EXISTS `tbl_tipodeconta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_tipodeconta` (
  `id_tipoDeConta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cln_nomeDoTipo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_tipoDeConta`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_tipodeconta`
--

LOCK TABLES `tbl_tipodeconta` WRITE;
/*!40000 ALTER TABLE `tbl_tipodeconta` DISABLE KEYS */;
INSERT INTO `tbl_tipodeconta` VALUES (1,'Poupança'),(2,' Conta-Corrente');
/*!40000 ALTER TABLE `tbl_tipodeconta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `v_extratomes`
--

DROP TABLE IF EXISTS `v_extratomes`;
/*!50001 DROP VIEW IF EXISTS `v_extratomes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_extratomes` AS SELECT 
 1 AS `codigo`,
 1 AS `operacao`,
 1 AS `numeroConta`,
 1 AS `dataOperacao`,
 1 AS `informacaoAdicional`,
 1 AS `valor`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_extratomesr`
--

DROP TABLE IF EXISTS `v_extratomesr`;
/*!50001 DROP VIEW IF EXISTS `v_extratomesr`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_extratomesr` AS SELECT 
 1 AS `codigo`,
 1 AS `operacao`,
 1 AS `numeroConta`,
 1 AS `dataOperacao`,
 1 AS `informacaoAdicional`,
 1 AS `valor`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `verificarlogin`
--

DROP TABLE IF EXISTS `verificarlogin`;
/*!50001 DROP VIEW IF EXISTS `verificarlogin`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `verificarlogin` AS SELECT 
 1 AS `numeroConta`,
 1 AS `agencia`,
 1 AS `titular`,
 1 AS `dataDeAbertura`,
 1 AS `saldo`,
 1 AS `limiteCreditoEspecial`,
 1 AS `creditoEspecialUtilizado`,
 1 AS `tipoConta`,
 1 AS `senha`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'desafiotechned'
--
/*!50003 DROP PROCEDURE IF EXISTS `sp_cadastro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_cadastro`(IN v_tipoconta VARCHAR(30), IN v_titular VARCHAR(100), IN v_numero INT, IN v_agencia INT, IN v_saldo FLOAT, IN v_senha VARCHAR(20))
BEGIN
	declare lid int;
	IF v_tipoconta = 'Conta Poupança' THEN
		insert into tbl_contas values (default,'1',v_titular,v_numero,v_agencia,CURDATE(),v_saldo,v_senha,'0','0');
	ELSE
		insert into tbl_contas values (default,'2',v_titular,v_numero,v_agencia,CURDATE(),v_saldo,v_senha,(v_saldo * 0.35),'0');  -- -- 35% do valor depositado pela primeira vez é o credito especial do usuario.
	end if;
    set lid = LAST_INSERT_ID();
    insert into tbl_extrato values (default,'2',lid,(DATE_FORMAT( CURRENT_DATE, '%Y-%m-01' )),'Saque para validação.','0');
    insert into tbl_extrato values (default,'2',lid,CURTIME(),'Depósito para Abertura de Conta.',v_saldo);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_realizarOperacaoCC` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_realizarOperacaoCC`(IN conta int,IN valor float, IN nsaldo float, IN nlimiteUsado float,IN operacao INT, IN info VARCHAR(100))
BEGIN
	declare v_idconta int;
    declare taxa float;
    set taxa = '0.03';
	set v_idconta = (select id_contas from tbl_contas where cln_numero = conta);
	UPDATE tbl_contas SET cln_saldo = nsaldo, cln_especialUtilizado = nlimiteUsado WHERE id_contas = v_idconta;
    INSERT INTO tbl_extrato VALUES (default,operacao,(select id_contas from tbl_contas where cln_numero = conta),(SELECT NOW()),info,valor);
	IF operacao = '3' then
        INSERT INTO tbl_extrato VALUES (default,6,(select id_contas from tbl_contas where cln_numero = conta),(SELECT NOW()),'',(valor * taxa));
    end if;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_realizarOperacaoCP` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_realizarOperacaoCP`(IN conta int,IN valor double, IN nsaldo double,IN operacao INT, IN info VARCHAR(100))
BEGIN
	declare v_idconta int;
	set v_idconta = (select id_contas from tbl_contas where cln_numero = conta);
    
	UPDATE tbl_contas SET cln_saldo = nsaldo WHERE id_contas = v_idconta;
    IF operacao = '5' then
    INSERT INTO tbl_extrato VALUES (default,operacao,(select id_contas from tbl_contas where cln_numero = conta),(DATE_FORMAT( CURRENT_DATE, '%Y-%m-01' )),CONCAT('Rendimento referente a: ',(DATE_FORMAT( CURRENT_DATE - INTERVAL 1 MONTH, '%m-%Y' ) )),valor);
    else
    INSERT INTO tbl_extrato VALUES (default,operacao,(select id_contas from tbl_contas where cln_numero = conta),(SELECT NOW()),info,valor);
    end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `v_extratomes`
--

/*!50001 DROP VIEW IF EXISTS `v_extratomes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_extratomes` AS select `tbl_extrato`.`id_extrato` AS `codigo`,`tbl_operacao`.`cln_tipoDeOperacao` AS `operacao`,`tbl_contas`.`cln_numero` AS `numeroConta`,`tbl_extrato`.`cln_data` AS `dataOperacao`,`tbl_extrato`.`cln_comentario` AS `informacaoAdicional`,`tbl_extrato`.`cln_valor` AS `valor` from ((`tbl_extrato` join `tbl_contas` on((`tbl_extrato`.`tbl_contas_id_contas` = `tbl_contas`.`id_contas`))) join `tbl_operacao` on((`tbl_extrato`.`tbl_operacao_id_operacao` = `tbl_operacao`.`id_operacao`))) where ((`tbl_extrato`.`cln_data` <= now()) and (`tbl_extrato`.`cln_data` >= (now() - interval 1 month))) order by `tbl_extrato`.`cln_data` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_extratomesr`
--

/*!50001 DROP VIEW IF EXISTS `v_extratomesr`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_extratomesr` AS select `tbl_extrato`.`id_extrato` AS `codigo`,`tbl_operacao`.`cln_tipoDeOperacao` AS `operacao`,`tbl_contas`.`cln_numero` AS `numeroConta`,`tbl_extrato`.`cln_data` AS `dataOperacao`,`tbl_extrato`.`cln_comentario` AS `informacaoAdicional`,`tbl_extrato`.`cln_valor` AS `valor` from ((`tbl_extrato` join `tbl_contas` on((`tbl_extrato`.`tbl_contas_id_contas` = `tbl_contas`.`id_contas`))) join `tbl_operacao` on((`tbl_extrato`.`tbl_operacao_id_operacao` = `tbl_operacao`.`id_operacao`))) where ((`tbl_extrato`.`cln_data` <= date_format(curdate(),'%Y/%m/01')) and (`tbl_extrato`.`cln_data` >= date_format((curdate() - interval 1 month),'%Y/%m/01'))) order by `tbl_extrato`.`cln_data` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `verificarlogin`
--

/*!50001 DROP VIEW IF EXISTS `verificarlogin`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `verificarlogin` AS select `tbl_contas`.`cln_numero` AS `numeroConta`,`tbl_contas`.`cln_agencia` AS `agencia`,`tbl_contas`.`cln_titular` AS `titular`,`tbl_contas`.`cln_dataAbertura` AS `dataDeAbertura`,`tbl_contas`.`cln_saldo` AS `saldo`,`tbl_contas`.`cln_limiteEspecial` AS `limiteCreditoEspecial`,`tbl_contas`.`cln_especialUtilizado` AS `creditoEspecialUtilizado`,`tbl_contas`.`tbl_tipoDeConta_id_tipoDeConta` AS `tipoConta`,`tbl_contas`.`cln_senha` AS `senha` from `tbl_contas` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-07  0:54:33
