/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : Library

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 29/12/2018 14:59:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Admin
-- ----------------------------
DROP TABLE IF EXISTS `Admin`;
CREATE TABLE `Admin` (
  `AdmId` int(11) NOT NULL AUTO_INCREMENT,
  `IDcard` varchar(18) DEFAULT NULL,
  `AdmPassword` varchar(50) DEFAULT NULL,
  `AdmRight` int(1) DEFAULT NULL,
  PRIMARY KEY (`AdmId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Admin
-- ----------------------------
BEGIN;
INSERT INTO `Admin` VALUES (1, '111', 'fgh2vckifm18lshfb4gj9q4j4i066v0d', 0);
COMMIT;

-- ----------------------------
-- Table structure for Book
-- ----------------------------
DROP TABLE IF EXISTS `Book`;
CREATE TABLE `Book` (
  `bookID` int(11) NOT NULL AUTO_INCREMENT,
  `bookName` varchar(200) NOT NULL,
  `booktypeid` varchar(7) NOT NULL,
  `press` varchar(50) NOT NULL,
  `brief` varchar(4096) DEFAULT NULL,
  `total` int(4) NOT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `author` varchar(30) DEFAULT NULL,
  `isbn` varchar(20) NOT NULL,
  `display` int(1) NOT NULL DEFAULT '1',
  `floor` int(3) DEFAULT NULL,
  `bookcase` int(3) DEFAULT NULL,
  `layer` int(3) DEFAULT NULL,
  PRIMARY KEY (`bookID`),
  KEY `type` (`booktypeid`),
  CONSTRAINT `type` FOREIGN KEY (`booktypeid`) REFERENCES `booktype` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1458478551 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for BorrowedBooks
-- ----------------------------
DROP TABLE IF EXISTS `BorrowedBooks`;
CREATE TABLE `BorrowedBooks` (
  `orderid` int(11) NOT NULL,
  `bookid` int(11) NOT NULL,
  `ReturnTime` datetime DEFAULT NULL,
  `BorrowTime` datetime DEFAULT NULL,
  `LimitTime` datetime DEFAULT NULL,
  PRIMARY KEY (`orderid`,`bookid`) USING BTREE,
  KEY `bookId` (`bookid`),
  CONSTRAINT `bookId` FOREIGN KEY (`bookid`) REFERENCES `Book` (`bookID`) ON DELETE CASCADE,
  CONSTRAINT `orderID` FOREIGN KEY (`orderid`) REFERENCES `borrow` (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for booktype
-- ----------------------------
DROP TABLE IF EXISTS `booktype`;
CREATE TABLE `booktype` (
  `id` varchar(7) NOT NULL,
  `booktype` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of booktype
-- ----------------------------
BEGIN;
INSERT INTO `booktype` VALUES ('A', '马克思主义、列宁主义、毛泽东思想、邓小平理论');
INSERT INTO `booktype` VALUES ('B', '哲学、宗教');
INSERT INTO `booktype` VALUES ('C', '社会科学总论');
INSERT INTO `booktype` VALUES ('D', '政治、法律');
INSERT INTO `booktype` VALUES ('E', '军事');
INSERT INTO `booktype` VALUES ('F', '经济');
INSERT INTO `booktype` VALUES ('G', '文化、科学、教育、体育');
INSERT INTO `booktype` VALUES ('H', '语言、文字');
INSERT INTO `booktype` VALUES ('I', '文学');
INSERT INTO `booktype` VALUES ('J', '艺术');
INSERT INTO `booktype` VALUES ('K', '历史、地理');
INSERT INTO `booktype` VALUES ('N', '自然科学总论');
INSERT INTO `booktype` VALUES ('O', '数理科学和化学');
INSERT INTO `booktype` VALUES ('P', '天文学、地球科学');
INSERT INTO `booktype` VALUES ('Q', '生物科学');
INSERT INTO `booktype` VALUES ('R', '医药、卫生');
INSERT INTO `booktype` VALUES ('S', '农业科学');
INSERT INTO `booktype` VALUES ('T', '工业技术');
INSERT INTO `booktype` VALUES ('U', '交通运输');
INSERT INTO `booktype` VALUES ('V', '航空、航天');
INSERT INTO `booktype` VALUES ('X', '环境科学、劳动保护科学（安全科学）');
INSERT INTO `booktype` VALUES ('Z', '综合性图书');
COMMIT;

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  PRIMARY KEY (`orderId`),
  KEY `borrow_id` (`userID`),
  CONSTRAINT `borrow_id` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory` (
  `bookid` int(11) NOT NULL,
  `left_num` int(4) DEFAULT NULL,
  PRIMARY KEY (`bookid`),
  CONSTRAINT `bookid_left` FOREIGN KEY (`bookid`) REFERENCES `Book` (`bookID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `TicketId` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) NOT NULL,
  `overdueTime` int(11) NOT NULL,
  `dealTime` datetime DEFAULT NULL,
  `fee` decimal(4,1) NOT NULL,
  `ticket_status` int(1) NOT NULL,
  `bookid` int(11) NOT NULL,
  PRIMARY KEY (`TicketId`),
  KEY `ticket_orderID` (`orderId`),
  CONSTRAINT `ticket_orderID` FOREIGN KEY (`orderId`) REFERENCES `borrow` (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`userID`),
  CONSTRAINT `id` FOREIGN KEY (`userID`) REFERENCES `userInfo` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'fgh2vckifm18lshfb4gj9q4j4i066v0d');
COMMIT;

-- ----------------------------
-- Table structure for userInfo
-- ----------------------------
DROP TABLE IF EXISTS `userInfo`;
CREATE TABLE `userInfo` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `IDcard` char(19) NOT NULL,
  `sex` char(2) NOT NULL,
  `userTele` varchar(20) NOT NULL,
  `access` int(1) NOT NULL,
  `user_status` int(1) NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `IDcard_2` (`IDcard`),
  KEY `userID` (`userID`),
  KEY `IDcard` (`IDcard`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userInfo
-- ----------------------------
BEGIN;
INSERT INTO `userInfo` VALUES (1, 'jack', '123', '男', '12312312', 1, 1);

COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
