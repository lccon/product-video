-- 弹幕添加字段
ALTER TABLE barrages ADD create_date datetime comment '创建时间';
ALTER TABLE barrages ADD update_date datetime comment '修改时间';

-- 评论添加字段
ALTER TABLE comments ADD create_date datetime comment '创建时间';
ALTER TABLE comments ADD update_date datetime comment '修改时间';

-- 用户添加字段
ALTER TABLE users ADD create_date datetime comment '创建时间';
ALTER TABLE users ADD update_date datetime comment '修改时间';

-- 点赞添加字段
ALTER TABLE video_comment_praise ADD update_date datetime comment '修改时间';

-- 视频添加字段
ALTER TABLE videos ADD create_date datetime comment '创建时间';
ALTER TABLE videos ADD update_date datetime comment '修改时间';