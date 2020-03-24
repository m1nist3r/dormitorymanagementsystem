-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 02 Mar 2020, 02:52
-- Wersja serwera: 10.4.8-MariaDB
-- Wersja PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: dormitory
--
CREATE DATABASE IF NOT EXISTS dormitory DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE dormitory;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli adminstrator
--

DROP TABLE IF EXISTS adminstrator;
CREATE TABLE adminstrator (
  idAdmin int(11) NOT NULL,
  idAdmin_type int(11) NOT NULL,
  Name varchar(45) NOT NULL,
  Surname varchar(45) NOT NULL,
  PESEL varchar(11) NOT NULL,
  Password varchar(80) NOT NULL,
  Email varchar(50) NOT NULL,
  DOB date NOT NULL,
  Phone_number varchar(15) NOT NULL,
  Last_login date DEFAULT NULL,
  Last_logout date DEFAULT NULL,
  Date_of_creation date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli adminstrator
--

INSERT INTO adminstrator (idAdmin, idAdmin_type, `Name`, Surname, PESEL, `Password`, Email, DOB, Phone_number, Last_login, Last_logout, Date_of_creation) VALUES
(1, 1, 'test', 'test', '12345678', '5e8667a439c68f5145dd2fcbecf02209', 'test@test.test', '1955-10-13', '+48555444999', NULL, NULL, '0000-00-00');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli admin_type
--

DROP TABLE IF EXISTS admin_type;
CREATE TABLE admin_type (
  idAdmin_type int(11) NOT NULL,
  Description varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli admin_type
--

INSERT INTO admin_type (idAdmin_type, Description) VALUES
(1, 'Receptionist'),
(2, 'Supervisor');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli archive
--

DROP TABLE IF EXISTS archive;
CREATE TABLE archive (
  idBackup int(11) NOT NULL,
  Year_backup date NOT NULL,
  Path_to_archive varchar(100) NOT NULL,
  Archive_name varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli payment
--

DROP TABLE IF EXISTS payment;
CREATE TABLE payment (
  idPayment int(11) NOT NULL,
  idResident int(11) NOT NULL,
  idAdmin int(11) NOT NULL,
  Amount decimal(10,2) NOT NULL,
  Balance decimal(10,2) NOT NULL,
  Date date NOT NULL,
  PaymentType varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli payment
--

INSERT INTO payment (idPayment, idResident, idAdmin, Amount, Balance, `Date`, PaymentType) VALUES
(1, 8, 1, '350.00', '5.00', '2020-02-03', 'gotówka'),
(2, 20, 1, '400.00', '0.00', '2020-02-26', 'karta'),
(3, 19, 1, '345.00', '0.00', '2020-02-09', 'gotówka'),
(4, 1, 1, '345.00', '0.00', '2020-03-02', 'karta'),
(5, 2, 1, '345.00', '0.00', '2020-03-02', 'gotówka'),
(6, 3, 1, '345.00', '0.00', '2020-03-02', 'gotówka'),
(7, 4, 1, '345.00', '0.00', '2020-03-02', 'karta'),
(8, 5, 1, '345.00', '0.00', '2020-03-02', 'karta');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli resident
--

DROP TABLE IF EXISTS resident;
CREATE TABLE resident (
  id_resident int(11) NOT NULL,
  id_type int(11) NOT NULL,
  id_room int(4) NOT NULL,
  first_name varchar(45) NOT NULL,
  last_name varchar(45) NOT NULL,
  pesel varchar(11) DEFAULT NULL,
  gender char(1) NOT NULL,
  dob date NOT NULL,
  mothers_name varchar(45) NOT NULL,
  fathers_name varchar(45) NOT NULL,
  email varchar(40) NOT NULL,
  country varchar(45) NOT NULL,
  address varchar(45) NOT NULL,
  phone_number varchar(20) NOT NULL,
  accommodation_date date NOT NULL DEFAULT current_timestamp(),
  eviction_date date DEFAULT NULL,
  is_blocked tinyint(1) NOT NULL DEFAULT 0,
  payment_fee decimal(10,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli resident
--

INSERT INTO resident (id_resident, id_type, id_room, first_name, last_name, pesel, gender, dob, mothers_name, fathers_name, email, country, address, phone_number, accommodation_date, eviction_date, is_blocked, payment_fee) VALUES
(1, 1, 101, 'Maryla', 'Woźniak', '96101802461', 'M', '1996-10-18', 'Patrycja', 'Paweł', 'MarylaWozniak@wp.pl', 'Polska', 'ul. Zgrzebna 139 20-412 Lublin', '513 992 360', '2019-10-01', NULL, 0, '0.00'),
(2, 1, 403, 'Dorota', 'Kozłowska', '99093024703', 'M', '1994-05-15', 'Ola', 'Jakub', 'DorotaKozlowska@wp.pl', 'Polska', 'ul. Złotowska 1 20-519 Lublin', '601 562 041', '2019-10-01', NULL, 0, '0.00'),
(3, 1, 605, 'Paweł', 'Sokołowski', '98022108378', 'K', '1995-12-02', 'Monika', 'Marcin', 'PawelSokolowski@wp.pl', 'Polska', 'ul. Płońska 20 20-540 Lublin', '722 390 001', '2019-10-01', NULL, 0, '0.00'),
(4, 1, 305, 'Miłosław', 'Adamczyk', '95030535572', 'K', '1995-03-05', 'Agata', 'Filip', 'MiloslawAdamczyk@wp.pl', 'Polska', 'ul. Krawiecka 89 45-023 Opole', '536 586 968', '2019-10-01', NULL, 0, '0.00'),
(5, 1, 106, 'Karol', 'Adamczyk', '92102400622', 'K', '2000-11-09', 'Paulina', 'Karol', 'karol.adam@mail.net', 'Poland', 'ul. Płońska 46 03-683 Warszawa', '+48 72 330 35 98', '2019-10-01', NULL, 0, '0.00'),
(6, 1, 108, 'Czesław', 'Wiśnia', '91092211867', 'K', '1999-12-12', 'Małgorzata', 'Konrad', 'kurama@win.com', 'Poland', 'ul. Jelenia 114 81-598 Gdynia', '+48 69 174 41 28', '2019-10-01', NULL, 0, '-345.00'),
(7, 1, 102, 'Konrad', 'Piotrowski', '87053097960', 'K', '1998-02-25', 'Sylwia', 'Andrzej', 'Bijuu@onemail.net', 'Poland', 'ul. Dubois Stanisława 82 00-184 Warszawa', '79 843 58 53', '2019-10-01', NULL, 0, '-345.00'),
(8, 1, 1001, 'Andrzej', 'Kowalski', '89102799568', 'K', '1993-04-21', 'Marta', 'Marek', 'Smithy@onemail.net', 'Poland', 'ul. Mieszka I 142 81-778 Sopot', '60 798 58 89', '2019-10-01', NULL, 0, '5.00'),
(9, 2, 1012, 'Michał', 'Szczepański', '36061670250', 'K', '1990-12-29', 'Iwona', 'Arkadiusz', 'm.szczepanski@onemail.net', 'Czech', 'ul. Vaclava 54 87-104 Praha', '72 818 46 80', '2019-10-01', NULL, 0, '0.00'),
(10, 2, 408, 'Krzysztof', 'Rutek', '70041066796', 'K', '1989-09-01', 'Maryna', 'Szymon', 'andrzej@onemail.net', 'Chech', 'ul. Czerviniećę 21 87-201 Praha', '51 241 57 33', '2019-10-01', NULL, 0, '0.00'),
(11, 4, 909, 'Krystian', 'Wieczorek', '54100847858', 'K', '1999-05-30', 'Iga', 'Marcin', 'jestes@onemail.net', 'Uganda', 'ul. Uwuwweniaka 1 14880 Umpulumpu', '78 915 23 88', '2019-10-01', NULL, 0, '-45.00'),
(12, 3, 812, 'Patryk', 'Kucharz', '67032336178', 'K', '1998-12-21', 'Katarzyna', 'Robert', 'pa.kucharz@onemail.net', 'Uganda', 'ul. Dawae 12 14228 Umpulumpu', '69 869 22 14', '2019-10-01', NULL, 0, '0.00'),
(13, 1, 408, 'Robert', 'Duda', '61051384172', 'K', '2000-01-12', 'Sylwia', 'Waldemar', 'rob.duda@gmail.com', 'Nigeria', 'ul. Akashimet 25 11148 Narobi', '78 941 25 29', '2019-10-01', NULL, 0, '-345.00'),
(14, 1, 607, 'Bartosz', 'Kalina', '69111994646', 'K', '2001-12-22', 'Maria', 'Grzegorz', 'azaza@mail.ru', 'Germany', 'Bleibtreustrasse 6 32760 Detmold Oberschönhag', '60 361 44 84', '2019-11-14', NULL, 0, '-345.00'),
(15, 1, 608, 'Klim', 'Voronov', '66062876454', 'K', '1995-12-02', 'Maria', 'Stanislav', 'crow@mail.ru', 'Russia', 'ul. Krasnoarmeitsev 65 19201 Dobrovolsk', '72 738 72 53', '2019-10-01', NULL, 0, '-345.00'),
(16, 1, 304, 'Walter', 'Clark', '45012128856', 'K', '1992-11-12', 'Natalia', 'Andrey', 'zdenised12128856@mail.ru', 'Montenegro', 'ul. Zrtw Fasizmu 2 Budwa', '+382 634 052 33', '2019-10-01', NULL, 0, '-345.00'),
(17, 4, 804, 'Johnny', 'Depp', '00272428843', 'K', '1995-02-12', 'Ruta', 'Aradesh', 'johny.superstart@gmail.com', 'Australia', '52 Sale-Heyfield Road KARDELLA VIC 3951', '72 552 96 50', '2019-10-01', NULL, 0, '-45.00'),
(18, 4, 503, 'Danil', 'Ishutin', '90121213999', 'K', '1990-08-12', 'Ludmila', 'Grigoriy', 'dendi@mail.ru', 'Ukraine', 'ul. Kozatska 43 18205 Uzhhorod', '53 384 33 08', '2019-10-01', NULL, 0, '-45.00'),
(19, 1, 404, 'Grzegorz', 'Brzęczyśczykiewicz', '45050592291', 'K', '1990-07-27', 'Małgorzata', 'Szymon', 'napiszmojeimie@polmail.pl', 'Poland', 'ul. Kondratowicza Ludwika 60 41-300 Chrząszcz', '66 949 98 78', '2019-10-01', NULL, 0, '-345.00'),
(20, 3, 101, 'Denys', 'Zemlinski', '12345678987', 'M', '1992-01-03', 'Natalia', 'Andriy', 'zdenys@gmail.com', 'Czarna Góra', 'Zrtv Fasizmu', '38244562733', '2020-02-26', '2020-06-10', 0, '0.00'),
(21, 3, 101, 'Andrzej', 'Burawskij', '44444444444', 'K', '2020-02-01', 'Maria', 'Konstanty', 'andriy@sss.com', 'Poland', 'Nadbystrzycka 42', '888777555', '2020-02-26', '2020-03-13', 0, '0.00');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli resident_type_fields
--

DROP TABLE IF EXISTS resident_type_fields;
CREATE TABLE resident_type_fields (
  idField int(11) NOT NULL,
  idType int(11) NOT NULL,
  field_name varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli resident_type_fields
--

INSERT INTO resident_type_fields (idField, idType, field_name) VALUES
(1, 1, 'Student_number'),
(2, 1, 'Department'),
(3, 1, 'Year_of_study'),
(4, 1, 'Academic_year'),
(5, 1, 'Student_payment_account'),
(6, 2, 'Origin_university'),
(7, 2, 'Erasmus_number'),
(8, 3, 'Origin_university'),
(9, 4, 'Is_student'),
(10, 4, 'Is_part_time_student');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli resident_values_fields
--

DROP TABLE IF EXISTS resident_values_fields;
CREATE TABLE resident_values_fields (
  idValue int(11) NOT NULL,
  idField int(11) NOT NULL,
  idResident int(11) NOT NULL,
  value varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli resident_values_fields
--

INSERT INTO resident_values_fields (idValue, idField, idResident, `value`) VALUES
(1, 1, 1, '91956'),
(2, 2, 1, 'Elektrotechnika i Informatyka'),
(3, 3, 1, '4'),
(4, 4, 1, '2019/2020'),
(5, 5, 1, '5418 6570 4087 7401'),
(6, 1, 2, '67856'),
(7, 2, 2, 'Zarządzanie'),
(8, 3, 2, '2'),
(9, 4, 2, '2019/2020'),
(10, 5, 2, '5518 2678 3567 4444'),
(11, 1, 3, '67549'),
(12, 2, 3, 'Architektura'),
(13, 3, 3, '2'),
(14, 4, 3, '2019/2020'),
(15, 5, 3, '5562 9145 1749 0537'),
(16, 1, 4, '58065'),
(17, 2, 4, 'Mechatronika'),
(18, 3, 4, '3'),
(19, 4, 4, '2019/2020'),
(20, 5, 4, '5578 1546 7468 9031'),
(21, 1, 5, '77118'),
(22, 2, 5, 'Informatyka'),
(23, 3, 5, '1'),
(24, 4, 5, '2019/2020'),
(25, 5, 5, '5518 2678 3567 1212'),
(26, 1, 6, '77119'),
(27, 2, 6, 'Informatyka'),
(28, 3, 6, '1'),
(29, 4, 6, '2019/2020'),
(30, 5, 6, '5518 2678 3563 0574'),
(31, 1, 7, '74221'),
(32, 2, 7, 'Informatyka'),
(33, 3, 7, '2'),
(34, 4, 7, '2019/2020'),
(35, 5, 7, '5518 2678 3567 4444'),
(36, 1, 8, '67856'),
(37, 2, 8, 'Zarządzanie'),
(38, 3, 8, '4'),
(39, 4, 8, '2019/2020'),
(40, 5, 8, '5518 2178 1239 4344'),
(41, 6, 9, 'Vielkovića Universitat'),
(42, 7, 9, '984493'),
(43, 6, 10, 'Vielkovića Universitat'),
(44, 7, 10, '984492'),
(45, 9, 11, '1'),
(46, 10, 11, '1'),
(47, 8, 12, 'UMCS'),
(48, 1, 13, '77119'),
(49, 2, 13, 'Informatyka'),
(50, 3, 13, '1'),
(51, 4, 13, '2019/2020'),
(52, 5, 13, '3518 2678 3867 1511'),
(53, 1, 14, '75173'),
(54, 2, 14, 'Podstaw techniki'),
(55, 3, 14, '1'),
(56, 4, 14, '2019/2020'),
(57, 5, 14, '5238 2898 3969 4292'),
(58, 1, 15, '77221'),
(59, 2, 15, 'Informatyka'),
(60, 3, 15, '2'),
(61, 4, 15, '2019/2020'),
(62, 5, 15, '3318 2678 3537 4332'),
(63, 1, 16, '67128'),
(64, 2, 16, 'Podstaw techniki'),
(65, 3, 16, '3'),
(66, 4, 16, '2019/2020'),
(67, 5, 16, '5521 2278 3597 0042'),
(68, 9, 17, '0'),
(69, 10, 17, '1'),
(70, 9, 18, '0'),
(71, 10, 18, '0'),
(72, 1, 19, '67828'),
(73, 2, 19, 'Zarządzanie'),
(74, 3, 19, '3'),
(75, 4, 19, '2019/2020'),
(76, 5, 19, '3301 0074 1198 9473'),
(77, 8, 20, 'WYSPA'),
(78, 8, 21, 'UMCS');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli room
--

DROP TABLE IF EXISTS room;
CREATE TABLE room (
  idRoom int(11) NOT NULL,
  Room_type int(1) NOT NULL,
  Dormitory_number int(1) NOT NULL,
  Room_status varchar(45) NOT NULL,
  Resident_amount int(1) NOT NULL,
  Floor int(2) NOT NULL,
  Remarks text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli room
--

INSERT INTO room (idRoom, Room_type, Dormitory_number, Room_status, Resident_amount, `Floor`, Remarks) VALUES
(101, 3, 1, 'Full', 3, 1, NULL),
(102, 3, 1, '2 / 3', 2, 1, NULL),
(103, 3, 1, 'Full', 3, 1, NULL),
(104, 3, 1, 'Full', 3, 1, NULL),
(105, 3, 1, 'Full', 3, 1, NULL),
(106, 3, 1, 'Full', 3, 1, NULL),
(107, 3, 1, 'Full', 3, 1, NULL),
(108, 3, 1, 'Full', 3, 1, NULL),
(109, 3, 1, 'Full', 3, 1, NULL),
(110, 3, 1, 'Full', 3, 1, NULL),
(111, 3, 1, 'Full', 3, 1, NULL),
(112, 3, 1, 'Full', 3, 1, NULL),
(201, 3, 1, 'Full', 3, 2, NULL),
(202, 3, 1, 'Full', 3, 2, NULL),
(203, 3, 1, 'Full', 3, 2, NULL),
(204, 3, 1, 'Full', 3, 2, NULL),
(205, 3, 1, 'Full', 3, 2, NULL),
(206, 3, 1, 'Full', 3, 2, NULL),
(207, 3, 1, 'Full', 3, 2, NULL),
(208, 3, 1, 'Full', 3, 2, NULL),
(209, 3, 1, 'Full', 3, 2, NULL),
(210, 3, 1, 'Full', 3, 2, NULL),
(211, 3, 1, 'Full', 3, 2, NULL),
(212, 3, 1, 'Full', 3, 2, NULL),
(301, 3, 1, 'Full', 3, 3, NULL),
(302, 3, 1, 'Full', 3, 3, NULL),
(303, 3, 1, 'Full', 3, 3, NULL),
(304, 3, 1, 'Full', 3, 3, NULL),
(305, 3, 1, 'Full', 3, 3, NULL),
(306, 3, 1, 'Full', 3, 3, NULL),
(307, 3, 1, 'Full', 3, 3, NULL),
(308, 3, 1, 'Full', 3, 3, NULL),
(309, 3, 1, 'Full', 3, 3, NULL),
(310, 3, 1, 'Full', 3, 3, NULL),
(311, 3, 1, 'Full', 3, 3, NULL),
(312, 3, 1, 'Full', 3, 3, NULL),
(401, 3, 1, 'Full', 3, 4, NULL),
(402, 3, 1, 'Full', 3, 4, NULL),
(403, 3, 1, 'Full', 3, 4, NULL),
(404, 3, 1, 'Full', 3, 4, NULL),
(405, 3, 1, 'Full', 3, 4, NULL),
(406, 3, 1, 'Full', 3, 4, NULL),
(407, 3, 1, 'Full', 3, 4, NULL),
(408, 3, 1, 'Full', 3, 4, NULL),
(409, 3, 1, 'Full', 3, 4, NULL),
(410, 3, 1, 'Full', 3, 4, NULL),
(411, 3, 1, 'Full', 3, 4, NULL),
(412, 3, 1, 'Full', 3, 4, NULL),
(501, 3, 1, 'Full', 3, 5, NULL),
(502, 3, 1, 'Full', 3, 5, NULL),
(503, 3, 1, 'Full', 3, 5, NULL),
(504, 3, 1, 'Full', 3, 5, NULL),
(505, 3, 1, 'Full', 3, 5, NULL),
(506, 3, 1, 'Full', 3, 5, NULL),
(507, 3, 1, 'Full', 3, 5, NULL),
(508, 3, 1, 'Full', 3, 5, NULL),
(509, 3, 1, 'Full', 3, 5, NULL),
(510, 3, 1, 'Full', 3, 5, NULL),
(511, 3, 1, 'Full', 3, 5, NULL),
(512, 3, 1, 'Full', 3, 5, NULL),
(601, 3, 1, 'Full', 3, 6, NULL),
(602, 3, 1, 'Full', 3, 6, NULL),
(603, 3, 1, 'Full', 3, 6, NULL),
(604, 3, 1, 'Full', 3, 6, NULL),
(605, 3, 1, 'Full', 3, 6, NULL),
(606, 3, 1, 'Full', 3, 6, NULL),
(607, 3, 1, 'Full', 3, 6, NULL),
(608, 3, 1, 'Full', 3, 6, NULL),
(609, 3, 1, 'Full', 3, 6, NULL),
(610, 3, 1, 'Full', 3, 6, NULL),
(611, 3, 1, 'Full', 3, 6, NULL),
(612, 3, 1, 'Full', 3, 6, NULL),
(701, 3, 1, 'Full', 3, 7, NULL),
(702, 3, 1, 'Full', 3, 7, NULL),
(703, 3, 1, 'Full', 3, 7, NULL),
(704, 3, 1, 'Full', 3, 7, NULL),
(705, 3, 1, 'Full', 3, 7, NULL),
(706, 3, 1, 'Full', 3, 7, NULL),
(707, 3, 1, 'Full', 3, 7, NULL),
(708, 3, 1, 'Full', 3, 7, NULL),
(709, 3, 1, 'Full', 3, 7, NULL),
(710, 3, 1, 'Full', 3, 7, NULL),
(711, 3, 1, 'Full', 3, 7, NULL),
(712, 3, 1, 'Full', 3, 7, NULL),
(801, 3, 1, 'Full', 3, 8, NULL),
(802, 3, 1, 'Full', 3, 8, NULL),
(803, 3, 1, 'Full', 3, 8, NULL),
(804, 3, 1, 'Full', 3, 8, NULL),
(805, 3, 1, 'Full', 3, 8, NULL),
(806, 3, 1, 'Full', 3, 8, NULL),
(807, 3, 1, 'Full', 3, 8, NULL),
(808, 3, 1, 'Full', 3, 8, NULL),
(809, 3, 1, 'Full', 3, 8, NULL),
(810, 3, 1, 'Full', 3, 8, NULL),
(811, 3, 1, 'Full', 3, 8, NULL),
(812, 3, 1, 'Full', 3, 8, NULL),
(901, 3, 1, 'Full', 3, 9, NULL),
(902, 3, 1, 'Full', 3, 9, NULL),
(903, 3, 1, 'Full', 3, 9, NULL),
(904, 3, 1, 'Full', 3, 9, NULL),
(905, 3, 1, 'Full', 3, 9, NULL),
(906, 3, 1, 'Full', 3, 9, NULL),
(907, 3, 1, 'Full', 3, 9, NULL),
(908, 3, 1, 'Full', 3, 9, NULL),
(909, 3, 1, 'Full', 3, 9, NULL),
(910, 3, 1, 'Full', 3, 9, NULL),
(911, 3, 1, 'Full', 3, 9, NULL),
(912, 3, 1, 'Full', 3, 9, NULL),
(1001, 3, 1, 'Full', 3, 10, NULL),
(1002, 3, 1, 'Full', 3, 10, NULL),
(1003, 3, 1, 'Full', 3, 10, NULL),
(1004, 3, 1, 'Full', 3, 10, NULL),
(1005, 3, 1, 'Full', 3, 10, NULL),
(1006, 3, 1, 'Full', 3, 10, NULL),
(1007, 3, 1, 'Full', 3, 10, NULL),
(1008, 3, 1, 'Full', 3, 10, NULL),
(1009, 3, 1, 'Full', 3, 10, NULL),
(1010, 3, 1, 'Full', 3, 10, NULL),
(1011, 3, 1, 'Full', 3, 10, NULL),
(1012, 3, 1, 'Full', 3, 10, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli type_of_resident
--

DROP TABLE IF EXISTS type_of_resident;
CREATE TABLE type_of_resident (
  idType int(11) NOT NULL,
  Type varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli type_of_resident
--

INSERT INTO type_of_resident (idType, `Type`) VALUES
(1, 'Student'),
(2, 'Foreign Student'),
(3, 'Other Students'),
(4, 'Guests');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli adminstrator
--
ALTER TABLE adminstrator
  ADD PRIMARY KEY (idAdmin),
  ADD KEY Admin_type (idAdmin_type);

--
-- Indeksy dla tabeli admin_type
--
ALTER TABLE admin_type
  ADD PRIMARY KEY (idAdmin_type);

--
-- Indeksy dla tabeli archive
--
ALTER TABLE archive
  ADD PRIMARY KEY (idBackup);

--
-- Indeksy dla tabeli payment
--
ALTER TABLE payment
  ADD PRIMARY KEY (idPayment),
  ADD KEY Admin_Payment (idAdmin),
  ADD KEY Resident_Payment (idResident);

--
-- Indeksy dla tabeli resident
--
ALTER TABLE resident
  ADD PRIMARY KEY (id_resident),
  ADD KEY Room (id_room),
  ADD KEY idType (id_type) USING BTREE,
  ADD KEY pesel (pesel),
  ADD KEY pesel_2 (pesel);

--
-- Indeksy dla tabeli resident_type_fields
--
ALTER TABLE resident_type_fields
  ADD PRIMARY KEY (idField),
  ADD KEY idType (idType) USING BTREE;

--
-- Indeksy dla tabeli resident_values_fields
--
ALTER TABLE resident_values_fields
  ADD PRIMARY KEY (idValue),
  ADD KEY idField (idField) USING BTREE,
  ADD KEY idResident (idResident) USING BTREE;

--
-- Indeksy dla tabeli room
--
ALTER TABLE room
  ADD PRIMARY KEY (idRoom);

--
-- Indeksy dla tabeli type_of_resident
--
ALTER TABLE type_of_resident
  ADD PRIMARY KEY (idType);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli adminstrator
--
ALTER TABLE adminstrator
  MODIFY idAdmin int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli admin_type
--
ALTER TABLE admin_type
  MODIFY idAdmin_type int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli archive
--
ALTER TABLE archive
  MODIFY idBackup int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli payment
--
ALTER TABLE payment
  MODIFY idPayment int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT dla tabeli resident
--
ALTER TABLE resident
  MODIFY id_resident int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT dla tabeli resident_type_fields
--
ALTER TABLE resident_type_fields
  MODIFY idField int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT dla tabeli resident_values_fields
--
ALTER TABLE resident_values_fields
  MODIFY idValue int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;

--
-- AUTO_INCREMENT dla tabeli room
--
ALTER TABLE room
  MODIFY idRoom int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1013;

--
-- AUTO_INCREMENT dla tabeli type_of_resident
--
ALTER TABLE type_of_resident
  MODIFY idType int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli adminstrator
--
ALTER TABLE adminstrator
  ADD CONSTRAINT Admin_type FOREIGN KEY (idAdmin_type) REFERENCES admin_type (idAdmin_type) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli payment
--
ALTER TABLE payment
  ADD CONSTRAINT Admin_Payment FOREIGN KEY (idAdmin) REFERENCES adminstrator (idAdmin) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT Resident_Payment FOREIGN KEY (idResident) REFERENCES resident (id_resident) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli resident
--
ALTER TABLE resident
  ADD CONSTRAINT Room FOREIGN KEY (id_room) REFERENCES room (idRoom) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT TypeOfResident FOREIGN KEY (id_type) REFERENCES type_of_resident (idType) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli resident_type_fields
--
ALTER TABLE resident_type_fields
  ADD CONSTRAINT Type FOREIGN KEY (idType) REFERENCES type_of_resident (idType) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli resident_values_fields
--
ALTER TABLE resident_values_fields
  ADD CONSTRAINT Field FOREIGN KEY (idField) REFERENCES resident_type_fields (idField) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT Resident FOREIGN KEY (idResident) REFERENCES resident (id_resident) ON DELETE NO ACTION ON UPDATE NO ACTION;

DELIMITER $$
--
-- Zdarzenia
--
DROP EVENT `event1`$$
CREATE DEFINER=root@localhost EVENT event1 ON SCHEDULE EVERY 1 MONTH STARTS '2020-03-01 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
UPDATE resident r JOIN room ro ON r.id_room = ro.idRoom SET r.payment_fee = r.payment_fee - 345.00 WHERE r.id_type = 1 AND ro.Room_type = 3;
UPDATE resident r JOIN room ro ON r.id_room = ro.idRoom SET r.payment_fee = r.payment_fee - 440.00 WHERE r.id_type = 1 AND ro.Room_type = 2;
UPDATE resident r JOIN room ro ON r.id_room = ro.idRoom SET r.payment_fee = r.payment_fee - 850.00 WHERE r.id_type = 1 AND ro.Room_type = 1;
UPDATE resident r JOIN room ro ON r.id_room = ro.idRoom SET r.payment_fee = r.payment_fee - 410.00 WHERE r.id_type = 2 AND ro.Room_type = 3;
UPDATE resident r JOIN room ro ON r.id_room = ro.idRoom SET r.payment_fee = r.payment_fee - 490.00 WHERE r.id_type = 2 AND ro.Room_type = 2;
UPDATE resident r JOIN room ro ON r.id_room = ro.idRoom SET r.payment_fee = r.payment_fee - 1000.00 WHERE r.id_type = 2 AND ro.Room_type = 1;
UPDATE resident r JOIN room ro ON r.id_room = ro.idRoom SET r.payment_fee = r.payment_fee - 370.00 WHERE r.id_type = 3 AND ro.Room_type = 3;
UPDATE resident r JOIN room ro ON r.id_room = ro.idRoom SET r.payment_fee = r.payment_fee - 470.00 WHERE r.id_type = 3 AND ro.Room_type = 2;
UPDATE resident r JOIN room ro ON r.id_room = ro.idRoom SET r.payment_fee = r.payment_fee - 950.00 WHERE r.id_type = 3 AND ro.Room_type = 1;
END$$

DROP EVENT `event2`$$
CREATE DEFINER=root@localhost EVENT event2 ON SCHEDULE EVERY 1 DAY STARTS '2020-03-01 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
UPDATE resident r SET r.payment_fee = r.payment_fee - 45.00 WHERE r.id_type = 4;
END$$

DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
