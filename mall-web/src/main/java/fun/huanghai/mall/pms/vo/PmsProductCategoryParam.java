package fun.huanghai.mall.pms.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

public class PmsProductCategoryParam {

    private String description;// (string, optional): 描述 ,
    @Length(max = 255)
    private String icon;// (string, optional): 图标 ,
    @Length(max = 255)
    private String keywords;// (string, optional): 关键字 ,
    @NotNull
    @Length(min = 1,max = 64)
    private String name;// (string): 商品分类名称 ,
    @Min(0)
    @Max(1)
    private Integer navStatus;// (integer, optional): 是否在导航栏显示 ,

    private Long parentId;// (integer, optional): 父分类的编号 ,
    @Size(min = 1)
    private Long[] productAttributeIdList;// (Array[integer], optional): 产品相关筛选属性集合 ,
    @NotNull
    @Length(max = 64)
    private String productUnit;// (string, optional): 分类单位 ,
    @Min(0)
    @Max(1)
    private Integer showStatus;// (integer, optional): 是否进行显示 ,
    private Integer sort;// (integer, optional): 排序

    public PmsProductCategoryParam(String description, @Length(max = 255) String icon,
                                   @Length(max = 255) String keywords,
                                   @NotNull @Length(min = 1, max = 64) String name,
                                   @Min(0) @Max(1) Integer navStatus,
                                   Long parentId, Long[] productAttributeIdList,
                                   @NotNull @Length(max = 64) String productUnit,
                                   @Min(0) @Max(1) Integer showStatus, Integer sort) {
        this.description = description;
        this.icon = icon;
        this.keywords = keywords;
        this.name = name;
        this.navStatus = navStatus;
        this.parentId = parentId;
        this.productAttributeIdList = productAttributeIdList;
        this.productUnit = productUnit;
        this.showStatus = showStatus;
        this.sort = sort;
    }

    public PmsProductCategoryParam() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNavStatus() {
        return navStatus;
    }

    public void setNavStatus(Integer navStatus) {
        this.navStatus = navStatus;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long[] getProductAttributeIdList() {
        return productAttributeIdList;
    }

    public void setProductAttributeIdList(Long[] productAttributeIdList) {
        this.productAttributeIdList = productAttributeIdList;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "PmsProductCategoryParam{" +
                "description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", keywords='" + keywords + '\'' +
                ", name='" + name + '\'' +
                ", navStatus=" + navStatus +
                ", parentId=" + parentId +
                ", productAttributeIdList=" + Arrays.toString(productAttributeIdList) +
                ", productUnit='" + productUnit + '\'' +
                ", showStatus=" + showStatus +
                ", sort=" + sort +
                '}';
    }
}
