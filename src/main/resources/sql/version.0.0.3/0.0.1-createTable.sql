-- 用户权限
CREATE TABLE `user_session` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `session_id` varchar(48) NOT NULL,
  `user_id` bigint(10) unsigned DEFAULT NULL,
  `user_name` varchar(48) DEFAULT NULL,
  `access_ip` varchar(120) DEFAULT NULL,
  `access_time` datetime DEFAULT NULL,
  `is_login` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `login_date` datetime DEFAULT NULL,
  `last_url` varchar(240) DEFAULT NULL,
  `login_ip` varchar(32) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=328 DEFAULT CHARSET=utf8mb4;
