package com.skybook.activities;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u0018\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0012H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/skybook/activities/SeatSelectorActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "allTakenSeats", "", "", "baselineTaken", "", "flightId", "", "leftCols", "", "pricePerSeat", "", "rightCols", "rows", "selectedSeats", "tvSeatLabels", "Landroid/widget/TextView;", "tvSeatsInfo", "tvTotalPrice", "buildSeatGrid", "", "makeSeatView", "seatId", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "toggleSeat", "view", "updateBottomPanel", "app_debug"})
public final class SeatSelectorActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> selectedSeats = null;
    private double pricePerSeat = 169.0;
    private int flightId = -1;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> rows = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> leftCols = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> rightCols = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> baselineTaken = null;
    private android.widget.TextView tvSeatsInfo;
    private android.widget.TextView tvSeatLabels;
    private android.widget.TextView tvTotalPrice;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> allTakenSeats = null;
    
    public SeatSelectorActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void buildSeatGrid() {
    }
    
    private final android.widget.TextView makeSeatView(java.lang.String seatId) {
        return null;
    }
    
    private final void toggleSeat(java.lang.String seatId, android.widget.TextView view) {
    }
    
    private final void updateBottomPanel() {
    }
}