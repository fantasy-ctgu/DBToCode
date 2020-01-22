/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : sideline

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 22/01/2020 15:40:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `icon` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系邮箱',
  `last_login_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后登陆时间',
  `last_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登陆IP',
  `user_level_id` int(11) NULL DEFAULT 2,
  `createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  INDEX `user_level_id`(`user_level_id`) USING BTREE,
  CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`user_level_id`) REFERENCES `user_level` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'fantasy', '7a1c07ff60f9c07ffe8da34ecbf4edc2', 'fantasy', NULL, '776474961@qq.com', '2020-01-22 15:29:27', '127.0.0.1', 2, '2020-01-22 15:29:27');

-- ----------------------------
-- Table structure for appraise
-- ----------------------------
DROP TABLE IF EXISTS `appraise`;
CREATE TABLE `appraise`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employ_id` int(11) NOT NULL COMMENT '雇佣记录id',
  `appraise_type` int(1) NULL DEFAULT NULL COMMENT '0: 未评价 1: 被雇者评价雇佣者 2: 雇佣者评价被雇者',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评价内容',
  `score` int(2) NOT NULL COMMENT '评分: 1~10',
  `updatetime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `employ_id`(`employ_id`) USING BTREE,
  CONSTRAINT `appraise_ibfk_1` FOREIGN KEY (`employ_id`) REFERENCES `employ` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '雇佣评价' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for brief_message
-- ----------------------------
DROP TABLE IF EXISTS `brief_message`;
CREATE TABLE `brief_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_id` int(11) NOT NULL,
  `to_id` int(11) NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `message_type` int(1) NOT NULL COMMENT '1: 被雇者发给雇佣者 2:雇佣者发给被雇者',
  `is_read` int(1) NULL DEFAULT 0 COMMENT '1/0: 是/否已读',
  `read_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '阅读时间',
  `createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '被雇者消息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for employ
-- ----------------------------
DROP TABLE IF EXISTS `employ`;
CREATE TABLE `employ`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL COMMENT '被雇者',
  `employer_id` int(11) NOT NULL COMMENT '雇佣者',
  `employ_status_id` int(11) NULL DEFAULT 1,
  `is_read` int(1) NULL DEFAULT 0,
  `updatetime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `employer_id`(`employer_id`) USING BTREE,
  INDEX `employee_id`(`employee_id`) USING BTREE,
  INDEX `employ_status_id`(`employ_status_id`) USING BTREE,
  CONSTRAINT `employ_ibfk_1` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `employ_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `employ_ibfk_3` FOREIGN KEY (`employ_status_id`) REFERENCES `employ_status` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '雇佣记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for employ_status
-- ----------------------------
DROP TABLE IF EXISTS `employ_status`;
CREATE TABLE `employ_status`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `explains` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '雇佣状态' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employ_status
-- ----------------------------
INSERT INTO `employ_status` VALUES (1, '已申请');
INSERT INTO `employ_status` VALUES (2, '已放弃');
INSERT INTO `employ_status` VALUES (3, '申请成功');
INSERT INTO `employ_status` VALUES (4, '申请失败');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birth` date NULL DEFAULT NULL,
  `icon` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '头像',
  `top_degree` enum('博士','硕士','本科','大专','高中','初中','小学','其他') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最高学历',
  `school` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最近就读学校',
  `specialty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '兼职意向',
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系邮箱',
  `province` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在省份',
  `city` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在城市',
  `experience` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '个人经历',
  `evaluation` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '自我评价',
  `score` int(11) NOT NULL DEFAULT 0 COMMENT '雇佣记录总分',
  `employ_count` int(11) NOT NULL DEFAULT 0 COMMENT '雇佣成功次数',
  `report_count` int(11) NOT NULL DEFAULT 0 COMMENT '被举报次数',
  `last_login_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后登陆时间',
  `last_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登陆IP',
  `user_level_id` int(11) NULL DEFAULT 2,
  `updatetime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  INDEX `user_level_id`(`user_level_id`) USING BTREE,
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`user_level_id`) REFERENCES `user_level` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '兼职人员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'fantasy', '7a1c07ff60f9c07ffe8da34ecbf4edc2', 'fantasy', '男', '1996-07-27', NULL, '本科', 'ctgu', '计算机相关', '15871577788', '776474961@qq.com', '湖北省', '宜昌', 'ACM 一等奖', '十佳青年', 0, 0, 0, '2020-01-22 15:29:27', '127.0.0.1', 2, '2020-01-22 15:29:27', '2020-01-22 15:29:27');

