package fun.huanghai.mall.pms.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

public class VerifyStatusParam {

    @NotNull
    @Size(min = 1)
    private Long[] ids;

    @NotNull
    @Min(0)
    @Max(1)
    private Integer verifyStatus;

    @NotNull
    @Length(min = 1)
    private String detail;

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "VerifyStatusParam{" +
                "ids=" + Arrays.toString(ids) +
                ", verifyStatus=" + verifyStatus +
                ", detail='" + detail + '\'' +
                '}';
    }
}
