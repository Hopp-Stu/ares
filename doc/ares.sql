/*
 Navicat Premium Data Transfer

 Source Server         : springbootV1
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 127.0.0.1:3306
 Source Schema         : springbootv1

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 23/09/2020 14:54:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `Id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DeptName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ParentDeptId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CreateTime` datetime(0) NULL DEFAULT NULL,
  `Modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ModifyTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '00000000', '总部', '0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('476951546261803008', '00000011', '财务部', '1', '1', '2020-10-16 05:22:00', NULL, NULL);
INSERT INTO `sys_dept` VALUES ('476955797205684224', '00000001', '行政部', '1', '1', '2020-10-16 05:38:54', NULL, NULL);
INSERT INTO `sys_dept` VALUES ('476955894287044608', '00000002', '管理层', '1', '1', '2020-10-16 05:39:17', NULL, NULL);
INSERT INTO `sys_dept` VALUES ('476960080894496768', '11111', '部门1', '1', '1', '2020-10-16 05:55:55', NULL, NULL);
INSERT INTO `sys_dept` VALUES ('476960119641477120', '11112', '部门2', '476960080894496768', '1', '2020-10-16 05:56:04', NULL, NULL);
INSERT INTO `sys_dept` VALUES ('476960168698056704', '11113', '部门3', '476960119641477120', '1', '2020-10-16 05:56:16', NULL, NULL);
INSERT INTO `sys_dept` VALUES ('476960216085303296', '11114', '部门4', '476960080894496768', '1', '2020-10-16 05:56:27', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `Id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编码',
  `DictSort` int NULL DEFAULT 0 COMMENT '字典排序',
  `DictLabel` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `DictValue` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `DictType` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `Status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `Creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `CreateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `Modifier` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `ModifyTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `Remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES ('468626357786644480', 1, '正常', '0', 'sys_normal_status', '0', NULL, '2020-09-23 06:00:41', NULL, '2020-09-23 06:04:59', NULL);
INSERT INTO `sys_dict_data` VALUES ('468626403605221376', 2, '停用', '1', 'sys_normal_status', '0', NULL, '2020-09-23 06:00:52', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES ('468629725150056448', 1, '默认组', 'DEFAULT', 'sys_job_group', '0', NULL, '2020-09-23 06:14:03', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES ('468629765184688128', 2, '系统组', 'SYSTEM', 'sys_job_group', '0', NULL, '2020-09-23 06:14:13', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES ('468630476949688320', 1, '通知', '1', 'sys_notice_type', '0', NULL, '2020-09-23 06:17:03', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES ('468630500794306560', 2, '公告', '2', 'sys_notice_type', '0', NULL, '2020-09-23 06:17:08', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES ('468630734467371008', 1, '正常', '0', 'sys_notice_status', '0', NULL, '2020-09-23 06:18:04', NULL, '2020-09-23 06:20:17', NULL);
INSERT INTO `sys_dict_data` VALUES ('468630768239906816', 2, '关闭', '1', 'sys_notice_status', '0', NULL, '2020-09-23 06:18:12', NULL, '2020-09-23 06:20:20', NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `Id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典主键',
  `DictName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `DictType` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `Status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `Creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `CreateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `Modifier` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `ModifyTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `Remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  UNIQUE INDEX `DictType`(`DictType`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('468620096143757312', '状态', 'sys_normal_status', '0', NULL, '2020-09-23 05:35:48', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES ('468629672448626688', '任务组', 'sys_job_group', '0', NULL, '2020-09-23 06:13:51', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES ('468630402366574592', '公告分类', 'sys_notice_type', '0', NULL, '2020-09-23 06:16:45', NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES ('468630689772867584', '公告状态', 'sys_notice_status', '0', NULL, '2020-09-23 06:17:53', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `Id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `HostIP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UserName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `Notes` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CreateTime` datetime(0) NULL DEFAULT NULL,
  `ModifyTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `Id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `Name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `Description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `Path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `Url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权链接',
  `IsBlank` int NULL DEFAULT 0 COMMENT '是否跳转 0 不跳转 1跳转',
  `PId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父节点id',
  `Icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `Order` int NULL DEFAULT NULL COMMENT '排序',
  `Visible` int NULL DEFAULT NULL COMMENT '是否可见',
  `Perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Type` int NULL DEFAULT NULL,
  `Creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CreateTime` datetime(0) NULL DEFAULT NULL,
  `Modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ModifyTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '系统管理', 'system', '#', 0, '0', 'system', 10, 0, 'sys', 0, NULL, '2020-01-23 18:17:06', '1', '2020-07-17 06:37:34');
INSERT INTO `sys_menu` VALUES ('2', '菜单管理', '菜单管理', 'menu', '/system/menu/index', 0, '1', 'list', 1, 0, 'menu:list', 1, NULL, '2020-01-23 18:17:39', NULL, '2020-05-07 07:25:33');
INSERT INTO `sys_menu` VALUES ('3', '组织用户', '用户管理', 'user', '/system/user/index', 0, '1', 'user', 2, 0, 'user:list', 1, NULL, '2020-01-24 15:27:25', '1', '2020-10-16 05:55:32');
INSERT INTO `sys_menu` VALUES ('381295505541566464', '菜单新增/修改', '新增/修改', NULL, '#', NULL, '2', '', 1, 1, 'menu:edit', 2, NULL, '2020-01-26 06:19:03', NULL, '2020-01-29 05:41:40');
INSERT INTO `sys_menu` VALUES ('381296514569474048', '定时任务', '定时任务', 'job', '/monitor/job/index', NULL, '1', 'job', 4, 0, 'quartz:list', 1, NULL, '2020-01-26 06:23:03', NULL, '2020-05-07 07:26:10');
INSERT INTO `sys_menu` VALUES ('382382361008017408', '任务新增/修改', '新增/修改', NULL, '', NULL, '381296514569474048', '', 1, 1, 'quartz:edit', 2, NULL, '2020-01-29 06:17:49', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('382736944293089280', '任务日志', '任务日志', 'log', '/monitor/job/log', NULL, '1', 'log', 5, 0, 'quartz:logList', 1, NULL, '2020-01-30 05:46:49', NULL, '2020-05-07 07:26:22');
INSERT INTO `sys_menu` VALUES ('383822600511557632', '菜单删除', '删除', NULL, '#', NULL, '2', '', 2, 1, 'menu:delete', 2, NULL, '2020-02-02 05:40:49', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('383822867416092672', '用户新增/修改', '新增/修改', NULL, '#', NULL, '3', '', 1, 1, 'user:edit', 2, NULL, '2020-02-02 05:41:53', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('383822981555687424', '用户删除', '删除', NULL, '#', NULL, '3', '', 2, 1, 'user:delete', 2, NULL, '2020-02-02 05:42:20', NULL, '2020-02-02 05:42:27');
INSERT INTO `sys_menu` VALUES ('383823166725820416', '角色新增/修改', '新增/修改', NULL, '#', NULL, '4', '', 1, 1, 'role:edit', 2, NULL, '2020-02-02 05:43:04', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('383823257045962752', '角色删除', '删除', NULL, '#', NULL, '4', '', 2, 1, 'role:delete', 2, NULL, '2020-02-02 05:43:26', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('383823416903471104', '任务删除', '删除', NULL, '#', NULL, '381296514569474048', '', 2, 1, 'quartz:delete', 2, NULL, '2020-02-02 05:44:04', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('383838051287306240', '通用工具', '通用工具', 'tool', '#', NULL, '0', 'tool', 2, 0, '#', 1, NULL, '2020-02-02 06:42:13', NULL, '2020-05-07 07:26:48');
INSERT INTO `sys_menu` VALUES ('4', '角色管理', '角色管理', 'role', '/system/role/index', 0, '1', 'peoples', 3, 0, 'role:list', 1, NULL, '2020-01-24 15:27:27', NULL, '2020-05-07 07:26:40');
INSERT INTO `sys_menu` VALUES ('405938773428408320', '服务器信息类', '服务器信息类', 'service', '/monitor/server/index', NULL, '383838051287306240', 'server', 3, 0, '#', 1, NULL, '2020-04-03 06:22:36', NULL, '2020-05-07 07:27:08');
INSERT INTO `sys_menu` VALUES ('415363931222183936', '数据库监控', '数据库监控', 'druid', '/monitor/druid/index', NULL, '383838051287306240', 'druid', 5, 0, '#', 1, NULL, '2020-04-29 06:34:49', NULL, '2020-05-07 07:27:00');
INSERT INTO `sys_menu` VALUES ('418561360864088064', '删除', NULL, NULL, '#', NULL, '382736944293089280', NULL, 1, 0, 'quartz:logDelete', 2, NULL, '2020-05-08 02:20:15', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('421142300119404544', '系统接口', NULL, 'swagger', '/tool/swagger/index', NULL, '383838051287306240', 'swagger', 1, 0, '#', 1, '1', '2020-05-15 05:15:59', '1', '2020-09-23 06:50:19');
INSERT INTO `sys_menu` VALUES ('434952738292502528', '系统模版', NULL, 'template', '/system/template/index', NULL, '1', 'tab', 7, 0, 'sysTemplate:list', 1, '1', '2020-06-22 07:53:44', '1', '2020-09-23 06:48:56');
INSERT INTO `sys_menu` VALUES ('434953623361949696', '编辑', NULL, NULL, '#', NULL, '434952738292502528', NULL, 1, 0, 'sysTemplate:edit', 2, '1', '2020-06-22 07:57:15', '1', '2020-06-22 07:57:25');
INSERT INTO `sys_menu` VALUES ('434953738826944512', '删除', NULL, NULL, '#', NULL, '434952738292502528', NULL, 2, 0, 'sysTemplate:delete', 2, '1', '2020-06-22 07:57:43', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('443992667446185984', '知识图谱', NULL, 'topo', '#', NULL, '0', 'chart', 1, 0, '#', 1, '1', '2020-07-17 06:35:11', '1', '2020-07-17 06:37:47');
INSERT INTO `sys_menu` VALUES ('443992964990111744', 'neo4j', NULL, 'neo4j', '/topo/neo4j/index', NULL, '443992667446185984', 'example', 1, 0, '#', 1, '1', '2020-07-17 06:36:22', '1', '2020-07-17 06:37:01');
INSERT INTO `sys_menu` VALUES ('458794662770118656', '流程管理', NULL, 'workflow', '#', NULL, '0', 'table', 3, 0, '#', 1, '1', '2020-08-27 02:53:02', '1', '2020-08-31 08:30:46');
INSERT INTO `sys_menu` VALUES ('458794847856365568', '流程设计', NULL, 'editor', '/workflow/editor/index', NULL, '458794662770118656', 'component', 1, 0, '#', 1, '1', '2020-08-27 02:53:46', '1', '2020-08-27 02:54:30');
INSERT INTO `sys_menu` VALUES ('460324854965604352', 'Icons图标', NULL, 'components', '/components/icons/index', NULL, '383838051287306240', 'icon', 7, 0, '#', 1, '1', '2020-08-31 08:13:28', '1', '2020-09-17 05:48:49');
INSERT INTO `sys_menu` VALUES ('465671284915834880', 'MQ消息配置', NULL, 'mq', '/monitor/mq/index', NULL, '383838051287306240', 'message', 8, 0, '#', 1, '1', '2020-09-15 02:18:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('466826948786655232', '公告管理', NULL, 'notice', '/system/notice/index', NULL, '1', 'message', 11, 0, 'notice:list', 1, '1', '2020-09-18 06:50:28', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('466827074053738496', '新增修改', NULL, NULL, '#', NULL, '466826948786655232', NULL, 1, 0, 'notice:edit', 2, '1', '2020-09-18 06:50:58', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('466827163627294720', '删除', NULL, NULL, '#', NULL, '466826948786655232', NULL, 2, 0, 'notice:delete', 2, '1', '2020-09-18 06:51:19', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('468619571516018688', '字典管理', NULL, 'dict', '/system/dict/index', NULL, '1', 'dict', 12, 0, 'sysDictType:list', 1, '1', '2020-09-23 05:33:43', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('468619688822312960', '编辑', NULL, NULL, '#', NULL, '468619571516018688', NULL, 1, 0, 'sysDictType:edit', 2, '1', '2020-09-23 05:34:11', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('468619757386600448', '删除', NULL, NULL, '#', NULL, '468619571516018688', NULL, 2, 0, 'sysDictType:delete', 2, '1', '2020-09-23 05:34:27', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('468626126055542784', '字典数据编辑', NULL, NULL, '#', NULL, '468619571516018688', NULL, 4, 0, 'sysDictData:edit', 2, '1', '2020-09-23 05:59:45', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('468626200407969792', '字典数据删除', NULL, NULL, '#', NULL, '468619571516018688', NULL, 5, 0, 'sysDictData:delete', 2, '1', '2020-09-23 06:00:03', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('469358670350782464', '字典数据', NULL, NULL, '#', NULL, '468619571516018688', NULL, 3, 0, 'sysDictData:list', 2, '1', '2020-09-25 06:30:38', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('476964764061405184', '岗位管理', NULL, 'post', '/system/post/index', NULL, '1', 'post', 8, 0, 'sysPost:list', 1, '1', '2020-10-16 06:14:32', '1', '2020-10-16 06:14:42');
INSERT INTO `sys_menu` VALUES ('476965210788335616', '新增/修改', NULL, NULL, '#', NULL, '476964764061405184', NULL, 1, 0, 'sysPost:edit', 2, '1', '2020-10-16 06:16:18', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('476965267143004160', '删除', NULL, NULL, '#', NULL, '476964764061405184', NULL, 2, 0, 'sysPost:delete', 2, '1', '2020-10-16 06:16:31', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('5', '代码生成', '代码生成', 'gen', '/tool/gen/index', 0, '383838051287306240', 'build', 4, 0, '#', 1, NULL, '2020-04-28 14:28:03', NULL, '2020-05-13 07:33:05');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `Id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告ID',
  `NoticeTitle` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `NoticeType` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `NoticeContent` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `NoticeStatus` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `Deadline` datetime(0) NULL DEFAULT NULL COMMENT '有效时间',
  `Creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `CreateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `Modifier` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `ModifyTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `Remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('466828250807668736', '通知', '1', '<p>通知123123123</p>', '0', '2020-09-30 15:00:00', '1', '2020-09-18 06:55:38', '1', '2020-09-25 06:17:29', NULL);
INSERT INTO `sys_notice` VALUES ('466842367455006720', 'ddddd', '1', '<p>dqwdqwqdq</p><p>dqwqwd</p><p>dqwdqd</p><p>dqwdq</p>', '0', '2020-09-23 09:51:33', '1', '2020-09-18 07:51:44', '1', '2020-09-18 07:53:35', NULL);
INSERT INTO `sys_notice` VALUES ('466842457729011712', 'dqwdqqwd', '2', '<p>dqwdqwdqwdqwqwdqwdqwffewfwefwefwefwefwefwefewfwedwedwdwedwewdwedwedwedwedwedwefwef</p>', '0', '2020-09-23 12:01:00', '1', '2020-09-18 07:52:06', '1', '2020-09-18 08:26:18', NULL);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `Id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `RoleId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MenuId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('417905015328477184', '381303138021412864', '381296514569474048');
INSERT INTO `sys_permission` VALUES ('417905015336865792', '381303138021412864', '382382361008017408');
INSERT INTO `sys_permission` VALUES ('417905015341060096', '381303138021412864', '383823416903471104');
INSERT INTO `sys_permission` VALUES ('417905015345254400', '381303138021412864', '382736944293089280');
INSERT INTO `sys_permission` VALUES ('417905015349448704', '381303138021412864', '382743905826902016');
INSERT INTO `sys_permission` VALUES ('417905015357837312', '381303138021412864', '382744141844582400');
INSERT INTO `sys_permission` VALUES ('417905015370420224', '381303138021412864', '1');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `Id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PostCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PostName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CreateTime` datetime(0) NULL DEFAULT NULL,
  `Modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ModifyTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('476965363788156928', '0000', '员工', '1', '2020-10-16 06:16:55', NULL, NULL);
INSERT INTO `sys_post` VALUES ('476965408843370496', '0001', '经理', '1', '2020-10-16 06:17:05', NULL, NULL);

-- ----------------------------
-- Table structure for sys_properties
-- ----------------------------
DROP TABLE IF EXISTS `sys_properties`;
CREATE TABLE `sys_properties`  (
  `Id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CreateTime` datetime(0) NULL DEFAULT NULL,
  `Modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ModifyTime` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_properties
-- ----------------------------
INSERT INTO `sys_properties` VALUES ('1', '邮件协议', 'mail.protocol', 'smtp', 'mail', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_properties` VALUES ('2', '邮件发送地址', 'mail.host', 'smtp.qq.com', 'mail', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_properties` VALUES ('3', '邮箱用户名', 'mail.username', '2862322640@qq.com', 'mail', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_properties` VALUES ('4', '邮箱密码', 'mail.password', 'ffycbapqhjgjdfdj', 'mail', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_properties` VALUES ('5', '邮件验证', 'mail.auth', 'true', 'mail', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_properties` VALUES ('6', '邮箱端口', 'mail.port', '587', 'mail', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_properties` VALUES ('7', '上传路径', 'upload.path', 'E:\\GitHub\\ares\\upload', 'file', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_properties` VALUES ('8', '文件类型', 'file.allow', 'bmp,gif,jpg,jpeg,png,doc,docx,xls,xlsx,ppt,pptx,html,htm,txt,rar,zip,gz,bz2,pdf', 'file', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_properties` VALUES ('9', '文件大小', 'file.maxsize', '5242880000', 'file', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_properties` VALUES ('10', '文件名长度', 'file.namelength', '100', 'file', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_properties` VALUES ('11', '头像路径', 'avatar.path', 'E:\\GitHub\\ares\\upload\\avatar', 'avatar', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job`  (
  `Id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志id',
  `JobName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `JobGroup` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务组名',
  `Description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `InvokeTarget` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调用目标字符串',
  `CronExpression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron执行表达式',
  `ConCurrent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否并发执行（0允许 1禁止）',
  `Status` int NULL DEFAULT NULL COMMENT '任务状态（0正常 1暂停）',
  `Creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CreateTime` datetime(0) NULL DEFAULT NULL,
  `Modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ModifyTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
INSERT INTO `sys_quartz_job` VALUES ('449421892173041664', 'test1', 'DEFAULT', NULL, 'com.ares.quartz.jobs.TestJob.test()', '* 0/1 * * * ? *', '1', 1, '1', '2020-08-01 06:08:59', '1', NULL);
INSERT INTO `sys_quartz_job` VALUES ('468634278062854144', 'testHasParam', 'DEFAULT', 'testHasParam', 'com.ares.quartz.jobs.TestJob.testHasParam(\'job\',\'run\')', '0 0/1 * * * ?', '1', 1, '1', '2020-09-23 06:32:09', '1', '2020-09-23 06:34:35');

-- ----------------------------
-- Table structure for sys_quartz_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job_log`;
CREATE TABLE `sys_quartz_job_log`  (
  `Id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `JobName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `JobGroup` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务组名',
  `InvokeTarget` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调用目标字符串',
  `JobMessage` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '日志信息',
  `Status` int NULL DEFAULT NULL COMMENT '执行状态（0正常 1失败）',
  `ExceptionInfo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常信息',
  `StartTime` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `FinishTime` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_quartz_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `Id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `RoleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CreateTime` datetime(0) NULL DEFAULT NULL,
  `Modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ModifyTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'gly', '超级管理员', NULL, '2020-01-25 09:09:51', NULL, '2020-01-26 06:49:34');
INSERT INTO `sys_role` VALUES ('381303138021412864', 'user', '用户', NULL, '2020-01-26 06:49:23', NULL, '2020-05-06 06:52:10');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `Id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `UserId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `RoleId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('418574260357959680', '1', '1');
INSERT INTO `sys_role_user` VALUES ('476968734930636800', '380942348554735616', '381303138021412864');
INSERT INTO `sys_role_user` VALUES ('476968822222491648', '383826362324094976', '381303138021412864');

-- ----------------------------
-- Table structure for sys_template
-- ----------------------------
DROP TABLE IF EXISTS `sys_template`;
CREATE TABLE `sys_template`  (
  `Id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `Html` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `Params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CreateTime` datetime(0) NULL DEFAULT NULL,
  `Modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ModifyTime` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_template
-- ----------------------------
INSERT INTO `sys_template` VALUES ('1', 'test', '测试', '测试', '\r\n<div>\r\n    <h1>${name}</h1>\r\n</div>', 'name', '1', '2020-06-19 14:31:29', '1', '2020-06-22 08:09:10');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `Id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UserName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PhoneNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DeptId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PostId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CreateTime` datetime(0) NULL DEFAULT NULL,
  `Modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ModifyTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '管理员', '21232f297a57a5a743894a0e4a801fc3', '11111111', '11111111', 'RTpcR2l0SHViXGFyZXNcdXBsb2FkXGF2YXRhclwyMDIwLzEwLzEzXGJsb2IuanBlZw==', NULL, NULL, NULL, '2020-01-23 08:51:42', NULL, '2020-10-13 02:02:21');
INSERT INTO `sys_user` VALUES ('380942348554735616', 'user', '用户1', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, '476951546261803008', '476965408843370496', NULL, '2020-01-25 06:55:44', '1', '2020-10-16 06:30:18');
INSERT INTO `sys_user` VALUES ('383826362324094976', 'tttt', '测试', 'e10adc3949ba59abbe56e057f20f883e', NULL, 'eee', NULL, '476955797205684224', '476965363788156928', NULL, '2020-02-02 05:55:46', '1', '2020-10-16 06:30:39');

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for sys_notice_read
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice_read`;
CREATE TABLE `sys_notice_read`  (
  `Id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NoticeId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UserId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CreateTime` datetime(0) NULL DEFAULT NULL,
  `Modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ModifyTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
