package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsProductOperateLog;
import fun.huanghai.mall.pms.pojo.PmsProductOperateLogExample;

import java.util.List;

public interface PmsProductOperateLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductOperateLog record);

    int insertSelective(PmsProductOperateLog record);

    List<PmsProductOperateLog> selectByExample(PmsProductOperateLogExample example);

    PmsProductOperateLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductOperateLog record);

    int updateByPrimaryKey(PmsProductOperateLog record);
}