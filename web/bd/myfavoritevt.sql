-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-09-2018 a las 00:55:01
-- Versión del servidor: 10.1.28-MariaDB
-- Versión de PHP: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `myfavoritevt`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_citamedica`
--

CREATE TABLE `tbl_citamedica` (
  `PK_CIT_IdCita` int(11) NOT NULL COMMENT 'Este campo es el identificador de las citas',
  `FK_USU_IdUsuario` int(11) NOT NULL COMMENT 'Este campo es la foranea del identificador de la tabla usuarios para saber quien genero la cita',
  `FK_MAS_IdMascota` int(11) NOT NULL,
  `FK_PRO_Procedimiento` int(11) NOT NULL COMMENT 'Este campo es la foranea del identificador de la tabla de procedimientos con el cual se podrá indicar que la cita es por un procedimiento ',
  `FK_TIP_IdTipo` int(11) NOT NULL COMMENT 'Este campo es la foránea del identificador de la tabla tipo para indicar de que tipo es la cita',
  `CIT_Fecha` date NOT NULL COMMENT 'En este campo se indica la fecha de la cita',
  `CIT_Hora` time NOT NULL COMMENT 'En este campo se indica la hora de la cita',
  `CIT_Estado` varchar(20) NOT NULL DEFAULT 'En progreso' COMMENT 'En este campo se indica el estado que tiene la cita actualmente '
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbl_citamedica`
--

