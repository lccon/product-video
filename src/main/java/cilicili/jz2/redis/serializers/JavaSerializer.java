package cilicili.jz2.redis.serializers;

import cilicili.jz2.redis.exception.RedisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Description:
 *
 * @Date:2019/12/13
 * @Author:lc
 */
public class JavaSerializer implements Serializer {
    private static final Logger logger = LoggerFactory.getLogger(JavaSerializer.class);

    public JavaSerializer() {
    }

    public byte[] serialize(Object object) throws RuntimeException {
        ObjectOutputStream oos = null;

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            return bos.toByteArray();
        } catch (Exception var4) {
            var4.printStackTrace();
            throw new RedisException("序列化失败", var4);
        }
    }

    public <T> T deserialize(byte[] bytes) throws RuntimeException {
        if (bytes != null && bytes.length != 0) {
            ObjectInputStream ois = null;

            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                ois = new ObjectInputStream(bis);
                return (T) ois.readObject();
            } catch (Exception var4) {
                throw new RedisException("反序列化失败", var4);
            }
        } else {
            return null;
        }
    }
}
