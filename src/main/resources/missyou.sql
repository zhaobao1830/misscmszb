/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : missyou

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2023-11-14 09:52:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `activity`
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(60) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `start_time` datetime(3) NOT NULL,
  `end_time` datetime(3) NOT NULL,
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `remark` varchar(60) DEFAULT NULL,
  `online` tinyint(3) unsigned DEFAULT '1',
  `entrance_img` varchar(255) DEFAULT NULL,
  `internal_top_img` varchar(255) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('2', '夏日好礼送不停', '长夏村墟风日清', '2019-08-03 18:04:52.000', '2030-08-31 18:05:16.000', '2019-08-03 17:59:01.000', '2019-09-20 01:12:37.442', null, '限服装、鞋、文具等商品', '1', null, null, 'a-2');

-- ----------------------------
-- Table structure for `activity_category`
-- ----------------------------
DROP TABLE IF EXISTS `activity_category`;
CREATE TABLE `activity_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` int(10) unsigned NOT NULL,
  `activity_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of activity_category
-- ----------------------------
INSERT INTO `activity_category` VALUES ('1', '2', '2');
INSERT INTO `activity_category` VALUES ('2', '7', '2');
INSERT INTO `activity_category` VALUES ('6', '4', '2');
INSERT INTO `activity_category` VALUES ('7', '27', '2');
INSERT INTO `activity_category` VALUES ('8', '32', '2');
INSERT INTO `activity_category` VALUES ('9', '27', '1');
INSERT INTO `activity_category` VALUES ('11', '1', '3');
INSERT INTO `activity_category` VALUES ('12', '2', '3');
INSERT INTO `activity_category` VALUES ('13', '1', '4');
INSERT INTO `activity_category` VALUES ('14', '2', '4');
INSERT INTO `activity_category` VALUES ('15', '3', '4');

-- ----------------------------
-- Table structure for `activity_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `activity_coupon`;
CREATE TABLE `activity_coupon` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `coupon_id` int(10) unsigned NOT NULL,
  `activity_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of activity_coupon
-- ----------------------------
INSERT INTO `activity_coupon` VALUES ('1', '3', '2');
INSERT INTO `activity_coupon` VALUES ('2', '4', '2');
INSERT INTO `activity_coupon` VALUES ('3', '5', '2');
INSERT INTO `activity_coupon` VALUES ('4', '7', '1');
INSERT INTO `activity_coupon` VALUES ('6', '3', '3');
INSERT INTO `activity_coupon` VALUES ('7', '4', '3');
INSERT INTO `activity_coupon` VALUES ('8', '3', '4');
INSERT INTO `activity_coupon` VALUES ('9', '4', '4');
INSERT INTO `activity_coupon` VALUES ('10', '5', '4');

-- ----------------------------
-- Table structure for `activity_spu`
-- ----------------------------
DROP TABLE IF EXISTS `activity_spu`;
CREATE TABLE `activity_spu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `activity_id` int(10) unsigned NOT NULL,
  `spu_id` int(10) unsigned NOT NULL,
  `participation` tinyint(3) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of activity_spu
-- ----------------------------

-- ----------------------------
-- Table structure for `banner`
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL COMMENT '部分banner可能有标题图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES ('1', 'b-1', '首页顶部主banner', '2019-07-28 04:47:15.000', '2019-08-04 01:03:16.000', null, null, null);
INSERT INTO `banner` VALUES ('2', 'b-2', '热销商品banner', '2019-08-01 00:37:47.000', '2019-09-20 00:56:45.843', null, null, null);

-- ----------------------------
-- Table structure for `banner_item`
-- ----------------------------
DROP TABLE IF EXISTS `banner_item`;
CREATE TABLE `banner_item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `img` varchar(255) DEFAULT NULL,
  `keyword` varchar(50) DEFAULT NULL,
  `type` smallint(5) unsigned NOT NULL DEFAULT '0',
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `banner_id` int(10) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of banner_item
-- ----------------------------
INSERT INTO `banner_item` VALUES ('5', null, '28', '1', '2019-08-01 00:41:41.000', '2019-09-20 01:02:57.058', null, '2', 'left');
INSERT INTO `banner_item` VALUES ('6', null, '26', '1', '2019-08-01 00:41:41.000', '2019-09-20 01:03:20.431', null, '2', 'right-top');
INSERT INTO `banner_item` VALUES ('7', null, '27', '1', '2019-08-01 00:41:41.000', '2019-09-20 01:03:22.441', null, '2', 'right-bottom');
INSERT INTO `banner_item` VALUES ('12', null, 't-2', '3', '2019-09-15 17:29:52.000', '2019-09-20 00:47:22.829', null, '1', null);
INSERT INTO `banner_item` VALUES ('13', null, '23', '1', '2019-07-28 04:39:22.000', '2019-09-18 21:27:20.349', null, '1', null);
INSERT INTO `banner_item` VALUES ('14', null, '24', '1', '2019-07-28 04:40:10.000', '2019-09-18 21:27:20.349', null, '1', null);

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `author` varchar(30) DEFAULT NULL,
  `summary` varchar(1000) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '深入理解计算机系统', 'Randal E.Bryant', '从程序员的视角，看计算机系统！\n本书适用于那些想要写出更快、更可靠程序的程序员。通过掌握程序是如何映射到系统上，以及程序是如何执行的，读者能够更好的理解程序的行为为什么是这样的，以及效率低下是如何造成的。', 'https://img3.doubanio.com/lpic/s1470003.jpg', '2023-11-14 09:01:49.985', '2023-11-14 09:01:49.985', null, '0');
INSERT INTO `book` VALUES ('2', 'C程序设计语言', '（美）Brian W. Kernighan', '在计算机发展的历史上，没有哪一种程序设计语言像C语言这样应用广泛。本书原著即为C语言的设计者之一Dennis M.Ritchie和著名计算机科学家Brian W.Kernighan合著的一本介绍C语言的权威经典著作。', 'https://img3.doubanio.com/lpic/s1106934.jpg', '2023-11-14 09:01:49.990', '2023-11-14 09:01:49.990', null, '0');

-- ----------------------------
-- Table structure for `brand`
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of brand
-- ----------------------------

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_root` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `parent_id` int(10) unsigned DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `index` int(10) unsigned DEFAULT NULL,
  `online` int(10) unsigned DEFAULT '1',
  `level` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '鞋', null, '2019-07-15 08:51:19.000', '2019-09-21 23:23:28.452', null, '1', null, null, '3', '1', null);
INSERT INTO `category` VALUES ('2', '服装', null, '2019-07-15 08:51:28.000', '2019-09-21 23:25:37.271', null, '1', null, null, '2', '1', null);
INSERT INTO `category` VALUES ('3', '包包', null, '2019-07-15 08:51:35.000', '2019-09-21 23:26:14.260', null, '1', null, null, '1', '1', null);
INSERT INTO `category` VALUES ('4', '居家', null, '2019-07-15 08:51:42.000', '2019-09-21 23:26:17.106', null, '1', null, null, '5', '1', null);
INSERT INTO `category` VALUES ('5', '饰品', null, '2019-07-15 08:51:49.000', '2019-09-21 23:26:25.245', null, '1', null, null, '4', '1', null);
INSERT INTO `category` VALUES ('6', '平底鞋', null, '2019-07-15 08:51:55.000', '2019-09-21 23:32:00.822', null, '0', '1', null, null, '1', null);
INSERT INTO `category` VALUES ('7', '凉鞋', null, '2019-07-15 08:52:47.000', '2019-09-21 23:32:04.346', null, '0', '1', null, null, '1', null);
INSERT INTO `category` VALUES ('8', '拖鞋', null, '2019-07-15 08:53:04.000', '2019-09-21 23:37:12.047', null, '0', '1', null, null, '1', null);
INSERT INTO `category` VALUES ('9', '运动鞋', null, '2019-07-15 08:53:23.000', '2019-09-21 23:37:18.707', null, '0', '1', null, null, '1', null);
INSERT INTO `category` VALUES ('10', '高跟鞋', null, '2019-07-15 08:53:51.000', '2019-09-21 23:37:26.999', null, '0', '1', null, null, '1', null);
INSERT INTO `category` VALUES ('11', '衬衫', null, '2019-07-15 08:54:41.000', '2019-09-21 23:37:40.172', null, '0', '2', null, null, '1', null);
INSERT INTO `category` VALUES ('12', 'T恤', null, '2019-07-15 08:55:11.000', '2019-09-21 23:37:47.560', null, '0', '2', null, null, '1', null);
INSERT INTO `category` VALUES ('13', '牛仔裤', null, '2019-07-15 08:55:58.000', '2019-09-21 23:37:50.095', null, '0', '2', null, null, '1', null);
INSERT INTO `category` VALUES ('14', '针织衫', null, '2019-07-15 08:56:27.000', '2019-09-21 23:37:55.138', null, '0', '2', null, null, '1', null);
INSERT INTO `category` VALUES ('15', '连衣裙', null, '2019-07-15 08:56:43.000', '2019-09-21 23:38:08.140', null, '0', '2', null, null, '1', null);
INSERT INTO `category` VALUES ('16', '风衣', null, '2019-07-15 08:57:38.000', '2019-09-21 23:38:10.184', null, '0', '2', null, null, '1', null);
INSERT INTO `category` VALUES ('17', '手包', null, '2019-07-15 08:58:12.000', '2019-09-21 23:38:17.871', null, '0', '3', null, null, '1', null);
INSERT INTO `category` VALUES ('18', '旅行包', null, '2019-07-15 08:58:38.000', '2019-09-21 23:38:24.079', null, '0', '3', null, null, '1', null);
INSERT INTO `category` VALUES ('19', '单肩包', null, '2019-07-15 08:58:51.000', '2019-09-21 23:38:28.196', null, '0', '3', null, null, '1', null);
INSERT INTO `category` VALUES ('20', '收纳', null, '2019-07-15 09:00:19.000', '2019-09-21 23:31:27.823', null, '0', '4', null, null, '1', null);
INSERT INTO `category` VALUES ('21', '毛巾', null, '2019-07-15 09:01:38.000', '2019-09-21 23:31:32.074', null, '0', '4', null, null, '1', null);
INSERT INTO `category` VALUES ('22', '四件套', null, '2019-07-15 09:04:52.000', '2019-09-21 23:31:35.949', null, '0', '4', null, null, '1', null);
INSERT INTO `category` VALUES ('23', '台灯', null, '2019-07-15 14:18:40.000', '2019-09-21 23:31:45.456', null, '0', '4', null, null, '1', null);
INSERT INTO `category` VALUES ('24', '工艺', null, '2019-07-15 14:20:05.000', '2019-09-21 23:26:58.146', null, '1', null, null, '1', '1', null);
INSERT INTO `category` VALUES ('25', '玻璃杯', null, '2019-07-15 14:23:01.000', '2019-09-21 23:40:47.698', null, '0', '27', null, null, '1', null);
INSERT INTO `category` VALUES ('26', '桌布', null, '2019-07-15 14:26:25.000', '2019-07-15 14:32:14.000', null, '0', '27', null, null, '1', null);
INSERT INTO `category` VALUES ('27', '餐边', null, '2019-07-15 14:32:01.000', '2019-09-11 14:35:36.000', null, '1', null, null, '6', '0', null);
INSERT INTO `category` VALUES ('28', '盘和碗', null, '2019-07-15 14:40:23.000', '2019-07-15 14:43:47.000', null, '0', '27', null, null, '1', null);
INSERT INTO `category` VALUES ('29', '数码', null, '2019-07-17 07:51:07.000', '2019-09-11 14:36:54.000', null, '1', null, null, '7', '0', null);
INSERT INTO `category` VALUES ('30', '笔电', null, '2019-07-17 07:51:57.000', '2019-07-17 07:52:25.000', null, '0', '29', null, null, '1', null);
INSERT INTO `category` VALUES ('31', '手机', null, '2019-07-17 08:01:59.000', '2019-07-17 08:02:02.000', null, '0', '29', null, null, '1', null);
INSERT INTO `category` VALUES ('32', '文具', null, '2019-07-30 01:29:19.000', '2019-09-22 00:14:40.026', null, '0', '24', null, null, '1', null);
INSERT INTO `category` VALUES ('33', '女妆', null, '2019-08-02 02:54:11.000', '2019-09-11 14:35:27.000', null, '1', null, null, '8', '0', null);
INSERT INTO `category` VALUES ('34', '香水', null, '2019-08-02 02:54:30.000', '2019-08-02 02:54:30.000', null, '0', '33', null, null, '1', null);
INSERT INTO `category` VALUES ('35', '家具', null, '2019-08-02 19:44:24.000', '2019-09-21 23:40:27.664', null, '0', '4', null, '9', '1', null);
INSERT INTO `category` VALUES ('36', '酷玩', null, '2019-08-07 22:49:22.000', '2019-09-21 23:38:50.402', null, '0', '5', null, null, '1', null);
INSERT INTO `category` VALUES ('37', '测试数据', null, '2019-08-25 19:06:24.000', '2019-09-21 23:39:21.756', null, '1', null, null, null, '1', null);
INSERT INTO `category` VALUES ('38', '测试数据', null, '2019-08-25 19:07:53.000', '2019-09-21 23:38:52.170', null, '0', '37', null, null, '1', null);
INSERT INTO `category` VALUES ('39', '束带', null, '2019-09-07 16:43:29.000', '2019-09-21 23:39:47.444', null, '0', '5', null, null, '1', null);

