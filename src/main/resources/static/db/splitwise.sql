-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 24, 2020 at 11:34 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

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
-- Table structure for table `case`
--

CREATE TABLE `case` (
  `case_id` bigint(20) NOT NULL,
  `case_date` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `case_note` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `case_price` decimal(19,2) DEFAULT NULL,
  `client_name` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `client_surname` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `case_lawyer` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `case`
--

INSERT INTO `case` (`case_id`, `case_date`, `case_note`, `case_price`, `client_name`, `client_surname`, `case_lawyer`) VALUES
(1, '2018-11-11', 'Prosba o adwokata ws. marszu niepodleglosci', NULL, 'Adam', 'Kowalski', 3),
(2, '2018-06-11', 'Pan prosi o porade prawna w sprawie konstytucji', NULL, 'Baśka', 'Tyszkiewicz', 3),
(3, '2018-06-11', 'Pan prosi o porade prawna w sprawie pobicia', NULL, 'Mirosław', 'Nowak', 3),
(4, '2018-06-12', 'Pan prosi o porade prawna w sprawie kradziezy', NULL, 'Zdzisław', 'Kręcina', 3),
(5, '2018-06-14', 'Pan prosi o porade prawna w sprawie wypadku', NULL, 'Stefan', 'Kowalski', 3),
(6, '2018-06-20', 'Pan prosi o porade prawna w sprawie nekania', NULL, 'Beata', 'Tyszkiewicz', 3),
(7, '2018-06-21', 'Pan prosi o porade prawna w sprawie awantury', NULL, 'Mariusz', 'Pudzianowski', 3),
(8, '2018-07-02', 'Pan prosi o porade prawna w sprawie libacji', NULL, 'Robert', 'Makłowicz', 3),
(9, '2018-07-10', 'Pan prosi o porade prawna w sprawie mandatu', NULL, 'Zdzisław', 'Kręcina', 3),
(10, '2018-06-11', 'Pan prosi o porade prawna w sprawie morderstwa', NULL, 'Beata', 'Tyszkiewicz', 5),
(11, '2018-06-11', 'Pan prosi o porade prawna w sprawie plagiatu', NULL, 'Mariusz', 'Pudzianowski', 5),
(12, '2018-07-13', 'Pan prosi o porade prawna w sprawie obrazania uczuc religijnych', NULL, 'Robert', 'Makłowicz', 5),
(13, '2018-07-22', 'Pan prosi o porade prawna w sprawie ochrony dobrego imienia', NULL, 'Mariusz', 'Pudzianowski', 5),
(14, '2018-06-14', 'Osoba prosi o poradę prawną w związku z pobiciem', NULL, 'Adam', 'Kowalski', 5);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `client_id` int(11) NOT NULL,
  `client_name` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `client_phone` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `client_surname` varchar(255) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`client_id`, `client_name`, `client_phone`, `client_surname`) VALUES
(1, 'Adam', '788996411', 'Kowalski'),
(2, 'Beata', '964789125', 'Tyszkiewicz'),
(3, 'Stefan', '789444566', 'Kowalski'),
(4, 'Zdzisław', '687888654', 'Kręcina'),
(5, 'Mariusz', '765893527', 'Pudzianowski'),
(6, 'Robert', '687572997', 'Makłowicz'),
(7, 'Mirosław', '655455155', 'Nowak');

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
(2, 'RECORDER'),
(3, 'LAWYER'),
(4, 'ACCOUNTANT'),
(5, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `service_id` int(11) NOT NULL,
  `service_name` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `service_price` decimal(19,2) NOT NULL,
  `service_inner_price` decimal(19,2) NOT NULL,
  `service_case_id` int(11) NOT NULL,
  `service_date` varchar(255) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`service_id`, `service_name`, `service_price`, `service_inner_price`, `service_case_id`, `service_date`) VALUES
(8, 'Porada prawna', '160.00', '0.00', 1, '2018-05-28'),
(10, 'Konsultacje', '150.00', '20.00', 1, '2018-06-08'),
(11, 'Konsultacje', '290.00', '0.00', 2, '2018-06-15'),
(12, 'Przygotowanie dokumentacji', '190.00', '20.00', 2, '2018-06-15'),
(13, 'Porada prawna', '80.00', '0.00', 1, '2018-05-20'),
(14, 'Porada prawna', '80.00', '0.00', 1, '2018-05-22'),
(15, 'Porada prawna', '160.00', '0.00', 1, '2018-05-28'),
(16, 'Szkolenie z RODO', '450.00', '50.00', 2, '2018-05-28');

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
(12, 'Przygotowanie dokumentacji', '199.00', '2018-06-15', 20, 2, 0),
(16, 'GongBao', '14.50', '2020-01-23', 20, 21, 0),
(17, 'Makaronik', '15.70', '2020-01-23', 20, 4, 0),
(18, 'Pizza', '25', '2020-01-02', 22, 21, 1),
(19, 'Łyżwy', '13.70', '2020-01-20', 21, 22, 1),
(23, 'Zakupy', '350.00', '2020-01-24', 21, 20, 0);

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
(1, 1, 'user1@gmail.com', 'Kowlaski', 'Jan', '$2a$10$TNilSEl6ePjGf1K9a5q4NuNB.vkaPzcP/XBRY.zwrnfNPMfF9KxAK', 1),
(2, 1, 'user2@gmail.com', 'Kubica', 'Robert', '$2a$10$TNilSEl6ePjGf1K9a5q4NuNB.vkaPzcP/XBRY.zwrnfNPMfF9KxAK', 2),
(22, 1, 'user3@gmail.com', 'Małysz', 'Adam', '$2a$10$TNilSEl6ePjGf1K9a5q4NuNB.vkaPzcP/XBRY.zwrnfNPMfF9KxAK', 3),
(4, 1, 'user4@gmail.com', 'Rodowicz', 'Maryla', '$2a$10$TNilSEl6ePjGf1K9a5q4NuNB.vkaPzcP/XBRY.zwrnfNPMfF9KxAK', 4),
(20, 1, 'user@gmail.com', 'Cichopek', 'Katarzyna', '$2a$10$39H.AqVm5S7twSh/pFsdMOeaxufWCix53uhSoO7HMAvcdsU./VS9G', 5),
(21, 1, 'user5@gmail.com', 'Mostowiak', 'Stefan', '$2a$10$UHn8/Viu10vSfREItMeO7.SyIZ/Yf/aU/ioFBukmmm1Dequdgm0YS', 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `case`
--
ALTER TABLE `case`
  ADD PRIMARY KEY (`case_id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`client_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`service_id`);

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
-- AUTO_INCREMENT for table `case`
--
ALTER TABLE `case`
  MODIFY `case_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `spendings`
--
ALTER TABLE `spendings`
  MODIFY `spend_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
