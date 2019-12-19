package fun.huanghai.mall.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UmsAdminParam implements Serializable{

    @Email(message = "邮箱格式错误！")
    private String email;// (string, optional): 邮箱 ,

    @NotNull
    @Length(min = 1,max = 20)
    private String icon;// (string, optional): 用户头像 ,

    @NotNull
    @Length(min = 1,max = 200)
    private String nickName;// (string, optional): 用户昵称 ,

    @Length(max = 500)
    private String note;// (string, optional): 备注 ,

    @NotNull
    @Length(min = 6,max = 18)
    private String password;// (string): 密码 ,

    @NotNull
    @Length(min = 3,max = 20)
    private String username;// (string): 用户名

    public UmsAdminParam() {
    }

    public UmsAdminParam(String email, String icon, String nickName, String note, String password, String username) {
        this.email = email;
        this.icon = icon;
        this.nickName = nickName;
        this.note = note;
        this.password = password;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UmsAdminParam{" +
                "email='" + email + '\'' +
                ", icon='" + icon + '\'' +
                ", nickName='" + nickName + '\'' +
                ", note='" + note + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
