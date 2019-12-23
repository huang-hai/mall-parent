package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsSkuStock;
import fun.huanghai.mall.pms.pojo.PmsSkuStockExample;

import java.util.List;

public interface PmsSkuStockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsSkuStock record);

    int insertSelective(PmsSkuStock record);

    List<PmsSkuStock> selectByExample(PmsSkuStockExample example);

    PmsSkuStock selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsSkuStock record);

    int updateByPrimaryKey(PmsSkuStock record);
}