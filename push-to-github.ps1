Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  🚀 GameArena - GitHub Upload" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Check if git is available
Write-Host "🔍 Checking Git installation..." -ForegroundColor Yellow
$gitVersion = git --version 2>&1
if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ Git found: $gitVersion" -ForegroundColor Green
} else {
    Write-Host "✗ Git not found. Please install Git first." -ForegroundColor Red
    exit 1
}
Write-Host ""

# Check remote
Write-Host "🔗 Checking remote repository..." -ForegroundColor Yellow
$remote = git remote get-url origin 2>&1
if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ Remote: $remote" -ForegroundColor Green
} else {
    Write-Host "⚠ No remote configured" -ForegroundColor Yellow
    Write-Host "  Please set remote with:" -ForegroundColor Gray
    Write-Host "  git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git" -ForegroundColor Gray
    exit 1
}
Write-Host ""

# Show current branch
$branch = git branch --show-current 2>&1
Write-Host "📍 Current branch: $branch" -ForegroundColor Cyan
Write-Host ""

# Stage all changes
Write-Host "📦 Staging all changes..." -ForegroundColor Yellow
git add . 2>&1 | Out-Null

if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ All changes staged" -ForegroundColor Green
} else {
    Write-Host "✗ Failed to stage changes" -ForegroundColor Red
    exit 1
}
Write-Host ""

# Show what will be committed
Write-Host "📋 Files ready to commit:" -ForegroundColor Yellow
git status --short | ForEach-Object {
    if ($_ -match "^M ") {
        Write-Host "  📝 Modified: $_" -ForegroundColor Blue
    } elseif ($_ -match "^A ") {
        Write-Host "  ➕ Added: $_" -ForegroundColor Green
    } elseif ($_ -match "^D ") {
        Write-Host "  ➖ Deleted: $_" -ForegroundColor Red
    } elseif ($_ -match "^\?\? ") {
        Write-Host "  📄 New: $_" -ForegroundColor Cyan
    }
}
Write-Host ""

# Confirm
Write-Host "⚠️  Ready to commit and push to GitHub" -ForegroundColor Yellow
Write-Host "   Press Enter to continue or Ctrl+C to cancel..." -ForegroundColor Gray
Read-Host

# Commit
Write-Host ""
Write-Host "💾 Creating commit..." -ForegroundColor Yellow

$commitMessage = @"
Complete GameArena sports facility booking app

Major Changes:
- Refactored package from helloworldapk to gamearena
- Fixed all compilation errors (10+ critical fixes)
- Added Firebase Authentication and Firestore integration
- Implemented booking system with Room database
- Created modern UI with Jetpack Compose & Material 3
- Added comprehensive documentation

Features:
- User authentication (login/signup/password reset)
- Sports facility browsing with search
- Real-time booking system with time slots
- Booking history and management
- User profile management
- Firebase cloud sync

Tech Stack:
- Kotlin 2.0.21
- Jetpack Compose (Material 3)
- Firebase (Auth + Firestore)
- Room Database
- MVVM Architecture
- Coroutines + Flow

Fixes Applied:
- UserPreferences: Added saveUserSession method
- GameArenaTextField: Added enabled parameter
- LoginScreen: Cleaned up unused imports
- gradle.properties: Fixed JDK 25 incompatibility
- BookingViewModel: Fixed package references
- Test files: Updated package names
- Themes: Renamed to GameArena
- Manifest: Updated theme references

Status: All errors fixed, tested, and ready for deployment.
"@

git commit -m $commitMessage 2>&1 | Out-Null

if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ Commit created successfully" -ForegroundColor Green
} else {
    Write-Host "⚠ Nothing to commit or commit failed" -ForegroundColor Yellow
    $continueAnyway = Read-Host "Continue with push anyway? (y/n)"
    if ($continueAnyway -ne "y") {
        exit 1
    }
}
Write-Host ""

# Push to GitHub
Write-Host "⬆️  Pushing to GitHub..." -ForegroundColor Yellow
Write-Host "   (This may take a minute...)" -ForegroundColor Gray
Write-Host ""

git push origin $branch 2>&1

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Green
    Write-Host "  ✅ Successfully uploaded to GitHub!" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Green
    Write-Host ""
    Write-Host "🔗 Repository URL:" -ForegroundColor Cyan
    Write-Host "   $remote" -ForegroundColor White
    Write-Host ""
    Write-Host "📝 Next Steps:" -ForegroundColor Yellow
    Write-Host "   1. Visit your repository on GitHub" -ForegroundColor Gray
    Write-Host "   2. Verify all files are uploaded" -ForegroundColor Gray
    Write-Host "   3. Update repository description" -ForegroundColor Gray
    Write-Host "   4. Add topics/tags" -ForegroundColor Gray
    Write-Host "   5. Consider updating README.md with README_GITHUB.md" -ForegroundColor Gray
    Write-Host ""
} else {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Red
    Write-Host "  ❌ Upload failed" -ForegroundColor Red
    Write-Host "========================================" -ForegroundColor Red
    Write-Host ""
    Write-Host "🔍 Possible solutions:" -ForegroundColor Yellow
    Write-Host "   1. Check your internet connection" -ForegroundColor Gray
    Write-Host "   2. Verify GitHub authentication" -ForegroundColor Gray
    Write-Host "   3. Try: git pull origin $branch --rebase" -ForegroundColor Gray
    Write-Host "   4. Check repository permissions" -ForegroundColor Gray
    Write-Host ""
}

Write-Host "Press Enter to exit..."
Read-Host

