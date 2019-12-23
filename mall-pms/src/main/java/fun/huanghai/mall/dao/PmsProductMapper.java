package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsProduct;
import fun.huanghai.mall.pms.pojo.PmsProductExample;
import fun.huanghai.mall.pms.pojo.PmsProductWithBLOBs;

import java.util.List;

public interface PmsProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductWithBLOBs record);

    int insertSelective(PmsProductWithBLOBs record);

    List<PmsProductWithBLOBs> selectByExampleWithBLOBs(PmsProductExample example);

    List<PmsProduct> selectByExample(PmsProductExample example);

    PmsProductWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PmsProductWithBLOBs record);

    int updateByPrimaryKey(PmsProduct record);
}