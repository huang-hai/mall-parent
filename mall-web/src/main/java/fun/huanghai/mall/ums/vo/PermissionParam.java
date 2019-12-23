package fun.huanghai.mall.ums.vo;


import org.apache.dubbo.common.utils.StringUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PermissionParam {

    @NotNull(message = "日期格式不正确")
    private String createTime;// (string, optional): 创建时间 ,

    @Length(max = 500)
    private String icon;// (string, optional): 图标 ,
    private Long id;// (integer, optional),
    @NotNull
    @Length(min = 3, max = 50)
    private String name;// (string, optional): 名称 ,
    @NotNull
    @Min(0)
    private Long pid;// (integer, optional): 父级权限id ,
    private Integer sort;// (integer, optional): 排序 ,
    @NotNull
    @Min(0)
    @Max(1)
    private Integer status;// (integer, optional): 启用状态；0->禁用；1->启用 ,
    @NotNull
    @Min(0)
    @Max(2)
    private Integer type;// (integer, optional): 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限） ,
    @Length(max = 200)
    private String uri;// (string, optional): 前端资源路径 ,
    @Length(max = 200)
    private String value;// (string, optional): 权限值

    public PermissionParam() {
    }

    public PermissionParam(@NotNull(message = "日期格式不正确") String createTime, @Length(max = 500) String icon, Long id, @Length(min = 3, max = 50) String name, @Min(0) Long pid, Integer sort, @NotNull @Min(0) @Max(1) Integer status, @NotNull @Min(0) @Max(2) Integer type, @Length(max = 200) String uri, @Length(max = 200) String value) {
        this.createTime = createTime;
        this.icon = icon;
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.sort = sort;
        this.status = status;
        this.type = type;
        this.uri = uri;
        this.value = value;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(StringUtils.isNotEmpty(createTime)){
            try {
                sdf.parse(createTime);
                this.createTime = createTime;
            } catch (ParseException e) {
                e.printStackTrace();
                this.createTime = null;
            }
        } else {
            this.createTime = sdf.format(new Date());
        }
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PermissionParam{" +
                "createTime='" + createTime + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", sort=" + sort +
                ", status=" + status +
                ", type=" + type +
                ", uri='" + uri + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
