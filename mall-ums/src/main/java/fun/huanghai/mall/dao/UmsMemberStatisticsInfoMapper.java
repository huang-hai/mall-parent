package fun.huanghai.mall.dao;

import fun.huanghai.mall.pojo.UmsMemberStatisticsInfo;
import fun.huanghai.mall.pojo.UmsMemberStatisticsInfoExample;

import java.util.List;

public interface UmsMemberStatisticsInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberStatisticsInfo record);

    int insertSelective(UmsMemberStatisticsInfo record);

    List<UmsMemberStatisticsInfo> selectByExample(UmsMemberStatisticsInfoExample example);

    UmsMemberStatisticsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsMemberStatisticsInfo record);

    int updateByPrimaryKey(UmsMemberStatisticsInfo record);
}