package cilicili.jz2.redis.template;

import redis.clients.jedis.SortingParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 *
 * @Date:2019/12/13
 * @Author:lc
 */
public interface RedisTemplate {
    void set(String var1, Object var2);

    void set(String var1, Object var2, int var3);

    boolean exists(String var1);

    long getLifeTime(String var1);

    <T> T get(String var1);

    void append(String var1, Object var2);

    Long lPush(String var1, Object... var2);

    Long rPush(String var1, Object... var2);

    <T> List<T> sort(String var1, SortingParams var2);

    Long sadd(String var1, Object... var2);

    Long sdelete(String var1, Object... var2);

    <T> Set<T> sgetAll(String var1);

    boolean sisExist(String var1, Object var2);

    void hset(String var1, Map<?, ?> var2);

    void hdelete(String var1, Object... var2);

    boolean hisExists(String var1, Object var2);

    List<Object> lrange(String var1, long var2, long var4);

    String flushDB();

    void delete(String var1);

    long incr(String var1);

    long incrBy(String var1, long var2);

    long decr(String var1);

    long decrBy(String var1, long var2);

    long getCounts(String var1);
}
