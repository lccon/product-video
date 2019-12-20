package cilicili.jz2.component;

import cilicili.jz2.constant.PermissionConstant;
import cilicili.jz2.domain.Session;
import cilicili.jz2.domain.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 线程局部变量
 *
 * @Date:2019/12/11
 * @Author:lc
 */
public class ThreadVariable {
    private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public static void clearThreadVariable() {
        threadLocal.remove();
    }

    public static void setSession(Object session) {
        Map map = (Map)threadLocal.get();
        if(map == null) {
            map = new HashMap();
        }
        map.put(PermissionConstant.LOGIN_SESSION_ID, session);
        threadLocal.set(map);
    }

    public static Session getSession() {
        Map map = (Map) threadLocal.get();
        if (map != null) {
            return (Session) map.get(PermissionConstant.LOGIN_SESSION_ID);
        }
        return null;
    }

    public static void setUser(User user) {
        Map map = (Map) threadLocal.get();
        if (map == null) {
            map = new HashMap();
        }
        map.put(PermissionConstant.LOGIN_USER_ID, user);
        threadLocal.set(map);
    }

    public static User getUser() {
        Map map = (Map) threadLocal.get();
        if (map != null) {
            return (User) map.get(PermissionConstant.LOGIN_USER_ID);
        }
        return null;
    }
}
