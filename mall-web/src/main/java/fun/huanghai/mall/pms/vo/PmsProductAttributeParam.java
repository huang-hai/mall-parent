package fun.huanghai.mall.pms.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PmsProductAttributeParam {

    @NotNull
    @Min(0)
    @Max(1)
    private Integer filterType;// (integer, optional): 分类筛选样式：0->普通；1->颜色 ,
    @NotNull
    @Min(0)
    @Max(1)
    private Integer handAddStatus;// (integer, optional): 是否支持手动新增；0->不支持；1->支持 ,
    @Length(max = 255)
    private String inputList;// (string, optional): 可选值列表，以逗号隔开 ,
    @NotNull
    @Min(0)
    @Max(1)
    private Integer inputType;// (integer, optional): 属性录入方式：0->手工录入；1->从列表中选取 ,
    @Length(max = 64)
    private String name;// (string, optional): 属性名称 ,
    @Min(1)
    private Long productAttributeCategoryId;// (integer, optional): 属性分类ID ,
    @NotNull
    @Min(0)
    @Max(1)
    private Integer relatedStatus;// (integer, optional): 相同属性产品是否关联；0->不关联；1->关联 ,
    @NotNull
    @Min(0)
    @Max(2)
    private Integer searchType;// (integer, optional): 检索类型；0->不需要进行检索；1->关键字检索；2->范围检索 ,
    @NotNull
    @Min(0)
    @Max(2)
    private Integer selectType;// (integer, optional): 属性选择类型：0->唯一；1->单选；2->多选 ,
    private Integer sort;// (integer, optional),
    @NotNull
    @Min(0)
    @Max(1)
    private Integer type;// (integer, optional): 属性的类型；0->规格；1->参数

    public Integer getFilterType() {
        return filterType;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }

    public Integer getHandAddStatus() {
        return handAddStatus;
    }

    public void setHandAddStatus(Integer handAddStatus) {
        this.handAddStatus = handAddStatus;
    }

    public String getInputList() {
        return inputList;
    }

    public void setInputList(String inputList) {
        this.inputList = inputList;
    }

    public Integer getInputType() {
        return inputType;
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductAttributeCategoryId() {
        return productAttributeCategoryId;
    }

    public void setProductAttributeCategoryId(Long productAttributeCategoryId) {
        this.productAttributeCategoryId = productAttributeCategoryId;
    }

    public Integer getRelatedStatus() {
        return relatedStatus;
    }

    public void setRelatedStatus(Integer relatedStatus) {
        this.relatedStatus = relatedStatus;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PmsProductAttributeParam{" +
                "filterType=" + filterType +
                ", handAddStatus=" + handAddStatus +
                ", inputList='" + inputList + '\'' +
                ", inputType=" + inputType +
                ", name='" + name + '\'' +
                ", productAttributeCategoryId=" + productAttributeCategoryId +
                ", relatedStatus=" + relatedStatus +
                ", searchType=" + searchType +
                ", selectType=" + selectType +
                ", sort=" + sort +
                ", type=" + type +
                '}';
    }
}
