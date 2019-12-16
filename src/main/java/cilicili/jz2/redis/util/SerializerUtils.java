package cilicili.jz2.redis.util;

import cilicili.jz2.redis.serializers.JavaSerializer;
import cilicili.jz2.redis.serializers.Serializer;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Description:
 *
 * @Date:2019/12/13
 * @Author:lc
 */
public class SerializerUtils {
    private static final Serializer redisSerializer = new JavaSerializer();

    public SerializerUtils() {
    }

    public static Serializer getRedisSerializer() {
        return redisSerializer;
    }

    public static byte[] rawKey(Object key) {
        Assert.notNull(key, "non null key required");
        return redisSerializer == null && key instanceof byte[] ? (byte[])key : redisSerializer.serialize(key);
    }

    public static byte[] rawValue(Object value) {
        return redisSerializer == null && value instanceof byte[] ? (byte[])value : redisSerializer.serialize(value);
    }

    public static byte[][] rawValues(Object... values) {
        byte[][] rawValues = new byte[values.length][];
        int i = 0;
        Object[] var6 = values;
        int var5 = values.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            Object value = var6[var4];
            rawValues[i++] = rawValue(value);
        }

        return rawValues;
    }

    public static <T extends Collection<?>> T deserializeValues(Collection<byte[]> rawValues, Class<T> type, Serializer redisSerializer) {
        if (redisSerializer == null) {
            return (T) rawValues;
        } else if (rawValues == null) {
            return null;
        } else {
            Collection<Object> values = new HashSet(rawValues.size());
            Iterator var5 = rawValues.iterator();

            while(var5.hasNext()) {
                byte[] bs = (byte[])var5.next();
                Object o = redisSerializer.deserialize(bs);
                values.add(o);
            }

            return (T) values;
        }
    }

    public static <HK> byte[] rawHashKey(HK hashKey) {
        Assert.notNull(hashKey, "non null hash key required");
        return redisSerializer == null && hashKey instanceof byte[] ? (byte[])hashKey : redisSerializer.serialize(hashKey);
    }

    public static <HV> byte[] rawHashValue(HV value) {
        return redisSerializer == null & value instanceof byte[] ? (byte[])value : redisSerializer.serialize(value);
    }

    public static <HK> byte[][] rawHashKeys(HK... hashKeys) {
        Assert.notNull(hashKeys, "non null key required");
        byte[][] rawHashKeys = new byte[hashKeys.length][];
        int i = 0;
        Object[] var6 = hashKeys;
        int var5 = hashKeys.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            HK hashKey = (HK) var6[var4];
            rawHashKeys[i++] = rawHashKey(hashKey);
        }

        return rawHashKeys;
    }
}