-- ----------------------------
-- Table structure for `coupon`
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `full_money` decimal(10,2) DEFAULT NULL,
  `minus` decimal(10,2) DEFAULT NULL,
  `rate` decimal(10,2) DEFAULT NULL,
  `type` smallint(6) NOT NULL COMMENT '1. 满减券 2.折扣券 3.无门槛券 4.满金额折扣券',
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `activity_id` int(10) unsigned DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `whole_store` tinyint(3) unsigned DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES ('3', '无门槛减0.1券', '2019-08-05 06:11:42', '2031-08-05 06:11:48', null, null, '0.10', null, '3', '2019-08-03 08:22:06.000', '2019-08-26 14:50:55.000', null, '2', '全场无门槛立减', '1');
INSERT INTO `coupon` VALUES ('4', '满500减100券', '2019-08-05 06:11:42', '2030-08-05 06:11:48', null, '500.00', '100.00', null, '1', '2019-08-03 08:19:34.000', '2019-09-15 21:44:53.000', null, '2', '限服装、居家、文具等商品', '0');
INSERT INTO `coupon` VALUES ('7', '满1000减230券', '2019-08-05 06:10:48', '2030-03-05 06:11:17', null, '1000.00', '230.00', null, '1', '2019-08-03 08:18:36.000', '2019-09-15 21:44:53.000', null, '2', '限服装、家具、文具等商品', '0');
INSERT INTO `coupon` VALUES ('10', '满1000打8折', '2019-08-23 09:07:29', '2030-08-23 09:07:36', null, null, null, null, '2', '2019-08-23 09:07:57.000', '2019-08-23 09:07:57.000', null, null, null, null);
INSERT INTO `coupon` VALUES ('11', '满100打9.9折', '2019-08-28 03:49:55', '2030-08-28 03:49:59', null, '100.00', null, '0.99', '2', '2019-08-28 03:49:11.000', '2019-09-05 19:14:56.000', null, '2', '全场通用券', '1');

-- ----------------------------
-- Table structure for `coupon_category`
-- ----------------------------
DROP TABLE IF EXISTS `coupon_category`;
CREATE TABLE `coupon_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` int(10) unsigned NOT NULL,
  `coupon_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of coupon_category
-- ----------------------------
INSERT INTO `coupon_category` VALUES ('1', '15', '4');
INSERT INTO `coupon_category` VALUES ('2', '32', '4');
INSERT INTO `coupon_category` VALUES ('6', '35', '4');
INSERT INTO `coupon_category` VALUES ('7', '15', '7');
INSERT INTO `coupon_category` VALUES ('8', '35', '7');
INSERT INTO `coupon_category` VALUES ('9', '32', '7');

-- ----------------------------
-- Table structure for `coupon_template`
-- ----------------------------
DROP TABLE IF EXISTS `coupon_template`;
CREATE TABLE `coupon_template` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `full_money` decimal(10,2) DEFAULT NULL,
  `minus` decimal(10,2) DEFAULT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `type` smallint(6) NOT NULL COMMENT '1. 满减券 2.折扣券 3.无门槛券 4.满金额折扣券',
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of coupon_template
-- ----------------------------
INSERT INTO `coupon_template` VALUES ('3', '满1000-230', null, '1000.00', '230.00', null, '1', '2019-08-03 08:18:36.000', '2019-08-03 08:20:18.000', null);
INSERT INTO `coupon_template` VALUES ('4', '满500-100', null, '500.00', '100.00', null, '1', '2019-08-03 08:19:34.000', '2019-08-03 08:19:34.000', null);
INSERT INTO `coupon_template` VALUES ('5', '满2000-500', null, '2000.00', '500.00', null, '1', '2019-08-03 08:20:03.000', '2019-08-03 08:20:23.000', null);
INSERT INTO `coupon_template` VALUES ('6', '满5000-1500', null, '5000.00', '1500.00', null, '1', '2019-08-03 08:21:15.000', '2019-08-03 08:21:15.000', null);
INSERT INTO `coupon_template` VALUES ('7', '无门槛-20', null, null, null, null, '3', '2019-08-03 08:22:06.000', '2019-08-03 08:22:06.000', null);
INSERT INTO `coupon_template` VALUES ('8', '满减折扣', null, '800.00', null, '0.90', '4', '2019-08-03 08:23:54.000', '2019-08-03 08:25:02.000', null);
INSERT INTO `coupon_template` VALUES ('9', '无门槛折扣', null, null, null, '0.95', '2', '2019-08-03 08:25:54.000', '2019-08-03 08:25:54.000', null);

-- ----------------------------
-- Table structure for `coupon_type`
-- ----------------------------
DROP TABLE IF EXISTS `coupon_type`;
CREATE TABLE `coupon_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `code` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of coupon_type
-- ----------------------------

-- ----------------------------
-- Table structure for `grid_category`
-- ----------------------------
DROP TABLE IF EXISTS `grid_category`;
CREATE TABLE `grid_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `root_category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of grid_category
-- ----------------------------
INSERT INTO `grid_category` VALUES ('1', '服装', null, null, '2019-08-02 19:41:55.000', '2019-09-18 21:27:20.349', null, null, '2');
INSERT INTO `grid_category` VALUES ('2', '包包', null, null, '2019-08-02 19:42:11.000', '2019-09-18 21:27:20.349', null, null, '3');
INSERT INTO `grid_category` VALUES ('3', '鞋', null, null, '2019-08-02 19:42:57.000', '2019-09-18 21:27:20.349', null, null, '1');
INSERT INTO `grid_category` VALUES ('4', '饰品', null, null, '2019-08-02 19:43:34.000', '2019-09-18 21:27:20.349', null, null, '5');
INSERT INTO `grid_category` VALUES ('5', '居家', null, null, '2019-08-02 19:44:34.000', '2019-09-18 21:27:20.349', null, null, '4');
INSERT INTO `grid_category` VALUES ('6', '工艺', null, null, '2019-08-02 19:46:53.000', '2019-09-18 21:27:20.349', null, null, '24');

-- ----------------------------
-- Table structure for `information`
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of information
-- ----------------------------
INSERT INTO `information` VALUES ('1', '测试测试', '1');
INSERT INTO `information` VALUES ('2', '测试', '2');
INSERT INTO `information` VALUES ('3', '测试1', '3');

