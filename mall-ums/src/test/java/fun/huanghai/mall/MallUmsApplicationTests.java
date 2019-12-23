package fun.huanghai.mall;

import fun.huanghai.mall.dao.UmsAdminLoginLogMapper;
import fun.huanghai.mall.dao.UmsRoleDaoExpand;
import fun.huanghai.mall.ums.pojo.UmsAdminLoginLogExample;
import fun.huanghai.mall.ums.pojo.UmsRole;
import fun.huanghai.mall.ums.pojo.UmsRoleExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallUmsApplicationTests {

	@Autowired
	@Qualifier("umsRoleDaoExpand")
	UmsRoleDaoExpand umsRoleDaoExpand;

	@Test
	public void contextLoads() {
		UmsRoleExample example = new UmsRoleExample();
		List<UmsRole> umsRoles = umsRoleDaoExpand.selectByExample(example);
		umsRoles.forEach(u -> {
			System.out.println(u.getName());
		});
		System.out.println();
	}

}
