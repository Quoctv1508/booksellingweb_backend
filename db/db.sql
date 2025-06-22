INSERT INTO `user` (`shipping_address`, `billing_address`, `email`, `gender`, `last_name`, `password`, `number_phone`, `first_name`, `user_name`) VALUES
('123 Đường ABC', '456 Đường XYZ', 'nguoidung1@email.com', 'M', 'Nguyen Van', 'matkhau1', '123456789', 'Thanh', 'user1'),
('456 Đường XYZ', '789 Đường DEF', 'nguoidung2@email.com', 'F', 'Tran Thi', 'matkhau2', '987654321', 'Lan', 'user2'),
('789 Đường DEF', '123 Đường ABC', 'nguoidung3@email.com', 'M', 'Le Van', 'matkhau3', '555555555', 'Tuan', 'user3'),
('101 Đường GHI', '202 Đường JKL', 'nguoidung4@email.com', 'M', 'Pham Van', 'matkhau4', '666666666', 'Hoa', 'user4'),
('303 Đường MNO', '404 Đường PQR', 'nguoidung5@email.com', 'M', 'Hoang Van', 'matkhau5', '777777777', 'Tinh', 'user5');


INSERT INTO `role` (`title`) VALUES
('Admin'),
('Staff'),
('User');


INSERT INTO `user_role` (`role_id`, `user_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(3, 4),
(3, 5);


INSERT INTO `delivery_method` (`delivery_fee`, `description`, `title`) VALUES
(10000, 'Giao hàng tận nơi', 'Giao hàng tận nơi'),
(0, 'Tự lấy hàng tại cửa hàng', 'Tự lấy hàng tại cửa hàng');


INSERT INTO `payment_method` (`payment_fee`, `description`, `title`) VALUES
(5000, 'Thanh toán khi nhận hàng', 'Thanh toán khi nhận hàng'),
(0, 'Thanh toán qua thẻ tín dụng', 'Thanh toán qua thẻ tín dụng');


INSERT INTO `category` (`title`) VALUES
('Khoa học'),
('Tiểu thuyết'),
('Tâm lý học'),
('Lịch sử'),
('Huyền bí');


INSERT INTO `book` (`isbn`, `sale_price`, `listed_price`, `description`, `quantity`, `title`, `author`, `avg_rank`) VALUES
('1234567890', 15999, 19990, 'Sách về khoa học', 20, 'Cuốn sách về Khoa học', 'Nguyen Van A', 4.5),
('2345678901', 12999, 15990, 'Sách về tiểu thuyết', 15, 'Cuốn sách về Tiểu thuyết', 'Tran Thi B', 4.2),
('3456789012', 10000, 13000, 'Sách về tâm lý học', 30, 'Cuốn sách về Tâm lý học', 'Le Van C', 4.8),
('4567890123', 18000, 22000, 'Sách về lịch sử', 25, 'Cuốn sách về Lịch sử', 'Pham Van D', 4.1),
('5678901234', 14999, 18999, 'Sách về huyền bí', 18, 'Cuốn sách về Huyền bí', 'Hoang Van E', 4.6);

INSERT INTO `image` (`data`, `link`, `icon`, `title`, `book_id`) VALUES
('Dữ liệu ảnh 1', 'duongdan1.jpg', 0, 'Ảnh sách 1', 1),
('Dữ liệu ảnh 2', 'duongdan2.jpg', 0, 'Ảnh sách 2', 2),
('Dữ liệu ảnh 3', 'duongdan3.jpg', 0, 'Ảnh sách 3', 3),
('Dữ liệu ảnh 4', 'duongdan4.jpg', 0, 'Ảnh sách 4', 4),
('Dữ liệu ảnh 5', 'duongdan5.jpg', 0, 'Ảnh sách 5', 5);


INSERT INTO `book_category` (`category_id`, `book_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);


INSERT INTO `book_order` (`delivery_fee`, `payment_fee`, `billing_address`, `shopping_address`, `created_at`, `total_price`, `total_product_price`, `delivery_method_id`, `payment_method_id`, `user_id`) VALUES
(5000, 0, '123 Đường ABC', '456 Đường XYZ', '2023-09-20', 25990, 15990, 1, 1, 1),
(3000, 2500, '456 Đường XYZ', '789 Đường DEF', '2023-09-21', 32490, 12990, 2, 2, 2),
(4500, 0, '789 Đường DEF', '123 Đường ABC', '2023-09-22', 19490, 9990, 1, 1, 3),
(2000, 2500, '101 Đường GHI', '202 Đường JKL', '2023-09-23', 24490, 18990, 2, 2, 4),
(7000, 0, '303 Đường MNO', '404 Đường PQR', '2023-09-24', 62990, 29990, 1, 1, 5);


INSERT INTO `order_detail` (`sale_price`, `quantity`, `order_id`, `book_id`) VALUES
(15990, 2, 1, 1),
(12990, 3, 1, 2),
(9990, 1, 2, 3),
(18990, 4, 3, 4),
(14990, 2, 4, 5);