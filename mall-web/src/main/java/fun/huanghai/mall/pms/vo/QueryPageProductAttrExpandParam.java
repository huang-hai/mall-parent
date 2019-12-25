package fun.huanghai.mall.pms.vo;

import fun.huanghai.mall.qo.QueryPageParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class QueryPageProductAttrExpandParam extends QueryPageParam{

    @NotNull
    @Min(0) //规格(属性)
    @Max(1) //参数
    private Integer type;

    public QueryPageProductAttrExpandParam(@NotNull @Min(0) @Max(1) Integer type) {
        this.type = type;
    }

    public QueryPageProductAttrExpandParam(Integer pageNum, Integer pageSize, Object obj, @NotNull @Min(0) @Max(1) Integer type) {
        super(pageNum, pageSize, obj);
        this.type = type;
    }

    public QueryPageProductAttrExpandParam() {
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "QueryPageProductAttrExpandParam{" +
                "type=" + type +
                '}';
    }
}
