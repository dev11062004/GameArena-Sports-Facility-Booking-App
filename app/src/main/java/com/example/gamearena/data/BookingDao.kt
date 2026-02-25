package com.example.gamearena.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {
    @Query("SELECT * FROM bookings WHERE userId = :userId ORDER BY startTime DESC")
    fun getBookingsByUser(userId: String): Flow<List<Booking>>

    @Query("SELECT * FROM bookings WHERE facilityId = :facilityId AND startTime >= :fromTime AND endTime <= :toTime")
    suspend fun getBookingsForFacilityInRange(facilityId: Long, fromTime: Long, toTime: Long): List<Booking>

    @Query("SELECT * FROM bookings WHERE facilityId = :facilityId AND status = :status AND ((startTime >= :startTime AND startTime < :endTime) OR (endTime > :startTime AND endTime <= :endTime) OR (startTime <= :startTime AND endTime >= :endTime))")
    suspend fun getConflictingBookings(
        facilityId: Long,
        startTime: Long,
        endTime: Long,
        status: BookingStatus = BookingStatus.CONFIRMED
    ): List<Booking>

    @Insert
    suspend fun insertBooking(booking: Booking): Long

    @Update
    suspend fun updateBooking(booking: Booking)

    @Query("SELECT * FROM bookings WHERE id = :bookingId")
    suspend fun getBookingById(bookingId: Long): Booking?
}
