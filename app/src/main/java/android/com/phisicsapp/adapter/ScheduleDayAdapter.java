package android.com.phisicsapp.adapter;

import android.com.phisicsapp.adapter.holders.ScheduleDayHeaderHolder;
import android.com.phisicsapp.adapter.holders.ScheduleDayHolder;
import android.com.phisicsapp.model.Schedule;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ScheduleDayAdapter extends ArrayAdapter<Schedule, RecyclerView.ViewHolder> {
    public static final int HEADER_TYPE = 1;
    private int dayOfWeek = 1;

    public ScheduleDayAdapter(@Nullable ArrayList<Schedule> objects) {
        super(objects);
        dayOfWeek = objects != null && !objects.isEmpty() ? objects.get(0).getDayOfWeek() : 1;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER_TYPE;
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE)
            return new ScheduleDayHeaderHolder(parent, dayOfWeek);
        return new ScheduleDayHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0)
            ((ScheduleDayHeaderHolder) holder).update();
        else
            ((ScheduleDayHolder) holder).update(getItem(position - 1));
    }
}
