# Plan de trabajo — Migración YBioC

Documento guía para ordenar la migración del sistema viejo (`ybgestor-cobiar`, Java/Swing, funcional) hacia `YBioC` (backend Spring Boot + frontend JavaFX desacoplados).

Acompaña a:
- `docs/MATRIZ_MIGRACION.md` — tablero vivo de estado por dominio.
- `docs/ESTRUCTURA_MIGRACION.md` — convenciones de módulos y nombres.
- `docs/AUDITORIA_ENTIDADES_BD.md` — estado de entidades vs SQL.

Última actualización: 2026-05-28.

## 1. Objetivo

Completar `YBioC` tomando `ybgestor-cobiar` como **fuente de verdad funcional**, migrando la lógica de negocio real al backend y reconstruyendo la UI en JavaFX, hasta poder retirar el sistema viejo.

## 2. Estrategia y principios

- **Frontend-first**: la pantalla JavaFX (guiada por el form Swing viejo) define qué endpoints y lógica hacen falta. El frontend marca la demanda.
- **El viejo manda en lo funcional; el SQL manda en el modelo de datos.** (`backend/src/main/resources/bd/bioquimicos estructura.sql`).
- **El "service listo" del backend es un mito**: hoy son andamiajes CRUD. La lógica de negocio real (cálculos, validaciones, flujos, queries/vistas) está en el viejo y hay que extraerla dominio por dominio.
- **Un dominio activo a la vez** (regla de WIP): terminar de punta a punta antes de pasar al siguiente, salvo que una dependencia obligue.
- **No se codea un dominio hasta tener hechas las etapas 1 (Relevar) y 2 (Diseñar).** Eso es lo que elimina la improvisación.

## 3. Arquitectura objetivo

Dos procesos, ya en ejecución:
- **backend** `ybioq-backend` — web server en `:8080`, MariaDB local `bioquimicos` (`localhost:3306`) + remota `colegiobioquimicos`. Expone REST bajo `/api/**`.
- **frontend** `ybioq-frontend` — Spring Boot `web-application-type: none` que lanza JavaFX y consume el backend por HTTP (`backend.api.base-url`).

La UI no toca repositorios ni la BD directamente: todo pasa por REST.

## 4. Pipeline por dominio (definición de terminado)

Cada dominio recorre estas 7 etapas, en orden:

1. **RELEVAR** (del viejo) — leer el/los form Swing + clases asociadas en `ybgestor-cobiar`; documentar reglas, validaciones, cálculos, dependencias.
   - Sub-paso **vistas SQL**: si el método llama a una vista, registrar su nombre y SQL en el inventario de vistas (sección 8). No arrastrar vistas a ciegas.
2. **DISEÑAR** — decidir qué lógica se conserva / rediseña / descarta; definir el contrato: método(s) de service, endpoint REST y DTOs. Para cada vista relevada: recuperar / replicar en service / descartar.
3. **SERVICE** — implementar la lógica de negocio **real** en el service (no solo CRUD).
4. **REST** — `XApiController` + DTOs en `api/` (patrón espejo de `MedicoApiController`).
5. **FX** — client + controller + FXML, incluyendo layout responsivo.
6. **VALIDAR** — correr backend + frontend; comparar comportamiento contra el viejo (validación funcional; ver §5).
7. **CERRAR** — actualizar la matriz; marcar el form Swing viejo como migrado (no borrar hasta validado).

## 5. Reglas globales de diseño

- **Tablas grandes → nunca `findAll`.** `personas` tiene +1.000.000 de registros. Toda búsqueda sobre tablas grandes (personas y probablemente ordenes/resultados) usa filtros server-side + paginación. (El `PersonaClient.findAll()` actual es un bug a corregir en la Ola 3.)
- **Validación funcional, no por datos.** Los datos locales pueden estar incompletos: validar misma lógica/salida con mismos inputs, sin asumir data completa.
- **Vistas SQL caso por caso.** Muchas son obsoletas o mal diseñadas para las exigencias de hoy; preferir replicar la lógica en el service cuando convenga.
- **No migrar infraestructura DB del viejo**: `ConexionMySQL`, `ConexionMySQLLocal`, `HiloConectar` y afines. Spring ya gestiona los datasources.
- **No borrar forms Swing** del viejo (ni del backend `view/`) hasta que el equivalente JavaFX esté validado.
- **Nombres de pantalla**: por dominio, en plural, sin prefijos de acción (ver `ESTRUCTURA_MIGRACION.md`).

