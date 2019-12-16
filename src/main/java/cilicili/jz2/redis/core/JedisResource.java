package cilicili.jz2.redis.core;

import redis.clients.jedis.Jedis;

/**
 * Description:
 *
 * @Date:2019/12/13
 * @Author:lc
 */
public interface JedisResource {
    Jedis getInstance();

    void returnResource(Jedis var1);
}
