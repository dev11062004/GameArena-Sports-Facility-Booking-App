package com.example.gamearena.data.repository

import com.example.gamearena.data.FirebaseBooking
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Firebase Booking Repository
 * Handles all CRUD operations for bookings in Firestore
 */
class FirebaseBookingRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val bookingsCollection = firestore.collection("bookings")

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

    /**
     * READ - Get single booking by ID
     */
    suspend fun getBookingById(bookingId: String): Result<FirebaseBooking> {
        return try {
            val document = bookingsCollection.document(bookingId).get().await()
            if (document.exists()) {
                val booking = FirebaseBooking.fromMap(document.data as Map<String, Any>)
                Result.success(booking)
            } else {
                Result.failure(Exception("Booking not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * READ - Get all bookings (for admin/testing)
     */
    suspend fun getAllBookings(): Result<List<FirebaseBooking>> {
        return try {
            val snapshot = bookingsCollection
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()

            val bookings = snapshot.documents.mapNotNull { doc ->
                try {
                    FirebaseBooking.fromMap(doc.data as Map<String, Any>)
                } catch (e: Exception) {
                    null
                }
            }
            Result.success(bookings)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

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

    /**
     * UPDATE - Update entire booking object
     */
    suspend fun updateBooking(booking: FirebaseBooking): Result<Unit> {
        return try {
            if (booking.bookingId.isEmpty()) {
                return Result.failure(Exception("Booking ID is required for update"))
            }
            bookingsCollection.document(booking.bookingId).set(booking.toMap()).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

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

    /**
     * CANCEL - Soft delete by updating status
     */
    suspend fun cancelBooking(bookingId: String): Result<Unit> {
        return try {
            bookingsCollection.document(bookingId).update("status", false).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
