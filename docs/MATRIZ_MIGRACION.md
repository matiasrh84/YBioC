# Matriz de migración YBioC

Mapa por dominio: pantalla Swing vieja (fuente de verdad funcional) → pantalla JavaFX → client HTTP → endpoint REST backend → service/entity backend → estado.

Última actualización: 2026-05-29.

## Leyenda de estado

- ✅ **OK** — FX + client + REST + service conectados de punta a punta. Pendiente validación funcional contra el viejo (etapa 6 del pipeline).
- 🟡 **PARCIAL** — alguna pieza falta o no está cableada (p. ej. FX sin client, REST sin FX, lógica de negocio faltante).
- 🔵 **SCAFFOLD-CRUD** — entidad + repo + service existen, pero solo CRUD vacío; falta lógica real + REST + FX.
- ⬜ **NO INICIADO** — existe en el sistema viejo; nada en YBioC todavía.

> ⚠️ ✅ OK **no significa validado contra el viejo**. Significa que el circuito FX → client → REST → service está cableado. La validación funcional (etapa 6 del pipeline) es un paso separado que ningún dominio ha completado aún.

---

## 1. ABM simples (Ola 1)

| Dominio | Form Swing | FX Controller | Client → endpoint | REST backend | Estado |
|---|---|---|---|---|---|
| Login / Auth | Login | LoginFxController ✓ | AuthClient → `/api/auth/login` | ✓ GET+POST | ✅ OK |
| Especialidades | Alta_Especialidad | EspecialidadesFxController ✓ | EspecialidadClient → `/api/especialidades` | ✓ GET+POST | ✅ OK |
| Médicos | Alta_Medico | MedicosFxController ✓ | MedicoClient → `/api/medicos` | ✓ GET+POST | ✅ OK |
| Métodos | Metodos | MetodosFxController ✓ | MetodoClient → `/api/metodos` | ✓ GET+POST | ✅ OK |
| Títulos | Titulo | TitulosFxController ✓ | TituloClient → `/api/titulos` | ✓ GET+POST | ✅ OK |
| Unidades | Alta_Unidad | UnidadesFxController ✓ | UnidadClient → `/api/unidades` | ✓ GET+POST | ✅ OK |
| Derivaciones | Derivaciones | DerivacionesFxController ✓ | DerivacionClient → `/api/derivaciones` | ✓ GET+POST | ✅ OK |
| Secciones | Alta_Seccion | — | — | ❌ falta | 🔵 SCAFFOLD-CRUD |
| Tipo Resultado | Alta_Tipo_Resultado | — | — | ❌ falta | 🔵 SCAFFOLD-CRUD |
| Materiales | Alta_Materiales | — | — | ❌ falta | 🔵 SCAFFOLD-CRUD |
| Provincia / Localidad | (embebido) | — | — | ❌ falta | 🔵 SCAFFOLD-CRUD |

---

## 2. Personas / Pacientes (Ola 3)

| Dominio | Form Swing | FX Controller | Client → endpoint | REST backend | Estado |
|---|---|---|---|---|---|
| Buscar Persona | BuscarPersonas | BuscarPersonaFxController ✓ | PersonaClient → `/api/personas` | ❌ falta | 🟠 ROTO |
| Pacientes | Pacientes | — | — | ❌ falta | 🔵 SCAFFOLD-CRUD |
| Patologías x Paciente | PatologiasPorPaciente | — | — | ❌ falta | 🔵 SCAFFOLD-CRUD |
| Elegir Bioquímico | ElegirBioquimicos | ElegirBioquimicoFxController 🟡 | sin client | ❌ falta | 🟡 PARCIAL |

> ⚠️ `PersonaClient.findAll()` es una bomba — tabla con +1.000.000 de registros. Requiere búsqueda server-side con filtros + paginación obligatoria.

---

## 3. Obras Sociales y Prácticas (Ola 2)

| Dominio | Form Swing | FX Controller | Client → endpoint | REST backend | Estado |
|---|---|---|---|---|---|
| Obras Sociales | AgregarObraSocial, Tabla_ObrasSociales | ObrasSocialesFxController ✓ | ObraSocialClient → `/api/obras-sociales` | ✓ GET+POST | ✅ OK |
| Prácticas | Practicas, Detalle_Practicas | DetallePracticasFxController 🟡 | PracticaClient → `/api/practicas` | ✓ GET (solo lectura) | 🟡 PARCIAL |
| Prácticas NBU | Tabla_PracticasNBU | — | — | ❌ falta | 🔵 SCAFFOLD-CRUD |
| Aranceles | Arancel | — | — | ❌ falta | ⬜ NO INICIADO |

> Obras Sociales: layout corregido 2026-05-29 (`sizeToScene()`). CRUD cableado punta a punta, pendiente validación funcional.
> Prácticas: `PracticaApiController` expone GET /api/practicas y GET /api/practicas/{id}. Falta POST/PUT/DELETE y FX propio.

---

## 4. Análisis (Ola 4 — catálogo clínico)

| Dominio | Form Swing | FX Controller | Client → endpoint | REST backend | Estado |
|---|---|---|---|---|---|
| Análisis | Analisis | AnalisisControllerNewFx ✓ | AnalisisClient → `/api/analisis` | ✓ GET+POST+PUT+DELETE | ✅ OK |

