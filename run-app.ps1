# Quick Build and Run Script
# Run this to build and install the app on your connected device

Write-Host "======================================" -ForegroundColor Cyan
Write-Host "  GameArena - Build and Run Script" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""

# Stop any running Gradle daemons
Write-Host "Stopping Gradle daemons..." -ForegroundColor Yellow
.\gradlew --stop | Out-Null

Write-Host "✓ Gradle daemons stopped" -ForegroundColor Green
Write-Host ""

# Clean build
Write-Host "Cleaning build..." -ForegroundColor Yellow
.\gradlew clean | Out-Null

Write-Host "✓ Build cleaned" -ForegroundColor Green
Write-Host ""

# Build debug APK
Write-Host "Building debug APK..." -ForegroundColor Yellow
Write-Host "(This may take a few minutes on first run)" -ForegroundColor Gray

$buildResult = .\gradlew assembleDebug 2>&1

if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ Build successful!" -ForegroundColor Green
    Write-Host ""
    Write-Host "APK location: app\build\outputs\apk\debug\app-debug.apk" -ForegroundColor Cyan
    Write-Host ""

    # Check if device is connected
    $devices = adb devices | Select-String -Pattern "device$"

    if ($devices) {
        Write-Host "Device detected! Installing app..." -ForegroundColor Yellow
        .\gradlew installDebug

        if ($LASTEXITCODE -eq 0) {
            Write-Host "✓ App installed successfully!" -ForegroundColor Green
            Write-Host ""
            Write-Host "Starting app..." -ForegroundColor Yellow
            adb shell am start -n com.example.gamearena/.MainActivity
            Write-Host "✓ App started!" -ForegroundColor Green
        } else {
            Write-Host "✗ Installation failed" -ForegroundColor Red
        }
    } else {
        Write-Host "ℹ No device connected" -ForegroundColor Yellow
        Write-Host "  Connect your device via USB to install automatically" -ForegroundColor Gray
        Write-Host "  Or copy APK to phone manually:" -ForegroundColor Gray
        Write-Host "  app\build\outputs\apk\debug\app-debug.apk" -ForegroundColor Cyan
    }
} else {
    Write-Host "✗ Build failed" -ForegroundColor Red
    Write-Host ""
    Write-Host "Error details:" -ForegroundColor Yellow
    Write-Host $buildResult -ForegroundColor Red
}

Write-Host ""
Write-Host "======================================" -ForegroundColor Cyan

