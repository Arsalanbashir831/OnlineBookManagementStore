-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2022 at 08:54 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookmanagementsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `bookcategories`
--

CREATE TABLE `bookcategories` (
  `Cate_Id` int(11) NOT NULL,
  `Category_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookcategories`
--

INSERT INTO `bookcategories` (`Cate_Id`, `Category_name`) VALUES
(3, 'Fiction'),
(1, 'Horror'),
(2, 'Novel');

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `ISBN` int(11) NOT NULL,
  `BookName` varchar(100) NOT NULL,
  `AuthorName` varchar(100) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `PublisherName` varchar(100) NOT NULL,
  `PublisherDate` date NOT NULL,
  `BookRatings` int(11) NOT NULL,
  `BookCategory` int(11) NOT NULL,
  `Price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`ISBN`, `BookName`, `AuthorName`, `Quantity`, `PublisherName`, `PublisherDate`, `BookRatings`, `BookCategory`, `Price`) VALUES
(1, 'Psycology', 'Arsalan', 30, 'Ghufran Publishers', '2001-02-12', 4, 2, 450),
(2, 'Data Structure', 'Ahmed', 90, 'Bakar Publishers', '2000-11-24', 3, 1, 1876),
(4, 'dasfs', 'dsaf', 2, 'fsda', '1999-11-12', 3, 1, 0),
(5, 'das', 'sdfa', 21, 'dasf', '2001-01-12', 3, 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `charitybooks`
--

CREATE TABLE `charitybooks` (
  `ISBN` int(11) NOT NULL,
  `Credit` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `e_club`
--

CREATE TABLE `e_club` (
  `UserId` int(11) NOT NULL,
  `MembershipRank` int(11) NOT NULL DEFAULT 1,
  `Points` int(11) NOT NULL,
  `DiscountAvailible` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `e_club`
--

INSERT INTO `e_club` (`UserId`, `MembershipRank`, `Points`, `DiscountAvailible`) VALUES
(2, 1, 1, 0.1);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `UserId` int(11) NOT NULL,
  `ISBN` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Price` int(11) NOT NULL,
  `Delivery_Status` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`UserId`, `ISBN`, `Quantity`, `Price`, `Delivery_Status`) VALUES
(2, 1, 2, 900, 1);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `Pid` int(11) NOT NULL,
  `Pname` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`Pid`, `Pname`) VALUES
(1, 'COD'),
(3, 'Credit Card'),
(2, 'Jazz Cash');

-- --------------------------------------------------------

--
-- Table structure for table `rank`
--

CREATE TABLE `rank` (
  `RankId` int(11) NOT NULL,
  `RankName` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rank`
--

INSERT INTO `rank` (`RankId`, `RankName`) VALUES
(1, 'Member'),
(2, 'Super Member');

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `UserId` int(11) NOT NULL,
  `ISBN` int(11) NOT NULL,
  `Description` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`UserId`, `ISBN`, `Description`) VALUES
(2, 1, 'this is the nice book');

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `StatusId` int(11) NOT NULL,
  `StatusName` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`StatusId`, `StatusName`) VALUES
(1, 'Pending'),
(2, 'Completed');

-- --------------------------------------------------------

--
-- Table structure for table `userrole`
--

CREATE TABLE `userrole` (
  `UserRoleId` int(11) NOT NULL,
  `RoleName` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userrole`
--

INSERT INTO `userrole` (`UserRoleId`, `RoleName`) VALUES
(1, 'Admin'),
(2, 'Customer');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserId` int(11) NOT NULL,
  `UserName` varchar(100) NOT NULL,
  `UserPassword` varchar(100) NOT NULL,
  `UserEmail` varchar(100) NOT NULL,
  `UserGender` char(1) NOT NULL,
  `UserAddress` varchar(100) NOT NULL,
  `UserRole` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserId`, `UserName`, `UserPassword`, `UserEmail`, `UserGender`, `UserAddress`, `UserRole`) VALUES
