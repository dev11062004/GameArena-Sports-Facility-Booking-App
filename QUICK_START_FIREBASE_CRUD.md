# ⚡ QUICK START - Firebase CRUD Bookings

**Get up and running in 10 minutes!**

---

## 🚀 STEP 1: Enable Firestore (2 minutes)

1. Open [Firebase Console](https://console.firebase.google.com/)
2. Select **GameArena** project
3. Click **Firestore Database** (sidebar)
4. Click **Create Database**
5. Choose **Test mode**
6. Click **Enable**

✅ **Done! Firestore is ready**

---

## 🔧 STEP 2: Sync & Build (3 minutes)

```bash
# Open Android Studio
# Wait for Gradle sync
# Click: File → Sync Project with Gradle Files
```

✅ **Build successful!**

---

## 📱 STEP 3: Run the App (2 minutes)

1. Connect Android device via USB (or start emulator)
2. Click **Run** (green triangle)
3. Wait for app to install

✅ **App running!**

---

## 🎯 STEP 4: Access Firebase Bookings (1 minute)

1. **Login** to the app
2. On **Home Screen**, look at top-right
3. Click **☁️ Cloud icon** (neon yellow)
4. You're now on **Firebase Bookings List**!

---

## ✏️ STEP 5: Test CRUD (2 minutes)

### CREATE:
1. Tap **+ button** (bottom-right)
2. Fill form:
   - Facility: "Cricket Ground A"
   - Location: "Downtown"
   - Date: "2026-02-10"
   - Time: "10:00 AM - 11:00 AM"
   - Price: "50"
3. Tap **Create Booking**
4. ✅ **Booking created!**

### READ:
- See your booking in the list
- Opens Firebase Console → Firestore
- See the `bookings` collection

### UPDATE:
1. Tap **Edit icon** (pencil)
2. Change any field
3. Tap **Update Booking**
4. ✅ **Booking updated!**

### DELETE:
1. Tap **Delete icon** (trash)
2. Confirm deletion
3. ✅ **Booking deleted!**

---

## 🎉 YOU'RE DONE!

**All CRUD operations working!**

### What to do next:

1. **Test more bookings** - Add, edit, delete multiple
2. **Take screenshots** - All screens + Firebase Console
3. **Create lab report** - Include screenshots
4. **Upload to GitHub** - See `LAB7_CRUD_COMPLETE_GUIDE.md`

---

## 🆘 Quick Troubleshooting

**Cloud icon missing?**
- Rebuild the app
- Check HomeScreen.kt has the icon

**Bookings not loading?**
- Check internet connection
- Verify Firestore is enabled
- Check you're logged in

**Build error?**
- Sync Gradle: File → Sync Project with Gradle Files
- Clean build: Build → Clean Project

---

## 📚 Full Documentation

See **LAB7_CRUD_COMPLETE_GUIDE.md** for:
- Complete implementation details
- Database schema
- Code snippets
- Detailed testing procedures
- Troubleshooting guide
- GitHub upload instructions

---

**Happy Coding! 🚀**
