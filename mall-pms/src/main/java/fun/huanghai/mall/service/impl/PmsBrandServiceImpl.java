package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.dao.PmsBrandDaoExpand;
import fun.huanghai.mall.dao.PmsBrandMapper;
import fun.huanghai.mall.pms.pojo.PmsBrand;
import fun.huanghai.mall.pms.pojo.PmsBrandExample;
import fun.huanghai.mall.pms.service.PmsBrandService;
import fun.huanghai.mall.qo.QueryPageParam;
import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.vo.PageInfoVo;
import org.apache.dubbo.common.utils.StringUtils;
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

    @Autowired
    @Qualifier("pmsBrandDaoExpand")
    private PmsBrandDaoExpand pmsBrandDaoExpand;

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

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public Integer delAll(Long[] ids) {
        try {
            if(ids.length == 0) return SysVariable.SYS_FAILURE;
            int row = pmsBrandDaoExpand.delAll(ids);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"delAll");
        }
    }

    /**
     * 批量更新显示状态
     *
     * @param ids
     * @param status
     * @return
     */
    @Override
    public Integer updateShowStatus(Long[] ids, Integer status) {
        try {
            if(ids.length==0) return SysVariable.SYS_FAILURE;
            int row = pmsBrandDaoExpand.updateAllStatus(ids, "show_status", status);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"updateShowStatus");
        }
    }

    /**
     * 批量更新厂家制造商状态
     *
     * @param ids
     * @param status
     * @return
     */
    @Override
    public Integer updateFactoryStatus(Long[] ids, Integer status) {
        try {
            if(ids.length==0) return SysVariable.SYS_FAILURE;
            int row = pmsBrandDaoExpand.updateAllStatus(ids, "factory_status", status);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"updateFactoryStatus");
        }
    }

    @Override
    public PageInfoVo queryPages(QueryPageParam queryPageParam) {
        String brandName = (String) queryPageParam.getObj();
        PmsBrandExample example = new PmsBrandExample();
        if(StringUtils.isNotEmpty(brandName))
            example.createCriteria().andNameLike("%"+brandName+"%");
        queryPageParam.setObj(example);
        return super.queryPages(queryPageParam);
    }
}
