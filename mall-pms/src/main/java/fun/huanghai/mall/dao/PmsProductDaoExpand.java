package fun.huanghai.mall.dao;


import org.apache.ibatis.annotations.Param;

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
}