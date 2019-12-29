package fun.huanghai.mall.pms.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

public class NewStatusParam {

    @NotNull
    @Size(min = 1)
    private Long[] ids;

    @NotNull
    @Min(0)
    @Max(1)
    private Integer newStatus;

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Integer getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }

    @Override
    public String toString() {
        return "NewStatusParam{" +
                "ids=" + Arrays.toString(ids) +
                ", newStatus=" + newStatus +
                '}';
    }
}
