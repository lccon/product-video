package com.lc.service.impl;

import com.lc.component.ThreadVariable;
import com.lc.constant.PermissionConstant;
import com.lc.domain.Session;
import com.lc.redis.template.RedisTemplate;
import com.lc.service.LoginService;
import com.lc.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Description: 登录判定
 *
 * @Date:2019/12/11
 * @Author:lc
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SessionService sessionService;

    @Override
    public Boolean isLogin(String sessionId, String accessIp, String requestUrl) {
        Session session = getSession(sessionId);
        if(!validateSession(session)) {
            return false;
        }
        Date accessTime = Calendar.getInstance().getTime();
        session.setAccessIp(accessIp);
        session.setAccessTime(accessTime);
        session.setLastUrl(requestUrl);

        if(isBlinkAccess(accessTime, session)) {
            session = sessionService.updateSessionBySessionId(session);
        }

        ThreadVariable.setSession(session);
        return true;
    }

    private Session getSession(String sid) {
        if (StringUtils.isEmpty(sid)) {
            return null;
        }
        Object se = redisTemplate.get(sid);
        Session session = null;
        if (se == null) {
            session = sessionService.getSessionBySessionId(sid);
        } else {
            session = (Session)se;
        }
        return session;
    }

    private Boolean validateSession(Session session) {
        if(session == null || session.getId() == null || !session.getIsLogin() || isTimeOut(session)) {
            return false;
        }
        return true;
    }

    private Boolean isTimeOut(Session session) {
        if ((System.currentTimeMillis()-session.getAccessTime().getTime()) > PermissionConstant.SESSION_TIME_OUT) {
            sessionService.deleteSessionBySessionId(session.getSessionId());
            return true;
        }
        return false;
    }

    private boolean isBlinkAccess(Date accessTime, Session session) {
        if ((accessTime.getTime() - session.getAccessTime().getTime()) <= 60000) {
            return true;
        }
        return false;
    }
}
