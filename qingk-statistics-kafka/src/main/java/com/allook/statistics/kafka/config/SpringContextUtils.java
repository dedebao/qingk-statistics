package com.allook.statistics.kafka.config;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Spring容器获取bean工具类
 * 
 * @since v1.0.0
 * @author zhuguoxiao<br>
 *         Created on 2018年5月7日
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
    private static ConfigurableApplicationContext applicationContext;
    private static BeanDefinitionRegistry beanDefinitionRegistry;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = (ConfigurableApplicationContext) applicationContext;
        SpringContextUtils.beanDefinitionRegistry = (DefaultListableBeanFactory) SpringContextUtils.applicationContext.getBeanFactory();
    }

    /**
     * 获取applicationContext对象
     */
    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据bean的id来查找对象
     * 
     * @param id
     *            bean的ID
     * @return 容器中的对象
     */
    public static Object getBeanById(String id) {
        return applicationContext.getBean(id);
    }

    /**
     * 根据bean的class来查找对象
     * 
     * @param clazz
     * @return
     */
    public static <T> T getBeanByClass(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 根据bean的class来查找所有的对象(包括子类)
     * 
     * @param clazz
     * @return
     */
    public static <T> Map<String, T> getBeansByClass(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

    public static void removeBeanById(String id) {
        beanDefinitionRegistry.removeBeanDefinition(id);
    }

}
