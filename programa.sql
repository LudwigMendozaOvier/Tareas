-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-03-2018 a las 15:31:45
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `programa`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_agregarTarea` (`p_id` INT(3), `p_nombre` VARCHAR(20), `p_descripcion` VARCHAR(120), `p_tiempo` TIME, `p_fecha` VARCHAR(10))  begin
insert into registroTarea(id,nombre,descripcion,tiempo,fecha)
values (p_id,p_nombre,p_descripcion,p_tiempo,p_fecha);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminarTarea` (`p_id` INT(3))  begin
delete from registroTarea where id = p_id;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registrotarea`
--

CREATE TABLE `registrotarea` (
  `id` int(3) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(120) NOT NULL,
  `tiempo` time DEFAULT NULL,
  `fecha` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `registrotarea`
--

INSERT INTO `registrotarea` (`id`, `nombre`, `descripcion`, `tiempo`, `fecha`) VALUES
(1, '', '', '00:00:00', '14-03-2018'),
(2, 'Tarea 1', 'realizar las actividades que se quedaron pendientes en el salon de clases', '00:00:09', '14-03-2018'),
(3, 'Tarea 2', 'hacer la tarea', '00:00:48', '15-03-2018'),
(4, 'Tarea 3', 'la inetento realizar', '00:03:12', '15-03-2018'),
(5, 'Tarea 4', 'realizarla', '00:00:20', '15-03-2018');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `registrotarea`
--
ALTER TABLE `registrotarea`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `registrotarea`
--
ALTER TABLE `registrotarea`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
