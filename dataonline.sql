-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 25, 2023 lúc 05:28 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `dataonline`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `iddonhang` int(11) NOT NULL,
  `idsp` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `gia` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`iddonhang`, `idsp`, `soluong`, `gia`) VALUES
(9, 4, 1, '25000'),
(10, 4, 1, '20500000'),
(10, 2, 1, '46600000'),
(11, 4, 1, '20500000'),
(11, 5, 1, '13950000'),
(15, 4, 1, '20500000'),
(16, 2, 1, '46600000'),
(17, 4, 1, '20500000'),
(18, 4, 1, '20500000'),
(19, 4, 1, '20500000'),
(20, 4, 1, '20500000'),
(21, 2, 1, '46600000'),
(22, 2, 1, '46600000'),
(23, 1, 1, '12200000'),
(24, 4, 2, '20500000'),
(24, 5, 1, '13950000'),
(25, 4, 1, '20500000'),
(26, 6, 1, '15499000'),
(26, 2, 1, '46600000'),
(26, 3, 1, '24280000'),
(27, 1, 1, '12200000'),
(28, 30, 1, '500');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `diachi` text NOT NULL,
  `sodienthoai` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `soluong` int(11) NOT NULL,
  `tongtien` varchar(255) NOT NULL,
  `trangthai` int(2) NOT NULL DEFAULT 0,
  `momo` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `iduser`, `diachi`, `sodienthoai`, `email`, `soluong`, `tongtien`, `trangthai`, `momo`) VALUES
(9, 2, 'duong', '123456', 'duong@gmail.com', 1, '25000', 0, ''),
(10, 7, 'duong11', '123456', 'duong4', 2, '67100000', 0, ''),
(11, 7, 'Bach Sam', '123456', 'duong4', 2, '34450000', 0, ''),
(12, 7, 'new', '123456', 'duong4', 0, '13950000', 0, ''),
(13, 7, 'new', '123456', 'duong4', 0, '12200000', 0, ''),
(14, 7, 'new', '123456', 'duong4', 0, '20500000', 3, ''),
(15, 7, 'new', '123456', 'duong4', 1, '20500000', 0, ''),
(16, 7, 'ok', '123456', 'duong4', 1, '46600000', 0, ''),
(17, 9, 'test', '0123456789', 'user1@gmail.com', 1, '20500000', 0, ''),
(18, 9, 'test', '0123456789', 'user1@gmail.com', 1, '20500000', 0, ''),
(19, 9, 'test', '0123456789', 'user1@gmail.com', 1, '20500000', 2, ''),
(20, 9, 'tam', '0123456789', 'user1@gmail.com', 1, '20500000', 0, ''),
(21, 9, 'ok', '0123456789', 'user1@gmail.com', 1, '46600000', 0, ''),
(22, 9, 'test ok', '0123456789', 'user1@gmail.com', 1, '46600000', 0, ''),
(23, 9, 'test', '0123456789', 'user1@gmail.com', 1, '12200000', 2, ''),
(24, 9, '115 test', '0123456789', 'user1@gmail.com', 3, '54950000', 0, ''),
(25, 9, 'ok', '0123456789', 'user1@gmail.com', 1, '20500000', 1, ''),
(26, 9, '14 THANG 5', '0123456789', 'user1@gmail.com', 3, '86379000', 3, ''),
(27, 9, 'ok', '0123456789', 'user1@gmail.com', 1, '12200000', 1, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(100) NOT NULL,
  `hinhanh` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `hinhanh`) VALUES
