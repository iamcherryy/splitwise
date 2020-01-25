-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 25, 2020 at 10:40 PM
-- Server version: 5.6.34-log
-- PHP Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `splitwise`
--

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role`) VALUES
(1, 'ADMIN'),
(5, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `spendings`
--

CREATE TABLE `spendings` (
  `spend_id` int(11) NOT NULL,
  `spend_name` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `spend_price` decimal(19,2) NOT NULL,
  `spend_date` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `spend_user_1` int(11) NOT NULL,
  `spend_user_2` int(11) NOT NULL,
  `paid_debt` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `spendings`
--

INSERT INTO `spendings` (`spend_id`, `spend_name`, `spend_price`, `spend_date`, `spend_user_1`, `spend_user_2`, `paid_debt`) VALUES
(24, 'Bułka', 5.00, '2020-01-25', 21, 22, 0),
(25, 'Pizza', 17.50, '2020-01-25', 21, 23, 0),
(26, 'Wydrukowanie wykładów z ZMN', 7.50, '2020-01-24', 22, 21, 0),
(27, 'Cola', 2.00, '2020-01-23', 22, 23, 0),
(28, 'Obiad kurczak GongBao', 14.70, '2020-01-20', 23, 21, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `active`, `email`, `last_name`, `name`, `password`, `role_id`) VALUES
(1, 1, 'admin@gmail.com', 'Admin', 'Pan', '$2a$10$TNilSEl6ePjGf1K9a5q4NuNB.vkaPzcP/XBRY.zwrnfNPMfF9KxAK', 1),
(21, 1, 'userTomek@gmail.com', 'Karaś', 'Tomek', '$2a$10$ijGNK4m61B5sdU5.SzygtOsn7Xgf3KpRd1MDqB0LvBrknG4BIVb/W', 5),
(22, 1, 'userWiki@gmail.com', 'Gancarczyk', 'Wiktoria', '$2a$10$vHV7J3fIJG.AyUmNir/9h.h6ipkVDRC5uZKHfCA6FjkfPvI55sEN2', 5),
(23, 1, 'userAda@gmail.com', 'Jung', 'Adrianna', '$2a$10$b2G5OhXUY0Eq8WFglRtfgOd3UA29N8Ss3rEd0n9HA8arKJ3VKh5iC', 5),
(24, 1, 'test@gmail.com', 'owy', 'test', '$2a$10$/lXTvNlh2i2QPte9RFEKKeJr6jlnOErUKoEDbEfj2DaXxUFkKQUbK', 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `spendings`
--
ALTER TABLE `spendings`
  ADD PRIMARY KEY (`spend_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `spendings`
--
ALTER TABLE `spendings`
  MODIFY `spend_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
