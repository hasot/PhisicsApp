package android.com.phisicsapp.adapter.holders;

import android.com.phisicsapp.R;
import android.com.phisicsapp.model.Schedule;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScheduleDayHolder extends RecyclerView.ViewHolder {
    private final Context context;
    private TextView tvDateStart;
    private TextView tvDateEnd;
    private TextView tvName;
    private TextView tvWeekType;

    public ScheduleDayHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shedule_day, parent, false));
        this.context = parent.getContext();
        tvDateStart = (TextView) itemView.findViewById(R.id.tv_date_start);
        tvDateEnd = (TextView) itemView.findViewById(R.id.tv_date_end);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        tvWeekType = (TextView) itemView.findViewById(R.id.tv_week_type);
    }

    public void update(Schedule item) {
        tvDateStart.setText(item.getDateStart());
        tvDateEnd.setText(item.getDateEnd());
        tvName.setText(item.getName());
        tvWeekType.setVisibility(item.getWeekType() == 0 ? View.GONE : View.VISIBLE);
        switch (item.getWeekType()) {
            case Schedule.WEEK_TYPE_TOP:
                tvWeekType.setText(context.getString(R.string.week_type_top));
                break;
            case Schedule.WEEK_TYPE_BOTTOM:
                tvWeekType.setText(context.getString(R.string.week_type_bottom));
                break;
        }
    }
}
