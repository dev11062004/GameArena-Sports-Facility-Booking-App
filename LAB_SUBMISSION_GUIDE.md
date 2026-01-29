# 🎓 Lab Assignment Submission - GameArena Wireframes & UI Flow

**Student Project**: GameArena - Sports Facility Booking Application  
**Assignment**: Low-Fidelity Wireframes and UI Screen Navigation Flow  
**Date**: January 26, 2026  
**Platform**: Android Mobile Application

---

## 📋 Assignment Overview

### Objective
To create low-fidelity wireframes and design a complete UI screen navigation flow for the GameArena mobile application. This includes defining visual structure, screen layout, component placement, and user experience flow.

### What We've Created
✅ **Complete wireframe documentation** for all 3 main screens  
✅ **Interactive HTML wireframes** for visual demonstration  
✅ **UI navigation flow diagrams** with state transitions  
✅ **User journey maps** showing complete booking flows  
✅ **Component specifications** with dimensions and styling  
✅ **Design annotations** and accessibility guidelines  

---

## 📁 Deliverable Files

### 1. **WIREFRAMES_DOCUMENTATION.md** 📄
**Location**: `D:\AndriodStudioAPK\WIREFRAMES_DOCUMENTATION.md`

**Contents**:
- Complete ASCII art wireframes for all 3 screens
- Component specifications (dimensions, spacing, colors)
- Typography scale and design tokens
- User journey maps with step-by-step flows
- Design rationale and accessibility notes
- Tool recommendations (Figma, Adobe XD, etc.)
- Deliverables checklist for submission

**Key Sections**:
```
1. Introduction & Overview
2. Screen Inventory (3 screens)
3. Low-Fidelity Wireframes
   - Home Screen (Browse & Filter)
   - Facility Detail Screen (Book)
   - Booking History Screen (Manage)
4. UI Navigation Flow Diagram
5. Component Specifications
6. User Journey Maps
7. Design Annotations
```

---

### 2. **UI_FLOW_DIAGRAMS.md** 🔄
**Location**: `D:\AndriodStudioAPK\UI_FLOW_DIAGRAMS.md`

**Contents**:
- Mermaid diagram code (copy-paste ready)
- Complete application flow diagrams
- Screen-by-screen state transitions
- Data flow architecture
- User interaction flow matrix
- Error handling flows
- Animation timing diagrams
- Responsive layout guidelines

**Key Features**:
```
✓ Mermaid Live Editor compatible code
✓ State diagram for each screen
✓ Swimlane user journey diagram
✓ Component hierarchy tree
✓ MVVM data flow visualization
✓ Keyboard navigation flow
✓ Design tokens reference
```

---

### 3. **INTERACTIVE_WIREFRAMES.html** 🎨
**Location**: `D:\AndriodStudioAPK\INTERACTIVE_WIREFRAMES.html`

**How to Use**: 
- Double-click the file to open in your browser
- Fully interactive wireframes
- Click chips, buttons, and tabs to see interactions
- Visual navigation flow diagrams included

**Features**:
```
✓ All 3 screens rendered as phone mockups
✓ Clickable filter chips and time slots
✓ Interactive bottom navigation
✓ Color-coded time slot availability
✓ Complete navigation flow visualization
✓ User journey breakdown
✓ Responsive design (works on mobile too!)
```

---

## 🖼️ Wireframe Summary

### Screen 1: Home Screen 🏠
**Purpose**: Browse and filter available sports facilities

**Components**:
- Top App Bar (64dp) - "GameArena" title
- Filter Chip Row - All, Cricket, Pool, Pickleball
- Facility List (LazyColumn) - Scrollable cards
- Facility Cards - Icon, name, description, price
- Bottom Navigation - Home (active), Bookings

**User Actions**:
- Tap filter chip → Show filtered results
- Tap facility card → Navigate to detail screen
- Tap Bookings tab → Switch to history screen

**State Management**:
- Loading: Show progress indicator
- Loaded: Display facility list (8 items)
- Filtered: Show subset based on sport type
- Empty: Show "No facilities available"

---

### Screen 2: Facility Detail Screen 📋
**Purpose**: Select date/time and book a facility

