# 📸 GameArena UI Components - Visual Screenshots Guide

## Project: GameArena Sports Facility Booking App
## Lab: UI Components and Layout Design
## Date: January 29, 2026

---

## 📱 Screen Screenshots Summary

This document provides detailed visual representations of all implemented UI screens.

---

## 1. Login Screen

**File**: `LoginScreen.kt`  
**Route**: `login` (Entry point)

### Visual Layout:
```
╔═══════════════════════════════════╗
║                                   ║
║                                   ║
║         🎮 GameArena              ║ ← Display Medium, Purple
║                                   ║
║   Book your favorite sports       ║ ← Body Large, Gray
║         facility                  ║
║                                   ║
║                                   ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ 📧  Email Address          ┃  ║ ← Outlined TextField
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║   Rounded (12dp)
║                                   ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ 🔒  Password         👁️    ┃  ║ ← Password field
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║   with toggle
║                                   ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃         Log In             ┃  ║ ← Primary Button
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║   56dp height
║                                   ║
║  Don't have an account? Sign Up   ║ ← Clickable Text
║                                   ║
╚═══════════════════════════════════╝
```

### Component Breakdown:
| Component | Type | Styling |
|-----------|------|---------|
| GameArena Title | Text | DisplayMedium, 34sp, Bold, Primary Color |
| Subtitle | Text | BodyLarge, 16sp, OnSurfaceVariant |
| Email Field | GameArenaTextField | OutlinedTextField, 12dp corners |
| Password Field | GameArenaTextField | With PasswordVisualTransformation |
| Visibility Toggle | IconButton | Visibility/VisibilityOff icons |
| Login Button | GameArenaButton | 56dp height, Primary color |
| Sign Up Link | Text | BodyMedium, Primary color, Clickable |

### Spacing:
- Container padding: 24dp
- Title to subtitle: 8dp
- Subtitle to fields: 48dp
- Between fields: 16dp
- Fields to button: 32dp
- Button to link: 24dp

### State Management:
```kotlin
var email by remember { mutableStateOf("") }
var password by remember { mutableStateOf("") }
var passwordVisible by remember { mutableStateOf(false) }
var errorMessage by remember { mutableStateOf<String?>(null) }
```

---

## 2. Sign-Up Screen

**File**: `SignUpScreen.kt`  
**Route**: `signup`

### Visual Layout:
```
╔═══════════════════════════════════╗
║  ↓ Scrollable Content             ║
║                                   ║
║      Create Account               ║ ← Display Small, 28sp
║     Join GameArena today          ║
║                                   ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ 👤  Full Name              ┃  ║
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ 📧  Email Address          ┃  ║
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ 📱  Phone Number           ┃  ║
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ 🔒  Password         👁️    ┃  ║
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ 🔒  Confirm Password 👁️    ┃  ║ ← Shows error if
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║   not matching
║    ⚠️ Passwords do not match     ║ ← Error text (red)
║                                   ║
║  ☑️ I agree to Terms & Conditions ║ ← Checkbox
║                                   ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃        Sign Up             ┃  ║ ← Disabled until
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║   checkbox checked
║                                   ║
║  Already have an account? Log In  ║
║                                   ║
║  ↑ Scrollable Content             ║
╚═══════════════════════════════════╝
```

### Key Features:
✅ Vertically scrollable (handles keyboard)  
✅ 5 input fields with appropriate icons  
✅ Real-time password matching validation  
✅ Terms checkbox requirement  
✅ Button disabled state  
✅ Navigation to login  

### Validation States:
| Field | Validation |
|-------|------------|
| Full Name | Required, not blank |
| Email | Required, not blank (future: regex) |
| Phone | Required, not blank (future: format) |
| Password | Required, minimum length (future) |
| Confirm | Must match password |
| Terms | Must be checked |

---

## 3. Home Screen (Dashboard)

**File**: `HomeScreen.kt`  
**Route**: `home`