(1, 'Home', 'https://ngochieu.name.vn/img/home.png'),
(2, 'Mobile', 'https://ngochieu.name.vn/img/mobile.png'),
(3, 'Laptop', 'https://ngochieu.name.vn/img/laptop.png'),
(4, 'Thông tin', 'https://ngochieu.name.vn/img/info.png'),
(5, 'Liên hệ', 'https://ngochieu.name.vn/img/contact.png'),
(6, 'Đơn hàng', 'https://media.istockphoto.com/id/1253479218/vi/vec-to/bi%E1%BB%83u-t%C6%B0%E1%BB%A3ng-vector-l%E1%BB%8Bch-s%E1%BB%AD-mua-h%C3%A0ng-b%E1%BB%8B-c%C3%B4-l%E1%BA%ADp-tr%C3%AAn-n%E1%BB%81n-tr%E1%BA%AFng.jpg?s=1024x1024&w=is&k=20&c=Cc5u5q3IjnKg9vdDPz9vJzMpKmDqP7BXp9sKQ5jmdew=');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanphammoi`
--

CREATE TABLE `sanphammoi` (
  `id` int(11) NOT NULL,
  `tensp` varchar(250) NOT NULL,
  `giasp` varchar(100) NOT NULL,
  `hinhanh` text NOT NULL,
  `mota` text NOT NULL,
  `loai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanphammoi`
--

INSERT INTO `sanphammoi` (`id`, `tensp`, `giasp`, `hinhanh`, `mota`, `loai`) VALUES
(1, 'Laptop Dell Vostro V3568', '12200000', 'http://mauweb.monamedia.net/hanoicomputer/wp-content/uploads/2017/12/dell-V3568-XF6C61-01.jpg', 'Chip: Intel Core i5-7200U\nRAM: DDR4 4GB (2 khe cắm)\nỔ cứng: HDD 1TB\nChipset đồ họa: Intel HD Graphics 620\nMàn hình: 15.6 Inches\nHệ điều hành: Free Dos\nPin: 4 Cell Lithium-ion', 2),
(2, 'Apple Macbook Pro 2020', '46600000', 'https://cdn.tgdd.vn/Files/2020/12/21/1315201/macbook10_800x450.jpg', 'Bộ xử lý: Intel Core i5 dual-core 3.1GHz, Turbo Boost up to 3.5GHz, with 64MB of eDRAM\nRAM: 8GB 2133MHz LPDDR3 memory\nỔ cứng SSD: 512GB\nCard đồ hoạ: Intel Iris Plus Graphics 650', 2),
(3, 'Laptop HP Envy 13-ad074TU', '24280000', 'http://mauweb.monamedia.net/hanoicomputer/wp-content/uploads/2017/12/hp-envy-13-01.jpg', 'CPU Intel Core i7-7500U 2.7GHz up to 3.5GHz 4MB\nRAM 8GB LPDDR3 Onboard\nĐĩa cứng 256 GB PCIe® NVMe™ M.2 SSD\nCard đồ họa Intel® HD Graphics 620\nMàn hình 13.3 inch FHD (1920 x 1080) diagonal IPS BrightView micro-edge WLED-backlit', 2),
(4, 'Samsung Galaxy Note 20 Ultra 5G', '20500000', 'https://cdn.tgdd.vn/Products/Images/42/230529/iphone-13-pro-max-sierra-blue-600x600.jpg', 'Kích thước màn hình 6.9 inches\nCông nghệ màn hình Dynamic AMOLED\nCamera sau 108 MP, f/1.8, 26mm (wide), 1/1.33\"\", 0.8µm, PDAF, Laser AF, OIS\n12 MP, f/3.0, 103mm (periscope telephoto), 1.0µm, PDAF, OIS, 5x optical zoom, 50x hybrid zoom\n12 MP, f/2.2, 13mm (ultrawide), 1/2.55\"\", 1.4µm\nCamera trước 10 MP, f/2.2, 26mm (wide), 1/3.2\"\", 1.22µm, Dual Pixel PDAF\nChipset Exynos 990 (7 nm+)\nDung lượng RAM 12 GB', 1),
(5, 'Samsung-Galaxy-S21', '13950000', 'https://images.samsung.com/fr/smartphones/galaxy-note20/buy/carousel/mobile/005-galaxynote20-mysticbronze.jpg', 'Kích thước màn hình 6.4\" Dynamic AMOLED 2X (2340 x 1080)Kích thước tổng thể 155.7 x 74.5 x 7.9mmTrọng lượng177g. Tần số quét lớn nhất 120 HzCamera sau, Camera trước 32MP Pin 4,500mAh(typical) RAM6GB/8GBBộ nhớ 128GB/256GB', 1),
(6, 'Laptop Asus X515EA-EJ1046W', '15499000', 'https://cdn.tgdd.vn/Products/Images/42/267211/Samsung-Galaxy-S21-FE-tim-600x600.jpg', 'CPU: Intel core i5 1135G7. RAM: 8GB. Ổ cứng: 512GB SSD. VGA: Onboard. Màn hình: 15.6 inch FHD. HĐH: Win 11. Màu: Bạc', 2),
(7, 'Laptop Asus VivoBook M513UA-EJ710W', '15649000', 'https://hanoicomputercdn.com/media/product/70200_laptop_asus_vivobook_m513ua_8.png', 'CPU: AMD Ryzen 7 5700U (1.8Ghz upto 4.3GHz, 16MB l3, 4MB L2)\nRAM: 8GB DDR4 onboard + 8GB cắm rời\nỔ cứng: 512GB M.2 NVMe™ PCIe® 3.0 SSD (không hỗ trợ ổ 2.5)\nVGA: AMD Radeon Graphics\nMàn hình: 15.6 WUXGA (1920 x 1200) 16:10 , IPS, 300nits, 45% NTSC\nMàu sắc: Bạc', 2),
(8, 'Laptop Dell Vostro 3520', '17599000', 'https://hanoicomputercdn.com/media/product/69589_laptop_dell_vostro_3520_25.png', 'CPU: Intel Core i5 1235U (upto 4.4Ghz/12MB cache)\nRAM: 8GB DDR4 2666Mhz (1*8GB)\nỔ cứng: 512GB PCIe NVMe SSD\nVGA: Intel UHD Graphics\nMàn hình: 15.6 inch FHD (1920 x 1080) 250 nits WVA Anti-Glare LED Backlit\nMàu sắc: Xám', 2),
(9, 'Laptop HP Pavilion 15-eg2057TU', '14999000', 'https://hanoicomputercdn.com/media/product/67297_laptop_hp_pavilion_15_10.jpeg', 'CPU: Intel® Core™ i5-1240P (upto 4.40 GHz, 16MB)\nRAM: 8GB (2 x 4GB) DDR4-3200 MHz ( 2 khe)\nỔ cứng: 256GB PCIe® NVMe™ M.2 SSD\nVGA: Intel®iris XE\nMàn hình: 15.6 inch FullHD (1920 x 1080), IPS, 250 nits, 45% NTSC\nMàu sắc: Bạc', 2),
(10, 'Laptop Lenovo Thinkpad X1 Carbon Gen 9', '17599000', 'https://hanoicomputercdn.com/media/product/70159_laptop_lenovo_thinkpad_x1_carbon_gen_9_11.png', 'CPU: Intel® Core ™ i7-1165G7 (upto 4.70 GHz, 12MB)\nRAM: 32GB Soldered LPDDR4x-4266Mhz (Không nâng cấp)\nỔ cứng: 1TB SSD M.2 2280 PCIe x4 NVMe Opal2\nVGA: Integrated Intel Iris Xe Graphics\nMàn hình: 14 inch WUXGA (1920x1200) IPS 400nits Anti-glare Cảm ứng\nPin: 4 Cell, 57Wh', 2),
(11, 'iPhone 12 128GB Xanh lá', '17999000', 'https://hanoicomputercdn.com/media/product/64657_iphone_12_2.jpg', 'Công nghệ màn hình: OLED\nĐộ phân giải: 1170 x 2532 Pixels, 2 camera 12 MP, 12 MP\nMàn hình rộng: 6.1\"\"\nHệ điều hành: iOS 14\nChip xử lý (CPU): Apple A14 Bionic 6 nhân\nBộ nhớ trong (ROM): 128GB', 1),
(12, 'Máy Điện Thoại Alcatel H3P', '2399000', 'https://hanoicomputercdn.com/media/product/68239_may_dien_thoai_alcatel_h3p__2_.jpg', 'Điện thoại song công có màn hình B&W 2,8 inch có đèn nền\nCác linh kiện và CPU được chọn lọc để có hiệu suất mạnh mẽ\nTiêu thụ điện năng thấp\nNhiều phím dòng / có thể lập trình hơn và phím quay số nhanh\nCác cổng kết nối bên ngoài\nKhả năng tương thích phong phú có thể dễ dàng triển khai', 1),
(14, 'iPhone 13 Pro 128GB Vàng', '27399000', 'https://hanoicomputercdn.com/media/product/64690_iphone_13_pro_max_2.png', 'Công nghệ màn hình: OLED\nĐộ phân giải: 1170 x 2532 Pixels, 2 camera 12 MP, 12 MP\nMàn hình rộng: 6.1\"\"\nHệ điều hành: iOS 14\nChip xử lý (CPU): Apple A14 Bionic 6 nhân\nBộ nhớ trong (ROM): 128GB', 1),
(15, 'Samsung Galaxy S21 FE 5G', '12199000', 'https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/42/298377/samsung-galaxy-a34-5g-xanh-thumb-600x600.jpg', 'Kích thước màn hình 6.1 inches\nCông nghệ màn hình OLED\nCamera sau Camera góc rộng: 12MP, f/1.6\nCamera góc siêu rộng: 12MP, ƒ/2.4\nCamera trước 12MP, f/2.2\nChipset Apple A15\nDung lượng RAM 4 GB', 1),
(16, 'OPPO Reno8 T 5G 256GB', '30299000', 'https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/42/303575/xiaomi-redmi-12c-grey-thumb-600x600.jpg', 'Màn hình 6.78 inch, AMOLED, FHD+, 2448 x 1080 Pixels\nCamera sau 50.0 MP + 13.0 MP + 5.0 MP\nCamera Selfie 12.0 MP\nRAM 12 GB\nBộ nhớ trong 256 G\nCPU Snapdragon 8+ Gen 1', 1),
(17, 'Samsung Galaxy A34 5G 256GB', '15649000', 'https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/42/301642/oppo-reno8t-vang1-thumb-600x600.jpg', 'Công nghệ màn hình: OLED\nĐộ phân giải: 1170 x 2532 Pixels, 2 camera 12 MP, 12 MP\nMàn hình rộng: 6.1\"\"\nHệ điều hành: iOS 14\nChip xử lý (CPU): Apple A14 Bionic 6 nhân\nBộ nhớ trong (ROM): 128GB', 1),
(18, 'Samsung Galaxy A23 6GB', '2399000', 'https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/42/246199/samsung-galaxy-a33-5g-thumb-new-1-600x600.jpg', 'Kích thước màn hình 6.9 inches\nCông nghệ màn hình Dynamic AMOLED\nCamera sau 108 MP, f/1.8, 26mm (wide), 1/1.33\"\"\"\", 0.8µm, PDAF, Laser AF, OIS\n12 MP, f/3.0, 103mm (periscope telephoto), 1.0µm, PDAF, OIS, 5x optical zoom, 50x hybrid zoom\n12 MP, f/2.2, 13mm (ultrawide), 1/2.55\"\"\"\", 1.4µm\nCamera trước 10 MP, f/2.2, 26mm (wide), 1/3.2\"\"\"\", 1.22µm, Dual Pixel PDAF\nChipset Exynos 990 (7 nm+)\nDung lượng RAM 12 GB', 1),
(19, 'Vivo Y02s 32GB', '50349000', 'https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/42/245261/Xiaomi-redmi-note-11-blue-600x600.jpg', 'Kích thước màn hình 6.1 inches\nCông nghệ màn hình OLED\nCamera sau Camera góc rộng: 12MP, f/1.6\nCamera góc siêu rộng: 12MP, ƒ/2.4\nCamera trước 12MP, f/2.2\nChipset Apple A15\nDung lượng RAM 4 GB', 1),
(20, 'Samsung Galaxy A33 5G 6GB', '17599000', 'https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/42/267211/Samsung-Galaxy-S21-FE-vang-1-2-600x600.jpg', 'Điện thoại song công có màn hình B&W 2,8 inch có đèn nền\nCác linh kiện và CPU được chọn lọc để có hiệu suất mạnh mẽ\nTiêu thụ điện năng thấp\nNhiều phím dòng / có thể lập trình hơn và phím quay số nhanh\nCác cổng kết nối bên ngoài\nKhả năng tương thích phong phú có thể dễ dàng triển khai', 1),
(30, 'duong', '500', '30.jpg', 'dep trai', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(250) NOT NULL,
  `pass` varchar(250) NOT NULL,
  `username` varchar(100) NOT NULL,
  `mobile` varchar(15) NOT NULL,
  `uid` text NOT NULL,
  `token` text NOT NULL,
  `status` int(2) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `email`, `pass`, `username`, `mobile`, `uid`, `token`, `status`) VALUES
(1, 'hoang@gmail.com', '123456', 'hoang', '0123456789', '', '', 0),
(2, 'duong@gmail.com', '123456', 'duong', '0123456789', '', '', 0),
(3, 'duong', '123456', 'duong', '0123456789', '', '', 0),
(4, 'duong1', '123456', 'duong1', '123456', '', '', 0),
(5, 'duong2', '123456', 'duong2', '123456', '', '', 0),
(6, 'duong3', '123456', 'duong3', '123456', '', '', 0),
(7, 'duong4', '123456', 'duong4', '123456', '', 'caXypdwvQM-pI6l_SL0wXS:APA91bFsu_2XBV5GqGxf_Ppud3WL_UC_Y0h6-WGHMA9-BSoLZfs4hXw4cZle5f2iC0Xe2OW_bxnEEOHtL1qjyaO9peLOW0wvCf_SQAeEyBIAClnYSob_O9nSmLyBHfSL-YPfSP1IeafX', 0),
(8, 'duongdbs@gmail.com', '123456', 'duong', '0969385325', 'hBvDv3DoujXIOvUuSG4tofFjTFo1', 'cc-_xpazS1CrDYLB97yoP_:APA91bEifvxGwuDnqVtLRV8etYk1naOKpduObqQaPsi8ANe9P_hvqd-ZJy7vvKAmXIznjst7vTCJScpxqw9UsiMVeV1nxDSiu5JeJ6md7chioACykmoGEwfgrtWl5IjpKKRoYwZ3AEVI', 1),
(9, 'user1@gmail.com', '123456', 'User name', '0123456789', '87H381K9jHQCO1eZRSrwmRUy7l33', 'caXypdwvQM-pI6l_SL0wXS:APA91bFsu_2XBV5GqGxf_Ppud3WL_UC_Y0h6-WGHMA9-BSoLZfs4hXw4cZle5f2iC0Xe2OW_bxnEEOHtL1qjyaO9peLOW0wvCf_SQAeEyBIAClnYSob_O9nSmLyBHfSL-YPfSP1IeafX', 0),
(10, 'user2@gmail.com', '123456', 'user1', '123456', 'lUjKXk6DXwfi8kFOo0RwWFzORAl1', 'caXypdwvQM-pI6l_SL0wXS:APA91bFsu_2XBV5GqGxf_Ppud3WL_UC_Y0h6-WGHMA9-BSoLZfs4hXw4cZle5f2iC0Xe2OW_bxnEEOHtL1qjyaO9peLOW0wvCf_SQAeEyBIAClnYSob_O9nSmLyBHfSL-YPfSP1IeafX', 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanphammoi`
--
ALTER TABLE `sanphammoi`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `sanphammoi`
--
ALTER TABLE `sanphammoi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
