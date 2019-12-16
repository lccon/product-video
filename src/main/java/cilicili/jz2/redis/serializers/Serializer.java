package cilicili.jz2.redis.serializers;

/**
 * Description:
 *
 * @Date:2019/12/13
 * @Author:lc
 */
public interface Serializer {
    byte[] serialize(Object var1) throws RuntimeException;

    <T> T deserialize(byte[] var1) throws RuntimeException;
}
