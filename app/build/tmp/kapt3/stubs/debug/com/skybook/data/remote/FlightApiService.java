package com.skybook.data.remote;

/**
 * Skyscanner API via RapidAPI.
 * Register at https://rapidapi.com/skyscanner/api/skyscanner44/
 * To get real time details, provide your RapidAPI Key.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001JF\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0003\u0010\t\u001a\u00020\u00062\b\b\u0003\u0010\n\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/skybook/data/remote/FlightApiService;", "", "searchFlights", "Lretrofit2/Response;", "Lcom/skybook/data/remote/FlightSearchResponse;", "from", "", "to", "date", "apiKey", "host", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface FlightApiService {
    
    @retrofit2.http.GET(value = "flights/search-one-way")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchFlights(@retrofit2.http.Query(value = "fromId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String from, @retrofit2.http.Query(value = "toId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String to, @retrofit2.http.Query(value = "date")
    @org.jetbrains.annotations.NotNull()
    java.lang.String date, @retrofit2.http.Header(value = "x-rapidapi-key")
    @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @retrofit2.http.Header(value = "x-rapidapi-host")
    @org.jetbrains.annotations.NotNull()
    java.lang.String host, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.skybook.data.remote.FlightSearchResponse>> $completion);
    
    /**
     * Skyscanner API via RapidAPI.
     * Register at https://rapidapi.com/skyscanner/api/skyscanner44/
     * To get real time details, provide your RapidAPI Key.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}