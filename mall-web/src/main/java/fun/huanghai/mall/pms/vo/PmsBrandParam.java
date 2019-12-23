package fun.huanghai.mall.pms.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PmsBrandParam {

    @NotNull
    @Length(max = 255)
    private String bigPic;// (string, optional): 品牌大图 ,
    private String brandStory;// (string, optional): 品牌故事 ,
    @NotNull
    @Min(0)
    @Max(1)
    private Integer factoryStatus;// (integer, optional): 是否为厂家制造商 ,
    @NotNull
    @Length(max = 8)
    private String firstLetter;// (string, optional): 品牌首字母 ,
    @NotNull
    @Length(max = 255)
    private String logo;// (string): 品牌logo ,
    @NotNull
    @Length(max = 64)
    private String name;// (string): 品牌名称 ,
    @NotNull
    @Min(0)
    @Max(1)
    private Integer showStatus;// (integer, optional): 是否进行显示 ,
    private Integer sort;// (integer, optional): 排序字段

    public PmsBrandParam(@NotNull @Length(max = 255) String bigPic, String brandStory, @NotNull @Min(0) @Max(1) Integer factoryStatus, @NotNull @Length(max = 8) String firstLetter, @NotNull @Length(max = 255) String logo, @NotNull @Length(max = 64) String name, @NotNull @Min(0) @Max(1) Integer showStatus, Integer sort) {
        this.bigPic = bigPic;
        this.brandStory = brandStory;
        this.factoryStatus = factoryStatus;
        this.firstLetter = firstLetter;
        this.logo = logo;
        this.name = name;
        this.showStatus = showStatus;
        this.sort = sort;
    }

    public PmsBrandParam() {
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getBrandStory() {
        return brandStory;
    }

    public void setBrandStory(String brandStory) {
        this.brandStory = brandStory;
    }

    public Integer getFactoryStatus() {
        return factoryStatus;
    }

    public void setFactoryStatus(Integer factoryStatus) {
        this.factoryStatus = factoryStatus;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "PmsBrandParam{" +
                "bigPic='" + bigPic + '\'' +
                ", brandStory='" + brandStory + '\'' +
                ", factoryStatus=" + factoryStatus +
                ", firstLetter=" + firstLetter +
                ", logo='" + logo + '\'' +
                ", name='" + name + '\'' +
                ", showStatus=" + showStatus +
                ", sort=" + sort +
                '}';
    }
}
