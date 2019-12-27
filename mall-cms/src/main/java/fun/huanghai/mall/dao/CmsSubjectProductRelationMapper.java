package fun.huanghai.mall.dao;

import fun.huanghai.mall.cms.pojo.CmsSubjectProductRelation;
import fun.huanghai.mall.cms.pojo.CmsSubjectProductRelationExample;

import java.util.List;

public interface CmsSubjectProductRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmsSubjectProductRelation record);

    int insertSelective(CmsSubjectProductRelation record);

    List<CmsSubjectProductRelation> selectByExample(CmsSubjectProductRelationExample example);

    CmsSubjectProductRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmsSubjectProductRelation record);

    int updateByPrimaryKey(CmsSubjectProductRelation record);
}