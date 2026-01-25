package com.example.helloworldapk.data.repository

import com.example.helloworldapk.data.Facility
import com.example.helloworldapk.data.FacilityDao
import com.example.helloworldapk.data.FacilityType
import kotlinx.coroutines.flow.Flow

class FacilityRepository(private val facilityDao: FacilityDao) {

    fun getAllFacilities(): Flow<List<Facility>> = facilityDao.getAllFacilities()

    fun getFacilitiesByType(type: FacilityType): Flow<List<Facility>> =
        facilityDao.getFacilitiesByType(type)

    suspend fun getFacilityById(id: Long): Facility? = facilityDao.getFacilityById(id)
}
