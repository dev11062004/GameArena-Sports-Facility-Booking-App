# 🚀 Step-by-Step: Running Your Firebase Authentication App

## 📋 Complete Walkthrough

---

## STEP 1: Firebase Console Setup (10 minutes)

### 1.1 Create Firebase Project

1. Open browser and go to: **https://console.firebase.google.com/**

2. Sign in with Google account

3. Click **"Add project"** or **"Create a project"**

4. Enter project name: **`GameArena`**

5. Click **"Continue"**

6. Toggle **OFF** Google Analytics (optional - can keep ON)

7. Click **"Create project"**

8. Wait 30-60 seconds for creation

9. Click **"Continue"** when ready

---

### 1.2 Add Android App to Firebase

1. On Firebase Console dashboard, click the **Android icon** (or "Add app" → Android)

2. Fill in the registration form:
   - **Android package name:** `com.example.gamearena`
   - **App nickname:** `GameArena` (optional)
   - **Debug signing certificate SHA-1:** Leave blank for now
   
3. Click **"Register app"**

---

### 1.3 Download google-services.json

1. Click **"Download google-services.json"** button

2. Save the file to your computer (Downloads folder is fine)

3. **IMPORTANT:** Navigate to `D:\AndriodStudioAPK\app\`

4. **DELETE** the existing `google-services.json` file (it's just a placeholder)

5. **COPY** your downloaded `google-services.json` to `D:\AndriodStudioAPK\app\`

6. Verify the file contains actual Firebase data (not placeholder text)

7. Click **"Next"** in Firebase Console

8. Click **"Continue to console"**

---

### 1.4 Enable Email/Password Authentication

1. In Firebase Console left sidebar, click **"Build"** → **"Authentication"**

2. Click **"Get started"** (if first time)

3. Go to **"Sign-in method"** tab

4. Click **"Email/Password"** row

5. Toggle **ON** the first option "Email/Password"

6. Leave "Email link (passwordless sign-in)" **OFF**

7. Click **"Save"**

8. Verify status shows **"Enabled"** with green dot

---

## STEP 2: Android Studio Setup (5 minutes)

### 2.1 Open Project

1. Open **Android Studio**

2. If not already open, click **"Open"** and navigate to `D:\AndriodStudioAPK`

3. Click **"OK"**

---

### 2.2 Sync Gradle

1. Android Studio should automatically detect changes and prompt to sync

2. If not, click **"File"** → **"Sync Project with Gradle Files"**

3. Wait 1-3 minutes for:
   - Firebase SDK download
   - Google Services plugin configuration
   - Dependency resolution

4. Watch bottom status bar for "Gradle sync finished"

---

### 2.3 Verify No Errors

1. Check **"Build"** tab at bottom for any errors

2. If you see Firebase-related errors:
   - Verify `google-services.json` is in `app/` folder (not root)
   - Verify it's the real file from Firebase (not placeholder)
   - Sync again: **File → Sync Project with Gradle Files**

3. Expected output: **"BUILD SUCCESSFUL"** or **"Sync successful"**

---

## STEP 3: Build the App (3 minutes)

### 3.1 Clean Build (Optional but Recommended)

```
Build → Clean Project
```

Wait 30 seconds for cleanup to complete.

---

### 3.2 Rebuild Project

```
Build → Rebuild Project
```

This will:
- Compile all Kotlin files
- Process Firebase configuration
- Generate necessary files
- Create APK components

**Expected time:** 2-4 minutes (first time)

---

### 3.3 Check Build Output

Look at **"Build"** tab at bottom:

✅ **Success indicators:**
```
> Task :app:compileDebugKotlin
> Task :app:kaptGenerateStubsDebugKotlin
> Task :app:processDebugGoogleServices
BUILD SUCCESSFUL in 2m 45s
```

❌ **If you see errors:**
- Check `google-services.json` is correct
- Verify internet connection
- Sync Gradle again
- See troubleshooting section below

---

## STEP 4: Run the App (2 minutes)

### 4.1 Connect Device or Start Emulator

**Option A: Physical Device (USB)**
1. Enable Developer Options on phone (tap Build Number 7 times)
2. Enable USB Debugging in Developer Options
3. Connect USB cable
4. Allow debugging on phone when prompted

**Option B: Emulator**
1. Click **Device Manager** icon (phone icon in toolbar)
2. Click **▶️** next to an existing emulator
3. OR click **"Create Device"** → Pixel 5 → API 34 → Finish
4. Wait 1-2 minutes for emulator to boot

---

### 4.2 Select Target Device

1. Look at top toolbar in Android Studio

2. Find device dropdown (next to Run button)

3. Select your connected device or running emulator

---

### 4.3 Run the App

1. Click green **▶️ Run** button in toolbar

2. OR press **Shift+F10**

3. OR click **Run → Run 'app'**

4. Watch console output:

```
Launching 'app' on [Device Name]
Installing APKs...
$ adb push-package
App installed successfully
Launching activity...
$ adb shell am start...
```

5. App should launch automatically on device/emulator

---

## STEP 5: Test Authentication (10 minutes)

### 5.1 First Launch - Login Screen

You should see:
- ✅ Dark background
- ✅ "GameArena" title in yellow/green
- ✅ Email input field
- ✅ Password input field
- ✅ "Log In" button (yellow)
- ✅ "Don't have an account? Sign Up" link

---

### 5.2 Test Registration

1. Click **"Sign Up"** link

2. You're now on Registration Screen

3. Fill in the form:
   ```
   Full Name: John Doe
   Email: test@gamearena.com
   Password: password123
   Confirm Password: password123
   Gender: Male (select radio button)
   Terms: ✓ (check the box)
   ```

4. Click **"Sign Up"** button

5. You should see:
   - Loading indicator (circular progress)
   - "Creating Account..." text
   - After 2-3 seconds → Navigate to Home Screen

6. **Verify in Firebase Console:**
   - Go to Authentication → Users tab
   - You should see `test@gamearena.com` listed

---

### 5.3 Test Logout

1. On Home Screen, look at top-right corner

2. Click the **logout icon** (exit/door icon)

3. Confirmation dialog appears: "Are you sure you want to logout?"

4. Click **"Logout"**

5. Should navigate back to Login Screen

---

### 5.4 Test Login

1. On Login Screen, enter:
   ```
   Email: test@gamearena.com
   Password: password123
   ```

2. Click **"Log In"** button

3. You should see:
   - Loading indicator
   - "Logging in..." text
   - After 1-2 seconds → Navigate to Home Screen

---

### 5.5 Test Session Persistence

1. **Close the app completely:**
   - Swipe up from bottom (or press recent apps button)
   - Swipe GameArena app away
   - OR Force stop in device settings

2. **Reopen the app** by clicking icon

3. **Expected behavior:**
   - Should **skip** Login Screen
   - Should go **directly** to Home Screen
   - User remains logged in

4. **This proves session persistence works!** ✅

---

## STEP 6: Test Error Handling

### 6.1 Registration Errors

Test these scenarios:

**Weak Password:**
```
Password: 123
Confirm: 123
Result: "Password must be at least 6 characters"
```

**Mismatched Passwords:**
```
Password: password123
Confirm: password456
Result: "Passwords do not match"
```

**Existing Email:**
```
Email: test@gamearena.com (already registered)
Result: "Email already registered"
```

**Invalid Email:**
```
Email: notanemail
Result: "Please enter a valid email address"
```

**Missing Terms:**
```
Terms: Unchecked
Result: "Please accept Terms & Conditions"
```

---

### 6.2 Login Errors

**Wrong Password:**
```
Email: test@gamearena.com
Password: wrongpassword
Result: "Incorrect password"
```

**Non-existent Email:**
```
Email: doesnotexist@test.com
Password: anything
Result: "No account found with this email"
```

**Empty Fields:**
```
Email: (empty)
Password: (empty)
Result: "Please enter a valid email address"
```

---

## STEP 7: Verify in Firebase Console

### 7.1 Check Authentication

1. Go to Firebase Console: https://console.firebase.google.com/

2. Select **"GameArena"** project

3. Click **Authentication** in left sidebar

4. Go to **Users** tab

5. You should see:
   - User ID (UID)
   - Email: test@gamearena.com
   - Created: [timestamp]
   - Last sign-in: [timestamp]
   - Providers: Email/Password

---

### 7.2 Check Firestore Database

1. In Firebase Console, click **Firestore Database**

2. If prompted, create database in test mode

3. Navigate to **users** collection

4. You should see documents with user data:
   ```
   Collection: users
   Document: [user UID]
   Fields:
     - email: "test@gamearena.com"
     - fullName: "John Doe"
     - createdAt: [timestamp]
   ```

---

## STEP 8: Capture Screenshots

For your lab report, capture:

1. **Login Screen** - Clean state
2. **Registration Screen** - Filled form
3. **Home Screen** - With logout button visible
4. **Logout Dialog** - Confirmation popup
5. **Firebase Console** - Authentication page
6. **Firebase Console** - Users list showing your test user
7. **Firebase Console** - Firestore users collection
8. **App Flow** - Video/GIF of login → home → logout

---

## ✅ SUCCESS CHECKLIST

After completing all steps:

- [ ] Firebase project created
- [ ] google-services.json downloaded and placed correctly
- [ ] Gradle sync successful
- [ ] App builds without errors
- [ ] App runs on device/emulator
- [ ] Can register new account
- [ ] Can login with credentials
- [ ] Can logout successfully
- [ ] Session persists after app restart
- [ ] All error messages work
- [ ] User appears in Firebase Console
- [ ] User data in Firestore
- [ ] Screenshots captured

---

## 🆘 TROUBLESHOOTING

### Problem: "google-services.json not found"
**Solution:**
```
1. Check file location: D:\AndriodStudioAPK\app\google-services.json
2. File must be in app/ folder (NOT root folder)
3. File name must be exactly: google-services.json
4. Sync Gradle: File → Sync Project with Gradle Files
```

### Problem: "Unresolved reference: FirebaseAuth"
**Solution:**
```
1. Verify internet connection
2. File → Sync Project with Gradle Files
3. Wait 2-3 minutes for Firebase SDK download
4. Build → Rebuild Project
5. If still fails, restart Android Studio
```

### Problem: "Package name doesn't match"
**Solution:**
```
1. Check Firebase Console → Project Settings → Your apps
2. Package name should be: com.example.gamearena
3. Check app/build.gradle.kts:
   applicationId = "com.example.gamearena"
