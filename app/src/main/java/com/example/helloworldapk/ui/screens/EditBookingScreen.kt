package com.example.helloworldapk.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.helloworldapk.data.FirebaseBooking
import com.example.helloworldapk.ui.viewmodel.FirebaseBookingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBookingScreen(
    booking: FirebaseBooking,
    onNavigateBack: () -> Unit,
    viewModel: FirebaseBookingViewModel = viewModel()
) {
    var facilityName by remember { mutableStateOf(booking.facilityName) }
    var location by remember { mutableStateOf(booking.location) }
    var date by remember { mutableStateOf(booking.date) }
    var timeSlot by remember { mutableStateOf(booking.timeSlot) }
    var price by remember { mutableStateOf(booking.price.toString()) }

    var facilityNameError by remember { mutableStateOf(false) }
    var locationError by remember { mutableStateOf(false) }
    var dateError by remember { mutableStateOf(false) }
    var timeSlotError by remember { mutableStateOf(false) }
    var priceError by remember { mutableStateOf(false) }

    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val successMessage by viewModel.successMessage.collectAsState()

    // Handle success
    LaunchedEffect(successMessage) {
        if (successMessage != null) {
            onNavigateBack()
            viewModel.clearSuccess()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Booking") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF121212),
                    titleContentColor = Color(0xFFC1FF00),
                    navigationIconContentColor = Color(0xFFC1FF00)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Instructions
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1E1E1E)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Update booking details",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFFC1FF00),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Booking ID: ${booking.bookingId}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.5f)
                    )
                }
            }

            // Facility Name
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

            // Location
            OutlinedTextField(
                value = location,
                onValueChange = {
                    location = it
                    locationError = false
                },
                label = { Text("Location") },
                leadingIcon = {
                    Icon(Icons.Default.LocationOn, contentDescription = null)
                },
                isError = locationError,
                supportingText = {
                    if (locationError) Text("Location is required")
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

            // Date
            OutlinedTextField(
                value = date,
                onValueChange = {
                    date = it
                    dateError = false
                },
                label = { Text("Date (YYYY-MM-DD)") },
                leadingIcon = {
                    Icon(Icons.Default.CalendarToday, contentDescription = null)
                },
                isError = dateError,
                supportingText = {
                    if (dateError) Text("Date is required")
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

            // Time Slot
            OutlinedTextField(
                value = timeSlot,
                onValueChange = {
                    timeSlot = it
                    timeSlotError = false
                },
                label = { Text("Time Slot") },
                leadingIcon = {
                    Icon(Icons.Default.Schedule, contentDescription = null)
                },
                isError = timeSlotError,
                supportingText = {
                    if (timeSlotError) Text("Time slot is required")
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

            // Price
            OutlinedTextField(
                value = price,
                onValueChange = {
                    price = it.filter { char -> char.isDigit() }
                    priceError = false
                },
                label = { Text("Price (per hour)") },
                leadingIcon = {
                    Icon(Icons.Default.AttachMoney, contentDescription = null)
                },
                isError = priceError,
                supportingText = {
                    if (priceError) Text("Price is required")
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

            // Error Message
            if (error != null) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFF5252).copy(alpha = 0.2f)
                    )
                ) {
                    Text(
                        text = error ?: "",
                        color = Color(0xFFFF5252),
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Update Button
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
                        viewModel.updateBooking(
                            bookingId = booking.bookingId,
                            facilityName = facilityName,
                            location = location,
                            date = date,
                            timeSlot = timeSlot,
                            price = price.toIntOrNull() ?: 0
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
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
                        text = "Update Booking",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Cancel Button
            OutlinedButton(
                onClick = onNavigateBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = !isLoading,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFFC1FF00)
                )
            ) {
                Text(
                    text = "Cancel",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
