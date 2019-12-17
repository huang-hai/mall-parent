package fun.huanghai.mall.controller;

import fun.huanghai.mall.pojo.UmsAdmin;
import fun.huanghai.mall.service.UmsAdminService;
import fun.huanghai.mall.to.CommonResult;
import fun.huanghai.mall.utils.JwtTokenUtil;
import fun.huanghai.mall.vo.UmsAdminLoginParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin//允许跨域请求
@RestController
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
    @PostMapping("/admin/login")
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
    @GetMapping("/admin/info")
    public CommonResult getUserInfo(HttpServletRequest request){
        String cusToken = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUserNameFromToken(cusToken.replace(tokenHead,""));
        if(null == username) return new CommonResult().validateFailed("token无效或已过期");
        else {
            UmsAdmin admin = umsAdminService.getUserInfo(username);
            return new CommonResult().success(admin);
        }
    }
}
