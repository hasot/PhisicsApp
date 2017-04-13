package android.com.phisicsapp.adapter.holders;

import android.com.phisicsapp.R;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScheduleDayHeaderHolder extends RecyclerView.ViewHolder {
    private final Context context;
    private final int datOfWeek;
    private TextView tvName;

    public ScheduleDayHeaderHolder(ViewGroup parent, int datOfWeek) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule_day_header, parent, false));
        this.context = parent.getContext();
        this.datOfWeek = datOfWeek;
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
    }

    public void update() {
        tvName.setText(context.getResources().getStringArray(R.array.days_of_week)[datOfWeek - 1]);
    }
}
