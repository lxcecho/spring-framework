package com.lxcecho.anno.tx.controller;

import com.lxcecho.anno.tx.service.BookService;
import com.lxcecho.anno.tx.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/11
 */
@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	/**
	 * 买书的方法：图书 id 和用户 id
	 *
	 * @param bookId 書本 ID
	 * @param userId 用戶 ID
	 */
	public void buyBook(Integer bookId, Integer userId) {
		// 调用 service 方法
		bookService.buyBook(bookId, userId);
	}

	@Autowired
	private CheckoutService checkoutService;

	public void checkout(Integer[] bookIds, Integer userId) {
		checkoutService.checkout(bookIds, userId);
	}

}
