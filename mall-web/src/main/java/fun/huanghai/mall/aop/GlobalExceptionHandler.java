package fun.huanghai.mall.aop;

import fun.huanghai.mall.cms.exception.CmsWebException;
import fun.huanghai.mall.pms.exception.PmsWebException;
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
    public CommonResult umsErrorHandler(Exception e){
        e.printStackTrace();
        LOGGER.error("后台系统系统全局异常感知，信息：{}",e.getStackTrace());
        return new CommonResult().validateFailed("后台系统出现异常了，请联系管理员！");
    }

    @ExceptionHandler(value = PmsWebException.class)
    public CommonResult pmsErrorHandler(Exception e){
        e.printStackTrace();
        LOGGER.error("商品系统全局异常感知，信息：{}",e.getStackTrace());
        return new CommonResult().validateFailed("商品系统出现异常了，请联系管理员！");
    }

    @ExceptionHandler(value = CmsWebException.class)
    public CommonResult cmsErrorHandler(Exception e){
        e.printStackTrace();
        LOGGER.error("专题系统全局异常感知，信息：{}",e.getStackTrace());
        return new CommonResult().validateFailed("专题系统出现异常了，请联系管理员！");
    }
}
