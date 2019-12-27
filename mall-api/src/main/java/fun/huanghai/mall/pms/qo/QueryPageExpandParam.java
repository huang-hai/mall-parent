package fun.huanghai.mall.pms.qo;

import fun.huanghai.mall.qo.QueryPageParam;

import java.io.Serializable;

/**
 * 商品分页搜索条件
 */
public class QueryPageExpandParam extends QueryPageParam implements Serializable{

    //关键字(商品中为：商品名称模糊关键字)
    private String keyword;

    //上架状态
    private Integer publishStatus;

    //审核状态
    private Integer verifyStatus;

    //商品货号
    private String productSn;

    //商品分类编号
    private Long productCategoryId;

    //商品品牌编号
    private Long brandId;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    @Override
    public String toString() {
        return "QueryPageExpandParam{" +
                "keyword='" + keyword + '\'' +
                ", publishStatus=" + publishStatus +
                ", verifyStatus=" + verifyStatus +
                ", productSn='" + productSn + '\'' +
                ", productCategoryId=" + productCategoryId +
                ", brandId=" + brandId +
                '}';
    }
}

