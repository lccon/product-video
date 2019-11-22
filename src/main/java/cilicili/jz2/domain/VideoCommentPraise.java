package cilicili.jz2.domain;

import cilicili.jz2.base.BaseDomain;

import java.util.Date;

/**
 * Description: 视频和评论点赞表
 *
 * @Date:2019/11/18
 * @Author:lc
 */
public class VideoCommentPraise extends BaseDomain {
    /** 主键id */
    private Integer id;
    /** 用户id */
    private Integer userId;
    /** 视频id */
    private Integer videoId;
    /** 评论id */
    private Integer commentId;
    /** 用户是否已经对评论进行过点赞 0:否，1:是*/
    private Integer hasCommentPraise;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getHasCommentPraise() {
        return hasCommentPraise;
    }

    public void setHasCommentPraise(Integer hasCommentPraise) {
        this.hasCommentPraise = hasCommentPraise;
    }
}
