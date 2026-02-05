# 📝 Code Snippets for Lab Report

**Lab 7: Firebase CRUD Operations - Booking Module**

---

## 1. DATABASE MODEL

### FirebaseBooking.kt

```kotlin
package com.example.helloworldapk.data

/**
 * Firebase Booking Model
 * Used for CRUD operations with Firebase Firestore
 */
data class FirebaseBooking(
    val bookingId: String = "",
    val userId: String = "",
    val facilityName: String = "",
    val location: String = "",
    val date: String = "",
    val timeSlot: String = "",
    val price: Int = 0,
    val status: Boolean = true, // true = active, false = cancelled
    val createdAt: Long = System.currentTimeMillis()
) {
    // No-arg constructor for Firebase
    constructor() : this("", "", "", "", "", "", 0, true, 0)
    
    // Convert to Map for Firebase
    fun toMap(): Map<String, Any> {
        return mapOf(
            "bookingId" to bookingId,
            "userId" to userId,
            "facilityName" to facilityName,
            "location" to location,
            "date" to date,
            "timeSlot" to timeSlot,
            "price" to price,
            "status" to status,
            "createdAt" to createdAt
        )
    }
    
    companion object {
        fun fromMap(map: Map<String, Any>): FirebaseBooking {
            return FirebaseBooking(
                bookingId = map["bookingId"] as? String ?: "",
                userId = map["userId"] as? String ?: "",
                facilityName = map["facilityName"] as? String ?: "",
                location = map["location"] as? String ?: "",
                date = map["date"] as? String ?: "",
                timeSlot = map["timeSlot"] as? String ?: "",
                price = (map["price"] as? Long)?.toInt() ?: 0,
                status = map["status"] as? Boolean ?: true,
                createdAt = map["createdAt"] as? Long ?: 0L
            )
        }
    }
}
```

---

## 2. CREATE OPERATION

### FirebaseBookingRepository.kt - Create Method

```kotlin
/**
 * CREATE - Add new booking to Firestore
 */
suspend fun createBooking(booking: FirebaseBooking): Result<String> {
    return try {
        val docRef = bookingsCollection.document()
        val bookingWithId = booking.copy(bookingId = docRef.id)
        docRef.set(bookingWithId.toMap()).await()
        Result.success(docRef.id)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
```

### FirebaseBookingViewModel.kt - Create Function

```kotlin
/**
 * CREATE - Add new booking
 */
fun createBooking(
    userId: String,
    facilityName: String,
    location: String,
    date: String,
    timeSlot: String,
    price: Int
) {
    viewModelScope.launch {
        _isLoading.value = true
        _error.value = null
        
        val booking = FirebaseBooking(
            userId = userId,
            facilityName = facilityName,
            location = location,
            date = date,
            timeSlot = timeSlot,
            price = price,
            status = true
        )
        
        repository.createBooking(booking)
            .onSuccess {
                _successMessage.value = "Booking created successfully!"
                _isLoading.value = false
            }
            .onFailure { e ->
                _error.value = e.message ?: "Failed to create booking"
                _isLoading.value = false
            }
    }
}
```

---

## 3. READ OPERATION

### FirebaseBookingRepository.kt - Read Method

```kotlin
/**
 * READ - Get all bookings for a user as Flow (real-time updates)
 */
fun getBookingsByUser(userId: String): Flow<List<FirebaseBooking>> = callbackFlow {
    val listener = bookingsCollection
        .whereEqualTo("userId", userId)
        .orderBy("createdAt", Query.Direction.DESCENDING)
        .addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            
            val bookings = snapshot?.documents?.mapNotNull { doc ->
                try {
                    FirebaseBooking.fromMap(doc.data as Map<String, Any>)
                } catch (e: Exception) {
                    null
                }
            } ?: emptyList()
            
            trySend(bookings)
        }
    
    awaitClose { listener.remove() }
}
```

### FirebaseBookingViewModel.kt - Load Function

