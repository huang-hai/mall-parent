package fun.huanghai.mall.dao;


import fun.huanghai.mall.pms.pojo.PmsProductAttribute;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsProductAttributeDaoExpand extends PmsProductAttributeMapper {

    int delAll(@Param("ids") Long[] ids);

    /**
     * 按分类ID查询
     * @param pid
     * @return
     */
    List<PmsProductAttribute> queryByPid(@Param("pid") Long pid);
}