package fun.huanghai.mall.ums.controller;

import fun.huanghai.mall.qo.QueryPageParam;
import fun.huanghai.mall.ums.pojo.UmsAdmin;
import fun.huanghai.mall.ums.pojo.UmsAdminExpand;
import fun.huanghai.mall.ums.service.UmsAdminService;
import fun.huanghai.mall.to.CommonResult;
import fun.huanghai.mall.utils.JwtTokenUtil;
import fun.huanghai.mall.vo.PageInfoVo;
import fun.huanghai.mall.vo.UmsAdminLoginParam;
import fun.huanghai.mall.vo.UmsAdminParam;
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
import java.util.HashMap;
import java.util.Map;

@CrossOrigin//允许跨域请求
@RestController
@RequestMapping("/admin")
public class UmsAdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminController.class);

    @Reference(methods = {
            @Method(name="add",retries = 0)/*新增操作只允许执行一次*/
    })
    private UmsAdminService umsAdminService;

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
        Integer row = umsAdminService.edit(admin);
        if(row > 0) return new CommonResult().success(row);
        else return new CommonResult().failed();
    }

    /**
     * 根据用户名或昵称查找分页数据
     * @param name
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("/list")
    public CommonResult list(@RequestParam(name = "name",required = false) String name,
                             @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize,
                             @RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum) throws Exception {
        PageInfoVo pageInfoVo = umsAdminService.queryPages(new QueryPageParam(pageNum, pageSize, name));

        return new CommonResult().success(pageInfoVo);
    }

    /**
     * 注册后台管理用户
     * @Valid 开启参数校验
     * @param adminParam
     * @param result
     * @return
     */
    @PostMapping("/register")
    public CommonResult register(@Valid @RequestBody UmsAdminParam adminParam, BindingResult result) throws Exception {
        System.out.println(adminParam);
        UmsAdmin admin = new UmsAdmin();
        //拷贝属性
        BeanUtils.copyProperties(adminParam,admin);
        System.out.println(admin);
        Integer row = umsAdminService.add(admin);
        if(row>0) return new CommonResult().success(adminParam);
        else if(row==-1) return new CommonResult().validateFailed("用户名已存在！");
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
        return new CommonResult().success(umsAdminService.queryByAdminId(adminId));
    }
}
