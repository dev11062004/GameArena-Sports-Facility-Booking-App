# Lab 7: Implementation of UI Components & User Controls

**Student Name:** [Your Name Here]
**Student ID:** [Your ID Here]

## 1. Introduction
In this lab, we implemented essential UI components to enhance the user experience of the Sports Facility Booking App. The application now includes functional forms for user registration, interactive lists for facilities and bookings, navigation between screens, and feedback mechanisms like confirmation dialogs.

## 2. Implementation Details

### A. Forms (Registration Screen)
We created a `RegistrationScreen` that includes:
-   **Text Fields:** Full Name, Email, Password, Confirm Password.
-   **Radio Buttons:** Gender selection (Male/Female/Other).
-   **Checkbox:** Terms & Conditions acceptance.
-   **Validation:** Ensures all fields are filled, passwords match, and terms are accepted.

**Code Snippet (Registration Form):**
```kotlin
// RegistrationScreen.kt (Partial)
Column(modifier = Modifier.padding(24.dp).verticalScroll(scrollState)) {
    // Text Fields
    GameArenaTextField(value = fullName, onValueChange = { fullName = it }, label = "Full Name")
    GameArenaTextField(value = email, onValueChange = { email = it }, label = "Email Address")
    
    // Gender Radio Buttons
    Row {
        radioOptions.forEach { text ->
            Row(Modifier.selectable(selected = (text == selectedGender), onClick = { selectedGender = text })) {
                RadioButton(selected = (text == selectedGender), onClick = null)
                Text(text = text)
            }
        }
    }

    // Terms Checkbox
    Row {
        Checkbox(checked = termsAccepted, onCheckedChange = { termsAccepted = it })
        Text("I agree to the Terms & Conditions")
    }

    // Sign Up Button with Validation
    GameArenaButton(text = "Sign Up", onClick = {
        if (fullName.isNotBlank() && termsAccepted) {
            onRegistrationSuccess()
        } else {
            errorMessage = "Please complete the form"
        }
    })
}
```

### B. List & Card Views (Dashboard & History)
We used `LazyColumn` for displaying lists of facilities and bookings efficiently. Each item is rendered using a `Card` composable.

**Code Snippet (Facility List & Card):**
```kotlin
// HomeScreen.kt (Partial)
LazyColumn(contentPadding = PaddingValues(16.dp)) {
    items(uiState.facilities) { facility ->
        FacilityCard(facility = facility, onClick = { onFacilityClick(facility.id) })
    }
}

// FacilityCard Component
Card(elevation = CardDefaults.cardElevation(2.dp)) {
    Row(modifier = Modifier.padding(16.dp)) {
        Icon(imageVector = getIconForType(facility.type), contentDescription = null)
        Column {
            Text(text = facility.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "$${facility.hourlyRate}/hour", color = MaterialTheme.colorScheme.primary)
        }
    }
}
```

### C. Navigation UI
The app uses Jetpack Compose Navigation with a Bottom Navigation Bar for the main screens (Home, History) and standard navigation for Auth screens.

**Code Snippet (Navigation Graph):**
```kotlin
// MainActivity.kt (Partial)
NavHost(navController = navController, startDestination = Screen.Login.route) {
    composable(Screen.Login.route) { 
        LoginScreen(
            onLoginSuccess = { navController.navigate(Screen.Home.route) },
            onSignUpClick = { navController.navigate(Screen.Registration.route) } // Navigate to Registration
        )
    }
    composable(Screen.Registration.route) {
        RegistrationScreen(
            onRegistrationSuccess = { navController.navigate(Screen.Home.route) },
            onBackClick = { navController.popBackStack() }
        )
    }
    // ... Home and other routes
}
```

### D. Feedback Mechanisms (Dialogs)
We implemented an `AlertDialog` to confirm booking cancellations, preventing accidental actions.

**Code Snippet (Cancellation Dialog):**
```kotlin
// BookingHistoryScreen.kt (Partial)
if (showDialog) {
    AlertDialog(
        onDismissRequest = { showDialog = false },
        title = { Text("Cancel Booking") },
        text = { Text("Are you sure you want to cancel this booking?") },
        confirmButton = {
            TextButton(onClick = { 
                showDialog = false
                onCancelBooking(booking.id) 
            }) { Text("Yes") }
        },
        dismissButton = {
            TextButton(onClick = { showDialog = false }) { Text("No") }
        }
    )
}
```

## 3. Screenshots

| Login Screen | Registration Screen |
| :---: | :---: |
| ![Login Screen Placeholder](placeholder_login.png) | ![Registration Screen Placeholder](placeholder_registration.png) |
| *Login Form* | *Form with Radio & Checkbox* |

| Dashboard (List) | Booking History (Dialog) |
| :---: | :---: |
| ![Dashboard Placeholder](placeholder_dashboard.png) | ![Dialog Placeholder](placeholder_dialog.png) |
| *Facility List with Cards* | *Cancellation Alert Dialog* |

## 4. Conclusion
The implementation of standard UI components and controls has significantly improved the app's structure and usability. The registration flow allows for user onboarding, the dashboard presents data clearly using cards, and the feedback dialogs ensure safe user interactions. The app is now ready for further API integration.
