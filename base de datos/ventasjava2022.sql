-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3312
-- Tiempo de generación: 09-04-2023 a las 20:20:40
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ventasjava2022`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `arqueo`
--

CREATE TABLE `arqueo` (
  `id` int(11) NOT NULL,
  `monton` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `arqueo`
--

INSERT INTO `arqueo` (`id`, `monton`) VALUES
(16, '4000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nota_credito`
--

CREATE TABLE `nota_credito` (
  `id` int(6) UNSIGNED NOT NULL,
  `nota_credito` varchar(15) DEFAULT '0001-000001'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `nota_credito`
--

INSERT INTO `nota_credito` (`id`, `nota_credito`) VALUES
(1, '0001-000001'),
(2, '0001-000002'),
(3, '0001-000003'),
(4, '0001-000004'),
(5, '0001-000005'),
(6, '0001-000006'),
(7, '0001-000006'),
(8, '0001-000007');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_nota_credito`
--

CREATE TABLE `tabla_nota_credito` (
  `id` int(11) NOT NULL,
  `dia` varchar(200) DEFAULT NULL,
  `mes` varchar(200) DEFAULT NULL,
  `anio` varchar(4) DEFAULT NULL,
  `notaCredito` varchar(255) DEFAULT NULL,
  `numeroFactura` varchar(255) DEFAULT NULL,
  `razon` varchar(255) DEFAULT NULL,
  `dinero` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tabla_nota_credito`
--

INSERT INTO `tabla_nota_credito` (`id`, `dia`, `mes`, `anio`, `notaCredito`, `numeroFactura`, `razon`, `dinero`) VALUES
(1, '5', 'abril', '2023', '0001-000001', '154', 'Por devolucion de items', '-200000'),
(2, '5', 'abril', '2023', '0001-000002', '159', 'Por devolucion de items', '-23000'),
(3, '5', 'abril', '2023', '0001-000003', '160', 'Por devolucion de items', '-250000'),
(4, '5', 'Abril', '2023', '0001-000004', '161', 'Por devolucion de items', '-2000'),
(5, '5', 'Abril', '2023', '0001-000005', '162', 'Por devolución de items', '-10000'),
(6, '6', 'Abril', '2023', '0001-000006', '20365', 'Por Intereses Financieros', '500000'),
(7, '6', 'Abril', '2023', '0001-000006', '45678', 'Por Gastos Bancarios', '30000'),
(8, '6', 'Abril', '2023', '0001-000007', '12345', 'Por Gastos Bancarios', '123456');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_productos`
--

CREATE TABLE `tabla_productos` (
  `id` int(11) NOT NULL,
  `factura` int(11) DEFAULT NULL,
  `cantidad` varchar(200) DEFAULT NULL,
  `productos` varchar(200) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `precioUnitario` varchar(200) DEFAULT NULL,
  `importe` varchar(200) DEFAULT NULL,
  `igv` varchar(200) DEFAULT NULL,
  `total` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tabla_productos`
--

INSERT INTO `tabla_productos` (`id`, `factura`, `cantidad`, `productos`, `descripcion`, `precioUnitario`, `importe`, `igv`, `total`) VALUES
(1, 152, '1', 'coca-cola', 'gaseosa negra', '1000', '1000.0', '1710.0', '9000.0'),
(2, 152, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '1710.0', '9000.0'),
(3, 153, '1', 'coca-cola', 'gaseosa negra', '1000', '1000.0', '190.0', '1000.0'),
(15, 163, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '1520.0', '8000.0'),
(16, 164, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '1520.0', '8000.0'),
(17, 165, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(18, 166, '1', 'coca-cola', 'gaseosa negra', '1000', '1000.0', '100.0', '1000.0'),
(19, 167, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(20, 169, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(21, 170, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(22, 171, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(23, 172, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '900.0', '9000.0'),
(24, 172, '1', 'coca-cola', 'gaseosa negra', '1000', '1000.0', '900.0', '9000.0'),
(25, 173, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(26, 174, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(27, 175, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(28, 176, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(29, 177, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(30, 178, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(31, 179, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(32, 180, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(33, 181, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(34, 182, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(35, 183, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '900.0', '9000.0'),
(36, 183, '1', 'coca-cola', 'gaseosa negra', '1000', '1000.0', '900.0', '9000.0'),
(37, 184, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(38, 185, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(39, 186, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(40, 187, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(41, 188, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(42, 189, '1', 'coca-cola', 'gaseosa negra', '1000', '1000.0', '100.0', '1000.0'),
(43, 190, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(44, 191, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(45, 192, '1', 'coca-cola', 'gaseosa negra', '1000', '1000.0', '900.0', '9000.0'),
(46, 192, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '900.0', '9000.0'),
(47, 193, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(48, 194, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(49, 195, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(50, 196, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(51, 197, '1', 'manzana', 'manzana rojas y verdes', '8000', '8000.0', '800.0', '8000.0'),
(52, 198, '18', 'manzana', 'manzana rojas y verdes', '8000', '144000.0', '14400.0', '144000.0'),
(53, 199, '1', 'coca-cola', 'gaseosa negra', '1000', '1000.0', '100.0', '1000.0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `table_cajas`
--

CREATE TABLE `table_cajas` (
  `id_table_cajas` int(200) NOT NULL,
  `fecha` date NOT NULL,
  `monto` float NOT NULL,
  `estado` varchar(200) NOT NULL,
  `guardar` varchar(255) DEFAULT NULL,
  `saldo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `table_cajas`
--

INSERT INTO `table_cajas` (`id_table_cajas`, `fecha`, `monto`, `estado`, `guardar`, `saldo`) VALUES
(20, '2023-03-08', 88000, 'cerrado', 'sobrante', '95000'),
(24, '2023-03-08', 2000, 'cerrado', 'Defalco', '-1900'),
(25, '2023-03-08', 5000, 'cerrado', 'Correcto', '0'),
(26, '2023-03-08', 234000, 'cerrado', 'Defalco', '-210000'),
(27, '2023-03-08', 3000, 'cerrado', 'Correcto', '0'),
(28, '2023-03-09', 4000, 'abierto', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `table_categoria`
--

CREATE TABLE `table_categoria` (
  `id_categoria` int(200) NOT NULL,
  `nombre_categoria` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `table_categoria`
--

INSERT INTO `table_categoria` (`id_categoria`, `nombre_categoria`) VALUES
(14, 'gaseosa'),
(15, 'frutas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `table_cliente`
--

CREATE TABLE `table_cliente` (
  `idCliente` int(11) NOT NULL,
  `Nombre_Cliente` varchar(45) NOT NULL,
  `Apellido_Cliente` varchar(45) NOT NULL,
  `razon_s_Cliente` varchar(200) NOT NULL,
  `ruc_Cliente` varchar(20) NOT NULL,
  `direccion_Cliente` varchar(100) NOT NULL,
  `telefono_Cliente` varchar(20) NOT NULL,
  `correo_Cliente` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `table_cliente`
--

INSERT INTO `table_cliente` (`idCliente`, `Nombre_Cliente`, `Apellido_Cliente`, `razon_s_Cliente`, `ruc_Cliente`, `direccion_Cliente`, `telefono_Cliente`, `correo_Cliente`) VALUES
(13, '543543', '453543', '101', '5423453', '453543', '453543', '543543453');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `table_compras`
--

CREATE TABLE `table_compras` (
  `idCompras` int(11) NOT NULL,
  `No_facturas_compras` int(11) NOT NULL,
  `Productos_Compras` int(11) NOT NULL,
  `Cantidad` int(200) NOT NULL,
  `importe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `table_configuracion`
--

CREATE TABLE `table_configuracion` (
  `id_configuracion` int(200) NOT NULL,
  `nombre_empresa` varchar(200) NOT NULL,
  `impuesto` varchar(200) NOT NULL,
  `moneda` varchar(200) NOT NULL,
  `simbolo_moneda` varchar(200) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `ruc` varchar(200) NOT NULL,
  `celular` varchar(200) NOT NULL,
  `dimension_x` varchar(200) NOT NULL,
  `dimension_y` varchar(200) NOT NULL,
  `cantidad_ceros_boleta` varchar(200) NOT NULL,
  `cantidad_ceros_factura` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `table_configuracion`
--

INSERT INTO `table_configuracion` (`id_configuracion`, `nombre_empresa`, `impuesto`, `moneda`, `simbolo_moneda`, `direccion`, `ruc`, `celular`, `dimension_x`, `dimension_y`, `cantidad_ceros_boleta`, `cantidad_ceros_factura`) VALUES
(1, 'Santa Isabel Minimarket', '10', 'Guarani', 'G', 'Calle Santa Isabel', '20113322', '95464564', '310', '160', '000000000', '001-001-00001');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `table_facturas`
--

CREATE TABLE `table_facturas` (
  `No_Facturas` int(11) NOT NULL,
  `cliente` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `totals` int(11) NOT NULL,
  `nro_boleta` int(11) NOT NULL,
  `factura_boleta` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `table_facturas`
--

INSERT INTO `table_facturas` (`No_Facturas`, `cliente`, `fecha`, `totals`, `nro_boleta`, `factura_boleta`) VALUES
(132, 13, '2023-04-01', 1000, 1, 'factura'),
(133, 13, '2023-04-01', 8000, 2, 'factura'),
(134, 13, '2023-04-01', 3000, 3, 'factura'),
(135, 13, '2023-04-01', 0, 4, 'factura'),
(136, 13, '2023-04-01', 2000, 5, 'factura'),
(137, 13, '2023-04-01', 1000, 6, 'factura'),
(138, 13, '2023-04-01', 1000, 7, 'factura'),
(139, 13, '2023-04-01', 0, 8, 'factura'),
(140, 13, '2023-04-01', 1000, 9, 'factura'),
(141, 13, '2023-04-01', 1000, 10, 'factura'),
(142, 13, '2023-04-01', 0, 11, 'factura'),
(143, 13, '2023-04-01', 1000, 12, 'factura'),
(144, 13, '2023-04-01', 1000, 13, 'factura'),
(145, 13, '2023-04-01', 1000, 14, 'factura'),
(146, 13, '2023-04-01', 1000, 15, 'factura'),
(147, 13, '2023-04-01', 41000, 16, 'factura'),
(148, 13, '2023-04-01', 9000, 17, 'factura'),
(149, 13, '2023-04-01', 9000, 18, 'factura'),
(150, 13, '2023-04-01', 9000, 19, 'factura'),
(151, 13, '2023-04-01', 9000, 20, 'factura'),
(163, 13, '2023-04-06', 8000, 21, 'factura'),
(164, 13, '2023-04-06', 8000, 22, 'factura'),
(165, 13, '2023-04-06', 8000, 23, 'factura'),
(166, 13, '2023-04-06', 1000, 24, 'factura'),
(167, 13, '2023-04-06', 8000, 25, 'factura'),
(168, 13, '2023-04-06', 0, 26, 'factura'),
(169, 13, '2023-04-06', 8000, 27, 'factura'),
(170, 13, '2023-04-06', 8000, 28, 'factura'),
(171, 13, '2023-04-06', 8000, 29, 'factura'),
(172, 13, '2023-04-06', 9000, 30, 'factura'),
(173, 13, '2023-04-06', 8000, 31, 'factura'),
(174, 13, '2023-04-06', 8000, 32, 'factura'),
(175, 13, '2023-04-06', 8000, 33, 'factura'),
(176, 13, '2023-04-06', 8000, 34, 'factura'),
(177, 13, '2023-04-06', 8000, 35, 'factura'),
(178, 13, '2023-04-06', 8000, 36, 'factura'),
(179, 13, '2023-04-06', 8000, 37, 'factura'),
(180, 13, '2023-04-06', 8000, 38, 'factura'),
(181, 13, '2023-04-06', 8000, 39, 'factura'),
(182, 13, '2023-04-06', 8000, 40, 'factura'),
(183, 13, '2023-04-06', 9000, 41, 'factura'),
(184, 13, '2023-04-06', 8000, 42, 'factura'),
(185, 13, '2023-04-06', 8000, 43, 'factura'),
(186, 13, '2023-04-06', 8000, 44, 'factura'),
(187, 13, '2023-04-06', 8000, 45, 'factura'),
(188, 13, '2023-04-06', 8000, 46, 'factura'),
(189, 13, '2023-04-06', 1000, 47, 'factura'),
(190, 13, '2023-04-06', 8000, 48, 'factura'),
(191, 13, '2023-04-06', 8000, 49, 'factura'),
(192, 13, '2023-04-06', 9000, 50, 'factura'),
(193, 13, '2023-04-06', 8000, 51, 'factura'),
(194, 13, '2023-04-06', 8000, 52, 'factura'),
(195, 13, '2023-04-06', 8000, 53, 'factura'),
(196, 13, '2023-04-06', 8000, 54, 'factura'),
(197, 13, '2023-04-08', 8000, 55, 'factura'),
(198, 13, '2023-04-08', 144000, 56, 'factura'),
(199, 13, '2023-04-08', 1000, 57, 'factura');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `table_facturas_compras`
--

CREATE TABLE `table_facturas_compras` (
  `No_Facturas_Compras` int(11) NOT NULL,
  `proveedor` int(11) NOT NULL,
  `fecha_compras` date NOT NULL,
  `totals_compras` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `table_guia_remision`
--

CREATE TABLE `table_guia_remision` (
  `id_guia` int(11) NOT NULL,
  `punto_par` varchar(50) NOT NULL,
  `punto_lle` varchar(50) NOT NULL,
  `fecha_traslado` date NOT NULL,
  `costo_min` int(10) NOT NULL,
  `nom_empresa_transportes` varchar(100) NOT NULL,
  `ruc_empresa_transportes` varchar(50) NOT NULL,
  `marca_num_placa` varchar(50) NOT NULL,
  `nro_cons_inscripcion` varchar(50) NOT NULL,
  `nro_lic_conductor` varchar(50) NOT NULL,
  `tipo_num_comp_pago` varchar(50) NOT NULL,
  `orden_compra` varchar(50) NOT NULL,
  `cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `table_guia_remision`
--

INSERT INTO `table_guia_remision` (`id_guia`, `punto_par`, `punto_lle`, `fecha_traslado`, `costo_min`, `nom_empresa_transportes`, `ruc_empresa_transportes`, `marca_num_placa`, `nro_cons_inscripcion`, `nro_lic_conductor`, `tipo_num_comp_pago`, `orden_compra`, `cliente`) VALUES
(1, 'av panamericana', 'jr lima', '2016-10-20', 19, 'etecsa', '1321312', 'toyota hylux', '13132', '12321', 'recibo', '12', 1),
(2, 'av panamericana', 'av barzenas', '2016-10-16', 38, 'wari', '123132123', 'hyundai 42432432', '121234', '43242234', 'boleta 32131', '', 1),
(3, '', '', '2016-10-20', 9, '', '', '', '', '', '', '', 0),
(4, '', '', '2016-10-16', 0, '', '', '', '', '', '', '', 3),
(5, '', '', '2016-10-16', 0, '', '', '', '', '', '', '', 2),
(6, '', '', '2016-10-16', 0, '', '', '', '', '', '', '', 1),
(7, '', '', '2016-10-09', 0, '', '', '', '', '', '', '', 3),
(8, '', '', '2016-10-17', 0, '', '', '', '', '', '', '', 4),
(9, 'cusco', 'abancay', '2016-10-12', 0, '', '', '', '', '', '', '', 3),
(10, 'cusco', 'abancay', '2016-10-14', 0, '', '', '', '', '', '', '', 4),
(11, 'cusco', 'abancay', '2016-10-08', 0, '', '', '', '', '', '', '', 3),
(12, 'cusco', 'abancay', '2016-10-17', 0, '', '', '', '', '', '', '', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `table_productos`
--

CREATE TABLE `table_productos` (
  `idProductos` int(10) NOT NULL,
  `nombreProductos` varchar(45) NOT NULL,
  `preciosProductos` float NOT NULL,
  `descripcionProductos` varchar(200) NOT NULL,
  `cantidadProductos` int(11) NOT NULL,
  `preciocompraProductos` float NOT NULL,
  `Difererencia` varchar(200) NOT NULL,
  `codigo` varchar(200) NOT NULL,
  `categoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `table_productos`
--

INSERT INTO `table_productos` (`idProductos`, `nombreProductos`, `preciosProductos`, `descripcionProductos`, `cantidadProductos`, `preciocompraProductos`, `Difererencia`, `codigo`, `categoria`) VALUES
(183, 'coca-cola', 1000, 'gaseosa negra', 17, 500, '500.0', 'xxx', 14),
(184, 'manzana', 8000, 'manzana rojas y verdes', 7938, 8000, '0.0', 'xxx', 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `table_proveedor`
--

CREATE TABLE `table_proveedor` (
  `id_proveedor` int(11) NOT NULL,
  `razon_social_proveedor` varchar(200) NOT NULL,
  `ruc_proveedor` varchar(200) NOT NULL,
  `direccion_proveedor` varchar(200) NOT NULL,
  `telefono_proveedor` varchar(200) NOT NULL,
  `correo_proveedor` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `table_ventas`
--

CREATE TABLE `table_ventas` (
  `idVentas` int(11) NOT NULL,
  `No_Facturas` int(11) NOT NULL,
  `Productos` int(10) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `importe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `table_ventas`
--

INSERT INTO `table_ventas` (`idVentas`, `No_Facturas`, `Productos`, `cantidad`, `importe`) VALUES
(209, 132, 183, 1, 1000),
(210, 133, 183, 8, 8000),
(211, 134, 183, 1, 1000),
(212, 134, 183, 2, 2000),
(213, 136, 183, 1, 1000),
(214, 136, 183, 1, 1000),
(215, 146, 183, 1, 1000),
(216, 147, 183, 1, 1000),
(217, 147, 184, 5, 40000),
(218, 148, 183, 1, 1000),
(219, 148, 184, 1, 8000),
(234, 163, 184, 1, 8000),
(235, 164, 184, 1, 8000),
(236, 165, 184, 1, 8000),
(237, 166, 183, 1, 1000),
(238, 167, 184, 1, 8000),
(239, 169, 184, 1, 8000),
(240, 170, 184, 1, 8000),
(241, 171, 184, 1, 8000),
(242, 172, 184, 1, 8000),
(243, 172, 183, 1, 1000),
(244, 173, 184, 1, 8000),
(245, 174, 184, 1, 8000),
(246, 175, 184, 1, 8000),
(247, 176, 184, 1, 8000),
(248, 177, 184, 1, 8000),
(249, 178, 184, 1, 8000),
(250, 179, 184, 1, 8000),
(251, 180, 184, 1, 8000),
(252, 181, 184, 1, 8000),
(253, 182, 184, 1, 8000),
(254, 183, 184, 1, 8000),
(255, 183, 183, 1, 1000),
(256, 184, 184, 1, 8000),
(257, 185, 184, 1, 8000),
(258, 186, 184, 1, 8000),
(259, 187, 184, 1, 8000),
(260, 188, 184, 1, 8000),
(261, 189, 183, 1, 1000),
(262, 190, 184, 1, 8000),
(263, 191, 184, 1, 8000),
(264, 192, 183, 1, 1000),
(265, 192, 184, 1, 8000),
(266, 193, 184, 1, 8000),
(267, 194, 184, 1, 8000),
(268, 195, 184, 1, 8000),
(269, 196, 184, 1, 8000),
(270, 197, 184, 1, 8000),
(271, 198, 184, 18, 144000),
(272, 199, 183, 1, 1000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(10) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `pasword` varchar(50) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `dni` int(8) NOT NULL,
  `telefono` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `usuario`, `pasword`, `tipo`, `nombres`, `apellidos`, `dni`, `telefono`) VALUES
(1, 'admin', 'admin', 'Administrador', 'Tusolutionweb', 'tutos', 43121223, 321132),
(5, 'david', 'davis', 'Empleado', 'david', 'david', 1004, 3014),
(6, 'david', 'david', 'Administrador', 'david', 'david', 0, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `arqueo`
--
ALTER TABLE `arqueo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `nota_credito`
--
ALTER TABLE `nota_credito`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tabla_nota_credito`
--
ALTER TABLE `tabla_nota_credito`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tabla_productos`
--
ALTER TABLE `tabla_productos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `table_cajas`
--
ALTER TABLE `table_cajas`
  ADD PRIMARY KEY (`id_table_cajas`);

--
-- Indices de la tabla `table_categoria`
--
ALTER TABLE `table_categoria`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indices de la tabla `table_cliente`
--
ALTER TABLE `table_cliente`
  ADD PRIMARY KEY (`idCliente`),
  ADD UNIQUE KEY `idCliente_UNIQUE` (`idCliente`);

--
-- Indices de la tabla `table_compras`
--
ALTER TABLE `table_compras`
  ADD PRIMARY KEY (`idCompras`);

--
-- Indices de la tabla `table_configuracion`
--
ALTER TABLE `table_configuracion`
  ADD PRIMARY KEY (`id_configuracion`);

--
-- Indices de la tabla `table_facturas`
--
ALTER TABLE `table_facturas`
  ADD PRIMARY KEY (`No_Facturas`),
  ADD UNIQUE KEY `No_Facturas_UNIQUE` (`No_Facturas`),
  ADD KEY `llavesForaneas_Cliente_idx` (`cliente`);

--
-- Indices de la tabla `table_facturas_compras`
--
ALTER TABLE `table_facturas_compras`
  ADD PRIMARY KEY (`No_Facturas_Compras`);

--
-- Indices de la tabla `table_guia_remision`
--
ALTER TABLE `table_guia_remision`
  ADD PRIMARY KEY (`id_guia`);

--
-- Indices de la tabla `table_productos`
--
ALTER TABLE `table_productos`
  ADD PRIMARY KEY (`idProductos`),
  ADD UNIQUE KEY `idtProductos_UNIQUE` (`idProductos`);

--
-- Indices de la tabla `table_proveedor`
--
ALTER TABLE `table_proveedor`
  ADD PRIMARY KEY (`id_proveedor`);

--
-- Indices de la tabla `table_ventas`
--
ALTER TABLE `table_ventas`
  ADD PRIMARY KEY (`idVentas`),
  ADD UNIQUE KEY `idVentas_UNIQUE` (`idVentas`),
  ADD KEY `llavesForaneas_Facturas_idx` (`No_Facturas`),
  ADD KEY `llavesForaneas_Productos_idx` (`Productos`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `arqueo`
--
ALTER TABLE `arqueo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `nota_credito`
--
ALTER TABLE `nota_credito`
  MODIFY `id` int(6) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `tabla_nota_credito`
--
ALTER TABLE `tabla_nota_credito`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `tabla_productos`
--
ALTER TABLE `tabla_productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT de la tabla `table_cajas`
--
ALTER TABLE `table_cajas`
  MODIFY `id_table_cajas` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `table_categoria`
--
ALTER TABLE `table_categoria`
  MODIFY `id_categoria` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `table_cliente`
--
ALTER TABLE `table_cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `table_compras`
--
ALTER TABLE `table_compras`
  MODIFY `idCompras` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `table_configuracion`
--
ALTER TABLE `table_configuracion`
  MODIFY `id_configuracion` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `table_facturas`
--
ALTER TABLE `table_facturas`
  MODIFY `No_Facturas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=200;

--
-- AUTO_INCREMENT de la tabla `table_facturas_compras`
--
ALTER TABLE `table_facturas_compras`
  MODIFY `No_Facturas_Compras` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `table_guia_remision`
--
ALTER TABLE `table_guia_remision`
  MODIFY `id_guia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `table_productos`
--
ALTER TABLE `table_productos`
  MODIFY `idProductos` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=185;

--
-- AUTO_INCREMENT de la tabla `table_proveedor`
--
ALTER TABLE `table_proveedor`
  MODIFY `id_proveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `table_ventas`
--
ALTER TABLE `table_ventas`
  MODIFY `idVentas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=273;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `table_facturas`
--
ALTER TABLE `table_facturas`
  ADD CONSTRAINT `table_Facturas_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `table_cliente` (`idCliente`);

--
-- Filtros para la tabla `table_ventas`
--
ALTER TABLE `table_ventas`
  ADD CONSTRAINT `table_Ventas_ibfk_1` FOREIGN KEY (`Productos`) REFERENCES `table_productos` (`idProductos`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
