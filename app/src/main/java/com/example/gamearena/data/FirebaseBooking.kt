package com.example.gamearena.data

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
