package fun.huanghai.mall.ums.vo;

import fun.huanghai.mall.qo.QueryPageParam;

public class QueryPageExpandParam extends QueryPageParam{

    private String name;

    public QueryPageExpandParam() {
    }

    public QueryPageExpandParam(String name) {
        this.name = name;
    }

    public QueryPageExpandParam(Integer pageNum, Integer pageSize, Object obj, String name) {
        super(pageNum, pageSize, obj);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "QueryPageExpandParam{" +
                "name='" + name + '\'' +
                '}';
    }
}

