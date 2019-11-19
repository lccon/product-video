package cilicili.jz2.vo;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Description:
 *
 * @Date:2019/11/19
 * @Author:lc
 */
public class VideoVO implements Serializable {

    private Integer id;

    private String title;

    private String url;

    private Integer uploadUserid;

    private ZonedDateTime uploadTime;

    private Integer countPlay;

    private Integer countLike;

    private String picUrl;

    private String description;

    private String uploadUsername;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUploadUserid() {
        return uploadUserid;
    }

    public void setUploadUserid(Integer uploadUserid) {
        this.uploadUserid = uploadUserid;
    }

    public ZonedDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(ZonedDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getCountPlay() {
        return countPlay;
    }

    public void setCountPlay(Integer countPlay) {
        this.countPlay = countPlay;
    }

    public Integer getCountLike() {
        return countLike;
    }

    public void setCountLike(Integer countLike) {
        this.countLike = countLike;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUploadUsername() {
        return uploadUsername;
    }

    public void setUploadUsername(String uploadUsername) {
        this.uploadUsername = uploadUsername;
    }

    @Override
    public String toString() {
        return "VideoVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", uploadUserid=" + uploadUserid +
                ", uploadTime=" + uploadTime +
                ", countPlay=" + countPlay +
                ", countLike=" + countLike +
                ", picUrl='" + picUrl + '\'' +
                ", description='" + description + '\'' +
                ", uploadUsername='" + uploadUsername + '\'' +
                '}';
    }
}
