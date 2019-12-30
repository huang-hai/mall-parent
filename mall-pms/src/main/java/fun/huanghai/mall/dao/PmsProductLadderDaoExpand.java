package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsProductLadder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsProductLadderDaoExpand extends PmsProductLadderMapper{

    /**
     * 批量删除
     * @param records
     * @return
     */
    int insertAll(@Param("records") List<PmsProductLadder> records);

    /**
     * 按条件删除
     * @param name
     * @param id
     * @return
     */
    int delByCondition(@Param("name")String name,@Param("val")Long id);
}