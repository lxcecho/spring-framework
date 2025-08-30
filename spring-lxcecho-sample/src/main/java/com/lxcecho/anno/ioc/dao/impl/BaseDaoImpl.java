package com.lxcecho.anno.ioc.dao.impl;

import com.lxcecho.anno.ioc.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/10
 */
@Repository("redisDaoImpl")
public class BaseDaoImpl implements BaseDao {

	@Override
	public void add() {
		System.out.println("BaseDaoImpl redis.........");
	}

}
