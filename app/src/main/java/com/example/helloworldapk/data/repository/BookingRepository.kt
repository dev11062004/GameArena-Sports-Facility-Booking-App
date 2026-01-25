package com.example.helloworldapk.data.repository

import com.example.helloworldapk.data.Booking
import com.example.helloworldapk.data.BookingDao
import com.example.helloworldapk.data.BookingStatus
import com.example.helloworldapk.data.TimeSlot
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

class BookingRepository(private val bookingDao: BookingDao) {

    fun getBookingsByUser(userId: String): Flow<List<Booking>> =
        bookingDao.getBookingsByUser(userId)

    suspend fun createBooking(booking: Booking): Result<Long> {
        return try {
            // Check for conflicts
            val conflicts = bookingDao.getConflictingBookings(
                booking.facilityId,
                booking.startTime,
                booking.endTime,
                BookingStatus.CONFIRMED
            )

            if (conflicts.isNotEmpty()) {
                Result.failure(Exception("Time slot already booked"))
            } else {
                val bookingId = bookingDao.insertBooking(booking)
                Result.success(bookingId)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun cancelBooking(bookingId: Long): Result<Unit> {
        return try {
            val booking = bookingDao.getBookingById(bookingId)
            if (booking != null) {
                bookingDao.updateBooking(booking.copy(status = BookingStatus.CANCELLED))
                Result.success(Unit)
            } else {
                Result.failure(Exception("Booking not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAvailableTimeSlots(
        facilityId: Long,
        date: Long,
        slotDurationMinutes: Int = 60
    ): List<TimeSlot> {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = date
            set(Calendar.HOUR_OF_DAY, 6) // Start at 6 AM
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val endOfDay = Calendar.getInstance().apply {
            timeInMillis = date
            set(Calendar.HOUR_OF_DAY, 22) // End at 10 PM
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val slots = mutableListOf<TimeSlot>()
        val existingBookings = bookingDao.getBookingsForFacilityInRange(
            facilityId,
            calendar.timeInMillis,
            endOfDay.timeInMillis
        ).filter { it.status == BookingStatus.CONFIRMED }

        while (calendar.timeInMillis < endOfDay.timeInMillis) {
            val slotStart = calendar.timeInMillis
            calendar.add(Calendar.MINUTE, slotDurationMinutes)
            val slotEnd = calendar.timeInMillis

            val isAvailable = existingBookings.none { booking ->
                (booking.startTime < slotEnd && booking.endTime > slotStart)
            }

            slots.add(TimeSlot(slotStart, slotEnd, isAvailable))
        }

        return slots
    }
}
