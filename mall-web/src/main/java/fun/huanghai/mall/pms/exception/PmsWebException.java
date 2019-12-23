package fun.huanghai.mall.pms.exception;


public class PmsWebException extends Exception{

    public PmsWebException() {
    }

    public PmsWebException(String message) {
        super(message);
    }

    public PmsWebException(String message, Throwable cause) {
        super(message, cause);
    }

    public PmsWebException(Throwable cause) {
        super(cause);
    }

    public PmsWebException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
