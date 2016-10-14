-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 14, 2016 lúc 11:42 SA
-- Phiên bản máy phục vụ: 5.7.14
-- Phiên bản PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qlbh`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `permissionid` int(11) NOT NULL,
  `isdeleted` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id`, `name`, `username`, `password`, `permissionid`, `isdeleted`) VALUES
(1, 'Administrator', 'admin', 'admin', 1, b'0'),
(2, 'Huỳnh Chí Phong', 'hcphong', 'hcphong', 2, b'0'),
(3, 'Tô Chính Tín', 'tctin', 'tctin', 3, b'0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'CPU'),
(2, 'Mainboard'),
(3, 'Storage'),
(4, 'Ram'),
(5, 'Monitor'),
(6, 'Keyboard'),
(7, 'Mouse'),
(8, 'PSU'),
(9, 'Case'),
(10, 'Kit Keyboard & Mouse');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `phonenumber` varchar(45) NOT NULL,
  `company` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`id`, `name`, `phonenumber`, `company`) VALUES
(1, 'Nguyễn Thị Lạ', '0909776724', 'Tin Học Hoàng'),
(2, 'Huỳnh Thị Út', '0909232323', 'Tin Học Linh Trung'),
(3, 'Nguyễn Thị Đẹt', '7878343434', 'Tin Học B'),
(4, 'Nguyễn Hoàng', '6534234567', 'Tin Học B'),
(5, 'Võ Hoài Thanh', '0907653527', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dram`
--

CREATE TABLE `dram` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `dram`
--

INSERT INTO `dram` (`id`, `name`) VALUES
(1, 'Bộ'),
(2, 'Cái'),
(3, 'Món');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `import`
--

CREATE TABLE `import` (
  `id` int(11) NOT NULL,
  `createrid` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `import`
--

INSERT INTO `import` (`id`, `createrid`, `date`) VALUES
(1, 2, '2016-10-01'),
(2, 2, '2016-10-01'),
(3, 2, '2016-10-02'),
(4, 2, '2016-10-03'),
(5, 2, '2016-10-04'),
(6, 2, '2016-10-05');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `importext`
--

CREATE TABLE `importext` (
  `importid` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  `providerid` int(11) NOT NULL,
  `cost` double NOT NULL,
  `num` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `importext`
--

INSERT INTO `importext` (`importid`, `itemid`, `providerid`, `cost`, `num`) VALUES
(1, 1, 1, 24500000, 5),
(1, 2, 2, 5000000, 10),
(1, 3, 3, 15500000, 5),
(2, 4, 1, 4200000, 10),
(2, 7, 4, 6000000, 10),
(2, 10, 2, 500000, 20),
(2, 16, 2, 1550000, 10),
(3, 3, 3, 15700000, 5),
(3, 9, 2, 3000000, 20),
(3, 8, 4, 950000, 6),
(4, 14, 2, 3800000, 10),
(4, 13, 4, 4800000, 5),
(4, 13, 2, 4700000, 5),
(5, 11, 2, 1750000, 10),
(5, 12, 3, 1700000, 5),
(6, 9, 1, 3050000, 10);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `invoice`
--

CREATE TABLE `invoice` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `createrid` int(11) NOT NULL,
  `ispayment` bit(1) NOT NULL DEFAULT b'0',
  `paymentdate` date DEFAULT NULL,
  `customerid` int(11) NOT NULL,
  `isdeleted` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `invoice`
--

INSERT INTO `invoice` (`id`, `date`, `createrid`, `ispayment`, `paymentdate`, `customerid`, `isdeleted`) VALUES
(1, '2016-10-10', 2, b'0', NULL, 1, b'0'),
(2, '2016-10-11', 3, b'0', NULL, 2, b'0'),
(3, '2016-10-11', 2, b'0', NULL, 3, b'0'),
(4, '2016-10-12', 2, b'0', NULL, 4, b'0'),
(5, '2016-10-12', 2, b'0', NULL, 5, b'0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `invoiceext`
--

CREATE TABLE `invoiceext` (
  `invoiceid` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  `cost` double NOT NULL,
  `num` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `invoiceext`
--

INSERT INTO `invoiceext` (`invoiceid`, `itemid`, `cost`, `num`) VALUES
(1, 1, 24999920, 1),
(2, 7, 6299986, 5),
(3, 16, 1800000, 1),
(4, 2, 5199986, 3),
(1, 2, 5199986, 1),
(1, 14, 4000000, 1),
(1, 16, 1800000, 4),
(1, 13, 5000000, 2),
(2, 8, 1049994, 2),
(2, 9, 3199988, 2),
(2, 10, 550000, 2),
(3, 13, 5000000, 1),
(3, 14, 4000000, 1),
(4, 3, 16599990, 3),
(4, 4, 4439996, 1),
(5, 16, 1800000, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `item`
--

CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `cost` double NOT NULL,
  `num` int(11) NOT NULL,
  `dram` int(11) NOT NULL,
  `cat` int(11) NOT NULL,
  `img` varchar(128) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `item`
--

INSERT INTO `item` (`id`, `name`, `cost`, `num`, `dram`, `cat`, `img`) VALUES
(1, 'Intel Core i7-5960X Extreme 3.0GHz - Haswell-E LGA 2011-V3', 24999920, 4, 2, 1, NULL),
(2, 'AMD Vishera FX 9590 4.7GHz ( 5.0GHz Turbo ) ', 5199986, 6, 2, 1, NULL),
(3, 'Intel Xeon E5-2630 V4 2.1GHz - 8 cores LGA 2011-V3', 16599990, 7, 2, 1, NULL),
(4, 'Intel Core i5-6400 2.7GHz (3.3GHz Turbo Boost ) ', 4439996, 9, 2, 1, NULL),
(5, 'Intel Pentium G3450 3.4GHz Haswell LGA 1150', 1519980, 0, 2, 1, NULL),
(6, 'Asus X99-Pro - LGA 2011-V3 Intel X99', 7099994, 0, 2, 2, NULL),
(7, 'Asus X99-A - LGA 2011-V3 Intel X99', 6299986, 5, 2, 2, NULL),
(8, 'Toshiba 1TB 7200rpm - Desktop HDD', 1049994, 4, 2, 3, NULL),
(9, 'Western Digital Caviar Black 2TB -64MB cache Sata 3', 3199988, 28, 2, 3, NULL),
(10, 'Mushkin Enhanced Sodim 4GB ( 1x4GB ) bus 1866 - DDR3 Notebook Ram', 550000, 18, 2, 4, NULL),
(11, 'Mushkin Enhanced Sodim 16GB ( 2x8GB ) bus 1866 - DDR3 Notebook Ram', 1900000, 10, 1, 4, NULL),
(12, 'Gskill Ripjaws V 16GB ( 2x8GB ) Bus 2133 cas 15 - DDR4 Quad Channel', 1900000, 5, 1, 4, NULL),
(13, 'Philips 273G3 27', 5000000, 7, 2, 5, NULL),
(14, 'LG 24MP68VQ Full HD - AH-IPS + LED LCD', 4000000, 12, 2, 5, NULL),
(15, 'Dell UltraSharp UP2715K - 5K 10Bit IPS LCD', 42000000, 0, 2, 5, NULL),
(16, 'infinity Champions - Rainbow Led RGB side Mechanical Blue Gaming Keyboard', 1800000, 4, 2, 6, NULL),
(17, 'infinity Side 108 Black - Side RGB + Rainbow led Mechanical Blue Keyboard', 2000000, 0, 2, 6, NULL),
(18, 'CM Storm Devastator - Gaming keyboard & Mouse combo', 750000, 0, 1, 10, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `permission`
--

CREATE TABLE `permission` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `permission`
--

INSERT INTO `permission` (`id`, `name`) VALUES
(1, 'Administrator'),
(2, 'Manager'),
(3, 'Personel');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `provider`
--

CREATE TABLE `provider` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `provider`
--

INSERT INTO `provider` (`id`, `name`) VALUES
(1, 'FPT'),
(2, 'Viễn Sơn'),
(3, 'Vĩnh Xuân'),
(4, 'Digiworld');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `phonenumber_UNIQUE` (`phonenumber`);

--
-- Chỉ mục cho bảng `dram`
--
ALTER TABLE `dram`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `import`
--
ALTER TABLE `import`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `importext`
--
ALTER TABLE `importext`
  ADD PRIMARY KEY (`importid`,`itemid`,`providerid`);

--
-- Chỉ mục cho bảng `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `invoiceext`
--
ALTER TABLE `invoiceext`
  ADD PRIMARY KEY (`invoiceid`,`itemid`);

--
-- Chỉ mục cho bảng `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `permission`
--
ALTER TABLE `permission`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `provider`
--
ALTER TABLE `provider`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT cho bảng `dram`
--
ALTER TABLE `dram`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT cho bảng `import`
--
ALTER TABLE `import`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT cho bảng `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT cho bảng `item`
--
ALTER TABLE `item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT cho bảng `permission`
--
ALTER TABLE `permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT cho bảng `provider`
--
ALTER TABLE `provider`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