```kotlin
/**
 * Load bookings for a specific user
 */
fun loadBookings(userId: String) {
    viewModelScope.launch {
        _isLoading.value = true
        try {
            repository.getBookingsByUser(userId).collect { bookingList ->
                _bookings.value = bookingList
                _isLoading.value = false
            }
        } catch (e: Exception) {
            _error.value = e.message ?: "Failed to load bookings"
            _isLoading.value = false
        }
    }
}
```

---

## 4. UPDATE OPERATION

### FirebaseBookingRepository.kt - Update Method

```kotlin
/**
 * UPDATE - Update existing booking
 */
suspend fun updateBooking(bookingId: String, updates: Map<String, Any>): Result<Unit> {
    return try {
        bookingsCollection.document(bookingId).update(updates).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
```

### FirebaseBookingViewModel.kt - Update Function

```kotlin
/**
 * UPDATE - Update existing booking
 */
fun updateBooking(
    bookingId: String,
    facilityName: String,
    location: String,
    date: String,
    timeSlot: String,
    price: Int
) {
    viewModelScope.launch {
        _isLoading.value = true
        _error.value = null
        
        val updates = mapOf(
            "facilityName" to facilityName,
            "location" to location,
            "date" to date,
            "timeSlot" to timeSlot,
            "price" to price
        )
        
        repository.updateBooking(bookingId, updates)
            .onSuccess {
                _successMessage.value = "Booking updated successfully!"
                _isLoading.value = false
            }
            .onFailure { e ->
                _error.value = e.message ?: "Failed to update booking"
                _isLoading.value = false
            }
    }
}
```

---

## 5. DELETE OPERATION

### FirebaseBookingRepository.kt - Delete Method

```kotlin
/**
 * DELETE - Delete booking
 */
suspend fun deleteBooking(bookingId: String): Result<Unit> {
    return try {
        bookingsCollection.document(bookingId).delete().await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
```

### FirebaseBookingViewModel.kt - Delete Function

```kotlin
/**
 * DELETE - Delete booking permanently
 */
fun deleteBooking(bookingId: String) {
    viewModelScope.launch {
        _isLoading.value = true
        _error.value = null
        
        repository.deleteBooking(bookingId)
            .onSuccess {
                _successMessage.value = "Booking deleted successfully!"
                _isLoading.value = false
            }
            .onFailure { e ->
                _error.value = e.message ?: "Failed to delete booking"
                _isLoading.value = false
            }
    }
}
```

---

## 6. UI IMPLEMENTATION

### AddBookingScreen.kt - Form with Validation

```kotlin
// Facility Name Input with Validation
OutlinedTextField(
    value = facilityName,
    onValueChange = { 
        facilityName = it
        facilityNameError = false
    },
    label = { Text("Facility Name") },
    leadingIcon = {
        Icon(Icons.Default.LocationOn, contentDescription = null)
    },
    isError = facilityNameError,
    supportingText = {
        if (facilityNameError) Text("Facility name is required")
    },
    modifier = Modifier.fillMaxWidth(),
    colors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color(0xFFC1FF00),
        focusedLabelColor = Color(0xFFC1FF00),
        cursorColor = Color(0xFFC1FF00),
        unfocusedBorderColor = Color.Gray,
        unfocusedLabelColor = Color.Gray,
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White
    )
)

// Submit Button with Loading State
Button(
    onClick = {
        // Validate inputs
        facilityNameError = facilityName.isBlank()
        locationError = location.isBlank()
        dateError = date.isBlank()
        timeSlotError = timeSlot.isBlank()
        priceError = price.isBlank()
        
        if (!facilityNameError && !locationError && !dateError && 
            !timeSlotError && !priceError) {
            viewModel.createBooking(
                userId = userId,
                facilityName = facilityName,
                location = location,
                date = date,
                timeSlot = timeSlot,
                price = price.toIntOrNull() ?: 0
            )
        }
    },
    modifier = Modifier.fillMaxWidth().height(56.dp),
    enabled = !isLoading,
    colors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFFC1FF00),
        contentColor = Color.Black
    )
) {
    if (isLoading) {
        CircularProgressIndicator(
            color = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    } else {
        Text(
            text = "Create Booking",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}
```

### BookingListScreen.kt - Display Bookings

