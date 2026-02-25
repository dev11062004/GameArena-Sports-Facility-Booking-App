package com.example.gamearena.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facilities")
data class Facility(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val type: FacilityType,
    val hourlyRate: Double,
    val description: String,
    val imageRes: Int = 0
)
