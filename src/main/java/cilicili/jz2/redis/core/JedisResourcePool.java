package cilicili.jz2.redis.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Description:
 *
 * @Date:2019/12/13
 * @Author:lc
 */
@Repository("jedisResourcePool")
public class JedisResourcePool implements JedisResource {
    private static final Logger LOG = LoggerFactory.getLogger(JedisResourcePool.class);
    @Autowired
    private JedisPool jedisPool;

    public JedisResourcePool() {
    }

    public Jedis getInstance() {
        Jedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            return jedis;
        } catch (Exception var3) {
            LOG.error("[JedisDS] getRedisClent error:" + var3.getMessage());
            if (jedis != null) {
                jedis.close();
            }

            return null;
        }
    }

    public void returnResource(Jedis jedis) {
        jedis.close();
    }
}
