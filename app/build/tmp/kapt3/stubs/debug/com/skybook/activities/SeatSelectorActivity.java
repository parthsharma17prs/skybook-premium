package com.skybook.activities;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0002J\u0012\u0010\u001c\u001a\u00020\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J \u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0012H\u0002J\b\u0010#\u001a\u00020\u0016H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/skybook/activities/SeatSelectorActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "allTakenSeats", "", "", "flightId", "", "leftCols", "", "pricePerSeat", "", "rightCols", "rows", "selectedSeats", "takenSeats", "", "tvSeatLabels", "Landroid/widget/TextView;", "tvSeatsInfo", "tvTotalPrice", "buildSeatGrid", "", "makeSeatView", "Landroid/widget/LinearLayout;", "seatId", "sizeDp", "marginDp", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "toggleSeat", "icon", "Landroid/widget/ImageView;", "label", "updateBottomBar", "app_debug"})
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
    private final java.util.Set<java.lang.String> takenSeats = null;
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
    
    private final android.widget.LinearLayout makeSeatView(java.lang.String seatId, int sizeDp, int marginDp) {
        return null;
    }
    
    private final void toggleSeat(java.lang.String seatId, android.widget.ImageView icon, android.widget.TextView label) {
    }
    
    private final void updateBottomBar() {
    }
}