(1, 'Arsalan', 'dsaq@2206', 'arsalanbashir831@gmail.com', 'M', 'Rajpoot town Lahore', 1),
(2, 'Bashir', 'Abb123', 'barbariankids2001@gmail.com', 'M', 'Township Lahore', 2),
(10, 'ali', 'Admin123', 'arsalan@gmail.com', 'M', 'dsafasd', 1),
(44, 'dasfaa', 'dasfsa', 'dsadfas', 'M', 'dsafas', 1),
(88, 'djsak', 'djsaklf', 'fjsalf', 'M', 'djaskjlf', 1),
(90, 'dsjaldjsa', 'ldjsalkd', 'dsjadlkfa', 'M', 'djsaklfjas', 1);

-- --------------------------------------------------------

--
-- Table structure for table `userspayment`
--

CREATE TABLE `userspayment` (
  `UserId` int(11) NOT NULL,
  `Pid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userspayment`
--

INSERT INTO `userspayment` (`UserId`, `Pid`) VALUES
(2, 1),
(2, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bookcategories`
--
ALTER TABLE `bookcategories`
  ADD PRIMARY KEY (`Cate_Id`),
  ADD UNIQUE KEY `Category_name` (`Category_name`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`ISBN`),
  ADD KEY `Category` (`BookCategory`);

--
-- Indexes for table `charitybooks`
--
ALTER TABLE `charitybooks`
  ADD KEY `Books` (`ISBN`),
  ADD KEY `Credit` (`Credit`);

--
-- Indexes for table `e_club`
--
ALTER TABLE `e_club`
  ADD UNIQUE KEY `UserId` (`UserId`),
  ADD KEY `MembershipRank` (`MembershipRank`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD KEY `Status` (`Delivery_Status`),
  ADD KEY `UserId` (`UserId`),
  ADD KEY `ISBN` (`ISBN`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`Pid`),
  ADD UNIQUE KEY `Pname` (`Pname`);

--
-- Indexes for table `rank`
--
ALTER TABLE `rank`
  ADD PRIMARY KEY (`RankId`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD KEY `ISBN` (`ISBN`),
  ADD KEY `UserId` (`UserId`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`StatusId`);

--
-- Indexes for table `userrole`
--
ALTER TABLE `userrole`
  ADD PRIMARY KEY (`UserRoleId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserId`),
  ADD KEY `Roles` (`UserRole`);

--
-- Indexes for table `userspayment`
--
ALTER TABLE `userspayment`
  ADD KEY `Payments` (`Pid`),
  ADD KEY `UsersP` (`UserId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `UserId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `books`
--
ALTER TABLE `books`
  ADD CONSTRAINT `Category` FOREIGN KEY (`BookCategory`) REFERENCES `bookcategories` (`Cate_Id`);

--
-- Constraints for table `charitybooks`
--
ALTER TABLE `charitybooks`
  ADD CONSTRAINT `Books` FOREIGN KEY (`ISBN`) REFERENCES `books` (`ISBN`);

--
-- Constraints for table `e_club`
--
ALTER TABLE `e_club`
  ADD CONSTRAINT `e_club_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`),
  ADD CONSTRAINT `e_club_ibfk_2` FOREIGN KEY (`MembershipRank`) REFERENCES `rank` (`RankId`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `Status` FOREIGN KEY (`Delivery_Status`) REFERENCES `status` (`StatusId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`ISBN`) REFERENCES `books` (`ISBN`);

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`ISBN`) REFERENCES `books` (`ISBN`),
  ADD CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `Roles` FOREIGN KEY (`UserRole`) REFERENCES `userrole` (`UserRoleId`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `userspayment`
--
ALTER TABLE `userspayment`
  ADD CONSTRAINT `Payments` FOREIGN KEY (`Pid`) REFERENCES `payment` (`Pid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `UsersP` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
