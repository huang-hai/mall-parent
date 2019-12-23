package fun.huanghai.mall.ums.pojo;

import java.io.Serializable;
import java.util.List;

public class UmsPermissionExpand extends UmsPermission implements Serializable{

    private List<UmsPermissionExpand> children;

    public UmsPermissionExpand() {
    }

    public UmsPermissionExpand(List<UmsPermissionExpand> children) {
        this.children = children;
    }

    public List<UmsPermissionExpand> getChildren() {
        return children;
    }

    public void setChildren(List<UmsPermissionExpand> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "UmsPermissionExpand{" +
                "children=" + children +
                '}';
    }
}
