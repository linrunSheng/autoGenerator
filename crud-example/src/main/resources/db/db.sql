-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.11 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

create database crud_test;
grant all privileges on crud_test.* to crud_test@'%' identified by 'crud_test';
flush privileges;
USE `crud_test`;

-- 导出  表 crud_test.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '用户名',
  `pass` varchar(32) DEFAULT NULL COMMENT '密码',
  `phone` varchar(13) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(1) DEFAULT NULL COMMENT '用户状态',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_name` (`name`) USING BTREE,
  UNIQUE KEY `uk_phone` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户';

-- 正在导出表  crud_test.t_user 的数据：~10 rows (大约)
DELETE FROM `t_user`;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`id`, `name`, `pass`, `phone`, `status`, `created`, `updated`) VALUES
	(1, '张三丰1', '', '156123456781', 1, '2018-03-24 20:04:34', '2018-03-24 20:04:37'),
	(2, '张三丰', NULL, '1111111111111', 1, NULL, '2018-03-24 23:00:46'),
	(6, '张三丰4', NULL, '4', 1, NULL, '2018-03-24 23:56:15'),
	(7, '张三丰5', NULL, '5', 1, NULL, '2018-03-24 23:56:31'),
	(8, '张三丰6', NULL, '6', 1, NULL, '2018-03-24 23:56:36'),
	(9, '张三丰7', NULL, '7', 1, NULL, '2018-03-24 23:56:41'),
	(10, '张三丰8', NULL, '8', 1, NULL, '2018-03-24 23:56:59'),
	(11, '张三丰9', NULL, '9', 1, NULL, '2018-03-24 23:57:06'),
	(12, '张三丰10', NULL, '10', 1, NULL, '2018-03-24 23:57:21'),
	(111, '张三丰11', '111', '11', 1, NULL, '2018-03-25 00:52:38');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
