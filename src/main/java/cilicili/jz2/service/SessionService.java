package cilicili.jz2.service;

import cilicili.jz2.domain.Session;

/**
 * Description:
 *
 * @Date:2019/12/16
 * @Author:lc
 */
public interface SessionService {

    Session getSessionBySessionId(String sessionId);

    Boolean deleteSessionBySessionId(String sessionId);

    Session updateSessionBySessionId(Session session);

    Session addSession(Session newSession);
}
