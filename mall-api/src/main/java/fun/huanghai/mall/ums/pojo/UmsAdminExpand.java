package fun.huanghai.mall.ums.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UmsAdminExpand extends UmsAdmin implements Serializable{

    //对应的角色
    private List<UmsRole> roles = new ArrayList<>();

    public List<UmsRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UmsRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UmsAdminExpand{" +
                "roles=" + roles +
                '}';
    }
}
