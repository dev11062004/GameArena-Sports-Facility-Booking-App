# GameArena Quick Setup Guide

## What You Need to Do Now

### 1. Install/Setup Java (Required)
The Gradle build requires Java. You have two options:

**Option A: Set JAVA_HOME (if Java is already installed)**
```powershell
# Find your Java installation (usually in Program Files)
# Then set JAVA_HOME environment variable
$env:JAVA_HOME = "C:\Program Files\Java\jdk-11.0.x"
# Or wherever your JDK is installed
```

**Option B: Android Studio will handle it**
- Open this project in Android Studio
- Android Studio includes its own JDK
- No manual Java setup needed!

### 2. Open in Android Studio (RECOMMENDED)

1. **Launch Android Studio**
2. **File → Open** → Select `D:\AndriodStudioAPK`
3. **Wait for Gradle Sync** (first time will download dependencies - may take 5-10 minutes)
4. **Build → Make Project** (or Ctrl+F9)
5. **Run → Run 'app'** (or Shift+F10)

### 3. What Happens on First Build

The build system will:
- Download all dependencies (Navigation, Room, Compose libraries)
- Run KSP annotation processor to generate Room database code
- Compile Kotlin code to Android bytecode
- Package everything into an APK

**Expected build time**: 3-10 minutes (first time only)

### 4. Running the App

**On Emulator:**
- Tools → Device Manager → Create new device
- Select Pixel 5 or similar (API 24+)
- Click "Run" button in Android Studio

**On Physical Device:**
- Enable Developer Options on your phone
- Enable USB Debugging
- Connect via USB
- Select device and click "Run"

## What the App Does

### Home Screen
- Shows all 8 pre-populated facilities
- Filter by type: Cricket / Pool / Pickleball
- Tap any facility to book

### Booking Screen
- Select date (next 7 days available)
- Choose time slot (6 AM - 10 PM, hourly)
- See price calculation
- Confirm booking

### My Bookings Screen
- View all your bookings
- See status: Confirmed/Cancelled/Completed
- Cancel upcoming bookings

## App Architecture Summary

```
USER TAPS FACILITY
    ↓
HomeScreen (Compose UI)
    ↓
FacilitiesViewModel (State Management)
    ↓
FacilityRepository (Business Logic)
    ↓
FacilityDao (Room Database)
    ↓
SQLite Database (Local Storage)
```

## Quick Test Flow

1. **Launch app** → See list of facilities
2. **Tap "Cricket Ground A"** → Opens booking screen
3. **Scroll dates** → Select tomorrow
4. **Tap "10:00"** → Selects time slot
5. **Tap "Book Now"** → Creates booking
6. **Tap "Bookings" tab** → See your booking
7. **Tap "Cancel Booking"** → Cancels it

## Files Created/Modified

### ✅ New Files (25 files)
**Data Layer:**
- `data/Facility.kt`
- `data/Booking.kt`
- `data/FacilityType.kt`
- `data/TimeSlot.kt`
- `data/BookingWithFacility.kt`
- `data/FacilityDao.kt`
- `data/BookingDao.kt`
- `data/Converters.kt`
- `data/AppDatabase.kt`
- `data/repository/FacilityRepository.kt`
- `data/repository/BookingRepository.kt`

**ViewModel Layer:**
- `ui/viewmodel/FacilitiesViewModel.kt`
- `ui/viewmodel/BookingViewModel.kt`
- `ui/viewmodel/BookingHistoryViewModel.kt`

**UI Layer:**
- `ui/navigation/Navigation.kt`
- `ui/screens/HomeScreen.kt`
- `ui/screens/FacilityDetailScreen.kt`
- `ui/screens/BookingHistoryScreen.kt`

**Utils:**
- `utils/UserPreferences.kt`

### ✅ Modified Files
- `app/build.gradle.kts` (added dependencies)
- `gradle/libs.versions.toml` (added version catalogs)
- `MainActivity.kt` (complete rewrite)
- `ui/theme/Theme.kt` (renamed theme)
- `res/values/strings.xml` (updated app name)

## Troubleshooting

### "JAVA_HOME not set"
→ Open in Android Studio instead of command line

### "Unresolved reference: ..."
→ Wait for Gradle sync to complete
→ Build → Clean Project
→ Build → Rebuild Project

### "Room schema error"
→ Clean build: `Build → Clean Project`
→ KSP needs to regenerate database code

### App crashes on launch
→ Check Logcat in Android Studio
→ Look for database initialization errors
→ Ensure minSdk is 24+

### Icons not showing
→ Material Icons Extended dependency is included
→ If missing, sync Gradle again

## Build Configuration

**Namespace**: `com.example.gamearena`
**Application ID**: `com.example.gamearena`
**Min SDK**: 24 (Android 7.0+)
**Target SDK**: 36 (Android 14)
**Compile SDK**: 36

## Next Steps After Successful Build

1. **Test the full flow** - Book a facility, cancel it
2. **Customize facilities** - Edit `AppDatabase.kt` to add your own
3. **Change theme colors** - Edit `ui/theme/Color.kt`
4. **Add features** - Payment, photos, reviews
5. **Deploy to Play Store** - Generate signed APK

## Support

If you encounter issues:
1. Check Android Studio's "Build" tab for errors
2. Look at Logcat for runtime errors
3. Ensure all dependencies downloaded (check Gradle sync output)
4. Try File → Invalidate Caches → Restart

---

**Ready to build?** Open in Android Studio now!
