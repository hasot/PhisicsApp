package android.com.phisicsapp.activities;

import android.com.phisicsapp.R;
import android.com.phisicsapp.fragments.FragmentDirection;
import android.com.phisicsapp.fragments.FragmentLinks;
import android.com.phisicsapp.fragments.FragmentNews;
import android.com.phisicsapp.fragments.TimetableFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static final int REQ_SCHEDULE = 1;

    private ViewPager viewPager;

    FragmentLinks fragnetnLinks;
    FragmentDirection fragmentDirection;
    TimetableFragment timetableFragment;
    FragmentNews fragmentNews;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        //     initTabs();
        fragnetnLinks = new FragmentLinks();
        fragmentDirection = new FragmentDirection();
        timetableFragment = new TimetableFragment();
        fragmentNews = new FragmentNews();

        toolbar.setTitle(R.string.nav_news);
        fragmentNews.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragmentNews).commit();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    /*  private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    } */


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        FragmentTransaction ftrans = getSupportFragmentManager().beginTransaction();


        if (id == R.id.nav_news) {
            ftrans.replace(R.id.container, fragmentNews);
            toolbar.setTitle(R.string.nav_news);
        } else if (id == R.id.nav_timetable) {
            ftrans.replace(R.id.container, timetableFragment);
            toolbar.setTitle(R.string.nav_timetable);
        } else if (id == R.id.nav_teacher) {
            ftrans.replace(R.id.container, fragmentDirection);
            toolbar.setTitle(R.string.nav_teacher);
        } else if (id == R.id.nav_links) {
            ftrans.replace(R.id.container, fragnetnLinks);
            toolbar.setTitle(R.string.nav_link);
        }ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}



