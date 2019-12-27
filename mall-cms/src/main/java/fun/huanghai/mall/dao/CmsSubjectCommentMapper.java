package fun.huanghai.mall.dao;

import fun.huanghai.mall.cms.pojo.CmsSubjectComment;
import fun.huanghai.mall.cms.pojo.CmsSubjectCommentExample;

import java.util.List;

public interface CmsSubjectCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmsSubjectComment record);

    int insertSelective(CmsSubjectComment record);

    List<CmsSubjectComment> selectByExample(CmsSubjectCommentExample example);

    CmsSubjectComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmsSubjectComment record);

    int updateByPrimaryKey(CmsSubjectComment record);
}