package fun.huanghai.mall.aop;

import fun.huanghai.mall.to.CommonResult;
import fun.huanghai.mall.ums.exception.UmsWebException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = UmsWebException.class)
    public CommonResult errorHandler(Exception e){
        e.printStackTrace();
        LOGGER.error("系统全局异常感知，信息：{}",e.getStackTrace());
        return new CommonResult().validateFailed("系统出现异常了，请联系管理员！");
    }
}
