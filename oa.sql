-- MySQL dump 10.13  Distrib 5.7.43, for Win64 (x86_64)
--
-- Host: localhost    Database: oa
-- ------------------------------------------------------
-- Server version       5.7.43-log

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
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content` text,
  `department_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `announcement_department_id_fk` (`department_id`),
  CONSTRAINT `announcement_department_id_fk` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
INSERT INTO `announcement` VALUES (1,'Meeting Announcement','There will be a department meeting next Monday.',1,'2024-06-03 06:31:41','2024-06-03 06:31:41'),(2,'Holiday Notice','The office will be closed for the holidays from December 24th to December 26th.',2,'2024-06-03 06:31:41','2024-06-03 06:31:41'),(3,'New Policy Announcement','Please review the updated company policy document.',1,'2024-06-03 06:42:37','2024-06-03 06:42:37'),(4,'Training Session','There will be a training session on new software tools next Friday.',2,'2024-06-03 06:42:37','2024-06-03 06:42:37'),(5,'Team Building Activity','Join us for a team building activity at the local park next Saturday.',3,'2024-06-03 06:42:37','2024-06-03 06:42:37'),(6,'New Announcement','This is a test announcement for testing purposes.',3,'2024-06-05 06:54:02','2024-06-05 06:54:02'),(7,'New Announcement','This is a test announcement for testing purposes.',3,'2024-06-05 08:37:59','2024-06-05 08:37:59');
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `manager_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `department_user_id_fk` (`manager_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (0,'NULL','2024-06-04 11:37:24','2024-06-05 09:03:36',0),(1,'HR','2024-06-03 06:31:41','2024-06-04 04:29:14',2),(2,'Finance','2024-06-03 06:31:41','2024-06-04 04:29:23',3),(3,'IT','2024-06-03 06:31:41','2024-06-04 04:29:37',10),(4,'MANAGE','2024-06-04 04:30:23','2024-06-04 04:29:14',9),(5,'OPERATE','2024-06-04 11:37:24','2024-06-05 14:33:16',0);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department_transfer_request`
--

DROP TABLE IF EXISTS `department_transfer_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department_transfer_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `current_department_id` int(11) NOT NULL,
  `target_department_id` int(11) NOT NULL,
  `application_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status_out` enum('PENDING','APPROVED','REJECTED') NOT NULL DEFAULT 'PENDING',
  `status_in` enum('PENDING','APPROVED','REJECTED') NOT NULL DEFAULT 'PENDING',
  `reason` text,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `current_department_id` (`current_department_id`),
  KEY `target_department_id` (`target_department_id`),
  CONSTRAINT `department_transfer_request_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `department_transfer_request_ibfk_2` FOREIGN KEY (`current_department_id`) REFERENCES `department` (`id`) ON DELETE CASCADE,
  CONSTRAINT `department_transfer_request_ibfk_3` FOREIGN KEY (`target_department_id`) REFERENCES `department` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_transfer_request`
--

