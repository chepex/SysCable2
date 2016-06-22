-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: syscable
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `cliente`
--
CREATE DATABASE syscable;

USE syscable;

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idcliente` int(11) NOT NULL,
  `nombres` varchar(45) DEFAULT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `dui` varchar(45) DEFAULT NULL,
  `nit` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `lugar_trabajo` varchar(45) DEFAULT NULL,
  `dirTrabajo` varchar(45) DEFAULT NULL,
  `tel_trabajo` varchar(45) DEFAULT NULL,
  `nombre_conyugue` varchar(45) DEFAULT NULL,
  `trabajo_conyugue` varchar(45) DEFAULT NULL,
  `tel_trabajo_conyugue` varchar(45) DEFAULT NULL,
  `dirInstalacion` varchar(45) DEFAULT NULL,
  `profesion_idprofesion` int(11) NOT NULL,
  `departamento_iddepartamento` int(11) NOT NULL,
  `municipio_idmunicipio` int(11) NOT NULL,
  `nacionalidad_idnacionalidad` int(11) NOT NULL,
  `colonia_idcolonia` int(11) NOT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `user_create` varchar(45) DEFAULT NULL,
  `date_create` date DEFAULT NULL,
  `user_mod` varchar(45) DEFAULT NULL,
  `date_mod` date DEFAULT NULL,
  `dir_trabajo_conyugue` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcliente`),
  KEY `fk_cliente_profesion_idx` (`profesion_idprofesion`),
  KEY `fk_cliente_departamento1_idx` (`departamento_iddepartamento`),
  KEY `fk_cliente_municipio1_idx` (`municipio_idmunicipio`),
  KEY `fk_cliente_nacionalidad1_idx` (`nacionalidad_idnacionalidad`),
  KEY `fk_cliente_colonia1_idx` (`colonia_idcolonia`),
  CONSTRAINT `fk_cliente_colonia1` FOREIGN KEY (`colonia_idcolonia`) REFERENCES `colonia` (`idcolonia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_departamento1` FOREIGN KEY (`departamento_iddepartamento`) REFERENCES `departamento` (`iddepartamento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_municipio1` FOREIGN KEY (`municipio_idmunicipio`) REFERENCES `municipio` (`idmunicipio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_nacionalidad1` FOREIGN KEY (`nacionalidad_idnacionalidad`) REFERENCES `nacionalidad` (`idnacionalidad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_profesion` FOREIGN KEY (`profesion_idprofesion`) REFERENCES `profesion` (`idprofesion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colonia`
--

DROP TABLE IF EXISTS `colonia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `colonia` (
  `idcolonia` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `municipio_idmunicipio` int(11) NOT NULL,
  `departamento_iddepartamento` int(11) NOT NULL,
  PRIMARY KEY (`idcolonia`),
  KEY `fk_colonia_municipio1_idx` (`municipio_idmunicipio`),
  KEY `fk_colonia_departamento1_idx` (`departamento_iddepartamento`),
  CONSTRAINT `fk_colonia_departamento1` FOREIGN KEY (`departamento_iddepartamento`) REFERENCES `departamento` (`iddepartamento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_colonia_municipio1` FOREIGN KEY (`municipio_idmunicipio`) REFERENCES `municipio` (`idmunicipio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colonia`
--

LOCK TABLES `colonia` WRITE;
/*!40000 ALTER TABLE `colonia` DISABLE KEYS */;
/*!40000 ALTER TABLE `colonia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contrato` (
  `idcontrato` int(11) NOT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `cliente_idcliente` int(11) NOT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `fechainicio` date DEFAULT NULL,
  `fechafin` date DEFAULT NULL,
  `valor_total` decimal(16,4) DEFAULT NULL,
  `valor_cuota` decimal(16,4) DEFAULT NULL,
  `cuotas` decimal(16,4) DEFAULT NULL,
  `saldo` decimal(16,4) DEFAULT NULL,
  `cuotas_pagadas` decimal(16,4) DEFAULT NULL,
  `ultimoPago` date DEFAULT NULL,
  `user_create` varchar(45) DEFAULT NULL,
  `date_create` date DEFAULT NULL,
  `user_mod` varchar(45) DEFAULT NULL,
  `date_mod` date DEFAULT NULL,
  PRIMARY KEY (`idcontrato`),
  KEY `fk_contrato_cliente1_idx` (`cliente_idcliente`),
  CONSTRAINT `fk_contrato_cliente1` FOREIGN KEY (`cliente_idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contratoproducto`
--

DROP TABLE IF EXISTS `contratoproducto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contratoproducto` (
  `idcontratoProducto` int(11) NOT NULL,
  `contrato_idcontrato` int(11) NOT NULL,
  `producto_idproducto` int(11) NOT NULL,
  `valor` decimal(12,4) DEFAULT NULL,
  PRIMARY KEY (`idcontratoProducto`),
  KEY `fk_contratoProducto_contrato1_idx` (`contrato_idcontrato`),
  KEY `fk_contratoProducto_producto1_idx` (`producto_idproducto`),
  CONSTRAINT `fk_contratoProducto_contrato1` FOREIGN KEY (`contrato_idcontrato`) REFERENCES `contrato` (`idcontrato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contratoProducto_producto1` FOREIGN KEY (`producto_idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contratoproducto`
--

LOCK TABLES `contratoproducto` WRITE;
/*!40000 ALTER TABLE `contratoproducto` DISABLE KEYS */;
/*!40000 ALTER TABLE `contratoproducto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departamento` (
  `iddepartamento` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iddepartamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES (1,'Ahuachapán'),(2,'Cabañas'),(3,'Chalatenango'),(4,'Cuscatlán'),(5,'La Libertad'),(6,'La Paz'),(7,'La Union'),(8,'Morazán'),(9,'San Miguel'),(10,'San Salvador'),(11,'San Vicente'),(12,'Santa Ana'),(13,'Sonsonate'),(14,'Usulután');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `idmenu` int(11) NOT NULL,
  `menu` varchar(45) DEFAULT NULL,
  `activo` varchar(45) DEFAULT NULL,
  `user_create` varchar(45) DEFAULT NULL,
  `date_create` date DEFAULT NULL,
  `user_mod` varchar(45) DEFAULT NULL,
  `date_mod` date DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `submenu` int(3) DEFAULT NULL,
  `correlativo` int(3) DEFAULT NULL,
  `principal` tinyint(1) DEFAULT NULL,
  `modulo_idmodulo` int(11) NOT NULL,
  PRIMARY KEY (`idmenu`),
  KEY `fk_menu_modulo1_idx` (`modulo_idmodulo`),
  CONSTRAINT `fk_menu_modulo1` FOREIGN KEY (`modulo_idmodulo`) REFERENCES `modulo` (`idmodulo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'Catalogos','1',NULL,NULL,NULL,NULL,'#',1,0,1,1),(2,'Modulo','1',NULL,NULL,NULL,NULL,'/modulo/List.xhtml',1,1,1,1),(3,'Menu','1',NULL,NULL,NULL,NULL,'/menu/List.xhtml',1,2,1,1),(4,'Rol','1',NULL,NULL,NULL,NULL,'/rol/List.xhtml',1,3,1,1),(5,'Privilegios','1',NULL,NULL,NULL,NULL,'/rolMenu/List.xhtml',1,4,1,1),(6,'Usuario','1',NULL,NULL,NULL,NULL,'/usuario/List.xhtml',1,5,1,1),(7,'Procesos','1',NULL,NULL,NULL,NULL,'#',2,0,1,1),(8,'Cliente','1',NULL,NULL,NULL,NULL,'/cliente/List.xhtml',2,1,1,1),(9,'Contrato','1',NULL,NULL,NULL,NULL,'/contrato/List.xhtml',2,2,1,1),(10,'Pago','1',NULL,NULL,NULL,NULL,'/pago/List.xhtml',2,3,1,1);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modulo`
--

DROP TABLE IF EXISTS `modulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modulo` (
  `idmodulo` int(11) NOT NULL,
  `modulo` varchar(45) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `user_create` varchar(45) DEFAULT NULL,
  `date_create` date DEFAULT NULL,
  `user_mod` varchar(45) DEFAULT NULL,
  `date_mod` date DEFAULT NULL,
  PRIMARY KEY (`idmodulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modulo`
--

LOCK TABLES `modulo` WRITE;
/*!40000 ALTER TABLE `modulo` DISABLE KEYS */;
INSERT INTO `modulo` VALUES (1,'CXC',1,'',NULL,'',NULL);
/*!40000 ALTER TABLE `modulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `municipio`
--

DROP TABLE IF EXISTS `municipio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `municipio` (
  `idmunicipio` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `departamento_iddepartamento` int(11) NOT NULL,
  PRIMARY KEY (`idmunicipio`),
  KEY `fk_municipio_departamento1_idx` (`departamento_iddepartamento`),
  CONSTRAINT `fk_municipio_departamento1` FOREIGN KEY (`departamento_iddepartamento`) REFERENCES `departamento` (`iddepartamento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `municipio`
--

LOCK TABLES `municipio` WRITE;
/*!40000 ALTER TABLE `municipio` DISABLE KEYS */;
INSERT INTO `municipio` VALUES (102,'Apaneca',1),(103,'Atiquizaya',1),(104,'Concepción de Ataco',1),(105,'El Refugio',1),(106,'Guaymango',1),(107,'Jujutla',1),(108,'San Francisco Menéndez ',1),(109,'San Lorenzo',1),(110,'San Pedro Puxtla',1),(111,'Tacuba',1),(112,'Turín',1),(201,'Candelaria de la Frontera',12),(202,'Coatepeque',12),(203,'Chalchuapa',12),(204,'El Congo',12),(205,'El Porvenir',12),(206,'Masahuat',12),(207,'Metapán',12),(208,'San Antonio Pajonal',12),(209,'San Sebastián Salitrillo',12),(210,'Santa Ana',12),(211,'Santa Rosa Guachipilín',12),(212,'Santiago de la Frontera',12),(213,'Texistepeque',12),(301,'Acajutla',13),(302,'Armenia',13),(303,'Caluco',13),(304,'Cuisnahuat',13),(305,'Santa Isabel Ishuatán',13),(306,'Izalco',13),(307,'Juayúa',13),(308,'Nahuizalco',13),(309,'Nahulingo',13),(310,'Salcoatitán',13),(311,'San Antonio del Monte',13),(312,'San Julián',13),(313,'Santa Catarina Masahuat',13),(314,'Santo Domingo de Guzmán',13),(315,'Sonsonate',13),(316,'Sonzacate',13),(401,'Agua Caliente',3),(402,'Arcatao',3),(403,'Azacualpa',3),(404,'Citalá',3),(405,'Comalapa',3),(406,'Concepción Quezaltepeque',3),(407,'Chalatenango',3),(408,'Dulce Nombre de María',3),(409,'El Carrizal',3),(410,'El Paraíso',3),(411,'La Laguna',3),(412,'La Palma',3),(413,'La Reina',3),(414,'Las Vueltas',3),(415,'Nombre de Jesús',3),(416,'Nueva Concepción',3),(417,'Nueva Trinidad',3),(418,'Ojos de Agua',3),(419,'Potonico',3),(420,'San Antonio de la Cruz',3),(421,'San Antonio Los Ranchos',3),(422,'San Fernando',3),(423,'San Francisco Lempa',3),(424,'San Francisco Morazán',3),(425,'San Ignacio',3),(426,'San Isidro Labrador',3),(427,'San José Cancasque / Cancasque',3),(428,'San José Las Flores / Las Flores',3),(429,'San Luis del Carmen',3),(430,'San Miguel de Mercedes',3),(431,'San Rafael',3),(432,'Santa Rita',3),(433,'Tejutla',3),(501,'Antiguo Cuscatlán',6),(502,'Ciudad Arce',6),(503,'Colón',6),(504,'Comasagua',6),(505,'Chiltiupán',6),(506,'Huizúcar',6),(507,'Jayaque',6),(508,'Jicalapa',6),(509,'La Libertad',6),(510,'Nuevo Cuscatlán',6),(511,'Santa Tecla',6),(512,'Quezaltepeque',6),(513,'Sacacoyo',6),(514,'San José Villanueva',6),(515,'San Juan Opico',6),(516,'San Matías',6),(517,'San Pablo Tacachico',6),(518,'Tamanique',6),(519,'Talnique',6),(520,'Teotepeque',6),(521,'Tepecoyo',6),(522,'Zaragoza',6),(601,'Aguilares',10),(602,'Apopa',10),(603,'Ayutuxtepeque',10),(604,'Cuscatancingo',10),(605,'El Paisnal',10),(606,'Guazapa',10),(607,'Ilopango',10),(608,'Mejicanos',10),(609,'Nejapa',10),(610,'Panchimalco',10),(611,'Rosario de Mora',10),(612,'San Marcos',10),(613,'San Martín',10),(614,'San Salvador',10),(615,'Santiago Texacuangos',10),(616,'Santo Tomás',10),(617,'Soyapango',10),(618,'Tonacatepeque',10),(619,'Delgado',10),(701,'Candelaria',4),(702,'Cojutepeque',4),(703,'El Carmen',4),(704,'El Rosario',4),(705,'Monte San Juan',4),(706,'Oratorio de Concepción',4),(707,'San Bartolomé Perulapía',4),(708,'San Cristóbal',4),(709,'San José Guayabal',4),(710,'San Pedro Perulapán',4),(711,'San Rafael Cedros',4),(712,'San Ramón',4),(713,'Santa Cruz Analquito',4),(714,'Santa Cruz Michapa',4),(715,'Suchitoto',4),(716,'Tenancingo',4),(801,'Cuyultitán',7),(802,'El Rosario / Rosario de La Paz',7),(803,'Jerusalén',7),(804,'Mercedes La Ceiba',7),(805,'Olocuilta',7),(806,'Paraíso de Osorio',7),(807,'San Antonio Masahuat',7),(808,'San Emigdio',7),(809,'San Francisco Chinameca',7),(810,'San Juan Nonualco',7),(811,'San Juan Talpa',7),(812,'San Juan Tepezontes',7),(813,'San Luis Talpa',7),(814,'San Miguel Tepezontes',7),(815,'San Pedro Masahuat',7),(816,'San Pedro Nonualco',7),(817,'San Rafael Obrajuelo',7),(818,'Santa María Ostuma',7),(819,'Santiago Nonualco',7),(820,'Tapalhuaca',7),(821,'Zacatecoluca',7),(822,'San Luis La Herradura',7),(901,'Cinquera',2),(902,'Guacotecti',2),(903,'Ilobasco',2),(904,'Jutiapa',2),(905,'San Isidro',2),(906,'Sensuntepeque',2),(907,'Tejutepeque',2),(908,'Victoria',2),(909,'Dolores / Villa Dolores',2),(1001,'Apastepeque',11),(1002,'Guadalupe',11),(1003,'San Cayetano Istepeque',11),(1004,'Santa Clara',11),(1005,'Santo Domingo',11),(1006,'San Esteban Catarina',11),(1007,'San Ildefonso',11),(1008,'San Lorenzo',11),(1009,'San Sebastián',11),(1010,'San Vicente',11),(1011,'Tecoluca',11),(1012,'Tepetitán',11),(1013,'Verapaz',11),(1101,'Alegría',14),(1102,'Berlín',14),(1103,'California',14),(1104,'Concepción Batres',14),(1105,'El Triunfo',14),(1106,'Ereguayquín',14),(1107,'Estanzuelas',14),(1108,'Jiquilisco',14),(1109,'Jucuapa',14),(1110,'Jucuarán',14),(1111,'Mercedes Umaña',14),(1112,'Nueva Granada',14),(1113,'Ozatlán',14),(1114,'Puerto El Triunfo',14),(1115,'San Agustín',14),(1116,'San Buenaventura',14),(1117,'San Dionisio',14),(1118,'Santa Elena',14),(1119,'San Francisco Javier',14),(1120,'Santa María',14),(1121,'Santiago de María',14),(1122,'Tecapán',14),(1123,'Usulután',14),(1201,'Carolina',9),(1202,'Ciudad Barrios',9),(1203,'Comacarán',9),(1204,'Chapeltique',9),(1205,'Chinameca',9),(1206,'Chirilagua',9),(1207,'El Tránsito',9),(1208,'Lolotique',9),(1209,'Moncagua',9),(1210,'Nueva Guadalupe',9),(1211,'Nuevo Edén de San Juan',9),(1212,'Quelepa',9),(1213,'San Antonio del Mosco',9),(1214,'San Gerardo',9),(1215,'San Jorge',9),(1216,'San Luis de la Reina',9),(1217,'San Miguel',9),(1218,'San Rafael Oriente',9),(1219,'Sesori',9),(1220,'Uluazapa',9),(1301,'Arambala',5),(1302,'Cacaopera',5),(1303,'Corinto',5),(1304,'Chilanga',5),(1305,'Delicias de Concepción',5),(1306,'El Divisadero',5),(1307,'El Rosario',5),(1308,'Gualococti',5),(1309,'Guatajiagua',5),(1310,'Joateca',5),(1311,'Jocoaitique',5),(1312,'Jocoro',5),(1313,'Lolotiquillo',5),(1314,'Meanguera',5),(1315,'Osicala',5),(1316,'Perquín',5),(1317,'San Carlos',5),(1318,'San Fernando',5),(1319,'San Francisco Gotera',5),(1320,'San Isidro',5),(1321,'San Simón',5),(1322,'Sensembra',5),(1323,'Sociedad',5),(1324,'Torola',5),(1325,'Yamabal',5),(1326,'Yoloaiquín',5),(1401,'Anamorós',8),(1402,'Bolívar',8),(1403,'Concepción de Oriente',8),(1404,'Conchagua',8),(1405,'El Carmen',8),(1406,'El Sauce',8),(1407,'Intipucá',8),(1408,'La Unión',8),(1409,'Lilisque',8),(1410,'Meanguera del Golfo',8),(1411,'Nueva Esparta',8),(1412,'Pasaquina',8),(1413,'Polorós',8),(1414,'San Alejo',8),(1415,'San José',8),(1416,'Santa Rosa de Lima',8),(1417,'Yayantique',8),(1418,'Yucuaiquín',8);
/*!40000 ALTER TABLE `municipio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nacionalidad`
--

DROP TABLE IF EXISTS `nacionalidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nacionalidad` (
  `idnacionalidad` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idnacionalidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nacionalidad`
--

LOCK TABLES `nacionalidad` WRITE;
/*!40000 ALTER TABLE `nacionalidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `nacionalidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordenproducto`
--

DROP TABLE IF EXISTS `ordenproducto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordenproducto` (
  `idordenProducto` int(11) NOT NULL,
  `ordenProductocol` varchar(45) DEFAULT NULL,
  `ordenTrabajo_idordenTrabajo` int(11) NOT NULL,
  `producto_idproducto` int(11) NOT NULL,
  `precio` decimal(16,4) DEFAULT NULL,
  PRIMARY KEY (`idordenProducto`),
  KEY `fk_ordenProducto_ordenTrabajo1_idx` (`ordenTrabajo_idordenTrabajo`),
  KEY `fk_ordenProducto_producto1_idx` (`producto_idproducto`),
  CONSTRAINT `fk_ordenProducto_ordenTrabajo1` FOREIGN KEY (`ordenTrabajo_idordenTrabajo`) REFERENCES `ordentrabajo` (`idordenTrabajo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordenProducto_producto1` FOREIGN KEY (`producto_idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordenproducto`
--

LOCK TABLES `ordenproducto` WRITE;
/*!40000 ALTER TABLE `ordenproducto` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordenproducto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordentrabajo`
--

DROP TABLE IF EXISTS `ordentrabajo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordentrabajo` (
  `idordenTrabajo` int(11) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `cliente_idcliente` int(11) NOT NULL,
  `tecnico_idtecnico` int(11) NOT NULL,
  `fecha_ing` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `descripcionSolucion` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `tipoOrden_idtipoOrden` int(11) NOT NULL,
  `user_create` varchar(45) DEFAULT NULL,
  `date_create` date DEFAULT NULL,
  `user_mod` varchar(45) DEFAULT NULL,
  `date_mod` date DEFAULT NULL,
  `valor` decimal(16,4) DEFAULT NULL,
  PRIMARY KEY (`idordenTrabajo`),
  KEY `fk_ordenTrabajo_cliente1_idx` (`cliente_idcliente`),
  KEY `fk_ordenTrabajo_tecnico1_idx` (`tecnico_idtecnico`),
  KEY `fk_ordenTrabajo_tipoOrden1_idx` (`tipoOrden_idtipoOrden`),
  CONSTRAINT `fk_ordenTrabajo_cliente1` FOREIGN KEY (`cliente_idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordenTrabajo_tecnico1` FOREIGN KEY (`tecnico_idtecnico`) REFERENCES `tecnico` (`idtecnico`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordenTrabajo_tipoOrden1` FOREIGN KEY (`tipoOrden_idtipoOrden`) REFERENCES `tipoorden` (`idtipoOrden`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordentrabajo`
--

LOCK TABLES `ordentrabajo` WRITE;
/*!40000 ALTER TABLE `ordentrabajo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordentrabajo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pago` (
  `idpagos` int(11) NOT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `cliente_idcliente` int(11) NOT NULL,
  `contrato_idcontrato` int(11) NOT NULL,
  `valor` decimal(16,4) DEFAULT NULL,
  `num_cuota` int(11) DEFAULT NULL,
  `user_create` varchar(45) DEFAULT NULL,
  `date_create` date DEFAULT NULL,
  `user_mod` varchar(45) DEFAULT NULL,
  `date_mod` date DEFAULT NULL,
  `ordenTrabajo_idordenTrabajo` int(11) NOT NULL,
  `descripcion` varchar(145) DEFAULT NULL,
  `descuento` decimal(16,4) DEFAULT NULL,
  `total` decimal(16,4) DEFAULT NULL,
  PRIMARY KEY (`idpagos`),
  KEY `fk_pagos_cliente1_idx` (`cliente_idcliente`),
  KEY `fk_pagos_contrato1_idx` (`contrato_idcontrato`),
  KEY `fk_pagos_ordenTrabajo1_idx` (`ordenTrabajo_idordenTrabajo`),
  CONSTRAINT `fk_pagos_cliente1` FOREIGN KEY (`cliente_idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pagos_contrato1` FOREIGN KEY (`contrato_idcontrato`) REFERENCES `contrato` (`idcontrato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pagos_ordenTrabajo1` FOREIGN KEY (`ordenTrabajo_idordenTrabajo`) REFERENCES `ordentrabajo` (`idordenTrabajo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `idproducto` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `costo` decimal(16,4) DEFAULT NULL,
  `precio` decimal(16,4) DEFAULT NULL,
  `existencia` int(11) DEFAULT NULL,
  PRIMARY KEY (`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesion`
--

DROP TABLE IF EXISTS `profesion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profesion` (
  `idprofesion` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idprofesion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesion`
--

LOCK TABLES `profesion` WRITE;
/*!40000 ALTER TABLE `profesion` DISABLE KEYS */;
/*!40000 ALTER TABLE `profesion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `idrol` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `user_create` varchar(45) DEFAULT NULL,
  `date_create` date DEFAULT NULL,
  `user_mod` varchar(45) DEFAULT NULL,
  `date_mod` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ADMIN',1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol_menu`
--

DROP TABLE IF EXISTS `rol_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol_menu` (
  `idrol_menu` int(11) NOT NULL,
  `activo` varchar(45) DEFAULT NULL,
  `user_create` varchar(45) DEFAULT NULL,
  `date_create` date DEFAULT NULL,
  `user_mod` varchar(45) DEFAULT NULL,
  `date_mod` date DEFAULT NULL,
  `rol_idrol` int(11) NOT NULL,
  `menu_idmenu` int(11) NOT NULL,
  PRIMARY KEY (`idrol_menu`),
  KEY `fk_rol_menu_rol1_idx` (`rol_idrol`),
  KEY `fk_rol_menu_menu1_idx` (`menu_idmenu`),
  CONSTRAINT `fk_rol_menu_menu1` FOREIGN KEY (`menu_idmenu`) REFERENCES `menu` (`idmenu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rol_menu_rol1` FOREIGN KEY (`rol_idrol`) REFERENCES `rol` (`idrol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol_menu`
--

LOCK TABLES `rol_menu` WRITE;
/*!40000 ALTER TABLE `rol_menu` DISABLE KEYS */;
INSERT INTO `rol_menu` VALUES (1,'1',NULL,NULL,NULL,NULL,1,1),(2,'1',NULL,NULL,NULL,NULL,1,2),(3,'1',NULL,NULL,NULL,NULL,1,3),(4,'1',NULL,NULL,NULL,NULL,1,4),(5,'1',NULL,NULL,NULL,NULL,1,5),(6,'1',NULL,NULL,NULL,NULL,1,6),(7,'1',NULL,NULL,NULL,NULL,1,7),(8,'1','1',NULL,NULL,NULL,1,8),(9,'1',NULL,NULL,NULL,NULL,1,9),(10,'1','1',NULL,NULL,NULL,1,10);
/*!40000 ALTER TABLE `rol_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tecnico`
--

DROP TABLE IF EXISTS `tecnico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tecnico` (
  `idtecnico` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtecnico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tecnico`
--

LOCK TABLES `tecnico` WRITE;
/*!40000 ALTER TABLE `tecnico` DISABLE KEYS */;
/*!40000 ALTER TABLE `tecnico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoorden`
--

DROP TABLE IF EXISTS `tipoorden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoorden` (
  `idtipoOrden` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtipoOrden`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoorden`
--

LOCK TABLES `tipoorden` WRITE;
/*!40000 ALTER TABLE `tipoorden` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipoorden` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `user_rol`
--

DROP TABLE IF EXISTS `user_rol`;
/*!50001 DROP VIEW IF EXISTS `user_rol`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `user_rol` AS SELECT 
 1 AS `usuario`,
 1 AS `password`,
 1 AS `nombre`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `pass` varchar(45) DEFAULT NULL,
  `rol_idrol` int(11) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `fk_Usuario_rol1_idx` (`rol_idrol`),
  CONSTRAINT `fk_Usuario_rol1` FOREIGN KEY (`rol_idrol`) REFERENCES `rol` (`idrol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'MMIXCO','f9ddafee0a6de28430d61e67c0053e7d',1),(2,'ADMIN','f9ddafee0a6de28430d61e67c0053e7d',1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `user_rol`
--

/*!50001 DROP VIEW IF EXISTS `user_rol`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `user_rol` AS select `usuario`.`user_name` AS `usuario`,`usuario`.`pass` AS `password`,`rol`.`nombre` AS `nombre` from (`usuario` join `rol`) where (`usuario`.`rol_idrol` = `rol`.`idrol`) */;
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

-- Dump completed on 2016-05-23 21:49:14
