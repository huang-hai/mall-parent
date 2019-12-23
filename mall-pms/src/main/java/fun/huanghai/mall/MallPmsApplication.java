package fun.huanghai.mall;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"fun.huanghai.mall.dao"})
@EnableDubbo
@SpringBootApplication
public class MallPmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallPmsApplication.class, args);
	}

}
