package fun.huanghai.mall.pms.service;

import fun.huanghai.mall.pms.pojo.PmsProductAttribute;
import fun.huanghai.mall.service.BaseService;

import java.util.List;

public interface PmsProductAttributeService extends BaseService<PmsProductAttribute> {

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Integer delAll(Long[] ids);

    /**
     * 按分类ID查询
     * @param pid
     * @return
     */
    List<PmsProductAttribute> queryByPid(Long pid);
}
