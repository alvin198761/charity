/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80000
Source Host           : localhost:3306
Source Database       : ci_shan

Target Server Type    : MYSQL
Target Server Version : 80000
File Encoding         : 65001

Date: 2018-11-14 12:27:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin_dept`
-- ----------------------------
DROP TABLE IF EXISTS `admin_dept`;
CREATE TABLE `admin_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(20) DEFAULT NULL COMMENT '上级ID',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `enable` tinyint(4) DEFAULT NULL COMMENT '可用标志',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_dept
-- ----------------------------
INSERT INTO `admin_dept` VALUES ('1', '0', '总部', '1', '初始');

-- ----------------------------
-- Table structure for `admin_func`
-- ----------------------------
DROP TABLE IF EXISTS `admin_func`;
CREATE TABLE `admin_func` (
  `func_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型',
  `color` varchar(10) DEFAULT NULL COMMENT '颜色',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父结点ID',
  `tip` varchar(50) DEFAULT NULL COMMENT '提示信息',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `url` varchar(100) DEFAULT NULL COMMENT '地址',
  `order_num` smallint(5) DEFAULT NULL COMMENT '排序编号',
  PRIMARY KEY (`func_id`)
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8 COMMENT='菜单';

-- ----------------------------
-- Records of admin_func
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_menu`
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `icon` varchar(150) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `tip` varchar(255) DEFAULT NULL,
  `title` varchar(150) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_menu_rule`
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu_rule`;
CREATE TABLE `admin_menu_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `did` bigint(20) DEFAULT NULL,
  `mid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_menu_rule
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_role`
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_role
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_role_dept`
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_dept`;
CREATE TABLE `admin_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色主键',
  `dept_id` int(11) DEFAULT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_role_func`
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_func`;
CREATE TABLE `admin_role_func` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色主键',
  `user_id` int(11) DEFAULT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_role_func
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `admin_sys_user`;
CREATE TABLE `admin_sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '会员编号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `department_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_sys_user
-- ----------------------------
INSERT INTO `admin_sys_user` VALUES ('1004', 'admin', '21232f297a57a5a743894a0e4a801fc3', null, '1', '1', null, null, null);

-- ----------------------------
-- Table structure for `base_dict`
-- ----------------------------
DROP TABLE IF EXISTS `base_dict`;
CREATE TABLE `base_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `data_key` bigint(20) DEFAULT NULL COMMENT '键',
  `data_value` varchar(50) DEFAULT NULL COMMENT '值',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `type_code` bigint(20) DEFAULT NULL COMMENT '分类',
  `type_name` varchar(50) DEFAULT NULL COMMENT '类型名称',
  `author` bigint(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of base_dict
-- ----------------------------

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(4) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `author` bigint(20) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `c_id` bigint(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='捐助物资';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('2', '4', 'sdfsfdssd', '12', '2018-11-03 13:34:42', '1004', 'asdfasf', '4', '0');

-- ----------------------------
-- Table structure for `charity`
-- ----------------------------
DROP TABLE IF EXISTS `charity`;
CREATE TABLE `charity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(4) DEFAULT NULL,
  `charity_name` varchar(200) DEFAULT NULL,
  `phone_no` char(20) DEFAULT NULL,
  `gender` char(10) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `author` bigint(20) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `p_id` bigint(20) DEFAULT NULL,
  `category` tinyint(4) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='慈善方';

-- ----------------------------
-- Records of charity
-- ----------------------------
INSERT INTO `charity` VALUES ('3', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `charity` VALUES ('4', '7', '唐植超', '123123123123', '男', '123123123', null, null, '123123321132', '0', '1', null);
INSERT INTO `charity` VALUES ('5', '11', '卖火柴的小女孩', '123123123', '男', '123123123', '2018-10-31 12:26:58', '1004', '123123123', '0', '2', '0');

-- ----------------------------
-- Table structure for `charity_event`
-- ----------------------------
DROP TABLE IF EXISTS `charity_event`;
CREATE TABLE `charity_event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(4) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `chairman_id` bigint(20) DEFAULT NULL,
  `target_id` bigint(20) DEFAULT NULL,
  `event_time` datetime DEFAULT NULL,
  `author` bigint(20) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `chairmain_pnone` char(20) DEFAULT NULL,
  `target_phone` char(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='善行活动';

-- ----------------------------
-- Records of charity_event
-- ----------------------------
INSERT INTO `charity_event` VALUES ('1', '1', 'asdf', '123123', '1', '1', '2018-10-01 00:00:00', '1004', '123', '123', '123', '1', '2018-10-30 11:20:08');
INSERT INTO `charity_event` VALUES ('2', '1', 'asdf', 'asdfafds', '4', '5', '2018-11-09 00:00:00', '1004', 'fdsa', '13212123', '123123123', '1', '2018-11-03 16:17:49');
INSERT INTO `charity_event` VALUES ('3', '1', 'adsf', 'dfasdsfsdf', '4', '5', '2018-11-06 00:00:00', '1004', null, '1212312132', '12312312', '1', '2018-11-03 16:46:11');
INSERT INTO `charity_event` VALUES ('4', '3', 'adfdsfa', 'adfdafs', '4', '5', '2018-11-15 00:00:00', '1004', 'adsfdf', '123123', '123123', '1', '2018-11-03 16:48:54');

-- ----------------------------
-- Table structure for `event_user`
-- ----------------------------
DROP TABLE IF EXISTS `event_user`;
CREATE TABLE `event_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `event_id` bigint(20) DEFAULT NULL COMMENT '活动ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '参与人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='活动参与方';

-- ----------------------------
-- Records of event_user
-- ----------------------------
INSERT INTO `event_user` VALUES ('4', '4', '4');

-- ----------------------------
-- Table structure for `type_dict`
-- ----------------------------
DROP TABLE IF EXISTS `type_dict`;
CREATE TABLE `type_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='类型字典';

-- ----------------------------
-- Records of type_dict
-- ----------------------------
INSERT INTO `type_dict` VALUES ('2', '1', '捐款');
INSERT INTO `type_dict` VALUES ('3', '1', '物资');
INSERT INTO `type_dict` VALUES ('4', '2', '衣服');
INSERT INTO `type_dict` VALUES ('5', '2', '书本');
INSERT INTO `type_dict` VALUES ('6', '2', '学习用品');
INSERT INTO `type_dict` VALUES ('7', '3', '个人');
INSERT INTO `type_dict` VALUES ('8', '3', '公司');
INSERT INTO `type_dict` VALUES ('9', '3', '慈善组织');
INSERT INTO `type_dict` VALUES ('10', '3', '团体');
INSERT INTO `type_dict` VALUES ('11', '4', '个人');
INSERT INTO `type_dict` VALUES ('12', '4', '学校');
INSERT INTO `type_dict` VALUES ('13', '4', '家庭');
