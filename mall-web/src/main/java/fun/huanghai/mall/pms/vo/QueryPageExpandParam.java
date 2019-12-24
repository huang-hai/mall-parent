package fun.huanghai.mall.pms.vo;

import fun.huanghai.mall.qo.QueryPageParam;

public class QueryPageExpandParam extends QueryPageParam{

    //关键字
    private String keyword;

    public QueryPageExpandParam() {
    }

    public QueryPageExpandParam(String keyword) {
        this.keyword = keyword;
    }

    public QueryPageExpandParam(Integer pageNum, Integer pageSize, Object obj, String keyword) {
        super(pageNum, pageSize, obj);
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "QueryPageExpandParam{" +
                "keyword='" + keyword + '\'' +
                '}';
    }
}
