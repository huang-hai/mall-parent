package fun.huanghai.mall.cms.service;

import fun.huanghai.mall.cms.pojo.CmsPrefrenceAreaProductRelation;
import fun.huanghai.mall.service.BaseService;

import java.util.List;

public interface CmsPrefrenceAreaProductRelationService extends BaseService<CmsPrefrenceAreaProductRelation> {

    /**
     * 批量新增
     * @param list
     * @return
     */
    Integer addAll(List<CmsPrefrenceAreaProductRelation> list);
}
