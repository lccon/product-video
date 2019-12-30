package com.lc.service.impl;

import com.lc.dao.SessionMapper;
import com.lc.domain.Session;
import com.lc.exception.base.BusinessValidationException;
import com.lc.exception.base.ServiceValidationException;
import com.lc.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Description:
 *
 * @Date:2019/12/16
 * @Author:lc
 */
@Service("sessionService")
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionMapper sessionMapper;

    @Override
    public Session getSessionBySessionId(String sessionId) {
        if(StringUtils.isEmpty(sessionId)) {
            throw new BusinessValidationException("参数不能为空!");
        }
        try {
            return sessionMapper.getSessionBySessionId(sessionId);
        } catch (Exception e) {
            throw new ServiceValidationException("获取用户session出错!", e);
        }
    }

    @Override
    public Boolean deleteSessionBySessionId(String sessionId) {
        try {
            Integer count = sessionMapper.deleteSessionBySessionId(sessionId);
            return count > 0;
        } catch (Exception e) {
            throw new ServiceValidationException("删除用户session出错!", e);
        }
    }

    @Override
    public Session updateSessionBySessionId(Session session) {
        if(session == null) {
            throw new BusinessValidationException("参数不能为空!");
        }
        try {
            sessionMapper.updateSessionBySessionId(session);
            return session;
        } catch (Exception e) {
            throw new ServiceValidationException("修改用户session出错!", e);
        }
    }

    @Override
    public Session addSession(Session newSession) {
        if (newSession == null) {
            throw new BusinessValidationException("参数不能为空!");
        }
        try {
            sessionMapper.addSession(newSession);
            return newSession;
        } catch (Exception e) {
            throw new ServiceValidationException("新增用户session出错!", e);
        }
    }
}
