package fun.huanghai.mall.cms.service;

import fun.huanghai.mall.cms.pojo.CmsSubjectProductRelation;
import fun.huanghai.mall.service.BaseService;

import java.util.List;

public interface CmsSubjectProductRelationService extends BaseService<CmsSubjectProductRelation> {

    /**
     * 批量保存
     * @param list
     * @return
     */
    Integer addAll(List<CmsSubjectProductRelation> list);
}
