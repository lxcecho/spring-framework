package com.lxcecho.dao.impl;

import com.lxcecho.anno.Bean;
import com.lxcecho.dao.UserDao;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/11
 */
@Bean
public class UserDaoImpl  implements UserDao {
    @Override
    public void add() {
        System.out.println("dao.......");
    }

}
