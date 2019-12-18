package fun.huanghai.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.huanghai.mall.qo.QueryPageParam;
import fun.huanghai.mall.service.BaseService;
import fun.huanghai.mall.util.SpringUtil;
import fun.huanghai.mall.vo.PageInfoVo;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T>{

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
        //开始分页
        PageHelper.startPage(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        try {
            //反射获取方法
            System.out.println(exampleClass.getClass());
            Method method = aClass.getDeclaredMethod("selectByExample",exampleClass);
            //执行方法
            List<T> datas = (List<T>) method.invoke(obj, queryPageParam.getObj());
            PageInfo<T> pageInfo = new PageInfo<>(datas);
            return PageInfoVo.getVo(pageInfo,queryPageParam.getPageSize());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
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
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer add(T t) {
        try {
            //反射获取方法
            Method method = aClass.getDeclaredMethod("insert",t.getClass());
            Integer row = (Integer) method.invoke(obj, t);
            return row;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer edit(T t) {
        try {
            //反射获取方法
            Method method = aClass.getDeclaredMethod("updateByPrimaryKeySelective",t.getClass());
            Integer row = (Integer) method.invoke(obj, t);
            return row;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer del(Integer id) {
        try {
            //反射获取方法
            Method method = aClass.getDeclaredMethod("deleteByPrimaryKey",Long.class);
            Integer row = (Integer) method.invoke(obj, id);
            return row;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