-- ----------------------------
-- Table structure for `lin_file`
-- ----------------------------
DROP TABLE IF EXISTS `lin_file`;
CREATE TABLE `lin_file` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `path` varchar(500) NOT NULL,
  `type` varchar(10) NOT NULL DEFAULT 'LOCAL' COMMENT 'LOCAL 本地，REMOTE 远程',
  `name` varchar(100) NOT NULL,
  `extension` varchar(50) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `md5` varchar(40) DEFAULT NULL COMMENT 'md5值，防止上传重复文件',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `md5_del` (`md5`,`delete_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of lin_file
-- ----------------------------

-- ----------------------------
-- Table structure for `lin_group`
-- ----------------------------
DROP TABLE IF EXISTS `lin_group`;
CREATE TABLE `lin_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL COMMENT '分组名称，例如：搬砖者',
  `info` varchar(255) DEFAULT NULL COMMENT '分组信息：例如：搬砖的人',
  `level` tinyint(2) NOT NULL DEFAULT '3' COMMENT '分组级别 1：root 2：guest 3：user  root（root、guest分组只能存在一个)',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_del` (`name`,`delete_time`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of lin_group
-- ----------------------------
INSERT INTO `lin_group` VALUES ('1', 'root', '超级用户组', '1', '2023-11-14 09:01:49.995', '2023-11-14 09:01:49.995', null, '0');
INSERT INTO `lin_group` VALUES ('2', 'guest', '游客组', '2', '2023-11-14 09:01:49.996', '2023-11-14 09:01:49.996', null, '0');

-- ----------------------------
-- Table structure for `lin_group_permission`
-- ----------------------------
DROP TABLE IF EXISTS `lin_group_permission`;
CREATE TABLE `lin_group_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `group_id` int(10) unsigned NOT NULL COMMENT '分组id',
  `permission_id` int(10) unsigned NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  KEY `group_id_permission_id` (`group_id`,`permission_id`) USING BTREE COMMENT '联合索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of lin_group_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `lin_log`
-- ----------------------------
DROP TABLE IF EXISTS `lin_log`;
CREATE TABLE `lin_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `message` varchar(450) DEFAULT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `username` varchar(24) DEFAULT NULL,
  `status_code` int(11) DEFAULT NULL,
  `method` varchar(20) DEFAULT NULL,
  `path` varchar(50) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of lin_log
-- ----------------------------

-- ----------------------------
-- Table structure for `lin_permission`
-- ----------------------------
DROP TABLE IF EXISTS `lin_permission`;
CREATE TABLE `lin_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL COMMENT '权限名称，例如：访问首页',
  `module` varchar(50) NOT NULL COMMENT '权限所属模块，例如：人员管理',
  `mount` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0：关闭 1：开启',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of lin_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `lin_user`
-- ----------------------------
DROP TABLE IF EXISTS `lin_user`;
CREATE TABLE `lin_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(24) NOT NULL COMMENT '用户名，唯一',
  `nickname` varchar(24) DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像url',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_del` (`username`,`delete_time`),
  UNIQUE KEY `email_del` (`email`,`delete_time`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of lin_user
-- ----------------------------
INSERT INTO `lin_user` VALUES ('1', 'root', 'root', null, null, '2023-11-14 09:01:49.991', '2023-11-14 09:01:49.991', null, '0');

-- ----------------------------
-- Table structure for `lin_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `lin_user_group`;
CREATE TABLE `lin_user_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL COMMENT '用户id',
  `group_id` int(10) unsigned NOT NULL COMMENT '分组id',
  PRIMARY KEY (`id`),
  KEY `user_id_group_id` (`user_id`,`group_id`) USING BTREE COMMENT '联合索引'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of lin_user_group
-- ----------------------------
INSERT INTO `lin_user_group` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for `lin_user_identity`
-- ----------------------------
DROP TABLE IF EXISTS `lin_user_identity`;
CREATE TABLE `lin_user_identity` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL COMMENT '用户id',
  `identity_type` varchar(100) NOT NULL,
  `identifier` varchar(100) DEFAULT NULL,
  `credential` varchar(100) DEFAULT NULL,
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of lin_user_identity
-- ----------------------------
INSERT INTO `lin_user_identity` VALUES ('1', '1', 'USERNAME_PASSWORD', 'root', 'pbkdf2sha256:64000:18:24:n:yUnDokcNRbwILZllmUOItIyo9MnI00QW:6ZcPf+sfzyoygOU8h/GSoirF', '2023-11-14 09:01:49.995', '2023-11-14 09:01:49.995', null, '0');

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_no` varchar(20) DEFAULT NULL,
  `user_id` int(10) unsigned DEFAULT NULL COMMENT 'user表外键',
  `total_price` decimal(10,2) DEFAULT '0.00',
  `total_count` int(11) unsigned DEFAULT '0',
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `expired_time` datetime(3) DEFAULT NULL,
  `placed_time` datetime(3) DEFAULT NULL,
  `snap_img` varchar(255) DEFAULT NULL,
  `snap_title` varchar(255) DEFAULT NULL,
  `snap_items` json DEFAULT NULL,
  `snap_address` json DEFAULT NULL,
  `prepay_id` varchar(255) DEFAULT NULL,
  `final_total_price` decimal(10,2) DEFAULT NULL,
  `status` tinyint(3) unsigned DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for `sale_explain`
-- ----------------------------
DROP TABLE IF EXISTS `sale_explain`;
CREATE TABLE `sale_explain` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fixed` tinyint(3) unsigned DEFAULT '0',
  `text` varchar(100) NOT NULL,
  `spu_id` int(11) DEFAULT NULL,
  `index` int(10) unsigned DEFAULT NULL,
  `replace_id` int(10) unsigned DEFAULT NULL,
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sale_explain
-- ----------------------------
INSERT INTO `sale_explain` VALUES ('1', '1', '发货地：上海', null, '1', null, '2019-08-17 04:59:44.000', null, '2019-08-17 04:59:44.000');
INSERT INTO `sale_explain` VALUES ('2', '1', '物流：顺丰', null, '2', null, '2019-08-17 04:59:44.000', null, '2019-08-17 05:00:27.000');
INSERT INTO `sale_explain` VALUES ('3', '1', '发货时间：七个工作日', null, '3', null, '2019-08-17 04:59:44.000', null, '2019-08-17 05:00:29.000');
INSERT INTO `sale_explain` VALUES ('4', '1', '售后：不支持7天无理由退货', null, '4', null, '2019-08-17 04:59:44.000', null, '2019-09-10 12:17:04.000');

-- ----------------------------
-- Table structure for `sku`
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `price` decimal(10,2) unsigned NOT NULL,
  `discount_price` decimal(10,2) unsigned DEFAULT NULL,
  `online` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `img` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `spu_id` int(10) unsigned NOT NULL,
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `specs` json DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `stock` int(10) unsigned NOT NULL DEFAULT '0',
  `category_id` int(10) unsigned DEFAULT NULL,
  `root_category_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sku
-- ----------------------------
INSERT INTO `sku` VALUES ('1', '13.80', '11.10', '1', null, '青峰·7英寸', '1', '2019-07-16 13:14:26.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"青蓝色\", \"key_id\": 1, \"value_id\": 1}, {\"key\": \"尺寸\", \"value\": \"7英寸\", \"key_id\": 2, \"value_id\": 5}]', '1$1-1#2-5', '99', '28');
INSERT INTO `sku` VALUES ('2', '77.76', null, '1', null, '金属灰·七龙珠', '2', '2019-07-18 13:11:07.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"金属灰\", \"key_id\": 1, \"value_id\": 45}, {\"key\": \"图案\", \"value\": \"七龙珠\", \"key_id\": 3, \"value_id\": 9}, {\"key\": \"尺码\", \"value\": \"小号 S\", \"key_id\": 4, \"value_id\": 14}]', '2$1-45#3-9#4-14', '5', '17');
INSERT INTO `sku` VALUES ('3', '66.00', '59.00', '1', null, '青芒色·灌篮高手', '2', '2019-07-18 13:11:11.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"青芒色\", \"key_id\": 1, \"value_id\": 42}, {\"key\": \"图案\", \"value\": \"灌篮高手\", \"key_id\": 3, \"value_id\": 10}, {\"key\": \"尺码\", \"value\": \"中号 M\", \"key_id\": 4, \"value_id\": 15}]', '2$1-42#3-10#4-15', '999', '17');
INSERT INTO `sku` VALUES ('4', '88.00', null, '1', null, '青芒色·圣斗士', '2', '2019-07-18 13:11:13.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"青芒色\", \"key_id\": 1, \"value_id\": 42}, {\"key\": \"图案\", \"value\": \"圣斗士\", \"key_id\": 3, \"value_id\": 11}, {\"key\": \"尺码\", \"value\": \"大号  L\", \"key_id\": 4, \"value_id\": 16}]', '2$1-42#3-11#4-16', '8', '17');
INSERT INTO `sku` VALUES ('5', '77.00', '59.00', '1', null, '橘黄色·七龙珠', '2', '2019-07-18 13:11:16.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"橘黄色\", \"key_id\": 1, \"value_id\": 44}, {\"key\": \"图案\", \"value\": \"七龙珠\", \"key_id\": 3, \"value_id\": 9}, {\"key\": \"尺码\", \"value\": \"小号 S\", \"key_id\": 4, \"value_id\": 14}]', '2$1-44#3-9#4-14', '7', '17');
INSERT INTO `sku` VALUES ('6', '29.99', '19.99', '1', null, '黑色', '3', '2019-07-30 02:26:25.000', '2020-02-25 15:07:01.442', '2019-09-14 06:43:46.000', '[{\"key\": \"颜色\", \"value\": \"黑色\", \"key_id\": 1, \"value_id\": 12}]', '3$1-12', '2', '32');
INSERT INTO `sku` VALUES ('7', '32.00', null, '1', null, '银色', '3', '2019-07-30 02:27:39.000', '2020-02-25 15:07:01.442', '2019-09-14 06:43:49.000', '[{\"key\": \"颜色\", \"value\": \"银色\", \"key_id\": 1, \"value_id\": 18}]', '3$1-18', '3', '32');
INSERT INTO `sku` VALUES ('8', '49.70', '37.70', '1', null, '金色', '3', '2019-07-30 02:27:39.000', '2020-02-25 15:07:01.442', '2019-09-14 06:43:51.000', '[{\"key\": \"颜色\", \"value\": \"金色\", \"key_id\": 1, \"value_id\": 19}]', '3$1-19', '0', '32');
INSERT INTO `sku` VALUES ('9', '16.60', null, '1', null, '白羽·4.3英寸', '1', '2019-07-30 02:36:26.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"深白色\", \"key_id\": 1, \"value_id\": 3}, {\"key\": \"尺寸\", \"value\": \"4.3英寸\", \"key_id\": 2, \"value_id\": 7}]', '1$1-3#2-7', '0', '28');
INSERT INTO `sku` VALUES ('10', '78.00', null, '1', null, '桌旗 130 cm', '4', '2019-07-30 06:46:56.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色规格\", \"value\": \"桌旗 30x 100 cm\", \"key_id\": 5, \"value_id\": 20}]', '4$5-20', '665', '26');
INSERT INTO `sku` VALUES ('11', '128.00', null, '1', null, '桌布 140x 360 cm', '4', '2019-07-30 06:52:40.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色规格\", \"value\": \"桌布 140x 360 cm\", \"key_id\": 5, \"value_id\": 21}]', '4$5-21', '555', '26');
INSERT INTO `sku` VALUES ('12', '72.00', null, '1', null, '桌旗 30x 220 cm', '4', '2019-07-30 06:52:40.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色规格\", \"value\": \"桌旗 30x 220 cm\", \"key_id\": 5, \"value_id\": 22}]', '4$5-22', '556', '26');
INSERT INTO `sku` VALUES ('13', '188.00', null, '1', null, '桌布 160x 330 cm', '4', '2019-07-30 06:52:40.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色规格\", \"value\": \"桌布 160x 330 cm\", \"key_id\": 5, \"value_id\": 23}]', '4$5-23', '555', '26');
INSERT INTO `sku` VALUES ('14', '77.00', null, '1', null, '飞行员墨镜（阳光橙）', '11', '2019-08-07 22:53:15.000', '2020-02-25 15:07:01.442', null, null, null, '331', '36');
INSERT INTO `sku` VALUES ('15', '0.20', null, '1', null, '测试数据', '12', '2019-08-26 01:01:35.000', '2020-02-25 15:07:01.442', null, '[]', '12$', '942', '38');
INSERT INTO `sku` VALUES ('16', '72.00', '68.00', '1', null, '碳素墨水', '10', '2019-08-28 01:25:41.000', '2020-02-25 15:07:01.442', null, null, null, '71', '32');
INSERT INTO `sku` VALUES ('17', '297.00', '236.00', '1', null, '爱丽丝Alisia束带（黑色）', '13', '2019-08-28 01:25:41.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"黑色\", \"key_id\": 1, \"value_id\": 12}]', '13$1-12', '71', '39');
INSERT INTO `sku` VALUES ('18', '297.00', '236.00', '1', null, '爱丽丝Alisia束带（棕色）', '13', '2019-08-28 01:25:41.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"棕色\", \"key_id\": 1, \"value_id\": 24}]', '13$1-24', '71', '39');
INSERT INTO `sku` VALUES ('19', '297.00', '236.00', '1', null, '爱丽丝Alisia束带  （咖色）', '13', '2019-08-28 01:25:41.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"咖色\", \"key_id\": 1, \"value_id\": 25}]', '13$1-25', '71', '39');
INSERT INTO `sku` VALUES ('20', '19.90', null, '1', null, 'Decend Mini小夹子  （橙色）', '14', '2019-08-28 01:25:41.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"橙色\", \"key_id\": 1, \"value_id\": 27}]', '14$1-27', '71', '20');
INSERT INTO `sku` VALUES ('21', '19.90', null, '1', null, 'Decend Mini小夹子  （红色）', '14', '2019-08-28 01:25:41.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"红色\", \"key_id\": 1, \"value_id\": 26}]', '14$1-26', '71', '20');
INSERT INTO `sku` VALUES ('22', '19.90', null, '1', null, 'Decend Mini小夹子   （金色）', '14', '2019-08-28 01:25:41.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"金色\", \"key_id\": 1, \"value_id\": 19}]', '14$1-19', '71', '20');
INSERT INTO `sku` VALUES ('23', '24.00', '19.90', '1', null, '多彩别针、回形针 Mini （金色）一盒30个', '15', '2019-09-10 02:05:48.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"金色\", \"key_id\": 1, \"value_id\": 19}, {\"key\": \"数量\", \"value\": \"一盒30个\", \"key_id\": 6, \"value_id\": 28}]', '15$1-19#6-28', '71', '32');
INSERT INTO `sku` VALUES ('24', '24.00', '19.90', '1', null, '多彩别针、回形针 Mini （银色）一盒30个', '15', '2019-09-10 02:05:48.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"银色\", \"key_id\": 1, \"value_id\": 18}, {\"key\": \"数量\", \"value\": \"一盒30个\", \"key_id\": 6, \"value_id\": 28}]', '15$1-18#6-28', '71', '32');
INSERT INTO `sku` VALUES ('25', '24.00', '19.90', '1', null, '多彩别针、回形针 Mini （黑色）一盒30个', '15', '2019-09-10 02:05:48.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"黑色\", \"key_id\": 1, \"value_id\": 12}, {\"key\": \"数量\", \"value\": \"一盒30个\", \"key_id\": 6, \"value_id\": 28}]', '15$1-12#6-28', '71', '32');
INSERT INTO `sku` VALUES ('26', '32.00', '24.00', '1', null, '多彩别针、回形针 Mini （黑色）一盒50个', '15', '2019-09-10 02:05:48.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"黑色\", \"key_id\": 1, \"value_id\": 12}, {\"key\": \"数量\", \"value\": \"一盒50个\", \"key_id\": 6, \"value_id\": 29}]', '15$1-12#6-29', '71', '32');
INSERT INTO `sku` VALUES ('27', '1799.00', null, '1', null, '风袖说 Sleeven牛仔 ', '6', '2019-09-10 02:05:48.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"黑色\", \"key_id\": 1, \"value_id\": 12}, {\"key\": \"尺码\", \"value\": \"小号 S\", \"key_id\": 4, \"value_id\": 14}]', '6$1-12#4-14', '70', '14');
INSERT INTO `sku` VALUES ('28', '1799.00', null, '1', null, '风袖说 Sleeve牛仔 ', '6', '2019-09-10 02:05:48.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"黑色\", \"key_id\": 1, \"value_id\": 12}, {\"key\": \"尺码\", \"value\": \"中号 M\", \"key_id\": 4, \"value_id\": 15}]', '6$1-12#4-15', '70', '14');
INSERT INTO `sku` VALUES ('29', '1799.00', null, '1', null, '风袖说 Sleeve牛仔 ', '6', '2019-09-10 02:05:48.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"黑色\", \"key_id\": 1, \"value_id\": 12}, {\"key\": \"尺码\", \"value\": \"中号 L\", \"key_id\": 4, \"value_id\": 16}]', '6$1-12#4-16', '70', '14');
INSERT INTO `sku` VALUES ('30', '1399.00', null, '1', null, '双色百褶裙（棕色）', '23', '2019-09-12 22:42:33.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"棕色\", \"key_id\": 1, \"value_id\": 24}, {\"key\": \"尺码\", \"value\": \"小号 S\", \"key_id\": 4, \"value_id\": 14}]', '23$1-24#4-14', '69', '15');
INSERT INTO `sku` VALUES ('31', '1399.00', null, '1', null, '双色百褶裙（鹅暖青）', '23', '2019-09-14 01:44:49.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"鹅暖青\", \"key_id\": 1, \"value_id\": 30}, {\"key\": \"尺码\", \"value\": \"中号 M\", \"key_id\": 4, \"value_id\": 15}]', '23$1-30#4-15', '7', '15');
INSERT INTO `sku` VALUES ('32', '1399.00', null, '1', null, '双色百褶裙（鹅暖青 小号）', '23', '2019-09-14 01:46:06.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"鹅暖青\", \"key_id\": 1, \"value_id\": 30}, {\"key\": \"尺码\", \"value\": \"小号 S\", \"key_id\": 4, \"value_id\": 14}]', '23$1-30#4-14', '8', '15');
INSERT INTO `sku` VALUES ('33', '2799.00', '1799.00', '1', null, '秋冬新款驼色大衣', '24', '2019-09-14 02:17:15.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"驼色\", \"key_id\": 1, \"value_id\": 31}, {\"key\": \"尺码\", \"value\": \"小号 S\", \"key_id\": 4, \"value_id\": 14}]', '24$1-31#4-14', '70', '16');
INSERT INTO `sku` VALUES ('34', '2699.00', '1799.00', '1', null, '秋冬新款驼色大衣（驼色 M号）', '24', '2019-09-14 02:19:39.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"驼色\", \"key_id\": 1, \"value_id\": 31}, {\"key\": \"尺码\", \"value\": \"中号 M\", \"key_id\": 4, \"value_id\": 15}]', '24$1-31#4-15', '70', '16');
INSERT INTO `sku` VALUES ('35', '2999.00', '1699.00', '1', null, '秋冬新款驼色大衣 （L号）', '24', '2019-09-14 02:21:02.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"驼色\", \"key_id\": 1, \"value_id\": 31}, {\"key\": \"尺码\", \"value\": \"大号  L\", \"key_id\": 4, \"value_id\": 16}]', '24$1-31#4-16', '47', '16');
INSERT INTO `sku` VALUES ('36', '3999.00', null, '1', null, '复古双色沙发（藏青色）', '25', '2019-09-14 02:42:50.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"藏青色\", \"key_id\": 1, \"value_id\": 2}, {\"key\": \"双色沙发尺寸（非标）\", \"value\": \"1.5米 x 1米\", \"key_id\": 7, \"value_id\": 32}]', '25$1-2#7-32', '87', '35');
INSERT INTO `sku` VALUES ('37', '3999.00', null, '1', null, '复古双色沙发 (米黄色）', '25', '2019-09-14 02:43:47.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"米黄色\", \"key_id\": 1, \"value_id\": 35}, {\"key\": \"双色沙发尺寸（非标）\", \"value\": \"2米 x 1米\", \"key_id\": 7, \"value_id\": 33}]', '25$1-35#7-33', '56', '35');
INSERT INTO `sku` VALUES ('38', '4799.00', '4299.00', '1', null, 'SemiConer柔质沙发（长款）', '26', '2019-09-14 05:44:45.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"金色\", \"key_id\": 1, \"value_id\": 27}, {\"key\": \"双色沙发尺寸（非标）\", \"value\": \"2米 x 1米\", \"key_id\": 7, \"value_id\": 33}]', '26$1-27#7-33', '7', '35');
INSERT INTO `sku` VALUES ('39', '4799.00', null, '1', null, 'SemiConer柔质沙发 （L型）', '26', '2019-09-14 05:45:44.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"金色\", \"key_id\": 1, \"value_id\": 27}, {\"key\": \"双色沙发尺寸（非标）\", \"value\": \"L型 2米 + 0.8米\", \"key_id\": 7, \"value_id\": 34}]', '26$1-27#7-34', '7', '35');
INSERT INTO `sku` VALUES ('40', '4799.00', '4299.00', '1', null, 'SemiConer柔质沙发（短款）', '26', '2019-09-14 05:46:29.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"金色\", \"key_id\": 1, \"value_id\": 27}, {\"key\": \"双色沙发尺寸（非标）\", \"value\": \"1.5米 x 1米\", \"key_id\": 7, \"value_id\": 32}]', '26$1-27#7-32', '6', '35');
INSERT INTO `sku` VALUES ('41', '1399.00', null, '1', null, 'Mier双色靠椅（海蓝色）', '27', '2019-09-14 06:11:11.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"海蓝色\", \"key_id\": 1, \"value_id\": 36}]', '27$1-36', '67', '35');
INSERT INTO `sku` VALUES ('42', '1399.00', null, '1', null, 'Mier双色靠椅 （象牙白）', '27', '2019-09-14 06:11:50.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"象牙白\", \"key_id\": 1, \"value_id\": 37}]', '27$1-37', '13', '35');
INSERT INTO `sku` VALUES ('43', '999.00', null, '1', null, 'Ins复古金色落地灯（落地灯）', '28', '2019-09-14 06:25:46.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"金色\", \"key_id\": 1, \"value_id\": 27}, {\"key\": \"台灯高低\", \"value\": \"落地灯\", \"key_id\": 8, \"value_id\": 38}]', '28$1-27#8-38', '19', '23');
INSERT INTO `sku` VALUES ('44', '999.00', null, '1', null, 'Ins复古金色落地灯 （台灯）', '28', '2019-09-14 06:26:25.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"金色\", \"key_id\": 1, \"value_id\": 27}, {\"key\": \"台灯高低\", \"value\": \"台灯\", \"key_id\": 8, \"value_id\": 39}]', '28$1-27#8-39', '19', '23');
INSERT INTO `sku` VALUES ('45', '22.00', '17.00', '1', null, '抹茶小橡皮 （一盒30个）', '3', '2019-09-14 06:36:02.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"抹茶绿\", \"key_id\": 1, \"value_id\": 40}, {\"key\": \"数量\", \"value\": \"一盒30个\", \"key_id\": 6, \"value_id\": 28}]', '3$1-40#6-28', '120', '32');
INSERT INTO `sku` VALUES ('46', '22.00', '20.00', '1', null, '抹茶小橡皮（一盒50个）', '3', '2019-09-14 06:40:00.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"抹茶绿\", \"key_id\": 1, \"value_id\": 40}, {\"key\": \"数量\", \"value\": \"一盒50个\", \"key_id\": 6, \"value_id\": 29}]', '3$1-40#6-29', '8', '32');
INSERT INTO `sku` VALUES ('47', '29.00', '20.00', '1', null, 'ins复古印花NoteBook （一盒30）', '8', '2019-09-14 06:51:47.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"青草绿\", \"key_id\": 1, \"value_id\": 41}, {\"key\": \"数量\", \"value\": \"一盒30个\", \"key_id\": 6, \"value_id\": 28}]', '8$1-41#6-28', '19', '32');
INSERT INTO `sku` VALUES ('48', '29.00', '27.00', '1', null, 'ins复古印花NoteBook （一盒50个）', '8', '2019-09-14 06:55:14.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"青草绿\", \"key_id\": 1, \"value_id\": 41}, {\"key\": \"数量\", \"value\": \"一盒50个\", \"key_id\": 6, \"value_id\": 29}]', '8$1-41#6-29', '89', '32');
INSERT INTO `sku` VALUES ('49', '379.00', '279.00', '1', null, '七色针织衫（米黄）', '5', '2019-09-14 07:08:06.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"米黄色\", \"key_id\": 1, \"value_id\": 35}, {\"key\": \"尺码\", \"value\": \"中号 M\", \"key_id\": 4, \"value_id\": 15}]', '5$1-35#4-15', '7', '14');
INSERT INTO `sku` VALUES ('50', '349.00', '279.00', '1', null, '七色针织衫（白色）', '5', '2019-09-14 07:09:31.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"象牙白\", \"key_id\": 1, \"value_id\": 37}, {\"key\": \"尺码\", \"value\": \"小号 S\", \"key_id\": 4, \"value_id\": 14}]', '5$1-37#4-14', '60', '14');
INSERT INTO `sku` VALUES ('51', '349.00', '279.00', '1', null, '七色针织衫（蓝色）', '5', '2019-09-14 07:12:13.000', '2020-02-25 15:07:01.442', null, '[{\"key\": \"颜色\", \"value\": \"青蓝色\", \"key_id\": 1, \"value_id\": 1}, {\"key\": \"尺码\", \"value\": \"中号 M\", \"key_id\": 4, \"value_id\": 15}]', '5$1-1#4-15', '60', '14');

-- ----------------------------
-- Table structure for `sku_spec`
-- ----------------------------
DROP TABLE IF EXISTS `sku_spec`;
CREATE TABLE `sku_spec` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `spu_id` int(10) unsigned NOT NULL,
  `sku_id` int(10) unsigned NOT NULL,
  `key_id` int(10) unsigned NOT NULL,
  `value_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sku_spec
-- ----------------------------
INSERT INTO `sku_spec` VALUES ('5', '23', '2728', '1', '25');
INSERT INTO `sku_spec` VALUES ('6', '23', '2728', '4', '14');
INSERT INTO `sku_spec` VALUES ('7', '23', '2729', '1', '1');
INSERT INTO `sku_spec` VALUES ('8', '23', '2729', '4', '14');
INSERT INTO `sku_spec` VALUES ('9', '23', '2730', '1', '24');
INSERT INTO `sku_spec` VALUES ('10', '23', '2730', '4', '14');
INSERT INTO `sku_spec` VALUES ('11', '23', '30', '1', '24');
INSERT INTO `sku_spec` VALUES ('12', '23', '30', '4', '14');
INSERT INTO `sku_spec` VALUES ('13', '23', '31', '1', '30');
INSERT INTO `sku_spec` VALUES ('14', '23', '31', '4', '15');
INSERT INTO `sku_spec` VALUES ('15', '23', '32', '1', '30');
INSERT INTO `sku_spec` VALUES ('16', '23', '32', '4', '14');
INSERT INTO `sku_spec` VALUES ('23', '25', '36', '1', '2');
INSERT INTO `sku_spec` VALUES ('24', '25', '36', '7', '32');
INSERT INTO `sku_spec` VALUES ('25', '25', '37', '1', '35');
INSERT INTO `sku_spec` VALUES ('26', '25', '37', '7', '33');
INSERT INTO `sku_spec` VALUES ('27', '26', '38', '1', '27');
INSERT INTO `sku_spec` VALUES ('28', '26', '38', '7', '33');
INSERT INTO `sku_spec` VALUES ('29', '26', '39', '1', '27');
INSERT INTO `sku_spec` VALUES ('30', '26', '39', '7', '34');
INSERT INTO `sku_spec` VALUES ('31', '26', '40', '1', '27');
INSERT INTO `sku_spec` VALUES ('32', '26', '40', '7', '32');
INSERT INTO `sku_spec` VALUES ('33', '27', '41', '1', '36');
INSERT INTO `sku_spec` VALUES ('34', '27', '42', '1', '37');
INSERT INTO `sku_spec` VALUES ('35', '28', '43', '1', '27');
INSERT INTO `sku_spec` VALUES ('36', '28', '43', '8', '38');
INSERT INTO `sku_spec` VALUES ('37', '28', '44', '1', '27');
INSERT INTO `sku_spec` VALUES ('38', '28', '44', '8', '39');
INSERT INTO `sku_spec` VALUES ('41', '3', '45', '1', '40');
INSERT INTO `sku_spec` VALUES ('42', '3', '45', '6', '28');
INSERT INTO `sku_spec` VALUES ('43', '3', '46', '1', '40');
INSERT INTO `sku_spec` VALUES ('44', '3', '46', '6', '29');
INSERT INTO `sku_spec` VALUES ('47', '8', '47', '1', '41');
INSERT INTO `sku_spec` VALUES ('48', '8', '47', '6', '28');
INSERT INTO `sku_spec` VALUES ('49', '8', '48', '1', '41');
INSERT INTO `sku_spec` VALUES ('50', '8', '48', '6', '29');
INSERT INTO `sku_spec` VALUES ('51', '5', '49', '1', '35');
INSERT INTO `sku_spec` VALUES ('52', '5', '49', '4', '15');
INSERT INTO `sku_spec` VALUES ('53', '5', '50', '1', '37');
INSERT INTO `sku_spec` VALUES ('54', '5', '50', '4', '14');
INSERT INTO `sku_spec` VALUES ('55', '5', '51', '1', '1');
INSERT INTO `sku_spec` VALUES ('56', '5', '51', '4', '15');
INSERT INTO `sku_spec` VALUES ('57', '24', '34', '1', '31');
INSERT INTO `sku_spec` VALUES ('58', '24', '34', '4', '15');
INSERT INTO `sku_spec` VALUES ('59', '24', '35', '1', '31');
INSERT INTO `sku_spec` VALUES ('60', '24', '35', '4', '16');
INSERT INTO `sku_spec` VALUES ('61', '24', '33', '1', '31');
INSERT INTO `sku_spec` VALUES ('62', '24', '33', '4', '14');
INSERT INTO `sku_spec` VALUES ('78', '2', '5', '1', '43');
INSERT INTO `sku_spec` VALUES ('79', '2', '5', '3', '9');
INSERT INTO `sku_spec` VALUES ('80', '2', '5', '4', '14');
INSERT INTO `sku_spec` VALUES ('81', '2', '3', '1', '42');
INSERT INTO `sku_spec` VALUES ('82', '2', '3', '3', '10');
INSERT INTO `sku_spec` VALUES ('83', '2', '3', '4', '15');
INSERT INTO `sku_spec` VALUES ('84', '2', '2', '1', '3');
INSERT INTO `sku_spec` VALUES ('85', '2', '2', '3', '9');
INSERT INTO `sku_spec` VALUES ('86', '2', '2', '4', '14');
INSERT INTO `sku_spec` VALUES ('87', '2', '4', '1', '42');
INSERT INTO `sku_spec` VALUES ('88', '2', '4', '3', '11');
INSERT INTO `sku_spec` VALUES ('89', '2', '4', '4', '16');

-- ----------------------------
-- Table structure for `spec_key`
-- ----------------------------
DROP TABLE IF EXISTS `spec_key`;
CREATE TABLE `spec_key` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `unit` varchar(30) DEFAULT NULL,
  `standard` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spec_key
-- ----------------------------
INSERT INTO `spec_key` VALUES ('1', '颜色', null, '1', '2019-07-15 15:33:18', '2019-07-15 15:33:18', null, null);
INSERT INTO `spec_key` VALUES ('2', '尺寸', '英寸', '0', '2019-07-15 15:48:52', '2019-07-16 13:02:44', null, null);
INSERT INTO `spec_key` VALUES ('3', '图案', null, '0', '2019-07-17 08:21:42', '2019-07-17 08:21:42', null, null);
INSERT INTO `spec_key` VALUES ('4', '尺码', null, '1', '2019-07-17 08:24:40', '2019-07-17 08:24:47', null, null);
INSERT INTO `spec_key` VALUES ('5', '颜色与规格', null, '0', '2019-07-30 06:39:27', '2019-07-30 06:39:27', null, null);
INSERT INTO `spec_key` VALUES ('6', '数量', '个', '0', '2019-09-10 02:13:11', '2019-09-10 02:13:11', null, null);
INSERT INTO `spec_key` VALUES ('7', '双色沙发尺寸（非标）', '米', '0', '2019-09-14 02:32:05', '2019-09-14 02:32:05', null, '');
INSERT INTO `spec_key` VALUES ('8', '台灯高低', '', '0', '2019-09-14 03:28:00', '2019-09-14 03:28:00', null, '');

-- ----------------------------
-- Table structure for `spec_value`
-- ----------------------------
DROP TABLE IF EXISTS `spec_value`;
CREATE TABLE `spec_value` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `value` varchar(255) NOT NULL,
  `spec_id` int(10) unsigned NOT NULL,
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `extend` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spec_value
-- ----------------------------
INSERT INTO `spec_value` VALUES ('1', '青蓝色', '1', '2019-07-16 12:52:36.000', '2019-07-16 12:52:36.000', null, null);
INSERT INTO `spec_value` VALUES ('2', '藏青色', '1', '2019-07-16 12:55:45.000', '2019-07-16 12:55:45.000', null, null);
INSERT INTO `spec_value` VALUES ('3', '深白色', '1', '2019-07-16 12:56:33.000', '2019-07-16 12:56:33.000', null, null);
INSERT INTO `spec_value` VALUES ('4', '少女粉', '1', '2019-07-16 12:57:01.000', '2019-07-16 12:57:01.000', null, null);
INSERT INTO `spec_value` VALUES ('5', '7英寸', '2', '2019-07-16 12:58:47.000', '2019-07-16 12:58:47.000', null, null);
INSERT INTO `spec_value` VALUES ('6', '5英寸', '2', '2019-07-16 12:58:56.000', '2019-07-16 12:58:56.000', null, null);
INSERT INTO `spec_value` VALUES ('7', '4.3英寸', '2', '2019-07-16 12:59:12.000', '2019-07-16 12:59:12.000', null, null);
INSERT INTO `spec_value` VALUES ('8', '10英寸', '2', '2019-07-16 12:59:23.000', '2019-07-16 12:59:23.000', null, null);
INSERT INTO `spec_value` VALUES ('9', '七龙珠', '3', '2019-07-17 08:22:05.000', '2019-07-17 08:22:05.000', null, null);
INSERT INTO `spec_value` VALUES ('10', '灌篮高手', '3', '2019-07-17 08:22:15.000', '2019-07-17 08:22:15.000', null, null);
INSERT INTO `spec_value` VALUES ('11', '圣斗士', '3', '2019-07-17 08:22:24.000', '2019-07-17 08:22:24.000', null, null);
INSERT INTO `spec_value` VALUES ('12', '黑色', '1', '2019-07-17 08:22:42.000', '2019-07-17 08:22:42.000', null, null);
INSERT INTO `spec_value` VALUES ('13', '墨绿色', '1', '2019-07-17 08:25:04.000', '2019-07-17 08:25:04.000', null, null);
INSERT INTO `spec_value` VALUES ('14', '小号 S', '4', '2019-07-17 08:25:34.000', '2019-09-10 15:47:36.000', null, null);
INSERT INTO `spec_value` VALUES ('15', '中号 M', '4', '2019-07-17 08:25:44.000', '2019-09-10 15:47:40.000', null, null);
INSERT INTO `spec_value` VALUES ('16', '大号  L', '4', '2019-07-17 08:25:50.000', '2019-09-10 15:47:45.000', null, null);
INSERT INTO `spec_value` VALUES ('17', '加大 XL', '4', '2019-07-17 08:26:03.000', '2019-09-10 15:47:50.000', null, null);
INSERT INTO `spec_value` VALUES ('18', '银色', '1', '2019-07-30 02:23:10.000', '2019-07-30 02:23:10.000', null, null);
INSERT INTO `spec_value` VALUES ('19', '金色', '1', '2019-07-30 02:23:21.000', '2019-07-30 02:23:21.000', null, null);
INSERT INTO `spec_value` VALUES ('20', '桌旗 30x 100 cm', '5', '2019-07-30 06:40:26.000', '2019-07-30 06:43:19.000', null, null);
INSERT INTO `spec_value` VALUES ('21', '桌旗 30x 220 cm', '5', '2019-07-30 06:42:16.000', '2019-07-30 06:43:22.000', null, null);
INSERT INTO `spec_value` VALUES ('22', '桌布 140x 360 cm', '5', '2019-07-30 06:42:48.000', '2019-07-30 06:43:27.000', null, null);
INSERT INTO `spec_value` VALUES ('23', '桌布 160x 330 cm', '5', '2019-07-30 06:43:16.000', '2019-07-30 06:43:29.000', null, null);
INSERT INTO `spec_value` VALUES ('24', '棕色', '1', '2019-07-30 06:43:16.000', '2019-09-07 19:22:05.000', null, null);
INSERT INTO `spec_value` VALUES ('25', '咖色', '1', '2019-07-30 06:43:16.000', '2019-09-07 19:22:08.000', null, null);
INSERT INTO `spec_value` VALUES ('26', '红色', '1', '2019-09-07 19:22:01.000', '2019-09-07 19:22:01.000', null, null);
INSERT INTO `spec_value` VALUES ('27', '金色', '1', '2019-09-07 19:22:33.000', '2019-09-07 19:22:33.000', null, null);
INSERT INTO `spec_value` VALUES ('28', '一盒30个', '6', '2019-09-10 02:13:42.000', '2019-09-10 02:13:42.000', null, null);
INSERT INTO `spec_value` VALUES ('29', '一盒50个', '6', '2019-09-10 02:13:42.000', '2019-09-10 02:13:42.000', null, null);
INSERT INTO `spec_value` VALUES ('30', '鹅暖青', '1', '2019-09-14 01:42:00.000', '2019-09-14 01:42:00.000', null, '');
INSERT INTO `spec_value` VALUES ('31', '驼色', '1', '2019-09-14 02:15:59.000', '2019-09-14 02:15:59.000', null, '');
INSERT INTO `spec_value` VALUES ('32', '1.5米 x 1米', '7', '2019-09-14 02:32:57.000', '2019-09-14 02:32:57.000', null, '');
INSERT INTO `spec_value` VALUES ('33', '2米 x 1米', '7', '2019-09-14 02:33:39.000', '2019-09-14 02:33:39.000', null, '');
INSERT INTO `spec_value` VALUES ('34', 'L型 2米 + 0.8米', '7', '2019-09-14 02:34:12.000', '2019-09-14 02:34:12.000', null, '');
INSERT INTO `spec_value` VALUES ('35', '米黄色', '1', '2019-09-14 02:40:45.000', '2019-09-14 02:40:45.000', null, '');
INSERT INTO `spec_value` VALUES ('36', '海蓝色', '1', '2019-09-14 05:50:31.000', '2019-09-14 05:50:31.000', null, '');
INSERT INTO `spec_value` VALUES ('37', '象牙白', '1', '2019-09-14 05:50:42.000', '2019-09-14 05:50:42.000', null, '');
INSERT INTO `spec_value` VALUES ('38', '落地灯', '8', '2019-09-14 06:24:15.000', '2019-09-14 06:24:15.000', null, '');
INSERT INTO `spec_value` VALUES ('39', '台灯', '8', '2019-09-14 06:24:27.000', '2019-09-14 06:24:27.000', null, '');
INSERT INTO `spec_value` VALUES ('40', '抹茶绿', '1', '2019-09-14 06:37:13.000', '2019-09-14 06:37:13.000', null, '');
INSERT INTO `spec_value` VALUES ('41', '青草绿', '1', '2019-09-14 06:53:08.000', '2019-09-14 06:53:08.000', null, '');
INSERT INTO `spec_value` VALUES ('42', '青芒色', '1', '2019-09-15 18:02:01.000', '2019-09-15 18:02:01.000', null, '');
INSERT INTO `spec_value` VALUES ('43', '粉色', '1', '2019-09-15 18:02:16.000', '2019-09-15 18:02:16.000', null, '');
INSERT INTO `spec_value` VALUES ('44', '橘黄色', '1', '2019-09-18 03:32:43.000', '2019-09-18 03:32:43.000', null, '');
INSERT INTO `spec_value` VALUES ('45', '金属灰', '1', '2019-09-18 03:32:59.000', '2019-09-18 03:32:59.000', null, '');

-- ----------------------------
-- Table structure for `spu`
-- ----------------------------
DROP TABLE IF EXISTS `spu`;
CREATE TABLE `spu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `subtitle` varchar(800) DEFAULT NULL,
  `category_id` int(10) unsigned NOT NULL,
  `root_category_id` int(11) DEFAULT NULL,
  `online` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `price` varchar(20) NOT NULL COMMENT '文本型价格，有时候SPU需要展示的是一个范围，或者自定义平均价格',
  `sketch_spec_id` int(10) unsigned DEFAULT NULL COMMENT '某种规格可以直接附加单品图片',
  `default_sku_id` int(11) DEFAULT NULL COMMENT '默认选中的sku',
  `img` varchar(255) DEFAULT NULL,
  `discount_price` varchar(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `tags` varchar(30) DEFAULT NULL,
  `is_test` tinyint(3) unsigned DEFAULT '0',
  `spu_theme_img` json DEFAULT NULL,
  `for_theme_img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spu
-- ----------------------------
INSERT INTO `spu` VALUES ('1', '青锋大碗', '大碗主要用来盛宽面，凡凡倾情推荐', '28', '27', '1', '2019-07-15 14:47:11.000', '2020-02-25 15:07:01.451', null, '12.99', '1', null, null, '11.11', null, '热卖推荐', '1', null);
INSERT INTO `spu` VALUES ('2', '林间有风自营针织衫', '秋日冬款，浪漫满屋', '12', '2', '1', '2019-07-31 08:19:24.000', '2020-02-25 15:07:01.451', null, '77.00', '1', '2', null, '62.00', null, '秋日冬款$浪漫满屋', '1', null);
INSERT INTO `spu` VALUES ('3', '抹茶小橡皮', '小作文写错了，用它擦一擦', '32', '24', '1', '2019-09-16 09:55:51.000', '2020-02-25 15:07:01.451', null, '29.99', '1', null, null, '17.00', null, '一飞推荐', '0', null);
INSERT INTO `spu` VALUES ('4', '印花桌布', '生活需要仪式感，吃饭也一样。桌旗+桌布给你绚烂的生命色彩', '26', '27', '1', '2019-07-30 06:26:33.000', '2020-02-25 15:07:01.451', null, '119.00', '5', null, null, '97.00', null, '风袖臻选', '1', null);
INSERT INTO `spu` VALUES ('5', '七色针织衫', '女朋友不给你洗衣服？没关系，每天换一件。', '14', '2', '0', '2019-07-16 14:47:11.000', '2020-02-25 15:07:01.451', null, '349', '1', null, null, '279', null, 'pedro推荐', '1', null);
INSERT INTO `spu` VALUES ('6', 'Sleeve风袖说牛仔系列', 'Sleeve风袖说当季经典款', '14', '2', '1', '2019-08-01 08:19:24.000', '2020-02-25 15:07:01.451', null, '1799', '1', '5', null, '', null, '包邮$热门', '1', null);
INSERT INTO `spu` VALUES ('8', 'Ins复古碎花NoteBook', '林间有风默默的掏出小本本，将她说的话一次不漏的记了下来。', '32', '24', '1', '2019-09-15 05:00:21.000', '2020-02-25 15:07:01.451', null, '29.99', '1', null, null, '27.8', null, '热卖推荐', '0', null);
INSERT INTO `spu` VALUES ('10', '碳素墨水', '虽然我们早已不再使用钢笔书写，但钢笔在纸上划过的笔触永远是键盘无法替代的。一只钢笔+一瓶墨水在一个安静的午后，写写内心的故事。', '32', '24', '1', '2019-09-16 09:57:15.000', '2020-02-25 15:07:01.451', null, '80.00', null, null, null, '69.00', null, '', '1', null);
INSERT INTO `spu` VALUES ('11', '飞行员墨镜', '戴起来像小李子', '36', '5', '1', '2019-08-07 22:47:05.000', '2020-02-25 15:07:01.451', null, '77.00', null, null, null, null, null, null, '1', null);
INSERT INTO `spu` VALUES ('12', '林间有风测试商品', '测试商品，可购买体验完整支付和订单流程', '38', '37', '1', '2019-08-25 19:03:03.000', '2020-02-25 15:07:01.451', null, '0.2', null, null, null, null, null, '测试数据$可支付', '0', null);
INSERT INTO `spu` VALUES ('13', '基克的聚合束带', '三色可选，加攻、加防、还能加血', '39', '5', '0', '2019-09-07 16:06:47.000', '2020-02-25 15:07:01.451', null, '279', null, null, null, null, null, null, '1', null);
INSERT INTO `spu` VALUES ('14', 'Ins 复古小夹子（Mini)', '静静的，享受时光的流逝', '32', '24', '1', '2019-09-16 09:54:47.000', '2020-02-25 15:07:01.451', null, '19.9', null, null, null, null, null, '三色可选', '1', null);
INSERT INTO `spu` VALUES ('15', '多彩别针、回形针', '每盒70个，可爱多彩', '32', '24', '1', '2019-09-16 09:55:27.000', '2020-02-25 15:07:01.451', null, '24', '1', '25', null, '19.9', null, '三色可选', '1', null);
INSERT INTO `spu` VALUES ('23', '双色牛仔裤', '秋冬新款，做一个Cool Boy', '15', '2', '1', '2019-09-16 10:26:04.000', '2020-02-25 15:07:01.451', null, '1399', '1', null, null, null, null, '', '1', null);
INSERT INTO `spu` VALUES ('24', '秋冬新款驼色大衣', '2020新款，暖暖过秋冬', '16', '2', '1', '2019-09-14 02:13:20.000', '2020-02-25 15:07:01.451', null, '2999', '1', null, null, '2699', null, '经典单色', '1', null);
INSERT INTO `spu` VALUES ('25', '复古双色沙发', '双色可选，经典青黄两色', '35', '4', '1', '2019-09-14 02:30:23.000', '2020-02-25 15:07:01.451', null, '3999', '1', null, null, null, null, '复刻经典$双色可选', '1', null);
INSERT INTO `spu` VALUES ('26', 'SemiConer柔质沙发', '窝在沙发上，一杯红酒配电影', '35', '4', '1', '2019-09-14 05:43:19.000', '2020-02-25 15:07:01.451', null, '4799', '1', null, null, '4200', null, '', '1', null);
INSERT INTO `spu` VALUES ('27', 'Mier双色靠椅', '安静的午后，一杯清茶，追忆似水年华。看清风浮动，看落日余晖', '35', '4', '1', '2019-09-09 02:26:12.000', '2020-02-25 15:07:01.451', null, '1299', '1', null, null, null, null, '', '1', null);
INSERT INTO `spu` VALUES ('28', 'Ins复古金色落地灯', 'Instagram复古台灯，就在此刻回到过去，找寻逝去的记忆', '23', '4', '1', '2019-09-14 06:19:12.000', '2020-02-25 15:07:01.451', null, '999', '8', null, null, null, null, 'Ins$复古流行', '1', null);

-- ----------------------------
-- Table structure for `spu_detail_img`
-- ----------------------------
DROP TABLE IF EXISTS `spu_detail_img`;
CREATE TABLE `spu_detail_img` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `img` varchar(255) NOT NULL,
  `spu_id` int(10) unsigned DEFAULT NULL,
  `index` int(10) unsigned NOT NULL,
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spu_detail_img
-- ----------------------------
INSERT INTO `spu_detail_img` VALUES ('24', '', '2', '1', '2019-09-18 04:50:21.313', '2020-02-25 15:07:01.456', null);
INSERT INTO `spu_detail_img` VALUES ('25', '', '2', '3', '2019-09-18 04:50:21.313', '2020-02-25 15:07:01.456', null);
INSERT INTO `spu_detail_img` VALUES ('26', '', '2', '4', '2019-09-18 04:50:21.313', '2020-02-25 15:07:01.456', null);
INSERT INTO `spu_detail_img` VALUES ('27', '', '2', '2', '2019-09-18 04:50:21.313', '2020-02-25 15:07:01.456', null);

-- ----------------------------
-- Table structure for `spu_img`
-- ----------------------------
DROP TABLE IF EXISTS `spu_img`;
CREATE TABLE `spu_img` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `img` varchar(255) DEFAULT NULL,
  `spu_id` int(10) unsigned DEFAULT NULL,
  `delete_time` datetime(3) DEFAULT NULL,
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spu_img
-- ----------------------------
INSERT INTO `spu_img` VALUES ('11', null, '11', null, '2020-02-25 15:07:01.461', '2019-08-08 03:36:56.000');
INSERT INTO `spu_img` VALUES ('15', null, '13', null, '2020-02-25 15:07:01.461', '2019-09-07 16:33:59.000');
INSERT INTO `spu_img` VALUES ('16', null, '13', null, '2020-02-25 15:07:01.461', '2019-09-07 16:33:59.000');
INSERT INTO `spu_img` VALUES ('17', null, '13', null, '2020-02-25 15:07:01.461', '2019-09-07 16:33:59.000');
INSERT INTO `spu_img` VALUES ('18', null, '13', null, '2020-02-25 15:07:01.461', '2019-09-07 16:33:59.000');
INSERT INTO `spu_img` VALUES ('19', null, '14', null, '2020-02-25 15:07:01.461', '2019-09-07 16:33:59.000');
INSERT INTO `spu_img` VALUES ('20', null, '14', null, '2020-02-25 15:07:01.461', '2019-09-07 16:33:59.000');
INSERT INTO `spu_img` VALUES ('30', null, '15', null, '2020-02-25 15:07:01.461', '2019-09-07 16:33:59.000');
INSERT INTO `spu_img` VALUES ('31', null, '15', null, '2020-02-25 15:07:01.461', '2019-09-07 16:33:59.000');
INSERT INTO `spu_img` VALUES ('41', null, '16', null, '2020-02-25 15:07:01.461', '2019-09-11 21:16:57.000');
INSERT INTO `spu_img` VALUES ('42', null, '16', null, '2020-02-25 15:07:01.461', '2019-09-11 21:16:57.000');
INSERT INTO `spu_img` VALUES ('43', null, '6', null, '2020-02-25 15:07:01.461', '2019-09-12 18:06:59.000');
INSERT INTO `spu_img` VALUES ('44', null, '6', null, '2020-02-25 15:07:01.461', '2019-09-12 18:06:59.000');
INSERT INTO `spu_img` VALUES ('88', null, '25', null, '2020-02-25 15:07:01.461', '2019-09-14 02:37:42.000');
INSERT INTO `spu_img` VALUES ('89', null, '25', null, '2020-02-25 15:07:01.461', '2019-09-14 02:37:42.000');
INSERT INTO `spu_img` VALUES ('90', null, '25', null, '2020-02-25 15:07:01.461', '2019-09-14 02:37:42.000');
INSERT INTO `spu_img` VALUES ('107', null, '3', null, '2020-02-25 15:07:01.461', '2019-09-14 06:34:25.000');
INSERT INTO `spu_img` VALUES ('108', null, '3', null, '2020-02-25 15:07:01.461', '2019-09-14 06:34:25.000');
INSERT INTO `spu_img` VALUES ('124', null, '12', null, '2020-02-25 15:07:01.461', '2019-09-14 07:24:14.000');
INSERT INTO `spu_img` VALUES ('126', null, '4', null, '2020-02-25 15:07:01.461', '2019-09-14 19:15:48.000');
INSERT INTO `spu_img` VALUES ('138', null, '1', null, '2020-02-25 15:07:01.461', '2019-09-14 23:01:57.000');
INSERT INTO `spu_img` VALUES ('139', null, '1', null, '2020-02-25 15:07:01.461', '2019-09-14 23:01:57.000');
INSERT INTO `spu_img` VALUES ('141', null, '28', null, '2020-02-25 15:07:01.461', '2019-09-14 23:06:56.000');
INSERT INTO `spu_img` VALUES ('150', null, '26', null, '2020-02-25 15:07:01.461', '2019-09-14 23:07:16.000');
INSERT INTO `spu_img` VALUES ('151', null, '5', null, '2020-02-25 15:07:01.461', '2019-09-15 04:53:29.000');
INSERT INTO `spu_img` VALUES ('152', null, '5', null, '2020-02-25 15:07:01.461', '2019-09-15 04:53:29.000');
INSERT INTO `spu_img` VALUES ('153', null, '5', null, '2020-02-25 15:07:01.461', '2019-09-15 04:53:29.000');
INSERT INTO `spu_img` VALUES ('154', null, '5', null, '2020-02-25 15:07:01.461', '2019-09-15 04:53:29.000');
INSERT INTO `spu_img` VALUES ('155', null, '5', null, '2020-02-25 15:07:01.461', '2019-09-15 04:53:29.000');
INSERT INTO `spu_img` VALUES ('161', null, '10', null, '2020-02-25 15:07:01.461', '2019-09-15 17:50:36.000');
INSERT INTO `spu_img` VALUES ('165', null, '2', null, '2020-02-25 15:07:01.461', '2019-09-15 17:57:23.000');
INSERT INTO `spu_img` VALUES ('166', null, '2', null, '2020-02-25 15:07:01.461', '2019-09-15 17:57:23.000');
INSERT INTO `spu_img` VALUES ('167', null, '2', null, '2020-02-25 15:07:01.461', '2019-09-15 17:57:23.000');
INSERT INTO `spu_img` VALUES ('172', null, '24', null, '2020-02-25 15:07:01.461', '2019-09-16 01:02:58.000');
INSERT INTO `spu_img` VALUES ('177', null, '8', null, '2020-02-25 15:07:01.461', '2019-09-16 01:03:14.000');
INSERT INTO `spu_img` VALUES ('179', null, '8', null, '2020-02-25 15:07:01.461', '2019-09-16 01:03:14.000');
INSERT INTO `spu_img` VALUES ('186', null, '23', null, '2020-02-25 15:07:01.461', '2019-09-16 01:05:21.000');
INSERT INTO `spu_img` VALUES ('187', null, '23', null, '2020-02-25 15:07:01.461', '2019-09-16 01:05:21.000');
INSERT INTO `spu_img` VALUES ('189', null, '23', null, '2020-02-25 15:07:01.461', '2019-09-16 01:05:21.000');
INSERT INTO `spu_img` VALUES ('190', null, '27', null, '2020-02-25 15:07:01.461', '2019-09-16 01:05:46.000');
INSERT INTO `spu_img` VALUES ('191', null, '10', null, '2020-02-25 15:07:01.461', '2019-09-15 17:50:36.000');

-- ----------------------------
-- Table structure for `spu_key`
-- ----------------------------
DROP TABLE IF EXISTS `spu_key`;
CREATE TABLE `spu_key` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `spu_id` int(10) unsigned NOT NULL,
  `spec_key_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spu_key
-- ----------------------------
INSERT INTO `spu_key` VALUES ('1', '3', '1');
INSERT INTO `spu_key` VALUES ('3', '2', '1');
INSERT INTO `spu_key` VALUES ('7', '1', '1');
INSERT INTO `spu_key` VALUES ('8', '1', '2');
INSERT INTO `spu_key` VALUES ('9', '15', '1');
INSERT INTO `spu_key` VALUES ('10', '15', '2');
INSERT INTO `spu_key` VALUES ('11', '16', '2');
INSERT INTO `spu_key` VALUES ('13', '16', '1');
INSERT INTO `spu_key` VALUES ('14', '23', '1');
INSERT INTO `spu_key` VALUES ('15', '23', '4');
INSERT INTO `spu_key` VALUES ('16', '24', '1');
INSERT INTO `spu_key` VALUES ('17', '24', '4');
INSERT INTO `spu_key` VALUES ('18', '25', '1');
INSERT INTO `spu_key` VALUES ('19', '25', '7');
INSERT INTO `spu_key` VALUES ('20', '26', '1');
INSERT INTO `spu_key` VALUES ('21', '26', '7');
INSERT INTO `spu_key` VALUES ('22', '27', '1');
INSERT INTO `spu_key` VALUES ('23', '28', '1');
INSERT INTO `spu_key` VALUES ('24', '28', '8');
INSERT INTO `spu_key` VALUES ('25', '3', '6');
INSERT INTO `spu_key` VALUES ('26', '8', '1');
INSERT INTO `spu_key` VALUES ('27', '8', '6');
INSERT INTO `spu_key` VALUES ('28', '5', '1');
INSERT INTO `spu_key` VALUES ('29', '5', '4');
INSERT INTO `spu_key` VALUES ('30', '2', '3');
INSERT INTO `spu_key` VALUES ('31', '2', '4');

-- ----------------------------
-- Table structure for `spu_tag`
-- ----------------------------
DROP TABLE IF EXISTS `spu_tag`;
CREATE TABLE `spu_tag` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `spu_id` int(10) unsigned NOT NULL,
  `tag_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of spu_tag
-- ----------------------------
INSERT INTO `spu_tag` VALUES ('1', '2', '5');
INSERT INTO `spu_tag` VALUES ('2', '2', '1');
INSERT INTO `spu_tag` VALUES ('3', '2', '12');
INSERT INTO `spu_tag` VALUES ('4', '12', '13');
INSERT INTO `spu_tag` VALUES ('5', '12', '14');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `information_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '测试', '1');

-- ----------------------------
-- Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL COMMENT '中文限制6个，英文限制12个，由逻辑层控制',
  `description` varchar(255) DEFAULT NULL,
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `highlight` tinyint(4) unsigned DEFAULT '0',
  `type` tinyint(3) unsigned DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', 'Sleeve', '', '2019-09-14 07:35:17.000', null, '2019-07-30 04:10:10.000', '1', '1');
INSERT INTO `tag` VALUES ('3', '椅', null, '2019-09-14 07:37:52.000', null, '2019-07-30 04:11:06.000', null, '1');
INSERT INTO `tag` VALUES ('4', '非卖品', null, '2019-08-01 18:45:52.000', null, '2019-08-01 18:45:52.000', null, '1');
INSERT INTO `tag` VALUES ('5', '测试数据', null, '2019-09-16 02:19:38.000', null, '2019-08-01 18:46:01.000', '1', '1');
INSERT INTO `tag` VALUES ('10', '江浙沪不包邮', null, '2019-09-16 02:42:58.000', null, '2019-08-01 18:49:02.000', null, '1');
INSERT INTO `tag` VALUES ('11', '百褶裙', null, '2019-09-14 07:37:52.000', null, '2019-08-01 18:50:26.000', '1', '1');
INSERT INTO `tag` VALUES ('15', '秋冬', null, '2019-09-14 07:36:43.000', null, '2019-09-12 18:06:59.000', '1', '1');
INSERT INTO `tag` VALUES ('17', '落地灯', null, '2019-09-14 07:35:17.000', null, '2019-09-14 02:13:20.000', null, '1');
INSERT INTO `tag` VALUES ('18', '复刻', null, '2019-09-16 02:43:03.000', null, '2019-09-14 02:37:42.000', null, '1');
INSERT INTO `tag` VALUES ('19', '双色可选', null, '2019-09-14 02:37:42.000', null, '2019-09-14 02:37:42.000', null, '1');
INSERT INTO `tag` VALUES ('20', 'Ins', null, '2019-09-14 06:19:12.000', null, '2019-09-14 06:19:12.000', null, '1');
INSERT INTO `tag` VALUES ('21', '复古', null, '2019-09-14 07:37:52.000', null, '2019-09-14 06:19:12.000', '1', '1');
INSERT INTO `tag` VALUES ('22', '林间有风', null, '2019-09-16 02:42:44.000', null, '2019-09-14 06:34:25.000', '1', '1');
INSERT INTO `tag` VALUES ('23', '灯', null, '2019-09-14 07:36:43.000', null, '2019-09-14 06:50:11.000', null, '1');
INSERT INTO `tag` VALUES ('25', '可支付', null, '2019-09-14 07:24:14.000', null, '2019-09-14 07:24:14.000', null, '1');
INSERT INTO `tag` VALUES ('26', '风袖臻选', null, '2019-09-16 02:36:35.000', null, '2019-09-14 19:15:48.000', '0', '1');
INSERT INTO `tag` VALUES ('30', '包邮', null, '2019-09-15 17:55:04.000', null, '2019-09-15 17:55:04.000', null, '1');

-- ----------------------------
-- Table structure for `theme`
-- ----------------------------
DROP TABLE IF EXISTS `theme`;
CREATE TABLE `theme` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(60) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `tpl_name` varchar(30) DEFAULT NULL,
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `entrance_img` varchar(255) DEFAULT NULL,
  `extend` varchar(255) DEFAULT NULL,
  `internal_top_img` varchar(255) DEFAULT NULL,
  `title_img` varchar(255) DEFAULT NULL,
  `online` tinyint(3) unsigned DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of theme
-- ----------------------------
INSERT INTO `theme` VALUES ('1', '清凉一夏，折扣季', '秋天是金色的。麦穗是金色的，秋阳是金色的。虽然冬快至，但宜人的温度总是让我们心情愉快#我们为您精选了一系列秋冬折扣商品，慢慢挑选吧~', 't-1', '2019-07-18 07:10:59.000', 'janna', '2020-02-25 15:07:01.465', null, null, null, null, null, '1');
INSERT INTO `theme` VALUES ('4', '每周上新', '风袖`每周上新`活动#每周都有一款折扣商品，每周都有适合你的唯一专属#有Ins复古风装饰；金属CD唱片夹；每周来逛逛，找到你所喜爱的东西', 't-2', '2019-07-30 00:00:14.000', null, '2020-02-25 15:07:01.465', null, null, null, null, null, '1');
INSERT INTO `theme` VALUES ('5', '风袖甄选', '甄选', 't-3', '2019-07-30 17:20:23.000', 'diana', '2020-02-25 15:07:01.465', null, null, null, null, null, '1');
INSERT INTO `theme` VALUES ('6', '时尚穿搭', '帅点才有女朋友', 't-4', '2019-08-01 02:43:18.000', 'irelia', '2020-02-25 15:07:01.465', null, null, null, null, null, '1');
INSERT INTO `theme` VALUES ('7', '热卖好评', '那一定是最好的', 't-5', '2019-08-09 07:19:37.000', 'camille', '2020-02-25 15:07:01.465', null, null, null, null, null, '1');
INSERT INTO `theme` VALUES ('8', '热门推荐', null, 't-6', '2019-09-10 11:43:06.000', 'camille', '2020-02-25 15:07:01.465', null, null, null, null, null, '1');
INSERT INTO `theme` VALUES ('10', null, null, null, '2019-10-10 16:59:43.363', null, '2019-10-10 16:59:43.363', null, null, null, null, null, '1');
INSERT INTO `theme` VALUES ('11', null, null, null, '2020-02-06 21:49:43.994', null, '2020-02-06 21:49:43.994', null, null, null, null, null, '1');

-- ----------------------------
-- Table structure for `theme_spu`
-- ----------------------------
DROP TABLE IF EXISTS `theme_spu`;
CREATE TABLE `theme_spu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `theme_id` int(10) unsigned NOT NULL,
  `spu_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of theme_spu
-- ----------------------------
INSERT INTO `theme_spu` VALUES ('22', '6', '23');
INSERT INTO `theme_spu` VALUES ('23', '6', '24');
INSERT INTO `theme_spu` VALUES ('24', '6', '5');
INSERT INTO `theme_spu` VALUES ('25', '6', '6');
INSERT INTO `theme_spu` VALUES ('39', '7', '8');
INSERT INTO `theme_spu` VALUES ('41', '7', '23');
INSERT INTO `theme_spu` VALUES ('42', '7', '24');
INSERT INTO `theme_spu` VALUES ('43', '7', '28');
INSERT INTO `theme_spu` VALUES ('44', '7', '27');
INSERT INTO `theme_spu` VALUES ('45', '7', '25');
INSERT INTO `theme_spu` VALUES ('46', '7', '13');
INSERT INTO `theme_spu` VALUES ('47', '7', '14');
INSERT INTO `theme_spu` VALUES ('48', '6', '28');
INSERT INTO `theme_spu` VALUES ('49', '6', '27');
INSERT INTO `theme_spu` VALUES ('50', '6', '26');
INSERT INTO `theme_spu` VALUES ('51', '6', '8');
INSERT INTO `theme_spu` VALUES ('54', '8', '27');
INSERT INTO `theme_spu` VALUES ('55', '8', '23');
INSERT INTO `theme_spu` VALUES ('56', '8', '6');
INSERT INTO `theme_spu` VALUES ('57', '8', '25');
INSERT INTO `theme_spu` VALUES ('58', '8', '27');
INSERT INTO `theme_spu` VALUES ('59', '8', '26');
INSERT INTO `theme_spu` VALUES ('60', '1', '25');
INSERT INTO `theme_spu` VALUES ('61', '1', '28');
INSERT INTO `theme_spu` VALUES ('62', '1', '23');
INSERT INTO `theme_spu` VALUES ('63', '1', '27');
INSERT INTO `theme_spu` VALUES ('64', '1', '8');
INSERT INTO `theme_spu` VALUES ('65', '1', '14');
INSERT INTO `theme_spu` VALUES ('66', '1', '3');
INSERT INTO `theme_spu` VALUES ('67', '1', '26');
INSERT INTO `theme_spu` VALUES ('70', '8', '8');
INSERT INTO `theme_spu` VALUES ('71', '4', '25');
INSERT INTO `theme_spu` VALUES ('75', '4', '26');
INSERT INTO `theme_spu` VALUES ('76', '4', '28');
INSERT INTO `theme_spu` VALUES ('83', '4', '27');
INSERT INTO `theme_spu` VALUES ('84', '4', '23');
INSERT INTO `theme_spu` VALUES ('85', '4', '8');
INSERT INTO `theme_spu` VALUES ('86', '4', '24');
INSERT INTO `theme_spu` VALUES ('87', '4', '13');
INSERT INTO `theme_spu` VALUES ('88', '4', '6');
INSERT INTO `theme_spu` VALUES ('89', '4', '5');
INSERT INTO `theme_spu` VALUES ('90', '4', '3');
INSERT INTO `theme_spu` VALUES ('91', '5', '23');
INSERT INTO `theme_spu` VALUES ('92', '5', '8');
INSERT INTO `theme_spu` VALUES ('93', '5', '27');
INSERT INTO `theme_spu` VALUES ('94', '5', '24');
INSERT INTO `theme_spu` VALUES ('100', '8', '28');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `openid` varchar(50) DEFAULT NULL,
  `nickname` varchar(60) DEFAULT NULL,
  `unify_uid` int(10) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  `wx_profile` json DEFAULT NULL,
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_openid` (`openid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 'ohM5r5aKq78zRwYNrTdufqUp_dD0', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `user_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `user_coupon`;
CREATE TABLE `user_coupon` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `coupon_id` int(10) unsigned NOT NULL,
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '1:未使用，2：已使用， 3：已过期',
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `order_id` int(10) unsigned DEFAULT NULL,
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_user_coupon` (`user_id`,`coupon_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_coupon
-- ----------------------------
INSERT INTO `user_coupon` VALUES ('6', '16', '4', '1', '2019-08-20 10:50:20.000', null, '2019-09-08 06:06:51.000');
INSERT INTO `user_coupon` VALUES ('8', '16', '3', '1', '2019-08-20 10:53:06.000', null, '2019-09-15 21:14:08.000');
INSERT INTO `user_coupon` VALUES ('10', '16', '7', '1', '2019-08-25 18:03:54.000', null, '2019-09-10 12:21:16.000');
INSERT INTO `user_coupon` VALUES ('11', '16', '11', '1', '2019-08-28 03:51:07.000', null, '2019-09-15 05:53:03.000');
INSERT INTO `user_coupon` VALUES ('12', '19', '3', '2', '2019-09-11 21:49:55.000', '245', '2019-09-11 21:50:16.000');
INSERT INTO `user_coupon` VALUES ('13', '19', '4', '1', '2019-09-11 21:51:30.000', null, '2019-09-11 21:51:30.000');
INSERT INTO `user_coupon` VALUES ('14', '19', '7', '1', '2019-09-11 21:51:31.000', null, '2019-09-11 21:51:31.000');
INSERT INTO `user_coupon` VALUES ('15', '19', '11', '1', '2019-09-11 21:51:32.000', null, '2019-09-11 21:51:32.000');
INSERT INTO `user_coupon` VALUES ('16', '22', '11', '1', '2019-09-11 22:14:23.000', null, '2019-09-11 22:14:23.000');
INSERT INTO `user_coupon` VALUES ('17', '21', '3', '1', '2019-09-11 22:14:25.000', null, '2019-09-11 22:14:25.000');
INSERT INTO `user_coupon` VALUES ('18', '22', '3', '2', '2019-09-11 22:14:34.000', '249', '2019-09-11 22:17:22.000');
INSERT INTO `user_coupon` VALUES ('19', '22', '4', '1', '2019-09-11 22:16:10.000', null, '2019-09-11 22:16:10.000');
INSERT INTO `user_coupon` VALUES ('20', '23', '3', '1', '2019-09-11 22:20:04.000', null, '2019-09-11 22:20:04.000');
INSERT INTO `user_coupon` VALUES ('21', '23', '11', '1', '2019-09-11 22:20:06.000', null, '2019-09-11 22:20:06.000');
INSERT INTO `user_coupon` VALUES ('22', '21', '11', '1', '2019-09-11 22:34:39.000', null, '2019-09-11 22:34:39.000');
INSERT INTO `user_coupon` VALUES ('23', '20', '11', '1', '2019-09-11 22:44:26.000', null, '2019-09-11 22:44:26.000');
INSERT INTO `user_coupon` VALUES ('24', '20', '3', '1', '2019-09-11 22:44:47.000', null, '2019-09-11 22:44:47.000');
INSERT INTO `user_coupon` VALUES ('25', '26', '3', '1', '2019-09-12 05:32:09.000', null, '2019-09-12 05:32:09.000');
INSERT INTO `user_coupon` VALUES ('26', '30', '4', '1', '2019-09-12 17:48:57.000', null, '2019-09-12 17:48:57.000');
INSERT INTO `user_coupon` VALUES ('27', '30', '7', '1', '2019-09-12 17:48:59.000', null, '2019-09-12 17:48:59.000');
INSERT INTO `user_coupon` VALUES ('28', '30', '3', '1', '2019-09-12 17:49:00.000', null, '2019-09-12 17:49:00.000');
INSERT INTO `user_coupon` VALUES ('29', '20', '4', '1', '2019-09-14 08:12:51.000', null, '2019-09-14 08:12:51.000');
INSERT INTO `user_coupon` VALUES ('30', '20', '7', '1', '2019-09-15 22:36:37.000', null, '2019-09-15 22:36:37.000');
INSERT INTO `user_coupon` VALUES ('31', '2', '3', '1', '2023-08-10 09:13:56.391', null, '2023-08-10 09:13:56.413');
