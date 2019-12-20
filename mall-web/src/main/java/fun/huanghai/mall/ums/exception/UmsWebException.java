package fun.huanghai.mall.ums.exception;


public class UmsWebException extends Exception{

    public UmsWebException() {
    }

    public UmsWebException(String message) {
        super(message);
    }

    public UmsWebException(String message, Throwable cause) {
        super(message, cause);
    }

    public UmsWebException(Throwable cause) {
        super(cause);
    }

    public UmsWebException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
