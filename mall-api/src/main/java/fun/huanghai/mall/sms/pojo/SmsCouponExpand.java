package fun.huanghai.mall.sms.pojo;

import java.io.Serializable;
import java.util.List;

public class SmsCouponExpand extends SmsCoupon implements Serializable{

    //商品分类关系
    private List<SmsCouponProductCategoryRelation> productCategoryRelationList;

    //商品关系
    private List<SmsCouponProductRelation> productRelationList;

    public List<SmsCouponProductCategoryRelation> getProductCategoryRelationList() {
        return productCategoryRelationList;
    }

    public void setProductCategoryRelationList(List<SmsCouponProductCategoryRelation> productCategoryRelationList) {
        this.productCategoryRelationList = productCategoryRelationList;
    }

    public List<SmsCouponProductRelation> getProductRelationList() {
        return productRelationList;
    }

    public void setProductRelationList(List<SmsCouponProductRelation> productRelationList) {
        this.productRelationList = productRelationList;
    }

    @Override
    public String toString() {
        return "SmsCouponExpand{" +
                "productCategoryRelationList=" + productCategoryRelationList +
                ", productRelationList=" + productRelationList +
                '}';
    }
}