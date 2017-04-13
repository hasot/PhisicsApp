package android.com.phisicsapp.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter {

    private String[] tabs;



    public TabsPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[] {
                "Новости","Расписание",
                "О нас"
        };

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return null;
            case 1: return null;
            case 2: return null;
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
