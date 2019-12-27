package fun.huanghai.mall.cms.exception;

public class CmsWebException extends Exception{

    public CmsWebException() {
    }

    public CmsWebException(String message) {
        super(message);
    }

    public CmsWebException(String message, Throwable cause) {
        super(message, cause);
    }

    public CmsWebException(Throwable cause) {
        super(cause);
    }

    public CmsWebException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
