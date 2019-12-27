package fun.huanghai.mall.pms.pojo;

import fun.huanghai.mall.cms.pojo.CmsPrefrenceAreaProductRelation;
import fun.huanghai.mall.cms.pojo.CmsSubjectProductRelation;

import java.util.Arrays;

public class PmsProductExpand extends PmsProduct{

    //商品会员价格设置
    private PmsMemberPrice[] memberPriceList;

    //优选专区和商品的关系
    private CmsPrefrenceAreaProductRelation[] prefrenceAreaProductRelationList;

    //商品参数及自定义规格属性
    private PmsProductAttributeValue[] productAttributeValueList;

    //商品满减价格设置
    private PmsProductFullReduction[] productFullReductionList;

    //商品阶梯价格设置
    private PmsProductLadder[] productLadderList;

    //商品的sku库存信息
    private PmsSkuStock[] skuStockList;

    //专题和商品关系
    private CmsSubjectProductRelation[] subjectProductRelationList;

    public PmsMemberPrice[] getMemberPriceList() {
        return memberPriceList;
    }

    public void setMemberPriceList(PmsMemberPrice[] memberPriceList) {
        this.memberPriceList = memberPriceList;
    }

    public CmsPrefrenceAreaProductRelation[] getPrefrenceAreaProductRelationList() {
        return prefrenceAreaProductRelationList;
    }

    public void setPrefrenceAreaProductRelationList(CmsPrefrenceAreaProductRelation[] prefrenceAreaProductRelationList) {
        this.prefrenceAreaProductRelationList = prefrenceAreaProductRelationList;
    }

    public PmsProductAttributeValue[] getProductAttributeValueList() {
        return productAttributeValueList;
    }

    public void setProductAttributeValueList(PmsProductAttributeValue[] productAttributeValueList) {
        this.productAttributeValueList = productAttributeValueList;
    }

    public PmsProductFullReduction[] getProductFullReductionList() {
        return productFullReductionList;
    }

    public void setProductFullReductionList(PmsProductFullReduction[] productFullReductionList) {
        this.productFullReductionList = productFullReductionList;
    }

    public PmsProductLadder[] getProductLadderList() {
        return productLadderList;
    }

    public void setProductLadderList(PmsProductLadder[] productLadderList) {
        this.productLadderList = productLadderList;
    }

    public PmsSkuStock[] getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(PmsSkuStock[] skuStockList) {
        this.skuStockList = skuStockList;
    }

    public CmsSubjectProductRelation[] getSubjectProductRelationList() {
        return subjectProductRelationList;
    }

    public void setSubjectProductRelationList(CmsSubjectProductRelation[] subjectProductRelationList) {
        this.subjectProductRelationList = subjectProductRelationList;
    }

    @Override
    public String toString() {
        return "PmsProductExpand{" +
                "memberPriceList=" + Arrays.toString(memberPriceList) +
                ", prefrenceAreaProductRelationList=" + Arrays.toString(prefrenceAreaProductRelationList) +
                ", productAttributeValueList=" + Arrays.toString(productAttributeValueList) +
                ", productFullReductionList=" + Arrays.toString(productFullReductionList) +
                ", productLadderList=" + Arrays.toString(productLadderList) +
                ", skuStockList=" + Arrays.toString(skuStockList) +
                ", subjectProductRelationList=" + Arrays.toString(subjectProductRelationList) +
                '}';
    }
}