**Components**:
- Top App Bar with Back Button
- Facility Info Card - Icon, name, price, description
- Date Selector (Horizontal scroll) - Next 7 days
- Time Slot Grid (3 columns) - 6 AM to 10 PM
- Legend - Color meanings (Green/Gray/Blue)
- Book Now Button - Shows calculated price

**User Actions**:
- Tap back button → Return to home
- Select date chip → Load time slots for that date
- Select time slot → Enable booking button
- Tap Book Now → Create booking, return to home

**State Management**:
- Loading: Show facility placeholder
- Facility Loaded: Display info, no date selected
- Date Selected: Show time slots (green=available, gray=booked)
- Time Selected: Enable button with price
- Booking Processing: Show loading indicator
- Booking Success: Navigate to home

---

### Screen 3: Booking History Screen 📜
**Purpose**: View and manage user bookings

**Components**:
- Top App Bar - "My Bookings" title
- Booking List (LazyColumn) - Scrollable cards
- Booking Cards - ID, status, facility, date, time, price
- Status Badges - Confirmed (green), Cancelled (red)
- Cancel Buttons - Only for future confirmed bookings
- Bottom Navigation - Home, Bookings (active)

**User Actions**:
- Tap Home tab → Switch to home screen
- Tap Cancel Booking → Update status to cancelled
- Scroll list → View all bookings

**State Management**:
- Loading: Show progress indicator
- Empty: Show "No bookings yet" with icon
- Loaded: Display booking list (latest first)
- Cancelling: Update specific booking status
- Cancelled: Refresh list, disable cancel button

---

## 🔄 Navigation Flow Summary

### Primary Navigation Pattern

```
App Launch
    ↓
┌─────────────────┐
│   HOME SCREEN   │ ← Start Destination
│   (Browse)      │
└────────┬────────┘
         │
         ├─→ Tap Facility → DETAIL SCREEN → Book → Return to HOME
         │
         └─→ Tap Bookings Tab → HISTORY SCREEN ↔ HOME
```

### Navigation Types

**1. Stack Navigation (Push/Pop)**
- Home → Facility Detail (Push onto stack)
- Detail → Home (Pop from stack after booking)
- Back button always pops stack

**2. Tab Navigation (No Stack)**
- Home ↔ Bookings History
- Bottom navigation bar
- Switch between root destinations
- No back stack maintained

**3. Deep Linking (Future)**
- `gamearena://home`
- `gamearena://facility/{id}`
- `gamearena://bookings`

---

## 👤 User Journey: Complete Booking Flow

### Journey Steps (60-90 seconds total)

**Step 1: App Launch (0s)**
```
User opens GameArena app
→ Home Screen loads
→ 8 facilities displayed
→ All filter selected by default
```

**Step 2: Filter (Optional) (+5s)**
```
User taps "Cricket" filter chip
→ Chip turns purple (selected)
→ List updates to show 2 cricket grounds
→ Other facilities hidden
```

**Step 3: Select Facility (+10s)**
```
User taps "Cricket Ground A" card
→ Screen transitions (300ms)
→ Detail screen loads
→ Shows facility info, date selector, empty time slots
```

**Step 4: Select Date (+15s)**
```
User scrolls date chips
→ Sees next 7 days (Today, Jan 27, 28, 29...)
→ Taps "Jan 27" (tomorrow)
→ Chip highlights blue
→ Time slots load (check DB for bookings)
→ Green = available, Gray = already booked
```

**Step 5: Select Time (+25s)**
```
User views time slot grid (3 columns)
→ Sees 16 slots (6 AM - 10 PM)
→ Taps "10:00" slot (available/green)
→ Slot turns blue (selected)
→ Book button enables
→ Shows "Book Now - $50.00"
```

**Step 6: Confirm Booking (+40s)**
```
User taps "Book Now" button
→ Booking processing (loading indicator)
→ Database insert (Booking #1234)
→ Success state (bookingSuccess = true)
→ Navigate back to Home (300ms transition)
→ Booking confirmed!
```

