package fun.huanghai.mall.pms.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

public class UpdateFactoryStatusParam {

    @NotNull
    @Size(min = 1)
    private Long[] ids;
    @NotNull
    @Min(0)
    @Max(1)
    private Integer factoryStatus;

    public UpdateFactoryStatusParam(@NotNull @Size(min = 1) Long[] ids, @NotNull @Min(0) @Max(1) Integer factoryStatus) {
        this.ids = ids;
        this.factoryStatus = factoryStatus;
    }

    public UpdateFactoryStatusParam() {
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Integer getFactoryStatus() {
        return factoryStatus;
    }

    public void setFactoryStatus(Integer factoryStatus) {
        this.factoryStatus = factoryStatus;
    }

    @Override
    public String toString() {
        return "UpdateFactoryStatusParam{" +
                "ids=" + Arrays.toString(ids) +
                ", factoryStatus=" + factoryStatus +
                '}';
    }
}
