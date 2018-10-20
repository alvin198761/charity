/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.92
Source Server Version : 50719
Source Host           : 192.168.1.92:3306
Source Database       : admindata

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-10-20 15:01:17
*/

SET FOREIGN_KEY_CHECKS=0;

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
INSERT INTO `admin_func` VALUES ('1', '1', '', 'fa fa-laptop', '系统菜单', '0', '系统设置', '', '1', '', '1');
INSERT INTO `admin_func` VALUES ('2', '1', '', 'el-icon-menu', '门店管理', '1', '加盟商相关管理', null, '1', '', '2');
INSERT INTO `admin_func` VALUES ('3', '1', '', 'fa fa-handshake-o', '加盟商工作', '2', '加盟商管理', null, '1', '/branch/league', '3');
INSERT INTO `admin_func` VALUES ('4', '1', '', 'fa fa-home', '门店工作', '2', '门店管理', null, '1', '/branch/newList', '7');
INSERT INTO `admin_func` VALUES ('209', '1', '', 'fa fa-key', '门店权限', '2', '门店权限', null, '1', '/branch/rule', '5');
INSERT INTO `admin_func` VALUES ('210', '1', '', 'fa fa-key', '咨询信息', '2', '咨询信息', null, '1', '/branch/consule', '6');
INSERT INTO `admin_func` VALUES ('211', '1', '', 'fa fa-key', '门店功能', '2', '门店功能', null, '1', '/branch/function', '8');
INSERT INTO `admin_func` VALUES ('212', '1', '', 'el-icon-setting', '后台管理', '1', '后台管理', null, '1', '', '8');
INSERT INTO `admin_func` VALUES ('216', '1', '', 'fa fa-user-plus', '门店人员', '212', '门店人员', null, '1', '/setting/person', '12');
INSERT INTO `admin_func` VALUES ('217', '1', '', 'fa fa-user-plus', '人员编辑', '212', '人员编辑', null, '1', '/setting/editpersonList', '13');
INSERT INTO `admin_func` VALUES ('218', '1', '', 'el-icon-edit', '协议设置', '1', '协议设置', null, '1', '', '14');
INSERT INTO `admin_func` VALUES ('219', '1', '', 'fa fa-gavel', '协议模板管理', '218', '协议模板管理', null, '1', '/protocolTemplate/list', '15');
INSERT INTO `admin_func` VALUES ('220', '1', '', 'fa fa-asl-interpreting', 'APP注册协议', '218', 'APP注册协议', null, '1', '/protocolTemplate/AppProtocol', '16');
INSERT INTO `admin_func` VALUES ('221', '1', '', 'el-icon-document', '系统权限', '1', '系统权限', null, '1', '', '17');
INSERT INTO `admin_func` VALUES ('222', '1', '', 'el-icon-document', '用户管理', '221', '用户管理', null, '1', '/sys/adminSysUser', '18');
INSERT INTO `admin_func` VALUES ('223', '1', '', 'el-icon-document', '部门管理', '221', '部门管理', null, '1', '/sys/adminDept', '19');
INSERT INTO `admin_func` VALUES ('224', '1', '', 'el-icon-document', '角色管理', '221', '角色管理', null, '1', '/sys/adminRole', '20');
INSERT INTO `admin_func` VALUES ('225', '1', '', 'el-icon-document', '菜单管理', '221', '菜单管理', null, '1', '/sys/adminMenu', '21');
INSERT INTO `admin_func` VALUES ('226', '1', '', 'el-icon-document', '设备管理', '1', '设备管理', null, '1', '', '22');
INSERT INTO `admin_func` VALUES ('227', '1', '', 'fa fa-street-view', '体测仪管理', '226', '体测仪管理', null, '1', '/bca/list', '23');
INSERT INTO `admin_func` VALUES ('228', '1', '', 'fa fa-tags', '体测问题管理', '226', '体测问题管理', null, '1', '/bcaquestion/list', '24');
INSERT INTO `admin_func` VALUES ('229', '1', '', 'el-icon-document', 'APP内容发布', '1', 'APP内容发布', null, '1', '', '25');
INSERT INTO `admin_func` VALUES ('230', '1', '', 'fa fa-newspaper-o', '新闻推送', '229', '新闻推送', null, '1', '/cms/app-news', '26');
INSERT INTO `admin_func` VALUES ('231', '1', '', 'fa fa-server', '首页轮播设置', '229', '首页轮播设置', null, '1', '/cms/app-home', '27');
INSERT INTO `admin_func` VALUES ('232', '1', '', 'el-icon-setting', '软件运维', '1', '软件运维', null, '1', '', '29');
INSERT INTO `admin_func` VALUES ('234', '1', '', 'fa fa-envelope', '软件反馈', '232', '软件反馈', null, '1', '/setting/usefeedbackList', '30');
INSERT INTO `admin_func` VALUES ('235', '1', '', 'fa fa-laptop', 'WEB菜单', '232', 'WEB菜单', null, '1', '/setting/webFunctionList', '31');
INSERT INTO `admin_func` VALUES ('236', '1', '', 'fa fa-amazon', 'APP菜单', '232', 'APP菜单', null, '1', '/setting/appFunctionList', '32');
INSERT INTO `admin_func` VALUES ('237', '1', '', 'icon-wrench', '软件版本菜单', '232', '软件版本菜单', null, '1', '/setting/version_menu', '33');
INSERT INTO `admin_func` VALUES ('238', '1', '', 'el-icon-edit', '软件设置', '1', '软件设置', null, '1', '', '36');
INSERT INTO `admin_func` VALUES ('239', '1', '', 'fa fa-wordpress', '敏感字管理', '238', '敏感字管理', null, '1', '/setting/sensitive', '35');
INSERT INTO `admin_func` VALUES ('240', '1', '', 'fa fa-pencil-square-o', '回访内容管理', '238', '回访内容管理', null, '1', '/setting/dict', '28');
INSERT INTO `admin_func` VALUES ('241', '1', '', 'fa fa-wpforms', '说明须知管理', '238', '说明须知管理', null, '1', '/setting/explainList', '35');
INSERT INTO `admin_func` VALUES ('242', '1', '', 'el-icon-document', '缓存管理', '232', '缓存管理', '', '1', '/setting/redis', '36');
INSERT INTO `admin_func` VALUES ('243', '1', '', 'fa fa-leanpub', 'APP更新日志', '238', 'APP更新日志', '', '1', '/soft/appversion', '29');

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
INSERT INTO `admin_menu` VALUES ('1', 'el-icon-menu', null, '加盟商相关管理', '门店管理', null);
INSERT INTO `admin_menu` VALUES ('2', 'fa fa-handshake-o', '1', '加盟商管理', '加盟商工作', '/branch/league');
INSERT INTO `admin_menu` VALUES ('3', 'fa fa-home', '1', '门店管理', '门店工作', '/branch/newList');
INSERT INTO `admin_menu` VALUES ('4', 'fa fa-key', '1', '门店权限', '门店权限', '/branch/rule');
INSERT INTO `admin_menu` VALUES ('5', 'el-icon-setting', null, '后台相关管理', '后台管理', null);
INSERT INTO `admin_menu` VALUES ('6', 'fa fa-vcard', '5', '部门管理', '部门管理', '/setting/department');
INSERT INTO `admin_menu` VALUES ('7', 'fa fa-users', '5', '员工管理', '员工管理', '/setting/user');
INSERT INTO `admin_menu` VALUES ('8', 'fa fa-wrench', '5', '系统权限', '系统权限', '/setting/role');
INSERT INTO `admin_menu` VALUES ('9', 'el-icon-document', null, '内容发布管理', 'APP 内容发布', null);
INSERT INTO `admin_menu` VALUES ('10', 'fa fa-newspaper-o', '9', '新闻推送', '新闻推送', '/cms/app-news');
INSERT INTO `admin_menu` VALUES ('11', 'fa fa-server', '9', '首页轮播', '首页轮播设置', '/cms/app-home');
INSERT INTO `admin_menu` VALUES ('12', 'el-icon-edit', null, '设置', '软件设置', null);
INSERT INTO `admin_menu` VALUES ('14', 'el-icon-document', null, '设备管理', '设备管理', null);
INSERT INTO `admin_menu` VALUES ('15', 'fa fa-street-view', '14', '体测仪管理', '体测仪管理', '/bca/list');
INSERT INTO `admin_menu` VALUES ('16', 'fa fa-user-plus', '5', '人员管理', '门店人员', '/setting/person');
INSERT INTO `admin_menu` VALUES ('17', 'fa fa-wordpress', '12', '敏感字管理', '敏感字管理', '/setting/sensitive');
INSERT INTO `admin_menu` VALUES ('19', 'fa fa-pencil-square-o', '12', '回访内容管理', '回访内容管理', '/setting/dict');
INSERT INTO `admin_menu` VALUES ('20', 'fa fa-tags', '14', '体测问题管理', '体测问题管理', '/bcaquestion/list');
INSERT INTO `admin_menu` VALUES ('21', 'el-icon-edit', null, '协议设置', '协议设置', null);
INSERT INTO `admin_menu` VALUES ('24', 'fa fa-gavel', '21', '协议模板管理', '协议模板管理', '/protocolTemplate/list');
INSERT INTO `admin_menu` VALUES ('25', 'fa fa-asl-interpreting', '21', 'APP注册协议', 'APP 注册协议', '/protocolTemplate/AppProtocol');
INSERT INTO `admin_menu` VALUES ('26', 'fa fa-wpforms', '12', '说明须知管理', '说明须知管理', '/setting/explainList');
INSERT INTO `admin_menu` VALUES ('27', 'el-icon-setting', null, '软件开发部门运维', '软件运维', '');
INSERT INTO `admin_menu` VALUES ('28', 'fa fa-leanpub', '27', 'APP 更新日志', 'APP 更新日志', '/soft/appversion');
INSERT INTO `admin_menu` VALUES ('29', 'fa fa-envelope', '27', '软件反馈', '软件反馈', '/setting/usefeedbackList');
INSERT INTO `admin_menu` VALUES ('32', 'fa fa-laptop', '27', 'web菜单', 'web 菜单', '/setting/webFunctionList');
INSERT INTO `admin_menu` VALUES ('33', 'fa fa-amazon', '27', 'APP菜单', 'APP 菜单', '/setting/appFunctionList');
INSERT INTO `admin_menu` VALUES ('34', 'fa fa-user-plus', '5', '人员编辑', '人员编辑', '/setting/editpersonList');
INSERT INTO `admin_menu` VALUES ('35', 'icon-wrench', '27', '软件版本菜单', '软件版本菜单', '/setting/version_menu');
INSERT INTO `admin_menu` VALUES ('37', 'fa fa-key', '1', '门店功能', '门店功能', '/branch/function');
INSERT INTO `admin_menu` VALUES ('38', 'el-icon-document', null, '系统权限', '系统权限', null);
INSERT INTO `admin_menu` VALUES ('39', 'el-icon-document', '38', '部门管理', '用户管理', '/sys/adminSysUser');
INSERT INTO `admin_menu` VALUES ('40', 'el-icon-document', '38', '用户管理', '部门管理', '/sys/adminDept');
INSERT INTO `admin_menu` VALUES ('41', 'el-icon-document', '38', '角色管理', '角色管理', '/sys/adminRole');
INSERT INTO `admin_menu` VALUES ('42', 'el-icon-document', '38', '菜单管理', '菜单管理', '/sys/adminMenu');

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
INSERT INTO `admin_menu_rule` VALUES ('65', '1', '1');
INSERT INTO `admin_menu_rule` VALUES ('66', '1', '2');
INSERT INTO `admin_menu_rule` VALUES ('67', '1', '3');
INSERT INTO `admin_menu_rule` VALUES ('68', '1', '4');
INSERT INTO `admin_menu_rule` VALUES ('69', '1', '5');
INSERT INTO `admin_menu_rule` VALUES ('70', '1', '9');
INSERT INTO `admin_menu_rule` VALUES ('71', '1', '12');
INSERT INTO `admin_menu_rule` VALUES ('72', '1', '14');
INSERT INTO `admin_menu_rule` VALUES ('73', '1', '21');
INSERT INTO `admin_menu_rule` VALUES ('74', '1', '27');
INSERT INTO `admin_menu_rule` VALUES ('75', '2', '1');
INSERT INTO `admin_menu_rule` VALUES ('76', '2', '2');
INSERT INTO `admin_menu_rule` VALUES ('77', '2', '3');
INSERT INTO `admin_menu_rule` VALUES ('78', '2', '5');
INSERT INTO `admin_menu_rule` VALUES ('79', '2', '7');
INSERT INTO `admin_menu_rule` VALUES ('80', '2', '8');
INSERT INTO `admin_menu_rule` VALUES ('81', '2', '9');
INSERT INTO `admin_menu_rule` VALUES ('82', '2', '11');
INSERT INTO `admin_menu_rule` VALUES ('83', '2', '12');
INSERT INTO `admin_menu_rule` VALUES ('84', '2', '14');
INSERT INTO `admin_menu_rule` VALUES ('85', '2', '15');
INSERT INTO `admin_menu_rule` VALUES ('86', '2', '21');
INSERT INTO `admin_menu_rule` VALUES ('87', '2', '27');
INSERT INTO `admin_menu_rule` VALUES ('98', '7', '1');
INSERT INTO `admin_menu_rule` VALUES ('99', '7', '5');
INSERT INTO `admin_menu_rule` VALUES ('100', '7', '6');
INSERT INTO `admin_menu_rule` VALUES ('101', '7', '7');
INSERT INTO `admin_menu_rule` VALUES ('102', '7', '8');
INSERT INTO `admin_menu_rule` VALUES ('103', '7', '9');
INSERT INTO `admin_menu_rule` VALUES ('104', '7', '12');
INSERT INTO `admin_menu_rule` VALUES ('105', '7', '14');
INSERT INTO `admin_menu_rule` VALUES ('106', '7', '21');
INSERT INTO `admin_menu_rule` VALUES ('107', '7', '27');

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
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_sys_user
-- ----------------------------
INSERT INTO `admin_sys_user` VALUES ('1', '管理员', '532aeb4481a119e4d9860829cb197bdd', '11111111111', '1', '1', '1', '', '');
