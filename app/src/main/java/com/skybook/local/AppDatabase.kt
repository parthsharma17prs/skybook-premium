package com.skybook.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    // User functions
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun registerUser(user: UserEntity): Long

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    suspend fun login(email: String, password: String): UserEntity?

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserProfile(userId: Int): UserEntity?

    // Flight functions
    @Insert
    suspend fun insertFlights(flights: List<FlightEntity>)

    @Query("SELECT * FROM flights WHERE fromCity = :from AND toCity = :to")
    suspend fun searchFlights(from: String, to: String): List<FlightEntity>

    @Query("SELECT COUNT(*) FROM flights")
    suspend fun getFlightCount(): Int

    // Booking functions
    @Insert
    suspend fun bookTicket(booking: BookingEntity): Long

    @Transaction
    @Query("SELECT * FROM bookings WHERE userId = :userId")
    suspend fun getUserBookings(userId: Int): List<BookingWithFlight>

    @Query("UPDATE bookings SET status = :status WHERE id = :bookingId")
    suspend fun updateBookingStatus(bookingId: Int, status: String)
}

data class BookingWithFlight(
    @Embedded val booking: BookingEntity,
    @Relation(
        parentColumn = "flightId",
        entityColumn = "id"
    )
    val flight: FlightEntity
)

@Database(entities = [UserEntity::class, FlightEntity::class, BookingEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: android.content.Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "skybook_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
