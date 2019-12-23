package fun.huanghai.mall.ums.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Arrays;

public class RolePermissionRelationParam implements Serializable{

    @Min(1)
    private Long roleId;

    @Size(min = 1)
    private Long[] permissionIds;

    public RolePermissionRelationParam() {
    }

    public RolePermissionRelationParam(@Min(1) Long roleId, @Size(min = 1) Long[] permissionIds) {
        this.roleId = roleId;
        this.permissionIds = permissionIds;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long[] getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(Long[] permissionIds) {
        this.permissionIds = permissionIds;
    }

    @Override
    public String toString() {
        return "RolePermissionRelationParam{" +
                "roleId=" + roleId +
                ", permissionIds=" + Arrays.toString(permissionIds) +
                '}';
    }
}
