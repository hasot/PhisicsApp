package android.com.phisicsapp.adapter.holders;

import android.com.phisicsapp.R;
import android.com.phisicsapp.model.Settings;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScheduleHeaderHolder extends RecyclerView.ViewHolder {
    private final Context context;
    private final View.OnClickListener headerClickListener;
    private TextView tvSchedule;

    public ScheduleHeaderHolder(ViewGroup parent, View.OnClickListener headerClickListener) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule_header, parent, false));
        this.context = parent.getContext();
        this.headerClickListener = headerClickListener;
        tvSchedule = (TextView) itemView.findViewById(R.id.tv_schedule);
    }

    public void update() {
        tvSchedule.setText(Settings.getInstance().getCourse() == null && Settings.getInstance().getGroup() == null
                ? context.getString(R.string.default_select_schedule)
                : String.format("%s %s", Settings.getInstance().getCourse().getName(), Settings.getInstance().getGroup().getName()));
        itemView.setOnClickListener(headerClickListener);
    }
}
