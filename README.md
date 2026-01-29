# 🏟️ GameArena - Sports Facility Booking App

<div align="center">

![Android](https://img.shields.io/badge/Platform-Android-green.svg)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)
![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-blue)
![Material 3](https://img.shields.io/badge/Design-Material%203-purple)
![Min SDK](https://img.shields.io/badge/Min%20SDK-24-orange.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

**A modern Android app for booking sports facilities with real-time availability checking**

[Features](#-features) • [Screenshots](#-screenshots) • [Tech Stack](#-tech-stack) • [Installation](#-installation) • [Architecture](#-architecture) • [Lab Docs](#-lab-documentation) • [Contributing](#-contributing)

</div>

---

## 📚 Lab Documentation

### UI Components Lab (Latest) ⭐
Complete documentation for UI Components and Layout Design lab:

- **[UI_LAB_SUBMISSION.md](UI_LAB_SUBMISSION.md)** - Complete submission package with all requirements
- **[UI_COMPONENTS_LAB.md](UI_COMPONENTS_LAB.md)** - Detailed lab documentation (800+ lines)
- **[UI_SCREENSHOTS_GUIDE.md](UI_SCREENSHOTS_GUIDE.md)** - Visual guide with ASCII diagrams (600+ lines)

**Deliverables**:
- ✅ 6 fully functional UI screens (Login, Sign-Up, Home, Detail, History, Profile)
- ✅ 7 reusable components (GameArenaTextField, GameArenaButton, etc.)
- ✅ Complete Material Design 3 implementation
- ✅ Comprehensive documentation (2,500+ lines)

### Wireframes Lab
Wireframe and UI flow documentation:

- **[WIREFRAMES_README.md](WIREFRAMES_README.md)** - Complete wireframes guide
- **[WIREFRAMES_DOCUMENTATION.md](WIREFRAMES_DOCUMENTATION.md)** - ASCII art wireframes
- **[UI_FLOW_DIAGRAMS.md](UI_FLOW_DIAGRAMS.md)** - Navigation flow diagrams
- **[INTERACTIVE_WIREFRAMES.html](INTERACTIVE_WIREFRAMES.html)** - Interactive wireframes demo

---

## 📱 About

GameArena is a comprehensive sports facility booking application that eliminates the hassle of manual booking through calls or in-person visits. Users can instantly check availability and book cricket grounds, pool tables, and pickleball courts directly from their mobile devices.

### 🎯 Problem Solved

- ❌ **Before**: Manual booking via phone calls, scheduling conflicts, lack of transparency
- ✅ **After**: Instant booking, real-time availability, automatic conflict detection, digital booking history

---

## ✨ Features

### 🔐 Authentication
- Clean login screen with email/password
- Comprehensive sign-up with validation
- Password visibility toggle
- Real-time form validation

### 🏠 Facility Browsing
- Browse all available sports facilities with detailed information
- Filter by facility type (Cricket, Pool, Pickleball)
- View hourly rates and descriptions
- Beautiful card-based UI with sport-specific icons

### 📅 Smart Booking System
- **Real-time availability** - See available time slots instantly
- **Date selector** - Book up to 7 days in advance
- **Hourly time slots** - 6 AM to 10 PM availability
- **Automatic conflict detection** - Prevents double-booking
- **Dynamic pricing** - Automatic price calculation based on duration
- **Instant confirmation** - Get immediate booking confirmation

### 📜 Booking Management
- View complete booking history
- Track booking status (Confirmed/Cancelled/Completed)
- Cancel upcoming bookings with one tap

### 👤 User Profile
- View profile information
- Manage account settings
- Access help and support
- Secure logout
- See detailed booking information (date, time, price)

### 🎨 Modern UI/UX
- Material 3 design system
- Dynamic color theming (Android 12+)
- Smooth animations and transitions
- Responsive layouts for all screen sizes
- Loading states and error handling
- Empty states with helpful messages

---

## 📸 Screenshots

<div align="center">

| Home Screen | Booking Screen | My Bookings |
|------------|----------------|-------------|
| <img src="screenshots/home.png" width="250"> | <img src="screenshots/booking.png" width="250"> | <img src="screenshots/history.png" width="250"> |

</div>

---

## 🛠️ Tech Stack

### Core Technologies
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material 3
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room (SQLite)
- **Async**: Coroutines & Flow
- **Navigation**: Navigation Compose

### Key Libraries & Components

```kotlin
// Jetpack Compose
androidx.compose.material3
androidx.compose.ui
androidx.navigation:navigation-compose

// Room Database
androidx.room:room-runtime
androidx.room:room-ktx
com.google.devtools.ksp // For annotation processing

// ViewModel & Lifecycle
androidx.lifecycle:lifecycle-viewmodel-compose
androidx.lifecycle:lifecycle-runtime-ktx

// Material Icons
androidx.compose.material:material-icons-extended
```

---

## 🏗️ Architecture

### MVVM Pattern

```
┌─────────────────────────────────────────────┐
│              UI Layer (Compose)              │
│   HomeScreen | DetailScreen | HistoryScreen  │
└────────────────────┬────────────────────────┘
                     │
                     ↓
┌─────────────────────────────────────────────┐
│           ViewModel Layer                    │
│  FacilitiesVM | BookingVM | HistoryVM       │
│         (StateFlow for state management)     │
└────────────────────┬────────────────────────┘
                     │
                     ↓
┌─────────────────────────────────────────────┐
│          Repository Layer                    │
│  FacilityRepository | BookingRepository     │
│     (Business logic & data coordination)     │
└────────────────────┬────────────────────────┘
                     │
                     ↓
┌─────────────────────────────────────────────┐
│            Data Layer (Room)                 │
│     FacilityDao | BookingDao                │
│          (SQLite Database)                   │
└─────────────────────────────────────────────┘
```

### Project Structure

```
app/src/main/java/com/example/helloworldapk/
├── data/
│   ├── Facility.kt              # Facility entity
│   ├── Booking.kt               # Booking entity
│   ├── FacilityDao.kt           # Facility data access
│   ├── BookingDao.kt            # Booking data access
│   ├── AppDatabase.kt           # Room database
│   └── repository/
│       ├── FacilityRepository.kt
│       └── BookingRepository.kt
├── ui/
│   ├── screens/
│   │   ├── HomeScreen.kt
│   │   ├── FacilityDetailScreen.kt
│   │   └── BookingHistoryScreen.kt
│   ├── viewmodel/
│   │   ├── FacilitiesViewModel.kt
│   │   ├── BookingViewModel.kt
│   │   └── BookingHistoryViewModel.kt
│   ├── navigation/
│   │   └── Navigation.kt
│   └── theme/
│       ├── Theme.kt
│       ├── Color.kt
│       └── Type.kt
├── utils/
│   └── UserPreferences.kt
└── MainActivity.kt
```

---

## 🚀 Installation

### Prerequisites
- Android Studio Hedgehog | 2023.1.1 or later
- JDK 11 or higher
- Android SDK 24+ (Android 7.0+)

### Build Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/dev11062004/GameArena-Sports-Facility-Booking-App.git
   cd GameArena-Sports-Facility-Booking-App
   ```

2. **Open in Android Studio**
   - File → Open → Select project folder
   - Wait for Gradle sync (first time may take 5-10 minutes)

3. **Run the app**
   - Click the "Run" button (green play icon)
   - Select an emulator or connected device
   - App will install and launch automatically

### Alternative: Command Line Build

```bash
# Build debug APK
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug

# Run tests
./gradlew test
```

---

## 💾 Database Schema

### Facilities Table
```kotlin
@Entity(tableName = "facilities")
data class Facility(
    @PrimaryKey val id: Long,
    val name: String,
    val type: FacilityType,      // CRICKET_GROUND, POOL_TABLE, PICKLEBALL_COURT
    val hourlyRate: Double,
    val description: String
)
```

**Pre-loaded with 8 facilities:**
- 2 Cricket Grounds ($40-50/hr)
- 3 Pool Tables ($12-15/hr)
- 3 Pickleball Courts ($20-25/hr)

### Bookings Table
```kotlin
@Entity(tableName = "bookings")
data class Booking(
    @PrimaryKey val id: Long,
    val facilityId: Long,        // Foreign key
    val userId: String,
    val startTime: Long,
    val endTime: Long,
    val status: BookingStatus,   // CONFIRMED, CANCELLED, COMPLETED
    val totalPrice: Double
)
```

---

## 🔑 Key Features Implementation

### 1. Conflict Detection Algorithm
```kotlin
// Checks for overlapping bookings before confirmation
suspend fun createBooking(booking: Booking): Result<Long> {
    val conflicts = bookingDao.getConflictingBookings(
        facilityId = booking.facilityId,
        startTime = booking.startTime,
        endTime = booking.endTime
    )
    
    if (conflicts.isNotEmpty()) {
        return Result.failure(Exception("Time slot already booked"))
    }
    
    return Result.success(bookingDao.insertBooking(booking))
}
```

### 2. Time Slot Generation
- Generates 16 hourly slots per day (6 AM - 10 PM)
- Marks each slot as available/unavailable
- Updates in real-time based on bookings

### 3. User Management
- UUID-based user identification
- Stored in SharedPreferences
- Automatic generation on first launch

---

## 📝 Usage Example

```kotlin
// 1. User browses facilities
val facilities = facilityRepository.getAllFacilities()

// 2. Selects a facility and date
val timeSlots = bookingRepository.getAvailableTimeSlots(
    facilityId = facilityId,
    date = selectedDate
)

// 3. Books an available slot
val booking = Booking(
    facilityId = facilityId,
    userId = userId,
    startTime = slot.startTime,
    endTime = slot.endTime,
    totalPrice = calculatePrice()
)

bookingRepository.createBooking(booking)
```

---

## 🎨 Customization

### Change Theme Colors
Edit `ui/theme/Color.kt`:
```kotlin
val PrimaryColor = Color(0xFF6200EE)
val SecondaryColor = Color(0xFF03DAC6)
```

### Add Custom Facilities
Edit `data/AppDatabase.kt`:
```kotlin
val facilities = listOf(
    Facility(
        name = "Your Facility",
        type = FacilityType.CRICKET_GROUND,
        hourlyRate = 100.0,
        description = "Your description"
    )
)
```

### Modify Time Slots
Edit `data/repository/BookingRepository.kt`:
```kotlin
calendar.set(Calendar.HOUR_OF_DAY, 6)  // Start time
endOfDay.set(Calendar.HOUR_OF_DAY, 22) // End time
slotDurationMinutes = 60                // Duration
```

---

## 🧪 Testing

### Run Unit Tests
```bash
./gradlew test
```

### Run Instrumentation Tests
```bash
./gradlew connectedAndroidTest
```

### Test Coverage
- ViewModels: Business logic testing
- Repositories: Data operation testing
- DAOs: Database query testing
- UI: Compose UI testing

---

## 🚀 Future Enhancements

- [ ] Payment gateway integration (Stripe/Razorpay)
- [ ] Push notifications for booking reminders
- [ ] Photo gallery for facilities
- [ ] User reviews and ratings system
- [ ] Backend API integration for multi-device sync
- [ ] Firebase Authentication
- [ ] Google Maps integration for facility locations
- [ ] Advanced search and filters
- [ ] Facility owner dashboard
- [ ] Analytics and insights

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Code Style
- Follow [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable and function names
- Add comments for complex logic
- Write unit tests for new features

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Dev**
- GitHub: [@dev11062004](https://github.com/dev11062004)

---

## 🙏 Acknowledgments

- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern UI toolkit
- [Material Design 3](https://m3.material.io/) - Design system
- [Room Database](https://developer.android.com/training/data-storage/room) - Local persistence
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - Async programming

---

## 📊 Stats

- **Lines of Code**: 2,500+
- **Number of Screens**: 3
- **Database Tables**: 2
- **Pre-loaded Facilities**: 8
- **Supported Android Versions**: 7.0+ (API 24+)

---

## 📞 Support

If you have any questions or need help with the project, please:

1. Check the [SETUP_GUIDE.md](SETUP_GUIDE.md) for detailed instructions
2. Review the [VISUAL_GUIDE.md](VISUAL_GUIDE.md) for architecture diagrams
3. Open an issue on GitHub
4. Contact via email: dev@gamearena.com

---

<div align="center">

**⭐ Star this repo if you find it helpful!**

Made with ❤️ for sports enthusiasts

[Report Bug](https://github.com/dev11062004/GameArena-Sports-Facility-Booking-App/issues) • [Request Feature](https://github.com/dev11062004/GameArena-Sports-Facility-Booking-App/issues)

</div>
