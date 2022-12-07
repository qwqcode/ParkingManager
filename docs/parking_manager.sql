-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- 主机： dev-db:3306
-- 生成日期： 2022-12-07 06:38:19
-- 服务器版本： 8.0.30
-- PHP 版本： 8.0.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `parking_manager`
--

-- --------------------------------------------------------

--
-- 表的结构 `cars`
--

CREATE TABLE `cars` (
  `id` int NOT NULL,
  `plate` varchar(20) NOT NULL,
  `plate_type` varchar(20) NOT NULL,
  `user_id` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- 表的结构 `coupons`
--

CREATE TABLE `coupons` (
  `id` int NOT NULL,
  `de_price` decimal(10,2) NOT NULL,
  `user_id` int NOT NULL,
  `is_available` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- 表的结构 `logs`
--

CREATE TABLE `logs` (
  `id` int NOT NULL,
  `type` varchar(20) NOT NULL,
  `content` text NOT NULL,
  `user_id` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- 表的结构 `parks`
--

CREATE TABLE `parks` (
  `id` int NOT NULL,
  `addr` varchar(20) NOT NULL,
  `capacity` int NOT NULL,
  `open_at` datetime NOT NULL,
  `close_at` datetime NOT NULL,
  `status` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- 表的结构 `recs`
--

CREATE TABLE `recs` (
  `id` int NOT NULL,
  `car_id` int NOT NULL,
  `park_id` int NOT NULL,
  `in_at` datetime NOT NULL,
  `out_at` datetime NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- 表的结构 `rec_pays`
--

CREATE TABLE `rec_pays` (
  `id` int NOT NULL,
  `car_id` int NOT NULL,
  `rec_id` int NOT NULL,
  `park_id` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `use_ticket_id` int NOT NULL,
  `use_coupon_id` int NOT NULL,
  `is_use_vip_card` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- 表的结构 `tickets`
--

CREATE TABLE `tickets` (
  `id` int NOT NULL,
  `ticket_key` varchar(20) NOT NULL,
  `preset_id` int NOT NULL,
  `park_id` int NOT NULL,
  `is_available` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- 表的结构 `ticket_presets`
--

CREATE TABLE `ticket_presets` (
  `id` int NOT NULL,
  `cond` varchar(20) NOT NULL,
  `cond_val` varchar(20) NOT NULL,
  `act` varchar(20) NOT NULL,
  `act_val` varchar(20) NOT NULL,
  `is_available` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- 表的结构 `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `phone` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `openid` varchar(50) NOT NULL,
  `birthday` date NOT NULL,
  `gender` varchar(10) NOT NULL,
  `credit` int NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `role` int NOT NULL,
  `status` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- 表的结构 `user_parks`
--

CREATE TABLE `user_parks` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `park_id` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- 表的结构 `vip_cards`
--

CREATE TABLE `vip_cards` (
  `id` int NOT NULL,
  `expire` datetime NOT NULL,
  `type` int NOT NULL,
  `user_id` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 转储表的索引
--

--
-- 表的索引 `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `coupons`
--
ALTER TABLE `coupons`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `parks`
--
ALTER TABLE `parks`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `recs`
--
ALTER TABLE `recs`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `rec_pays`
--
ALTER TABLE `rec_pays`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `ticket_presets`
--
ALTER TABLE `ticket_presets`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `user_parks`
--
ALTER TABLE `user_parks`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `vip_cards`
--
ALTER TABLE `vip_cards`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `cars`
--
ALTER TABLE `cars`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `coupons`
--
ALTER TABLE `coupons`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `parks`
--
ALTER TABLE `parks`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `recs`
--
ALTER TABLE `recs`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `rec_pays`
--
ALTER TABLE `rec_pays`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `ticket_presets`
--
ALTER TABLE `ticket_presets`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `user_parks`
--
ALTER TABLE `user_parks`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `vip_cards`
--
ALTER TABLE `vip_cards`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
