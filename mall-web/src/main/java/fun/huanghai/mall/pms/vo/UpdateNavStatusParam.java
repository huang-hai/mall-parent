package fun.huanghai.mall.pms.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

public class UpdateNavStatusParam {

    @NotNull
    @Size(min = 1)
    private Long[] ids;
    @NotNull
    @Min(0)
    @Max(1)
    private Integer navStatus;

    public UpdateNavStatusParam() {
    }

    public UpdateNavStatusParam(@NotNull @Size(min = 1) Long[] ids, @NotNull @Min(0) @Max(1) Integer navStatus) {
        this.ids = ids;
        this.navStatus = navStatus;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Integer getNavStatus() {
        return navStatus;
    }

    public void setNavStatus(Integer navStatus) {
        this.navStatus = navStatus;
    }

    @Override
    public String toString() {
        return "UpdateNavStatusParam{" +
                "ids=" + Arrays.toString(ids) +
                ", navStatus=" + navStatus +
                '}';
    }
}
