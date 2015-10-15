package github.jadetang.maliang.exception;/**
 * Created by tangsicheng on 2015/9/18.
 */

/**
 * @author TANG SICHENG
 */
public class MaliangException extends RuntimeException {

    static final long serialVersionUID = -7034897190745766938L;

    public MaliangException() {
        super();
    }

    public MaliangException(String message) {
        super(message);
    }


    public MaliangException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaliangException(Throwable cause) {
        super(cause);
    }

    
}
