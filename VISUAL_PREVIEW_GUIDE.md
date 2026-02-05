# 🎨 VISUAL GUIDE - Where to Click

**ASCII art showing exactly where everything is!**

---

## 📺 ANDROID STUDIO WINDOW

```
┌─────────────────────────────────────────────────────────────────────┐
│ File  Edit  View  Navigate  Code  Build  Run  Tools  Window  Help  │
├─────────────────────────────────────────────────────────────────────┤
│                                                                      │
│ ┌──────────────┐ ┌─────────────────────────────────────────────┐  │
│ │              │ │  AddBookingScreen.kt   [Code][Split][Design]│◄─┐│
│ │  Project     │ │                             ▲▲▲▲▲▲▲▲▲▲▲▲▲▲  │  ││
│ │  Tree        │ │                       Click "Split" here!    │  ││
│ │              │ ├─────────────────────────────────────────────┤  ││
│ │ ┌app         │ │ YOUR CODE         │  PREVIEW PANEL          │  ││
│ │  └src        │ │                   │                         │  ││
│ │   └main      │ │ fun AddBooking()  │  ┌─────────────────┐  │  ││
│ │    └ui       │ │ {                 │  │← Add New Book.. │  │  ││
│ │     └screens │ │   Column {        │  ├─────────────────┤  │  ││
│ │      ▶Login  │ │     TextField()   │  │                 │  │  ││
│ │      ▶Add ◄──┼─┼─────Drag here    │  │ Facility Name   │  │  ││
│ │      ▶List   │ │     Button()      │  │ [___________]   │  │  ││
│ │              │ │   }               │  │                 │  │  ││
│ │              │ │ }                 │  │ Location        │  │  ││
│ │              │ │                   │  │ [___________]   │  │  ││
│ │              │ │ @Preview          │  │                 │  │  ││
│ │              │ │ fun Preview() {   │  │ Date            │  │  ││
│ │              │ │   AddBooking()    │  │ [___________]   │  │  ││
│ │              │ │ }                 │  │                 │  │  ││
│ │              │ │                   │  │ [Create Booking]│◄─┐││
│ │              │ │                   │  │                 │  │││
│ │              │ │                   │  └─────────────────┘  │││
│ └──────────────┘ └─────────────────────────────────────────────┘││
│                                                                  ││
└──────────────────────────────────────────────────────────────────┘│
                                                                    │
        UI Preview appears here after clicking "Split" ────────────┘
```

---

## 🎯 STEP-BY-STEP VISUAL

### Step 1: Open File Dialog

```
Press: Ctrl + Shift + N

┌────────────────────────────────────┐
│  Search files by name...          │◄── Type here
│  ┌──────────────────────────────┐ │
│  │ AddBookingScreen             │ │◄── Type this
│  └──────────────────────────────┘ │
│                                    │
│  Results:                          │
│  ┌──────────────────────────────┐ │
│  │ ✓ AddBookingScreen.kt        │ │◄── Press Enter
│  │   ui/screens/                 │ │
│  └──────────────────────────────┘ │
└────────────────────────────────────┘
```

---

### Step 2: Find Split Button

```
File opens, look at top-right:

┌─────────────────────────────────────────────────────┐
│ AddBookingScreen.kt              [Code][Split][Design]│
│                                         ▲▲▲▲▲       │
│                                    Click here!       │
└─────────────────────────────────────────────────────┘
```

---

### Step 3: Split View Appears

```
After clicking "Split":

┌─────────────────────────────────────────────────────────────┐
│ AddBookingScreen.kt              [Code][Split][Design]      │
├──────────────────────────┬──────────────────────────────────┤
│ CODE (Left side)         │ PREVIEW (Right side)             │
├──────────────────────────┼──────────────────────────────────┤
│                          │                                  │
│ @Composable              │  ┌────────────────────────────┐ │
│ fun AddBookingScreen()   │  │ ← Add New Booking         │ │
│ {                        │  ├────────────────────────────┤ │
│   var facilityName       │  │                            │ │
│   var location           │  │ Fill in booking details    │ │
│   var date               │  │                            │ │
│   var timeSlot           │  │ 📍 Facility Name          │ │
│   var price              │  │ [____________________]     │ │
│                          │  │                            │ │
│   Column {               │  │ 📍 Location               │ │
│     TextField(...)       │  │ [____________________]     │ │
│     TextField(...)       │  │                            │ │
│     Button(...)          │  │ 📅 Date                   │ │
│   }                      │  │ [____________________]     │ │
│ }                        │  │                            │ │
│                          │  │ 🕐 Time Slot              │ │
│ @Preview                 │  │ [____________________]     │ │
│ fun Preview() {          │  │                            │ │
│   AddBookingScreen()     │  │ 💰 Price                  │ │
│ }                        │  │ [____________________]     │ │
│                          │  │                            │ │
│                          │  │ [  Create Booking  ]       │ │
│                          │  │ [     Cancel      ]        │ │
│                          │  │                            │ │
│                          │  └────────────────────────────┘ │
│                          │                                  │
└──────────────────────────┴──────────────────────────────────┘
     Edit code here              See UI here!
```

---

## 📱 WHAT EACH SCREEN LOOKS LIKE

### LoginScreen Preview

