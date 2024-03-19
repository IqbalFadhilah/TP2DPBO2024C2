/*
 Navicat Premium Data Transfer

 Source Server         : db_YPMB
 Source Server Type    : MySQL
 Source Server Version : 100432 (10.4.32-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : db_mahasiswa

 Target Server Type    : MySQL
 Target Server Version : 100432 (10.4.32-MariaDB)
 File Encoding         : 65001

 Date: 19/03/2024 13:23:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mahasiswa
-- ----------------------------
DROP TABLE IF EXISTS `mahasiswa`;
CREATE TABLE `mahasiswa`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nim` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nama` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `jenis_kelamin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `agama` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mahasiswa
-- ----------------------------
INSERT INTO `mahasiswa` VALUES (1, '2203999', 'Amelia Zalfa Julianti', 'Perempuan', 'Islam');
INSERT INTO `mahasiswa` VALUES (3, '2202346', 'Muhammad Rifky Afandi', 'Laki-laki', 'Islam');
INSERT INTO `mahasiswa` VALUES (4, '2210239', 'Muhammad Hanif Abdillah', 'Laki-laki', 'Islam');
INSERT INTO `mahasiswa` VALUES (5, '2202046', 'Nurainun', 'Perempuan', 'Islam');
INSERT INTO `mahasiswa` VALUES (6, '2205101', 'Kelvin Julian Putra', 'Laki-laki', 'Islam');
INSERT INTO `mahasiswa` VALUES (7, '2200163', 'Rifanny Lysara Annastasya', 'Perempuan', 'Islam');
INSERT INTO `mahasiswa` VALUES (8, '2202869', 'Revana Faliha Salma', 'Perempuan', 'Islam');
INSERT INTO `mahasiswa` VALUES (9, '2209489', 'Rakha Dhifiargo Hariadi', 'Laki-laki', 'Islam');
INSERT INTO `mahasiswa` VALUES (10, '2203142', 'Roshan Syalwan Nurilham', 'Laki-laki', 'Islam');
INSERT INTO `mahasiswa` VALUES (11, '2200311', 'Raden Rahman Ismail', 'Laki-laki', 'Islam');
INSERT INTO `mahasiswa` VALUES (12, '2200978', 'Ratu Syahirah Khairunnisa', 'Perempuan', 'Islam');
INSERT INTO `mahasiswa` VALUES (13, '2204509', 'Muhammad Fahreza Fauzan', 'Laki-laki', 'Islam');
INSERT INTO `mahasiswa` VALUES (14, '2205027', 'Muhammad Rizki Revandi', 'Laki-laki', 'Islam');
INSERT INTO `mahasiswa` VALUES (15, '2203484', 'Arya Aydin Margono', 'Laki-laki', 'Islam');
INSERT INTO `mahasiswa` VALUES (16, '2200481', 'Marvel Ravindra Dioputra', 'Laki-laki', 'Islam');
INSERT INTO `mahasiswa` VALUES (17, '2209889', 'Muhammad Fadlul Hafiizh', 'Laki-laki', 'Islam');
INSERT INTO `mahasiswa` VALUES (18, '2206697', 'Rifa Sania', 'Perempuan', 'Islam');
INSERT INTO `mahasiswa` VALUES (19, '2207260', 'Imam Chalish Rafidhul Haque', 'Laki-laki', 'Islam');
INSERT INTO `mahasiswa` VALUES (20, '2204343', 'Meiva Labibah Putri', 'Perempuan', 'Islam');
INSERT INTO `mahasiswa` VALUES (21, '2202292', 'Muhammad Iqbal Fadhilah', 'Laki-laki', 'Islam');

SET FOREIGN_KEY_CHECKS = 1;
