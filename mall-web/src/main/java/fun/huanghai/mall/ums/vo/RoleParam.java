package fun.huanghai.mall.ums.vo;

import org.apache.dubbo.common.utils.StringUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RoleParam implements Serializable{

    private Integer adminCount;// (integer, optional): 后台用户数量 ,
    @NotNull(message = "日期格式错误")
    private String createTime;// (string, optional): 创建时间 ,
    @Length(max = 500)
    private String description;// (string, optional): 描述 ,
    private Long id;// (integer, optional),
    @NotNull
    @Length(min = 3,max = 50)
    private String name;// (string, optional): 名称 ,
    private Integer sort;// (integer, optional),
    private Integer status;// ;//(integer, optional): 启用状态：0->禁用；1->启用

    public RoleParam(Integer adminCount, String createTime, @Length(max = 500) String description, Long id, @NotNull @Length(min = 3, max = 50) String name, Integer sort, Integer status) {
        this.adminCount = adminCount;
        this.createTime = createTime;
        this.description = description;
        this.id = id;
        this.name = name;
        this.sort = sort;
        this.status = status;
    }

    public RoleParam() {
    }

    public Integer getAdminCount() {
        return adminCount;
    }

    public void setAdminCount(Integer adminCount) {
        this.adminCount = adminCount;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "RoleParam{" +
                "adminCount=" + adminCount +
                ", createTime='" + createTime + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                '}';
    }
}
