package com.lxcecho.dao.impl;

import com.lxcecho.spring.annotation.EchoBean;
import com.lxcecho.dao.UserDao;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/11
 */
@EchoBean
public class UserDaoImpl  implements UserDao {
    @Override
    public void add() {
        System.out.println("dao.......");
    }

}
