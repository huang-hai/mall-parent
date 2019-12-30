package fun.huanghai.mall;

import fun.huanghai.mall.dao.OmsCartItemMapper;
import fun.huanghai.mall.oms.pojo.OmsCartItem;
import fun.huanghai.mall.oms.pojo.OmsCartItemExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallOmsApplicationTests {

	@Autowired
	OmsCartItemMapper omsCartItemMapper;

	@Test
	public void contextLoads() {
		OmsCartItemExample omsCartItemExample = new OmsCartItemExample();
		List<OmsCartItem> omsCartItems =
				omsCartItemMapper.selectByExample(omsCartItemExample);
		System.out.println(omsCartItems.size());
	}

}
