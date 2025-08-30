package com.lxcecho.xml.ioc.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/10
 */
public class MyFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
