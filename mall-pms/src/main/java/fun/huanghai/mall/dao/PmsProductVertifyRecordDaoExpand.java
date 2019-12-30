package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsProductVertifyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsProductVertifyRecordDaoExpand extends PmsProductVertifyRecordMapper{

    /**
     * 批量新增
     * @param records
     * @return
     */
    int insertAll(@Param("records") List<PmsProductVertifyRecord> records);

    /**
     * 按单条件删除
     * @param name
     * @param id
     * @return
     */
    int delByCondition(@Param("name") String name,@Param("val") Long id);
}