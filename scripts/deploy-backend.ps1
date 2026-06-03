#Requires -RunAsAdministrator
<#
.SYNOPSIS
    Compila el backend de YBioC y lo despliega como servicio de Windows.

.PARAMETROS
    -SkipTests    Omite los tests de Maven (mas rapido)
    -SoloReiniciar  Solo reinicia el servicio sin recompilar (util si solo cambio application.yaml)

.EJEMPLOS
    .\deploy-backend.ps1                  # Compila con tests y despliega
    .\deploy-backend.ps1 -SkipTests       # Compila sin tests y despliega (mas rapido)
    .\deploy-backend.ps1 -SoloReiniciar   # Reinicia el servicio sin recompilar
#>

param(
    [switch]$SkipTests,
    [switch]$SoloReiniciar
)

$ErrorActionPreference = "Stop"

# ── Configuracion ─────────────────────────────────────────────────────────────
$SERVICE_NAME = "YBioCBackend"
$REPO_ROOT    = "C:\Repositorio_git\cobiar"
$BACKEND_DIR  = "$REPO_ROOT\YBioC\backend"
$DEPLOY_DIR   = "$REPO_ROOT\YBioC\deploy"
$DEPLOY_JAR   = "$DEPLOY_DIR\backend.jar"
$LOG_DIR      = "$DEPLOY_DIR\logs"
$JAVA_HOME_21 = "C:\Program Files\Java\jdk-21.0.7"   # Maven usara este JDK para compilar
# ──────────────────────────────────────────────────────────────────────────────

function Write-Step([string]$msg) {
    Write-Host "`n>> $msg" -ForegroundColor Cyan
}

function Stop-Backend {
    $svc = Get-Service -Name $SERVICE_NAME -ErrorAction SilentlyContinue
    if (-not $svc) {
        Write-Host "   Servicio '$SERVICE_NAME' no instalado. Usar instalar-servicio.ps1 primero." -ForegroundColor Yellow
        return $false
    }
    if ($svc.Status -eq "Running") {
        Write-Host "   Deteniendo servicio..."
        Stop-Service -Name $SERVICE_NAME -Force
        # Esperar hasta que se detenga (max 15s)
        $timeout = 15
        while ((Get-Service -Name $SERVICE_NAME).Status -ne "Stopped" -and $timeout -gt 0) {
            Start-Sleep -Seconds 1
            $timeout--
        }
        if ((Get-Service -Name $SERVICE_NAME).Status -ne "Stopped") {
            throw "El servicio no se detuvo en el tiempo esperado."
        }
        Write-Host "   Servicio detenido." -ForegroundColor Green
    } else {
        Write-Host "   Servicio ya estaba detenido." -ForegroundColor Yellow
    }
    return $true
}

function Start-Backend {
    Write-Host "   Iniciando servicio..."
    Start-Service -Name $SERVICE_NAME
    Start-Sleep -Seconds 3
    $status = (Get-Service -Name $SERVICE_NAME).Status
    Write-Host "   Estado: $status" -ForegroundColor $(if ($status -eq "Running") { "Green" } else { "Red" })
    if ($status -eq "Running") {
        Write-Host "   Backend disponible en http://localhost:8080" -ForegroundColor Green
        Write-Host "   Log: $LOG_DIR\backend.log"
    }
}

$startTime = Get-Date

if ($SoloReiniciar) {
    Write-Step "Reiniciando servicio (sin recompilar)..."
    Stop-Backend | Out-Null
    Start-Backend
} else {
    # 1. Detener servicio
    Write-Step "Deteniendo backend..."
    Stop-Backend | Out-Null

    # 2. Compilar
    Write-Step "Compilando backend$(if ($SkipTests) { ' (sin tests)' } else { ' (con tests)' })..."
    Push-Location $BACKEND_DIR
    try {
        $env:JAVA_HOME = $JAVA_HOME_21
        if ($SkipTests) {
            mvn clean package -DskipTests
        } else {
            mvn clean package
        }
        if ($LASTEXITCODE -ne 0) {
            throw "Maven fallo con codigo de salida $LASTEXITCODE."
        }
    } finally {
        Pop-Location
    }
    Write-Host "   Compilacion exitosa." -ForegroundColor Green

    # 3. Copiar JAR
    Write-Step "Desplegando JAR..."
    if (-not (Test-Path $DEPLOY_DIR)) { New-Item -ItemType Directory -Path $DEPLOY_DIR | Out-Null }
    if (-not (Test-Path $LOG_DIR))    { New-Item -ItemType Directory -Path $LOG_DIR    | Out-Null }

    $builtJar = Get-ChildItem "$BACKEND_DIR\target\*.jar" |
        Where-Object { $_.Name -notlike "*sources*" -and $_.Name -notlike "*javadoc*" -and $_.Name -notlike "*original*" } |
        Sort-Object LastWriteTime -Descending |
        Select-Object -First 1

    if (-not $builtJar) {
        throw "No se encontro ningun JAR en $BACKEND_DIR\target\. Verificar la compilacion."
    }

    Copy-Item $builtJar.FullName $DEPLOY_JAR -Force
    $jarSize = [math]::Round($builtJar.Length / 1MB, 1)
    Write-Host "   $($builtJar.Name) ($jarSize MB) → backend.jar" -ForegroundColor Green

    # 4. Iniciar servicio
    Write-Step "Iniciando backend..."
    Start-Backend
}

$elapsed = [math]::Round(((Get-Date) - $startTime).TotalSeconds, 1)
Write-Host "`n✓ Deploy completado en ${elapsed}s." -ForegroundColor Green
