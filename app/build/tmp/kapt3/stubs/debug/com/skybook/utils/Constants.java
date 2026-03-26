package com.skybook.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/skybook/utils/Constants;", "", "()V", "BASE_URL", "", "FLIGHT_BOARDING", "FLIGHT_CANCELLED", "FLIGHT_DELAYED", "FLIGHT_SCHEDULED", "SIGNALR_URL", "app_debug"})
public final class Constants {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASE_URL = "http://10.0.2.2:5001/api/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SIGNALR_URL = "http://10.0.2.2:5001/api/seathub";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FLIGHT_SCHEDULED = "Scheduled";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FLIGHT_DELAYED = "Delayed";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FLIGHT_CANCELLED = "Cancelled";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FLIGHT_BOARDING = "Boarding";
    @org.jetbrains.annotations.NotNull()
    public static final com.skybook.utils.Constants INSTANCE = null;
    
    private Constants() {
        super();
    }
}