```
┌───────────────────────────┐
│                           │
│      🎮 GameArena        │
│                           │
│  📧 Email                │
│  [_________________]     │
│                           │
│  🔒 Password             │
│  [_________________] 👁  │
│                           │
│  ┌───────────────────┐  │
│  │   LOG IN   🟢    │  │ ← Neon yellow
│  └───────────────────┘  │
│                           │
│  Don't have account?     │
│  Sign Up                 │
│                           │
└───────────────────────────┘
```

---

### AddBookingScreen Preview

```
┌───────────────────────────┐
│  ← Add New Booking       │
├───────────────────────────┤
│                           │
│  Fill in booking details  │
│  All fields required      │
│                           │
│  📍 Facility Name        │
│  [_________________]     │
│                           │
│  📍 Location             │
│  [_________________]     │
│                           │
│  📅 Date (YYYY-MM-DD)   │
│  [_________________]     │
│                           │
│  🕐 Time Slot            │
│  [_________________]     │
│                           │
│  💰 Price (per hour)     │
│  [_________________]     │
│                           │
│  ┌───────────────────┐  │
│  │ Create Booking 🟢│  │ ← Neon yellow!
│  └───────────────────┘  │
│                           │
│  ┌───────────────────┐  │
│  │     Cancel        │  │
│  └───────────────────┘  │
│                           │
└───────────────────────────┘
```

---

### BookingCard Preview

```
┌───────────────────────────────────┐
│  Cricket Ground A       [Active] │ ← Green badge
│                                   │
│  📍 Downtown Sports Complex      │
│  📅 2026-02-10                   │
│  🕐 10:00 AM - 11:00 AM         │
│  💰 $50/hour                     │
│                                   │
│                    [✏️] [🗑️]     │ ← Edit/Delete
└───────────────────────────────────┘
```

---

## 🖱️ MOUSE CLICKS

### To Open File:
```
Mouse: Click → File → Open
Or: Keyboard: Ctrl + Shift + N
```

### To See Preview:
```
Mouse: Click [Split] button top-right
```

### To Screenshot:
```
Keyboard: Windows + Shift + S
Mouse: Select area with mouse
```

---

## ⌨️ KEYBOARD SHORTCUTS VISUAL

```
┌─────────┬─────────┬─────────┐
│  Ctrl   │    +    │ Shift   │  +  N  →  Open file
└─────────┴─────────┴─────────┘

┌─────────┬─────────┬─────────┐
│  Win    │    +    │ Shift   │  +  S  →  Screenshot
└─────────┴─────────┴─────────┘

┌─────────┬─────────┐
│  Ctrl   │    +    │  S  →  Save (refresh preview)
└─────────┴─────────┘
```

---

## 🎨 COLOR SCHEME VISUAL

```
Your app colors:

┌────────────────────────────────┐
│ Background: #121212            │ ← Very dark gray
├────────────────────────────────┤
│ Cards: #1E1E1E                 │ ← Dark gray
├────────────────────────────────┤
│ Accent: #C1FF00 🟢             │ ← Neon yellow/green
├────────────────────────────────┤
│ Text: #FFFFFF                  │ ← White
├────────────────────────────────┤
│ Text dim: #FFFFFF70            │ ← White 70% opacity
└────────────────────────────────┘
```

---

## 📐 SCREEN LAYOUT VISUAL

```
Portrait phone view (9:16 ratio):

┌─────────────┐
│   Status    │ ← System UI
├─────────────┤
│   Title     │ ← TopAppBar
├─────────────┤
│             │
│             │
│   Content   │ ← Your screen
│             │
│             │
│             │
│             │
├─────────────┤
│  Buttons    │ ← Bottom actions
└─────────────┘
```

---

## 🎯 PREVIEW PANEL CONTROLS

```
Top of preview panel:

┌────────────────────────────────────────────┐
│ [All Previews ▼] [↻] [+] [-] [⚙️] [▶]    │
│    ▲              ▲   ▲   ▲   ▲    ▲       │
│    │              │   │   │   │    │       │
│    Select    Refresh  │   │   │  Run      │
│    preview           Zoom  │   │  preview  │
│                        in   │   Settings   │
│                        Zoom │              │
│                        out  └─────────────┘│
└────────────────────────────────────────────┘
```

---

## 🔄 WORKFLOW VISUAL

```
Start
  │
  ▼
Open Android Studio
  │
  ▼
Ctrl+Shift+N
  │
  ▼
Type filename
  │
  ▼
Press Enter
  │
  ▼
Click "Split"
  │
  ▼
See preview!
  │
  ▼
Take screenshot
  │
  ▼
Use in lab report
  │
  ▼
Done! ✅
```

---

## 🎊 SUCCESS INDICATORS

```
✅ You know it's working when:

┌────────────────────────────────────┐
│ • Right side panel appears         │
│ • Shows your UI design             │
│ • Colors match your theme          │
│ • Dark background visible          │
│ • Neon yellow buttons show         │
│ • No red errors in preview         │
│ • Can scroll in preview            │
└────────────────────────────────────┘
```

---

## ⚠️ TROUBLESHOOTING VISUAL

```
❌ If you see this:

┌────────────────────────────────────┐
│  No preview found                  │
│                                    │
│  Make sure @Preview annotation     │
│  exists in the file                │
└────────────────────────────────────┘

✅ Solution:
1. Scroll to bottom of code
2. Look for: @Preview
3. If missing, file doesn't have preview
4. Try different file
```

---

**🎨 Now you can visualize where everything is! Go try it! 🚀**

---

*Visual Guide Complete*
*See exactly where to click!* 👆
