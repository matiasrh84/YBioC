CREATE DATABASE IF NOT EXISTS bioquimicos;

USE bioquimicos;
-- bioquimicos.configuracion_reporte definition

CREATE TABLE `configuracion_reporte` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `observacion2` varchar(255) DEFAULT NULL,
  `logo` blob DEFAULT NULL,
  `formato` varchar(20) DEFAULT NULL,
  `orientacion` varchar(20) DEFAULT NULL,
  `diseno` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.derivaciones definition

CREATE TABLE `derivaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) DEFAULT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  `telefono` bigint(20) unsigned DEFAULT NULL,
  `mail` varchar(150) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.especialidades definition

CREATE TABLE `especialidades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.laboratorios definition

CREATE TABLE `laboratorios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `telefono` bigint(20) DEFAULT NULL,
  `lugar` varchar(100) DEFAULT NULL,
  `cuit` varchar(20) NOT NULL,
  `matricula` int(11) NOT NULL,
  `celular` bigint(20) DEFAULT NULL,
  `dni` int(11) DEFAULT NULL,
  `usuario` varchar(11) NOT NULL,
  `clave` varchar(20) NOT NULL,
  `mail_direccion` varchar(255) DEFAULT NULL,
  `mail_clave` varchar(255) DEFAULT NULL,
  `id_colegiado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.materiales definition

CREATE TABLE `materiales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_materiales_nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.medicos definition

CREATE TABLE `medicos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `mail` varchar(100) DEFAULT NULL,
  `telefono` bigint(20) DEFAULT NULL,
  `matricula` int(5) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1281 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.metodos definition

CREATE TABLE `metodos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.nbu definition

CREATE TABLE `nbu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `año` int(5) DEFAULT 2012,
  `detalle` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.provincia definition

