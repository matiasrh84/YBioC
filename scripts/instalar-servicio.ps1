#Requires -RunAsAdministrator
<#
.SYNOPSIS
    Instala el backend de YBioC como servicio de Windows usando NSSM.

.DESCRIPCION
    Ejecutar UNA SOLA VEZ. Para actualizar el backend, usar deploy-backend.ps1.

    Requisitos previos:
      1. Descargar NSSM desde https://nssm.cc/download
         Extraer nssm.exe (la version de 64 bits) a la ruta indicada en $NSSM_EXE
      2. Tener Java 21+ en el PATH (verificar con: java -version)
      3. Hacer al menos un deploy antes de iniciar el servicio:
         .\deploy-backend.ps1 -PrimerDeploy

.EJEMPLO
    .\instalar-servicio.ps1
#>

$ErrorActionPreference = "Stop"

# ── Configuracion ─────────────────────────────────────────────────────────────
$SERVICE_NAME = "YBioCBackend"
$SERVICE_DESC = "Backend Spring Boot del sistema de gestion bioquimica YBioC"
$REPO_ROOT    = "C:\Repositorio_git\cobiar"
$DEPLOY_DIR   = "$REPO_ROOT\YBioC\deploy"
$DEPLOY_JAR   = "$DEPLOY_DIR\backend.jar"
$LOG_DIR      = "$DEPLOY_DIR\logs"
$NSSM_EXE     = "C:\tools\nssm\nssm.exe"     # <-- ajustar si nssm.exe esta en otro lugar
$JAVA_HOME_21 = "C:\Program Files\Java\jdk-21.0.7"
$JAVA_EXE     = "$JAVA_HOME_21\bin\java.exe"
$JAVA_OPTS    = "-Xms256m -Xmx768m -Dfile.encoding=UTF-8"
# ──────────────────────────────────────────────────────────────────────────────

function Write-Step([string]$msg) {
    Write-Host "`n>> $msg" -ForegroundColor Cyan
}

# Verificar NSSM
Write-Step "Verificando NSSM..."
if (-not (Test-Path $NSSM_EXE)) {
    Write-Host @"

ERROR: No se encontro nssm.exe en '$NSSM_EXE'.

Pasos para instalar NSSM:
  1. Ir a https://nssm.cc/download
  2. Descargar el ZIP (version 2.24 o superior)
  3. Extraer nssm.exe (carpeta win64) a C:\tools\nssm\
  4. Volver a ejecutar este script

"@ -ForegroundColor Red
    exit 1
}
Write-Host "   NSSM encontrado: $NSSM_EXE" -ForegroundColor Green

# Verificar Java 21
Write-Step "Verificando Java 21..."
if (-not (Test-Path $JAVA_EXE)) {
    Write-Host "ERROR: No se encontro Java 21 en '$JAVA_EXE'." -ForegroundColor Red
    Write-Host "       Ajustar la variable `$JAVA_HOME_21 en este script." -ForegroundColor Red
    exit 1
}
$javaVersion = & $JAVA_EXE -version 2>&1 | Select-Object -First 1
Write-Host "   $javaVersion" -ForegroundColor Green

# Crear directorios
Write-Step "Creando directorios..."
if (-not (Test-Path $DEPLOY_DIR)) { New-Item -ItemType Directory -Path $DEPLOY_DIR | Out-Null }
if (-not (Test-Path $LOG_DIR))    { New-Item -ItemType Directory -Path $LOG_DIR    | Out-Null }
Write-Host "   $DEPLOY_DIR" -ForegroundColor Green
Write-Host "   $LOG_DIR"    -ForegroundColor Green

# Eliminar servicio anterior si existe
$existing = Get-Service -Name $SERVICE_NAME -ErrorAction SilentlyContinue
if ($existing) {
    Write-Step "Eliminando servicio anterior '$SERVICE_NAME'..."
    if ($existing.Status -eq "Running") {
        & $NSSM_EXE stop $SERVICE_NAME | Out-Null
        Start-Sleep -Seconds 3
    }
    & $NSSM_EXE remove $SERVICE_NAME confirm | Out-Null
    Start-Sleep -Seconds 2
    Write-Host "   Servicio anterior eliminado." -ForegroundColor Yellow
}

# Instalar servicio
Write-Step "Instalando servicio '$SERVICE_NAME'..."
$appArgs = "$JAVA_OPTS -jar `"$DEPLOY_JAR`""
& $NSSM_EXE install       $SERVICE_NAME $JAVA_EXE $appArgs
& $NSSM_EXE set           $SERVICE_NAME AppDirectory    $DEPLOY_DIR
& $NSSM_EXE set           $SERVICE_NAME DisplayName     $SERVICE_NAME
& $NSSM_EXE set           $SERVICE_NAME Description     $SERVICE_DESC
& $NSSM_EXE set           $SERVICE_NAME Start           SERVICE_AUTO_START
& $NSSM_EXE set           $SERVICE_NAME AppStdout       "$LOG_DIR\backend.log"
& $NSSM_EXE set           $SERVICE_NAME AppStderr       "$LOG_DIR\backend-error.log"
& $NSSM_EXE set           $SERVICE_NAME AppRotateFiles  1
& $NSSM_EXE set           $SERVICE_NAME AppRotateBytes  10485760   # 10 MB por archivo
& $NSSM_EXE set           $SERVICE_NAME AppRotateOnline 1

Write-Host @"

╔══════════════════════════════════════════════════════════════╗
║  Servicio '$SERVICE_NAME' instalado correctamente.
║
║  Proximos pasos:
║    1. Hacer el primer deploy:
║       .\deploy-backend.ps1
║
║    2. Verificar que arranco:
║       Get-Service -Name '$SERVICE_NAME'
║       Get-Content '$LOG_DIR\backend.log' -Tail 30
║
║  Para administrar el servicio:
║    Iniciar : Start-Service -Name '$SERVICE_NAME'
║    Detener : Stop-Service  -Name '$SERVICE_NAME' -Force
║    Estado  : Get-Service   -Name '$SERVICE_NAME'
╚══════════════════════════════════════════════════════════════╝
"@ -ForegroundColor Green
