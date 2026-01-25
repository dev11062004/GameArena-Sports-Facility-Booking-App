package com.example.helloworldapk.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

enum class BookingStatus {
    CONFIRMED,
    CANCELLED,
    COMPLETED
}

@Entity(
    tableName = "bookings",
    foreignKeys = [
        ForeignKey(
            entity = Facility::class,
            parentColumns = ["id"],
            childColumns = ["facilityId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Booking(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val facilityId: Long,
    val userId: String,
    val startTime: Long,
    val endTime: Long,
    val status: BookingStatus = BookingStatus.CONFIRMED,
    val totalPrice: Double,
    val createdAt: Long = System.currentTimeMillis()
)
