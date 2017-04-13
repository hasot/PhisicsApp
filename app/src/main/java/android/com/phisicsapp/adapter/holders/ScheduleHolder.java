package android.com.phisicsapp.adapter.holders;

import android.com.phisicsapp.R;
import android.com.phisicsapp.adapter.ScheduleDayAdapter;
import android.com.phisicsapp.controls.DividerDecoration;
import android.com.phisicsapp.model.Schedule;
import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ScheduleHolder extends RecyclerView.ViewHolder {
    private final Context context;
    private RecyclerView rvScheduleDay;

    public ScheduleHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false));
        this.context = parent.getContext();
        rvScheduleDay = (RecyclerView) itemView.findViewById(R.id.rv_schedule_day);
        rvScheduleDay.getLayoutManager().setAutoMeasureEnabled(true);
        rvScheduleDay.setNestedScrollingEnabled(false);
        rvScheduleDay.addItemDecoration(new DividerDecoration(context));
    }

    public void update(ArrayList<Schedule> item) {
        rvScheduleDay.setAdapter(new ScheduleDayAdapter(item));
    }
}