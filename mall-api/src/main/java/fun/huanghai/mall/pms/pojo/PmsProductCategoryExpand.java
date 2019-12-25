package fun.huanghai.mall.pms.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 商品分类实体扩展类
 */
public class PmsProductCategoryExpand extends PmsProductCategory implements Serializable{

    private List<PmsProductCategoryExpand> children;

    public PmsProductCategoryExpand() {
    }

    public PmsProductCategoryExpand(List<PmsProductCategoryExpand> children) {
        this.children = children;
    }

    public List<PmsProductCategoryExpand> getChildren() {
        return children;
    }

    public void setChildren(List<PmsProductCategoryExpand> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "PmsProductCategoryExpand{" +
                "children=" + children +
                '}';
    }
}