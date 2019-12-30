package fun.huanghai.mall.dao;

import fun.huanghai.mall.cms.pojo.CmsSubjectProductRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsSubjectProductRelationDaoExpand extends CmsSubjectProductRelationMapper{

    /**
     * 批量保存
     * @param records
     * @return
     */
    int insertAll(@Param("records") List<CmsSubjectProductRelation> records);

    /**
     * 按单条件删除
     * name=value
     * @param name
     * @param id
     * @return
     */
    int delByCondition(@Param("name") String name,@Param("val") Long id);
}