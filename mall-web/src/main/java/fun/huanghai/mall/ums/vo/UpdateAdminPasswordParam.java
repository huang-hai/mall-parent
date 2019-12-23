package fun.huanghai.mall.ums.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UpdateAdminPasswordParam {

    @NotNull
    @Length(min = 3,max = 18)
    private String newPassword;// (string): 新密码 ,

    @NotNull
    @Length(min = 3,max = 18)
    private String oldPassword;// (string): 旧密码 ,

    @NotNull
    @Length(min = 3,max = 20)
    private String username;// (string): 用户名

    public UpdateAdminPasswordParam(@NotNull @Length(min = 3, max = 18) String newPassword, @NotNull @Length(min = 3, max = 18) String oldPassword, @NotNull @Length(min = 3, max = 20) String username) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
        this.username = username;
    }

    public UpdateAdminPasswordParam() {
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UpdateAdminPasswordParam{" +
                "newPassword='" + newPassword + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
