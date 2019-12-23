package fun.huanghai.mall.ums.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

public class AdminPermissionRelationParam {

    @NotNull
    @Min(1)
    private Long adminId;

    @NotNull
    @Size(min = 1)
    private Long[] permissionIds;

    public AdminPermissionRelationParam(@NotNull @Min(1) Long adminId, @NotNull @Size(min = 1) Long[] permissionIds) {
        this.adminId = adminId;
        this.permissionIds = permissionIds;
    }

    public AdminPermissionRelationParam() {
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long[] getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(Long[] permissionIds) {
        this.permissionIds = permissionIds;
    }

    @Override
    public String toString() {
        return "AdminPermissionRelationParam{" +
                "adminId=" + adminId +
                ", permissionIds=" + Arrays.toString(permissionIds) +
                '}';
    }
}