**Implementado y cerrado 2026-05-29:**
- `GET /api/analisis?idPractica={id}`, `POST`, `PUT`, `DELETE`
- Autocomplete para Práctica, Método y Título (`AutocompleteTextField`)
- `guardar()` itera los parámetros y guarda uno por uno; también hace `PUT /api/practicas/{id}` para datos generales
- Secciones cargadas desde BD (`GET /api/secciones`); fallback a hardcodeados si backend no responde
- Fórmula (`txtformula`) guardada en columna `unidad_extra` vía `PrioridadItem.unidadExtra`; se restaura al editar
- `txtNombreInforme` eliminado — el nombre del análisis es un solo campo (`txtNombreParametro` → `nombre`)
- Materiales: no implementado (código comentado en el form viejo — nunca fue funcional; se deja para un módulo futuro)

**Pendiente:**
- Validación funcional profunda contra datos reales (etapa 6 del pipeline)

---

## 5. Operación: Órdenes / Resultados / Caja

| Dominio | Form Swing | FX Controller | Client → endpoint | REST backend | Estado |
|---|---|---|---|---|---|
| Órdenes (Cargar) | Cargar_orden, Proxima_Orden | CargarOrdenFxController ✓ | OrdenClient → `/api/ordenes` | ✓ GET+POST | 🟡 PARCIAL |
| Órdenes (Hub panel) | MainB (panel central) | PrincipalController panelCargarOrden ✓ | OrdenClient → `/api/ordenes/por-numero`, `/api/ordenes/recientes` | ✓ | 🟡 PARCIAL |
| Órdenes (Modificar/Anular) | Modifica_orden | — | — | ❌ falta | ⬜ NO INICIADO |
| Resultados (Cargar) | Resultado, Resultados | CargarResultadosFxController ✓ | ResultadoClient → `/api/resultados` | ✓ GET+PUT | ✅ OK |
| Resultados (Detalle orden) | Detalle_Practicas_ordenes | DetallePracticasOrdenFxController ✓ | OrdenClient + ResultadoClient | ✓ GET | ✅ OK |
| Resultados (Vista simple) | Detalle_Resultados | DetalleResultadosFxController | sin navegación | — | ⬜ NO INICIADO |
| Anticipos / Señas | Señas | AnticipoFxController ✓ | AnticipoClient → `/api/anticipos` | ❌ falta | 🟠 ROTO |
| Caja | Caja | — | — | ❌ falta | 🔵 SCAFFOLD-CRUD |
| Observaciones | Observacion | — | — | ❌ falta | ⬜ NO INICIADO |
| Protocolos | Protocolos | — | — | ❌ falta | ⬜ NO INICIADO |
| Detalle TXT | Detalle_txt | DetalleTxtFxController 🟡 | sin client | ❌ falta | 🟡 PARCIAL |

---

## 6. Facturación e informes (Ola 5)

| Dominio | Form Swing | Estado |
|---|---|---|
| Facturación | Facturacion, Enviar_Facturacion | 🟡 PARCIAL (FX scaffold sin client ni REST) |
| Informes / Reportes Jasper | Informe, Vista_Previa, informe_mesada | ⬜ NO INICIADO (Ola 6) |
| Períodos | Periodo, Periodo2, PeriodoImprime | ⬜ NO INICIADO |
| Histórico / Export | Historico, Exportar | ⬜ NO INICIADO |

---

## 7. Integraciones online y mail (Ola 7)

| Dominio | Estado |
|---|---|
| Validación/Anulación online (OSDE, Sancor, Boreal…) | ⬜ NO INICIADO — se deja para el final |
| Envío de mail | ⬜ NO INICIADO |

---

## 8. Configuración / varios (Ola 8)

| Dominio | Estado |
|---|---|
| Configuración / Laboratorio | ⬜ NO INICIADO |
| Usuarios (ABM) | 🔵 SCAFFOLD-CRUD |
| Licencia | ⬜ NO INICIADO |
| Plantillas / Fórmulas | ⬜ NO INICIADO |
| Acerca de / Ayuda | ⬜ NO INICIADO |

---

## Resumen al 2026-05-29

| Estado | Dominios |
|---|---|
| ✅ OK (cableados, sin validar) | Login, Especialidades, Médicos, **Métodos, Títulos, Unidades, Derivaciones, Obras Sociales, Análisis, Prácticas** |
| 🟡 PARCIAL | Resultados, Facturación, Elegir Bioquímico, Detalle TXT |
| 🟠 ROTO | Buscar Persona, **Anticipos** |
| 🔵 SCAFFOLD-CRUD | Secciones, Tipo Resultado, Materiales, Provincia/Localidad, Pacientes, Patologías, Prácticas NBU, Órdenes, Caja, Usuarios |
| ⬜ NO INICIADO | Aranceles, Observaciones, Protocolos, Informes/Reportes, Períodos, Histórico, Integraciones online, Mail, Config, Licencia, Plantillas |

## Próximos pasos sugeridos

1. **Cerrar Análisis**: cargar Secciones desde BD (`GET /api/secciones`) + validar contra el form Swing viejo.
2. **Anticipos** (🟠 ROTO): crear `AnticipoApiController` — patrón idéntico a Métodos/Títulos.
3. **Buscar Persona** (🟠 ROTO): requiere paginación server-side obligatoria (tabla +1M registros).
4. **Validar los ✅ OK**: recorrer cada dominio cableado y comparar comportamiento contra el viejo antes de darlo por cerrado.
