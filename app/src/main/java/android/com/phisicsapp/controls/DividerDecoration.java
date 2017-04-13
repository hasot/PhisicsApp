package android.com.phisicsapp.controls;

import android.com.phisicsapp.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerDecoration extends RecyclerView.ItemDecoration {

    private final Context context;
    private final Drawable drawable;

    public DividerDecoration(Context context) {
        this.context = context;
        drawable = ContextCompat.getDrawable(context, R.drawable.schedule_divider);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            if (position < parent.getAdapter().getItemCount() - 1) {
                drawable.setBounds(view.getLeft(), view.getBottom(), view.getRight(), view.getBottom() + drawable.getIntrinsicHeight());
                drawable.draw(c);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, drawable.getIntrinsicWidth(), 0);
    }
}
