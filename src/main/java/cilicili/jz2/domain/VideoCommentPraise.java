package cilicili.jz2.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 视频和评论点赞表
 *
 * @Date:2019/11/18
 * @Author:lc
 */
public class VideoCommentPraise implements Serializable {
    /** 主键id */
    private Integer id;
    /** 用户id */
    private Integer userId;
    /** 视频id */
    private Integer videoId;
    /** 评论id */
    private Integer commentId;
    /** 创建时间 */
    private Date createDate;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
