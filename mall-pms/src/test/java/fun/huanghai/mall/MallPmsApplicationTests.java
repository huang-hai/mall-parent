package fun.huanghai.mall;

import fun.huanghai.mall.dao.PmsBrandMapper;
import fun.huanghai.mall.pms.pojo.PmsBrand;
import fun.huanghai.mall.pms.pojo.PmsBrandExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallPmsApplicationTests {

	@Autowired
	PmsBrandMapper pmsBrandMapper;

	@Test
	public void contextLoads() {
		PmsBrandExample example = new PmsBrandExample();
		List<PmsBrand> pmsBrands = pmsBrandMapper.selectByExample(example);
		pmsBrands.forEach(brand -> {
			System.out.println(brand);
		});
	}

}
