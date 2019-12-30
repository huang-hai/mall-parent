package fun.huanghai.mall.dao;


import fun.huanghai.mall.pms.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsProductDaoExpand extends PmsProductMapper{

    /**
     * 批量更新状态,自定义状态
     * name=val
     * @param name
     * @param ids
     * @param status
     * @return
     */
    int updateStatus(@Param("name") String name,
                     @Param("ids") Long[] ids,
                     @Param("val") Integer status);

    /**
     * 查询会员价格
     * @param pid
     * @return
     */
    List<PmsMemberPrice> queryMemberPriceByPid(@Param("pid") Long pid);

    /**
     * 查询商品属性值
     * @param pid
     * @return
     */
    List<PmsProductAttributeValue> queryProductAttributeValueByPid(@Param("pid") Long pid);

    /**
     * 查询满减
     * @param pid
     * @return
     */
    List<PmsProductFullReduction> queryProductFullReductionByPid(@Param("pid") Long pid);

    /**
     * 查询阶梯价格
     * @param pid
     * @return
     */
    List<PmsProductLadder> queryProductLadderByPid(@Param("pid") Long pid);

    /**
     * 查询库存
     * @param pid
     * @return
     */
    List<PmsSkuStock> querySkuStockByPid(@Param("pid") Long pid);

    PmsProductExpand queryById(@Param("id")Long id);
}