-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 24 Apr 2020 pada 15.03
-- Versi server: 10.1.37-MariaDB
-- Versi PHP: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `utsmobile`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `Kode_mhs` char(10) NOT NULL,
  `Nama_mhs` varchar(45) NOT NULL,
  `Tgl_lhr` date NOT NULL,
  `Jns_kel` char(1) NOT NULL,
  `Agama` char(1) NOT NULL,
  `Gol_darah` char(1) NOT NULL,
  `Status_mhs` char(1) NOT NULL,
  `Kota` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `mahasiswa`
--

INSERT INTO `mahasiswa` (`Kode_mhs`, `Nama_mhs`, `Tgl_lhr`, `Jns_kel`, `Agama`, `Gol_darah`, `Status_mhs`, `Kota`) VALUES
('11', 'afe', '2020-04-01', '2', '1', '2', '1', 'semarang'),
('A22.02695', 'Meilisa Rahayu Kusuma', '2000-05-21', '0', '1', '4', '1', 'Senarang'),
('A22.02704', 'Arvita Listiya P', '2000-10-12', '0', '1', '3', '1', 'Semarang');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`Kode_mhs`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
