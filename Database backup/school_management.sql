-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 25, 2018 at 08:22 PM
-- Server version: 5.7.20-log
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `school_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `fee_schedule`
--

DROP TABLE IF EXISTS `fee_schedule`;
CREATE TABLE IF NOT EXISTS `fee_schedule` (
  `Start_From` date NOT NULL,
  `I` int(11) NOT NULL,
  `II` int(11) NOT NULL,
  `III` int(11) NOT NULL,
  `IV` int(11) NOT NULL,
  `V` int(11) NOT NULL,
  `VI` int(11) NOT NULL,
  `VII` int(11) NOT NULL,
  `VIII` int(11) NOT NULL,
  `IX` int(11) NOT NULL,
  `X` int(11) NOT NULL,
  UNIQUE KEY `Start_From` (`Start_From`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `fee_schedule`
--

INSERT INTO `fee_schedule` (`Start_From`, `I`, `II`, `III`, `IV`, `V`, `VI`, `VII`, `VIII`, `IX`, `X`) VALUES
('2017-01-01', 3200, 3300, 3400, 3500, 3600, 3700, 3800, 3900, 4000, 4100),
('2018-01-01', 3300, 3400, 3500, 3600, 3700, 3800, 3900, 4000, 4100, 4200);

-- --------------------------------------------------------

--
-- Table structure for table `fee_structure`
--

DROP TABLE IF EXISTS `fee_structure`;
CREATE TABLE IF NOT EXISTS `fee_structure` (
  `Class` varchar(6) NOT NULL,
  `Amount` int(11) NOT NULL,
  UNIQUE KEY `Class` (`Class`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `fee_structure`
--

INSERT INTO `fee_structure` (`Class`, `Amount`) VALUES
('I', 3200),
('II', 3300),
('III', 3400),
('IV', 3500),
('IX', 3900),
('V', 3600),
('VI', 3700),
('VII', 3800),
('VIII', 3850),
('X', 4000);

-- --------------------------------------------------------

--
-- Table structure for table `student_data`
--

DROP TABLE IF EXISTS `student_data`;
CREATE TABLE IF NOT EXISTS `student_data` (
  `Gr_No` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `F_Name` varchar(20) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `HomePh_No` varchar(13) NOT NULL,
  `Father_No` varchar(13) NOT NULL,
  `Birth_Date` date NOT NULL,
  `Class` varchar(5) NOT NULL,
  `Section` varchar(1) NOT NULL,
  `Adress` varchar(200) NOT NULL,
  `Fee_paid` date NOT NULL,
  UNIQUE KEY `Gr_No` (`Gr_No`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student_data`
--

INSERT INTO `student_data` (`Gr_No`, `Name`, `F_Name`, `Gender`, `HomePh_No`, `Father_No`, `Birth_Date`, `Class`, `Section`, `Adress`, `Fee_paid`) VALUES
(3, 'Hanzala', 'Farooq', 'Male', '02136675430', '03366814934', '2000-08-26', 'IX', 'B', '5-D Nazimabad karachi', '2018-10-27'),
(6, 'Areeb', 'Shahid', 'Male', '03403659827', '03343979654', '1998-07-22', 'VI', 'C', 'Friends Appartment', '2018-01-27'),
(7, 'Huzaifa', 'farooq', 'Male', '03352311772', '03366914934', '1998-08-20', 'IX', 'A', '5_D nAZIMBAD KARACHI', '2017-04-01'),
(9, 'Adnan Sidique', 'Gulzar Siddique', 'Male', '02165478963', '03254796241', '2001-06-02', 'IX', 'D', 'Goria Bazaar Karachi', '2017-08-27'),
(10, 'Fatima Hussain', 'Mehmood Mustafa', 'Female', '03698756332', '03456982165', '2003-06-14', 'VII', 'C', '2/9 AlFalah North Karachi', '2018-06-27'),
(12, 'Sadia  Rizvi', 'Ali Rizvi', 'Female', '03236698932', '03644896555', '2003-12-04', 'VIII', 'A', 'James Bond Road,Karachi', '2017-12-06'),
(13, 'yasir', 'Gulfam', 'Male', '02135457842', '03213454554', '1998-02-03', 'VI', 'B', 'Nazimabad 3E ,karachi', '2018-08-25');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
