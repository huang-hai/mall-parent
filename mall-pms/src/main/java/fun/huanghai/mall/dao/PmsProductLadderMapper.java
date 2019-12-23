package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsProductLadder;
import fun.huanghai.mall.pms.pojo.PmsProductLadderExample;

import java.util.List;

public interface PmsProductLadderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductLadder record);

    int insertSelective(PmsProductLadder record);

    List<PmsProductLadder> selectByExample(PmsProductLadderExample example);

    PmsProductLadder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductLadder record);

    int updateByPrimaryKey(PmsProductLadder record);
}