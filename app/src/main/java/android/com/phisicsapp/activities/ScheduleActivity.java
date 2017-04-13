package android.com.phisicsapp.activities;

import android.com.phisicsapp.R;
import android.com.phisicsapp.adapter.SpinnerAdapter;
import android.com.phisicsapp.model.Course;
import android.com.phisicsapp.model.Group;
import android.com.phisicsapp.model.Settings;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private AppCompatSpinner sCourses;
    private AppCompatSpinner sGroups;
    private Course course = null;
    private Group group = null;
    private SpinnerAdapter coursesAdaper = null;
    private SpinnerAdapter groupsAdaper = null;

    private AdapterView.OnItemSelectedListener courseClickListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            course = (Course) coursesAdaper.getItem(position);
            ArrayList<Group> groups = Group.getList(((Course) coursesAdaper.getItem(position)).getId());
            groupsAdaper = new SpinnerAdapter(ScheduleActivity.this, groups);
            sGroups.setAdapter(groupsAdaper);
            sGroups.setOnItemSelectedListener(groupClickListener);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener groupClickListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            group = (Group) groupsAdaper.getItem(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        sCourses = (AppCompatSpinner) findViewById(R.id.s_course);
        sGroups = (AppCompatSpinner) findViewById(R.id.s_group);
        initToolbar();

        ArrayList<Course> courses = Course.getList();
        coursesAdaper = new SpinnerAdapter(this, courses);
        sCourses.setAdapter(coursesAdaper);
        sCourses.setOnItemSelectedListener(courseClickListener);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.select_schedule);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.apply, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.apply:
                if (course != null && group != null) {
                    Settings.getInstance().setCourse(course);
                    Settings.getInstance().setGroup(group);
                    setResult(RESULT_OK);
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
