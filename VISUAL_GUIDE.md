# GameArena App - Visual Structure & Flow

## 🎯 App Screens Overview

```
┌─────────────────────────────────────────────────┐
│           GameArena (Top Bar)                   │
├─────────────────────────────────────────────────┤
│                                                 │
│  [All] [Cricket] [Pool] [Pickleball]  ← Filters│
│                                                 │
│  ┌───────────────────────────────────────────┐ │
│  │ 🏏 Cricket Ground A            $50/hr   →│ │
│  │ Premium cricket ground with lighting      │ │
│  └───────────────────────────────────────────┘ │
│                                                 │
│  ┌───────────────────────────────────────────┐ │
│  │ 🎱 Pool Table 1                $15/hr   →│ │
│  │ Professional-grade pool table             │ │
│  └───────────────────────────────────────────┘ │
│                                                 │
│  ┌───────────────────────────────────────────┐ │
│  │ 🎾 Pickleball Court 1          $25/hr   →│ │
│  │ Indoor pickleball court                   │ │
│  └───────────────────────────────────────────┘ │
│                                                 │
├─────────────────────────────────────────────────┤
│     [🏠 Home]         [📜 Bookings]            │
└─────────────────────────────────────────────────┘
```

## 🏗️ When User Taps a Facility

```
┌─────────────────────────────────────────────────┐
│  ← Cricket Ground A                             │
├─────────────────────────────────────────────────┤
│  ┌─────────────────────────────────────────┐   │
│  │ 🏏 Cricket Ground A                      │   │
│  │ $50/hour                                 │   │
│  │ Premium cricket ground with lighting     │   │
│  └─────────────────────────────────────────┘   │
│                                                 │
│  Select Date:                                   │
│  ┌─────────────────────────────────────────┐   │
│  │ ✓ Fri, Jan 25  ← Selected                │   │
│  │   Sat, Jan 26                            │   │
│  │   Sun, Jan 27                            │   │
│  └─────────────────────────────────────────┘   │
│                                                 │
│  Available Time Slots:                          │
│  ┌──────┐ ┌──────┐ ┌──────┐                   │
│  │06:00 │ │07:00 │ │08:00 │                   │
│  └──────┘ └──────┘ └──────┘                   │
│  ┌──────┐ ┌──────┐ ┌──────┐                   │
│  │09:00 │ │10:00✓│ │11:00 │ ← Selected        │
│  └──────┘ └──────┘ └──────┘                   │
│  ┌──────┐ ┌──────┐ ┌──────┐                   │
│  │12:00 │ │XXXXX │ │14:00 │ ← Unavailable     │
│  └──────┘ └──────┘ └──────┘                   │
│                                                 │
│  ┌─────────────────────────────────────────┐   │
│  │        Book Now - $50.00                 │   │
│  └─────────────────────────────────────────┘   │
└─────────────────────────────────────────────────┘
```

## 📋 Bookings History Screen

```
┌─────────────────────────────────────────────────┐
│           My Bookings (Top Bar)                 │
├─────────────────────────────────────────────────┤
│                                                 │
│  ┌───────────────────────────────────────────┐ │
│  │ Booking #1              [Confirmed] ←Status│ │
│  │ 📅 Jan 25, 2026                           │ │
│  │ 🕐 10:00 - 11:00                          │ │
│  │ 💰 $50.00                                 │ │
│  │ ┌─────────────────────────────────────┐   │ │
│  │ │    🚫 Cancel Booking                 │   │ │
│  │ └─────────────────────────────────────┘   │ │
│  └───────────────────────────────────────────┘ │
│                                                 │
│  ┌───────────────────────────────────────────┐ │
│  │ Booking #2              [Completed]        │ │
│  │ 📅 Jan 20, 2026                           │ │
│  │ 🕐 14:00 - 15:00                          │ │
│  │ 💰 $15.00                                 │ │
│  └───────────────────────────────────────────┘ │
│                                                 │
│  ┌───────────────────────────────────────────┐ │
│  │ Booking #3              [Cancelled]        │ │
│  │ 📅 Jan 18, 2026                           │ │
│  │ 🕐 16:00 - 17:00                          │ │
│  │ 💰 $25.00                                 │ │
│  └───────────────────────────────────────────┘ │
│                                                 │
├─────────────────────────────────────────────────┤
│     [🏠 Home]         [📜 Bookings]            │
└─────────────────────────────────────────────────┘
```

## 🔄 Data Flow Diagram

