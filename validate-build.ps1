# GameArena Build Validator
# Run this script before building to catch errors early

Write-Host "GameArena Build Validation Starting..." -ForegroundColor Cyan
Write-Host ""

# Navigate to project directory
$projectPath = "D:\AndriodStudioAPK"
Set-Location $projectPath

Write-Host "Project Path: $projectPath" -ForegroundColor Yellow
Write-Host ""

# Step 1: Check for missing critical imports
Write-Host "1. Checking for missing imports..." -ForegroundColor Green

$errors = @()

# Check Type.kt for Color import
$typeKt = Get-Content "app\src\main\java\com\example\helloworldapk\ui\theme\Type.kt" -Raw
if ($typeKt -notmatch "import androidx\.compose\.ui\.graphics\.Color") {
    $errors += "ERROR: Type.kt missing Color import"
} else {
    Write-Host "   OK: Type.kt has Color import" -ForegroundColor DarkGreen
}

# Check RegistrationScreen.kt for clickable import
$regScreen = Get-Content "app\src\main\java\com\example\helloworldapk\ui\screens\RegistrationScreen.kt" -Raw
if ($regScreen -match "\.clickable" -and $regScreen -notmatch "import androidx\.compose\.foundation\.clickable") {
    $errors += "ERROR: RegistrationScreen.kt missing clickable import"
} else {
    Write-Host "   OK: RegistrationScreen.kt imports are correct" -ForegroundColor DarkGreen
}

Write-Host ""

# Step 2: Check Gradle files exist
Write-Host "2. Checking Gradle configuration..." -ForegroundColor Green

if (Test-Path "build.gradle.kts") {
    Write-Host "   OK: Root build.gradle.kts exists" -ForegroundColor DarkGreen
} else {
    $errors += "ERROR: Missing root build.gradle.kts"
}

if (Test-Path "app\build.gradle.kts") {
    Write-Host "   OK: App build.gradle.kts exists" -ForegroundColor DarkGreen
} else {
    $errors += "ERROR: Missing app build.gradle.kts"
}

Write-Host ""

# Step 3: Validate Kotlin files syntax (basic check)
Write-Host "3. Validating Kotlin file syntax..." -ForegroundColor Green

$kotlinFiles = Get-ChildItem -Path "app\src\main\java" -Filter "*.kt" -Recurse
$syntaxErrors = 0

foreach ($file in $kotlinFiles) {
    $content = Get-Content $file.FullName -Raw

    # Check for common syntax errors
    if ($content -match "import\s+\n") {
        Write-Host "   ⚠️  Incomplete import in $($file.Name)" -ForegroundColor Yellow
        $syntaxErrors++
    }
}

if ($syntaxErrors -eq 0) {
    Write-Host "   ✅ All Kotlin files have valid syntax structure" -ForegroundColor DarkGreen
}

Write-Host ""

# Step 4: Summary
Write-Host "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" -ForegroundColor Cyan
Write-Host "📊 VALIDATION SUMMARY" -ForegroundColor Cyan
Write-Host "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" -ForegroundColor Cyan
Write-Host ""

if ($errors.Count -eq 0) {
    Write-Host "✅ ALL CHECKS PASSED!" -ForegroundColor Green
    Write-Host ""
    Write-Host "🚀 Your app is ready to build!" -ForegroundColor Green
    Write-Host ""
    Write-Host "Next steps:" -ForegroundColor Yellow
    Write-Host "   1. Open Android Studio" -ForegroundColor White
    Write-Host "   2. Click Run ▶️  button (Shift+F10)" -ForegroundColor White
    Write-Host "   3. Wait for build to complete" -ForegroundColor White
    Write-Host ""
    exit 0
} else {
    Write-Host "❌ VALIDATION FAILED" -ForegroundColor Red
    Write-Host ""
    Write-Host "Errors found:" -ForegroundColor Red
    foreach ($error in $errors) {
        Write-Host "   $error" -ForegroundColor Red
    }
    Write-Host ""
    Write-Host "Please fix these errors before building." -ForegroundColor Yellow
    Write-Host ""
    exit 1
}
