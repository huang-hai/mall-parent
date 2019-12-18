package fun.huanghai.mall.vo;


import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

public class PageInfoVo implements Serializable {

    //总记录数
    private Long total;

    //总页码
    private Integer totalPage;

    //页大小
    private Integer pageSize;

    //页数据
    private List<? extends  Object> list;

    //当前页
    private Integer pageNum;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<? extends Object> getList() {
        return list;
    }

    public void setList(List<? extends Object> list) {
        this.list = list;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public PageInfoVo(Long total, Integer totalPage, Integer pageSize, List<? extends Object> list, Integer pageNum) {
        this.total = total;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.list = list;
        this.pageNum = pageNum;
    }

    public PageInfoVo() {
    }

    public static PageInfoVo getVo(PageInfo page, Integer size){
        return new PageInfoVo(page.getTotal(),page.getPages(),size,page.getList(),
                page.getPageNum());

    }
}
