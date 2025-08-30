package com.lxcecho.anno.ioc.dao.impl;

import com.lxcecho.anno.ioc.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/10
 */
@Repository
public class UserDaoImpl  implements UserDao {

    @Override
    public void add() {
        System.out.println("UserDaoImpl........");
    }

}