```
┌──────────────────────────────────────────────────────────┐
│                      USER ACTIONS                         │
└───────────────────────┬──────────────────────────────────┘
                        │
                        ↓
┌──────────────────────────────────────────────────────────┐
│                   COMPOSE UI LAYER                        │
│  ┌──────────────┐  ┌──────────────┐  ┌───────────────┐  │
│  │  HomeScreen  │  │ DetailScreen │  │ HistoryScreen │  │
│  └──────────────┘  └──────────────┘  └───────────────┘  │
└───────────────────────┬──────────────────────────────────┘
                        │
                        ↓
┌──────────────────────────────────────────────────────────┐
│                   VIEWMODEL LAYER                         │
│  ┌──────────────┐  ┌──────────────┐  ┌───────────────┐  │
│  │ Facilities   │  │   Booking    │  │ BookingHistory│  │
│  │  ViewModel   │  │  ViewModel   │  │   ViewModel   │  │
│  └──────────────┘  └──────────────┘  └───────────────┘  │
│         StateFlow ↕️  State Management ↕️ StateFlow       │
└───────────────────────┬──────────────────────────────────┘
                        │
                        ↓
┌──────────────────────────────────────────────────────────┐
│                  REPOSITORY LAYER                         │
│  ┌──────────────────┐        ┌──────────────────┐        │
│  │  Facility        │        │    Booking       │        │
│  │  Repository      │        │   Repository     │        │
│  │                  │        │  • Validation    │        │
│  │  • Get All       │        │  • Conflict Check│        │
│  │  • Get By Type   │        │  • Time Slots    │        │
│  │  • Get By ID     │        │  • Create/Cancel │        │
│  └──────────────────┘        └──────────────────┘        │
└───────────────────────┬──────────────────────────────────┘
                        │
                        ↓
┌──────────────────────────────────────────────────────────┐
│                      DAO LAYER                            │
│  ┌──────────────────┐        ┌──────────────────┐        │
│  │  FacilityDao     │        │   BookingDao     │        │
│  │  (Room)          │        │   (Room)         │        │
│  └──────────────────┘        └──────────────────┘        │
└───────────────────────┬──────────────────────────────────┘
                        │
                        ↓
┌──────────────────────────────────────────────────────────┐
│                    ROOM DATABASE                          │
│  ┌──────────────────┐        ┌──────────────────┐        │
│  │  facilities      │        │    bookings      │        │
│  │  (8 pre-loaded)  │◄───────│  (user created)  │        │
│  │                  │  FK    │                  │        │
│  │  • id            │        │  • id            │        │
│  │  • name          │        │  • facilityId    │        │
│  │  • type          │        │  • userId        │        │
│  │  • hourlyRate    │        │  • startTime     │        │
│  │  • description   │        │  • endTime       │        │
│  └──────────────────┘        │  • status        │        │
│                               │  • totalPrice    │        │
│                               └──────────────────┘        │
│                     SQLite Database                       │
└──────────────────────────────────────────────────────────┘
```

## 🎨 Component Breakdown

### HomeScreen Components
```
HomeScreen
  ├── FilterChips Row
  │     ├── All Chip
  │     ├── Cricket Chip (with icon)
  │     ├── Pool Chip (with icon)
  │     └── Pickleball Chip (with icon)
  │
  └── LazyColumn (Facility List)
        └── FacilityCard (repeated for each facility)
              ├── Icon (sport type)
              ├── Name
              ├── Description
              ├── Price
              └── Chevron (→)
```

### FacilityDetailScreen Components
```
FacilityDetailScreen
  ├── TopAppBar (with back button)
  ├── Facility Info Card
  │     ├── Sport Icon
  │     ├── Name
  │     ├── Price
  │     └── Description
  │
  ├── Date Selector (LazyColumn)
  │     └── Date Cards (next 7 days)
  │
  ├── Time Slots Grid (3 columns)
  │     └── TimeSlotChip (repeated)
  │           ├── Available (clickable)
  │           ├── Selected (highlighted)
  │           └── Unavailable (disabled)
  │
  └── Book Button (with price)
```

### BookingHistoryScreen Components
```
BookingHistoryScreen
  └── LazyColumn (Booking List)
        └── BookingCard (repeated for each booking)
              ├── Booking ID
              ├── Status Chip
              ├── Date Icon + Text
              ├── Time Icon + Text
              ├── Price Icon + Text
              └── Cancel Button (if applicable)
```

## 🔐 User Management Flow

```
App Launch
    ↓
Check SharedPreferences
    ↓
User ID exists? ──NO──→ Generate UUID
    │                        ↓
    YES                  Save to SharedPreferences
    ↓                        ↓
Use existing UUID ←──────────┘
    ↓
Pass userId to all booking operations
```

## ⚡ Booking Creation Flow

```
User taps "Book Now"
    ↓
Get facility, timeSlot, userId
    ↓
Calculate total price
    ↓
Create Booking object
    ↓
BookingRepository.createBooking()
    ↓
Check for conflicts ───→ Conflict found? ──YES──→ Return error
    │                                               │
    NO                                              ↓
    ↓                                        Show error message
Insert into database
    ↓
Return success with booking ID
    ↓
Navigate back to home
    ↓
Show success (via state)
```

## 🚀 Conflict Detection Algorithm

```
New Booking: [Start: 10:00, End: 11:00]
    ↓
Query database for same facility
    ↓
Get all CONFIRMED bookings for that day
    ↓
Check each existing booking:
    ↓
┌────────────────────────────────────────┐
│ Does new booking overlap with existing?│
│                                        │
│ Overlap if:                            │
│ • new.start < existing.end AND         │
│ • new.end > existing.start             │
└────────────────────────────────────────┘
    ↓
Overlap found? ──YES──→ REJECT booking
    │
    NO
    ↓
ALLOW booking
```

## 📊 Database Relationships

```
┌─────────────────┐
│   Facility      │
│   (Parent)      │
│                 │
│ PK: id          │
└────────┬────────┘
         │
         │ 1 to Many
         │
         ↓
┌─────────────────┐
│   Booking       │
│   (Child)       │
│                 │
│ PK: id          │
│ FK: facilityId ─┘
│     userId
│     startTime
│     endTime
└─────────────────┘
```

## 🎯 Key Business Logic

### Time Slot Generation
- Start: 6:00 AM
- End: 10:00 PM
- Interval: 60 minutes
- Total slots per day: 16

### Pricing Calculation
```
Duration (hours) = (endTime - startTime) / (1000 * 60 * 60)
Total Price = Facility.hourlyRate × Duration
```

### Booking States
- **CONFIRMED**: Active booking, can be cancelled
- **CANCELLED**: User cancelled, shown in history
- **COMPLETED**: Past booking (startTime < now)

---

**This visual guide helps understand the complete app architecture!**
