package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsProductFullReduction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsProductFullReductionDaoExpand extends PmsProductFullReductionMapper{

    /**
     * 批量保存
     * @param records
     * @return
     */
    int insertAll(@Param("records") List<PmsProductFullReduction> records);
}