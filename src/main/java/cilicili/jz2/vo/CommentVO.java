package cilicili.jz2.vo;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Description:
 *
 * @Date:2019/11/19
 * @Author:lc
 */
public class CommentVO implements Serializable {
    private Integer id;
    /** 视频id */
    private Integer videoId;
    /** 用户id */
    private Integer userId;
    /** 评论内容 */
    private String content;
    /** 发送的时间 */
    private ZonedDateTime sendtime;
    /** 点赞数量 */
    private Integer countLike;
    /** 发送的用户姓名 */
    private String sendUsername;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getSendtime() {
        return sendtime;
    }

    public void setSendtime(ZonedDateTime sendtime) {
        this.sendtime = sendtime;
    }

    public Integer getCountLike() {
        return countLike;
    }

    public void setCountLike(Integer countLike) {
        this.countLike = countLike;
    }

    public String getSendUsername() {
        return sendUsername;
    }

    public void setSendUsername(String sendUsername) {
        this.sendUsername = sendUsername;
    }

    @Override
    public String toString() {
        return "CommentVO{" +
                "id=" + id +
                ", videoId=" + videoId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", sendtime=" + sendtime +
                ", countLike=" + countLike +
                ", sendUsername='" + sendUsername + '\'' +
                '}';
    }
}
