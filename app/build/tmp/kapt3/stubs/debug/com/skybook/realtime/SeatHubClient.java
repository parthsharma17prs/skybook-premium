package com.skybook.realtime;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R#\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u000f"}, d2 = {"Lcom/skybook/realtime/SeatHubClient;", "", "()V", "connection", "Lcom/microsoft/signalr/HubConnection;", "seatUpdates", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lkotlin/Pair;", "", "", "getSeatUpdates", "()Lkotlinx/coroutines/flow/MutableSharedFlow;", "start", "", "stop", "app_debug"})
public final class SeatHubClient {
    @org.jetbrains.annotations.NotNull()
    private com.microsoft.signalr.HubConnection connection;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<kotlin.Pair<java.lang.Integer, java.lang.String>> seatUpdates = null;
    
    public SeatHubClient() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableSharedFlow<kotlin.Pair<java.lang.Integer, java.lang.String>> getSeatUpdates() {
        return null;
    }
    
    public final void start() {
    }
    
    public final void stop() {
    }
}