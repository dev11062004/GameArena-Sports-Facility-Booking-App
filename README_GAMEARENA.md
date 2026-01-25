# GameArena - Sports Facility Booking App

## Overview
GameArena is a complete Android application for booking sports facilities including cricket grounds, pool tables, and pickleball courts. The app provides real-time availability checking, instant booking, and booking history management.

## Features Implemented

### ✅ Core Features
1. **Facility Browsing**
   - View all available sports facilities
   - Filter by facility type (Cricket, Pool, Pickleball)
   - See facility details, pricing, and descriptions

2. **Real-Time Booking**
   - Check availability for specific dates
   - View time slots in 1-hour intervals (6 AM - 10 PM)
   - Book available time slots instantly
   - Automatic conflict detection

3. **Booking Management**
   - View booking history
   - Cancel upcoming bookings
   - See booking status (Confirmed/Cancelled/Completed)
   - Track total prices and booking details

4. **User Experience**
   - Modern Material 3 design
   - Bottom navigation for easy access
   - Responsive UI with loading states
   - Error handling and user feedback

## Technical Stack

### Architecture
- **MVVM Pattern**: Separation of concerns with ViewModels
- **Repository Pattern**: Data abstraction layer
- **Navigation Component**: Multi-screen navigation
- **StateFlow**: Reactive state management

### Technologies
- **Kotlin**: Primary language
- **Jetpack Compose**: Modern UI framework
- **Room Database**: Local data persistence
- **Coroutines & Flow**: Asynchronous operations
- **Material 3**: Modern design system
- **Navigation Compose**: Screen navigation

## Project Structure

```
app/src/main/java/com/example/helloworldapk/
├── data/
│   ├── Facility.kt                 # Facility entity
│   ├── Booking.kt                  # Booking entity with status
│   ├── FacilityType.kt             # Enum for facility types
│   ├── TimeSlot.kt                 # Time slot data class
│   ├── BookingWithFacility.kt      # Combined data class
│   ├── FacilityDao.kt              # Room DAO for facilities
│   ├── BookingDao.kt               # Room DAO for bookings
│   ├── Converters.kt               # Type converters for Room
│   ├── AppDatabase.kt              # Room database with prepopulated data
│   └── repository/
│       ├── FacilityRepository.kt   # Facility data operations
│       └── BookingRepository.kt    # Booking logic & validation
├── ui/
│   ├── navigation/
│   │   └── Navigation.kt           # Navigation routes
│   ├── screens/
│   │   ├── HomeScreen.kt           # Facility list with filters
│   │   ├── FacilityDetailScreen.kt # Booking interface
│   │   └── BookingHistoryScreen.kt # Booking history
│   ├── viewmodel/
│   │   ├── FacilitiesViewModel.kt  # Home screen state
│   │   ├── BookingViewModel.kt     # Booking flow state
│   │   └── BookingHistoryViewModel.kt # History state
│   └── theme/
│       ├── Color.kt
│       ├── Theme.kt (GameArenaTheme)
│       └── Type.kt
├── utils/
│   └── UserPreferences.kt          # User ID management
└── MainActivity.kt                  # App entry point

```

## Database Schema

### Facilities Table
- **8 pre-populated facilities**:
  - 2 Cricket Grounds ($40-50/hr)
  - 3 Pool Tables ($12-15/hr)
  - 3 Pickleball Courts ($20-25/hr)

### Bookings Table
- Tracks all user bookings
- Foreign key relationship with facilities
- Status tracking (Confirmed/Cancelled/Completed)
- Automatic conflict detection

## Key Features Detail

### 1. Conflict Detection
- Checks for overlapping bookings before confirmation
- Prevents double-booking of facilities
- Real-time availability updates

### 2. Time Slot Management
- Generates 1-hour slots from 6 AM to 10 PM
- Shows availability status for each slot
- Calculates pricing based on duration

### 3. User Management
- UUID-based user identification
- Stored in SharedPreferences
- Single-device user mode (ready for multi-user expansion)

### 4. UI/UX Features
- Filter chips for quick facility type selection
- Material 3 design with dynamic colors
- Bottom navigation for main sections
- Card-based layouts for easy scanning
- Status badges for bookings
- Icon representation for facility types

## How to Build & Run

### Prerequisites
1. Android Studio (latest version)
2. JDK 11 or higher
3. Android SDK 24+ (minSdk)

### Build Steps
1. Open project in Android Studio
2. Sync Gradle dependencies
3. Wait for Room annotation processing (KSP)
4. Run on emulator or physical device (Android 7.0+)

### First Build
```bash
# In Android Studio Terminal
./gradlew clean build
./gradlew installDebug
```

## Sample Data
The app comes pre-populated with 8 facilities:
- **Cricket Ground A** ($50/hr) - Premium with lighting
- **Cricket Ground B** ($40/hr) - Standard practice ground
- **Pool Table 1** ($15/hr) - Professional grade
- **Pool Table 2** ($15/hr) - Standard with seating
- **Pool Table 3** ($12/hr) - Budget friendly
- **Pickleball Court 1** ($25/hr) - Indoor premium
- **Pickleball Court 2** ($25/hr) - Indoor excellent lighting
- **Outdoor Pickleball Court** ($20/hr) - Outdoor with cover

## Future Enhancements
- Payment gateway integration
- Push notifications for booking reminders
- Photo gallery for facilities
- Rating and review system
- Backend API integration for multi-device sync
- User authentication (Firebase/OAuth)
- Facility availability calendar view
- Search functionality
- Facility owners dashboard

## Dependencies Added
```toml
# Navigation
androidx.navigation:navigation-compose:2.7.7

# Room Database
androidx.room:room-runtime:2.6.1
androidx.room:room-ktx:2.6.1
androidx.room:room-compiler:2.6.1 (KSP)

# ViewModel
androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1

# Material Icons Extended
androidx.compose.material:material-icons-extended:1.6.0

# KSP Plugin
com.google.devtools.ksp:2.0.21-1.0.27
```

## App Permissions
Currently no special permissions required. The app uses:
- Local database storage
- SharedPreferences for user data

## Testing
- Unit tests ready for ViewModels and Repositories
- UI tests ready for Compose screens
- Room DAO tests available

## Notes
- The app uses local-only storage (no internet required)
- User data is tied to device (UUID-based)
- All bookings are stored locally
- Ready for backend integration

---

**Built with ❤️ for sports enthusiasts**
**Version**: 1.0
**Min SDK**: 24 (Android 7.0)
**Target SDK**: 36 (Android 14+)
