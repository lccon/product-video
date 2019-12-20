package cilicili.jz2.domain;

import cilicili.jz2.base.BaseDomain;

import java.util.Date;

/**
 * Description:
 *
 * @Date:2019/12/16
 * @Author:lc
 */
public class Session extends BaseDomain {
    private Integer id;
    private Integer userId;
    private String userName;
    private String sessionId;
    private Boolean isLogin;
    private String accessIp;
    private Date accessTime;
    private String loginIp;
    private Date loginDate;
    private String lastUrl;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Boolean isLogin) {
        this.isLogin = isLogin;
    }

    public String getAccessIp() {
        return accessIp;
    }

    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getLastUrl() {
        return lastUrl;
    }

    public void setLastUrl(String lastUrl) {
        this.lastUrl = lastUrl;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", isLogin=" + isLogin +
                ", accessIp='" + accessIp + '\'' +
                ", accessTime=" + accessTime +
                ", loginIp='" + loginIp + '\'' +
                ", loginDate=" + loginDate +
                ", lastUrl='" + lastUrl + '\'' +
                '}';
    }
}
