package com.example.helloworldapk.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.helloworldapk.data.Booking
import com.example.helloworldapk.data.BookingStatus
import com.example.helloworldapk.data.Facility
import com.example.helloworldapk.data.TimeSlot
import com.example.helloworldapk.data.repository.BookingRepository
import com.example.helloworldapk.data.repository.FacilityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

data class BookingUiState(
    val facility: Facility? = null,
    val selectedDate: Long = System.currentTimeMillis(),
    val timeSlots: List<TimeSlot> = emptyList(),
    val selectedTimeSlot: TimeSlot? = null,
    val isLoading: Boolean = false,
    val bookingSuccess: Boolean = false,
    val errorMessage: String? = null,
    val lastBookingId: Long? = null
)

class BookingViewModel(
    private val facilityRepository: FacilityRepository,
    private val bookingRepository: BookingRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookingUiState())
    val uiState: StateFlow<BookingUiState> = _uiState.asStateFlow()

    fun loadFacility(facilityId: Long) {
        viewModelScope.launch {
            val facility = facilityRepository.getFacilityById(facilityId)
            _uiState.value = _uiState.value.copy(facility = facility)
            loadTimeSlots()
        }
    }

    fun selectDate(date: Long) {
        _uiState.value = _uiState.value.copy(selectedDate = date)
        loadTimeSlots()
    }

    fun selectTimeSlot(timeSlot: TimeSlot) {
        _uiState.value = _uiState.value.copy(selectedTimeSlot = timeSlot)
    }

    private fun loadTimeSlots() {
        val facility = _uiState.value.facility ?: return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val slots = bookingRepository.getAvailableTimeSlots(
                facility.id,
                _uiState.value.selectedDate
            )
            _uiState.value = _uiState.value.copy(
                timeSlots = slots,
                isLoading = false
            )
        }
    }

    fun createBooking(userId: String) {
        val facility = _uiState.value.facility ?: return
        val timeSlot = _uiState.value.selectedTimeSlot ?: return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val durationHours = (timeSlot.endTime - timeSlot.startTime) / (1000.0 * 60 * 60)
            val totalPrice = facility.hourlyRate * durationHours

            val booking = Booking(
                facilityId = facility.id,
                userId = userId,
                startTime = timeSlot.startTime,
                endTime = timeSlot.endTime,
                totalPrice = totalPrice,
                status = BookingStatus.CONFIRMED
            )

            val result = bookingRepository.createBooking(booking)

            _uiState.value = if (result.isSuccess) {
                _uiState.value.copy(
                    isLoading = false,
                    bookingSuccess = true,
                    lastBookingId = result.getOrNull(),
                    errorMessage = null
                )
            } else {
                _uiState.value.copy(
                    isLoading = false,
                    bookingSuccess = false,
                    errorMessage = result.exceptionOrNull()?.message
                )
            }
        }
    }

    fun resetBookingState() {
        _uiState.value = _uiState.value.copy(
            bookingSuccess = false,
            errorMessage = null,
            selectedTimeSlot = null
        )
    }
}

class BookingViewModelFactory(
    private val facilityRepository: FacilityRepository,
    private val bookingRepository: BookingRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookingViewModel(facilityRepository, bookingRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
