# Auditoria entidades vs base

Fuente de verdad revisada: `src/main/resources/bd/bioquimicos estructura.sql`.

## Cambios ya aplicados

- `Usuario`: `clave` corregida a `varchar(60)`.
- `Localidad`: columnas corregidas a `id`, `id_provincia`, `nombre`.
- `Provincia`: columnas corregidas a `id`, `nombre`; se quito autogeneracion porque la tabla no usa `AUTO_INCREMENT`.
- `Material`: columnas corregidas a `id`, `nombre`; `precio` y `stock` quedaron como `@Transient` porque no existen en la tabla actual.
- `Medico`: id corregido a columna `id`.
- `Metodo`: columnas corregidas a `id`, `nombre`.
- `Nbu`: id corregido a `id`; periodo mapeado a la columna `año` del SQL.
- `Caja`: referencia corregida a `id_expediente`.
- `Orden`: id corregido a `id`; referencias corregidas a `id_usuarios` e `id_pacientes`; `anticipo` quedo `@Transient`; se agrego `idExpediente`.
- `Paciente`: id corregido a `id`; referencia a persona corregida a `id_persona`.
- `Practica`: columnas corregidas a `id`, `codigo`, `determinacion`; `metodo` quedo `@Transient`.
- `ObraSocialTienePracticaNbu`: columnas corregidas a los nombres del SQL.
- `Seccion`, `Titulo`, `Unidad`, `PracticaNbu`, `TipoResultado`: ids corregidos a columna `id`.
- `Analisis`: nombres snake_case explicitos para columnas que estaban dependiendo de convencion.
- `Anticipo`: columnas actuales agregadas (`id_orden`, `estado`, `fecha`, `observacion`); referencia vieja a historia clinica quedo `@Transient`.
- `Patologia`: `fechaInicio` corregida a `fecha_inicio`.
- Entidades agregadas: `ConfiguracionReporte`, `ConfiguracionReporteLayout`, `Expediente`.

## Entidades que aun requieren decision

Estas entidades apuntan a tablas que no estan en el SQL actual:

- `Arancel` -> `aranceles`
- `HistoriaClinica` -> `historia_clinica`
- `Informe` -> `informes`
- `Licencia` -> `licencia`
- `Reporte` -> `reportes`

Con `ddl-auto: update`, Hibernate puede intentar crear esas tablas aunque no pertenezcan a la base real. Antes de pasar a validacion estricta conviene moverlas a legado, excluirlas del `EntityManagerFactory`, o confirmar que faltan en el script SQL.

## Pendiente de segunda pasada

- Revisar relaciones compuestas (`ordenes_tienen_practicas`, `practicas_tienen_materiales`, `pacientes_tienen_obrasociales`) contra claves primarias del SQL.
- Revisar tipos Java para `tinyint(1)`: hoy hay mezcla de `Integer`, `boolean` y `Boolean`.
- Revisar columnas `date` que aun esten como `String`.
- Revisar longitudes `varchar` que quedaron por convencion o por codigo heredado.
- Evaluar cambiar `spring.local.datasource.jpa.hibernate.ddl-auto` de `update` a `validate` cuando las entidades esten alineadas.
