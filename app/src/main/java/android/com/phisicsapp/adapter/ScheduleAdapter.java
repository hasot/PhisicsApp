package android.com.phisicsapp.adapter;

import android.com.phisicsapp.adapter.holders.ScheduleHeaderHolder;
import android.com.phisicsapp.adapter.holders.ScheduleHolder;
import android.com.phisicsapp.model.Schedule;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ScheduleAdapter extends ArrayAdapter<ArrayList<Schedule>, RecyclerView.ViewHolder> {
    public static final int HEADER_TYPE = 1;
    private final View.OnClickListener headerClickListener;

    public ScheduleAdapter(@Nullable ArrayList<ArrayList<Schedule>> objects, View.OnClickListener headerClickListener) {
        super(objects);
        this.headerClickListener = headerClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER_TYPE;
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE)
            return new ScheduleHeaderHolder(parent, headerClickListener);
        return new ScheduleHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0)
            ((ScheduleHeaderHolder) holder).update();
        else
            ((ScheduleHolder) holder).update(getItem(position - 1));
    }
}