```kotlin
LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
) {
    items(bookings) { booking ->
        BookingCard(
            booking = booking,
            onEdit = { onNavigateToEditBooking(booking) },
            onDelete = {
                bookingToDelete = booking
                showDeleteDialog = true
            }
        )
    }
}
```

### BookingCard.kt - Card Component

```kotlin
@Composable
fun BookingCard(
    booking: FirebaseBooking,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            // Facility Name
            Text(
                text = booking.facilityName,
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFFC1FF00),
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Location
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = booking.location,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f)
                )
            }
            
            // Date, Time, Price...
            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onEdit) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = Color(0xFFC1FF00)
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color(0xFFFF5252)
                    )
                }
            }
        }
    }
}
```

---

## 7. NAVIGATION

### Navigation.kt - Routes

```kotlin
sealed class Screen(val route: String) {
    // ... existing routes
    
    // Firebase CRUD Screens
    object BookingList : Screen("booking_list")
    object AddBooking : Screen("add_booking")
    object EditBooking : Screen("edit_booking/{bookingData}") {
        fun createRoute(bookingData: String) = "edit_booking/$bookingData"
    }
}
```

### MainActivity.kt - Navigation Setup

```kotlin
// Firebase CRUD Booking Routes
composable(Screen.BookingList.route) {
    val viewModel: FirebaseBookingViewModel = viewModel()
    val userId = userPreferences.getUserId()

    BookingListScreen(
        userId = userId,
        onNavigateToAddBooking = {
            navController.navigate(Screen.AddBooking.route)
        },
        onNavigateToEditBooking = { booking ->
            val gson = Gson()
            val bookingJson = gson.toJson(booking)
            val encodedJson = java.net.URLEncoder.encode(bookingJson, "UTF-8")
            navController.navigate(Screen.EditBooking.createRoute(encodedJson))
        },
        viewModel = viewModel
    )
}

composable(Screen.AddBooking.route) {
    val viewModel: FirebaseBookingViewModel = viewModel()
    val userId = userPreferences.getUserId()

    AddBookingScreen(
        userId = userId,
        onNavigateBack = { navController.popBackStack() },
        viewModel = viewModel
    )
}
```

---

## 8. DEPENDENCIES

### build.gradle.kts (app level)

```kotlin
dependencies {
    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
    
    // Gson for JSON serialization
    implementation("com.google.code.gson:gson:2.10.1")
    
    // Room Database (existing)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}
```

### libs.versions.toml

```toml
[versions]
firebaseBom = "33.7.0"
googleServices = "4.4.2"

[libraries]
firebase-bom = { group = "com.google.firebase", name = "firebase-bom", version.ref = "firebaseBom" }
firebase-auth-ktx = { group = "com.google.firebase", name = "firebase-auth-ktx" }
firebase-firestore-ktx = { group = "com.google.firebase", name = "firebase-firestore-ktx" }

[plugins]
google-services = { id = "com.google.gms.google-services", version.ref = "googleServices" }
```

---

## 📊 DATABASE SCHEMA

### Firestore Collection Structure

```
bookings (collection)
├── abc123xyz (document)
│   ├── bookingId: "abc123xyz"
│   ├── userId: "user_uuid_12345"
│   ├── facilityName: "Cricket Ground A"
│   ├── location: "Downtown Sports Complex"
│   ├── date: "2026-02-10"
│   ├── timeSlot: "10:00 AM - 11:00 AM"
│   ├── price: 50
│   ├── status: true
│   └── createdAt: 1738800000000
├── def456uvw (document)
│   └── ...
└── ...
```

---

## 🎯 KEY FEATURES

### 1. Real-time Updates
- Uses Firestore `addSnapshotListener`
- UI updates automatically when data changes
- No manual refresh needed

### 2. Input Validation
- Client-side validation before submission
- Error messages below each field
- Form only submits when all fields valid

### 3. Loading States
- CircularProgressIndicator during operations
- Disabled buttons during loading
- User knows operation is in progress

### 4. Error Handling
- Try-catch blocks in repository
- Result<T> return type
- User-friendly error messages

### 5. MVVM Architecture
- Separation of concerns
- Testable code
- Reactive state management with Flow

---

**Use these code snippets in your lab report to demonstrate your implementation!**
