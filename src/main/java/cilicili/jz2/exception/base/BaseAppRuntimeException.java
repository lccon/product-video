package cilicili.jz2.exception.base;

/**
 * Description:
 *
 * @Date:2019/10/30
 * @Author:lc
 */
public class BaseAppRuntimeException extends RuntimeException {

    private String title;

    public BaseAppRuntimeException() {
        super();
    }

    public BaseAppRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseAppRuntimeException(String title, String message, Throwable cause) {
        super(message, cause);
        this.title = title;
    }

    public BaseAppRuntimeException(String message) {
        super(message);
    }

    public BaseAppRuntimeException(Throwable cause) {
        super(cause);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
