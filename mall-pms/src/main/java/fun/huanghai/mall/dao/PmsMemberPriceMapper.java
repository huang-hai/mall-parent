package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsMemberPrice;
import fun.huanghai.mall.pms.pojo.PmsMemberPriceExample;

import java.util.List;

public interface PmsMemberPriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsMemberPrice record);

    int insertSelective(PmsMemberPrice record);

    List<PmsMemberPrice> selectByExample(PmsMemberPriceExample example);

    PmsMemberPrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsMemberPrice record);

    int updateByPrimaryKey(PmsMemberPrice record);
}