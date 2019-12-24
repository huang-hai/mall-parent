package fun.huanghai.mall.pms.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

public class UpdateShowStatusParam {

    @NotNull
    @Size(min = 1)
    private Long[] ids;
    @NotNull
    @Min(0)
    @Max(1)
    private Integer showStatus;

    public UpdateShowStatusParam() {
    }

    public UpdateShowStatusParam(@NotNull @Size(min = 1) Long[] ids, @NotNull @Min(0) @Max(1) Integer showStatus) {
        this.ids = ids;
        this.showStatus = showStatus;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    @Override
    public String toString() {
        return "UpdateShowStatusParam{" +
                "ids=" + Arrays.toString(ids) +
                ", showStatus=" + showStatus +
                '}';
    }
}
