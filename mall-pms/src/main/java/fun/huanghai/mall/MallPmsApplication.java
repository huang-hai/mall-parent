package fun.huanghai.mall;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(basePackages = {"fun.huanghai.mall.dao"})
@EnableDubbo
@EnableAspectJAutoProxy(exposeProxy = true)//暴露代理对象
@EnableTransactionManagement//开启基于注解的事务功能
@SpringBootApplication
public class MallPmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallPmsApplication.class, args);
	}

}
