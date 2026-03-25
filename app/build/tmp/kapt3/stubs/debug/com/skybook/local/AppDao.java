package com.skybook.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\r\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\r\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0015J \u0010\u0016\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u001dJ$\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00140\u000b2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u001e\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010$\u00a8\u0006%"}, d2 = {"Lcom/skybook/local/AppDao;", "", "bookTicket", "", "booking", "Lcom/skybook/local/BookingEntity;", "(Lcom/skybook/local/BookingEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFlightCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserBookings", "", "Lcom/skybook/local/BookingWithFlight;", "userId", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserProfile", "Lcom/skybook/local/UserEntity;", "insertFlights", "", "flights", "Lcom/skybook/local/FlightEntity;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "email", "", "password", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerUser", "user", "(Lcom/skybook/local/UserEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchFlights", "from", "to", "updateBookingStatus", "bookingId", "status", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface AppDao {
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object registerUser(@org.jetbrains.annotations.NotNull()
    com.skybook.local.UserEntity user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skybook.local.UserEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE id = :userId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserProfile(int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skybook.local.UserEntity> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertFlights(@org.jetbrains.annotations.NotNull()
    java.util.List<com.skybook.local.FlightEntity> flights, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM flights WHERE fromCity = :from AND toCity = :to")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchFlights(@org.jetbrains.annotations.NotNull()
    java.lang.String from, @org.jetbrains.annotations.NotNull()
    java.lang.String to, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.skybook.local.FlightEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM flights")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFlightCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object bookTicket(@org.jetbrains.annotations.NotNull()
    com.skybook.local.BookingEntity booking, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "\n        SELECT * FROM bookings \n        JOIN flights ON bookings.flightId = flights.id \n        WHERE userId = :userId \n        ORDER BY bookingDate DESC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserBookings(int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.skybook.local.BookingWithFlight>> $completion);
    
    @androidx.room.Query(value = "UPDATE bookings SET status = :status WHERE id = :bookingId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateBookingStatus(int bookingId, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}