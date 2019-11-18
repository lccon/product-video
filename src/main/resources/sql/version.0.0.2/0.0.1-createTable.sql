-- 视频和评论点赞
CREATE TABLE `video_comment_praise` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `video_id` int(11) DEFAULT NULL COMMENT '视频id',
  `comment_id` int(11) DEFAULT NULL COMMENT '评论id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `moments_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='视频和评论点赞表';
