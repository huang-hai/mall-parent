package fun.huanghai.mall.pms.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

public class RecommendStatusParam {

    @NotNull
    @Size(min = 1)
    private Long[] ids;

    @NotNull
    @Min(0)
    @Max(1)
    private Integer recommendStatus;

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Integer getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(Integer recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    @Override
    public String toString() {
        return "RecommendStatusParam{" +
                "ids=" + Arrays.toString(ids) +
                ", recommendStatus=" + recommendStatus +
                '}';
    }
}
