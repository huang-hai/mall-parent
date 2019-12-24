package fun.huanghai.mall.dao;

import org.apache.ibatis.annotations.Param;


public interface PmsBrandDaoExpand extends PmsBrandMapper{

    int delAll(@Param("ids") Long[] ids);

    /**
     * 批量更新自定义状态
     * name=value
     * @param ids
     * @param name
     * @param status
     * @return
     */
    int updateAllStatus(@Param("ids") Long[] ids,
                        @Param("name") String name,
                        @Param("val") Integer status);

}