LOCK TABLES `department_transfer_request` WRITE;
/*!40000 ALTER TABLE `department_transfer_request` DISABLE KEYS */;
INSERT INTO `department_transfer_request` VALUES (1,1,1,2,'2024-06-09 16:00:00','PENDING','PENDING','Wants to move to a department with more opportunities for growth.','2024-06-06 01:32:04'),(2,3,2,3,'2024-06-10 16:00:00','PENDING','PENDING','Seeking a transfer to the IT department to use their technical skills.','2024-06-06 01:34:06'),(3,4,1,2,'2024-06-11 16:00:00','PENDING','PENDING','Desire to move to HR to better manage employee relations.','2024-06-06 01:34:06'),(4,7,2,1,'2024-06-07 06:30:34','APPROVED','APPROVED','don\'t like old department','2024-06-07 06:30:35'),(5,7,2,1,'2024-06-09 09:01:40','APPROVED','PENDING','don\'t like old department','2024-06-09 09:01:41');
/*!40000 ALTER TABLE `department_transfer_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `join_request`
--

DROP TABLE IF EXISTS `join_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `join_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `status` enum('PENDING','APPROVED','REJECTED') DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `join_request`
--

LOCK TABLES `join_request` WRITE;
/*!40000 ALTER TABLE `join_request` DISABLE KEYS */;
INSERT INTO `join_request` VALUES (1,7,1,'APPROVED','2024-06-05 07:15:29','2024-06-06 00:37:39'),(2,2,2,'PENDING','2024-06-05 07:15:29','2024-06-05 07:15:29'),(3,3,1,'APPROVED','2024-06-05 07:15:29','2024-06-05 07:15:29'),(4,6,3,'REJECTED','2024-06-05 07:15:29','2024-06-05 07:20:29'),(5,10,2,'APPROVED','2024-06-05 10:32:19','2024-06-05 16:06:29'),(6,2,2,'APPROVED','2024-06-07 12:46:46','2024-06-07 12:46:46');
/*!40000 ALTER TABLE `join_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_request`
--

DROP TABLE IF EXISTS `leave_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leave_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `reason` text,
  `status` enum('PENDING','APPROVED','REJECTED') DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_request`
--

LOCK TABLES `leave_request` WRITE;
/*!40000 ALTER TABLE `leave_request` DISABLE KEYS */;
INSERT INTO `leave_request` VALUES (1,1,'2024-06-05','2024-06-07','Family vacation','PENDING','2024-06-03 06:31:41','2024-06-03 06:31:41'),(2,2,'2024-06-10','2024-06-12','Personal reasons','APPROVED','2024-06-03 06:31:41','2024-06-03 06:31:41'),(3,3,'2024-06-15','2024-06-16','Medical leave','REJECTED','2024-06-03 06:31:41','2024-06-03 06:31:41'),(4,4,'2024-06-20','2024-06-22','Personal reasons','PENDING','2024-06-03 06:42:37','2024-06-03 06:42:37'),(5,5,'2024-06-25','2024-06-26','Family vacation','APPROVED','2024-06-03 06:42:37','2024-06-03 06:42:37'),(6,6,'2024-06-30','2024-07-02','Medical leave','REJECTED','2024-06-03 06:42:37','2024-06-03 06:42:37'),(7,7,'2024-06-05','2024-06-07','headache','APPROVED','2024-06-06 00:58:12','2024-06-06 01:18:35'),(8,7,'2024-06-05','2024-06-07','headache','PENDING','2024-07-06 04:20:15','2024-07-06 04:20:15');
/*!40000 ALTER TABLE `leave_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `description` text,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin','System Administrator','2024-06-03 06:31:41','2024-06-03 06:31:41'),(2,'Manager','Department Manager','2024-06-03 06:31:41','2024-06-03 06:31:41'),(3,'Employee','Regular Employee','2024-06-03 06:31:41','2024-06-03 06:31:41');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_department_id_fk` (`department_id`),
  KEY `user_role_id_fk` (`role_id`),
  CONSTRAINT `user_department_id_fk` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user1','123456',1,1,'2024-06-03 06:31:41','2024-06-04 05:22:05'),(2,'user2','123456',2,1,'2024-06-03 06:31:41','2024-06-04 05:22:05'),(3,'user3','123456',2,2,'2024-06-03 06:31:41','2024-06-06 16:15:44'),(4,'user4','123456',3,1,'2024-06-03 06:42:37','2024-06-04 05:22:05'),(5,'user5','123456',3,1,'2024-06-03 06:42:37','2024-06-04 05:22:05'),(6,'user6','123456',3,2,'2024-06-03 06:42:37','2024-06-07 11:29:59'),(7,'user7','123456',3,2,'2024-06-03 06:42:37','2024-06-07 11:29:59'),(8,'user8','123456',3,2,'2024-06-03 06:42:37','2024-06-07 11:29:59'),(9,'user9','123456',2,3,'2024-06-03 06:42:37','2024-06-06 16:15:55'),(10,'user10','123456',3,1,'2024-06-03 06:42:37','2024-06-06 16:09:39'),(11,'newUsername','newPassword123',0,1,'2024-06-04 14:28:51','2024-06-06 16:09:39'),(12,'gaochang','123456',3,3,'2024-06-07 09:45:48','2024-06-07 09:45:48'),(13,'testuemployee','123456',3,3,'2024-06-08 17:32:05','2024-06-08 17:32:05');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work`
--

DROP TABLE IF EXISTS `work`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `work` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content` text,
  `group_id` int(11) DEFAULT NULL,
  `status` enum('TODO','IN_PROGRESS','DONE') DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `work_work_group_id_fk` (`group_id`),
  CONSTRAINT `work_work_group_id_fk` FOREIGN KEY (`group_id`) REFERENCES `work_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work`
--

LOCK TABLES `work` WRITE;
/*!40000 ALTER TABLE `work` DISABLE KEYS */;
INSERT INTO `work` VALUES (1,'Project A','Complete project proposal by end of the week.',1,'TODO','2024-06-03 06:31:41','2024-06-03 06:31:41'),(2,'Task 1','Review the code changes and provide feedback.',2,'IN_PROGRESS','2024-06-03 06:31:41','2024-06-03 06:31:41'),(3,'Task 2','Update the user interface design based on the latest feedback.',3,'DONE','2024-06-03 06:31:41','2024-06-03 06:31:41'),(7,'test project','test content',1,'IN_PROGRESS','2024-06-07 04:32:54','2024-06-07 05:58:55');
/*!40000 ALTER TABLE `work` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_group`
--

DROP TABLE IF EXISTS `work_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `work_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(100) NOT NULL,
  `department_id` int(11) DEFAULT NULL,
  `leader_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `work_id` int(11) NOT NULL,
  `menber_1_id` int(11) DEFAULT NULL,
  `member_2_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `work_group_work_id_fk` (`work_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_group`
--

LOCK TABLES `work_group` WRITE;
/*!40000 ALTER TABLE `work_group` DISABLE KEYS */;
INSERT INTO `work_group` VALUES (1,'HR Team',1,2,'2024-06-03 06:31:41','2024-06-06 17:26:54',1,NULL,NULL),(2,'Finance Team',2,3,'2024-06-03 06:31:41','2024-06-06 17:26:54',2,NULL,NULL),(3,'IT Team',3,1,'2024-06-03 06:31:41','2024-06-06 17:26:54',3,NULL,NULL),(5,'Test Team',1,4,'2024-06-07 01:51:42','2024-06-07 01:51:42',0,5,6);
/*!40000 ALTER TABLE `work_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-18 11:35:44