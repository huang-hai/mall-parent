package fun.huanghai.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.huanghai.mall.qo.QueryPageParam;
import fun.huanghai.mall.service.BaseService;
import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.util.SpringUtil;
import fun.huanghai.mall.vo.PageInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T>{

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);

    private Class<? extends Object> aClass;
    private Class<? extends Object> exampleClass;
    private Object obj;

    public void setaClass(Class<? extends Object> aClass) {
        this.aClass = aClass;
        //获取实例对象
        String simpleName = aClass.getSimpleName();
        simpleName = simpleName.substring(0,1).toLowerCase()+simpleName.substring(1);
        obj = SpringUtil.getBean(simpleName);
    }

    public void setExampleClass(Class<? extends Object> exampleClass) {
        this.exampleClass = exampleClass;
    }

    @Override
    public PageInfoVo queryPages(QueryPageParam queryPageParam) {
        try {
            //开始分页
            PageHelper.startPage(queryPageParam.getPageNum(), queryPageParam.getPageSize());
            //反射获取方法
            System.out.println(exampleClass.getClass());
            Method method = aClass.getDeclaredMethod("selectByExample",exampleClass);
            //执行方法
            List<T> datas = (List<T>) method.invoke(obj, queryPageParam.getObj());
            PageInfo<T> pageInfo = new PageInfo<>(datas);
            return PageInfoVo.getVo(pageInfo,queryPageParam.getPageSize());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.queryPages-->NoSuchMethodException",e.getStackTrace());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.queryPages-->IllegalAccessException",e.getStackTrace());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.queryPages-->InvocationTargetException",e.getStackTrace());
        }
        return null;
    }

    /**
     * 根据条件查找
     *
     * @param obj
     * @return
     */
    @Override
    public List<T> queryByCondition(Object obj) {
        try {
            //反射获取方法
            Method method = aClass.getDeclaredMethod("selectByExample",exampleClass);
            //执行方法
            return (List<T>) method.invoke(this.obj, obj);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.queryByCondition-->NoSuchMethodException",e.getStackTrace());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.queryByCondition-->IllegalAccessException",e.getStackTrace());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.queryByCondition-->InvocationTargetException",e.getStackTrace());
        }
        return null;
    }

    @Override
    public T findById(Long id) {
        try {
            //反射获取方法
            Method method = aClass.getDeclaredMethod("selectByPrimaryKey",Long.class);
            T t = (T) method.invoke(obj, id);
            return t;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.findById-->NoSuchMethodException",e.getStackTrace());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.findById-->IllegalAccessException",e.getStackTrace());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.findById-->InvocationTargetException",e.getStackTrace());
        }
        return null;
    }

    @Override
    public Integer add(T t) {
        try {
            //反射获取方法
            Method method = aClass.getDeclaredMethod("insertSelective",t.getClass());
            Integer row = (Integer) method.invoke(obj, t);
            if(row > 0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.add-->NoSuchMethodException",e.getStackTrace());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.add-->IllegalAccessException",e.getStackTrace());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.add-->InvocationTargetException",e.getStackTrace());
        }
        return SysVariable.SYS_ERROR;
    }

    @Override
    public Integer edit(T t) {
        try {
            //反射获取方法
            Method method = aClass.getDeclaredMethod("updateByPrimaryKeySelective",t.getClass());
            Integer row = (Integer) method.invoke(obj, t);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.edit-->NoSuchMethodException",e.getStackTrace());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.edit-->IllegalAccessException",e.getStackTrace());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.edit-->InvocationTargetException",e.getStackTrace());
        }
        return SysVariable.SYS_ERROR;
    }

    @Override
    public Integer del(Long id) {
        try {
            //反射获取方法
            Method method = aClass.getDeclaredMethod("deleteByPrimaryKey",Long.class);
            Integer row = (Integer) method.invoke(obj, id);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.del-->NoSuchMethodException",e.getStackTrace());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.del-->IllegalAccessException",e.getStackTrace());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            LOGGER.error("BaseService.del-->InvocationTargetException",e.getStackTrace());
        }
        return SysVariable.SYS_ERROR;
    }

    /**
     * 系统错误
     * @param e
     * @param name 方法名
     * @return
     */
    protected Integer error(Exception e,String name) {
        e.printStackTrace();
        LOGGER.error("{}.{}-->Exception,{}",this.getClass().getSimpleName(),name,e.getStackTrace());
        return SysVariable.SYS_ERROR;
    }

}
