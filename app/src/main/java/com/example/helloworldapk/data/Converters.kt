package com.example.helloworldapk.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromFacilityType(value: FacilityType): String {
        return value.name
    }

    @TypeConverter
    fun toFacilityType(value: String): FacilityType {
        return FacilityType.valueOf(value)
    }

    @TypeConverter
    fun fromBookingStatus(value: BookingStatus): String {
        return value.name
    }

    @TypeConverter
    fun toBookingStatus(value: String): BookingStatus {
        return BookingStatus.valueOf(value)
    }
}
