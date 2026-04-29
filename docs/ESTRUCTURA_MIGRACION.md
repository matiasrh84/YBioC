# Estructura de migracion

La migracion a JavaFX se organiza sin borrar ni mover los formularios Swing viejos. Esos archivos siguen siendo la fuente para recuperar reglas de pantalla, validaciones y flujo de negocio.

## Proyecto nuevo

- `src/main/java/com/ybc/ybioq/fx`
  - Aplicacion JavaFX, navegacion, carga FXML y controladores de pantalla nuevos.
- `src/main/resources/fx`
  - Vistas FXML y estilos CSS del cliente JavaFX.

## Codigo compartido

- `src/main/java/com/ybc/ybioq/entity/local`
  - Entidades JPA de la base local MariaDB `bioquimicos`.
  - La fuente de verdad es `src/main/resources/bd/bioquimicos estructura.sql`.
- `src/main/java/com/ybc/ybioq/repository/local`
  - Repositorios Spring Data JPA.
- `src/main/java/com/ybc/ybioq/service`
  - Logica reutilizable por JavaFX.
- `src/main/java/com/ybc/ybioq/controller`
  - Fachadas actuales usadas por Swing y reutilizables temporalmente desde JavaFX.
  - A medida que avance la migracion, conviene mover la logica real a `service` y dejar estos controladores finos.

## Proyecto viejo conservado

- `src/main/java/com/ybc/ybioq/view`
  - Formularios Swing/NetBeans y sus `.form`.
  - No se eliminan ni se renombran por ahora.
  - Se usan como referencia para reconstruir formularios JavaFX.
- `src/main/java/com/ybc/ybioq/legacy`
  - Entidades y repositorios del modelo viejo que no coinciden con el SQL actual.
  - Compilan, pero no estan dentro de los paquetes escaneados por Spring Data/JPA.
  - Sirven como referencia historica hasta confirmar si se migran, se reemplazan por tablas nuevas o se eliminan mas adelante.
- `src/main/java/com/ybc/ybioq/config`
  - Mezcla de configuracion Spring, utilidades y clases auxiliares usadas por el sistema viejo.
  - Debe separarse gradualmente: configuracion Spring real en `config`, utilidades de negocio en `service` o `utils`, y codigo puramente Swing como legado.

## Regla de trabajo

1. La base SQL manda sobre las entidades.
2. No borrar formularios Swing hasta que el formulario JavaFX equivalente este implementado y validado.
3. Cada pantalla nueva JavaFX debe llamar servicios, no consultar directamente repositorios desde la UI.
4. Los CRUD simples se implementan en servicios pequenos y controladores JavaFX dedicados.
5. Las entidades que no existan en el SQL actual deben tratarse como legado hasta decidir si se eliminan del modelo JPA o si falta su tabla en el script.

## Convencion de nombres para pantallas JavaFX

Las pantallas nuevas se nombran por dominio, en plural, sin prefijos de accion:

- `Especialidades`
- `Medicos`
- `Pacientes`
- `Ordenes`
- `Practicas`

Archivos asociados:

- FXML: `especialidades-view.fxml`, `medicos-view.fxml`
- Controller JavaFX: `EspecialidadesFxController`, `MedicosFxController`
- Controller Spring existente: `EspecialidadController`, `MedicoController`

No crear nuevos formularios con nombres `Alta...`, `Agregar...`, `Tabla...` o similares. Esas palabras quedan como acciones internas de pantalla:

- `Nuevo`
- `Guardar`
- `Baja / Reactivar`
- `Actualizar`

Si una pantalla necesita un dialogo puntual, el dialogo debe nombrarse por su proposito especifico, por ejemplo `SeleccionMedicoDialog`, no `AgregarMedico`.
