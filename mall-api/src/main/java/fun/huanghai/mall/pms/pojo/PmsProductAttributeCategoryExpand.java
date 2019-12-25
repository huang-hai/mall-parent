package fun.huanghai.mall.pms.pojo;

import java.io.Serializable;
import java.util.List;

public class PmsProductAttributeCategoryExpand extends PmsProductAttributeCategory implements Serializable{

    private List<PmsProductAttribute> productAttributeList;

    public List<PmsProductAttribute> getProductAttributeList() {
        return productAttributeList;
    }

    public void setProductAttributeList(List<PmsProductAttribute> productAttributeList) {
        this.productAttributeList = productAttributeList;
    }

    @Override
    public String toString() {
        return "PmsProductAttributeCategoryExpand{" +
                "productAttributeList=" + productAttributeList +
                '}';
    }
}