4. They must match exactly
5. If wrong, download new google-services.json with correct package
```

### Problem: "Authentication failed"
**Solution:**
```
1. Firebase Console → Authentication → Sign-in method
2. Verify Email/Password is Enabled
3. Check internet connection on device
4. Try registering a new user
5. Check Firebase Console logs for errors
```

### Problem: App crashes on login
**Solution:**
```
1. Check Logcat in Android Studio for error stack trace
2. Common causes:
   - google-services.json is placeholder (not real file)
   - Firebase not properly initialized
   - Network connectivity issues
3. Clean and rebuild: Build → Clean → Rebuild
4. Uninstall app from device and reinstall
```

### Problem: Session not persisting
**Solution:**
```
1. Check DataStore is working:
   - Look for app data in device settings
2. Verify UserPreferences.saveUserSession() is called
3. Check Logcat for DataStore errors
4. Clear app data and test again
```

---

## 📱 Expected App Behavior

### On First Launch:
- Shows Login Screen
- No user logged in
- Clean slate

### After Registration:
- Creates Firebase account
- Saves to Firestore
- Saves session locally
- Navigates to Home

### After Login:
- Authenticates with Firebase
- Saves session locally
- Navigates to Home

### After Logout:
- Signs out from Firebase
- Clears local session
- Returns to Login

### On App Restart (while logged in):
- Checks DataStore
- Finds active session
- Skips Login
- Goes to Home

---

## 🎯 Performance Metrics

| Action | Expected Time |
|--------|---------------|
| Registration | 2-3 seconds |
| Login | 1-2 seconds |
| Logout | Instant |
| Session check | < 100ms |
| First app launch | 2-3 seconds |

---

## 🎉 CONGRATULATIONS!

If you've completed all steps successfully, you now have:

✅ A fully functional Firebase Authentication system  
✅ User registration with validation  
✅ User login with session management  
✅ Logout with confirmation  
✅ Session persistence across app restarts  
✅ Professional error handling  
✅ Production-ready authentication flows  

---

## 📤 NEXT: Upload to GitHub

Follow the guide in **GITHUB_UPLOAD_GUIDE_LAB5.md** to:
1. Commit all changes
2. Push to GitHub
3. Verify upload
4. Submit lab

---

**Your Lab 5 is complete and working! 🔥🎊**
