package fun.huanghai.mall.pms.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

public class DelStatusParam {

    @NotNull
    @Size(min = 1)
    private Long[] ids;

    @NotNull
    @Min(0)
    @Max(1)
    private Integer deleteStatus;

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    @Override
    public String toString() {
        return "DelStatusParam{" +
                "ids=" + Arrays.toString(ids) +
                ", deleteStatus=" + deleteStatus +
                '}';
    }
}
