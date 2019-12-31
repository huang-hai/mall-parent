package fun.huanghai.mall.sms.exception;

public class SmsWebException extends Exception{

    public SmsWebException() {
    }

    public SmsWebException(String message) {
        super(message);
    }

    public SmsWebException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmsWebException(Throwable cause) {
        super(cause);
    }

    public SmsWebException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
