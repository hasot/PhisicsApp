package android.com.phisicsapp.fragments;

import android.com.phisicsapp.R;
import android.com.phisicsapp.activities.ScheduleActivity;
import android.com.phisicsapp.adapter.ScheduleAdapter;
import android.com.phisicsapp.model.Schedule;
import android.com.phisicsapp.model.Settings;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class TimetableFragment extends Fragment {
    public static final int REQ_SCHEDULE = 1;
    private static final int LAYOUT = R.layout.fragment_timetable;
    private RecyclerView rvSchedules;

    private View.OnClickListener headerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TimetableFragment.this.startActivityForResult(new Intent(getContext(), ScheduleActivity.class), REQ_SCHEDULE);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        rvSchedules = (RecyclerView) view.findViewById(R.id.rv_schedule);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (Settings.getInstance().getGroup() != null && Settings.getInstance().getCourse() != null) {
            ArrayList<ArrayList<Schedule>> scheduls = Schedule.getList(Settings.getInstance().getGroup().getId());
            rvSchedules.setAdapter(new ScheduleAdapter(scheduls, headerClickListener));
        } else {
            rvSchedules.setAdapter(new ScheduleAdapter(new ArrayList<ArrayList<Schedule>>(), headerClickListener));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case REQ_SCHEDULE:
                ArrayList<ArrayList<Schedule>> scheduls = Schedule.getList(Settings.getInstance().getGroup().getId());
                rvSchedules.setAdapter(new ScheduleAdapter(scheduls, headerClickListener));
        }
    }

    public static FragmentDirection newInstance() {
        FragmentDirection fragment = new FragmentDirection();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}
