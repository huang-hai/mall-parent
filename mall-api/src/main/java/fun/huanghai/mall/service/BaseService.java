package fun.huanghai.mall.service;

import fun.huanghai.mall.qo.QueryPageParam;
import fun.huanghai.mall.vo.PageInfoVo;

import java.util.List;

public interface BaseService<T> {

    /**
     * 查询分页数据
     * @param queryPageParam
     * @return
     */
    public PageInfoVo queryPages(QueryPageParam queryPageParam);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public T findById(Long id);

    /**
     * 新增记录
     * @param t
     * @return
     */
    public Integer add(T t);

    /**
     * 编辑记录
     * @param t
     * @return
     */
    public Integer edit(T t);

    /**
     * 删除记录
     * @param id
     * @return
     */
    public Integer del(Integer id);

}
