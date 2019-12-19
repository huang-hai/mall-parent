package fun.huanghai.mall.config;


import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 验证配置
 */
@Configuration
public class ValidatorConfiguration {

    @Bean
    public Validator validator(){
        //开启快速模式(校验一出错就返回,不在继续执行)
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure().failFast(true)
                /*.addProperty( "hibernate.validator.fail_fast", "true" )*/
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }
}