INSERT INTO `tbl_citamedica` (`PK_CIT_IdCita`, `FK_USU_IdUsuario`, `FK_MAS_IdMascota`, `FK_PRO_Procedimiento`, `FK_TIP_IdTipo`, `CIT_Fecha`, `CIT_Hora`, `CIT_Estado`) VALUES
(14, 32, 1, 2, 1, '2018-06-29', '10:00:00', 'Aceptada'),
(15, 31, 6, 1, 1, '2018-06-29', '09:00:00', 'Aceptada'),
(16, 32, 1, 1, 1, '2018-06-30', '07:00:00', 'Aceptada'),
(17, 31, 7, 2, 2, '2018-07-01', '13:00:00', 'En progreso'),
(18, 33, 8, 2, 1, '2018-08-30', '14:30:00', 'En progreso'),
(19, 31, 1, 1, 1, '2018-08-12', '10:09:00', 'En progreso'),
(20, 31, 6, 3, 1, '2018-09-14', '15:00:00', 'En progreso'),
(25, 33, 8, 1, 2, '2018-09-20', '13:00:00', 'En progreso'),
(26, 31, 1, 1, 1, '2018-09-11', '16:00:00', 'En progreso'),
(27, 33, 1, 1, 1, '2018-09-11', '19:00:00', 'En progreso'),
(28, 31, 1, 3, 1, '2018-09-12', '16:00:00', 'En progreso');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_citaservicio`
--

CREATE TABLE `tbl_citaservicio` (
  `PK_CIS_Citaservicio` int(11) NOT NULL,
  `FK_USU_IdUsuario` int(11) NOT NULL,
  `FK_MAS_IdMascota` int(11) NOT NULL,
  `FK_SER_IdServicio` int(11) NOT NULL,
  `CIS_Fecha` date NOT NULL,
  `CIS_Hora` time NOT NULL,
  `CIS_Estado` varchar(45) NOT NULL DEFAULT 'En progreso',
  `CIS_FechaEnLaQueSeAgendo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbl_citaservicio`
--

INSERT INTO `tbl_citaservicio` (`PK_CIS_Citaservicio`, `FK_USU_IdUsuario`, `FK_MAS_IdMascota`, `FK_SER_IdServicio`, `CIS_Fecha`, `CIS_Hora`, `CIS_Estado`, `CIS_FechaEnLaQueSeAgendo`) VALUES
(2, 31, 6, 3, '2018-06-29', '08:00:00', 'Agendada', '2018-09-11 20:03:41'),
(5, 32, 8, 4, '2018-08-30', '17:00:00', 'Agendada', '2018-09-11 20:03:41'),
(9, 32, 6, 3, '2018-08-23', '11:00:00', 'Aceptada', '2018-09-11 20:03:41'),
(10, 32, 6, 5, '2018-08-25', '15:30:00', 'Aceptada', '2018-09-11 20:03:41'),
(11, 34, 1, 4, '2018-08-28', '11:00:00', 'Agendada', '2018-09-11 20:03:41'),
(12, 36, 7, 3, '2018-08-14', '07:00:00', 'Agendada', '2018-09-11 20:03:41'),
(13, 36, 7, 4, '2018-08-31', '18:00:00', 'Agendada', '2018-09-11 20:03:41'),
(14, 32, 3, 3, '2018-09-27', '10:00:00', 'En progreso', '2018-09-11 20:27:33'),
(15, 32, 3, 3, '2018-09-27', '10:00:00', 'En progreso', '2018-09-11 20:28:05'),
(16, 32, 1, 3, '2018-09-27', '10:00:00', 'En progreso', '2018-09-11 20:33:30'),
(17, 32, 1, 3, '2018-09-12', '11:00:00', 'En progreso', '2018-09-11 20:44:37'),
(18, 32, 1, 3, '2018-09-29', '12:00:00', 'En progreso', '2018-09-11 21:08:03'),
(19, 32, 1, 3, '2018-09-29', '13:00:00', 'En progreso', '2018-09-11 21:08:16'),
(20, 32, 1, 3, '2018-09-29', '14:00:00', 'En progreso', '2018-09-11 21:08:22'),
(21, 32, 1, 3, '2018-09-29', '17:00:00', 'En progreso', '2018-09-11 21:08:28'),
(22, 32, 1, 3, '2018-09-26', '10:00:00', 'En progreso', '2018-09-11 21:24:18'),
(23, 32, 1, 3, '2018-09-24', '16:00:00', 'En progreso', '2018-09-11 21:39:16'),
(24, 32, 1, 3, '2018-09-27', '18:00:00', 'En progreso', '2018-09-11 21:54:07'),
(25, 32, 5, 3, '2018-10-19', '12:00:00', 'En progreso', '2018-09-15 10:00:44'),
(26, 32, 1, 3, '2018-09-21', '12:00:00', 'En progreso', '2018-09-15 14:09:49'),
(27, 32, 1, 3, '2018-09-29', '10:00:00', 'En progreso', '2018-09-15 17:15:54'),
(28, 32, 1, 3, '2018-09-30', '09:00:00', 'En progreso', '2018-09-15 17:21:15'),
(29, 32, 1, 3, '2018-10-08', '10:00:00', 'En progreso', '2018-09-15 17:33:04'),
(30, 32, 1, 3, '2018-10-30', '09:00:00', 'En progreso', '2018-09-15 17:38:02'),
(31, 32, 1, 3, '2018-09-11', '20:00:00', 'En progreso', '2018-09-17 19:59:46'),
(32, 32, 1, 3, '2018-09-28', '08:00:00', 'En progreso', '2018-09-17 20:13:10'),
(33, 32, 1, 3, '2018-09-30', '11:00:00', 'En progreso', '2018-09-17 20:15:29'),
(34, 32, 1, 3, '2018-10-25', '18:00:00', 'En progreso', '2018-09-17 20:18:07'),
(35, 32, 1, 5, '2018-09-26', '12:00:00', 'En progreso', '2018-09-18 00:00:00'),
(36, 32, 1, 3, '2018-09-30', '17:00:00', 'En progreso', '2018-09-18 00:00:00'),
(37, 32, 1, 4, '2018-09-22', '15:00:00', 'En progreso', '2018-09-18'),
(38, 32, 3, 3, '2018-10-10', '09:00:00', 'Agendada', '2018-09-18'),
(39, 32, 3, 3, '2018-10-10', '10:00:00', 'Agendada', '2018-09-18'),
(40, 32, 3, 3, '2018-10-10', '12:00:00', 'Agendada', '2018-09-18'),
(41, 32, 1, 3, '2018-09-29', '18:00:00', 'Agendada', '2018-09-18'),
(42, 31, 7, 3, '2018-09-18', '13:00:00', 'Agendada', '2018-09-18'),
(43, 31, 6, 3, '2018-09-18', '16:00:00', 'Agendada', '2018-09-18'),
(44, 31, 7, 5, '2018-09-18', '18:00:00', 'Agendada', '2018-09-18'),
(45, 31, 7, 3, '2018-10-26', '11:00:00', 'Agendada', '2018-09-19'),
(46, 32, 1, 3, '2018-09-28', '09:00:00', 'Agendada', '2018-09-20'),
(47, 32, 1, 3, '2018-09-28', '10:00:00', 'Agendada', '2018-09-20'),
(48, 32, 1, 3, '2018-09-28', '11:00:00', 'Agendada', '2018-09-20'),
(49, 32, 1, 5, '2018-09-29', '11:00:00', 'Agendada', '2018-09-28'),
(50, 32, 1, 4, '2018-09-29', '16:00:00', 'Agendada', '2018-09-28'),
(51, 32, 3, 3, '2018-09-29', '08:00:00', 'Agendada', '2018-09-28');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_historiaclinica`
--

