/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : bug

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 08/05/2022 14:21:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test_orders
-- ----------------------------
DROP TABLE IF EXISTS `test_orders`;
CREATE TABLE `test_orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `order_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `order_money` decimal(11, 2) NULL DEFAULT NULL COMMENT '订单金额',
  `back_money` decimal(11, 2) NULL DEFAULT NULL COMMENT '退款金额',
  `order_state` int(4) NULL DEFAULT NULL COMMENT '订单状态：1-待付款，2-待发货，3-待收货，4-已签收，5-已完成，6-已取消',
  `status` int(4) NULL DEFAULT NULL COMMENT '数据状态：0-无效，1-有效',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_orders
-- ----------------------------

-- ----------------------------
-- Table structure for test_orders_product
-- ----------------------------
DROP TABLE IF EXISTS `test_orders_product`;
CREATE TABLE `test_orders_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `orders_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `sku_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `sku_num` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  `sku_state` int(11) NULL DEFAULT NULL COMMENT '商品状态：1-正常，2-售后',
  `sku_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单件商品售价',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '该商品总价格（单价 * 数量）',
  `status` int(4) NULL DEFAULT NULL COMMENT '数据状态：0-无效，1-有效',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_orders_product
-- ----------------------------

-- ----------------------------
-- Table structure for test_product
-- ----------------------------
DROP TABLE IF EXISTS `test_product`;
CREATE TABLE `test_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `sku_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名字',
  `sku_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `sku_inventory` int(11) NULL DEFAULT NULL COMMENT '商品库存',
  `status` int(4) NULL DEFAULT NULL COMMENT '数据状态：0-无效，1-有效',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_product
-- ----------------------------
INSERT INTO `test_product` VALUES (1, 'a', 1.00, 100, 1, '2022-05-08 12:08:05', NULL, '2022-05-08 14:19:32', NULL);
INSERT INTO `test_product` VALUES (2, 'b', 2.00, 99, 1, '2022-05-08 12:31:15', NULL, '2022-05-08 14:19:34', NULL);
INSERT INTO `test_product` VALUES (3, 'c', 3.00, 5, 1, '2022-05-08 12:31:24', NULL, '2022-05-08 14:19:37', NULL);

SET FOREIGN_KEY_CHECKS = 1;
