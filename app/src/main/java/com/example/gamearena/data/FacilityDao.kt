package com.example.gamearena.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FacilityDao {
    @Query("SELECT * FROM facilities")
    fun getAllFacilities(): Flow<List<Facility>>

    @Query("SELECT * FROM facilities WHERE type = :type")
    fun getFacilitiesByType(type: FacilityType): Flow<List<Facility>>

    @Query("SELECT * FROM facilities WHERE id = :id")
    suspend fun getFacilityById(id: Long): Facility?

    @Insert
    suspend fun insertFacility(facility: Facility): Long

    @Insert
    suspend fun insertAll(facilities: List<Facility>)
}
