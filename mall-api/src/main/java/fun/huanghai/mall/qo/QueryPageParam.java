package fun.huanghai.mall.qo;

import java.io.Serializable;

/**
 * qo
 * query object
 * 查询分页参数对象
 */
public class QueryPageParam implements Serializable{

    //当前页
    private Integer pageNum;

    //页大小
    private Integer pageSize;

    //查询参数
    private Object obj;

    public QueryPageParam() {
    }

    public QueryPageParam(Integer pageNum, Integer pageSize, Object obj) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
