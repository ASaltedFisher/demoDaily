/*
 Navicat MySQL Data Transfer

 Source Server         : 194
 Source Server Type    : MySQL
 Source Server Version : 50635
 Source Host           : 180.3.13.194:3306
 Source Schema         : dailyreport

 Target Server Type    : MySQL
 Target Server Version : 50635
 File Encoding         : 65001

 Date: 09/08/2019 16:35:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dr_daily
-- ----------------------------
DROP TABLE IF EXISTS `t_dr_daily`;
CREATE TABLE `t_dr_daily`  (
  `f_did` int(11) NOT NULL AUTO_INCREMENT,
  `f_uid` int(11) NOT NULL,
  `f_pid` int(11) NOT NULL,
  `f_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_date` date NOT NULL,
  `f_man_hours` int(11) NOT NULL,
  `f_flag` int(1) NOT NULL,
  PRIMARY KEY (`f_did`) USING BTREE,
  INDEX `f_uid`(`f_uid`) USING BTREE,
  INDEX `f_pid`(`f_pid`) USING BTREE,
  CONSTRAINT `t_dr_daily_ibfk_1` FOREIGN KEY (`f_uid`) REFERENCES `t_dr_user` (`f_uid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_dr_daily_ibfk_2` FOREIGN KEY (`f_pid`) REFERENCES `t_dr_project` (`f_pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_dr_project
-- ----------------------------
DROP TABLE IF EXISTS `t_dr_project`;
CREATE TABLE `t_dr_project`  (
  `f_pid` int(11) NOT NULL AUTO_INCREMENT,
  `f_projectname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_validity` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`f_pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_dr_role
-- ----------------------------
DROP TABLE IF EXISTS `t_dr_role`;
CREATE TABLE `t_dr_role`  (
  `f_rid` int(5) NOT NULL,
  `f_rolename` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_jurisdiction` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`f_rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_dr_task
-- ----------------------------
DROP TABLE IF EXISTS `t_dr_task`;
CREATE TABLE `t_dr_task`  (
  `f_tid` int(5) NOT NULL COMMENT '主键，任务编号',
  `f_pid` int(5) NOT NULL COMMENT '项目编号',
  `f_create_uid` int(5) NOT NULL COMMENT '创建人',
  `f_accept_uid` int(5) NULL DEFAULT NULL COMMENT '接任务人',
  `f_taskname` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务标题',
  `f_task_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务内容',
  `f_create_time` date NOT NULL COMMENT '创建时间',
  `f_complete_time` date NOT NULL COMMENT '截止时间',
  `f_distribute_time` date NULL DEFAULT NULL COMMENT '指派时间',
  `f_task_type` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务类型',
  `f_task_state` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务状态',
  PRIMARY KEY (`f_tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_dr_user
-- ----------------------------
DROP TABLE IF EXISTS `t_dr_user`;
CREATE TABLE `t_dr_user`  (
  `f_uid` int(11) NOT NULL,
  `f_username` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_realname` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_jurisdiction` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_ame_username` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_ame_password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_ame_projectid` int(8) NULL DEFAULT NULL,
  `f_ame_taskid` int(6) NULL DEFAULT NULL,
  `f_mail` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`f_uid`) USING BTREE,
  INDEX `f_ame_projectid`(`f_ame_projectid`) USING BTREE,
  INDEX `f_ame_taskid`(`f_ame_taskid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
