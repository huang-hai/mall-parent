package fun.huanghai.mall.dao;

import fun.huanghai.mall.sms.pojo.SmsCouponProductRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmsCouponProductRelationDaoExpand extends SmsCouponProductRelationMapper{

    /**
     * 批量新增
     * @param records
     * @return
     */
    int insertAll(@Param("records") List<SmsCouponProductRelation> records);

    /**
     * 按单条件删除
     * name=value
     * @param name
     * @param id
     * @return
     */
    int delByCondition(@Param("name")String name,@Param("val")Long id);
}