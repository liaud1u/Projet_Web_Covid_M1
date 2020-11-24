-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 24 nov. 2020 à 07:31
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `covid`
--

-- --------------------------------------------------------

--
-- Structure de la table `activite`
--

DROP TABLE IF EXISTS `activite`;
CREATE TABLE IF NOT EXISTS `activite` (
  `idActivite` int(32) NOT NULL,
  `heureDebut` date NOT NULL,
  `heureFin` date NOT NULL,
  `login` varchar(32) NOT NULL,
  `idLieu` int(32) NOT NULL,
  PRIMARY KEY (`idActivite`),
  KEY `fk_activite_lieu` (`idLieu`),
  CONSTRAINT  `fk_activite_lieu` FOREIGN KEY (`idLieu`) REFERENCES  `lieu` (`idLieu`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ami`
--

DROP TABLE IF EXISTS `ami`;
CREATE TABLE IF NOT EXISTS `ami` (
  `login1` varchar(32) NOT NULL,
  `login2` varchar(32) NOT NULL,
  PRIMARY KEY (`login1`,`login2`),
  KEY `fk_ami_login1` (`login1`),
  KEY `fk_ami_login2` (`login2`),
  CONSTRAINT  `fk_ami_login1` FOREIGN KEY (`login1`) REFERENCES  `utilisateur` (`login`)
  CONSTRAINT  `fk_ami_login2` FOREIGN KEY (`login2`) REFERENCES  `utilisateur` (`login`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

DROP TABLE IF EXISTS `lieu`;
CREATE TABLE IF NOT EXISTS `lieu` (
  `idLieu` int(32) NOT NULL,
  `nom` varchar(64) NOT NULL,
  `adresse` varchar(128) NOT NULL,
  PRIMARY KEY (`idLieu`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `idNotif` int(32) NOT NULL,
  `repondu` tinyint(1) NOT NULL,
  `contenu` varchar(128) NOT NULL,
  `lu` tinyint(1) NOT NULL,
  `date` date NOT NULL,
  `login1` varchar(32) NOT NULL,
  `login2` varchar(32) NOT NULL,
  PRIMARY KEY (`idNotif`),
  KEY `fk_notification_login1` (`login1`),
  KEY `fk_notification_login2` (`login2`)
  CONSTRAINT  `fk_ami_login1` FOREIGN KEY (`login1`) REFERENCES  `utilisateur` (`login`)
  CONSTRAINT  `fk_ami_login2` FOREIGN KEY (`login2`) REFERENCES  `utilisateur` (`login`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `login` varchar(32) NOT NULL,
  `mdp` varchar(32) NOT NULL,
  `admin` tinyint(1) NOT NULL,
  `nom` varchar(32) NOT NULL,
  `prenom` varchar(32) NOT NULL,
  `date` date NOT NULL,
  `photo` blob NOT NULL,
  PRIMARY KEY (`login`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