**Step 7: Verify (Optional) (+60s)**
```
User taps "Bookings" tab
→ Switches to History screen
→ Sees new booking at top
→ Booking #1234 | Cricket Ground A
→ Status: Confirmed ✓
→ Jan 27, 10:00-11:00 | $50.00
→ Cancel button available
```

**Total Time**: 60-90 seconds  
**Screens Visited**: 3 (Home → Detail → Home → History)  
**User Interactions**: 6 taps  
**Database Operations**: 1 insert query  

---

## 🎨 Design Specifications

### Color Palette (Material Design 3)

```yaml
Primary:                #6750A4
On Primary:             #FFFFFF
Primary Container:      #EADDFF
On Primary Container:   #21005D

Success (Available):    #4CAF50
Info (Selected):        #2196F3
Error/Cancelled:        #F44336
Disabled (Booked):      #9E9E9E

Background:             #FFFBFE
Surface:                #FFFBFE
Outline:                #79747E
```

### Spacing System

```yaml
XS:  4dp   - Tight spacing
SM:  8dp   - Small gaps
MD:  12dp  - Medium gaps (default grid)
LG:  16dp  - Large padding
XL:  24dp  - Section spacing
XXL: 32dp  - Screen margins
```

### Typography Scale

```yaml
Headline Small:   24sp (Screen titles)
Title Large:      22sp (App bar titles)
Title Medium:     16sp (Card titles, buttons)
Body Medium:      14sp (Descriptions)
Body Small:       12sp (Supporting text)
Label Medium:     12sp (Nav labels)
```

### Component Dimensions

```yaml
Top App Bar:       64dp height
Bottom Nav:        80dp height
Facility Card:     120dp min height
Time Slot:         48dp height
Button:            48dp height
Filter Chip:       32dp height
Icon (Small):      18dp
Icon (Medium):     24dp
Icon (Large):      48dp
Touch Target:      48dp minimum
```

---

## 🎯 Key Features Demonstrated

### 1. **Filter & Search**
- Real-time filtering by sport type
- Visual feedback (selected chip color change)
- List updates without navigation

### 2. **Date Selection**
- Horizontal scrollable date chips
- Shows next 7 days from today
- "Today" label for current date
- Selected state persists

### 3. **Time Slot Availability**
- Color-coded status:
  - 🟢 Green = Available (clickable)
  - 🔵 Blue = Selected by user
  - ⚪ Gray = Already booked (disabled)
- 3-column grid layout (fits phone screens)
- 1-hour slots from 6 AM to 10 PM

### 4. **Dynamic Pricing**
- Price updates based on facility and duration
- Shown on booking button: "Book Now - $50.00"
- Clear pricing in facility cards and details

### 5. **Booking Management**
- View all bookings (latest first)
- Status badges (Confirmed, Cancelled, Completed)
- Cancel functionality (only future confirmed)
- Booking IDs for reference

### 6. **Persistent Navigation**
- Bottom navigation always visible (except detail)
- Selected state indicated
- Tab switching without losing state

---

## ♿ Accessibility Features

### Screen Reader Support
```
✓ All components have content descriptions
✓ Logical focus order (top to bottom, left to right)
✓ Grouping of related items
✓ Status announcements (filter applied, booking confirmed)
```

### Visual Accessibility
```
✓ Minimum text contrast: 4.5:1 (WCAG AA)
✓ Large touch targets: 48dp minimum
✓ Color is not the only indicator (text + color)
✓ Clear visual focus indicators
```

### Keyboard Navigation
```
✓ Tab: Next element
✓ Shift+Tab: Previous element
✓ Enter/Space: Activate button
✓ Arrow keys: Navigate grid/list
✓ Escape: Go back/close
```

---

## 📱 Responsive Design

### Phone Portrait (360-480dp)
```
✓ Bottom navigation bar
✓ Single column facility list
✓ 3-column time slot grid
✓ Full-width cards
```

### Phone Landscape (640-900dp)
```
✓ Bottom navigation bar
✓ 2-column facility grid
✓ 4-column time slot grid
✓ Content width: 80% centered
```

### Tablet Portrait (600-840dp)
```
✓ Navigation rail (left side)
✓ 2-column facility grid
✓ 4-column time slot grid
✓ Max content width: 600dp
```

