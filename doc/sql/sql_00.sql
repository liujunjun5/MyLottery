/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50639
 Source Host           : localhost:3306
 Source Schema         : lottery

 Target Server Type    : MySQL
 Target Server Version : 50639
 File Encoding         : 65001

 Date: 05/25/2025 16:24:34
*/


create database lottery;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
  `activity_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '活动名称',
  `activity_desc` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '活动描述',
  `begin_date_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date_time` datetime DEFAULT NULL COMMENT '结束时间',
  `stock_count` int(11) DEFAULT NULL COMMENT '库存',
  `take_count` int(11) DEFAULT NULL COMMENT '每人可参与次数',
  `state` tinyint(2) DEFAULT NULL COMMENT '活动状态：1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启',
  `creator` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_activity_id` (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='活动配置';

-- ----------------------------
-- Records of activity
-- ----------------------------
BEGIN;
INSERT INTO `activity` VALUES (1, 100001, '活动名', '测试活动', '2025-08-08 20:14:50', '2025-08-08 20:14:50', 100, 10, 1, 'xiaofuge', '2025-08-08 20:14:50', '2025-08-08 20:14:50');
COMMIT;

-- ----------------------------
-- Table structure for award
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `award_id` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '奖品ID',
  `award_type` tinyint(4) DEFAULT NULL COMMENT '奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）',
  `award_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '奖品名称',
  `award_content` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_award_id` (`award_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='奖品配置';

-- ----------------------------
-- Records of award
-- ----------------------------
BEGIN;
INSERT INTO `award` VALUES (1, '1', 1, 'IMac', 'Code', '2025-08-15 15:38:05', '2025-08-15 15:38:05');
INSERT INTO `award` VALUES (2, '2', 1, 'iphone', 'Code', '2025-08-15 15:38:05', '2025-08-15 15:38:05');
INSERT INTO `award` VALUES (3, '3', 1, 'ipad', 'Code', '2025-08-15 15:38:05', '2025-08-15 15:38:05');
INSERT INTO `award` VALUES (4, '4', 1, 'AirPods', 'Code', '2025-08-15 15:38:05', '2025-08-15 15:38:05');
INSERT INTO `award` VALUES (5, '5', 1, 'Book', 'Code', '2025-08-15 15:38:05', '2025-08-15 15:38:05');
COMMIT;

-- ----------------------------
-- Table structure for strategy
-- ----------------------------
DROP TABLE IF EXISTS `strategy`;
CREATE TABLE `strategy` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategy_id` bigint(11) NOT NULL COMMENT '策略ID',
  `strategy_desc` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '策略描述',
  `strategy_mode` tinyint(2) DEFAULT NULL COMMENT '策略方式（1:单项概率、2:总体概率）',
  `grant_type` tinyint(2) DEFAULT NULL COMMENT '发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）',
  `grant_date` datetime DEFAULT NULL COMMENT '发放奖品时间',
  `ext_info` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '扩展信息',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `strategy_strategyId_uindex` (`strategy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='策略配置';

-- ----------------------------
-- Records of strategy
-- ----------------------------
BEGIN;
INSERT INTO `strategy` VALUES (1, 10001, 'test', 1, 1, NULL, '', '2025-09-04 15:37:52', '2025-09-04 15:37:52');
COMMIT;

-- ----------------------------
-- Table structure for strategy_detail
-- ----------------------------
DROP TABLE IF EXISTS `strategy_detail`;
CREATE TABLE `strategy_detail` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategy_id` bigint(11) NOT NULL COMMENT '策略ID',
  `award_id` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '奖品ID',
  `award_name` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '奖品描述',
  `award_count` int(11) DEFAULT NULL COMMENT '奖品库存',
  `award_surplus_count` int(11) DEFAULT '0' COMMENT '奖品剩余库存',
  `award_rate` decimal(5,2) DEFAULT NULL COMMENT '中奖概率',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='策略明细';

-- ----------------------------
-- Records of strategy_detail
-- ----------------------------
BEGIN;
INSERT INTO `strategy_detail` VALUES (1, 10001, '1', 'IMac', 10, 0, 0.05, '2025-08-15 15:38:05', '2025-08-15 15:38:05');
INSERT INTO `strategy_detail` VALUES (2, 10001, '2', 'iphone', 20, 20, 0.15, '2025-08-15 15:38:05', '2025-08-15 15:38:05');
INSERT INTO `strategy_detail` VALUES (3, 10001, '3', 'ipad', 50, 50, 0.20, '2025-08-15 15:38:05', '2025-08-15 15:38:05');
INSERT INTO `strategy_detail` VALUES (4, 10001, '4', 'AirPods', 100, 80, 0.25, '2025-08-15 15:38:05', '2025-08-15 15:38:05');
INSERT INTO `strategy_detail` VALUES (5, 10001, '5', 'Book', 500, 391, 0.35, '2025-08-15 15:38:05', '2025-08-15 15:38:05');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
