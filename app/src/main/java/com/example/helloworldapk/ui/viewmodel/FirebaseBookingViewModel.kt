package com.example.helloworldapk.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworldapk.data.FirebaseBooking
import com.example.helloworldapk.data.repository.FirebaseBookingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing Firebase Booking CRUD operations
 */
class FirebaseBookingViewModel : ViewModel() {

    private val repository = FirebaseBookingRepository()

    private val _bookings = MutableStateFlow<List<FirebaseBooking>>(emptyList())
    val bookings: StateFlow<List<FirebaseBooking>> = _bookings

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _successMessage = MutableStateFlow<String?>(null)
    val successMessage: StateFlow<String?> = _successMessage

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

    /**
     * CANCEL - Cancel booking (soft delete)
     */
    fun cancelBooking(bookingId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            repository.cancelBooking(bookingId)
                .onSuccess {
                    _successMessage.value = "Booking cancelled successfully!"
                    _isLoading.value = false
                }
                .onFailure { e ->
                    _error.value = e.message ?: "Failed to cancel booking"
                    _isLoading.value = false
                }
        }
    }

    /**
     * Clear error message
     */
    fun clearError() {
        _error.value = null
    }

    /**
     * Clear success message
     */
    fun clearSuccess() {
        _successMessage.value = null
    }
}
