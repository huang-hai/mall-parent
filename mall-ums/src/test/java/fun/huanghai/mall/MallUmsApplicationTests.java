package fun.huanghai.mall;

import fun.huanghai.mall.dao.UmsAdminLoginLogMapper;
import fun.huanghai.mall.ums.pojo.UmsAdminLoginLogExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallUmsApplicationTests {

	@Autowired
	UmsAdminLoginLogMapper umsAdminLoginLogMapper;

	@Test
	public void contextLoads() {
		UmsAdminLoginLogExample example = new UmsAdminLoginLogExample();
		System.out.println(umsAdminLoginLogMapper.selectByExample(example));
	}

}
