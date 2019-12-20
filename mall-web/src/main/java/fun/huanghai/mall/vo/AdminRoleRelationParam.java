package fun.huanghai.mall.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

public class AdminRoleRelationParam {

    @NotNull
    @Min(1)
    private Long adminId;

    @NotNull
    @Size(min = 1)
    private Long[] roleIds;

    public AdminRoleRelationParam() {
    }

    public AdminRoleRelationParam(@NotNull @Min(1) Long adminId, @NotNull @Size(min = 1) Long[] roleIds) {
        this.adminId = adminId;
        this.roleIds = roleIds;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return "AdminRoleRelationParam{" +
                "adminId=" + adminId +
                ", roleIds=" + Arrays.toString(roleIds) +
                '}';
    }
}
