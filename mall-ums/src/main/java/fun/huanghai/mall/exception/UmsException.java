package fun.huanghai.mall.exception;


public class UmsException extends Exception{

    public UmsException() {
    }

    public UmsException(String message) {
        super(message);
    }

    public UmsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UmsException(Throwable cause) {
        super(cause);
    }

    public UmsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
