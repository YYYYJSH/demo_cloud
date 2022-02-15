package com.yjsh.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {
    /**
     * 应用程序上下文对象
     * 获取IoC容器内部的对象的时候要结合上下文获取
     */
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext=applicationContext;
    }

    /**
     * 调用spring的上下文，获取容器内对应类型的对象
     */
    public static <T>  T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public static void main(String[] args) {
        System.out.println((int)Character.MAX_VALUE);
    }
}
