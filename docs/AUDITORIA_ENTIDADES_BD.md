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

## Entidades movidas a legado

Estas entidades apuntan a tablas que no estan en el SQL actual. Se movieron a `com.ybc.ybioq.legacy` para que compilen, pero no sean escaneadas por el `EntityManagerFactory` local:

- `Arancel` -> `aranceles`
- `HistoriaClinica` -> `historia_clinica`
- `Informe` -> `informes`
- `Licencia` -> `licencia`
- `Reporte` -> `reportes`

Con `ddl-auto: update`, si estas entidades estuvieran en el paquete activo Hibernate podria intentar crear tablas que no pertenecen a la base real.

## Cambios de segunda pasada

- `PacienteTieneObraSocial`: columnas corregidas a `id_pacientes` e `id_obrasocial`.
- `ResultadoId`: se quitaron de la clave embebida los campos viejos `id_medicos`, `id_especialidades` e `id_Pacientes`; la clave actual queda en `id_analisis`, `id_practicas`, `id_ordenes`, con `id_usuarios` como columna adicional.
- `Orden`: `total` y `precio_coseguro` pasaron a `BigDecimal`; `fecha` y `fecha_de_autorizacion` pasaron a `LocalDateTime`; `hora` paso a `LocalTime`.
- `OrdenTienePractica`: `precio_practica` paso a `BigDecimal`.
- `Practica`: precios `precio1..precio4` pasaron a `BigDecimal`.
- `Nbu`: se agrego `detalle`.
- `Analisis`, `Anticipo`, `Caja`, `Derivacion`, `Patologia` y `Persona`: se agregaron anotaciones `@Column` explicitas para evitar depender de convenciones.

## Pendiente de segunda pasada

- Revisar relaciones compuestas (`ordenes_tienen_practicas`, `practicas_tienen_materiales`, `pacientes_tienen_obrasociales`) contra claves primarias del SQL.
- Revisar tipos Java para `tinyint(1)`: hoy hay mezcla de `Integer`, `boolean` y `Boolean`.
- Revisar columnas `date` que aun esten como `String`.
- Revisar longitudes `varchar` que quedaron por convencion o por codigo heredado.
- Evaluar cambiar `spring.local.datasource.jpa.hibernate.ddl-auto` de `update` a `validate` cuando las entidades esten alineadas.
