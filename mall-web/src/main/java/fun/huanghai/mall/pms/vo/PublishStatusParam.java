package fun.huanghai.mall.pms.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

public class PublishStatusParam {

    @NotNull
    @Size(min = 1)
    private Long[] ids;

    @NotNull
    @Min(0)
    @Max(1)
    private Integer publishStatus;

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    @Override
    public String toString() {
        return "PublishStatusParam{" +
                "ids=" + Arrays.toString(ids) +
                ", publishStatus=" + publishStatus +
                '}';
    }
}
