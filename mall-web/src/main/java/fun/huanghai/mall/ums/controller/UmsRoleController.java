package fun.huanghai.mall.ums.controller;

import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.to.CommonResult;
import fun.huanghai.mall.ums.exception.UmsWebException;
import fun.huanghai.mall.ums.pojo.UmsRole;
import fun.huanghai.mall.ums.service.UmsRoleService;
import fun.huanghai.mall.vo.RoleParam;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/role")
public class UmsRoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsRoleController.class);

    @Reference(methods = {
            @Method(name="add",retries = 0)/*新增操作只允许执行一次*/
    })
    private UmsRoleService umsRoleService;

    /**
     * 添加角色
     * @param role
     * @param result
     * @return
     * @throws UmsWebException
     */
    @PostMapping("/create")
    public CommonResult add(@Valid @RequestBody RoleParam role,
                            BindingResult result) throws UmsWebException {
        if(null == role.getStatus()) role.setStatus(1);
        UmsRole umsRole = new UmsRole();
        BeanUtils.copyProperties(role,umsRole);
        if(null == role.getCreateTime()) umsRole.setCreateTime(new Date());
        else {
            try {
                umsRole.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( role.getCreateTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Integer res = umsRoleService.add(umsRole);
        if(res == SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res == SysVariable.SYS_ERROR) throw new UmsWebException("系统错误！");
        return new CommonResult().failed();
    }

    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public CommonResult del(@RequestParam("ids") Long[] ids) throws UmsWebException {
        if(ids.length==0) return new CommonResult().validateFailed("参数不能为空！");
        Integer res = umsRoleService.delAll(ids);
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new UmsWebException("系统错误！");
        return new CommonResult().failed();
    }
}
