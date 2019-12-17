package fun.huanghai.mall.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UmsAdminExpand extends UmsAdmin implements Serializable{

    //对应的角色
    private List<UmsAdminRoleRelation> roles = new ArrayList<>();

    public List<UmsAdminRoleRelation> getRoles() {
        return roles;
    }

    public void setRoles(List<UmsAdminRoleRelation> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UmsAdminExpand{" +
                "roles=" + roles +
                '}';
    }
}
