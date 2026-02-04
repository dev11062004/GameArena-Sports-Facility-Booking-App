# 🔥 LAB 5: Firebase Authentication Implementation Guide

## 📋 Complete Step-by-Step Implementation

---

## 🎯 Lab Objectives
✅ Create Login & Registration UI  
✅ Integrate Firebase Authentication  
✅ Implement input validation  
✅ Add session management  
✅ Implement logout functionality  
✅ Add app logo  
✅ Upload to GitHub  

---

## 📱 PART 1: Firebase Setup (Detailed Steps)

### Step 1: Create Firebase Project

1. **Go to Firebase Console:**
   - Visit: https://console.firebase.google.com/
   - Sign in with your Google account

2. **Create New Project:**
   ```
   Click "Add project" or "Create a project"
   
   Step 1: Enter project name: "GameArena"
   Click Continue
   
   Step 2: Google Analytics (Optional)
   Toggle OFF (or keep ON if you want analytics)
   Click "Create project"
   
   Wait 30-60 seconds for project creation
   Click "Continue"
   ```

3. **You're now in Firebase Console Dashboard**

---

### Step 2: Add Android App to Firebase

1. **Click Android Icon (or "Add app" → Android)**

2. **Register App:**
   ```
   Android package name: com.example.gamearena
   (This MUST match your app's applicationId in build.gradle.kts)
   
   App nickname (optional): GameArena
   
   Debug signing certificate SHA-1 (optional - skip for now)
   
   Click "Register app"
   ```

3. **Download google-services.json:**
   ```
   Click "Download google-services.json"
   
   Save the file
   ```

4. **Add google-services.json to Your Project:**
   ```
   Location: D:\AndriodStudioAPK\app\
   
   Copy the downloaded file to:
   D:\AndriodStudioAPK\app\google-services.json
   
   ✅ File should be in app folder (NOT root folder)
   ```

5. **Click "Next" then "Continue to console"**

---

### Step 3: Enable Email/Password Authentication

1. **In Firebase Console, go to:**
   ```
   Left sidebar → Build → Authentication
   ```

2. **Get Started (if first time):**
   ```
   Click "Get started"
   ```

3. **Enable Email/Password:**
   ```
   Go to "Sign-in method" tab
   
   Click "Email/Password"
   
   Toggle ON "Email/Password" (first option)
   
   Toggle OFF "Email link (passwordless sign-in)" (leave disabled)
   
   Click "Save"
   ```

4. **Verification:**
   ```
   You should see:
   ✅ Email/Password - Enabled
   ```

---

### Step 4: Add Firebase Dependencies

This will be done in the code files (next section).

**Files to modify:**
1. `build.gradle.kts` (Project level)
2. `gradle/libs.versions.toml`
3. `app/build.gradle.kts` (App level)

---

## 🛠️ PART 2: Code Implementation

### Files to Create/Modify:
1. ✅ AuthViewModel.kt (New - handles authentication logic)
2. ✅ AuthRepository.kt (New - Firebase operations)
3. ✅ UserPreferences.kt (Update - session management)
4. ✅ LoginScreen.kt (Update - connect to Firebase)
5. ✅ RegistrationScreen.kt (Update - connect to Firebase)
6. ✅ HomeScreen.kt (Update - add logout)
7. ✅ ProfileScreen.kt (Update - add logout button)
8. ✅ MainActivity.kt (Update - check login state)
9. ✅ Add app logo/icon

---

## 📱 PART 3: Expected Outcomes

### What You'll Have After Implementation:

#### 1. **Registration Flow**
```
Registration Screen
    ↓
Enter: Name, Email, Password, Confirm Password
    ↓
Click "Register"
    ↓
Firebase creates account
    ↓
Navigate to Home Screen
```

#### 2. **Login Flow**
```
Login Screen
    ↓
Enter: Email, Password
    ↓
Click "Log In"
    ↓
Firebase authenticates
    ↓
Save session locally
    ↓
Navigate to Home Screen
```

