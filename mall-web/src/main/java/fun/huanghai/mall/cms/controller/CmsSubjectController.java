package fun.huanghai.mall.cms.controller;

import fun.huanghai.mall.cms.service.CmsSubjectService;
import fun.huanghai.mall.to.CommonResult;
import fun.huanghai.mall.vo.QueryPageExpandParam;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/subject")
@RestController
public class CmsSubjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsSubjectController.class);

    @Reference(methods = {
            @Method(name="add",retries = 0)/*新增操作只允许执行一次*/
    })
    private CmsSubjectService cmsSubjectService;

    /**
     * 获取所有商品专题
     * @return
     */
    @GetMapping("/listAll")
    public CommonResult listAll(){
        return new CommonResult().success(cmsSubjectService.queryByCondition(null));
    }

    /**
     * 按商品专题名获取分页数据
     * @param queryPageExpandParam
     * @return
     */
    @GetMapping("/list")
    public CommonResult list(QueryPageExpandParam queryPageExpandParam){
        queryPageExpandParam.setObj(queryPageExpandParam.getKeyword());
        return new CommonResult().success(cmsSubjectService.queryPages(queryPageExpandParam));
    }
}