CREATE TABLE `provincia` (
  `id` int(2) NOT NULL DEFAULT 0,
  `nombre` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.secciones definition

CREATE TABLE `secciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `prioridad` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.titulos definition

CREATE TABLE `titulos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL DEFAULT '',
  `estado` tinyint(1) NOT NULL DEFAULT 1,
  `prioridad` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=442 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.unidades definition

CREATE TABLE `unidades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.usuarios definition

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(25) DEFAULT NULL,
  `apellido` varchar(25) DEFAULT NULL,
  `usuario` varchar(15) DEFAULT NULL,
  `clave` varchar(60) DEFAULT NULL,
  `datos` tinyint(1) NOT NULL DEFAULT 0,
  `cbt` tinyint(1) NOT NULL DEFAULT 0,
  `informes` tinyint(1) NOT NULL DEFAULT 0,
  `facturacion` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.configuracion_reporte_layout definition

CREATE TABLE `configuracion_reporte_layout` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_laboratorio` int(11) NOT NULL,
  `elemento` varchar(30) NOT NULL,
  `instancia` int(11) NOT NULL,
  `col` int(11) DEFAULT NULL,
  `fila` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_layout` (`id_laboratorio`,`elemento`,`instancia`),
  CONSTRAINT `fk_layout_config_reporte` FOREIGN KEY (`id_laboratorio`) REFERENCES `configuracion_reporte` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.localidad definition

CREATE TABLE `localidad` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `id_provincia` int(2) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_localidad_provincia` (`id_provincia`),
  CONSTRAINT `fk_localidad_provincia1` FOREIGN KEY (`id_provincia`) REFERENCES `provincia` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2388 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.medicos_tienen_especialidades definition

CREATE TABLE `medicos_tienen_especialidades` (
  `id_medicos` int(11) NOT NULL,
  `id_especialidades` int(11) NOT NULL DEFAULT 0,
  `matricula` int(5) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_medicos`,`id_especialidades`),
  KEY `idx_medicos_tienen_especialidades` (`id_especialidades`),
  CONSTRAINT `rel_esp` FOREIGN KEY (`id_especialidades`) REFERENCES `especialidades` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rel_medicos` FOREIGN KEY (`id_medicos`) REFERENCES `medicos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.obrasocial definition

CREATE TABLE `obrasocial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `int_codigo` varchar(50) DEFAULT NULL,
  `celular_referente` varchar(45) DEFAULT NULL,
  `importe_unidad_de_arancel` decimal(10,2) DEFAULT NULL,
  `razon_social` varchar(45) DEFAULT NULL,
  `importe_unidad_de_gasto` decimal(10,2) DEFAULT NULL,
  `codigo_facturacion` varchar(45) DEFAULT NULL,
  `codigo_fac_liq` varchar(45) DEFAULT NULL,
  `codigo` varchar(45) DEFAULT NULL,
  `cuit` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `factura_alta_complejidad` tinyint(1) NOT NULL DEFAULT 0,
  `factura_no_nomenclados` tinyint(1) NOT NULL DEFAULT 0,
  `factura_por` varchar(45) DEFAULT NULL,
  `factura_por_paciente` tinyint(1) NOT NULL DEFAULT 0,
  `fax` varchar(45) DEFAULT NULL,
  `fecha_de_alta` date DEFAULT NULL,
  `fecha_de_baja` date DEFAULT NULL,
  `web` varchar(45) DEFAULT NULL,
  `imprime_doble_informe` varchar(45) DEFAULT NULL,
  `d998` varchar(45) DEFAULT NULL,
  `mail1` varchar(45) DEFAULT NULL,
  `mail2` varchar(45) DEFAULT NULL,
  `mail3` varchar(45) DEFAULT NULL,
  `nombre_referente` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `numero_resolucion_ingreso` varchar(45) DEFAULT NULL,
  `n_tabla_aranceles` varchar(45) DEFAULT NULL,
  `porcentaje_afiliado` varchar(45) DEFAULT NULL,
  `porcentaje_descuento` varchar(45) DEFAULT NULL,
  `subtotal_por_paciente` tinyint(1) NOT NULL DEFAULT 0,
  `telefono` varchar(45) DEFAULT NULL,
  `tiene_categorizacion` tinyint(1) NOT NULL DEFAULT 0,
  `tipo_de_facturacion` varchar(45) DEFAULT NULL,
  `tipo_de_facturacion_directa_o_colegio` varchar(45) DEFAULT NULL,
  `tipo_iva` varchar(45) DEFAULT NULL,
  `periodo_nbu` varchar(4) DEFAULT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 0,
  `id_localidad` int(4) NOT NULL DEFAULT 2358,
  `id_provincia` int(2) NOT NULL DEFAULT 25,
  PRIMARY KEY (`id`),
  KEY `idx_localidad` (`id_localidad`),
  KEY `idx_provincia` (`id_provincia`),
  CONSTRAINT `rel_localidad` FOREIGN KEY (`id_localidad`) REFERENCES `localidad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rel_provincia` FOREIGN KEY (`id_provincia`) REFERENCES `provincia` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.personas definition

CREATE TABLE `personas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dni` int(11) NOT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `domicilio` varchar(45) DEFAULT NULL,
  `sexo` varchar(1) DEFAULT NULL,
  `id_localidad` int(4) NOT NULL DEFAULT 2358,
  `id_provincia` int(2) NOT NULL DEFAULT 25,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_personas_dni` (`dni`),
  KEY `rel_personas_provincia` (`id_provincia`),
  KEY `rel_personas_localidad` (`id_localidad`),
  CONSTRAINT `rel_personas_localidad` FOREIGN KEY (`id_localidad`) REFERENCES `localidad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rel_personas_provincia` FOREIGN KEY (`id_provincia`) REFERENCES `provincia` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1079119 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.practicas definition

CREATE TABLE `practicas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` int(11) NOT NULL DEFAULT 0,
  `determinacion` varchar(255) DEFAULT '',
  `instrucciones` varchar(255) DEFAULT NULL,
  `id_derivaciones` int(11) DEFAULT NULL,
  `precio1` decimal(10,2) NOT NULL DEFAULT 0.00,
  `precio2` decimal(10,2) NOT NULL DEFAULT 0.00,
  `precio3` decimal(10,2) NOT NULL DEFAULT 0.00,
  `precio4` decimal(10,2) NOT NULL DEFAULT 0.00,
  `tiempo_procesamiento` int(2) NOT NULL DEFAULT 1,
  `estado_deriva` tinyint(1) NOT NULL DEFAULT 0,
  `id_seccion` int(11) DEFAULT NULL,
  `prioridad` int(11) DEFAULT NULL,
  `tipo_informe` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `Practicas_tiene_secciones` (`id_seccion`),
  KEY `practicas_derivaciones_FK` (`id_derivaciones`),
  CONSTRAINT `Practicas_tiene_secciones` FOREIGN KEY (`id_seccion`) REFERENCES `secciones` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `practicas_derivaciones_FK` FOREIGN KEY (`id_derivaciones`) REFERENCES `derivaciones` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1660 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.practicas_nbu definition

CREATE TABLE `practicas_nbu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_practicas` int(11) NOT NULL,
  `id_nbu` int(11) NOT NULL,
  `unidadbioquimica_practica` decimal(10,2) NOT NULL DEFAULT 0.00,
  `fercuencia_practica` varchar(4) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `relacion_practicas` (`id_practicas`),
  KEY `relacion_nbu` (`id_nbu`),
  CONSTRAINT `relacion_nbu` FOREIGN KEY (`id_nbu`) REFERENCES `nbu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `relacion_practicas` FOREIGN KEY (`id_practicas`) REFERENCES `practicas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7458 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.practicas_tienen_materiales definition

CREATE TABLE `practicas_tienen_materiales` (
  `id_practicas` int(11) NOT NULL DEFAULT 0,
  `id_materiales` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_materiales`,`id_practicas`),
  KEY `fk_analisis_has_materiales_materiales1_idx` (`id_materiales`),
  KEY `fk_practicastienemateriales` (`id_practicas`),
  CONSTRAINT `fk_analisis_has_materiales_materiales1` FOREIGN KEY (`id_materiales`) REFERENCES `materiales` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_practicastienemateriales` FOREIGN KEY (`id_practicas`) REFERENCES `practicas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.analisis definition

CREATE TABLE `analisis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `valores_referencia` varchar(500) DEFAULT NULL,
  `unidad` varchar(100) DEFAULT NULL,
  `id_practica` int(11) NOT NULL,
  `codigo_interno` varchar(15) NOT NULL DEFAULT '',
  `estado_titulo` tinyint(1) NOT NULL DEFAULT 0,
  `id_titulo` int(11) DEFAULT NULL,
  `tipo_resultado` varchar(25) NOT NULL DEFAULT '',
  `unidad_extra` varchar(100) DEFAULT NULL,
  `prioridad` int(2) DEFAULT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1,
  `id_metodo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_analisis_practicas` (`id_practica`),
  KEY `idx_analisis_titulo` (`id_titulo`),
  KEY `idx_id_metodo` (`id_metodo`),
  CONSTRAINT `fk_analisis_practicas` FOREIGN KEY (`id_practica`) REFERENCES `practicas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_analisis_titulo` FOREIGN KEY (`id_titulo`) REFERENCES `titulos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_metodo` FOREIGN KEY (`id_metodo`) REFERENCES `metodos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=701 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.obrasocial_tiene_practicas_nbu definition

CREATE TABLE `obrasocial_tiene_practicas_nbu` (
  `id_obra_social` int(11) NOT NULL,
  `id_practicas_nbu` int(11) NOT NULL DEFAULT 0,
  `codigo_fac_practicas_obra_social` int(6) DEFAULT NULL,
  `unidad_bioquimica` decimal(10,2) DEFAULT 0.00,
  `importe_unidad_de_arancel_obra_social` varchar(5) DEFAULT NULL,
  `precio_fijo` decimal(10,2) NOT NULL DEFAULT 0.00,
  `precio_total` decimal(10,2) NOT NULL DEFAULT 0.00,
  `id_nbu` int(11) NOT NULL,
  `id_usuarios` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_obra_social`,`id_practicas_nbu`,`id_nbu`),
  KEY `idx_usuario` (`id_usuarios`),
  KEY `idx_practicas_nbu` (`id_practicas_nbu`),
  CONSTRAINT `rel_obrasocial` FOREIGN KEY (`id_obra_social`) REFERENCES `obrasocial` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rel_usuario` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `relacion_practicasnbu` FOREIGN KEY (`id_practicas_nbu`) REFERENCES `practicas_nbu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.pacientes definition

CREATE TABLE `pacientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `telefono` varchar(20) DEFAULT '0',
  `celular` varchar(20) DEFAULT '0',
  `mail` varchar(45) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT '1900-01-01',
  `estado` tinyint(1) NOT NULL DEFAULT 1,
  `id_persona` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pacientes_personas` (`id_persona`),
  CONSTRAINT `fk_pacientes_personas` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=440 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.pacientes_tienen_obrasociales definition

CREATE TABLE `pacientes_tienen_obrasociales` (
  `id_pacientes` int(11) NOT NULL,
  `id_obrasocial` int(11) NOT NULL,
  `numero_afiliado` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_pacientes`,`id_obrasocial`),
  KEY `rel_obrasocial_obrasocial_pacientes` (`id_obrasocial`),
  CONSTRAINT `rel_obrasocial_obrasocial_pacientes` FOREIGN KEY (`id_obrasocial`) REFERENCES `obrasocial` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rel_pacientes_obrasocial_pacientes` FOREIGN KEY (`id_pacientes`) REFERENCES `pacientes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.patologias definition

CREATE TABLE `patologias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL DEFAULT '',
  `id_paciente` int(11) NOT NULL DEFAULT 0,
  `descripcion` text DEFAULT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `paciente_tiene_patologia` (`id_paciente`),
  CONSTRAINT `paciente_tiene_patologia` FOREIGN KEY (`id_paciente`) REFERENCES `pacientes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.tipo_resultados definition

CREATE TABLE `tipo_resultados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(500) NOT NULL DEFAULT '',
  `estado` tinyint(1) NOT NULL DEFAULT 1,
  `id_analisis` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tipores_analisis` (`id_analisis`),
  CONSTRAINT `fk_tipores_analisis` FOREIGN KEY (`id_analisis`) REFERENCES `analisis` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.ordenes definition

CREATE TABLE `ordenes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `periodo` int(11) NOT NULL,
  `numero_orden` varchar(20) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `estado_orden` tinyint(1) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `id_usuarios` int(11) NOT NULL,
  `id_medicos` int(11) NOT NULL,
  `id_especialidades` int(11) NOT NULL,
  `id_pacientes` int(11) NOT NULL,
  `servicio` varchar(45) DEFAULT NULL,
  `cama` int(5) DEFAULT NULL,
  `tipo_orden` varchar(45) DEFAULT NULL,
  `nro_de_autorizacion` bigint(20) DEFAULT NULL,
  `fecha_de_autorizacion` datetime DEFAULT NULL,
  `fecha_de_coseguro` date DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `id_obrasocial` int(11) NOT NULL DEFAULT 0,
  `precio_coseguro` decimal(10,2) DEFAULT NULL,
  `nombre_recien_nacido` varchar(100) DEFAULT NULL,
  `estado_enviado` tinyint(1) NOT NULL DEFAULT 0,
  `hora` time DEFAULT NULL,
  `id_expediente` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_medico_ordenes` (`id_medicos`),
  KEY `idx_especialidades_ordenes` (`id_especialidades`),
  KEY `idx_paciente_ordenes` (`id_pacientes`),
  KEY `idx_os_ordenes` (`id_obrasocial`),
  KEY `idx_usuario_ordenes` (`id_usuarios`),
  CONSTRAINT `rel_especialidades_ordenes` FOREIGN KEY (`id_especialidades`) REFERENCES `especialidades` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rel_medico_ordenes` FOREIGN KEY (`id_medicos`) REFERENCES `medicos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rel_os_ordenes` FOREIGN KEY (`id_obrasocial`) REFERENCES `obrasocial` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rel_paciente_ordenes` FOREIGN KEY (`id_pacientes`) REFERENCES `pacientes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rel_usuario_ordenes` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.ordenes_tienen_practicas definition

CREATE TABLE `ordenes_tienen_practicas` (
  `id_practicas` int(11) NOT NULL,
  `id_ordenes` int(11) NOT NULL,
  `precio_practica` decimal(10,2) NOT NULL DEFAULT 0.00,
  `cod_practica_fac` varchar(6) NOT NULL DEFAULT '',
  `factura` tinyint(1) NOT NULL DEFAULT 0,
  `estado` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_practicas`,`id_ordenes`),
  KEY `idx_ordenes_tienen_practicas` (`id_ordenes`),
  CONSTRAINT `fk_otp_ordenes` FOREIGN KEY (`id_ordenes`) REFERENCES `ordenes` (`id`),
  CONSTRAINT `fk_otp_practicas` FOREIGN KEY (`id_practicas`) REFERENCES `practicas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.resultados definition

CREATE TABLE `resultados` (
  `id_analisis` int(11) NOT NULL,
  `id_practicas` int(11) NOT NULL,
  `id_ordenes` int(11) NOT NULL,
  `id_usuarios` int(11) NOT NULL,
  `resultado` varchar(500) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `estado_imprime` tinyint(1) DEFAULT 0,
  `imprimir_nombre` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_analisis`,`id_practicas`,`id_ordenes`),
  KEY `fk_res_practicas` (`id_practicas`),
  KEY `fk_res_ordenes` (`id_ordenes`),
  KEY `fk_res_usuarios` (`id_usuarios`),
  CONSTRAINT `fk_res_analisis` FOREIGN KEY (`id_analisis`) REFERENCES `analisis` (`id`),
  CONSTRAINT `fk_res_ordenes` FOREIGN KEY (`id_ordenes`) REFERENCES `ordenes` (`id`),
  CONSTRAINT `fk_res_practicas` FOREIGN KEY (`id_practicas`) REFERENCES `practicas` (`id`),
  CONSTRAINT `fk_res_usuarios` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.anticipo definition

CREATE TABLE `anticipo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `anticipo` decimal(10,2) DEFAULT NULL,
  `id_orden` int(11) NOT NULL,
  `estado` varchar(20) NOT NULL DEFAULT 'retenido',
  `fecha` date DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_anticipo_orden` (`id_orden`),
  CONSTRAINT `fk_anticipo_orden` FOREIGN KEY (`id_orden`) REFERENCES `ordenes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.expediente definition

CREATE TABLE `expediente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_entrega` date DEFAULT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 0,
  `id_paciente` int(11) NOT NULL,
  `id_orden` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_historia_clinica_paciente` (`id_paciente`),
  KEY `fk_expediente_orden` (`id_orden`),
  CONSTRAINT `fk_expediente_orden` FOREIGN KEY (`id_orden`) REFERENCES `ordenes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_hc_paciente` FOREIGN KEY (`id_paciente`) REFERENCES `pacientes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- bioquimicos.caja definition

CREATE TABLE `caja` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `monto` decimal(10,2) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `id_expediente` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_caja_historia_clinica` (`id_expediente`),
  CONSTRAINT `caja_expediente_fk` FOREIGN KEY (`id_expediente`) REFERENCES `expediente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;