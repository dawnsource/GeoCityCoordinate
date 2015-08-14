/*
Navicat MySQL Data Transfer

Source Server         : localhost-nisp3
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : iptable_db

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-08-14 09:51:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_china_province
-- ----------------------------
DROP TABLE IF EXISTS `t_china_province`;
CREATE TABLE `t_china_province` (
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