## 6. Secuencia de olas (por dependencias)

- **Ola 0 · Piloto — Métodos.** Recorrer las 7 etapas completas para fijar el patrón (relevamiento, service, REST, layout FX estándar). Plantilla de todo lo demás.
- **Ola 1 · Datos de referencia**: Especialidades, Títulos, Unidades, Secciones, Tipo Resultado, Métodos, Materiales, Provincias/Localidades, Médicos, Usuarios.
- **Ola 2 · Catálogo clínico y precios**: Obras Sociales, Prácticas, NBU, Práctica-NBU, precios/coseguros, Aranceles.
- **Ola 3 · Personas**: Personas → Pacientes, Paciente-ObraSocial, Patologías, Historia Clínica. (Acá se arregla la paginación de personas.)
- **Ola 4 · Núcleo operativo**: Órdenes (cargar/modificar/próxima), Análisis, Resultados, Derivaciones, Anticipos/Señas, Caja, Observaciones, Protocolos.
- **Ola 5 · Salida financiera**: Facturación, Períodos, Histórico, Export.
- **Ola 6 · Reportes Jasper** (transversal; depende de datos de olas 2–5).
- **Ola 7 · Integraciones online** (OSDE, Sancor, Boreal, etc.) + mail. (Funcionan en el viejo; se dejan para el final.)
- **Ola 8 · Config + limpieza**: Configuración/Laboratorio, Licencia, Plantillas, Fórmulas; borrar `view/` Swing del backend, podar `config/`, hardening de seguridad.

## 7. Piloto — Métodos (Ola 0)

Estado actual: 🟠 ROTO. Existen `MetodosFxController`, `metodos-view.fxml`, `MetodoClient` (→ `/metodos`), `MetodoDto`, entidad `Metodo`, `MetodoRepository`, `MetodoService` (CRUD). Falta el REST.

Desglose por etapa:
1. **RELEVAR**: leer `Formularios/Metodos.java` (+ `Clases/Metodo.java`, `ListaMetodo.java`) en el viejo. Documentar campos, validaciones, si usa alguna vista.
2. **DISEÑAR**: contrato del endpoint (`GET /api/metodos`, `POST /api/metodos`, y `DELETE` si aplica) y forma del DTO.
3. **SERVICE**: completar `MetodoService` con la lógica que aporte el relevamiento (probablemente mínima en este dominio).
4. **REST**: crear `MetodoApiController` en `api/`.
5. **FX**: verificar `MetodoClient`/`MetodosFxController` y arreglar `metodos-view.fxml` (layout/botones).
6. **VALIDAR**: correr ambos procesos y probar alta/listado/baja contra el comportamiento del viejo.
7. **CERRAR**: actualizar matriz; documentar el patrón resultante como plantilla.

## 8. Seguimiento (tablero vivo)

- La **matriz** (`MATRIZ_MIGRACION.md`) es el tablero: cada dominio lleva checkboxes por etapa (Relevado / Diseñado / Service / REST / FX / Validado).
- **Inventario de vistas SQL**: a medida que aparezcan en el relevamiento, registrar nombre, SQL y decisión (recuperar/replicar/descartar). (Se irá agregando como sección en la matriz o en un doc propio.)
- **Lista NO-MIGRAR**: clases del viejo descartadas explícitamente (arranca con `ConexionMySQL`, `ConexionMySQLLocal`, `HiloConectar`).

## 9. Decisiones registradas

- Piloto = Métodos. Sin urgencias de negocio: se sigue el orden por dependencias.
- Reportes Jasper e integraciones online → al final (Olas 6 y 7).
- BD local en `localhost:3306` (datos posiblemente incompletos).

## 10. Deuda transversal / banderas rojas

- 🔐 Password de la BD remota commiteada en `backend/src/main/resources/application.yaml` → mover a variables de entorno y rotar.
- 🔓 Seguridad abierta (`permitAll` total) y sin token/sesión FE↔BE tras el login.
- ⚠️ `ddl-auto: update` en ambas BD → evaluar pasar a `validate` cuando las entidades estén alineadas.

(Estas se abordan en la Ola 8, salvo que algo las vuelva urgentes antes.)
