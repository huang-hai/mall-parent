package fun.huanghai.mall.ums.controller;

import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.to.CommonResult;
import fun.huanghai.mall.ums.exception.UmsWebException;
import fun.huanghai.mall.ums.pojo.UmsPermission;
import fun.huanghai.mall.ums.pojo.UmsRole;
import fun.huanghai.mall.ums.service.UmsRoleService;
import fun.huanghai.mall.ums.vo.RoleParam;
import fun.huanghai.mall.ums.vo.RolePermissionRelationParam;
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
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/role")
public class UmsRoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsRoleController.class);

    @Reference(methods = {
            @Method(name="add",retries = 0),/*新增操作只允许执行一次*/
            @Method(name="addRolePermissionRelation",retries = 0)/*新增操作只允许执行一次*/
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
        try {
            umsRole.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( role.getCreateTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Integer res = umsRoleService.add(umsRole);
        if(res == SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res == SysVariable.SYS_ERROR) throw new UmsWebException("系统错误！");
        return new CommonResult().failed();
    }

    /**
     * 更新角色信息
     * @param id
     * @param param
     * @param result
     * @return
     * @throws UmsWebException
     */
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id") Long id,
                               @Valid @RequestBody RoleParam param,
                               BindingResult result) throws UmsWebException {
        UmsRole umsRole = new UmsRole();
        BeanUtils.copyProperties(param,umsRole);
        try {
            umsRole.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(param.getCreateTime()));
            umsRole.setId(id);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer res = umsRoleService.edit(umsRole);
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

    /**
     * 获取角色列表
     * @return
     */
    @GetMapping("/list")
    public CommonResult list(){
        return new CommonResult().success(umsRoleService.queryByCondition(null));
    }

    /**
     * 获取角色权限列表
     * @param roleId
     * @return
     */
    @GetMapping("/permission/{roleId}")
    public CommonResult permissions(@PathVariable("roleId") Long roleId){
        List<UmsPermission> permissions = umsRoleService.queryByRoleId(roleId);
        return new CommonResult().success(permissions);
    }

    /**
     * 更新角色权限
     * @param param
     * @param result
     * @return
     * @throws UmsWebException
     */
    @PostMapping("/permission/update")
    public CommonResult updatePermissions(@Valid RolePermissionRelationParam param,
                                          BindingResult result) throws UmsWebException {
        Integer res = umsRoleService.addRolePermissionRelation(param.getRoleId(),
                Arrays.asList(param.getPermissionIds()));
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new UmsWebException("系统错误！");
        return new CommonResult().failed();
    }
}
