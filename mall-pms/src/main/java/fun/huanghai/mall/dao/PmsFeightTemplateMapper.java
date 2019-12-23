package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsFeightTemplate;
import fun.huanghai.mall.pms.pojo.PmsFeightTemplateExample;

import java.util.List;

public interface PmsFeightTemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsFeightTemplate record);

    int insertSelective(PmsFeightTemplate record);

    List<PmsFeightTemplate> selectByExample(PmsFeightTemplateExample example);

    PmsFeightTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsFeightTemplate record);

    int updateByPrimaryKey(PmsFeightTemplate record);
}