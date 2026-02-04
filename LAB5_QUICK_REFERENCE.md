# 🎯 LAB 5 QUICK REFERENCE CARD

**Print this or keep it open while working!**

---

## 🔥 FIREBASE SETUP (15 min)

```
1. console.firebase.google.com → Create "GameArena"
2. Add Android app → Package: com.example.gamearena
3. Download google-services.json
4. Place in: D:\AndriodStudioAPK\app\
5. Authentication → Enable Email/Password
6. Android Studio → Sync Gradle
```

---

## 🚀 RUN APP (5 min)

```
1. Open Android Studio
2. File → Sync Project with Gradle Files
3. Build → Rebuild Project
4. Connect device OR start emulator
5. Shift+F10 (Run)
```

---

## 🧪 TEST FLOWS

### Registration:
```
Name: John Doe
Email: test@gamearena.com
Password: password123
Confirm: password123
Terms: ✓
Click: Sign Up
Result: → Home Screen
```

### Login:
```
Email: test@gamearena.com
Password: password123
Click: Log In
Result: → Home Screen
```

### Logout:
```
Home → Click logout icon (top-right)
Confirm: Logout
Result: → Login Screen
```

### Session:
```
Close app → Reopen
Result: → Home Screen (auto-login)
```

---

## ✅ SUCCESS INDICATORS

| Check | Expected |
|-------|----------|
| Build | "BUILD SUCCESSFUL" |
| Registration | User in Firebase Console |
| Login | Navigate to Home |
| Logout | Return to Login |
| Session | Auto-login on restart |
| Errors | User-friendly messages |

---

## 📁 FILES CREATED

**Backend:**
- `AuthRepository.kt`
- `AuthViewModel.kt`

**Updated:**
- `LoginScreen.kt`
- `RegistrationScreen.kt`
- `HomeScreen.kt`
- `UserPreferences.kt`
- `MainActivity.kt`

**Config:**
- `build.gradle.kts` (x2)
- `libs.versions.toml`
- `google-services.json`

---

## 📚 DOCUMENTATION

| File | Purpose |
|------|---------|
| LAB5_FIREBASE_GUIDE.md | Detailed Firebase setup |
| QUICK_START_LAB5.md | 15-min quick start |
| STEP_BY_STEP_RUN_GUIDE.md | Complete walkthrough |
| LAB5_IMPLEMENTATION_COMPLETE.md | Technical details |
| LAB5_COMPLETE_SUMMARY.md | Executive summary |
| GITHUB_UPLOAD_GUIDE_LAB5.md | Git upload guide |
| README_LAB5.md | Complete package info |

---

## 🐛 QUICK TROUBLESHOOTING

| Problem | Solution |
|---------|----------|
| "google-services.json not found" | Place in `app/` folder, sync Gradle |
| "Unresolved reference: Firebase" | Sync Gradle, wait for download |
| "Package name doesn't match" | Verify `com.example.gamearena` |
| "Authentication failed" | Enable Email/Password in Firebase |
| App crashes | Check google-services.json is real file |

---

## 📤 GIT UPLOAD

```bash
cd D:\AndriodStudioAPK
git add .
git commit -F COMMIT_MESSAGE_LAB5.md
git push origin main
```

---

## 🎯 VALIDATION

### Registration:
- [ ] Min 6 char password
- [ ] Email format valid
- [ ] Passwords match
- [ ] Terms accepted
- [ ] Name not empty

### Login:
- [ ] Email format valid
- [ ] Password not empty
- [ ] Firebase auth
- [ ] Session saved

### Logout:
- [ ] Confirmation shown
- [ ] Firebase signout
- [ ] Session cleared
- [ ] Navigate to Login

---

## 📸 SCREENSHOTS NEEDED

1. Login Screen
2. Registration Screen
3. Home Screen (with logout)
4. Logout Dialog
5. Firebase Console - Auth
6. Firebase Console - Users
7. Firestore - users collection
8. Flow demo video

---

## 🔐 SECURITY CHECKLIST

- [ ] Passwords hidden
- [ ] Min 6 characters
- [ ] Email validated
- [ ] Firebase Auth used
- [ ] DataStore encrypted
- [ ] No hardcoded creds
- [ ] Error msgs safe

---

## 📊 FIREBASE CONSOLE CHECK

**Authentication → Users:**
- User email listed ✓
- UID present ✓
- Created timestamp ✓

**Firestore → users:**
- Collection exists ✓
- User document ✓
- Fields: name, email, createdAt ✓

---

## ⚡ KEYBOARD SHORTCUTS

| Action | Shortcut |
|--------|----------|
| Run app | Shift+F10 |
| Sync Gradle | Ctrl+Shift+O |
| Rebuild | Ctrl+Shift+F9 |
| Stop app | Ctrl+F2 |
| Find | Ctrl+F |
| Replace | Ctrl+R |

---

## 🎓 LAB OBJECTIVES

- [✅] Login screen
- [✅] Registration screen
- [✅] Input validation
- [✅] Firebase integration
- [✅] Session management
- [✅] Logout functionality
- [✅] Documentation
- [✅] GitHub upload

---

## 📞 QUICK LINKS

**Firebase Console:**
https://console.firebase.google.com/

**Firebase Docs:**
https://firebase.google.com/docs/auth

**Android Docs:**
https://developer.android.com/

---

## 💾 BACKUP COMMANDS

```bash
# Save current state
git add .
git commit -m "Backup before testing"

# If something breaks
git reset --hard HEAD

# Check what changed
git status
git diff
```

---

## 🎉 COMPLETION CHECKLIST

- [ ] Firebase project created
- [ ] google-services.json downloaded
- [ ] Authentication enabled
- [ ] App builds successfully
- [ ] Registration works
- [ ] Login works
- [ ] Logout works
- [ ] Session persists
- [ ] All errors handled
- [ ] Screenshots captured
- [ ] Code uploaded to GitHub
- [ ] Lab report written

---

## 🌟 TIPS

✅ Test on real device for best experience  
✅ Check Firebase Console after each registration  
✅ Clear app data if testing multiple accounts  
✅ Take screenshots as you go  
✅ Read error messages carefully  
✅ Save your work frequently  
✅ Test logout before closing app  
✅ Verify session persistence  

---

## 🚨 EMERGENCY CONTACTS

**Build fails?**
→ Clean project, sync Gradle, rebuild

**Auth fails?**
→ Check Firebase Console settings

**Session fails?**
→ Clear app data, reinstall

**Confused?**
→ Read STEP_BY_STEP_RUN_GUIDE.md

---

**Keep this card handy while implementing Lab 5!**

**You've got this! 🔥💪**