### Tablet Landscape (900dp+)
```
✓ Navigation rail (permanent)
✓ 2-pane layout (list + detail side-by-side)
✓ 3-column facility grid
✓ 6-column time slot grid
✓ Max content width: 840dp
```

---

## 🛠️ Tools & Resources

### For Creating Wireframes

**Recommended: Figma (Free)**
- URL: https://figma.com
- Sign up for free account
- Use phone frame (360x800dp)
- Material Design plugin available
- Export as PDF or PNG

**Alternative: Draw.io (Free)**
- URL: https://app.diagrams.net
- No signup required
- Android mockup templates
- Simple drag-and-drop

**Alternative: Adobe XD (Free tier)**
- URL: https://adobe.com/products/xd
- Professional design tool
- Repeat grid feature
- Prototyping support

### For Creating Flow Diagrams

**Mermaid Live Editor**
- URL: https://mermaid.live
- Paste code from UI_FLOW_DIAGRAMS.md
- Export as SVG or PNG
- Free, no signup

**Lucidchart**
- URL: https://lucidchart.com
- Flowchart templates
- Collaboration features
- Free tier available

### Material Design Resources

- **Guidelines**: https://m3.material.io
- **Icons**: https://fonts.google.com/icons
- **Color Tool**: https://m3.material.io/theme-builder
- **Typography**: https://m3.material.io/styles/typography

---

## 📤 How to Submit Your Lab

### Submission Checklist

- [ ] **Wireframes** (3 screens minimum)
  - Export INTERACTIVE_WIREFRAMES.html to PDF
  - Or create in Figma and export
  - Include all 3 screens: Home, Detail, History

- [ ] **Navigation Flow Diagram**
  - Use mermaid code from UI_FLOW_DIAGRAMS.md
  - Generate diagram at mermaid.live
  - Export as image or PDF

- [ ] **Documentation**
  - Include WIREFRAMES_DOCUMENTATION.md
  - Or write your own based on the template
  - Explain design decisions

- [ ] **User Journey Map**
  - Document at least 1 complete flow
  - Include timing estimates
  - Show expected outcomes

### Submission Format

**Option 1: PDF Report**
```
GameArena_Wireframes_[YourName].pdf

Contents:
1. Cover page with project name
2. Introduction (app overview)
3. Wireframes (3 screens with annotations)
4. Navigation flow diagram
5. User journey map
6. Component specifications table
7. Design rationale
8. Conclusion
```

**Option 2: Digital Submission**
```
Zip file: GameArena_Wireframes_[YourName].zip

Contains:
- wireframes/ (PNG or PDF of each screen)
- navigation_flow.png (Flow diagram)
- documentation.md (Written explanation)
- interactive.html (INTERACTIVE_WIREFRAMES.html)
```

**Option 3: Figma Link**
```
Share public Figma link with:
- All 3 screen wireframes
- Prototype connections showing navigation
- Annotations explaining components
- User flow documented in comments
```

---

## 🎓 Learning Outcomes

By completing this wireframe exercise, you should now understand:

✅ **What wireframes are** - Blueprints showing structure without styling  
✅ **Why they matter** - Define UX before coding  
✅ **How to create them** - Using tools like Figma or draw.io  
✅ **Navigation patterns** - Stack vs. tab navigation  
✅ **User flows** - Mapping complete user journeys  
✅ **Component specs** - Dimensions, spacing, typography  
✅ **Accessibility** - Designing for all users  
✅ **State management** - How screens change based on data  

---

## 🚀 Next Steps After Wireframes

### Lab Progression

**Current Lab**: ✅ Wireframes & UI Flow (COMPLETED)

**Next Labs**:

1. **UI Components Lab**
   - Implement filter chips in Compose
   - Create facility card component
   - Build time slot grid
   - Style with Material 3

2. **Navigation Lab**
   - Set up Navigation Component
   - Implement bottom navigation
   - Add back stack handling
   - Test deep links

3. **Database Lab**
   - Create Room entities
   - Write DAO queries
   - Test CRUD operations
   - Seed initial data

4. **State Management Lab**
   - Build ViewModels
   - Implement UI states
   - Handle loading/error states
   - Add form validation