### Visual Layout:
```
╔═══════════════════════════════════╗
║  GameArena                     ☰  ║ ← TopAppBar (64dp)
╠═══════════════════════════════════╣
║                                   ║
║ [All] [Cricket] [Pool] [P-ball]   ║ ← Filter Chips
║                                   ║   Horizontal scroll
╠───────────────────────────────────╣
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ 🏏  Cricket Ground A    ➤  ┃  ║ ← Facility Card
║  ┃     Professional cricket   ┃  ║   Clickable
║  ┃     ground with lights     ┃  ║
║  ┃     💰 $50.00/hour         ┃  ║
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ 🏏  Cricket Ground B    ➤  ┃  ║
║  ┃     Standard cricket       ┃  ║
║  ┃     ground                 ┃  ║
║  ┃     💰 $40.00/hour         ┃  ║
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ 🎱  Pool Table 1        ➤  ┃  ║
║  ┃     Professional pool      ┃  ║
║  ┃     table                  ┃  ║
║  ┃     💰 $15.00/hour         ┃  ║
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║
║                                   ║
║  ↓ Scrollable (LazyColumn)        ║
╠═══════════════════════════════════╣
║    🏠 Home    |    📜 Bookings    ║ ← Bottom Nav (80dp)
╚═══════════════════════════════════╝
```

### Filter Chip States:
```
Selected:   [All]  ← Primary background
Unselected: [Cricket]  ← Outline only
```

### Facility Card Anatomy:
```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃ [Icon]  Title            ➤   ┃ ← 48dp icon, chevron
┃         Description          ┃ ← OnSurfaceVariant
┃         💰 $XX.XX/hour       ┃ ← Primary color, bold
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
  ↑                             ↑
 16dp padding              2dp elevation
```

### Loading State:
```
╔═══════════════════════════════════╗
║                                   ║
║              🔄                   ║ ← CircularProgressIndicator
║          Loading...               ║
║                                   ║
╚═══════════════════════════════════╝
```

### Empty State:
```
╔═══════════════════════════════════╗
║                                   ║
║              📭                   ║ ← Icon (64dp)
║     No facilities available       ║
║                                   ║
╚═══════════════════════════════════╝
```

---

## 4. Facility Detail Screen

**File**: `FacilityDetailScreen.kt`  
**Route**: `facility/{id}`

### Visual Layout:
```
╔═══════════════════════════════════╗
║  ← Cricket Ground A            ☰  ║ ← TopAppBar with back
╠═══════════════════════════════════╣
║                                   ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ 🏏  Cricket Ground A        ┃  ║ ← Facility Info Card
║  ┃     💰 $50.00/hour          ┃  ║
║  ┃     Professional cricket    ┃  ║
║  ┃     ground with lights      ┃  ║
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║
║                                   ║
║  Select Date:                     ║
║  [Jan 27] [Jan 28] [Jan 29] ...   ║ ← Horizontal scroll
║                                   ║   Selected: Primary
║  Select Time:                     ║
║  ┌───────┬───────┬───────┐       ║
║  │ 06:00 │ 07:00 │ 08:00 │       ║ ← 3 column grid
║  └───────┴───────┴───────┘       ║
║  ┌───────┬───────┬───────┐       ║
║  │ 09:00 │ 10:00 │ 11:00 │       ║ ← Selected (Blue)
║  └───────┴───────┴───────┘       ║
║  ┌───────┬───────┬───────┐       ║
║  │ 12:00 │ 13:00 │ 14:00 │       ║ ← Booked (Gray)
║  └───────┴───────┴───────┘       ║
║                                   ║
║  Legend:                          ║
║  🟢 Available  🔵 Selected  ⚫ Booked
║                                   ║
║  ↓ Scrollable                     ║
║                                   ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃    Book Now - $50.00       ┃  ║ ← GameArenaButton
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║   (bottom fixed)
╚═══════════════════════════════════╝
```

### Time Slot States:
```
Available:  ┌───────┐
            │ 10:00 │  ← Green background (#4CAF50)
            └───────┘    Clickable

Selected:   ┌───────┐
            │ 11:00 │  ← Blue background (#2196F3)
            └───────┘    Bold text

Booked:     ┌───────┐
            │ 12:00 │  ← Gray background (#9E9E9E)
            └───────┘    Disabled
```

### Date Chip States:
```
Selected:   [Jan 29]  ← Filled (Primary)
Unselected: [Jan 30]  ← Outlined
```

---

## 5. Booking History Screen

**File**: `BookingHistoryScreen.kt`  
**Route**: `bookings`

