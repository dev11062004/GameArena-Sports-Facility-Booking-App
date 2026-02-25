package com.example.gamearena.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gamearena.data.Booking
import com.example.gamearena.data.repository.BookingRepository
import com.example.gamearena.data.repository.FacilityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class BookingHistoryUiState(
    val bookings: List<Booking> = emptyList(),
    val isLoading: Boolean = true
)

class BookingHistoryViewModel(
    private val bookingRepository: BookingRepository,
    private val facilityRepository: FacilityRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookingHistoryUiState())
    val uiState: StateFlow<BookingHistoryUiState> = _uiState.asStateFlow()

    fun loadBookings(userId: String) {
        viewModelScope.launch {
            bookingRepository.getBookingsByUser(userId).collect { bookings ->
                _uiState.value = _uiState.value.copy(
                    bookings = bookings,
                    isLoading = false
                )
            }
        }
    }

    fun cancelBooking(bookingId: Long) {
        viewModelScope.launch {
            bookingRepository.cancelBooking(bookingId)
        }
    }
}

class BookingHistoryViewModelFactory(
    private val bookingRepository: BookingRepository,
    private val facilityRepository: FacilityRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookingHistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookingHistoryViewModel(bookingRepository, facilityRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
