
create database lottery_01;

/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50639
 Source Host           : localhost:3306
 Source Schema         : lottery_01

 Target Server Type    : MySQL
 Target Server Version : 50639
 File Encoding         : 65001

 Date: 01/10/2021 17:43:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_strategy_export_001
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export_001`;
CREATE TABLE `user_strategy_export_001` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `u_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户ID',
  `activity_id` bigint(20) DEFAULT NULL COMMENT '活动ID',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单ID',
  `strategy_id` bigint(20) DEFAULT NULL COMMENT '策略ID',
  `strategy_mode` tinyint(2) DEFAULT NULL COMMENT '策略方式（1:单项概率、2:总体概率）',
  `grant_type` tinyint(2) DEFAULT NULL COMMENT '发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）',
  `grant_date` datetime DEFAULT NULL COMMENT '发奖时间',
  `grant_state` int(11) DEFAULT NULL COMMENT '发奖状态',
  `award_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发奖ID',
  `award_type` tinyint(2) DEFAULT NULL COMMENT '奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）',
  `award_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品名称',
  `award_content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
  `uuid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '防重ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户策略计算结果表';

-- ----------------------------
-- Records of user_strategy_export_001
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_strategy_export_002
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export_002`;
CREATE TABLE `user_strategy_export_002` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `u_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户ID',
  `activity_id` bigint(20) DEFAULT NULL COMMENT '活动ID',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单ID',
  `strategy_id` bigint(20) DEFAULT NULL COMMENT '策略ID',
  `strategy_mode` tinyint(2) DEFAULT NULL COMMENT '策略方式（1:单项概率、2:总体概率）',
  `grant_type` tinyint(2) DEFAULT NULL COMMENT '发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）',
  `grant_date` datetime DEFAULT NULL COMMENT '发奖时间',
  `grant_state` int(11) DEFAULT NULL COMMENT '发奖状态',
  `award_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发奖ID',
  `award_type` tinyint(2) DEFAULT NULL COMMENT '奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）',
  `award_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品名称',
  `award_content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
  `uuid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '防重ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户策略计算结果表';

-- ----------------------------
-- Records of user_strategy_export_002
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_strategy_export_003
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export_003`;
CREATE TABLE `user_strategy_export_003` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `u_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户ID',
  `activity_id` bigint(20) DEFAULT NULL COMMENT '活动ID',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单ID',
  `strategy_id` bigint(20) DEFAULT NULL COMMENT '策略ID',
  `strategy_mode` tinyint(2) DEFAULT NULL COMMENT '策略方式（1:单项概率、2:总体概率）',
  `grant_type` tinyint(2) DEFAULT NULL COMMENT '发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）',
  `grant_date` datetime DEFAULT NULL COMMENT '发奖时间',
  `grant_state` int(11) DEFAULT NULL COMMENT '发奖状态',
  `award_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发奖ID',
  `award_type` tinyint(2) DEFAULT NULL COMMENT '奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）',
  `award_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品名称',
  `award_content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
  `uuid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '防重ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户策略计算结果表';

-- ----------------------------
-- Records of user_strategy_export_003
-- ----------------------------
BEGIN;
INSERT INTO `user_strategy_export_003` VALUES (1, 'Uhdgkw766120d', 120405215, 1443558966104850432, 42480428672, 1, 1, '2021-09-30 20:50:52', 1, '1', 1, 'IMac', '????', '1443558966104850432', '2021-09-30 20:50:52', '2021-09-30 20:50:52');
COMMIT;

-- ----------------------------
-- Table structure for user_strategy_export_004
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export_004`;
CREATE TABLE `user_strategy_export_004` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `u_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户ID',
  `activity_id` bigint(20) DEFAULT NULL COMMENT '活动ID',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单ID',
  `strategy_id` bigint(20) DEFAULT NULL COMMENT '策略ID',
  `strategy_mode` tinyint(2) DEFAULT NULL COMMENT '策略方式（1:单项概率、2:总体概率）',
  `grant_type` tinyint(2) DEFAULT NULL COMMENT '发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）',
  `grant_date` datetime DEFAULT NULL COMMENT '发奖时间',
  `grant_state` int(11) DEFAULT NULL COMMENT '发奖状态',
  `award_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发奖ID',
  `award_type` tinyint(2) DEFAULT NULL COMMENT '奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）',
  `award_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品名称',
  `award_content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
  `uuid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '防重ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户策略计算结果表';

-- ----------------------------
-- Records of user_strategy_export_004
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_take_activity
-- ----------------------------
DROP TABLE IF EXISTS `user_take_activity`;
CREATE TABLE `user_take_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `u_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户ID',
  `take_id` bigint(20) DEFAULT NULL COMMENT '活动领取ID',
  `activity_id` bigint(20) DEFAULT NULL COMMENT '活动ID',
  `activity_name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '活动名称',
  `take_date` datetime DEFAULT NULL COMMENT '活动领取时间',
  `take_count` int(11) DEFAULT NULL COMMENT '领取次数',
  `uuid` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '防重ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE COMMENT '防重ID'
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户参与活动记录表';

-- ----------------------------
-- Records of user_take_activity
-- ----------------------------
BEGIN;
INSERT INTO `user_take_activity` VALUES (1, 'Ukdli109op811d', 121019889410, 100001, '????', '2021-09-22 20:35:51', 10, 'lfjdlsoi230ii01', NULL, NULL);
INSERT INTO `user_take_activity` VALUES (5, 'Uhdgkw766120d', 121019889410, 100001, '????', '2021-09-22 20:53:57', 10, 'Uhdgkw766120d', '2021-09-22 20:53:57', '2021-09-22 20:53:57');
INSERT INTO `user_take_activity` VALUES (6, 'Uhdgkw766120d', 1443840523520606208, 100001, '???', '2021-10-01 15:28:15', 1, '100001_Uhdgkw766120d_1', '2021-10-01 15:29:55', '2021-10-01 15:29:55');
INSERT INTO `user_take_activity` VALUES (12, 'Uhdgkw766120d', 1443860509899259904, 100001, '???', '2021-10-01 16:49:05', 2, '100001_Uhdgkw766120d_2', '2021-10-01 16:49:05', '2021-10-01 16:49:05');
INSERT INTO `user_take_activity` VALUES (13, 'Uhdgkw766120d', 1443869371343732736, 100001, '???', '2021-10-01 17:24:18', 3, '100001_Uhdgkw766120d_3', '2021-10-01 17:24:18', '2021-10-01 17:24:18');
INSERT INTO `user_take_activity` VALUES (14, 'Uhdgkw766120d', 1443872365099515904, 100001, '???', '2021-10-01 17:36:12', 4, '100001_Uhdgkw766120d_4', '2021-10-01 17:36:12', '2021-10-01 17:36:12');
COMMIT;

-- ----------------------------
-- Table structure for user_take_activity_count
-- ----------------------------
DROP TABLE IF EXISTS `user_take_activity_count`;
CREATE TABLE `user_take_activity_count` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `u_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户ID',
  `activity_id` bigint(20) DEFAULT NULL COMMENT '活动ID',
  `total_count` int(11) DEFAULT NULL COMMENT '可领取次数',
  `left_count` int(11) DEFAULT NULL COMMENT '已领取次数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uId_activityId` (`u_id`,`activity_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户活动参与次数表';

-- ----------------------------
-- Records of user_take_activity_count
-- ----------------------------
BEGIN;
INSERT INTO `user_take_activity_count` VALUES (1, 'Uhdgkw766120d', 100001, 10, 0, '2021-10-01 15:29:27', '2021-10-01 15:29:27');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

