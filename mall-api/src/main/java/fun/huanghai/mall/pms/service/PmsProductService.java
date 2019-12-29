package fun.huanghai.mall.pms.service;

import fun.huanghai.mall.pms.pojo.PmsProduct;
import fun.huanghai.mall.service.BaseService;

public interface PmsProductService extends BaseService<PmsProduct>{

    /**
     * 批量更新删除状态
     * @param ids
     * @param status
     * @return
     */
    Integer updateDelStatus(Long[] ids,Integer status);

    /**
     * 批量更新上新状态
     * @param ids
     * @param status
     * @return
     */
    Integer updateNewStatus(Long[] ids,Integer status);

    /**
     * 批量更新上下架状态
     * @param ids
     * @param status
     * @return
     */
    Integer updatePublishStatus(Long[] ids,Integer status);

    /**
     * 批量更新商品推荐状态
     * @param ids
     * @param status
     * @return
     */
    Integer updateRecommendStatus(Long[] ids,Integer status);

    /**
     * 批量更新审核状态
     * @param ids
     * @param status
     * @param detail
     * @return
     */
    Integer updateVerifyStatus(Long[] ids,Integer status,String detail);
}
