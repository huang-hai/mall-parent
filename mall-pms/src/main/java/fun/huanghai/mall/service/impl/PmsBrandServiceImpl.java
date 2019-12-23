package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.dao.PmsBrandMapper;
import fun.huanghai.mall.pms.pojo.PmsBrand;
import fun.huanghai.mall.pms.pojo.PmsBrandExample;
import fun.huanghai.mall.pms.service.PmsBrandService;
import fun.huanghai.mall.sys.SysVariable;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * 商品品牌服务
 */
@Service(methods = {
        @Method(name = "add",retries = 0)
})
public class PmsBrandServiceImpl extends BaseServiceImpl<PmsBrand> implements PmsBrandService{

    private PmsBrandMapper pmsBrandMapper;

    @Autowired
    @Qualifier("pmsBrandMapper")
    public void setPmsBrandMapper(PmsBrandMapper pmsBrandMapper) {
        this.pmsBrandMapper = pmsBrandMapper;
        super.setaClass(PmsBrandMapper.class);
        super.setExampleClass(PmsBrandExample.class);
    }

    @Override
    public Integer add(PmsBrand pmsBrand) {
        try {
            PmsBrandExample example = new PmsBrandExample();
            example.createCriteria().andNameEqualTo(pmsBrand.getName());
            List<PmsBrand> pmsBrands = super.queryByCondition(example);
            if(pmsBrands.size() > 0) return SysVariable.BRANDNAME_EXIST;

            Integer row = super.add(pmsBrand);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"add");
        }
    }

    @Override
    public Integer edit(PmsBrand pmsBrand) {
        try {
            PmsBrandExample example = new PmsBrandExample();
            example.createCriteria().andNameEqualTo(pmsBrand.getName())
            .andIdNotEqualTo(pmsBrand.getId());
            List<PmsBrand> pmsBrands = super.queryByCondition(example);
            if(pmsBrands.size() > 0) return SysVariable.BRANDNAME_EXIST;

            Integer row = super.edit(pmsBrand);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"edit");
        }
    }
}
