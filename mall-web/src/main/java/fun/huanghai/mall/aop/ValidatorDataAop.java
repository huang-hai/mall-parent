package fun.huanghai.mall.aop;

import fun.huanghai.mall.to.CommonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@Aspect
public class ValidatorDataAop {

    /**
     * 环绕通知
     * @Desc 统一请求接口接收参数时，参数校验失败处理
     * @param pjp
     */
    @Around("execution(* fun.huanghai.mall..controller.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //调用目标方法前===================
        System.out.println("调用目标方法前===================");
        Object[] args = pjp.getArgs();
        for(Object obj:args){
            if(obj instanceof BindingResult){
                BindingResult result = (BindingResult) obj;
                int errorCount = result.getErrorCount();
                if(errorCount > 0){
                    /*List<FieldError> errors = result.getFieldErrors();
                    errors.forEach((err) -> {
                        System.out.println(err.getField()+":"+err.getDefaultMessage());
                    });*/
                    return new CommonResult().validateFailed(result);
                }
            }
        }
        //调用目标方法前===================
        //调用目标方法===================
        System.out.println("调用目标方法前===================");
        Object invoRes = pjp.proceed(args);
        //调用目标方法===================

        //调用目标方法后===================
        System.out.println("调用目标方法后===================");
        //调用目标方法后===================
        return invoRes;
    }
}
