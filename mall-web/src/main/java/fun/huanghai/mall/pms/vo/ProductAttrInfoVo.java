package fun.huanghai.mall.pms.vo;

public class ProductAttrInfoVo {

    private Long attributeCategoryId;//": 0,
    private Long attributeId;//": 0

    public ProductAttrInfoVo(Long attributeCategoryId, Long attributeId) {
        this.attributeCategoryId = attributeCategoryId;
        this.attributeId = attributeId;
    }

    public ProductAttrInfoVo() {
    }

    public Long getAttributeCategoryId() {
        return attributeCategoryId;
    }

    public void setAttributeCategoryId(Long attributeCategoryId) {
        this.attributeCategoryId = attributeCategoryId;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    @Override
    public String toString() {
        return "ProductAttrInfoVo{" +
                "attributeCategoryId=" + attributeCategoryId +
                ", attributeId=" + attributeId +
                '}';
    }
}
