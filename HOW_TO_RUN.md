# 🚀 How to Run GameArena Project

## Prerequisites

Before running the project, ensure you have:

### Required Software
1. **Android Studio** (Latest version - Hedgehog or newer)
   - Download: https://developer.android.com/studio
   - Minimum version: Android Studio Hedgehog | 2023.1.1

2. **JDK 11 or Higher**
   - Comes bundled with Android Studio
   - Or download separately: https://adoptium.net/

3. **Android Device/Emulator**
   - Physical Android device (Android 7.0+, API 24+)
   - OR Android Emulator (comes with Android Studio)

---

## 📥 Step 1: Get the Project

### If Already on Your Computer
Your project is located at: `D:\AndriodStudioAPK`

### If Cloning from GitHub (Future)
```bash
git clone https://github.com/dev11062004/GameArena-Sports-Facility-Booking-App.git
cd GameArena-Sports-Facility-Booking-App
```

---

## 🛠️ Step 2: Open in Android Studio

### Method A: Open Existing Project
1. **Launch Android Studio**
2. Click **"Open"** on the welcome screen
3. Navigate to `D:\AndriodStudioAPK`
4. Click **"OK"**

### Method B: From File Menu
1. Open Android Studio
2. Go to **File → Open**
3. Select `D:\AndriodStudioAPK` folder
4. Click **"OK"**

---

## ⚙️ Step 3: Gradle Sync (IMPORTANT)

After opening, Android Studio will automatically start syncing:

### What Happens During Sync:
- ✅ Downloads all dependencies (Navigation, Room, Compose)
- ✅ Configures build tools
- ✅ Runs KSP annotation processor for Room
- ✅ Prepares project for compilation

### Expected Time:
- **First time**: 5-10 minutes (downloads ~200MB)
- **Subsequent syncs**: 30 seconds - 2 minutes

### Monitor Progress:
- Look at the bottom status bar: "Gradle: Syncing..."
- Or check **Build** tab at the bottom

### If Sync Fails:
```
1. Check internet connection (needed to download dependencies)
2. File → Invalidate Caches → Restart
3. File → Sync Project with Gradle Files
4. Or: Build → Clean Project → Build → Rebuild Project
```

---

## 🎯 Step 4: Configure Run Configuration (Usually Automatic)

Android Studio should automatically detect the app configuration.

### Verify Configuration:
1. Look at the top toolbar
2. You should see **"app"** in the run configuration dropdown
3. Next to it, you'll see device selector

### If Not Configured:
1. Click **Run → Edit Configurations**
2. Click **+** → **Android App**
3. Name: `app`
4. Module: `GameArena-Sports-Facility-Booking-App.app`
5. Click **OK**

---

## 📱 Step 5: Setup Device

### Option A: Use Android Emulator (Recommended for Testing)

#### Create Emulator:
1. Click **Device Manager** icon (phone icon in toolbar)
2. Click **"Create Device"**
3. Select device: **Pixel 5** or any phone
4. Click **Next**
5. Select system image: **API 34** (Android 14) or **API 33**
   - Click **Download** if not available
6. Click **Next** → **Finish**

#### Start Emulator:
1. In Device Manager, click ▶️ next to your emulator
2. Wait for emulator to boot (1-2 minutes first time)
3. Emulator window will open showing Android home screen

### Option B: Use Physical Device

#### Enable Developer Options:
1. On your Android phone:
   - Go to **Settings → About Phone**
   - Tap **Build Number** 7 times
   - You'll see "You are now a developer!"

#### Enable USB Debugging:
1. Go to **Settings → System → Developer Options**
2. Turn on **USB Debugging**
3. Connect phone to computer via USB cable

#### Verify Connection:
1. Open PowerShell/Terminal
2. Run: `adb devices`
3. Your device should be listed
4. On phone, tap **"Allow"** when prompted

---

## ▶️ Step 6: Build and Run

### Build the Project:
1. Click **Build → Make Project** (or press `Ctrl+F9`)
2. Wait for build to complete
3. Check **Build** tab for any errors

### Run the App:
1. **Select Device**: 
   - Use dropdown next to ▶️ button
   - Choose your emulator or connected phone

2. **Click Run**:
   - Click the green ▶️ **Run** button (or press `Shift+F10`)
   - Or: **Run → Run 'app'**

