package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.cms.pojo.CmsPrefrenceAreaProductRelation;
import fun.huanghai.mall.cms.pojo.CmsPrefrenceAreaProductRelationExample;
import fun.huanghai.mall.cms.pojo.CmsSubjectProductRelation;
import fun.huanghai.mall.cms.pojo.CmsSubjectProductRelationExample;
import fun.huanghai.mall.cms.service.CmsPrefrenceAreaProductRelationService;
import fun.huanghai.mall.cms.service.CmsSubjectProductRelationService;
import fun.huanghai.mall.dao.*;
import fun.huanghai.mall.pms.pojo.*;
import fun.huanghai.mall.pms.qo.QueryPageExpandParam;
import fun.huanghai.mall.pms.service.PmsProductService;
import fun.huanghai.mall.qo.QueryPageParam;
import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.vo.PageInfoVo;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 商品服务
 */
@Service(methods = {
        @Method(name = "add",retries = 0)
})
public class PmsProductServiceImpl extends BaseServiceImpl<PmsProduct> implements PmsProductService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsSkuStockServiceImpl.class);

    private PmsProductMapper pmsProductMapper;

    @Autowired
    @Qualifier("pmsProductMapper")
    public void setPmsProductMapper(PmsProductMapper pmsProductMapper) {
        this.pmsProductMapper = pmsProductMapper;
        super.setaClass(PmsProductMapper.class);
        super.setExampleClass(PmsProductExample.class);
    }

    //单实例成员变量保存商品id
    private ThreadLocal threadLocal = new ThreadLocal();

    //商品会员价格设置
    @Autowired
    @Qualifier("pmsMemberPriceDaoExpand")
    private PmsMemberPriceDaoExpand pmsMemberPriceDaoExpand;

    //优选专区和商品的关系
    @Reference(methods = {
            @Method(name="add",retries = 0),/*新增操作只允许执行一次*/
            @Method(name="addAll",retries = 0)/*新增操作只允许执行一次*/
    })
    private CmsPrefrenceAreaProductRelationService cmsPrefrenceAreaProductRelationService;

    //商品参数及自定义规格属性
    @Autowired
    @Qualifier("pmsProductAttributeValueDaoExpand")
    private PmsProductAttributeValueDaoExpand pmsProductAttributeValueDaoExpand;

    //商品满减价格设置
    @Autowired
    @Qualifier("pmsProductFullReductionDaoExpand")
    private PmsProductFullReductionDaoExpand pmsProductFullReductionDaoExpand;

    //商品阶梯价格设置
    @Autowired
    @Qualifier("pmsProductLadderDaoExpand")
    private PmsProductLadderDaoExpand pmsProductLadderDaoExpand;

    //商品的sku库存信息
    @Autowired
    @Qualifier("pmsSkuStockDaoExpand")
    private PmsSkuStockDaoExpand pmsSkuStockDaoExpand;

    //专题和商品关系
    @Reference(methods = {
            @Method(name="add",retries = 0),/*新增操作只允许执行一次*/
            @Method(name="addAll",retries = 0)/*新增操作只允许执行一次*/
    })
    private CmsSubjectProductRelationService cmsSubjectProductRelationService;

    @Autowired
    @Qualifier("pmsProductDaoExpand")
    private PmsProductDaoExpand pmsProductDaoExpand;

    @Autowired
    @Qualifier("pmsProductVertifyRecordDaoExpand")
    private PmsProductVertifyRecordDaoExpand pmsProductVertifyRecordDaoExpand;

    //必须使用事务
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer add(PmsProduct pmsProduct) {
        PmsProductExpand productExpand = (PmsProductExpand) pmsProduct;
        //获取当前对象的代理类,使用这个代理对象调用方法使得事务生效
        PmsProductServiceImpl proxy = (PmsProductServiceImpl) AopContext.currentProxy();
        PmsProductWithBLOBs product = new PmsProductWithBLOBs();
        BeanUtils.copyProperties(pmsProduct,product);
        //1、保存商品基本信息
        proxy.addBaseInfo(product);

        //7、保存商品的sku库存信息
        proxy.addAllSkuStock(productExpand.getSkuStockList());

        //以下所有互不影响

        try {
            //2、保存商品会员价格
            proxy.addAllMemberPrice(productExpand.getMemberPriceList());
        } catch (Exception e) {
            error(e,"addAllMemberPrice");
        }

        try {
            //3、保存优选专区和商品的关系
            proxy.addAllPrefrenceAreaProductRelation(productExpand.getPrefrenceAreaProductRelationList());
        } catch (Exception e) {
            error(e,"addAllPrefrenceAreaProductRelation");
        }

        try {
            //4、保存商品参数及自定义规格属性
            proxy.addAllProductAttributeValues(productExpand.getProductAttributeValueList());
        } catch (Exception e) {
            error(e,"addAllProductAttributeValues");
        }

        try {
            //5、保存商品满减价格
            proxy.addAllProductFullReduction(productExpand.getProductFullReductionList());
        } catch (Exception e) {
            error(e,"addAllProductFullReduction");
        }

        try {
            //6、保存商品阶梯价格
            proxy.addAllProductLadder(productExpand.getProductLadderList());
        } catch (Exception e) {
            error(e,"addAllProductLadder");
        }

        try {
            //8、保存专题和商品关系
            proxy.addAllSubjectProductRelation(productExpand.getSubjectProductRelationList());
        } catch (Exception e) {
            error(e,"addAllSubjectProductRelation");
        }

        return SysVariable.SYS_SUCCESS;
    }

    //沿用外方法的事务(add方法)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addBaseInfo(PmsProductWithBLOBs product){
        Integer row = pmsProductMapper.insertSelective(product);
        if(row>0) threadLocal.set(product.getId());
        LOGGER.info("PmsProductServiceImpl--->addBaseInfo:productId={}"
                ,product.getId());
    }

    //不沿用外方法事务,自己重新开启一个新事物
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addAllMemberPrice(List<PmsMemberPrice> memberPrices){
        List<PmsMemberPrice> list = new ArrayList<>();
        Long pid = (Long) threadLocal.get();
        for(PmsMemberPrice mp: memberPrices){
            mp.setProductId(pid);
            list.add(mp);
        }

        pmsMemberPriceDaoExpand.insertAll(list);
        LOGGER.info("PmsProductServiceImpl--->addAllMemberPrice:productId={},memberPrices.size={}"
                ,pid,list.size());
    }

    //不沿用外方法事务,自己重新开启一个新事物
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addAllPrefrenceAreaProductRelation(List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelations){
        List<CmsPrefrenceAreaProductRelation> list = new ArrayList<>();
        Long pid = (Long) threadLocal.get();
        for(CmsPrefrenceAreaProductRelation obj: prefrenceAreaProductRelations){
            obj.setProductId(pid);
            list.add(obj);
        }

        cmsPrefrenceAreaProductRelationService.addAll(list);
        LOGGER.info("PmsProductServiceImpl--->addAllPrefrenceAreaProductRelation:productId={},prefrenceAreaProductRelations.size={}"
                ,pid,list.size());
    }

    //不沿用外方法事务,自己重新开启一个新事物
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addAllProductAttributeValues(List<PmsProductAttributeValue> productAttributeValues){
        List<PmsProductAttributeValue> list = new ArrayList<>();
        Long pid = (Long) threadLocal.get();
        for(PmsProductAttributeValue obj: productAttributeValues){
            obj.setProductId(pid);
            list.add(obj);
        }

        pmsProductAttributeValueDaoExpand.insertAll(list);
        LOGGER.info("PmsProductServiceImpl--->addAllProductAttributeValues:productId={},productAttributeValues.size={}"
                ,pid,list.size());
    }

    //不沿用外方法事务,自己重新开启一个新事物
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addAllProductFullReduction(List<PmsProductFullReduction> productFullReductions){
        List<PmsProductFullReduction> list = new ArrayList<>();
        Long pid = (Long) threadLocal.get();
        for(PmsProductFullReduction obj: productFullReductions){
            obj.setProductId(pid);
            list.add(obj);
        }

        pmsProductFullReductionDaoExpand.insertAll(list);
        LOGGER.info("PmsProductServiceImpl--->addAllProductFullReduction:productId={},productFullReductions.size={}"
                ,pid,list.size());
    }

    //不沿用外方法事务,自己重新开启一个新事物
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addAllProductLadder(List<PmsProductLadder> productLadders){
        List<PmsProductLadder> list = new ArrayList<>();
        Long pid = (Long) threadLocal.get();
        for(PmsProductLadder obj: productLadders){
            obj.setProductId(pid);
            list.add(obj);
        }

        pmsProductLadderDaoExpand.insertAll(list);
        LOGGER.info("PmsProductServiceImpl--->addAllProductLadder:productId={},productLadders.size={}"
                ,pid,list.size());
    }

    //不沿用外方法事务,自己重新开启一个新事物
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addAllSkuStock(List<PmsSkuStock> skuStocks){
        List<PmsSkuStock> list = new ArrayList<>();
        Long pid = (Long) threadLocal.get();
        for(int i = 0; i < skuStocks.size(); i++){
            PmsSkuStock obj = skuStocks.get(i);
            if(StringUtils.isBlank(obj.getSkuCode())){
                //格式为商品id_自增id : 1_1 1_2
                obj.setSkuCode(pid+"_"+i);
            }
            obj.setProductId(pid);
            list.add(obj);
        }

        pmsSkuStockDaoExpand.insertAll(list);
        LOGGER.info("PmsProductServiceImpl--->addAllSkuStock:productId={},skuStocks.size={}"
                ,pid,list.size());
    }

    //不沿用外方法事务,自己重新开启一个新事物
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addAllSubjectProductRelation(List<CmsSubjectProductRelation> subjectProductRelations){
        List<CmsSubjectProductRelation> list = new ArrayList<>();
        Long pid = (Long) threadLocal.get();
        for(CmsSubjectProductRelation obj: subjectProductRelations){
            obj.setProductId(pid);
            list.add(obj);
        }
        cmsSubjectProductRelationService.addAll(list);
        LOGGER.info("PmsProductServiceImpl--->addAllSubjectProductRelation:productId={},subjectProductRelations.size={}"
                ,pid,list.size());
    }

    @Override
    public PageInfoVo queryPages(QueryPageParam queryPageParam) {
        QueryPageExpandParam expandParam = (QueryPageExpandParam) queryPageParam;
        PmsProductExample example = new PmsProductExample();
        PmsProductExample.Criteria criteria = example.createCriteria();
        PmsProductExample.Criteria or = example.createCriteria();
        if(null != expandParam.getBrandId() && expandParam.getBrandId() > 0){
            criteria.andBrandIdEqualTo(expandParam.getBrandId());
            or.andBrandIdEqualTo(expandParam.getBrandId());
        }
        if(null != expandParam.getProductCategoryId() && expandParam.getProductCategoryId() > 0){
            criteria.andProductCategoryIdEqualTo(expandParam.getProductCategoryId());
            or.andProductCategoryIdEqualTo(expandParam.getProductCategoryId());
        }
        if(null != expandParam.getPublishStatus()){
            criteria.andPublishStatusEqualTo(expandParam.getPublishStatus());
            or.andPublishStatusEqualTo(expandParam.getPublishStatus());
        }
        if(null != expandParam.getVerifyStatus()){
            criteria.andVerifyStatusEqualTo(expandParam.getVerifyStatus());
            or.andVerifyStatusEqualTo(expandParam.getVerifyStatus());
        }
        if(StringUtils.isNotEmpty(expandParam.getProductSn())){
            criteria.andProductSnLike("%"+expandParam.getProductSn()+"%");
            or.andProductSnLike("%"+expandParam.getProductSn()+"%");
        }
        if(StringUtils.isNotEmpty(expandParam.getKeyword())){
            criteria.andNameLike("%"+expandParam.getKeyword()+"%");
            or.andKeywordsLike("%"+expandParam.getKeyword()+"%");
            example.or(or);
        }
        queryPageParam.setObj(example);
        return super.queryPages(queryPageParam);
    }

    /**
     * 根据条件查找
     *
     * @param obj
     * @return
     */
    @Override
    public List<PmsProduct> queryByCondition(Object obj) {
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andNameLike("%"+obj+"%")
                .andProductSnLike("%"+obj+"%");
        return super.queryByCondition(example);
    }

    @Override
    public Integer updateDelStatus(Long[] ids, Integer status) {
        try {
            if(ids.length==0) return SysVariable.SYS_FAILURE;
            int row = pmsProductDaoExpand.updateStatus("delete_status", ids, status);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"updateDelStatus");
        }
    }

    @Override
    public Integer updateNewStatus(Long[] ids, Integer status) {
        try {
            if(ids.length==0) return SysVariable.SYS_FAILURE;
            int row = pmsProductDaoExpand.updateStatus("new_status", ids, status);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"updateNewStatus");
        }
    }

    @Override
    public Integer updatePublishStatus(Long[] ids, Integer status) {
        try {
            if(ids.length==0) return SysVariable.SYS_FAILURE;
            int row = pmsProductDaoExpand.updateStatus("publish_status", ids, status);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"updatePublishStatusStatus");
        }
    }

    @Override
    public Integer updateRecommendStatus(Long[] ids, Integer status) {
        try {
            if(ids.length==0) return SysVariable.SYS_FAILURE;
            int row = pmsProductDaoExpand.updateStatus("recommand_status", ids, status);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"updateRecommendStatus");
        }
    }

    @Override
    public Integer updateVerifyStatus(Long[] ids, Integer status, String detail) {
        try {
            if(ids.length==0) return SysVariable.SYS_FAILURE;
            int row = pmsProductDaoExpand.updateStatus("verify_status",ids,status);
            if(row==0) return SysVariable.SYS_FAILURE;
            List<PmsProductVertifyRecord>  list = new ArrayList<>();
            for(Long id:ids){
                PmsProductVertifyRecord vertifyRecord = new PmsProductVertifyRecord();
                vertifyRecord.setDetail(detail);
                vertifyRecord.setCreateTime(new Date());
                vertifyRecord.setProductId(id);
                vertifyRecord.setStatus(status);
                vertifyRecord.setVertifyMan("system");
                list.add(vertifyRecord);
            }
            row = pmsProductVertifyRecordDaoExpand.insertAll(list);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"updateVerifyStatus");
        }
    }

    @Override
    public Integer edit(PmsProduct product) {
        PmsProductServiceImpl proxy = (PmsProductServiceImpl) AopContext.currentProxy();
        PmsProductExpand productExpand = (PmsProductExpand) product;
        threadLocal.set(product.getId());
        PmsProductWithBLOBs productWithBLOBs = new PmsProductWithBLOBs();
        BeanUtils.copyProperties(product,productWithBLOBs);
        //1、更新商品基本信息
        int row = pmsProductMapper.updateByPrimaryKeySelective(productWithBLOBs);
        if(row==0) return SysVariable.SYS_FAILURE;

        //7、更新商品的sku库存信息
        pmsSkuStockDaoExpand.delByCondition("product_id",product.getId());
        proxy.addAllSkuStock(productExpand.getSkuStockList());

        //以下所有互不影响

        try {
            //2、保存商品会员价格
            pmsMemberPriceDaoExpand.delByCondition("product_id",product.getId());
            proxy.addAllMemberPrice(productExpand.getMemberPriceList());
        } catch (Exception e) {
            error(e,"addAllMemberPrice");
        }

        try {
            //3、保存优选专区和商品的关系
            cmsPrefrenceAreaProductRelationService.delByCondition("product_id",product.getId());
            proxy.addAllPrefrenceAreaProductRelation(productExpand.getPrefrenceAreaProductRelationList());
        } catch (Exception e) {
            error(e,"addAllPrefrenceAreaProductRelation");
        }

        try {
            //4、保存商品参数及自定义规格属性
            pmsProductAttributeValueDaoExpand.delByCondition("product_id",product.getId());
            proxy.addAllProductAttributeValues(productExpand.getProductAttributeValueList());
        } catch (Exception e) {
            error(e,"addAllProductAttributeValues");
        }

        try {
            //5、保存商品满减价格
            pmsProductFullReductionDaoExpand.delByCondition("product_id",product.getId());
            proxy.addAllProductFullReduction(productExpand.getProductFullReductionList());
        } catch (Exception e) {
            error(e,"addAllProductFullReduction");
        }

        try {
            //6、保存商品阶梯价格
            pmsProductLadderDaoExpand.delByCondition("product_id",product.getId());
            proxy.addAllProductLadder(productExpand.getProductLadderList());
        } catch (Exception e) {
            error(e,"addAllProductLadder");
        }

        try {
            //8、保存专题和商品关系
            cmsSubjectProductRelationService.delByCondition("product_id",product.getId());
            proxy.addAllSubjectProductRelation(productExpand.getSubjectProductRelationList());
        } catch (Exception e) {
            error(e,"addAllSubjectProductRelation");
        }

        return SysVariable.SYS_SUCCESS;
    }

    @Override
    public PmsProduct findById(Long id) {
        PmsProductExpand product = pmsProductDaoExpand.queryById(id);

        //查询优选专区和商品的关系
        CmsPrefrenceAreaProductRelationExample prefrenceAreaProductRelationExample = new CmsPrefrenceAreaProductRelationExample();
        prefrenceAreaProductRelationExample.createCriteria().andProductIdEqualTo(id);
        List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList = cmsPrefrenceAreaProductRelationService.queryByCondition(prefrenceAreaProductRelationExample);
        product.setPrefrenceAreaProductRelationList(prefrenceAreaProductRelationList);

        //查询专题和商品关系
        CmsSubjectProductRelationExample subjectProductRelationExample = new CmsSubjectProductRelationExample();
        subjectProductRelationExample.createCriteria().andProductIdEqualTo(id);
        List<CmsSubjectProductRelation> subjectProductRelations = cmsSubjectProductRelationService.queryByCondition(subjectProductRelationExample);
        product.setSubjectProductRelationList(subjectProductRelations);
        return product;
    }
}