### Visual Layout:
```
╔═══════════════════════════════════╗
║  My Bookings                   ☰  ║ ← TopAppBar
╠═══════════════════════════════════╣
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ #BK001        [Confirmed]   ┃  ║ ← Booking Card
║  ┃ Cricket Ground A            ┃  ║   Status Badge
║  ┃ 📅 Jan 29, 2026             ┃  ║
║  ┃ ⏰ 10:00 AM - 11:00 AM      ┃  ║
║  ┃ 💰 $50.00                   ┃  ║
║  ┃              ┌──────────┐   ┃  ║
║  ┃              │  Cancel  │   ┃  ║ ← Cancel Button
║  ┃              └──────────┘   ┃  ║
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ #BK002        [Cancelled]   ┃  ║ ← Red badge
║  ┃ Pool Table 1                ┃  ║
║  ┃ 📅 Jan 28, 2026             ┃  ║
║  ┃ ⏰ 03:00 PM - 04:00 PM      ┃  ║
║  ┃ 💰 $15.00                   ┃  ║
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║
║                                   ║
║  ↓ Scrollable LazyColumn          ║
╠═══════════════════════════════════╣
║    🏠 Home    |    📜 Bookings    ║
╚═══════════════════════════════════╝
```

### Status Badges:
```
Confirmed:  [Confirmed]  ← Green badge (#4CAF50)
Cancelled:  [Cancelled]  ← Red badge (#F44336)
Completed:  [Completed]  ← Gray badge
```

### Empty State:
```
╔═══════════════════════════════════╗
║                                   ║
║              📭                   ║
║       No bookings yet             ║
║   Book a facility to get started  ║
║                                   ║
╚═══════════════════════════════════╝
```

---

## 6. Profile Screen

**File**: `ProfileScreen.kt`  
**Route**: `profile`

### Visual Layout:
```
╔═══════════════════════════════════╗
║  ← Profile                        ║ ← TopAppBar
╠═══════════════════════════════════╣
║          ┌──────────┐             ║
║          │    👤    │             ║ ← Circular Avatar
║          │          │             ║   120dp diameter
║          └──────────┘             ║
║                                   ║
║         John Doe                  ║ ← Headline Medium
║    john.doe@example.com           ║ ← Body Large
║                                   ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ 📧  Email                   ┃  ║ ← Info Card
║  ┃     john.doe@example.com    ┃  ║
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ 📱  Phone                   ┃  ║
║  ┃     +1 234 567 8900         ┃  ║
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ 📍  Location                ┃  ║
║  ┃     New York, USA           ┃  ║
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║
║                                   ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ ✏️  Edit Profile        ➤   ┃  ║ ← Action Button
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║   (OutlinedButton)
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ ⚙️  Settings            ➤   ┃  ║
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ 🔔  Notifications       ➤   ┃  ║
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ ❓  Help & Support      ➤   ┃  ║
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║
║                                   ║
║  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓  ║
║  ┃ 🚪  Logout                  ┃  ║ ← Error Container
║  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛  ║   Red/Pink background
║                                   ║
║  ↑ Scrollable Content             ║
╚═══════════════════════════════════╝
```

### Profile Info Card Component:
```kotlin
@Composable
fun ProfileInfoCard(
    icon: ImageVector,
    title: String,
    value: String
) {
    Card {
        Row {
            Icon(icon, tint = Primary)  ← 24dp
            Column {
                Text(title, BodySmall)   ← Label
                Text(value, BodyLarge, Bold)  ← Value
            }
        }
    }
}
```

---

## 7. Reusable Components Visual Reference

### GameArenaTextField
```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃ 📧  Email Address            ┃  ← Full width
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛    12dp corners
 ↑                             ↑    OutlinedTextField
Icon                      Trailing
(optional)                (optional)

States:
- Focused: Primary border (#6750A4)
- Unfocused: Outline border
- Error: Error border (#F44336)
```

### GameArenaButton
```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃         Button Text          ┃  ← 56dp height
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛    Full width
                                   12dp corners
States:
- Enabled: Primary background
- Disabled: Gray, 38% opacity
- Pressed: Darker primary
```

