package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsProductVertifyRecord;
import fun.huanghai.mall.pms.pojo.PmsProductVertifyRecordExample;

import java.util.List;

public interface PmsProductVertifyRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductVertifyRecord record);

    int insertSelective(PmsProductVertifyRecord record);

    List<PmsProductVertifyRecord> selectByExample(PmsProductVertifyRecordExample example);

    PmsProductVertifyRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductVertifyRecord record);

    int updateByPrimaryKey(PmsProductVertifyRecord record);
}