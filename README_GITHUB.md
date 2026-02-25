# 🏟️ GameArena - Sports Facility Booking App

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com/)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-brightgreen.svg)](https://developer.android.com/jetpack/compose)
[![Firebase](https://img.shields.io/badge/Backend-Firebase-orange.svg)](https://firebase.google.com/)

A modern Android application for booking sports facilities with real-time availability, built using Jetpack Compose and Firebase.

## 📱 Features

### Authentication
- ✅ User registration with email/password
- ✅ Secure login with Firebase Authentication
- ✅ Session management
- ✅ Password reset functionality

### Facility Management
- ✅ Browse available sports facilities
- ✅ View detailed facility information
- ✅ Real-time availability checking
- ✅ Search and filter facilities

### Booking System
- ✅ Book time slots for facilities
- ✅ View booking history
- ✅ Edit existing bookings
- ✅ Cancel bookings
- ✅ Firebase Firestore sync for cloud storage

### User Profile
- ✅ View and edit user information
- ✅ Logout functionality
- ✅ Booking statistics

## 🛠️ Technology Stack

- **Language**: Kotlin 2.0.21
- **UI Framework**: Jetpack Compose (Material 3)
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room (Local) + Firebase Firestore (Cloud)
- **Authentication**: Firebase Auth
- **Dependency Injection**: Manual DI with ViewModelProvider
- **Navigation**: Jetpack Navigation Compose
- **Async Operations**: Kotlin Coroutines + Flow

## 📋 Prerequisites

- Android Studio Hedgehog or later
- JDK 17 or 21 (Android Studio bundled)
- Android SDK (API 24-36)
- Firebase account and project setup

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/AndriodStudioAPK.git
cd AndriodStudioAPK
```

### 2. Firebase Setup

1. Create a Firebase project at [Firebase Console](https://console.firebase.google.com/)
2. Add an Android app to your project
   - Package name: `com.example.gamearena`
3. Download `google-services.json`
4. Place it in `app/` directory
5. Enable Firebase Authentication (Email/Password)
6. Enable Cloud Firestore

### 3. Build and Run

#### Using Android Studio:
1. Open the project in Android Studio
2. Wait for Gradle sync to complete
3. Connect your Android device or start an emulator
4. Click the Run button (▶️) or press `Shift + F10`

#### Using Command Line:
```powershell
# Build debug APK
.\gradlew assembleDebug

# Install on connected device
.\gradlew installDebug

# Or use the automated script
.\run-app.ps1
```

## 📁 Project Structure

```
app/src/main/java/com/example/gamearena/
├── data/
│   ├── models/                 # Data classes
│   │   ├── Booking.kt
│   │   ├── Facility.kt
│   │   ├── FirebaseBooking.kt
│   │   └── TimeSlot.kt
│   ├── repository/             # Data repositories
│   │   ├── AuthRepository.kt
│   │   ├── BookingRepository.kt
│   │   ├── FacilityRepository.kt
│   │   └── FirebaseBookingRepository.kt
│   ├── AppDatabase.kt          # Room database
│   └── DAOs                    # Room Data Access Objects
├── ui/
│   ├── components/             # Reusable UI components
│   │   └── CommonUi.kt
│   ├── navigation/             # Navigation graph
│   │   └── Navigation.kt
│   ├── screens/                # App screens
│   │   ├── LoginScreen.kt
│   │   ├── SignUpScreen.kt
│   │   ├── HomeScreen.kt
│   │   ├── FacilityDetailScreen.kt
│   │   ├── AddBookingScreen.kt
│   │   ├── BookingHistoryScreen.kt
│   │   └── ProfileScreen.kt
│   ├── theme/                  # Material 3 theming
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   └── viewmodel/              # ViewModels
│       ├── AuthViewModel.kt
│       ├── BookingViewModel.kt
│       ├── FacilitiesViewModel.kt
│       └── FirebaseBookingViewModel.kt
├── utils/                      # Utility classes
│   └── UserPreferences.kt
└── MainActivity.kt             # Entry point
```

## 🎨 Screenshots

*Coming soon...*

## 🔧 Configuration

### Minimum Requirements
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 36 (Android 14)
- **Compile SDK**: 36

### Dependencies
See [build.gradle.kts](app/build.gradle.kts) for full list of dependencies.

Key dependencies:
- Jetpack Compose BOM
- Firebase Auth & Firestore
- Room Database
- Kotlin Coroutines
- Navigation Compose
- Material 3

## 📖 Documentation

- [Quick Start Guide](QUICK_START_NOW.md)
- [Build Status](BUILD_STATUS_READY.md)
- [All Fixes Applied](ALL_ERRORS_FIXED_COMPLETE.md)
- [Running on USB Device](APP_IS_RUNNABLE.md)

## 🐛 Known Issues

All major issues have been resolved. See [ALL_ERRORS_FIXED_COMPLETE.md](ALL_ERRORS_FIXED_COMPLETE.md) for details.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Dev Dadhania**

## 🙏 Acknowledgments

- Firebase for backend services
- Google for Jetpack Compose
- Android community for excellent documentation

## 📞 Support

For support, email your.email@example.com or open an issue in the repository.

## 🔄 Version History

### v1.0.0 (Current)
- Initial release
- User authentication
- Facility browsing and booking
- Firebase integration
- Material 3 UI

---

**Made with ❤️ using Kotlin and Jetpack Compose**