### GameArenaCard
```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃ [Icon]  Title                ┃  ← 16dp padding
┃         Subtitle             ┃    2dp elevation
┃         Description          ┃    Clickable
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

### LoadingIndicator
```
╔═══════════════════════════════════╗
║                                   ║
║              🔄                   ║ ← CircularProgressIndicator
║          Loading...               ║    Primary color
║                                   ║    Centered
╚═══════════════════════════════════╝
```

### EmptyState
```
╔═══════════════════════════════════╗
║                                   ║
║              📭                   ║ ← Icon (64dp)
║         No items found            ║    OnSurfaceVariant
║                                   ║    Centered
╚═══════════════════════════════════╝
```

---

## 8. Color Coding Reference

### UI State Colors:
```
Available:   🟢 #4CAF50 (Green)     - Available time slots
Selected:    🔵 #2196F3 (Blue)      - Selected items
Confirmed:   🟢 #4CAF50 (Green)     - Confirmed bookings
Cancelled:   🔴 #F44336 (Red)       - Cancelled bookings
Booked:      ⚫ #9E9E9E (Gray)      - Unavailable slots
Primary:     🟣 #6750A4 (Purple)    - Branding, CTAs
Error:       🔴 #F44336 (Red)       - Errors, warnings
```

### Visual Examples:
```
Filter Chips:
[All] ← Selected (filled purple)
[Cricket] ← Unselected (outlined)

Time Slots:
┌───────┐  ┌───────┐  ┌───────┐
│ 10:00 │  │ 11:00 │  │ 12:00 │
└───────┘  └───────┘  └───────┘
  Green      Blue       Gray
(Available) (Selected) (Booked)

Status Badges:
[Confirmed] ← Green background
[Cancelled] ← Red background
[Completed] ← Gray background
```

---

## 9. Typography Scale Visual

### Text Hierarchy:
```
GameArena               ← Display Medium (34sp, Bold)
  ↓
Create Account          ← Display Small (28sp, Bold)
  ↓
Cricket Ground A        ← Headline Medium (24sp, Bold)
  ↓
Log In Button           ← Title Medium (16sp, Medium)
  ↓
Book your favorite...   ← Body Large (16sp, Regular)
  ↓
Professional cricket    ← Body Medium (14sp, Regular)
  ↓
Email label             ← Body Small (12sp, Regular)
```

---

## 10. Spacing Visual Guide

### Vertical Spacing:
```
Title
  ↕ 8dp
Subtitle
  ↕ 48dp (major section)
Input Field 1
  ↕ 16dp (standard)
Input Field 2
  ↕ 16dp
Input Field 3
  ↕ 32dp (pre-action)
Button
  ↕ 24dp
Link
```

### Horizontal Spacing:
```
Container: 24dp padding
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃                             ┃
┃  [Icon]  16dp  [Content]    ┃ ← Row spacing
┃                             ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

---

## 11. Component Dimensions

### Touch Targets:
```
Button:           ┌───────────┐
                  │  56dp     │ ← Minimum 48dp
                  └───────────┘

IconButton:       ┌───────┐
                  │ 48dp  │ ← Material guideline
                  └───────┘

TextField:        ┌───────────┐
                  │  56dp     │ ← With padding
                  └───────────┘
```

### Icons:
```
Small (Chips):    [18dp]  🏏
Standard:         [24dp]  📧
Large (Feature):  [48dp]  🎮
XLarge (Avatar):  [64dp]  👤
Profile Pic:      [120dp] 👤
```

### Cards:
```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃  16dp padding               ┃ ← Internal padding
┃                             ┃   2dp elevation
┃  Content                    ┃   12dp corners
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

---

## 12. Navigation Flow Diagram

```
┌─────────────┐
│   Login     │ ← Entry Point
└──────┬──────┘
       │ login success
       ↓
┌─────────────┐
│    Home     │ ← Tab: Home (default)
└──────┬──────┘
       │ click facility
       ↓
┌─────────────┐
│   Detail    │ ← Stack push
└──────┬──────┘
       │ book
       ↓
┌─────────────┐
│    Home     │ ← Stack pop
└──────┬──────┘
       │ switch tab
       ↓
┌─────────────┐
│  Bookings   │ ← Tab: Bookings
└─────────────┘
       ↕ tab switch
