package com.lxcecho.anno.tx.service;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/11
 */
public interface CheckoutService {

	/**
	 * 买多本书的方法
	 *
	 * @param bookIds
	 * @param userId
	 */
	void checkout(Integer[] bookIds, Integer userId);

}
