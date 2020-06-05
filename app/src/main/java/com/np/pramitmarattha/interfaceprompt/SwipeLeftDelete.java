package com.np.pramitmarattha.interfaceprompt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.np.pramitmarattha.R;
/**
 * yo class chai swipe to delete ko lagi
 *
 * this claass is for implementing swipe to delete feature
 *
 */
abstract public class SwipeLeftDelete extends ItemTouchHelper.Callback {

    private Drawable deleteDrawable;
    private int intrinsicWidth;
    private int nativeHeight;
    private Paint paintClearGarney;
    private ColorDrawable colorDrawableBackground;
    private int backgroundColor;

    /**
     * drawables and color set gareko
     */
    protected SwipeLeftDelete(Context context) {
        colorDrawableBackground = new ColorDrawable();
        backgroundColor = context.getResources().getColor(R.color.transparent);
        paintClearGarney = new Paint();
        paintClearGarney.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        deleteDrawable = ContextCompat.getDrawable(context, R.drawable.delete);
        if (deleteDrawable != null) {
            intrinsicWidth = deleteDrawable.getIntrinsicWidth();
            nativeHeight = deleteDrawable.getIntrinsicHeight();
        }
    }
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.LEFT);
    }
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View itemView = viewHolder.itemView;
        int itemHeight = itemView.getHeight();
        boolean isCancelled = dX == 0 && !isCurrentlyActive;
        if (isCancelled) {
            clearCanvas(c, itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            return;
        }
        colorDrawableBackground.setColor(backgroundColor);
        colorDrawableBackground.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
        colorDrawableBackground.draw(c);
        int deleteIconTop = itemView.getTop() + (itemHeight - nativeHeight) / 2;
        int deleteIconMargin = (itemHeight - nativeHeight) / 2;
        int deleteIconLeft = itemView.getRight() - deleteIconMargin - intrinsicWidth;
        int deleteIconRight = itemView.getRight() - deleteIconMargin;
        int deleteIconBottom = deleteIconTop + nativeHeight;
        deleteDrawable.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom);
        deleteDrawable.draw(c);
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
    private void clearCanvas(Canvas c, Float left, Float top, Float right, Float bottom) {
        c.drawRect(left, top, right, bottom, paintClearGarney);
    }
    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 0.7f;
    }
}