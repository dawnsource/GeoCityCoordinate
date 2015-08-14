/*
Navicat MySQL Data Transfer

Source Server         : localhost-nisp3
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : iptable_db

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-08-14 09:51:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_china_city
-- ----------------------------
DROP TABLE IF EXISTS `t_china_city`;
CREATE TABLE `t_china_city` (
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  PRIMARY KEY (`city`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
