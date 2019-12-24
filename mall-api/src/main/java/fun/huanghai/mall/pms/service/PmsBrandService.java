package fun.huanghai.mall.pms.service;

import fun.huanghai.mall.pms.pojo.PmsBrand;
import fun.huanghai.mall.service.BaseService;

public interface PmsBrandService extends BaseService<PmsBrand>{

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Integer delAll(Long[] ids);

    /**
     * 批量更新显示状态
     * @param ids
     * @param status
     * @return
     */
    Integer updateShowStatus(Long[] ids,Integer status);

    /**
     * 批量更新厂家制造商状态
     * @param ids
     * @param status
     * @return
     */
    Integer updateFactoryStatus(Long[] ids,Integer status);
}
