package cilicili.jz2.redis.exception;

/**
 * Description:
 *
 * @Date:2019/12/13
 * @Author:lc
 */
public class RedisException extends RuntimeException {

    public RedisException() {
    }

    public RedisException(String message) {
        super(message);
    }

    public RedisException(String message, Throwable cause) {
        super(message, cause);
    }

}
