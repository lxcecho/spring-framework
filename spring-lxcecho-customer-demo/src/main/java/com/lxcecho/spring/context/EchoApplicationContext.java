package com.lxcecho.spring.context;

import com.lxcecho.spring.annotation.EchoAutowired;
import com.lxcecho.spring.annotation.EchoController;
import com.lxcecho.spring.annotation.EchoService;
import com.lxcecho.spring.aop.EchoDefaultAopProxyFactory;
import com.lxcecho.spring.aop.config.EchoAopConfig;
import com.lxcecho.spring.aop.support.EchoAdvisedSupport;
import com.lxcecho.spring.beans.EchoBeanWrapper;
import com.lxcecho.spring.beans.config.EchoBeanDefinition;
import com.lxcecho.spring.beans.support.EchoBeanDefinitionReader;
import com.lxcecho.spring.core.EchoBeanFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 职责：完成 Bean 的创建和 DI
 *
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public class EchoApplicationContext implements EchoBeanFactory {

    private EchoBeanDefinitionReader reader;

    private Map<String, EchoBeanDefinition> beanDefinitionMap = new HashMap<String, EchoBeanDefinition>();

    private Map<String, EchoBeanWrapper> factoryBeanInstanceCache = new HashMap<String, EchoBeanWrapper>();
    private Map<String,Object> factoryBeanObjectCache = new HashMap<String, Object>();

    private EchoDefaultAopProxyFactory proxyFactory = new EchoDefaultAopProxyFactory();

    public EchoApplicationContext(String... configLocations) {

        //1、加载配置文件
        reader = new EchoBeanDefinitionReader(configLocations);

        try {
            //2、解析配置文件，封装成BeanDefinition
            List<EchoBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();

            //3、把BeanDefintion缓存起来
            doRegistBeanDefinition(beanDefinitions);

            doAutowrited();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doAutowrited() {
        //调用getBean()
        //这一步，所有的Bean并没有真正的实例化，还只是配置阶段
        for (Map.Entry<String,EchoBeanDefinition> beanDefinitionEntry : this.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            getBean(beanName);
        }
    }

    private void doRegistBeanDefinition(List<EchoBeanDefinition> beanDefinitions) throws Exception {
        for (EchoBeanDefinition beanDefinition : beanDefinitions) {
            if(this.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new Exception("The " + beanDefinition.getFactoryBeanName() + "is exists");
            }
            beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
            beanDefinitionMap.put(beanDefinition.getBeanClassName(),beanDefinition);
        }
    }

    //Bean的实例化，DI是从而这个方法开始的
    public Object getBean(String beanName){
        //1、先拿到BeanDefinition配置信息
        EchoBeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        //2、反射实例化newInstance();
        Object instance = instantiateBean(beanName,beanDefinition);
        //3、封装成一个叫做BeanWrapper
        EchoBeanWrapper beanWrapper = new EchoBeanWrapper(instance);
        //4、保存到IoC容器
        factoryBeanInstanceCache.put(beanName,beanWrapper);
        //5、执行依赖注入
        populateBean(beanName,beanDefinition,beanWrapper);

        return beanWrapper.getWrapperInstance();
    }

    private void populateBean(String beanName, EchoBeanDefinition beanDefinition, EchoBeanWrapper beanWrapper) {
        //可能涉及到循环依赖？
        //A{ B b}
        //B{ A b}
        //用两个缓存，循环两次
        //1、把第一次读取结果为空的BeanDefinition存到第一个缓存
        //2、等第一次循环之后，第二次循环再检查第一次的缓存，再进行赋值

        Object instance = beanWrapper.getWrapperInstance();

        Class<?> clazz = beanWrapper.getWrappedClass();

        //在Spring中@Component
        if(!(clazz.isAnnotationPresent(EchoController.class) || clazz.isAnnotationPresent(EchoService.class))){
            return;
        }

        //把所有的包括private/protected/default/public 修饰字段都取出来
        for (Field field : clazz.getDeclaredFields()) {
            if(!field.isAnnotationPresent(EchoAutowired.class)){ continue; }

            EchoAutowired autowired = field.getAnnotation(EchoAutowired.class);

            //如果用户没有自定义的beanName，就默认根据类型注入
            String autowiredBeanName = autowired.value().trim();
            if("".equals(autowiredBeanName)){
                //field.getType().getName() 获取字段的类型
                autowiredBeanName = field.getType().getName();
            }

            //暴力访问
            field.setAccessible(true);

            try {
                if(this.factoryBeanInstanceCache.get(autowiredBeanName) == null){
                    continue;
                }
                //ioc.get(beanName) 相当于通过接口的全名拿到接口的实现的实例
                field.set(instance,this.factoryBeanInstanceCache.get(autowiredBeanName).getWrapperInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }
        }

    }


    //创建真正的实例对象
    private Object instantiateBean(String beanName, EchoBeanDefinition beanDefinition) {
        String className = beanDefinition.getBeanClassName();
        Object instance = null;
        try {
            if(this.factoryBeanObjectCache.containsKey(beanName)){
                instance = this.factoryBeanObjectCache.get(beanName);
            }else {
                Class<?> clazz = Class.forName(className);
                //2、默认的类名首字母小写
                instance = clazz.getConstructor().newInstance();

                //==================AOP开始=========================
                //如果满足条件，就直接返回Proxy对象
                //1、加载AOP的配置文件
                EchoAdvisedSupport config = instantionAopConfig(beanDefinition);
                config.setTargetClass(clazz);
                config.setTarget(instance);

                //判断规则，要不要生成代理类，如果要就覆盖原生对象
                //如果不要就不做任何处理，返回原生对象
                if(config.pointCutMath()){
                    instance = proxyFactory.createAopProxy(config).getProxy();
                }

                //===================AOP结束========================
                this.factoryBeanObjectCache.put(beanName, instance);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return instance;
    }



    private EchoAdvisedSupport instantionAopConfig(EchoBeanDefinition beanDefinition) {
        EchoAopConfig config = new EchoAopConfig();
        config.setPointCut(this.reader.getConfig().getProperty("pointCut"));
        config.setAspectClass(this.reader.getConfig().getProperty("aspectClass"));
        config.setAspectBefore(this.reader.getConfig().getProperty("aspectBefore"));
        config.setAspectAfter(this.reader.getConfig().getProperty("aspectAfter"));
        config.setAspectAfterThrow(this.reader.getConfig().getProperty("aspectAfterThrow"));
        config.setAspectAfterThrowingName(this.reader.getConfig().getProperty("aspectAfterThrowingName"));
        return new EchoAdvisedSupport(config);
    }

    public Object getBean(Class<?> beanClass){
        return getBean(beanClass.getName());
    }

    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public Properties getConfig() {
        return this.reader.getConfig();
    }
}
