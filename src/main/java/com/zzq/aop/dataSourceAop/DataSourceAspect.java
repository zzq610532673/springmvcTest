package com.zzq.aop.dataSourceAop;

import com.zzq.utils.DataSourceContextHolder;
import com.zzq.utils.DynamicDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

public class DataSourceAspect {
    
    public void intercept(JoinPoint joinPoint) {
        Class<?> target = joinPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        for (Class<?> clazz : target.getInterfaces()) {
            resolveDataSource(clazz, signature.getMethod());
        }
        resolveDataSource(target, signature.getMethod());
    }

    private void resolveDataSource(Class<?> clazz, Method method) {
        Class<?>[] types = method.getParameterTypes();
        if (clazz.isAnnotationPresent(DataSource.class)) {
            DataSource dataSource = clazz.getAnnotation(DataSource.class);
            DataSourceContextHolder.setCustomerType(dataSource.value());
        }
        //方法注解可以覆盖类型注解
        Method m = null;
        try {
            m = clazz.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource dataSource = m.getAnnotation(DataSource.class);
                DataSourceContextHolder.setCustomerType(dataSource.value());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        
    }

}