#### 3. **Session Management**
```
App Launch
    ↓
Check if user logged in
    ↓
If YES → Go to Home Screen
If NO → Go to Login Screen
```

#### 4. **Logout Flow**
```
Home/Profile Screen
    ↓
Click "Logout"
    ↓
Firebase signs out
    ↓
Clear local session
    ↓
Navigate to Login Screen
```

---

## ✅ PART 4: Validation Rules

### Registration Validation:
- ✅ Full Name: Not empty, min 2 characters
- ✅ Email: Valid email format (contains @)
- ✅ Password: Minimum 6 characters
- ✅ Confirm Password: Must match password
- ✅ All fields required

### Login Validation:
- ✅ Email: Valid email format
- ✅ Password: Not empty
- ✅ Show error messages for invalid credentials

---

## 🎨 PART 5: UI Features

### Login Screen Features:
- ✅ Email input field with icon
- ✅ Password input field with show/hide toggle
- ✅ Login button (disabled if invalid)
- ✅ "Create Account" link → Registration
- ✅ Error message display
- ✅ Loading indicator during authentication

### Registration Screen Features:
- ✅ Full name input
- ✅ Email input
- ✅ Password input with visibility toggle
- ✅ Confirm password input
- ✅ Terms acceptance checkbox
- ✅ Register button
- ✅ Back navigation
- ✅ Error/success messages

### Home Screen Logout:
- ✅ Logout button in Profile section
- ✅ Confirmation dialog before logout
- ✅ Clear all user data on logout

---

## 🔐 PART 6: Error Handling

### Firebase Errors Handled:
```kotlin
- USER_NOT_FOUND → "No account found with this email"
- WRONG_PASSWORD → "Incorrect password"
- EMAIL_ALREADY_IN_USE → "Email already registered"
- WEAK_PASSWORD → "Password should be at least 6 characters"
- INVALID_EMAIL → "Please enter a valid email"
- NETWORK_ERROR → "Check your internet connection"
```

---

## 📊 PART 7: Testing Checklist

After implementation, test:

### Registration Testing:
- [ ] Register with valid data → Success
- [ ] Register with existing email → Error
- [ ] Register with weak password (<6 chars) → Error
- [ ] Register with mismatched passwords → Error
- [ ] Register with invalid email → Error
- [ ] Register without accepting terms → Error

### Login Testing:
- [ ] Login with valid credentials → Success
- [ ] Login with wrong password → Error
- [ ] Login with non-existent email → Error
- [ ] Login with invalid email format → Error
- [ ] Login without internet → Error

### Session Testing:
- [ ] Close and reopen app → Should stay logged in
- [ ] Logout → Should go to login screen
- [ ] Logout and reopen app → Should show login screen

---

## 🚀 PART 8: GitHub Upload

After implementation, upload changes:

```bash
cd D:\AndriodStudioAPK
git add .
git commit -m "Lab 5: Firebase Authentication Implementation - Login, Registration, Logout"
git push origin main
```

---

## 📝 PART 9: Implementation Timeline

**Total Time: ~2 hours**

| Step | Task | Time |
|------|------|------|
| 1 | Firebase setup | 15 min |
| 2 | Add dependencies | 5 min |
| 3 | Create AuthViewModel & Repository | 20 min |
| 4 | Update LoginScreen | 15 min |
| 5 | Update RegistrationScreen | 15 min |
| 6 | Add logout functionality | 10 min |
| 7 | Add app logo | 10 min |
| 8 | Testing | 20 min |
| 9 | Upload to GitHub | 10 min |

---

## ✅ Success Criteria

Your app will be complete when:

✅ Users can register with email/password  
✅ Users can login with credentials  
✅ Invalid inputs show error messages  
✅ Sessions persist across app restarts  
✅ Users can logout successfully  
✅ App has a custom logo  
✅ All changes pushed to GitHub  
✅ Firebase Console shows registered users  

---

**Ready to implement? Let's start with the code!**
