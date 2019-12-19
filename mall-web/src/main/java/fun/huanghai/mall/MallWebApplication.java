package fun.huanghai.mall;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.validation.annotation.Validated;

@EnableAspectJAutoProxy
@EnableDubbo
@ComponentScan("fun.huanghai.mall")
@SpringBootApplication
public class MallWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallWebApplication.class, args);
	}

}
