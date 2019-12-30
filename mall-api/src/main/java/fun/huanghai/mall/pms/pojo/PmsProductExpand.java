package fun.huanghai.mall.pms.pojo;

import fun.huanghai.mall.cms.pojo.CmsPrefrenceAreaProductRelation;
import fun.huanghai.mall.cms.pojo.CmsSubjectProductRelation;

import java.util.Arrays;
import java.util.List;

public class PmsProductExpand extends PmsProductWithBLOBs{

    //商品会员价格设置
    private List<PmsMemberPrice> memberPriceList;

    //优选专区和商品的关系
    private List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList;

    //商品参数及自定义规格属性
    private List<PmsProductAttributeValue> productAttributeValueList;

    //商品满减价格设置
    private List<PmsProductFullReduction> productFullReductionList;

    //商品阶梯价格设置
    private List<PmsProductLadder> productLadderList;

    //商品的sku库存信息
    private List<PmsSkuStock> skuStockList;

    //专题和商品关系
    private List<CmsSubjectProductRelation> subjectProductRelationList;

    public List<PmsMemberPrice> getMemberPriceList() {
        return memberPriceList;
    }

    public void setMemberPriceList(List<PmsMemberPrice> memberPriceList) {
        this.memberPriceList = memberPriceList;
    }

    public List<CmsPrefrenceAreaProductRelation> getPrefrenceAreaProductRelationList() {
        return prefrenceAreaProductRelationList;
    }

    public void setPrefrenceAreaProductRelationList(List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList) {
        this.prefrenceAreaProductRelationList = prefrenceAreaProductRelationList;
    }

    public List<PmsProductAttributeValue> getProductAttributeValueList() {
        return productAttributeValueList;
    }

    public void setProductAttributeValueList(List<PmsProductAttributeValue> productAttributeValueList) {
        this.productAttributeValueList = productAttributeValueList;
    }

    public List<PmsProductFullReduction> getProductFullReductionList() {
        return productFullReductionList;
    }

    public void setProductFullReductionList(List<PmsProductFullReduction> productFullReductionList) {
        this.productFullReductionList = productFullReductionList;
    }

    public List<PmsProductLadder> getProductLadderList() {
        return productLadderList;
    }

    public void setProductLadderList(List<PmsProductLadder> productLadderList) {
        this.productLadderList = productLadderList;
    }

    public List<PmsSkuStock> getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(List<PmsSkuStock> skuStockList) {
        this.skuStockList = skuStockList;
    }

    public List<CmsSubjectProductRelation> getSubjectProductRelationList() {
        return subjectProductRelationList;
    }

    public void setSubjectProductRelationList(List<CmsSubjectProductRelation> subjectProductRelationList) {
        this.subjectProductRelationList = subjectProductRelationList;
    }

    @Override
    public String toString() {
        return "PmsProductExpand{" +
                "memberPriceList=" + memberPriceList +
                ", prefrenceAreaProductRelationList=" + prefrenceAreaProductRelationList +
                ", productAttributeValueList=" + productAttributeValueList +
                ", productFullReductionList=" + productFullReductionList +
                ", productLadderList=" + productLadderList +
                ", skuStockList=" + skuStockList +
                ", subjectProductRelationList=" + subjectProductRelationList +
                '}';
    }
}
