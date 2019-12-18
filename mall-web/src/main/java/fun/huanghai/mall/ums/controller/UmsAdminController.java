package fun.huanghai.mall.ums.controller;

import fun.huanghai.mall.qo.QueryPageParam;
import fun.huanghai.mall.ums.pojo.UmsAdmin;
import fun.huanghai.mall.ums.pojo.UmsAdminExample;
import fun.huanghai.mall.ums.pojo.UmsAdminExpand;
import fun.huanghai.mall.ums.service.UmsAdminService;
import fun.huanghai.mall.to.CommonResult;
import fun.huanghai.mall.utils.JwtTokenUtil;
import fun.huanghai.mall.vo.PageInfoVo;
import fun.huanghai.mall.vo.UmsAdminLoginParam;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin//允许跨域请求
@RestController
@RequestMapping("/admin")
public class UmsAdminController {

    @Reference
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
            UmsAdminExpand adminExpand = umsAdminService.getAdminInfo(username);
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
    public CommonResult del(@PathVariable("id") Long id){
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
                             @RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum){
        UmsAdminExample example = new UmsAdminExample();
        if(StringUtils.isNotEmpty(name)){
            example.createCriteria().andUsernameLike("%"+name+"%");
            example.or(example.createCriteria().andNickNameLike("%"+name+"%"));
        }
        PageInfoVo pageInfoVo = umsAdminService.queryPages(new QueryPageParam(pageNum, pageSize, example));

        return new CommonResult().success(pageInfoVo);
    }
}
