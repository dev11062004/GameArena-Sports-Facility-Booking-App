# GameArena Build Validator
Write-Host "GameArena Build Validation Starting..." -ForegroundColor Cyan

$projectPath = "D:\AndriodStudioAPK"
Set-Location $projectPath
$errors = @()

# Check Type.kt for Color import
$typeKt = Get-Content "app\src\main\java\com\example\helloworldapk\ui\theme\Type.kt" -Raw
if ($typeKt -notmatch "import androidx\.compose\.ui\.graphics\.Color") {
    $errors += "Type.kt missing Color import"
} else {
    Write-Host "OK: Type.kt has Color import" -ForegroundColor Green
}

# Check RegistrationScreen.kt for clickable import
$regScreen = Get-Content "app\src\main\java\com\example\helloworldapk\ui\screens\RegistrationScreen.kt" -Raw
if ($regScreen -match "\.clickable" -and $regScreen -notmatch "import androidx\.compose\.foundation\.clickable") {
    $errors += "RegistrationScreen.kt missing clickable import"
} else {
    Write-Host "OK: RegistrationScreen.kt imports correct" -ForegroundColor Green
}

# Summary
Write-Host ""
Write-Host "======================================"
Write-Host "VALIDATION SUMMARY"
Write-Host "======================================"

if ($errors.Count -eq 0) {
    Write-Host "ALL CHECKS PASSED!" -ForegroundColor Green
    Write-Host "Your app is ready to build!" -ForegroundColor Green
} else {
    Write-Host "VALIDATION FAILED" -ForegroundColor Red
    foreach ($error in $errors) {
        Write-Host "  - $error" -ForegroundColor Red
    }
}