CREATE TABLE `tbl_historiaclinica` (
  `PK_HIS_IdHistoriaClinica` int(11) NOT NULL COMMENT 'Este campo es el identificador de la histori clinica',
  `FK_MAS_IdMascota` int(11) NOT NULL,
  `HIS_Fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Este campo especifica la fecha en la que se creo y/o modifico la historia',
  `HIS_Hora` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Este campo especifica la hora en la cual se creo y/o modifico la historia',
  `HIS_DescripcionHistoriaClinica` text NOT NULL COMMENT 'Este campo contiene la descripción de la historia clinica'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbl_historiaclinica`
--

INSERT INTO `tbl_historiaclinica` (`PK_HIS_IdHistoriaClinica`, `FK_MAS_IdMascota`, `HIS_Fecha`, `HIS_Hora`, `HIS_DescripcionHistoriaClinica`) VALUES
(31, 1, '2018-06-08 00:00:00', '2018-06-28 01:57:00', 'sdfghjkl'),
(33, 4, '2018-06-08 00:00:00', '2018-06-28 01:57:00', 'ghjk'),
(35, 3, '2018-06-12 00:00:00', '2018-06-28 19:00:00', 'ewrertrt'),
(36, 3, '2018-06-19 00:00:00', '2018-06-28 16:00:00', 'sdfdfff'),
(37, 4, '2018-06-08 00:00:00', '2018-06-28 01:57:00', 'ghjk223345'),
(38, 4, '2018-02-02 00:00:00', '2018-06-28 13:02:00', 'Otra historia'),
(39, 3, '2018-04-03 00:00:00', '2018-06-28 03:03:00', 'Prueba 2'),
(40, 5, '2018-06-27 00:00:00', '2018-06-28 23:27:00', 'prueba'),
(41, 1, '2018-06-28 11:11:39', '2018-06-28 11:11:39', 'Prueba'),
(42, 1, '2018-06-28 11:21:20', '2018-06-28 11:21:20', 'prueba11'),
(43, 4, '2018-08-18 13:40:48', '2018-08-18 13:40:48', 'khddglgfjlkhfgjglfgdhd'),
(44, 4, '2018-08-18 13:41:05', '2018-08-18 13:41:05', ''),
(45, 1, '2018-08-18 13:43:17', '2018-08-18 13:43:17', 'faffasfsaafafaasf'),
(46, 5, '2018-08-18 13:44:59', '2018-08-18 13:44:59', 'HOLA SOY TU ANO :)'),
(47, 6, '2018-09-16 10:18:46', '2018-09-16 10:18:46', 'fdsfsdfsdffddsf');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_historiareceta`
--

CREATE TABLE `tbl_historiareceta` (
  `FK_HIS_IdHistoriaClinica` int(11) NOT NULL COMMENT 'Este campo es la foránea del identificador de la tabla de historia clinica',
  `FK_REC_IdReceta` int(11) NOT NULL COMMENT 'Este campo es la foránea del identificador de la tabla de receta '
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_mascota`
--

CREATE TABLE `tbl_mascota` (
  `PK_MAS_IdMascota` int(11) NOT NULL COMMENT 'Este campo es el identificador de la mascota',
  `MAS_Mascota_Beneficiaria` varchar(20) NOT NULL,
  `MAS_Nombre_Mascota` varchar(25) NOT NULL COMMENT 'Este campo contiene el nombre de la mascota',
  `MAS_Sexo_Mascota` varchar(25) NOT NULL COMMENT 'Este campo contiene el sexo de la mascota si es macho (M) si es hembra (H)',
  `MAS_Tipo_Mascota` varchar(25) DEFAULT NULL COMMENT 'En este campo se especificara si la mascota es un gato o un perro',
  `MAS_Raza_Mascota` varchar(20) NOT NULL COMMENT 'Este campo contiene la raza de la masxota',
  `MAS_Peso_Mascota` smallint(6) NOT NULL COMMENT 'Este campo contiene el peso de la mascota que se especifica en kilogramos',
  `MAS_Estatura_Mascota` double NOT NULL COMMENT 'Este campo contiene la estatura de la mascota la cual se especifica en metros',
  `MAS_Estado` varchar(10) NOT NULL DEFAULT 'Activo'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbl_mascota`
--

INSERT INTO `tbl_mascota` (`PK_MAS_IdMascota`, `MAS_Mascota_Beneficiaria`, `MAS_Nombre_Mascota`, `MAS_Sexo_Mascota`, `MAS_Tipo_Mascota`, `MAS_Raza_Mascota`, `MAS_Peso_Mascota`, `MAS_Estatura_Mascota`, `MAS_Estado`) VALUES
(1, 'Si', 'Firuais', 'Macho', 'Perro', 'Puggel', 13, 100, 'Active'),
(3, 'si', 'Kaizer', 'Macho', 'Perro', 'Pastor Alemán', 22, 40, 'Activo'),
(4, 'si', 'Pacha', 'Hembra', 'Gato', 'Burmés', 5, 10, 'Activo'),
(5, 'No', 'Pinki', 'Macho', 'Ave', 'hanster', 234, 12, 'Activo'),
(6, 'No', 'Ashe', 'Hembra', 'Perro', 'Labrador', 25, 1, 'Activo'),
(7, 'No', 'Anais', 'Macho', 'Perro', 'Chow chow', 4, 5, 'Activo'),
(8, 'No', 'Firr', 'Hembra', 'Gato', 'Arabe', 5, 2, 'Activo'),
(9, 'Si', 'ujhghgjgh', 'Hembra', 'Otro', 'Conejo Blanco', 34, 34, 'Active'),
(10, 'Si', 'Blanquito', 'Hembra', 'Otro', 'Conejo Blanco', 34, 34, 'Activo'),
(11, 'Si', 'asdfghj', 'Macho', 'Perro', 'asdfg', 0, 0, 'Inactivo'),
(12, 'No', 'say', 'Female', 'Cat', 'No se', 23, 20, 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_medicamento`
--

CREATE TABLE `tbl_medicamento` (
  `PK_MED_IdMedicamento` int(11) NOT NULL COMMENT 'En este campo se especifica el identificador del medicamento',
  `MED_Medicamento_Donado` varchar(50) NOT NULL,
  `MED_Nombre` text NOT NULL COMMENT 'En este campo se identifica el nombre del medicamento',
  `MED_Fecha_Vencimiento` date NOT NULL COMMENT 'Este campo contiene la fecha de vencimiento del medicamento',
  `MED_Tipo` varchar(40) NOT NULL COMMENT 'En este campo se especifica si el medicamento es en pastilla o como inyeccion',
  `MED_Cantidad` varchar(35) NOT NULL COMMENT 'En este campo se especifica la cantidad que se va a suministrar '
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbl_medicamento`
--

INSERT INTO `tbl_medicamento` (`PK_MED_IdMedicamento`, `MED_Medicamento_Donado`, `MED_Nombre`, `MED_Fecha_Vencimiento`, `MED_Tipo`, `MED_Cantidad`) VALUES
(1, 'No', 'CANIDRYL 100 MG', '2019-11-18', 'Oral', '100 MG'),
(2, 'No', 'CARPRODYL 20 MG 1 Comprimido', '2020-10-28', 'Oral', '20 MG'),
(3, 'No', 'CIMALGEX 8 mg 32 Comprimidos', '2021-09-15', 'Oral', '8 MG'),
(4, 'No', 'CORTEXONA RETARD 50 ML', '2021-01-23', 'Inyectable', '50 ML'),
(5, 'No', 'DEPO-MODERIN 5 ml', '2022-11-06', 'Inyectable', '5 ML'),
(6, 'Si', 'ibuprofeno', '2018-08-01', 'Pastilla', '5');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_procedimiento`
--

CREATE TABLE `tbl_procedimiento` (
  `PK_PRO_Procedimiento` int(11) NOT NULL COMMENT 'En este campo se especifica el identificador del procedimiento',
  `PRO_Nombre` varchar(45) NOT NULL COMMENT 'En este campo se identifica el nombre del procedimiento',
  `PRO_Descripcion` text NOT NULL COMMENT 'En este campo se identifica la descripcion del procedimiento'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='En esta tabla se almacenan los procedimientos medicos';

--
-- Volcado de datos para la tabla `tbl_procedimiento`
--

INSERT INTO `tbl_procedimiento` (`PK_PRO_Procedimiento`, `PRO_Nombre`, `PRO_Descripcion`) VALUES
(1, 'Vacunación ', 'A la mascota se le suministra la vacuna dependiendo vacunas posteriores o si es primera vez. '),
(2, 'Desparasitación ', 'A la mascota se le suministra el medicamento necesario para poder desparasitarlo.'),
(3, 'Cirugía ', 'A la mascota se le remite una orden para poder operarla en una entidad externa.'),
(4, 'Esterilización ', 'A la mascota se le hace el procedimiento para poder esterilizar a la mascota haciendo así que no pueda tener crías.'),
(5, 'Laboratorio ', 'A la mascota se le remite a una entidad externa para poder tomarles muestras de laboratorio.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_receta`
--

CREATE TABLE `tbl_receta` (
  `PK_REC_IdReceta` int(11) NOT NULL COMMENT 'En este campo se especifica el identificador de la receta',
  `REC_Fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'En este campo se especifica la fecha de la receta',
  `REC_Descripcion` text NOT NULL COMMENT 'En este campo se especifica el contenido de la receta'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbl_receta`
--

INSERT INTO `tbl_receta` (`PK_REC_IdReceta`, `REC_Fecha`, `REC_Descripcion`) VALUES
(6, '2018-06-27 19:29:30', 'Esta es una receta');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_recetamedicamento`
--

CREATE TABLE `tbl_recetamedicamento` (
  `FK_REC_Id_Receta` int(11) NOT NULL COMMENT 'En este campo se especifica el identificador de la receta',
  `FK_MED_IdMedicamento` int(11) NOT NULL COMMENT 'En este campo se especifica el identificador del medicamento'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_rol`
--

CREATE TABLE `tbl_rol` (
  `PK_ROL_Id_Rol` int(11) NOT NULL COMMENT 'En este campo se especifica el identificador del rol',
  `ROL_Nombre_Rol` varchar(20) NOT NULL COMMENT 'En este campo se especifica el nombre del rol'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbl_rol`
--

INSERT INTO `tbl_rol` (`PK_ROL_Id_Rol`, `ROL_Nombre_Rol`) VALUES
(1, 'Administrador'),
(2, 'Veterinario'),
(3, 'Cliente'),
(4, 'Empleado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_servicio`
--

CREATE TABLE `tbl_servicio` (
  `PK_SER_Id_Servicio` int(25) NOT NULL COMMENT 'En este campo se especifica el identificador del servicio',
  `SER_Nombre_Servicio` varchar(35) NOT NULL COMMENT 'En este campo se especifica el nombre del servicio',
  `SER_Descripcion` text NOT NULL COMMENT 'En este campo se especifica la descripcion del servicio'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbl_servicio`
--

INSERT INTO `tbl_servicio` (`PK_SER_Id_Servicio`, `SER_Nombre_Servicio`, `SER_Descripcion`) VALUES
(3, 'Baño', 'A la mascota se le hara un lavado externo completo.'),
(4, 'Peluquería', 'A la mascota se le hara un corte según las exigencias de el cliente.'),
(5, 'Estética', 'A la mascota se le hara un corte de uñas y un lavado de dientes.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_tipo`
--

CREATE TABLE `tbl_tipo` (
  `PK_TIP_Id_Tipo` int(11) NOT NULL COMMENT 'En este campo se especifica el identificador del tipo de cita',
  `TIP_Nombre` varchar(25) NOT NULL COMMENT 'En este campo se especifica el nombre del tipo de la cita'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbl_tipo`
--

INSERT INTO `tbl_tipo` (`PK_TIP_Id_Tipo`, `TIP_Nombre`) VALUES
(1, 'Prioritaria'),
(2, 'Urgencias');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_usuario`
--

CREATE TABLE `tbl_usuario` (
  `PK_USU_Id_Usuario` int(11) NOT NULL COMMENT 'En este campo se especifica el identificador del usuario',
  `USU_Nombre` varchar(50) DEFAULT NULL COMMENT 'En este campo se especifica el nombre del usuario',
  `USU_Apellido` varchar(50) NOT NULL COMMENT 'En este campo se especifica el apellido del usuario',
  `USU_Direccion` varchar(30) NOT NULL COMMENT 'En este campo se especifica la dirección del usuario',
  `USU_Email` varchar(50) NOT NULL COMMENT 'En este campo se especifica la direccion de correo del usuario',
  `USU_estado` varchar(50) DEFAULT 'Inactivo' COMMENT 'En este campo se especifica si el usuario esta  Activo o Inactivo',
  `USU_Clave` varchar(30) NOT NULL,
  `USU_Sexo` varchar(50) NOT NULL COMMENT 'En este campo se especifica el sexo del usuario',
  `USU_Fecha_Nacimiento` date NOT NULL COMMENT 'En este campo se especifica la fecha de nacimiento del usuario',
  `USU_Tipo_Documento` varchar(25) NOT NULL COMMENT 'En este campo se especifica el tipo de documento de identificacion del usuario',
  `USU_Numero_Documento` bigint(20) NOT NULL COMMENT 'En este campo se especifica el numero de documento del usuario',
  `USU_Telefono` bigint(20) NOT NULL COMMENT 'En este campo se especifica el numero telefonico del usuario'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbl_usuario`
--

INSERT INTO `tbl_usuario` (`PK_USU_Id_Usuario`, `USU_Nombre`, `USU_Apellido`, `USU_Direccion`, `USU_Email`, `USU_estado`, `USU_Clave`, `USU_Sexo`, `USU_Fecha_Nacimiento`, `USU_Tipo_Documento`, `USU_Numero_Documento`, `USU_Telefono`) VALUES
(31, 'Diego Andres', 'Burgos Estrada', 'Cr:17 N 127 ', 'diedburgos@gmail.com', 'Activo', '469872', 'Masculino', '2018-06-12', 'Cedula', 8555669, 32456),
(32, 'Alexander', 'Suarez', 'Cra 59 #32-44', 'jasuarez028@misena.edu.co', 'Activo', '12345', 'Masculino', '2018-06-12', 'Cedula', 12345678, 4041339),
(33, 'Maria ', 'Burgos ', 'cr:32', 'mariana150519@gmail.com', 'Activo', '693187', 'Mujer', '1986-05-15', 'Cedula', 1234567890, 1234567890),
(34, 'Angela', 'zabala', '', 'angela@gmail.com', 'Activo', '', 'Mujer', '0000-00-00', 'Cedula', 1234567, 576543),
(35, 'Rrar', 'Nose', 'Cra nose', 'rrar@gmail.com', 'Inactivo', '456', 'Hombre', '1990-03-16', 'Cedula', 85296374, 7418547),
(36, 'empanada', 'depollo', 'Cra 15', 'empanada@pollo.com', 'Activo', '74102', 'Hombre', '1994-07-16', 'Cedula', 85555444, 123663),
(37, 'maria', 'rojas', 'cra 5 # 35-89', 'mariana@gmail.com', 'Inactivo', '14785', 'Hombre', '2018-08-15', 'Cedula', 1234567, 3217658970),
(38, 'Marta', 'Mendez', 'Calle 23', 'marta@gmail.com', 'Inactivo', '123', 'Woman', '2018-09-19', 'Cedula', 2345678907, 87654323);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_usuariomascota`
--

CREATE TABLE `tbl_usuariomascota` (
  `FK_USU_Id_Usuario` int(11) NOT NULL COMMENT 'En este campo se especifica el identificador del usuario',
  `FK_MAS_Id_Mascota` int(11) NOT NULL COMMENT 'En este campo se especifica el identificador de la mascota'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbl_usuariomascota`
--

INSERT INTO `tbl_usuariomascota` (`FK_USU_Id_Usuario`, `FK_MAS_Id_Mascota`) VALUES
(31, 6),
(31, 7),
(32, 1),
(32, 3),
(32, 5),
(33, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_usuariorol`
--

CREATE TABLE `tbl_usuariorol` (
  `FK_USU_Id_Usuario` int(11) NOT NULL COMMENT 'En este campo se especifica el identificador del usuario',
  `FK_ROL_Id_Rol` int(11) NOT NULL COMMENT 'En este campo se identifica el identificador del rol'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbl_usuariorol`
--

INSERT INTO `tbl_usuariorol` (`FK_USU_Id_Usuario`, `FK_ROL_Id_Rol`) VALUES
(31, 1),
(31, 2),
(31, 4),
(31, 3),
(32, 4),
(32, 1),
(34, 1),
(34, 2),
(36, 1),
(36, 2),
(32, 2),
(32, 3),
(33, 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tbl_citamedica`
--
ALTER TABLE `tbl_citamedica`
  ADD PRIMARY KEY (`PK_CIT_IdCita`),
  ADD KEY `FK_USU_IdUsuario` (`FK_USU_IdUsuario`),
  ADD KEY `FK_PRO_Procedimientos` (`FK_PRO_Procedimiento`),
  ADD KEY `FK_TIP_IdTipo` (`FK_TIP_IdTipo`),
  ADD KEY `FK_MAS_IdMascota` (`FK_MAS_IdMascota`);

--
-- Indices de la tabla `tbl_citaservicio`
--
ALTER TABLE `tbl_citaservicio`
  ADD PRIMARY KEY (`PK_CIS_Citaservicio`),
  ADD KEY `FK_MAS_IdMascota` (`FK_MAS_IdMascota`),
  ADD KEY `Usu_serv` (`FK_USU_IdUsuario`) USING BTREE,
  ADD KEY `servic` (`FK_SER_IdServicio`) USING BTREE;

--
-- Indices de la tabla `tbl_historiaclinica`
--
ALTER TABLE `tbl_historiaclinica`
  ADD PRIMARY KEY (`PK_HIS_IdHistoriaClinica`),
  ADD KEY `IdMascota` (`FK_MAS_IdMascota`) USING BTREE;

--
-- Indices de la tabla `tbl_historiareceta`
--
ALTER TABLE `tbl_historiareceta`
  ADD KEY `FK_HIS_IdHistoriaClinica` (`FK_HIS_IdHistoriaClinica`),
  ADD KEY `FK_REC_IdReceta` (`FK_REC_IdReceta`);

--
-- Indices de la tabla `tbl_mascota`
--
ALTER TABLE `tbl_mascota`
  ADD PRIMARY KEY (`PK_MAS_IdMascota`);

--
-- Indices de la tabla `tbl_medicamento`
--
ALTER TABLE `tbl_medicamento`
  ADD PRIMARY KEY (`PK_MED_IdMedicamento`);

--
-- Indices de la tabla `tbl_procedimiento`
--
ALTER TABLE `tbl_procedimiento`
  ADD PRIMARY KEY (`PK_PRO_Procedimiento`);

--
-- Indices de la tabla `tbl_receta`
--
ALTER TABLE `tbl_receta`
  ADD PRIMARY KEY (`PK_REC_IdReceta`);

--
-- Indices de la tabla `tbl_recetamedicamento`
--
ALTER TABLE `tbl_recetamedicamento`
  ADD KEY `FK_REC_IdReceta` (`FK_REC_Id_Receta`),
  ADD KEY `FK_MED_IdMedicamento` (`FK_MED_IdMedicamento`);

--
-- Indices de la tabla `tbl_rol`
--
ALTER TABLE `tbl_rol`
  ADD PRIMARY KEY (`PK_ROL_Id_Rol`);

--
-- Indices de la tabla `tbl_servicio`
--
ALTER TABLE `tbl_servicio`
  ADD PRIMARY KEY (`PK_SER_Id_Servicio`);

--
-- Indices de la tabla `tbl_tipo`
--
ALTER TABLE `tbl_tipo`
  ADD PRIMARY KEY (`PK_TIP_Id_Tipo`);

--
-- Indices de la tabla `tbl_usuario`
--
ALTER TABLE `tbl_usuario`
  ADD PRIMARY KEY (`PK_USU_Id_Usuario`);

--
-- Indices de la tabla `tbl_usuariomascota`
--
ALTER TABLE `tbl_usuariomascota`
  ADD UNIQUE KEY `FK_MAS_Id_Mascota` (`FK_MAS_Id_Mascota`),
  ADD KEY `FK_USU_IdUsuario` (`FK_USU_Id_Usuario`),
  ADD KEY `FK_MAS_IdMascota` (`FK_MAS_Id_Mascota`);

--
-- Indices de la tabla `tbl_usuariorol`
--
ALTER TABLE `tbl_usuariorol`
  ADD KEY `FK_USU_IdUsuario` (`FK_USU_Id_Usuario`),
  ADD KEY `FK_ROL_IdRol` (`FK_ROL_Id_Rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tbl_citamedica`
--
ALTER TABLE `tbl_citamedica`
  MODIFY `PK_CIT_IdCita` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Este campo es el identificador de las citas', AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `tbl_citaservicio`
--
ALTER TABLE `tbl_citaservicio`
  MODIFY `PK_CIS_Citaservicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT de la tabla `tbl_historiaclinica`
--
ALTER TABLE `tbl_historiaclinica`
  MODIFY `PK_HIS_IdHistoriaClinica` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Este campo es el identificador de la histori clinica', AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT de la tabla `tbl_mascota`
--
ALTER TABLE `tbl_mascota`
  MODIFY `PK_MAS_IdMascota` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Este campo es el identificador de la mascota', AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `tbl_medicamento`
--
ALTER TABLE `tbl_medicamento`
  MODIFY `PK_MED_IdMedicamento` int(11) NOT NULL AUTO_INCREMENT COMMENT 'En este campo se especifica el identificador del medicamento', AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tbl_procedimiento`
--
ALTER TABLE `tbl_procedimiento`
  MODIFY `PK_PRO_Procedimiento` int(11) NOT NULL AUTO_INCREMENT COMMENT 'En este campo se especifica el identificador del procedimiento', AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tbl_receta`
--
ALTER TABLE `tbl_receta`
  MODIFY `PK_REC_IdReceta` int(11) NOT NULL AUTO_INCREMENT COMMENT 'En este campo se especifica el identificador de la receta', AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tbl_rol`
--
ALTER TABLE `tbl_rol`
  MODIFY `PK_ROL_Id_Rol` int(11) NOT NULL AUTO_INCREMENT COMMENT 'En este campo se especifica el identificador del rol', AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tbl_tipo`
--
ALTER TABLE `tbl_tipo`
  MODIFY `PK_TIP_Id_Tipo` int(11) NOT NULL AUTO_INCREMENT COMMENT 'En este campo se especifica el identificador del tipo de cita', AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tbl_usuario`
--
ALTER TABLE `tbl_usuario`
  MODIFY `PK_USU_Id_Usuario` int(11) NOT NULL AUTO_INCREMENT COMMENT 'En este campo se especifica el identificador del usuario', AUTO_INCREMENT=39;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tbl_citamedica`
--
ALTER TABLE `tbl_citamedica`
  ADD CONSTRAINT `tbl_citamedica_ibfk_1` FOREIGN KEY (`FK_PRO_Procedimiento`) REFERENCES `tbl_procedimiento` (`PK_PRO_Procedimiento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tbl_citamedica_ibfk_2` FOREIGN KEY (`FK_USU_IdUsuario`) REFERENCES `tbl_usuario` (`PK_USU_Id_Usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tbl_citamedica_ibfk_3` FOREIGN KEY (`FK_TIP_IdTipo`) REFERENCES `tbl_tipo` (`PK_TIP_Id_Tipo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tbl_citamedica_ibfk_4` FOREIGN KEY (`FK_MAS_IdMascota`) REFERENCES `tbl_mascota` (`PK_MAS_IdMascota`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tbl_citaservicio`
--
ALTER TABLE `tbl_citaservicio`
  ADD CONSTRAINT `tbl_citaservicio_ibfk_1` FOREIGN KEY (`FK_USU_IdUsuario`) REFERENCES `tbl_usuario` (`PK_USU_Id_Usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tbl_citaservicio_ibfk_2` FOREIGN KEY (`FK_SER_IdServicio`) REFERENCES `tbl_servicio` (`PK_SER_Id_Servicio`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tbl_citaservicio_ibfk_3` FOREIGN KEY (`FK_MAS_IdMascota`) REFERENCES `tbl_mascota` (`PK_MAS_IdMascota`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tbl_historiaclinica`
--
ALTER TABLE `tbl_historiaclinica`
  ADD CONSTRAINT `tbl_historiaclinica_ibfk_1` FOREIGN KEY (`FK_MAS_IdMascota`) REFERENCES `tbl_mascota` (`PK_MAS_IdMascota`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tbl_historiareceta`
--
ALTER TABLE `tbl_historiareceta`
  ADD CONSTRAINT `tbl_historiareceta_ibfk_1` FOREIGN KEY (`FK_HIS_IdHistoriaClinica`) REFERENCES `tbl_historiaclinica` (`PK_HIS_IdHistoriaClinica`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tbl_historiareceta_ibfk_2` FOREIGN KEY (`FK_REC_IdReceta`) REFERENCES `tbl_receta` (`PK_REC_IdReceta`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tbl_recetamedicamento`
--
ALTER TABLE `tbl_recetamedicamento`
  ADD CONSTRAINT `tbl_recetamedicamento_ibfk_1` FOREIGN KEY (`FK_REC_Id_Receta`) REFERENCES `tbl_receta` (`PK_REC_IdReceta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tbl_recetamedicamento_ibfk_2` FOREIGN KEY (`FK_MED_IdMedicamento`) REFERENCES `tbl_medicamento` (`PK_MED_IdMedicamento`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tbl_usuariomascota`
--
ALTER TABLE `tbl_usuariomascota`
  ADD CONSTRAINT `tbl_usuariomascota_ibfk_1` FOREIGN KEY (`FK_USU_Id_Usuario`) REFERENCES `tbl_usuario` (`PK_USU_Id_Usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tbl_usuariomascota_ibfk_2` FOREIGN KEY (`FK_MAS_Id_Mascota`) REFERENCES `tbl_mascota` (`PK_MAS_IdMascota`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tbl_usuariorol`
--
ALTER TABLE `tbl_usuariorol`
  ADD CONSTRAINT `tbl_usuariorol_ibfk_1` FOREIGN KEY (`FK_USU_Id_Usuario`) REFERENCES `tbl_usuario` (`PK_USU_Id_Usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tbl_usuariorol_ibfk_2` FOREIGN KEY (`FK_ROL_Id_Rol`) REFERENCES `tbl_rol` (`PK_ROL_Id_Rol`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
