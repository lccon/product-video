package cilicili.jz2.redis.template.Impl;

import cilicili.jz2.redis.core.JedisResource;
import cilicili.jz2.redis.exception.RedisException;
import cilicili.jz2.redis.template.RedisTemplate;
import cilicili.jz2.redis.util.SerializerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;
import redis.clients.util.SafeEncoder;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 *
 * @Date:2019/12/13
 * @Author:lc
 */
@Component("redisTemplate")
public class RedisTemplateImpl implements RedisTemplate {
    @Autowired
    private JedisResource jedisResource;

    public RedisTemplateImpl() {
    }

    public void set(String k, Object v) {
        Jedis jedis = this.jedisResource.getInstance();

        try {
            byte[] value = SerializerUtils.rawValue(v);
            jedis.set(SafeEncoder.encode(k), value);
        } catch (Exception var8) {
            throw new RedisException("set data is error", var8);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

    }

    public void set(String k, Object v, int seconds) {
        Jedis jedis = this.jedisResource.getInstance();

        try {
            byte[] value = SerializerUtils.rawValue(v);
            jedis.setex(SafeEncoder.encode(k), seconds, value);
        } catch (Exception var9) {
            throw new RedisException("set(seconds) data is error", var9);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

    }

    public long getLifeTime(String k) {
        Jedis jedis = this.jedisResource.getInstance();

        long var5;
        try {
            var5 = jedis.ttl(SafeEncoder.encode(k));
        } catch (Exception var9) {
            throw new RedisException("getLifeTime data is error", var9);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var5;
    }

    public <T> T get(String k) {
        Jedis jedis = this.jedisResource.getInstance();

        Object var5;
        try {
            byte[] bytes = jedis.get(SafeEncoder.encode(k));
            var5 = SerializerUtils.getRedisSerializer().deserialize(bytes);
        } catch (Exception var8) {
            throw new RedisException("get data is error", var8);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return (T) var5;
    }

    public void delete(String k) {
        Jedis jedis = this.jedisResource.getInstance();

        try {
            jedis.del(SafeEncoder.encode(k));
        } catch (Exception var7) {
            throw new RedisException("clearAll data is error", var7);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

    }

    public long incr(String key) {
        Jedis jedis = this.jedisResource.getInstance();

        long var5;
        try {
            var5 = jedis.incr(key);
        } catch (Exception var9) {
            throw new RedisException("incr data is error", var9);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var5;
    }

    public long incrBy(String key, long integer) {
        Jedis jedis = this.jedisResource.getInstance();

        long var7;
        try {
            var7 = jedis.incrBy(key, integer);
        } catch (Exception var11) {
            throw new RedisException("incr data is error", var11);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var7;
    }

    public long decr(String key) {
        Jedis jedis = this.jedisResource.getInstance();

        long var5;
        try {
            var5 = jedis.decr(key);
        } catch (Exception var9) {
            throw new RedisException("decr data is error", var9);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var5;
    }

    public long decrBy(String key, long integer) {
        Jedis jedis = this.jedisResource.getInstance();

        long var7;
        try {
            var7 = jedis.decrBy(key, integer);
        } catch (Exception var11) {
            throw new RedisException("decr data is error", var11);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var7;
    }

    public long getCounts(String key) {
        Jedis jedis = this.jedisResource.getInstance();
        long counts = 0L;

        long var7;
        try {
            String count = jedis.get(key);
            if (count != null) {
                counts = Long.valueOf(count);
            }

            var7 = counts;
        } catch (Exception var11) {
            throw new RedisException("getCounts data is error", var11);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var7;
    }

    public boolean exists(String key) {
        Jedis jedis = this.jedisResource.getInstance();

        boolean var5;
        try {
            var5 = jedis.exists(SafeEncoder.encode(key));
        } catch (Exception var8) {
            throw new RedisException("append data is error", var8);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var5;
    }

    public void append(String k, Object v) {
        Jedis jedis = this.jedisResource.getInstance();

        try {
            byte[] value = SerializerUtils.rawValue(v);
            jedis.append(SafeEncoder.encode(k), value);
        } catch (Exception var8) {
            throw new RedisException("append data is error", var8);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

    }

    public Long lPush(String k, Object... v) {
        Jedis jedis = this.jedisResource.getInstance();

        Long var6;
        try {
            byte[][] value = SerializerUtils.rawValues(v);
            var6 = jedis.lpush(SafeEncoder.encode(k), value);
        } catch (Exception var9) {
            throw new RedisException("append data is error", var9);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var6;
    }

    public Long rPush(String k, Object... v) {
        Jedis jedis = this.jedisResource.getInstance();

        Long var6;
        try {
            byte[][] value = SerializerUtils.rawValues(v);
            var6 = jedis.rpush(SafeEncoder.encode(k), value);
        } catch (Exception var9) {
            throw new RedisException("rPush data is error", var9);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var6;
    }

    public <T> List<T> sort(String k, SortingParams sortingParameters) {
        Jedis jedis = this.jedisResource.getInstance();

        List var6;
        try {
            List<byte[]> sortValue = jedis.sort(SafeEncoder.encode(k), sortingParameters);
            var6 = (List)SerializerUtils.deserializeValues(sortValue, List.class, SerializerUtils.getRedisSerializer());
        } catch (Exception var9) {
            throw new RedisException("sort data is error", var9);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var6;
    }

    public Long sadd(String k, Object... v) {
        Jedis jedis = this.jedisResource.getInstance();

        Long var6;
        try {
            byte[][] value = SerializerUtils.rawValues(v);
            var6 = jedis.sadd(SafeEncoder.encode(k), value);
        } catch (Exception var9) {
            throw new RedisException("sadd data is error", var9);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var6;
    }

    public Long sdelete(String k, Object... v) {
        Jedis jedis = this.jedisResource.getInstance();

        Long var6;
        try {
            byte[][] value = SerializerUtils.rawValues(v);
            var6 = jedis.srem(SafeEncoder.encode(k), value);
        } catch (Exception var9) {
            throw new RedisException("sdelete data is error", var9);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var6;
    }

    public <T> Set<T> sgetAll(String k) {
        Jedis jedis = this.jedisResource.getInstance();

        Set var5;
        try {
            Set<byte[]> rawValues = jedis.smembers(SafeEncoder.encode(k));
            var5 = (Set)SerializerUtils.deserializeValues(rawValues, Set.class, SerializerUtils.getRedisSerializer());
        } catch (Exception var8) {
            throw new RedisException("sgetAll data is error", var8);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var5;
    }

    public boolean sisExist(String k, Object v) {
        Jedis jedis = this.jedisResource.getInstance();

        boolean var6;
        try {
            byte[] value = SerializerUtils.rawValue(v);
            var6 = jedis.sismember(SafeEncoder.encode(k), value);
        } catch (Exception var9) {
            throw new RedisException("sisExist data is error", var9);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var6;
    }

    public void hmset(String k, Map<String, String> m) {
        if (m != null && !m.isEmpty()) {
            Jedis jedis = this.jedisResource.getInstance();

            try {
                jedis.hmset(k, m);
            } catch (Exception var8) {
                throw new RedisException("hmset data is error", var8);
            } finally {
                this.jedisResource.returnResource(jedis);
            }

        }
    }

    public List hmget(String k, String... hashK) {
        Jedis jedis = this.jedisResource.getInstance();

        List var6;
        try {
            var6 = jedis.hmget(k, hashK);
        } catch (Exception var9) {
            throw new RedisException("hmget data is error", var9);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var6;
    }

    public void hdelete(String k, Object... hashK) {
        Jedis jedis = this.jedisResource.getInstance();

        try {
            byte[][] hashKey = SerializerUtils.rawHashKeys(hashK);
            jedis.hdel(SafeEncoder.encode(k), hashKey);
        } catch (Exception var8) {
            throw new RedisException("hmget data is error", var8);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

    }

    public boolean hisExists(String k, Object hashK) {
        Jedis jedis = this.jedisResource.getInstance();

        boolean var6;
        try {
            byte[] rawHashKey = SerializerUtils.rawHashKey(hashK);
            var6 = jedis.hexists(SafeEncoder.encode(k), rawHashKey);
        } catch (Exception var9) {
            throw new RedisException("hmget data is error", var9);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var6;
    }

    public List<Object> lrange(String k, long start, long end) {
        Jedis jedis = this.jedisResource.getInstance();

        List var9;
        try {
            List<byte[]> rawValues = jedis.lrange(SafeEncoder.encode(k), start, end);
            var9 = (List)SerializerUtils.deserializeValues(rawValues, List.class, SerializerUtils.getRedisSerializer());
        } catch (Exception var12) {
            throw new RedisException("hmget data is error", var12);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var9;
    }

    public String flushDB() {
        Jedis jedis = this.jedisResource.getInstance();

        String var4;
        try {
            var4 = jedis.flushDB();
        } catch (Exception var7) {
            throw new RedisException("clearAll data is error", var7);
        } finally {
            this.jedisResource.returnResource(jedis);
        }

        return var4;
    }

    public void hset(String k, Map<?, ?> m) {
    }
}
