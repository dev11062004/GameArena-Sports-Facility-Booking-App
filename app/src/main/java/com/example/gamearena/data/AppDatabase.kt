package com.example.gamearena.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Facility::class, Booking::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun facilityDao(): FacilityDao
    abstract fun bookingDao(): BookingDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gamearena_database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries() // For development - remove in production
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database.facilityDao())
                }
            }
        }

        suspend fun populateDatabase(facilityDao: FacilityDao) {
            val facilities = listOf(
                Facility(
                    name = "Cricket Ground A",
                    type = FacilityType.CRICKET_GROUND,
                    hourlyRate = 50.0,
                    description = "Premium cricket ground with professional pitch and lighting"
                ),
                Facility(
                    name = "Cricket Ground B",
                    type = FacilityType.CRICKET_GROUND,
                    hourlyRate = 40.0,
                    description = "Standard cricket ground suitable for practice and matches"
                ),
                Facility(
                    name = "Pool Table 1",
                    type = FacilityType.POOL_TABLE,
                    hourlyRate = 15.0,
                    description = "Professional-grade pool table in climate-controlled room"
                ),
                Facility(
                    name = "Pool Table 2",
                    type = FacilityType.POOL_TABLE,
                    hourlyRate = 15.0,
                    description = "Standard pool table with comfortable seating area"
                ),
                Facility(
                    name = "Pool Table 3",
                    type = FacilityType.POOL_TABLE,
                    hourlyRate = 12.0,
                    description = "Budget-friendly pool table for casual games"
                ),
                Facility(
                    name = "Pickleball Court 1",
                    type = FacilityType.PICKLEBALL_COURT,
                    hourlyRate = 25.0,
                    description = "Indoor pickleball court with premium flooring"
                ),
                Facility(
                    name = "Pickleball Court 2",
                    type = FacilityType.PICKLEBALL_COURT,
                    hourlyRate = 25.0,
                    description = "Indoor pickleball court with excellent lighting"
                ),
                Facility(
                    name = "Outdoor Pickleball Court",
                    type = FacilityType.PICKLEBALL_COURT,
                    hourlyRate = 20.0,
                    description = "Outdoor pickleball court with covered seating"
                )
            )
            facilityDao.insertAll(facilities)
        }
    }
}