5. **Integration Lab**
   - Connect ViewModels to screens
   - Wire up navigation
   - Test complete user flows
   - Add error handling

6. **Testing Lab**
   - Write unit tests
   - Create UI tests
   - Test edge cases
   - Performance testing

---

## 💡 Tips for Success

### Design Tips

1. **Keep It Simple**
   - Wireframes focus on layout, not decoration
   - Use grayscale or basic colors
   - Don't worry about final styling yet

2. **Think Mobile-First**
   - Design for one-handed use
   - Important actions at bottom (thumb zone)
   - Large touch targets (48dp minimum)

3. **Be Consistent**
   - Use same spacing throughout
   - Consistent component sizes
   - Follow Material Design patterns

4. **Show All States**
   - Loading states (progress indicators)
   - Empty states ("No bookings yet")
   - Error states (retry buttons)
   - Success states (confirmations)

### Documentation Tips

1. **Annotate Everything**
   - Label all components
   - Show dimensions
   - Explain interactions
   - Note edge cases

2. **Tell the Story**
   - Walk through user journey
   - Explain design decisions
   - Show before/after states
   - Include alternative flows

3. **Be Specific**
   - "16dp padding" not "some space"
   - "Title Medium (16sp)" not "medium text"
   - "Primary color (#6750A4)" not "purple"

---

## 📞 Support & Resources

### If You Get Stuck

**Documentation**:
- Read WIREFRAMES_DOCUMENTATION.md (detailed specs)
- Check UI_FLOW_DIAGRAMS.md (navigation patterns)
- View INTERACTIVE_WIREFRAMES.html (working example)

**Tools**:
- Figma tutorials: https://help.figma.com
- Material Design: https://m3.material.io
- Jetpack Compose: https://developer.android.com/jetpack/compose

**Community**:
- Stack Overflow (tag: android-jetpack-compose)
- Reddit: r/androiddev
- Material Design Discord

---

## ✨ Summary

You now have **complete wireframe documentation** for the GameArena app:

### What You Have
1. ✅ **3 detailed wireframes** (Home, Detail, History)
2. ✅ **Complete navigation flow** with state diagrams
3. ✅ **Interactive HTML demo** (open in browser)
4. ✅ **User journey maps** with timing
5. ✅ **Component specifications** (dimensions, colors, typography)
6. ✅ **Design tokens** for consistent styling
7. ✅ **Accessibility guidelines** (WCAG AA compliant)
8. ✅ **Responsive design** considerations

### How to Use Them
- **For Lab Submission**: Export HTML to PDF or create Figma version
- **For Development**: Reference specs when building UI
- **For Collaboration**: Share with team for feedback
- **For Learning**: Study navigation patterns and state management

### Files Created
```
D:\AndriodStudioAPK\
├── WIREFRAMES_DOCUMENTATION.md      (Complete specs & ASCII wireframes)
├── UI_FLOW_DIAGRAMS.md             (Mermaid diagrams & flow charts)
├── INTERACTIVE_WIREFRAMES.html     (Visual demo - OPEN IN BROWSER!)
└── LAB_SUBMISSION_GUIDE.md         (This file - submission instructions)
```

---

## 🎉 Congratulations!

You've completed comprehensive wireframe documentation for GameArena. These wireframes will serve as the blueprint for building the actual application in future labs.

**What makes these wireframes great:**
- ✅ Clear visual hierarchy
- ✅ Logical user flows
- ✅ Complete state management
- ✅ Accessibility considered
- ✅ Responsive design planned
- ✅ Well documented

**Ready to submit?**
1. Open INTERACTIVE_WIREFRAMES.html in browser
2. Print to PDF or take screenshots
3. Include UI_FLOW_DIAGRAMS.md content
4. Write brief design rationale
5. Submit according to your instructor's requirements

---

**Good luck with your lab submission!** 🚀📱

**Questions?** Review the documentation files or consult Material Design guidelines.

**Next**: Start building the actual UI components in Jetpack Compose!

---

**Document Version**: 1.0  
**Created**: January 26, 2026  
**Project**: GameArena - Sports Facility Booking App  
**Assignment**: Low-Fidelity Wireframes & UI Navigation Flow
