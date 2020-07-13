/*
Navicat MySQL Data Transfer

Source Server         : 10.8.18.17
Source Server Version : 50717
Source Host           : 10.8.18.17:3306
Source Database       : db_auth

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-07-13 14:42:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_account
-- ----------------------------
DROP TABLE IF EXISTS `base_account`;
CREATE TABLE `base_account` (
  `account_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `account` varchar(255) NOT NULL COMMENT '标识：手机号、邮箱、 用户名、或第三方应用的唯一标识',
  `password` varchar(255) NOT NULL COMMENT '密码凭证：站内的保存密码、站外的不保存或保存token）',
  `account_type` varchar(255) NOT NULL COMMENT '登录类型:password-密码、mobile-手机号、email-邮箱、weixin-微信、weibo-微博、qq-等等',
  `domain` varchar(255) DEFAULT NULL COMMENT '账户域:@admin.com,@developer.com',
  `register_ip` varchar(255) DEFAULT NULL COMMENT '注册IP',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `status` int(11) DEFAULT NULL COMMENT '状态:0-禁用 1-启用 2-锁定',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`account_id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录账号';

-- ----------------------------
-- Records of base_account
-- ----------------------------
INSERT INTO `base_account` VALUES ('521677655368531968', '521677655146233856', 'admin', '$2a$10$P1p1wKVe.0gjX7nr5K1soeSjM.j.s1Xm7Nx5t4DFcjUDC.7Bx2D0K', 'username', '@admin.com', null, '2019-07-03 17:11:59', '1', '2020-07-09 17:57:38');
INSERT INTO `base_account` VALUES ('1282506305675841538', '1282506304530796545', 'huashaoge', '$2a$10$5hSnPtyp1AqC7S/vINZ7H.xmuHK9UTJq3fk6LRr2Uav/0JY5HtC.u', 'username', '@admin.com', null, '2020-07-13 10:45:06', '1', '2020-07-13 10:45:06');

-- ----------------------------
-- Table structure for base_action
-- ----------------------------
DROP TABLE IF EXISTS `base_action`;
CREATE TABLE `base_action` (
  `action_id` bigint(20) NOT NULL COMMENT '资源ID',
  `action_code` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '资源编码',
  `action_name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '资源名称',
  `action_desc` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '资源描述',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '资源父节点',
  `priority` int(10) NOT NULL DEFAULT '0' COMMENT '优先级 越小越靠前',
  `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '状态:0-无效 1-有效',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_persist` tinyint(3) NOT NULL DEFAULT '0' COMMENT '保留数据0-否 1-是 不允许删除',
  `service_id` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '服务名称',
  PRIMARY KEY (`action_id`),
  UNIQUE KEY `action_code` (`action_code`) USING BTREE,
  UNIQUE KEY `action_id` (`action_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统资源-功能操作';

-- ----------------------------
-- Records of base_action
-- ----------------------------
INSERT INTO `base_action` VALUES ('1272810046417305601', 'systemMenuEdit', '修改', '', '4', '1', '1', '2020-06-16 16:35:37', '2020-06-17 15:21:03', '0', 'venus-admin');
INSERT INTO `base_action` VALUES ('1272810133713354754', 'systemMenuAdd', '新增', '', '4', '1', '1', '2020-06-16 16:35:58', '2020-06-16 16:35:58', '0', 'venus-admin');
INSERT INTO `base_action` VALUES ('1273155797177704449', 'systemMenuDel', '删除', '', '4', '1', '1', '2020-06-17 15:29:31', '2020-06-17 15:36:31', '0', 'venus-admin');
INSERT INTO `base_action` VALUES ('1273157652704239618', 'systemMenuAction', '功能', '', '4', '1', '1', '2020-06-17 15:36:53', '2020-06-17 15:36:53', '0', 'venus-admin');
INSERT INTO `base_action` VALUES ('1282500972198875137', 'systemUserEdit', '编辑', '', '1268066315134939137', '1', '1', '2020-07-13 10:23:54', '2020-07-13 10:23:54', '0', 'venus-admin');
INSERT INTO `base_action` VALUES ('1282501158883151873', 'systemUserGrantRolesEdit', '分配用户角色', '', '1268066315134939137', '1', '1', '2020-07-13 10:24:39', '2020-07-13 10:24:39', '0', 'venus-admin');
INSERT INTO `base_action` VALUES ('1282501351061966849', 'systemUserGrantMenusEdit', '分配用户私人菜单', '', '1268066315134939137', '1', '1', '2020-07-13 10:25:24', '2020-07-13 10:25:24', '0', 'venus-admin');
INSERT INTO `base_action` VALUES ('1282501522579640321', 'systemUserPasswordEdit', '系统用户密码修改', '', '1268066315134939137', '1', '1', '2020-07-13 10:26:05', '2020-07-13 10:26:05', '0', 'venus-admin');
INSERT INTO `base_action` VALUES ('1282501925429956609', 'systemUserAdd', '添加系统用户', '', '1268066315134939137', '1', '1', '2020-07-13 10:27:41', '2020-07-13 10:27:41', '0', 'venus-admin');
INSERT INTO `base_action` VALUES ('1282503659892101121', 'systemUserSearch', '系统用户条件查询', '', '1268066315134939137', '1', '1', '2020-07-13 10:34:35', '2020-07-13 10:34:35', '0', 'venus-admin');
INSERT INTO `base_action` VALUES ('1282503942818877442', 'systemRoleSearch', '系统角色条件查询', '', '1273160837359575042', '1', '1', '2020-07-13 10:35:42', '2020-07-13 10:35:42', '0', 'venus-admin');
INSERT INTO `base_action` VALUES ('1282504071349129218', 'systemRoleAdd', '系统角色添加', '', '1273160837359575042', '1', '1', '2020-07-13 10:36:13', '2020-07-13 10:36:13', '0', 'venus-admin');
INSERT INTO `base_action` VALUES ('1282504157319778305', 'systemRoleEdit', '系统角色编辑', '', '1273160837359575042', '1', '1', '2020-07-13 10:36:34', '2020-07-13 10:36:34', '0', 'venus-admin');
INSERT INTO `base_action` VALUES ('1282504329470791681', 'systemRoleGrantMenusEdit', '系统角色分配菜单', '', '1273160837359575042', '1', '1', '2020-07-13 10:37:15', '2020-07-13 10:37:15', '0', 'venus-admin');
INSERT INTO `base_action` VALUES ('1282504798935044097', 'systemRoleAddUserEdit', '系统角色添加用户', '', '1273160837359575042', '1', '1', '2020-07-13 10:39:07', '2020-07-13 10:39:07', '0', 'venus-admin');
INSERT INTO `base_action` VALUES ('1282504874709340161', 'systemRoleDel', '系统角色删除', '', '1273160837359575042', '1', '1', '2020-07-13 10:39:25', '2020-07-13 10:39:25', '0', 'venus-admin');

-- ----------------------------
-- Table structure for base_authority
-- ----------------------------
DROP TABLE IF EXISTS `base_authority`;
CREATE TABLE `base_authority` (
  `authority_id` bigint(20) NOT NULL,
  `authority` varchar(255) NOT NULL COMMENT '权限标识',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单资源ID',
  `api_id` bigint(20) DEFAULT NULL COMMENT 'API资源ID',
  `action_id` bigint(20) DEFAULT NULL,
  `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`authority_id`),
  KEY `menu_id` (`menu_id`) USING BTREE,
  KEY `api_id` (`api_id`) USING BTREE,
  KEY `action_id` (`action_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限-菜单权限、操作权限、API权限';

-- ----------------------------
-- Records of base_authority
-- ----------------------------
INSERT INTO `base_authority` VALUES ('1', 'MENU_system', '1', null, null, '1', '2020-05-06 15:43:15', '2020-05-06 15:43:15');
INSERT INTO `base_authority` VALUES ('4', 'MENU_systemMenus', '4', null, null, '1', '2020-05-13 09:58:50', '2020-05-13 09:58:53');
INSERT INTO `base_authority` VALUES ('1268066315210436610', 'MENU_systemUsers', '1268066315134939137', null, null, '1', '2020-06-03 14:25:44', '2020-06-03 14:25:44');
INSERT INTO `base_authority` VALUES ('1269833096442748930', 'MENU_systemActions', '1269833096379834369', null, null, '0', '2020-06-08 11:26:17', '2020-06-08 11:26:17');
INSERT INTO `base_authority` VALUES ('1272810046551523329', 'ACTION_systemMenuEdit', null, null, '1272810046417305601', '1', '2020-06-16 16:35:37', '2020-06-17 15:21:03');
INSERT INTO `base_authority` VALUES ('1272810133784657922', 'ACTION_systemMenuAdd', null, null, '1272810133713354754', '1', '2020-06-16 16:35:58', '2020-06-16 16:35:58');
INSERT INTO `base_authority` VALUES ('1273155797223841793', 'ACTION_systemMenuDel', null, null, '1273155797177704449', '1', '2020-06-17 15:29:31', '2020-06-17 15:36:31');
INSERT INTO `base_authority` VALUES ('1273157652733599746', 'ACTION_systemMenuAction', null, null, '1273157652704239618', '1', '2020-06-17 15:36:53', '2020-06-17 15:36:53');
INSERT INTO `base_authority` VALUES ('1273160837422489601', 'MENU_systemRoles', '1273160837359575042', null, null, '1', '2020-06-17 15:49:32', '2020-06-17 15:49:32');
INSERT INTO `base_authority` VALUES ('1282500972819632129', 'ACTION_systemUserEdit', null, null, '1282500972198875137', '1', '2020-07-13 10:23:54', '2020-07-13 10:23:54');
INSERT INTO `base_authority` VALUES ('1282501159252250625', 'ACTION_systemUserGrantRolesEdit', null, null, '1282501158883151873', '1', '2020-07-13 10:24:39', '2020-07-13 10:24:39');
INSERT INTO `base_authority` VALUES ('1282501351162630145', 'ACTION_systemUserGrantMenusEdit', null, null, '1282501351061966849', '1', '2020-07-13 10:25:24', '2020-07-13 10:25:24');
INSERT INTO `base_authority` VALUES ('1282501522663526402', 'ACTION_systemUserPasswordEdit', null, null, '1282501522579640321', '1', '2020-07-13 10:26:05', '2020-07-13 10:26:05');
INSERT INTO `base_authority` VALUES ('1282501925480288258', 'ACTION_systemUserAdd', null, null, '1282501925429956609', '1', '2020-07-13 10:27:41', '2020-07-13 10:27:41');
INSERT INTO `base_authority` VALUES ('1282503660089233409', 'ACTION_systemUserSearch', null, null, '1282503659892101121', '1', '2020-07-13 10:34:35', '2020-07-13 10:34:35');
INSERT INTO `base_authority` VALUES ('1282503942873403393', 'ACTION_systemRoleSearch', null, null, '1282503942818877442', '1', '2020-07-13 10:35:42', '2020-07-13 10:35:42');
INSERT INTO `base_authority` VALUES ('1282504071491735553', 'ACTION_systemRoleAdd', null, null, '1282504071349129218', '1', '2020-07-13 10:36:13', '2020-07-13 10:36:13');
INSERT INTO `base_authority` VALUES ('1282504157487550466', 'ACTION_systemRoleEdit', null, null, '1282504157319778305', '1', '2020-07-13 10:36:34', '2020-07-13 10:36:34');
INSERT INTO `base_authority` VALUES ('1282504329525317634', 'ACTION_systemRoleGrantMenusEdit', null, null, '1282504329470791681', '1', '2020-07-13 10:37:15', '2020-07-13 10:37:15');
INSERT INTO `base_authority` VALUES ('1282504798956015617', 'ACTION_systemRoleAddUserEdit', null, null, '1282504798935044097', '1', '2020-07-13 10:39:07', '2020-07-13 10:39:07');
INSERT INTO `base_authority` VALUES ('1282504874893889538', 'ACTION_systemRoleDel', null, null, '1282504874709340161', '1', '2020-07-13 10:39:25', '2020-07-13 10:39:25');

-- ----------------------------
-- Table structure for base_authority_action
-- ----------------------------
DROP TABLE IF EXISTS `base_authority_action`;
CREATE TABLE `base_authority_action` (
  `action_id` bigint(20) NOT NULL COMMENT '操作ID',
  `authority_id` bigint(20) NOT NULL COMMENT 'API',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  KEY `action_id` (`action_id`) USING BTREE,
  KEY `authority_id` (`authority_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限-功能操作关联表';

-- ----------------------------
-- Records of base_authority_action
-- ----------------------------

-- ----------------------------
-- Table structure for base_authority_app
-- ----------------------------
DROP TABLE IF EXISTS `base_authority_app`;
CREATE TABLE `base_authority_app` (
  `authority_id` bigint(50) NOT NULL COMMENT '权限ID',
  `app_id` varchar(100) NOT NULL COMMENT '应用ID',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间:null表示长期',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  KEY `authority_id` (`authority_id`) USING BTREE,
  KEY `app_id` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限-应用关联';

-- ----------------------------
-- Records of base_authority_app
-- ----------------------------

-- ----------------------------
-- Table structure for base_authority_role
-- ----------------------------
DROP TABLE IF EXISTS `base_authority_role`;
CREATE TABLE `base_authority_role` (
  `authority_id` bigint(20) NOT NULL COMMENT '权限ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间:null表示长期',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  KEY `authority_id` (`authority_id`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限-角色关联';

-- ----------------------------
-- Records of base_authority_role
-- ----------------------------
INSERT INTO `base_authority_role` VALUES ('1', '1', null, '2020-05-11 10:35:06', '2020-05-11 10:35:09');
INSERT INTO `base_authority_role` VALUES ('1', '2', null, '2020-05-11 10:36:35', '2020-05-11 10:36:38');
INSERT INTO `base_authority_role` VALUES ('4', '1', null, '2020-05-13 09:59:18', '2020-05-13 09:59:20');
INSERT INTO `base_authority_role` VALUES ('1', '3', null, null, null);
INSERT INTO `base_authority_role` VALUES ('1268066315210436610', '3', null, null, null);
INSERT INTO `base_authority_role` VALUES ('1273160837422489601', '3', null, null, null);
INSERT INTO `base_authority_role` VALUES ('4', '3', null, null, null);
INSERT INTO `base_authority_role` VALUES ('1272810046551523329', '3', null, null, null);
INSERT INTO `base_authority_role` VALUES ('1273155797223841793', '3', null, null, null);
INSERT INTO `base_authority_role` VALUES ('1273157652733599746', '3', null, null, null);
INSERT INTO `base_authority_role` VALUES ('1', '1282500333209243650', null, null, null);
INSERT INTO `base_authority_role` VALUES ('1268066315210436610', '1282500333209243650', null, null, null);
INSERT INTO `base_authority_role` VALUES ('1273160837422489601', '1282500333209243650', null, null, null);
INSERT INTO `base_authority_role` VALUES ('4', '1282500333209243650', null, null, null);
INSERT INTO `base_authority_role` VALUES ('1272810046551523329', '1282500333209243650', null, null, null);
INSERT INTO `base_authority_role` VALUES ('1282500972819632129', '1282500333209243650', null, null, null);
INSERT INTO `base_authority_role` VALUES ('1282504157487550466', '1282500333209243650', null, null, null);
INSERT INTO `base_authority_role` VALUES ('1', '1282524142398173186', null, null, null);
INSERT INTO `base_authority_role` VALUES ('1273160837422489601', '1282524142398173186', null, null, null);

-- ----------------------------
-- Table structure for base_authority_user
-- ----------------------------
DROP TABLE IF EXISTS `base_authority_user`;
CREATE TABLE `base_authority_user` (
  `authority_id` bigint(20) NOT NULL COMMENT '权限ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  KEY `authority_id` (`authority_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限-用户关联';

-- ----------------------------
-- Records of base_authority_user
-- ----------------------------

-- ----------------------------
-- Table structure for base_menu
-- ----------------------------
DROP TABLE IF EXISTS `base_menu`;
CREATE TABLE `base_menu` (
  `menu_id` bigint(20) NOT NULL COMMENT '菜单Id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级菜单',
  `menu_code` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '菜单编码',
  `menu_name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '菜单名称',
  `menu_desc` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `scheme` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '路径前缀',
  `path` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '请求路径',
  `icon` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '菜单标题',
  `target` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '_self' COMMENT '打开方式:_self窗口内,_blank新窗口',
  `priority` bigint(20) NOT NULL DEFAULT '0' COMMENT '优先级 越小越靠前',
  `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '状态:0-无效 1-有效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_persist` tinyint(3) NOT NULL DEFAULT '0' COMMENT '保留数据0-否 1-是 不允许删除',
  `service_id` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '服务名',
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `menu_code` (`menu_code`) USING BTREE,
  KEY `service_id` (`service_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统资源-菜单信息';

-- ----------------------------
-- Records of base_menu
-- ----------------------------
INSERT INTO `base_menu` VALUES ('1', '0', 'system', '系统管理', '系统管理', '/', 'system/admin', 'clipboard', '_self', '0', '1', '2020-05-06 15:11:51', '2020-05-06 15:11:56', '1', 'venus-admin');
INSERT INTO `base_menu` VALUES ('4', '1', 'systemMenus', '菜单资源', '菜单资源管理', '/', 'system/menus/index', 'tree-table', '_self', '3', '1', '2020-05-13 09:57:16', '2020-05-13 09:57:19', '1', 'venus-admin');
INSERT INTO `base_menu` VALUES ('1268066315134939137', '1', 'systemUsers', '系统用户', null, '/', 'system/user/index', 'people', '_self', '1', '1', '2020-06-03 14:25:44', '2020-06-03 14:25:44', '0', 'venus-admin');
INSERT INTO `base_menu` VALUES ('1269833096379834369', '1', 'systemActions', '功能管理', null, '/', 'system/menus/action', 'tree-table', '_self', '1', '0', '2020-06-08 11:26:17', '2020-06-08 11:26:17', '0', 'venus-admin');
INSERT INTO `base_menu` VALUES ('1273160837359575042', '1', 'systemRoles', '角色信息', null, '/', 'system/role/index', 'peoples', '_self', '2', '1', '2020-06-17 15:49:32', '2020-06-17 15:49:32', '0', 'venus-admin');

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `role_code` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '角色编码',
  `role_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '状态:0-无效 1-有效',
  `role_desc` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_persist` tinyint(3) NOT NULL DEFAULT '0' COMMENT '保留数据0-否 1-是 不允许删除',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_code` (`role_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统角色-基础信息';

-- ----------------------------
-- Records of base_role
-- ----------------------------
INSERT INTO `base_role` VALUES ('1', 'admin', '系统管理员', '1', '系统管理员', '2018-07-29 21:14:54', '2019-05-25 03:06:57', '1');
INSERT INTO `base_role` VALUES ('1282500333209243650', 'edit', '编辑', '1', '功能编辑', '2020-07-13 10:21:22', '2020-07-13 10:44:21', '0');
INSERT INTO `base_role` VALUES ('1282524142398173186', 'test', '测试', '1', '', '2020-07-13 11:55:58', '2020-07-13 11:55:58', '0');

-- ----------------------------
-- Table structure for base_role_user
-- ----------------------------
DROP TABLE IF EXISTS `base_role_user`;
CREATE TABLE `base_role_user` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  KEY `fk_user` (`user_id`) USING BTREE,
  KEY `fk_role` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统角色-用户关联';

-- ----------------------------
-- Records of base_role_user
-- ----------------------------
INSERT INTO `base_role_user` VALUES ('1280430437201641473', '2', null, null);
INSERT INTO `base_role_user` VALUES ('1282506304530796545', '1282500333209243650', null, null);

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(255) DEFAULT NULL COMMENT '登陆账号',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT '' COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机号',
  `user_type` varchar(20) DEFAULT 'normal' COMMENT '用户类型:super-超级管理员 normal-普通管理员',
  `company_id` bigint(20) DEFAULT NULL COMMENT '企业ID',
  `user_desc` varchar(255) DEFAULT '' COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(3) DEFAULT '1' COMMENT '状态:0-禁用 1-正常 2-锁定',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户-管理员信息';

-- ----------------------------
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES ('521677655146233856', 'admin', '超级管理员', '', '515608851@qq.com', '', 'super', null, '', '2018-12-10 13:20:45', null, '1');
INSERT INTO `base_user` VALUES ('1282506304530796545', 'huashaoge', '花哨哥', '', '546103589@qq.com', '18580766904', 'platform', null, '', '2020-07-13 10:45:05', '2020-07-13 10:45:05', '1');

-- ----------------------------
-- Table structure for clientdetails
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails` (
  `appId` varchar(128) NOT NULL,
  `resourceIds` varchar(128) DEFAULT NULL,
  `appSecret` varchar(128) DEFAULT NULL,
  `scope` varchar(128) DEFAULT NULL,
  `grantTypes` varchar(128) DEFAULT NULL,
  `redirectUrl` varchar(128) DEFAULT NULL,
  `authorities` varchar(128) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of clientdetails
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(128) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(128) DEFAULT NULL,
  `client_id` varchar(128) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(128) DEFAULT NULL,
  `clientId` varchar(128) DEFAULT NULL,
  `scope` varchar(128) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp(6) NULL DEFAULT NULL,
  `lastModifiedAt` timestamp(6) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(128) DEFAULT NULL,
  `client_secret` varchar(128) DEFAULT NULL,
  `scope` varchar(128) DEFAULT NULL,
  `authorized_grant_types` varchar(128) DEFAULT NULL,
  `web_server_redirect_uri` varchar(128) DEFAULT NULL,
  `authorities` varchar(128) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('admin', null, '$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq', 'read,write', 'authorization_code,password,refresh_token,client_credentials', null, null, '43200', '2592000', null, null);
INSERT INTO `oauth_client_details` VALUES ('app', null, 'app', 'app', 'password,refresh_token', null, null, '43200', '2592000', null, null);

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(128) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(128) DEFAULT NULL,
  `client_id` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(128) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(128) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('18580766904', 'U6pwNkwqMQvtL4AbicMxJA==', 'ee8206LgQ79QnRMd4VGMMQ==', '2019-06-20 17:08:36');
INSERT INTO `persistent_logins` VALUES ('tcg', 'U6pwNkwqMQvtL4AbicMxJQ==', 'ee8206LgQ79QnRMd4VGSIQ==', '2019-06-20 17:08:36');

-- ----------------------------
-- Table structure for venus_userconnection
-- ----------------------------
DROP TABLE IF EXISTS `venus_userconnection`;
CREATE TABLE `venus_userconnection` (
  `userId` varchar(255) NOT NULL,
  `providerId` varchar(255) NOT NULL,
  `providerUserId` varchar(255) NOT NULL,
  `rank` int(11) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `profileUrl` varchar(512) DEFAULT NULL,
  `imageUrl` varchar(512) DEFAULT NULL,
  `accessToken` varchar(512) NOT NULL,
  `secret` varchar(512) DEFAULT NULL,
  `refreshToken` varchar(512) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `UserConnectionRank` (`userId`,`providerId`,`rank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of venus_userconnection
-- ----------------------------