-- ----------------------------
-- Table structure for employer
-- ----------------------------
DROP TABLE IF EXISTS `employer`;
CREATE TABLE `employer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位名称',
  `icon` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '头像',
  `province` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在省份',
  `city` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在城市',
  `address` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详细地址',
  `contacts` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduce` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '单位介绍',
  `score` int(11) NOT NULL DEFAULT 0 COMMENT '雇佣记录总分',
  `employ_count` int(11) NOT NULL DEFAULT 0 COMMENT '雇佣成功次数',
  `report_count` int(11) NOT NULL DEFAULT 0 COMMENT '被举报次数',
  `last_login_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后登陆时间',
  `last_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登陆IP',
  `user_level_id` int(11) NOT NULL DEFAULT 2,
  `updatetime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  INDEX `user_level_id`(`user_level_id`) USING BTREE,
  CONSTRAINT `employer_ibfk_1` FOREIGN KEY (`user_level_id`) REFERENCES `user_level` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '招聘单位' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employer
-- ----------------------------
INSERT INTO `employer` VALUES (1, 'fantasy', '7a1c07ff60f9c07ffe8da34ecbf4edc2', 'HUAWEI', NULL, '湖北', '武汉', '武汉大学南门口', 'chenxiang', '15871577021', '776474961@qq.com', '没有最好，只有更好', 983, 1000, 100, '2020-01-22 15:29:28', '192.168.9.211', 2, '2020-01-22 15:29:28', '2020-01-22 15:29:28');

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employer_id` int(11) NOT NULL COMMENT '发布单位',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作名称',
  `pay_type` enum('日','周','月','完工') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结算方式',
  `wages` int(11) NOT NULL COMMENT '最低薪水/结算方式',
  `job_time` int(11) NULL DEFAULT NULL COMMENT '每日工作时长',
  `province` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在省份',
  `city` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在城市',
  `address` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详细工作地点',
  `request_num` int(3) NULL DEFAULT 1 COMMENT '需要人数',
  `job_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位描述',
  `employee_require` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '对兼职者要求',
  `job_type_id` int(11) NOT NULL COMMENT '岗位分类',
  `keyword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关键字',
  `browse_count` int(11) NULL DEFAULT 0,
  `job_status_id` int(11) NULL DEFAULT 2,
  `updatetime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `employer_id`(`employer_id`) USING BTREE,
  INDEX `job_type_id`(`job_type_id`) USING BTREE,
  INDEX `job_status_id`(`job_status_id`) USING BTREE,
  CONSTRAINT `job_ibfk_1` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `job_ibfk_2` FOREIGN KEY (`job_type_id`) REFERENCES `job_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `job_ibfk_3` FOREIGN KEY (`job_status_id`) REFERENCES `job_status` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job_name
-- ----------------------------
DROP TABLE IF EXISTS `job_name`;
CREATE TABLE `job_name`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_type` int(11) NOT NULL,
  `explains` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `reference_num` int(11) NULL DEFAULT 1 COMMENT '引用计数',
  `createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位名称' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job_status
-- ----------------------------
DROP TABLE IF EXISTS `job_status`;
CREATE TABLE `job_status`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `explains` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位状态' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job_status
-- ----------------------------
INSERT INTO `job_status` VALUES (1, '待审核');
INSERT INTO `job_status` VALUES (2, '已审核');
INSERT INTO `job_status` VALUES (3, '已结束');
INSERT INTO `job_status` VALUES (4, '已删除');

-- ----------------------------
-- Table structure for job_type
-- ----------------------------
DROP TABLE IF EXISTS `job_type`;
CREATE TABLE `job_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `explains` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `reference_num` int(11) NULL DEFAULT 0 COMMENT '引用计数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job_type
-- ----------------------------
INSERT INTO `job_type` VALUES (1, '互联网/计算机/IT/通信', 0);
INSERT INTO `job_type` VALUES (2, '金融/会计/保险/销售', 0);
INSERT INTO `job_type` VALUES (3, '建筑/房地产', 0);
INSERT INTO `job_type` VALUES (4, '贸易/制造/运营', 0);
INSERT INTO `job_type` VALUES (5, '传媒/广告', 0);
INSERT INTO `job_type` VALUES (6, '服务/教育', 0);
INSERT INTO `job_type` VALUES (7, '物流/运输', 0);
INSERT INTO `job_type` VALUES (8, '政府/非营利组织/其它', 0);

-- ----------------------------
-- Table structure for keyword
-- ----------------------------
DROP TABLE IF EXISTS `keyword`;
CREATE TABLE `keyword`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `explains` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关键字',
  `reference_num` int(11) NULL DEFAULT 1 COMMENT '引用计数',
  `createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '关键字' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL COMMENT '被雇者',
  `employer_id` int(11) NOT NULL COMMENT '雇佣者',
  `reason` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '举报原因',
  `report_type` int(1) NOT NULL DEFAULT 1 COMMENT '1:用户举报单位,2:单位举报用户',
  `deal_admin_id` int(11) NULL DEFAULT NULL COMMENT '处理管理员id',
  `deal_result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `deal_result_type` int(1) NULL DEFAULT NULL COMMENT '0:恶意举报 1:有效举报',
  `updatetime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `employer_id`(`employer_id`) USING BTREE,
  INDEX `employee_id`(`employee_id`) USING BTREE,
  INDEX `deal_admin_id`(`deal_admin_id`) USING BTREE,
  CONSTRAINT `report_ibfk_1` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `report_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `report_ibfk_3` FOREIGN KEY (`deal_admin_id`) REFERENCES `admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '举报信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_level
-- ----------------------------
DROP TABLE IF EXISTS `user_level`;
CREATE TABLE `user_level`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `explains` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户状态' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_level
-- ----------------------------
INSERT INTO `user_level` VALUES (1, '封禁');
INSERT INTO `user_level` VALUES (2, '待审核');
INSERT INTO `user_level` VALUES (3, '普通用户');

SET FOREIGN_KEY_CHECKS = 1;
