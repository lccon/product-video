-- 弹幕
CREATE TABLE `barrages` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `video_id` int(11) NOT NULL COMMENT '视频id',
  `sendtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  `content` varchar(250) NOT NULL COMMENT '弹幕内容',
  `color` varchar(10) NOT NULL COMMENT '弹幕颜色',
  `offtime` int(11) NOT NULL COMMENT '播放偏移时间',
  `position` tinyint(1) NOT NULL COMMENT '0 固定弹幕 2 浮动弹幕',
  PRIMARY KEY (`id`),
  UNIQUE KEY `moments_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='弹幕表';

-- 评论
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `video_id` int(11) NOT NULL COMMENT '视频id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `content` varchar(250) NOT NULL COMMENT '评论内容',
  `sendtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '评论时间',
  `count_like` int(11) NOT NULL COMMENT '点赞量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `comments_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

-- 授权记录
CREATE TABLE `tokens` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `token` varchar(30) NOT NULL COMMENT '授权码',
  `applytime` datetime DEFAULT NULL COMMENT '申请时间',
  `expiretime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '失效时间',
  `count_auth` int(11) NOT NULL COMMENT '授权次数',
  `max_count_auth` int(11) NOT NULL COMMENT '最大授权次数',
  `ussage` varchar(30) NOT NULL COMMENT '用途',
  PRIMARY KEY (`id`),
  UNIQUE KEY `token_id_uindex` (`id`),
  UNIQUE KEY `token_token_uindex` (`token`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COMMENT='授权记录表';

-- 用户
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `identity` varchar(20) NOT NULL COMMENT '身份',
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_id_uindex` (`id`),
  UNIQUE KEY `users_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 视频
CREATE TABLE `videos` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `url` varchar(100) NOT NULL COMMENT '播放地址',
  `upload_userid` int(11) NOT NULL COMMENT '上传用户id',
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上传时间',
  `count_play` int(11) NOT NULL DEFAULT '0' COMMENT '播放量',
  `count_like` int(11) NOT NULL DEFAULT '0' COMMENT '点赞量',
  `pic_url` varchar(100) NOT NULL COMMENT '封面地址',
  `description` varchar(500) NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `videos_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COMMENT='视频信息表';