3. **Watch Progress**:
   - Build output appears in **Build** tab
   - Installation progress shown
   - App launches automatically

### First Run Expected Time:
- **Build**: 2-5 minutes (first time)
- **Install**: 10-30 seconds
- **Launch**: App opens on device

---

## ✅ Step 7: Verify App is Running

### You Should See:

#### 1. Home Screen (First Screen)
- **Top bar**: "GameArena"
- **Filter chips**: All, Cricket, Pool, Pickleball
- **Facility cards**: 8 facilities listed
  - Cricket Ground A ($50/hr)
  - Cricket Ground B ($40/hr)
  - Pool Table 1, 2, 3 ($12-15/hr)
  - Pickleball Court 1, 2, Outdoor ($20-25/hr)
- **Bottom navigation**: Home 🏠 | Bookings 📜

#### 2. Test Booking Flow:
1. **Tap any facility** (e.g., "Cricket Ground A")
2. **Select date** (scroll through next 7 days)
3. **Choose time slot** (tap any available green slot)
4. **Click "Book Now - $XX.XX"** button
5. **Verify**: Returns to Home, booking created

#### 3. Check Booking History:
1. **Tap "Bookings" tab** at bottom
2. **See your booking** with:
   - Booking ID
   - Date and time
   - Price
   - "Cancel Booking" button
3. **Test cancel**: Tap "Cancel Booking"

---

## 🐛 Troubleshooting

### Build Errors

#### "JAVA_HOME is not set"
```
→ Android Studio includes JDK, use built-in JDK
→ File → Project Structure → SDK Location → Use embedded JDK
```

#### "Room schema export directory not set"
```
→ This is just a warning, safe to ignore
→ Or add to app/build.gradle.kts:
  room {
    schemaDirectory("$projectDir/schemas")
  }
```

#### "Unresolved reference: ..."
```
→ Wait for Gradle sync to complete
→ File → Invalidate Caches → Restart
→ Build → Clean Project
→ Build → Rebuild Project
```

#### KSP Processing Failed
```
→ Clean and rebuild:
  Build → Clean Project
  Build → Rebuild Project
→ Or: ./gradlew clean build --refresh-dependencies
```

### Runtime Errors

#### App Crashes on Launch
1. **Check Logcat** (bottom panel):
   - Look for red error messages
   - Common: Database initialization errors

2. **Clear App Data**:
   - Long press app icon on device
   - App info → Storage → Clear Data
   - Relaunch app

3. **Reinstall**:
   - Uninstall from device
   - Run → Run 'app' again

#### "Database initialization failed"
```
→ Check minSdk in build.gradle.kts (should be 24)
→ Check device Android version (7.0+)
→ Clear app data and reinstall
```

#### Blank/White Screen
```
→ Check Logcat for Compose errors
→ Ensure all Compose dependencies synced
→ Rebuild project
```

### Device Connection Issues

#### Device Not Detected
```
→ Check USB cable connection
→ Enable USB Debugging on phone
→ Run: adb kill-server && adb start-server
→ Reconnect device
```

#### Emulator Won't Start
```
→ Check system requirements (HAXM/Intel VT-x enabled)
→ Create new emulator with different API level
→ Restart Android Studio
```

---

## 🎨 Using the App

### Complete User Flow:

#### 1. Browse Facilities
- Filter by sport type using chips
- Scroll through facility list
- View prices and descriptions

#### 2. Book a Facility
- Tap facility card
- Scroll dates (shows next 7 days)
- Select available time slot (green = available)
- Tap "Book Now" with calculated price
- Booking confirmed automatically

#### 3. View Bookings
- Tap "Bookings" tab at bottom
- See all your bookings (latest first)
- Status badges: Confirmed/Cancelled/Completed

#### 4. Cancel Booking
- In Bookings screen
- Tap "Cancel Booking" on upcoming booking
- Status changes to "Cancelled"

---

## 📊 Verify Everything Works

### Quick Test Checklist:

```
✅ Home screen loads with 8 facilities
✅ Filter chips work (Cricket/Pool/Pickleball)
✅ Tapping facility opens booking screen
✅ Date selector shows next 7 days
✅ Time slots display (6 AM - 10 PM)
✅ Can select a time slot (turns blue)
✅ "Book Now" button shows correct price
✅ Booking creates successfully
✅ Returns to home screen
✅ "Bookings" tab shows the booking
✅ Can see booking details
✅ Can cancel the booking
✅ Cancelled booking shows "Cancelled" status
```

