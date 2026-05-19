CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID，主键',
  `balance` int(11) DEFAULT NULL COMMENT '用户余额',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
CREATE TABLE `t_book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图书ID，主键',
  `price` int(11) DEFAULT NULL COMMENT '图书价格',
  `stock` int(11) DEFAULT NULL COMMENT '图书库存',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图书表';

INSERT INTO t_book (book_id, price, stock) VALUES (1, 100, 10);
INSERT INTO t_user (user_id, balance) VALUES (1, 200);
