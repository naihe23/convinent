/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : convinent

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-08-29 21:40:51
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `table_comment`
-- ----------------------------
DROP TABLE IF EXISTS `table_comment`;
CREATE TABLE `table_comment` (
  `comment_id` int(40) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(40) NOT NULL,
  `content` text NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `table_memo`
-- ----------------------------
DROP TABLE IF EXISTS `table_memo`;
CREATE TABLE `table_memo` (
  `memo_id` int(50) NOT NULL AUTO_INCREMENT,
  `edit_time` datetime NOT NULL,
  `send_time` datetime NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `state` int(2) NOT NULL,
  `memo_content` varchar(255) NOT NULL,
  PRIMARY KEY (`memo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_memo
-- ----------------------------

-- ----------------------------
-- Table structure for `table_user`
-- ----------------------------
DROP TABLE IF EXISTS `table_user`;
CREATE TABLE `table_user` (
  `user_id` int(50) NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `user_nickname` varchar(20) NOT NULL COMMENT '用户昵称',
  `user_password` varchar(32) NOT NULL COMMENT '用户密码',
  `user_email` varchar(50) NOT NULL COMMENT '用户邮箱\r\n	',
  `acti_state` int(11) unsigned zerofill NOT NULL DEFAULT '00000000000' COMMENT '激活状态，0表示未激活，1表示激活',
  `acti_code` varchar(200) NOT NULL COMMENT '随机验证码',
  `salt` varchar(50) NOT NULL COMMENT '随机盐，用于加密密码',
  `token_exptime` datetime NOT NULL COMMENT '用于判断邮箱链接有效时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of table_user
-- ----------------------------
INSERT INTO `table_user` VALUES ('29', 'naihe', 'df0ce7652250ebb7cd06ddc7bb10d953', 'zhangjwang@foxmail.com', '00000000001', 'a9e248c503da4c28b814fde4c636686ee07080151c93496891c33dc8d7e757f6', 'bc6b47f0-cab8-435f-9f8a-8b714d0e2d20', '2018-08-29 20:14:39');
