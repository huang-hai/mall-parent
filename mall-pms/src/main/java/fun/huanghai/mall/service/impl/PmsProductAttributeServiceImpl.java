package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.dao.PmsProductAttributeDaoExpand;
import fun.huanghai.mall.dao.PmsProductAttributeMapper;
import fun.huanghai.mall.pms.pojo.PmsProductAttribute;
import fun.huanghai.mall.pms.pojo.PmsProductAttributeExample;
import fun.huanghai.mall.pms.service.PmsProductAttributeService;
import fun.huanghai.mall.qo.QueryPageParam;
import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.vo.PageInfoVo;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 * 商品属性服务
 */
@Service(methods = {
        @Method(name = "add",retries = 0)
})
public class PmsProductAttributeServiceImpl extends BaseServiceImpl<PmsProductAttribute> implements PmsProductAttributeService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductAttributeServiceImpl.class);

    private PmsProductAttributeMapper pmsProductAttributeMapper;

    @Autowired
    @Qualifier("pmsProductAttributeMapper")
    public void setPmsProductAttributeMapper(PmsProductAttributeMapper pmsProductAttributeMapper) {
        this.pmsProductAttributeMapper = pmsProductAttributeMapper;
        super.setaClass(PmsProductAttributeMapper.class);
        super.setExampleClass(PmsProductAttributeExample.class);
    }

    @Autowired
    @Qualifier("pmsProductAttributeDaoExpand")
    private PmsProductAttributeDaoExpand pmsProductAttributeDaoExpand;

    @Override
    public Integer add(PmsProductAttribute pmsProductAttribute) {
        try {
            PmsProductAttributeExample example = new PmsProductAttributeExample();
            example.createCriteria().andNameEqualTo(pmsProductAttribute.getName());
            List<PmsProductAttribute> pmsProductAttributes = super.queryByCondition(example);
            if(pmsProductAttributes.size()>0) return SysVariable.PRODUCTATTRNAME_EXIST;
            return super.add(pmsProductAttribute);
        } catch (Exception e) {
            return error(e,"add");
        }
    }

    @Override
    public Integer edit(PmsProductAttribute pmsProductAttribute) {
        try {
            PmsProductAttributeExample example = new PmsProductAttributeExample();
            example.createCriteria().andNameEqualTo(pmsProductAttribute.getName())
            .andIdNotEqualTo(pmsProductAttribute.getId());
            List<PmsProductAttribute> pmsProductAttributes = super.queryByCondition(example);
            if(pmsProductAttributes.size()>0) return SysVariable.PRODUCTATTRNAME_EXIST;
            return super.edit(pmsProductAttribute);
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
            if(ids.length==0) return SysVariable.SYS_FAILURE;
            int row = pmsProductAttributeDaoExpand.delAll(ids);
            if(row>0)return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"delAll");
        }
    }

    /**
     * 按分类ID查询
     *
     * @param pid
     * @return
     */
    @Override
    public List<PmsProductAttribute> queryByPid(Long pid) {
        return pmsProductAttributeDaoExpand.queryByPid(pid);
    }

    @Override
    public PageInfoVo queryPages(QueryPageParam queryPageParam) {
        Map<String,Object> map = (Map<String,Object>) queryPageParam.getObj();
        Long cid = (Long) map.get("cid");
        Integer type = (Integer) map.get("type");
        PmsProductAttributeExample example = new PmsProductAttributeExample();
        PmsProductAttributeExample.Criteria criteria = example.createCriteria().andProductAttributeCategoryIdEqualTo(cid);
        if(null != type) criteria.andTypeEqualTo(type);
        queryPageParam.setObj(example);

        return super.queryPages(queryPageParam);
    }
}