---

## 🔧 Command Line Build (Alternative)

If you prefer terminal/command line:

### Windows PowerShell:
```powershell
# Navigate to project
cd D:\AndriodStudioAPK

# Clean and build
.\gradlew clean build

# Install on connected device
.\gradlew installDebug

# Or build and install in one command
.\gradlew clean installDebug

# Run tests
.\gradlew test
```

### Check Build Output:
- APK location: `app/build/outputs/apk/debug/app-debug.apk`
- Install manually: `adb install app/build/outputs/apk/debug/app-debug.apk`

---

## 📱 Install APK Directly

### Generate APK:
1. **Build → Build Bundle(s) / APK(s) → Build APK(s)**
2. Wait for build to complete
3. Click **"locate"** in notification
4. APK is at: `app/build/outputs/apk/debug/app-debug.apk`

### Install on Device:
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

Or transfer APK to phone and install manually.

---

## 🎓 Development Tips

### Hot Reload (Live Updates):
- ⚡ **Live Edit**: Modify Compose code while app runs
- Changes appear instantly (no rebuild needed)
- Limited to UI changes only

### View Logcat (Debug Console):
1. Click **Logcat** tab at bottom
2. Filter by app: Select "com.example.gamearena"
3. See real-time logs and errors

### Database Inspector:
1. **View → Tool Windows → App Inspection**
2. Select running device
3. Navigate to **Database Inspector**
4. Explore `gamearena_database`
5. View facilities and bookings tables in real-time

### Layout Inspector:
1. **Tools → Layout Inspector**
2. Select running app
3. See UI hierarchy and properties
4. Debug Compose layouts

---

## 📝 First Time Setup Summary

### Quick Start (TL;DR):
```
1. Open Android Studio
2. Open folder: D:\AndriodStudioAPK
3. Wait for Gradle sync (5-10 min)
4. Click Device Manager → Create Device (Pixel 5, API 34)
5. Start emulator
6. Click Run ▶️ button
7. Wait for app to launch
8. Test: Browse → Book → View History
```

### Expected Timeline:
- **Setup**: 15-20 minutes (first time)
- **Gradle sync**: 5-10 minutes (first time)
- **Build**: 2-5 minutes (first time)
- **Subsequent runs**: 30-60 seconds

---

## 🎯 Success Indicators

✅ **Gradle sync successful** (no red errors)
✅ **Build completed** (green checkmark)
✅ **App installed** on device
✅ **App launched** without crashes
✅ **Home screen** displays facilities
✅ **Booking flow** works end-to-end
✅ **Database persists** bookings

---

## 💡 Pro Tips

### Speed Up Builds:
1. **Enable Gradle Daemon** (usually enabled by default)
2. **Increase memory**: 
   - Edit `gradle.properties`
   - Add: `org.gradle.jvmargs=-Xmx4096m`
3. **Use Build Cache**: Already enabled

### Keyboard Shortcuts:
- **Run**: `Shift + F10`
- **Debug**: `Shift + F9`
- **Build**: `Ctrl + F9`
- **Clean**: `Build → Clean Project`
- **Sync**: `Ctrl + Alt + S` (Settings)

### Best Practices:
- 🔄 **Sync regularly** after Git pulls
- 🧹 **Clean build** if errors persist
- 📱 **Test on real device** before release
- 💾 **Check Logcat** for runtime issues

---

## 🆘 Need Help?

### Resources:
- **Android Studio Guide**: https://developer.android.com/studio/run
- **Jetpack Compose**: https://developer.android.com/jetpack/compose
- **Room Database**: https://developer.android.com/training/data-storage/room
- **Troubleshooting**: Check `SETUP_GUIDE.md` in project root

### Common Issues:
- All resolved in **Troubleshooting** section above
- Check **Logcat** for specific errors
- Search error messages on Stack Overflow

---

## ✨ You're Ready!

Your GameArena app is fully functional and ready to run!

**Just open in Android Studio, sync, and click Run!** 🚀

---

**Happy Coding!** 💻📱
