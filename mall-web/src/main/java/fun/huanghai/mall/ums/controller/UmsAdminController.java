package fun.huanghai.mall.ums.controller;

import fun.huanghai.mall.qo.QueryPageParam;
import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.ums.exception.UmsWebException;
import fun.huanghai.mall.ums.pojo.UmsAdmin;
import fun.huanghai.mall.ums.pojo.UmsAdminExpand;
import fun.huanghai.mall.ums.service.UmsAdminService;
import fun.huanghai.mall.to.CommonResult;
import fun.huanghai.mall.ums.service.UmsPermissionService;
import fun.huanghai.mall.ums.service.UmsRoleService;
import fun.huanghai.mall.utils.JwtTokenUtil;
import fun.huanghai.mall.ums.vo.*;
import fun.huanghai.mall.vo.PageInfoVo;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 本类的用户是指后台用户
 */
@CrossOrigin//允许跨域请求
@RestController
@RequestMapping("/admin")
public class UmsAdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminController.class);

    @Reference(methods = {
            @Method(name="add",retries = 0)/*新增操作只允许执行一次*/
    })
    private UmsAdminService umsAdminService;

    @Reference(methods = {
            @Method(name="add",retries = 0),
            @Method(name="addAdminPermissionRelation",retries = 0),
    })
    private UmsPermissionService umsPermissionService;


    @Reference(methods = {
            @Method(name="add",retries = 0),
            @Method(name="addAdminRoleRelation",retries = 0)
    })
    private UmsRoleService umsRoleService;

    @Value("${mall.jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${mall.jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    /**
     * 登录
     * @param umsAdminLoginParam
     * @return
     */
    @PostMapping("/login")
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam){
        UmsAdmin admin = umsAdminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        CommonResult commonResult = null;
        if(null != admin){
            String token = jwtTokenUtil.generateToken(admin);
            Map<String,String> map = new HashMap<>();
            map.put("token",token);
            map.put("tokenHead",tokenHead);
            commonResult = new CommonResult().success(map);
        } else commonResult = new CommonResult().validateFailed("用户名或密码错误！");
        return commonResult;
    }

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    @GetMapping("/info")
    public CommonResult getAdminInfo(HttpServletRequest request){
        String cusToken = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUserNameFromToken(cusToken.replace(tokenHead,""));
        if(null == username) return new CommonResult().validateFailed("token无效或已过期");
        else {
            UmsAdminExpand adminExpand = umsAdminService.queryAdminInfo(username);
            Map<String,Object> returnMap = new HashMap<>();
            returnMap.put("icon",adminExpand.getIcon());
            returnMap.put("username",adminExpand.getUsername());
            returnMap.put("roles",adminExpand.getRoles());
            return new CommonResult().success(returnMap);
        }
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public CommonResult del(@PathVariable("id") Long id) throws Exception {
        UmsAdmin admin = new UmsAdmin();
        admin.setStatus(0);
        admin.setId(id);
        Integer res = umsAdminService.edit(admin);
        if(res == SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new UmsWebException(SysVariable.SYS_ERROR_MES);
        else return new CommonResult().failed();
    }

    /**
     * 根据用户名或昵称查找分页数据
     * @param queryPageExpandParam
     * @return
     */
    @GetMapping("/list")
    public CommonResult list(QueryPageExpandParam queryPageExpandParam) throws Exception {
        QueryPageParam queryPageParam = new QueryPageParam(queryPageExpandParam.getPageNum(), queryPageExpandParam.getPageSize(), queryPageExpandParam.getName());
        PageInfoVo pageInfoVo = umsAdminService.queryPages(queryPageParam);

        return new CommonResult().success(pageInfoVo);
    }

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult admin(@PathVariable("id") Long id){
        return new CommonResult().success(umsAdminService.findById(id));
    }

    /**
     * 注册用户
     * @Valid 开启参数校验
     * @param adminParam
     * @param result
     * @return
     */
    @PostMapping("/register")
    public CommonResult register(@Valid @RequestBody UmsAdminParam adminParam, BindingResult result) throws UmsWebException {
        System.out.println(adminParam);
        UmsAdmin admin = new UmsAdmin();
        //拷贝属性
        BeanUtils.copyProperties(adminParam,admin);
        System.out.println(admin);
        Integer res = umsAdminService.add(admin);
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(adminParam);
        else if(res==SysVariable.USERNAME_EXIST) return new CommonResult().validateFailed(SysVariable.USERNAME_EXIST_MES);
        else if(res==SysVariable.SYS_ERROR) throw new UmsWebException(SysVariable.SYS_ERROR_MES);
        else return new CommonResult().failed();
    }

    /**
     * 退出功能
     * @return
     */
    @GetMapping("/logout")
    public CommonResult logout(){
        return new CommonResult().success(null);
    }

    /**
     * 获取权限列表
     * @param adminId
     * @return
     */
    @GetMapping("/permission/{adminId}")
    public CommonResult queryPermissions(@PathVariable("adminId") Long adminId){
        return new CommonResult().success(umsPermissionService.queryByAdminId(adminId));
    }

    /**
     * 添加用户权限
     * @param param
     * @return
     */
    @PostMapping("/permission/update")
    public CommonResult updatePermissions(@Valid AdminPermissionRelationParam param,BindingResult result) throws Exception {
        Integer res = umsPermissionService.addAdminPermissionRelation(param.getAdminId()
                , Arrays.asList(param.getPermissionIds()));
        if(res == SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res == SysVariable.SYS_ERROR) throw new UmsWebException(SysVariable.SYS_ERROR_MES);
        else return new CommonResult().failed();
    }

    /**
     * 查询用户角色列表
     * @param adminId
     * @return
     */
    @GetMapping("/role/{adminId}")
    public CommonResult queryRoles(@PathVariable("adminId") Long adminId){
        return new CommonResult().success(umsRoleService.queryByAdminId(adminId));
    }

    /**
     * 添加或更新用户角色
     * @param param
     * @param result
     * @return
     * @throws Exception
     */
    @PostMapping("/role/update")
    public CommonResult updateRoles(@Valid AdminRoleRelationParam param, BindingResult result) throws Exception {
        Integer res = umsRoleService.addAdminRoleRelation(param.getAdminId()
                , Arrays.asList(param.getRoleIds()));
        if(res == SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res == SysVariable.SYS_ERROR) throw new UmsWebException(SysVariable.SYS_ERROR_MES);
        else return new CommonResult().failed();
    }

    /**
     * 刷新token
     * @param request
     * @return
     */
    @GetMapping("/token/refresh")
    public CommonResult refreshToken(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        String refreshToken = jwtTokenUtil.refreshToken(token);
        if(null == refreshToken) return new CommonResult().validateFailed("token已过期！");
        Map<String,String> map = new HashMap<>();
        map.put("token",refreshToken);
        map.put("tokenHead",tokenHead);
        return new CommonResult().success(map);
    }

    /**
     * 更新密码
     * @param adminPasswordParam
     * @return
     */
    @PostMapping("/updatePassword")
    public CommonResult updatePassword(@Valid @RequestBody UpdateAdminPasswordParam adminPasswordParam,
                                       BindingResult result)
            throws UmsWebException {
        Integer res = umsAdminService.updatePassword(adminPasswordParam.getUsername(),
                adminPasswordParam.getOldPassword(),
                adminPasswordParam.getNewPassword());
        if(res == SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res == SysVariable.USERNAME_ERROR) return new CommonResult().validateFailed("用户名错误");
        else if(res == SysVariable.PASSWORD_ERROR) return new CommonResult().validateFailed("旧密码错误");
        else if(res == SysVariable.SYS_ERROR) throw new UmsWebException(SysVariable.SYS_ERROR_MES);
        else return new CommonResult().failed();
    }
}
