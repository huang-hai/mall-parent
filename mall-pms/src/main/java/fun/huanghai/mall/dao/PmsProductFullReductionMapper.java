package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsProductFullReduction;
import fun.huanghai.mall.pms.pojo.PmsProductFullReductionExample;

import java.util.List;

public interface PmsProductFullReductionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductFullReduction record);

    int insertSelective(PmsProductFullReduction record);

    List<PmsProductFullReduction> selectByExample(PmsProductFullReductionExample example);

    PmsProductFullReduction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductFullReduction record);

    int updateByPrimaryKey(PmsProductFullReduction record);
}