┌─────────────┐
│    Home     │
└─────────────┘
```

---

## 13. Screen State Variations

### Home Screen States:

**Loading:**
```
╔═══════════════════════════════════╗
║           🔄 Loading...           ║
╚═══════════════════════════════════╝
```

**Loaded (with data):**
```
╔═══════════════════════════════════╗
║  [Filters]                        ║
║  [Card 1]                         ║
║  [Card 2]                         ║
║  [Card 3]                         ║
╚═══════════════════════════════════╝
```

**Empty:**
```
╔═══════════════════════════════════╗
║    📭 No facilities available     ║
╚═══════════════════════════════════╝
```

**Error:**
```
╔═══════════════════════════════════╗
║    ⚠️ Failed to load facilities   ║
║         [Retry Button]            ║
╚═══════════════════════════════════╝
```

---

## 14. Responsive Behavior

### Portrait (Phone):
```
┌─────────────────┐
│   TopAppBar     │ 64dp
├─────────────────┤
│                 │
│   Content       │ Fill remaining
│   (Scrollable)  │
│                 │
├─────────────────┤
│  Bottom Nav     │ 80dp
└─────────────────┘
```

### Landscape (Future):
```
┌────────────────────────────────────┐
│  TopAppBar                         │
├────────────────────────────────────┤
│  Wider content, multi-column       │
├────────────────────────────────────┤
│  Bottom Nav (optional: rail)       │
└────────────────────────────────────┘
```

---

## 15. Component Hierarchy

```
MainActivity
└── GameArenaApp (Theme)
    └── Scaffold
        ├── TopAppBar
        │   └── Text("GameArena")
        ├── NavHost
        │   ├── LoginScreen
        │   │   ├── Column
        │   │   ├── GameArenaTextField (x2)
        │   │   └── GameArenaButton
        │   ├── HomeScreen
        │   │   ├── FilterChips Row
        │   │   └── LazyColumn
        │   │       └── FacilityCard (x N)
        │   ├── DetailScreen
        │   │   ├── Facility Card
        │   │   ├── Date Selector (LazyRow)
        │   │   ├── Time Grid (LazyVerticalGrid)
        │   │   └── GameArenaButton
        │   └── BookingHistoryScreen
        │       └── LazyColumn
        │           └── BookingCard (x N)
        └── BottomNavigationBar
            ├── Home Tab
            └── Bookings Tab
```

---

## 16. Animation & Transitions (Future Enhancement)

### Planned Animations:
```
Screen Transitions:
Login → Home:      Fade + Slide Up
Home → Detail:     Slide Left
Detail → Home:     Slide Right (back)
Tab Switch:        Crossfade

Component Animations:
Card Click:        Scale + Ripple
Chip Select:       Color Transition
Button Press:      Scale Down
Loading:           Rotate (Circular Progress)
```

---

## 17. Accessibility Features

### Touch Targets:
- Minimum 48dp for all clickable elements
- Button height: 56dp (exceeds minimum)
- Icon buttons: 48dp

### Color Contrast:
- Text on background: 4.5:1 (WCAG AA)
- Primary on surface: 4.5:1
- Error messages: High contrast red

### Screen Reader Support:
- Content descriptions on icons
- Semantic grouping of elements
- Proper focus order

---

## 📸 Screenshot Checklist

To capture actual screenshots for submission:

✅ Login Screen (complete view)  
✅ Login Screen (error state)  
✅ Sign-Up Screen (complete view)  
✅ Sign-Up Screen (validation error)  
✅ Home Screen (loaded state)  
✅ Home Screen (filtered view)  
✅ Detail Screen (date selection)  
✅ Detail Screen (time selection)  
✅ Booking History (with bookings)  
✅ Profile Screen (complete view)  
✅ Reusable Component (TextField)  
✅ Reusable Component (Button)  
✅ Folder Structure  

---

## 🎨 Design Consistency

All screens follow these principles:
- **8dp spacing grid**
- **12dp corner radius** for inputs/buttons
- **16dp card padding**
- **24dp screen padding**
- **Material Design 3** components
- **Purple primary** color (#6750A4)
- **Consistent typography** scale
- **Touch-friendly** sizes (48dp+)

---

**Document Status**: ✅ Complete  
**Screens Documented**: 6  
**Components Documented**: 7  
**Ready for Screenshots**: Yes  

---

