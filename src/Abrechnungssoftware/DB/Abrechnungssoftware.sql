-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 18. Jan 2019 um 12:47
-- Server-Version: 10.1.35-MariaDB
-- PHP-Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `abrechnung1`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `auftrag`
--

CREATE TABLE `auftrag` (
  `id` int(11) NOT NULL,
  `kunden_id` int(11) NOT NULL,
  `von` varchar(20) NOT NULL,
  `bis` varchar(20) NOT NULL,
  `grund` varchar(250) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `auftrag`
--

INSERT INTO `auftrag` (`id`, `kunden_id`, `von`, `bis`, `grund`) VALUES
(8, 2, '01.01.2019', '28.02.2019', 'srgdh');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `auftrag_intervall`
--

CREATE TABLE `auftrag_intervall` (
  `id` int(11) NOT NULL,
  `auftrag_id` int(11) NOT NULL,
  `von` varchar(20) NOT NULL,
  `bis` varchar(20) NOT NULL,
  `re_erstellt` enum('ja','nein') NOT NULL DEFAULT 'nein',
  `re_bezahlt` enum('ja','nein') NOT NULL DEFAULT 'nein'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `auftrag_intervall`
--

INSERT INTO `auftrag_intervall` (`id`, `auftrag_id`, `von`, `bis`, `re_erstellt`, `re_bezahlt`) VALUES
(7, 8, '01.02.2019', '28.02.2019', 'nein', 'nein'),
(6, 8, '01.01.2019', '31.01.2019', 'nein', 'nein');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kunden`
--

CREATE TABLE `kunden` (
  `id` int(11) NOT NULL,
  `firma` varchar(100) NOT NULL,
  `anrede` enum('Herr','Frau') NOT NULL DEFAULT 'Herr',
  `vorname` varchar(100) NOT NULL,
  `nachname` varchar(100) NOT NULL,
  `strasse` varchar(50) NOT NULL,
  `hsnr` varchar(20) NOT NULL,
  `plz` varchar(20) NOT NULL,
  `ort` varchar(50) NOT NULL,
  `telefon` varchar(50) NOT NULL,
  `telefax` varchar(50) NOT NULL,
  `web` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `ap_anrede` enum('Herr','Frau') NOT NULL DEFAULT 'Herr',
  `ap_vorname` varchar(50) NOT NULL,
  `ap_nachname` varchar(50) NOT NULL,
  `ap_telefon` varchar(50) NOT NULL,
  `ap_email` varchar(50) NOT NULL,
  `stdsatz` decimal(10,2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `kunden`
--

INSERT INTO `kunden` (`id`, `firma`, `anrede`, `vorname`, `nachname`, `strasse`, `hsnr`, `plz`, `ort`, `telefon`, `telefax`, `web`, `email`, `ap_anrede`, `ap_vorname`, `ap_nachname`, `ap_telefon`, `ap_email`, `stdsatz`) VALUES
(1, 'hjdghj', 'Herr', '', '', '', '', '', '', '', '', '', '', 'Herr', 'null', 'null', 'null', 'null', '0.00'),
(2, '4. firma', 'Herr', '', 'Manuel', '', '', '', '', '', '', '', '', 'Herr', 'null', 'null', 'null', 'null', '0.00'),
(3, '4. firma', 'Herr', '', '', 'Frintrop', '345', '45359', 'Essen', '01774016015', '', 'http://www.ostsan.de', 'support@ostsan.de', 'Herr', 'null', 'null', 'null', 'null', '0.00');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `rechnung`
--

CREATE TABLE `rechnung` (
  `id` int(11) NOT NULL,
  `auftrag_id` int(11) NOT NULL,
  `re_nr` varchar(10) NOT NULL,
  `bezahlt` enum('ja','nein') NOT NULL DEFAULT 'nein'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `rechnungen`
--

CREATE TABLE `rechnungen` (
  `id` int(11) NOT NULL,
  `grund` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `kunden_id` int(11) DEFAULT NULL,
  `auftrag_id` int(11) DEFAULT NULL,
  `von` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `bis` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `auftrag_intervall_id` int(11) DEFAULT NULL,
  `intervall_von` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `intervall_bis` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `tage` int(3) DEFAULT NULL,
  `korrektur_tage` int(2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `rechnungen`
--

INSERT INTO `rechnungen` (`id`, `grund`, `kunden_id`, `auftrag_id`, `von`, `bis`, `auftrag_intervall_id`, `intervall_von`, `intervall_bis`, `tage`, `korrektur_tage`) VALUES
(1, 'srgdh', 2, 8, '01.01.2019', '28.02.2019', 6, '01.01.2019', '31.01.2019', 23, NULL),
(2, 'srgdh', 2, 8, '01.01.2019', '28.02.2019', 7, '01.02.2019', '28.02.2019', 20, NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `stammdaten`
--

CREATE TABLE `stammdaten` (
  `id` tinyint(1) NOT NULL,
  `firma` varchar(100) NOT NULL,
  `vorname` varchar(50) NOT NULL,
  `nachname` varchar(50) NOT NULL,
  `strasse` varchar(100) NOT NULL,
  `hsnr` varchar(20) NOT NULL,
  `plz` varchar(10) NOT NULL,
  `ort` varchar(50) NOT NULL,
  `telefon` varchar(50) NOT NULL,
  `telefax` varchar(50) NOT NULL,
  `web` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `bankname` varchar(50) NOT NULL,
  `kontoinhaber` varchar(50) NOT NULL,
  `bic` varchar(50) NOT NULL,
  `iban` varchar(50) NOT NULL,
  `steuernummer` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `stammdaten`
--

INSERT INTO `stammdaten` (`id`, `firma`, `vorname`, `nachname`, `strasse`, `hsnr`, `plz`, `ort`, `telefon`, `telefax`, `web`, `email`, `bankname`, `kontoinhaber`, `bic`, `iban`, `steuernummer`) VALUES
(1, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `auftrag`
--
ALTER TABLE `auftrag`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `auftrag_intervall`
--
ALTER TABLE `auftrag_intervall`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `kunden`
--
ALTER TABLE `kunden`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `rechnungen`
--
ALTER TABLE `rechnungen`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `stammdaten`
--
ALTER TABLE `stammdaten`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `auftrag`
--
ALTER TABLE `auftrag`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT für Tabelle `auftrag_intervall`
--
ALTER TABLE `auftrag_intervall`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT für Tabelle `kunden`
--
ALTER TABLE `kunden`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `rechnungen`
--
ALTER TABLE `rechnungen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `stammdaten`
--
ALTER TABLE `stammdaten`
  MODIFY `id` tinyint(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
