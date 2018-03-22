package com.example.core.shiro.cache;

import com.example.common.utils.LoggerUtils;
import com.example.common.utils.SerializeUtil;
import com.example.core.shiro.session.CustomSessionManager;
import com.example.core.shiro.session.SessionStatus;
import com.example.core.shiro.session.ShiroSessionRepository;
import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;

public class JedisShiroSessionRepository implements ShiroSessionRepository {

    public static final String REDIS_SHIRO_SESSION = "sojson-shiro-demo-session:";
    public static final String REDIS_SHIRO_ALL = "*sojson-shiro-demo-session:*";
    private static final int SESSION_VAL_TIME_SPAN = 18000;
    private static final int DB_INDEX = 1;

    private JedisManager jedisManager;

    public void saveSession(Session session) {

        if (session == null || session.getId() == null)
            throw new NullPointerException("session is empty");
        try {

            byte[] key = SerializeUtil.serialize(buildRedisSessionKey(session.getId()));

            //不存在才添加
            if (null == session.getAttribute(CustomSessionManager.SESSION_STATUS)) {
                //Session 踢出自存存储。
                SessionStatus sessionStatus = new SessionStatus();
                session.setAttribute(CustomSessionManager.SESSION_STATUS, sessionStatus);
            }

            byte[] value = SerializeUtil.serialize(session);
            getJedisManager().saveValueByKey(DB_INDEX, key, value, (int) (session.getTimeout() / 1000));
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "save session error，id:[%s]",session.getId());
        }

    }

    public void deleteSession(Serializable sessionId) {

        if (sessionId == null) {
            throw new NullPointerException("session id is empty");
        }
        try {
            getJedisManager().deleteByKey(DB_INDEX,
                    SerializeUtil.serialize(buildRedisSessionKey(sessionId)));
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "删除session出现异常，id:[%s]",sessionId);
        }

    }

    public Session getSession(Serializable sessionId) {
        if (sessionId == null)
            throw new NullPointerException("session id is empty");
        Session session = null;
        try {
            byte[] value = getJedisManager().getValueByKey(DB_INDEX, SerializeUtil
                    .serialize(buildRedisSessionKey(sessionId)));
            session = SerializeUtil.deserialize(value, Session.class);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "获取session异常，id:[%s]",sessionId);
        }
        return session;
    }

    public Collection<Session> getAllSessions() {
        Collection<Session> sessions = null;
        try {
            sessions = getJedisManager().AllSession(DB_INDEX,REDIS_SHIRO_SESSION);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "获取全部session异常");
        }

        return sessions;
    }

    private String buildRedisSessionKey(Serializable sessionId) {
        return REDIS_SHIRO_SESSION + sessionId;
    }

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }
}
