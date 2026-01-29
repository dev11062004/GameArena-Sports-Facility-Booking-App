# 🎨 GameArena App - Wireframes & UI Navigation Flow Documentation

**Lab Assignment**: Low-Fidelity Wireframes and UI Screen Navigation Flow  
**Project**: GameArena - Sports Facility Booking Mobile Application  
**Date**: January 26, 2026  
**Platform**: Android (Jetpack Compose)

---

## 📋 Table of Contents

1. [Introduction](#introduction)
2. [App Overview](#app-overview)
3. [Screen Inventory](#screen-inventory)
4. [Low-Fidelity Wireframes](#low-fidelity-wireframes)
5. [UI Navigation Flow](#ui-navigation-flow)
6. [Component Specifications](#component-specifications)
7. [User Journey Maps](#user-journey-maps)
8. [Design Annotations](#design-annotations)

---

## 1. Introduction

### What are Wireframes?

Wireframes act as the **blueprint** of a mobile application. They define:
- ✅ Visual structure and layout hierarchy
- ✅ Screen component placement
- ✅ User interface elements
- ✅ Content organization
- ✅ User experience flow

**Purpose**: Wireframes focus on **functionality and structure**, not styling or colors.

### What is UI Flow?

A UI flow (user flow diagram) visually represents **how users navigate through the app**, ensuring smooth and logical navigation between screens.

---

## 2. App Overview

### GameArena Application

**Problem Statement**:  
Booking sports facilities (cricket grounds, pool tables, pickleball courts) is often manual, leading to scheduling conflicts and wasted time.

**Solution**:  
GameArena provides a centralized digital platform for instant facility booking with real-time availability.

### Key Features

1. **Browse Facilities** - View all available sports facilities with filtering
2. **Check Availability** - See real-time time slot availability
3. **Book Instantly** - Reserve facilities with date/time selection
4. **Manage Bookings** - View booking history and cancel reservations
5. **Filter by Sport** - Quick filtering by facility type

### Technical Stack

- **Frontend**: Jetpack Compose (Android)
- **Database**: Room (SQLite)
- **Architecture**: MVVM (Model-View-ViewModel)
- **Navigation**: Jetpack Navigation Component

---

## 3. Screen Inventory

### Total Screens: 3 Main Screens

| # | Screen Name | Purpose | Navigation Type |
|---|-------------|---------|-----------------|
| 1 | **Home Screen** | Browse and filter facilities | Bottom Tab |
| 2 | **Facility Detail Screen** | View details, select date/time, book | Modal/Stack |
| 3 | **Booking History Screen** | View and manage bookings | Bottom Tab |

### Additional UI Components

- **Top App Bar** - Context-aware title
- **Bottom Navigation Bar** - Home & Bookings tabs
- **Filter Chips** - Sport type filtering
- **Date Selector** - Horizontal scrollable dates
- **Time Slot Grid** - Available time slots
- **Booking Cards** - Facility and booking information

---

## 4. Low-Fidelity Wireframes

### 4.1 Home Screen (Main Landing)

```
┌─────────────────────────────────────────┐
│  ┌─────────────────────────────────┐   │
│  │         GameArena    [≡]        │   │  ← Top App Bar
│  └─────────────────────────────────┘   │
│                                         │
│  ┌───────────────────────────────────┐ │
│  │ [All] [Cricket] [Pool] [Pickleball]│ │  ← Filter Chips
│  └───────────────────────────────────┘ │
│                                         │
│  ┌─────────────────────────────────┐   │
│  │ ┌────┐  Cricket Ground A        │   │
│  │ │ 🏏 │  Professional cricket...  │   │  ← Facility Card 1
│  │ └────┘  $50.00/hour      [→]    │   │
│  └─────────────────────────────────┘   │
│                                         │
│  ┌─────────────────────────────────┐   │
│  │ ┌────┐  Cricket Ground B        │   │
│  │ │ 🏏 │  Standard cricket...      │   │  ← Facility Card 2
│  │ └────┘  $40.00/hour      [→]    │   │
│  └─────────────────────────────────┘   │
│                                         │
│  ┌─────────────────────────────────┐   │
│  │ ┌────┐  Pool Table 1            │   │
│  │ │ 🎱 │  Professional pool...     │   │  ← Facility Card 3
│  │ └────┘  $15.00/hour      [→]    │   │
│  └─────────────────────────────────┘   │
│                                         │
│  ┌─────────────────────────────────┐   │
│  │ ┌────┐  Pickleball Court 1      │   │
│  │ │ 🎾 │  Indoor pickleball...     │   │  ← Facility Card 4
│  │ └────┘  $25.00/hour      [→]    │   │
│  └─────────────────────────────────┘   │
│              [... more ...]             │
│                                         │
│  ┌─────────────────────────────────┐   │
│  │  [🏠 Home]  [📜 Bookings]       │   │  ← Bottom Nav
│  └─────────────────────────────────┘   │
└─────────────────────────────────────────┘
```

#### Components Breakdown

**1. Top App Bar**
- Title: "GameArena"
- Background: Primary container color
- Height: 64dp

**2. Filter Chip Row**
- Horizontal scrollable row
- Chips: All, Cricket, Pool, Pickleball
- Selected state: Filled background
- Unselected: Outlined

**3. Facility Card**
- Icon (64x64dp) - Sport specific
- Facility Name (Title Medium, Bold)
- Description (Body Small, 2 lines max)
- Price (Title Medium, Primary color)
- Clickable area
- Elevation: 2dp

**4. Bottom Navigation**
- 2 items: Home, Bookings
- Icons: House, History
- Selected indicator
- Label below icon

---

### 4.2 Facility Detail Screen (Booking Flow)

```
┌─────────────────────────────────────────┐
│  ┌─────────────────────────────────┐   │
│  │ [←] Cricket Ground A            │   │  ← Top Bar + Back
│  └─────────────────────────────────┘   │
│                                         │
│  ┌─────────────────────────────────┐   │
│  │  ┌────┐                          │   │
│  │  │ 🏏 │  Cricket Ground A        │   │
│  │  │    │  $50.00/hour             │   │  ← Facility Info Card
│  │  └────┘                          │   │
│  │                                  │   │
│  │  Professional cricket ground     │   │
│  │  with quality pitch...           │   │
│  └─────────────────────────────────┘   │
│                                         │
│  ┌─────────────────────────────────┐   │
│  │  📅 Select Date                  │   │  ← Section Header
│  └─────────────────────────────────┘   │
│                                         │
│  ┌───────────────────────────────────┐ │
│  │ [Jan 26][Jan 27][Jan 28][Jan 29]→│ │  ← Date Chips (Horizontal)
│  └───────────────────────────────────┘ │
│                                         │
│  ┌─────────────────────────────────┐   │
│  │  ⏰ Available Time Slots         │   │  ← Section Header
│  └─────────────────────────────────┘   │
│                                         │
│  ┌───────────────────────────────────┐ │
│  │ [06:00] [07:00] [08:00] [09:00] │ │
│  │                                   │ │
│  │ [10:00] [11:00] [12:00] [13:00] │ │  ← Time Slot Grid
│  │                                   │ │     (3 columns)
│  │ [14:00] [15:00] [16:00] [17:00] │ │
│  │                                   │ │
│  │ [18:00] [19:00] [20:00] [21:00] │ │
│  └───────────────────────────────────┘ │
│                                         │
│   Green = Available                    │
│   Gray = Booked                        │  ← Legend
│   Blue = Selected                      │
│                                         │
│  ┌─────────────────────────────────┐   │
│  │   [  Book Now - $50.00  ]       │   │  ← Booking Button
│  └─────────────────────────────────┘   │
└─────────────────────────────────────────┘
```

#### Components Breakdown

**1. Top App Bar**
- Back button (←)
- Facility name as title
- Background: Primary

**2. Facility Information Card**
- Large icon (48x48dp)
- Facility name (Headline Small)
- Hourly rate (Title Medium, Primary)
- Description text (Body Medium)
- Card elevation: 4dp

**3. Date Selector**
- Section title: "📅 Select Date"
- Horizontal scrollable row
- Shows next 7 days
- Format: "Jan 26" or "Today"
- Selected state: Filled chip
- Unselected: Outlined chip

**4. Time Slot Grid**
- Section title: "⏰ Available Time Slots"
- Grid layout: 3 columns
- 1-hour slots from 6 AM to 10 PM
- Color coding:
  - Green = Available (clickable)
  - Gray = Already booked (disabled)
  - Blue = User selected (highlighted)
- Each slot: 48dp height

**5. Booking Button**
- Full-width button
- Text: "Book Now - $XX.XX"
- Calculates price dynamically
- Enabled only when date + time selected
- Background: Primary color
- Position: Fixed at bottom

---

### 4.3 Booking History Screen

```
┌─────────────────────────────────────────┐
│  ┌─────────────────────────────────┐   │
│  │       My Bookings    [≡]        │   │  ← Top App Bar
│  └─────────────────────────────────┘   │
│                                         │
│  ┌─────────────────────────────────┐   │
│  │  Booking #1234         [✓]      │   │
│  │                                  │   │
│  │  Cricket Ground A                │   │  ← Booking Card 1
│  │  📅 Jan 26, 2026                 │   │     (Confirmed)
│  │  ⏰ 10:00 - 11:00                │   │
│  │  💰 $50.00                       │   │
│  │                                  │   │
│  │  [  Cancel Booking  ]           │   │
│  └─────────────────────────────────┘   │
│                                         │
│  ┌─────────────────────────────────┐   │
│  │  Booking #1233         [✓]      │   │
│  │                                  │   │
│  │  Pool Table 1                    │   │  ← Booking Card 2
│  │  📅 Jan 25, 2026                 │   │     (Confirmed)
│  │  ⏰ 15:00 - 16:00                │   │
│  │  💰 $15.00                       │   │
│  │                                  │   │
│  │  [  Cancel Booking  ]           │   │
│  └─────────────────────────────────┘   │
│                                         │
│  ┌─────────────────────────────────┐   │
│  │  Booking #1232         [✗]      │   │
│  │                                  │   │
│  │  Pickleball Court 1              │   │  ← Booking Card 3
│  │  📅 Jan 24, 2026                 │   │     (Cancelled)
│  │  ⏰ 18:00 - 19:00                │   │
│  │  💰 $25.00                       │   │
│  │                                  │   │
│  │  Status: Cancelled               │   │
│  └─────────────────────────────────┘   │
│                                         │
│  ┌─────────────────────────────────┐   │
│  │  [🏠 Home]  [📜 Bookings]       │   │  ← Bottom Nav
│  └─────────────────────────────────┘   │
└─────────────────────────────────────────┘
```

#### Empty State (No Bookings)

```
┌─────────────────────────────────────────┐
│  ┌─────────────────────────────────┐   │
│  │       My Bookings    [≡]        │   │
│  └─────────────────────────────────┘   │
│                                         │
│              ┌──────────┐               │
│              │          │               │
│              │    📅    │               │
│              │  64x64   │               │
│              │          │               │
│              └──────────┘               │
│                                         │
│          No bookings yet                │
│                                         │
│     Start booking facilities!           │
│                                         │
│  ┌─────────────────────────────────┐   │
│  │  [🏠 Home]  [📜 Bookings]       │   │
│  └─────────────────────────────────┘   │
└─────────────────────────────────────────┘
```

#### Components Breakdown

**1. Top App Bar**
- Title: "My Bookings"
- Background: Primary container

**2. Booking Card**
- Booking ID + Status badge
- Facility name (Title Medium, Bold)
- Date with calendar icon
- Time with clock icon
- Price with dollar icon
- Status badge:
  - ✓ Confirmed (Green)
  - ✗ Cancelled (Red)
  - ⏱ Completed (Gray)
- Cancel button (only for future confirmed bookings)
- Card elevation: 2dp

**3. Cancel Button**
- Outlined button style
- Text: "Cancel Booking"
- Enabled: Only for confirmed + future bookings
- Disabled: For past or cancelled bookings

**4. Empty State**
- Large icon (64x64dp)
- Message: "No bookings yet"
- Subtitle text
- Centered layout

---

## 5. UI Navigation Flow

### 5.1 Complete Navigation Diagram

```
┌──────────────────────────────────────────────────────────────┐
│                       APP START                              │
│                           ↓                                  │
│                    [MainActivity]                            │
│                           ↓                                  │
│                  Database Initialized                        │
│                     Repositories Ready                       │
│                           ↓                                  │
└──────────────────────────────────────────────────────────────┘
                            ↓
┌──────────────────────────────────────────────────────────────┐
│                    HOME SCREEN                               │
│                  (Start Destination)                         │
│  ┌────────────────────────────────────────────────────┐     │
│  │  • Display all facilities (8 default)              │     │
│  │  • Filter chips: All, Cricket, Pool, Pickleball    │     │
│  │  • Bottom Nav: Home (selected), Bookings           │     │
│  └────────────────────────────────────────────────────┘     │
│                                                              │
│  User Actions:                                               │
│  ┌──────────────────┐  ┌──────────────────┐                │
│  │ Tap Facility Card│  │ Tap Filter Chip  │                │
│  └──────┬───────────┘  └─────┬────────────┘                │
│         │                    │                              │
│         │                    └─→ Filter facilities          │
│         │                        (Stay on screen)           │
│         │                                                    │
└─────────┼────────────────────────────────────────────────────┘
          │
          ↓
┌──────────────────────────────────────────────────────────────┐
│              FACILITY DETAIL SCREEN                          │
│               (Navigation: Push Stack)                       │
│  ┌────────────────────────────────────────────────────┐     │
│  │  • Display facility info (icon, name, price, desc) │     │
│  │  • Date selector (next 7 days)                     │     │
│  │  • Time slot grid (6 AM - 10 PM)                   │     │
│  │  • Available slots (green), Booked (gray)          │     │
│  │  • Book Now button (disabled until selection)      │     │
│  └────────────────────────────────────────────────────┘     │
│                                                              │
│  User Actions:                                               │
│  ┌──────────────┐ ┌───────────────┐ ┌──────────────┐       │
│  │ Tap Back [←] │ │ Select Date + │ │ Tap Book Now │       │
│  │              │ │   Time Slot   │ │              │       │
│  └──────┬───────┘ └───────┬───────┘ └──────┬───────┘       │
│         │                 │                │                │
│         │                 ↓                │                │
│         │        Button becomes enabled    │                │
│         │        Shows calculated price    │                │
│         │                                  │                │
└─────────┼──────────────────────────────────┼────────────────┘
          │                                  │
          ↓                                  ↓
    ┌─────────┐                    ┌──────────────────┐
    │ Pop to  │                    │ Create Booking   │
    │  Home   │                    │   in Database    │
    │         │                    └────────┬─────────┘
    └─────────┘                             │
          ↑                                 │
          │                                 ↓
          │                    ┌──────────────────────┐
          │                    │ Success: Navigate to │
          └────────────────────┤      Home Screen     │
                               └──────────────────────┘


┌──────────────────────────────────────────────────────────────┐
│                 BOOKING HISTORY SCREEN                       │
│              (Via Bottom Navigation Tab)                     │
│  ┌────────────────────────────────────────────────────┐     │
│  │  • Load user's bookings from database              │     │
│  │  • Display booking cards (latest first)            │     │
│  │  • Show status: Confirmed/Cancelled/Completed      │     │
│  │  • Bottom Nav: Home, Bookings (selected)           │     │
│  └────────────────────────────────────────────────────┘     │
│                                                              │
│  User Actions:                                               │
│  ┌─────────────────┐  ┌───────────────────┐                │
│  │ Tap Home Tab    │  │ Tap Cancel Booking│                │
│  └────────┬────────┘  └─────────┬─────────┘                │
│           │                     │                            │
│           ↓                     ↓                            │
│    Navigate to            Update booking status             │
│    Home Screen            to "CANCELLED" in DB              │
│                           Refresh list                       │
└──────────────────────────────────────────────────────────────┘
```

### 5.2 Navigation Flow Chart

```
                    ┌──────────────┐
                    │     APP      │
                    │    LAUNCH    │
                    └──────┬───────┘
                           │
                           ↓
             ┌─────────────────────────┐
             │    HOME SCREEN (1)      │◄──────────┐
             │  • Browse facilities    │           │
             │  • Filter by type       │           │
             └──┬──────────────────┬───┘           │
                │                  │               │
    ┌───────────┘                  └───────────┐   │
    │                                          │   │
    │ Tap Facility                   Tap       │   │
    │                              Bookings    │   │
    ↓                                Tab       ↓   │
┌──────────────────────┐                  ┌────────────────────┐
│ FACILITY DETAIL (2)  │                  │ BOOKING HISTORY(3) │
│ • View details       │                  │ • View bookings    │
│ • Select date/time   │                  │ • Cancel booking   │
│ • Book facility      │                  └──────────┬─────────┘
└──┬──────────┬────────┘                             │
   │          │                                      │
   │ Back     │ Book Success                  Tap Home Tab
   │          │                                      │
   └──────────┴──────────────────────────────────────┘
```

### 5.3 State Transition Diagram

```
┌────────────────────────────────────────────────────────┐
│                    HOME SCREEN                         │
│                                                        │
│  State: Showing Facilities                             │
│  ┌──────────────────────────────────────────────┐     │
│  │ • isLoading: false                           │     │
│  │ • facilities: List<Facility>                 │     │
│  │ • selectedType: null (All)                   │     │
│  └──────────────────────────────────────────────┘     │
│                                                        │
│  Events:                                               │
│  • onFilterChange → Update selectedType                │
│  • onFacilityClick → Navigate to Detail                │
└────────────────────────────────────────────────────────┘
                          ↓
┌────────────────────────────────────────────────────────┐
│               FACILITY DETAIL SCREEN                   │
│                                                        │
│  Initial State: Loading                                │
│  ┌──────────────────────────────────────────────┐     │
│  │ • isLoading: true                            │     │
│  │ • facility: null                             │     │
│  └──────────────────────────────────────────────┘     │
│                   ↓                                    │
│  After Load: Ready to Book                             │
│  ┌──────────────────────────────────────────────┐     │
│  │ • facility: Facility                         │     │
│  │ • availableDates: List<Date> (next 7 days)   │     │
│  │ • selectedDate: null                         │     │
│  │ • selectedTimeSlot: null                     │     │
│  │ • bookingEnabled: false                      │     │
│  └──────────────────────────────────────────────┘     │
│                   ↓                                    │
│  After Date Selected:                                  │
│  ┌──────────────────────────────────────────────┐     │
│  │ • selectedDate: Date                         │     │
│  │ • timeSlots: List<TimeSlot>                  │     │
│  │ • bookingEnabled: false (need time)          │     │
│  └──────────────────────────────────────────────┘     │
│                   ↓                                    │
│  After Time Selected:                                  │
│  ┌──────────────────────────────────────────────┐     │
│  │ • selectedTimeSlot: TimeSlot                 │     │
│  │ • totalPrice: calculated                     │     │
│  │ • bookingEnabled: true ✓                     │     │
│  └──────────────────────────────────────────────┘     │
│                   ↓                                    │
│  After Booking Confirmed:                              │
│  ┌──────────────────────────────────────────────┐     │
│  │ • bookingSuccess: true                       │     │
│  │ • Navigate back to Home                      │     │
│  └──────────────────────────────────────────────┘     │
└────────────────────────────────────────────────────────┘
                          ↓
┌────────────────────────────────────────────────────────┐
│              BOOKING HISTORY SCREEN                    │
│                                                        │
│  Initial State: Loading                                │
│  ┌──────────────────────────────────────────────┐     │
│  │ • isLoading: true                            │     │
│  │ • bookings: emptyList()                      │     │
│  └──────────────────────────────────────────────┘     │
│                   ↓                                    │
│  After Load: Display Bookings                          │
│  ┌──────────────────────────────────────────────┐     │
│  │ • bookings: List<Booking>                    │     │
│  │ • sorted by date (latest first)              │     │
│  └──────────────────────────────────────────────┘     │
│                   ↓                                    │
│  After Cancel Action:                                  │
│  ┌──────────────────────────────────────────────┐     │
│  │ • Update booking status to CANCELLED         │     │
│  │ • Refresh bookings list                      │     │
│  └──────────────────────────────────────────────┘     │
└────────────────────────────────────────────────────────┘
```

---

## 6. Component Specifications

### 6.1 Top App Bar

**Properties:**
- Height: 64dp
- Background: Primary Container
- Title alignment: Left
- Title style: Title Large
- Navigation icon: 24dp

**Variants:**
1. **Home Screen**: Title only
2. **Detail Screen**: Back button + Title
3. **Booking History**: Title only

### 6.2 Bottom Navigation Bar

**Properties:**
- Height: 80dp
- 2 items maximum
- Icon size: 24dp
- Label style: Label Medium
- Selected indicator: Background fill

**Items:**
1. Home (Icon: House, Route: "/")
2. Bookings (Icon: History, Route: "/bookings")

### 6.3 Filter Chips

**Properties:**
- Height: 32dp
- Padding: 12dp horizontal
- Border radius: 16dp
- Icon size: 18dp

**States:**
- Selected: Filled background (Primary)
- Unselected: Outlined (1dp border)

**Types:**
1. All (no icon)
2. Cricket (🏏 icon)
3. Pool (🎱 icon)
4. Pickleball (🎾 icon)

### 6.4 Facility Card

**Properties:**
- Width: Match parent - 32dp (16dp margins)
- Min height: 120dp
- Padding: 16dp
- Elevation: 2dp
- Border radius: 12dp

**Layout:**
- Icon: 48x48dp (left)
- Content: Column (right of icon)
  - Title: Title Medium, Bold
  - Description: Body Small, 2 lines max
  - Price: Title Medium, Primary color

### 6.5 Time Slot Item

**Properties:**
- Size: 100dp x 48dp
- Border radius: 8dp
- Text: Label Large
- Grid: 3 columns, 12dp gap

**States:**
- Available: Green background (#4CAF50)
- Booked: Gray background (#9E9E9E), disabled
- Selected: Blue background (#2196F3)

### 6.6 Booking Card

**Properties:**
- Width: Match parent - 32dp
- Min height: 180dp
- Padding: 16dp
- Elevation: 2dp
- Border radius: 12dp

**Components:**
- Header row: Booking ID + Status badge
- Facility name
- Date (icon + text)
- Time (icon + text)
- Price (icon + text)
- Action button (Cancel)

---

## 7. User Journey Maps

### 7.1 Journey 1: First-Time User Books a Facility

```
Step 1: DISCOVER
┌────────────────────────────────────┐
│ User opens GameArena app           │
│ → Lands on Home Screen             │
│ → Sees list of 8 facilities        │
│ → Reads descriptions and prices    │
└────────────────┬───────────────────┘
                 ↓
Step 2: FILTER (Optional)
┌────────────────────────────────────┐
│ User wants cricket ground          │
│ → Taps "Cricket" filter chip       │
│ → List shows only cricket grounds  │
│ → Sees 2 options with prices       │
└────────────────┬───────────────────┘
                 ↓
Step 3: SELECT FACILITY
┌────────────────────────────────────┐
│ User taps "Cricket Ground A"       │
│ → Navigation to Detail Screen      │
│ → Sees facility info and pricing   │
│ → Understands booking process      │
└────────────────┬───────────────────┘
                 ↓
Step 4: CHOOSE DATE
┌────────────────────────────────────┐
│ User scrolls date selector         │
│ → Sees next 7 days available       │
│ → Taps "Jan 27" (tomorrow)         │
│ → Date chip turns selected (blue)  │
│ → Time slots load for that date    │
└────────────────┬───────────────────┘
                 ↓
Step 5: CHOOSE TIME
┌────────────────────────────────────┐
│ User views time slot grid          │
│ → Green slots are available        │
│ → Gray slots are booked            │
│ → Taps "10:00" slot                │
│ → Slot turns blue (selected)       │
│ → Book button shows "$50.00"       │
└────────────────┬───────────────────┘
                 ↓
Step 6: CONFIRM BOOKING
┌────────────────────────────────────┐
│ User taps "Book Now - $50.00"      │
│ → Booking created in database      │
│ → Success feedback (maybe toast)   │
│ → Auto navigate back to Home       │
│ → Booking confirmed                │
└────────────────┬───────────────────┘
                 ↓
Step 7: VERIFY BOOKING
┌────────────────────────────────────┐
│ User taps "Bookings" tab           │
│ → Sees newly created booking       │
│ → Booking #1234 shown              │
│ → Status: Confirmed ✓              │
│ → Can cancel if needed             │
└────────────────────────────────────┘

Total Steps: 7
Time Estimate: 60-90 seconds
```

### 7.2 Journey 2: Returning User Cancels a Booking

```
Step 1: OPEN APP
┌────────────────────────────────────┐
│ User opens GameArena               │
│ → Lands on Home Screen             │
└────────────────┬───────────────────┘
                 ↓
Step 2: NAVIGATE TO BOOKINGS
┌────────────────────────────────────┐
│ User taps "Bookings" tab           │
│ → Navigation to Booking History    │
│ → Sees list of all bookings        │
└────────────────┬───────────────────┘
                 ↓
Step 3: FIND BOOKING
┌────────────────────────────────────┐
│ User scrolls through bookings      │
│ → Finds future confirmed booking   │
│ → Reads: "Cricket Ground A, Jan 27"│
└────────────────┬───────────────────┘
                 ↓
Step 4: CANCEL BOOKING
┌────────────────────────────────────┐
│ User taps "Cancel Booking" button  │
│ → System updates status            │
│ → Status badge changes to ✗        │
│ → Button becomes disabled          │
│ → Cancellation confirmed           │
└────────────────────────────────────┘

Total Steps: 4
Time Estimate: 15-30 seconds
```

### 7.3 Journey 3: User Browses Multiple Facilities

```
Step 1: BROWSE ALL
┌────────────────────────────────────┐
│ User scrolls Home Screen           │
│ → Views all 8 facilities           │
│ → Compares prices                  │
└────────────────┬───────────────────┘
                 ↓
Step 2: FILTER BY SPORT
┌────────────────────────────────────┐
│ User taps "Pool" filter            │
│ → Shows 3 pool tables              │
│ → Sees prices: $12, $13, $15       │
└────────────────┬───────────────────┘
                 ↓
Step 3: VIEW DETAILS
┌────────────────────────────────────┐
│ User taps "Pool Table 1"           │
│ → Views details                    │
│ → Checks description               │
│ → Decides not to book (yet)        │
└────────────────┬───────────────────┘
                 ↓
Step 4: GO BACK
┌────────────────────────────────────┐
│ User taps back button              │
│ → Returns to filtered list (Pool)  │
│ → Filter state preserved           │
└────────────────┬───────────────────┘
                 ↓
Step 5: TRY DIFFERENT FILTER
┌────────────────────────────────────┐
│ User taps "Pickleball" filter      │
│ → Shows 3 pickleball courts        │
│ → Continues browsing               │
└────────────────────────────────────┘

Total Steps: 5
Time Estimate: 45-60 seconds
```

---

## 8. Design Annotations

### 8.1 Layout Specifications

**Screen Dimensions:**
- Target: 360 x 800dp (baseline)
- Max width: 600dp (tablet)
- Orientation: Portrait only

**Spacing System:**
- XS: 4dp
- SM: 8dp
- MD: 12dp
- LG: 16dp
- XL: 24dp
- XXL: 32dp

**Elevation Levels:**
- Surface: 0dp
- Card: 2dp
- Raised card: 4dp
- Modal: 8dp
- Navigation bar: 3dp

### 8.2 Typography Scale

```
Display Large:   57sp / Bold
Display Medium:  45sp / Bold
Display Small:   36sp / Bold

Headline Large:  32sp / Regular
Headline Medium: 28sp / Regular
Headline Small:  24sp / Regular

Title Large:     22sp / Medium
Title Medium:    16sp / Medium
Title Small:     14sp / Medium

Body Large:      16sp / Regular
Body Medium:     14sp / Regular
Body Small:      12sp / Regular

Label Large:     14sp / Medium
Label Medium:    12sp / Medium
Label Small:     11sp / Medium
```

### 8.3 Color System (Material 3)

**Primary Colors:**
- Primary: #6750A4
- On Primary: #FFFFFF
- Primary Container: #EADDFF
- On Primary Container: #21005D

**Status Colors:**
- Success (Green): #4CAF50
- Warning (Orange): #FF9800
- Error (Red): #F44336
- Info (Blue): #2196F3

**Neutral Colors:**
- Background: #FFFBFE
- Surface: #FFFBFE
- Outline: #79747E

### 8.4 Interactive States

**Touch Target:**
- Minimum size: 48x48dp
- Recommended: 56x56dp for important actions

**Ripple Effect:**
- All clickable items
- Color: Primary @ 12% opacity

**State Layers:**
- Hover: +8% opacity
- Focus: +12% opacity
- Press: +16% opacity
- Drag: +16% opacity

### 8.5 Accessibility

**Minimum Requirements:**
- Text contrast: 4.5:1 (WCAG AA)
- Large text: 3:1
- Touch targets: 48x48dp
- Focus indicators: Visible on all interactive elements

**Content Descriptions:**
- All icons have contentDescription
- All images have alt text
- Buttons have descriptive labels

---

## 9. Navigation Rules

### 9.1 Navigation Patterns

**Bottom Navigation:**
- Switch between Home and Bookings
- No stack: Always root level
- Selected state persists

**Stack Navigation:**
- Home → Facility Detail (Push)
- Facility Detail → Home (Pop after booking)
- Back button: Pop stack

**Deep Linking Support:**
```
gamearena://home
gamearena://facility/{id}
gamearena://bookings
```

### 9.2 Transition Animations

**Screen Transitions:**
- Forward: Slide in from right (300ms)
- Backward: Slide out to right (300ms)
- Tab switch: Fade (200ms)

**Component Animations:**
- Filter selection: Scale + color (150ms)
- Time slot selection: Scale + color (100ms)
- Button press: Scale down (50ms), up (100ms)

---

## 10. Wireframe Creation Tools

### Recommended Tools for Drawing These Wireframes:

1. **Figma** (Free, Web-based)
   - URL: https://figma.com
   - Best for: Collaborative design
   - Features: Components, auto-layout, prototyping

2. **Adobe XD** (Free version available)
   - URL: https://adobe.com/products/xd
   - Best for: High-fidelity mockups
   - Features: Repeat grid, responsive resize

3. **Balsamiq** (Paid, Low-fi focused)
   - URL: https://balsamiq.com
   - Best for: Quick wireframes
   - Features: Sketch-like appearance

4. **Sketch** (Mac only, Paid)
   - URL: https://sketch.com
   - Best for: Professional design
   - Features: Symbols, shared libraries

5. **Pencil & Paper** (Free!)
   - Best for: Initial ideation
   - Features: Maximum flexibility

### How to Create Wireframes from This Document:

1. **Choose your tool** (Figma recommended for beginners)
2. **Create artboards** (360x800dp each screen)
3. **Use rectangles** for cards and containers
4. **Add text labels** for all components
5. **Use icons** from Material Symbols
6. **Draw navigation arrows** between screens
7. **Add annotations** for interactions
8. **Export as PDF** or PNG for submission

---

## 11. Deliverables Checklist

### For Lab Submission:

✅ **Wireframes (3 screens minimum)**
- [ ] Home Screen wireframe
- [ ] Facility Detail Screen wireframe
- [ ] Booking History Screen wireframe

✅ **UI Navigation Flow Diagram**
- [ ] Complete navigation paths
- [ ] User actions labeled
- [ ] Screen transitions shown
- [ ] Back navigation indicated

✅ **Component Specifications**
- [ ] Dimensions listed
- [ ] Spacing defined
- [ ] Color codes provided
- [ ] Typography specified

✅ **User Journey Map**
- [ ] At least 1 complete user flow
- [ ] Step-by-step breakdown
- [ ] Expected outcomes listed

✅ **Documentation**
- [ ] Screen descriptions
- [ ] Design rationale
- [ ] Accessibility notes
- [ ] Technical annotations

---

## 12. Summary

### Key Takeaways

**Wireframes Provide:**
- 📐 **Structure**: Layout and hierarchy
- 🎯 **Clarity**: Component placement
- 🔄 **Flow**: User journey mapping
- 💡 **Foundation**: Development blueprint

**GameArena App Structure:**
- **3 main screens**: Home, Detail, History
- **Bottom navigation**: 2-tab structure
- **CRUD operations**: Create bookings, view history, cancel
- **Real-time feedback**: Status updates, availability

**Navigation Flow:**
- **Linear booking flow**: Browse → Detail → Book → Confirm
- **Tab-based history**: Quick access to bookings
- **Back navigation**: Intuitive stack management

---

## 13. Next Steps for Development

After wireframes are approved:

1. **UI Components Lab**: Build individual components (cards, chips, buttons)
2. **CRUD Operations Lab**: Implement database operations
3. **Navigation Lab**: Set up navigation graph
4. **State Management Lab**: Implement ViewModels
5. **Integration Lab**: Connect all components
6. **Testing Lab**: Write unit and UI tests

---

## 📚 References

- **Material Design 3**: https://m3.material.io
- **Jetpack Compose**: https://developer.android.com/jetpack/compose
- **Navigation Component**: https://developer.android.com/guide/navigation
- **Room Database**: https://developer.android.com/training/data-storage/room

---

**Document Version**: 1.0  
**Last Updated**: January 26, 2026  
**Author**: GameArena Development Team  
**Purpose**: Lab Assignment - Wireframes & UI Navigation Flow

---

## Appendix A: ASCII Art Components Library

### Button
```
┌─────────────────────┐
│   Button Text       │
└─────────────────────┘
```

### Chip (Selected)
```
[Selected Chip]
```

### Chip (Unselected)
```
[ Outlined Chip ]
```

### Icon
```
┌────┐
│ 🎯 │
└────┘
```

### Card
```
┌─────────────────────────┐
│  Card Title             │
│  Card content goes here │
│  More content...        │
└─────────────────────────┘
```

### Time Slot (Available)
```
┌─────────┐
│ 10:00   │  ← Green
└─────────┘
```

### Time Slot (Booked)
```
┌─────────┐
│ 11:00   │  ← Gray (disabled)
└─────────┘
```

### Time Slot (Selected)
```
┌─────────┐
│ 12:00   │  ← Blue
└─────────┘
```

---

**END OF WIREFRAMES DOCUMENTATION**
