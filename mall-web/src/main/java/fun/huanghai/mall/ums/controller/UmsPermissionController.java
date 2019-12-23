package fun.huanghai.mall.ums.controller;

import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.to.CommonResult;
import fun.huanghai.mall.ums.exception.UmsWebException;
import fun.huanghai.mall.ums.pojo.UmsPermission;
import fun.huanghai.mall.ums.service.UmsPermissionService;
import fun.huanghai.mall.ums.vo.PermissionParam;
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

@CrossOrigin
@RequestMapping("/permission")
@RestController
public class UmsPermissionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsPermissionController.class);

    @Reference(methods = {
            @Method(name="add",retries = 0)/*新增操作只允许执行一次*/
    })
    private UmsPermissionService umsPermissionService;

    /**
     * 创建权限
     * @param permission
     * @param result
     * @return
     * @throws UmsWebException
     */
    @PostMapping("/create")
    public CommonResult add(@Valid @RequestBody PermissionParam permission,
                            BindingResult result) throws UmsWebException {
        UmsPermission umsPermission = new UmsPermission();
        BeanUtils.copyProperties(permission,umsPermission);
        try {
            umsPermission.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( permission.getCreateTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Integer res = umsPermissionService.edit(umsPermission);
        if(res == SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res == SysVariable.PERMISSIONNAME_EXIST) return new CommonResult().validateFailed("权限名已存在！");
        else if(res == SysVariable.SYS_ERROR) throw new UmsWebException("系统错误！");
        return new CommonResult().failed();
    }

    /**
     * 更新权限信息
     * @param id
     * @param permission
     * @param result
     * @return
     * @throws UmsWebException
     */
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id") Long id,
                               @Valid @RequestBody PermissionParam permission,
                               BindingResult result) throws UmsWebException {
        UmsPermission umsPermission = new UmsPermission();
        BeanUtils.copyProperties(permission,umsPermission);
        umsPermission.setId(id);
        try {
            umsPermission.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( permission.getCreateTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Integer res = umsPermissionService.edit(umsPermission);
        if(res == SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res == SysVariable.PERMISSIONNAME_EXIST) return new CommonResult().validateFailed("权限名已存在！");
        else if(res == SysVariable.SYS_ERROR) throw new UmsWebException("系统错误！");
        return new CommonResult().failed();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") Long[] ids) throws UmsWebException {
        Integer res = umsPermissionService.delAll(ids);
        if(res==SysVariable.SYS_SUCCESS)return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new UmsWebException("系统错误！");
        else return new CommonResult().failed();
    }

    /**
     * 获取权限列表
     * @return
     */
    @GetMapping("/list")
    public CommonResult list(){
        return new CommonResult().success(umsPermissionService.queryByCondition(null));
    }

    /**
     * 获取树形列表
     * @return
     */
    @GetMapping("/treeList")
    public CommonResult treeList(){
        return new CommonResult().success(umsPermissionService.queryTreeList());
    }

}
