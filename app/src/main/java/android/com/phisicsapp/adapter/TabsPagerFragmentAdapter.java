package android.com.phisicsapp.adapter;

import android.com.phisicsapp.fragment.ExampleFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by root on 29.10.16.
 */
public class TabsPagerFragmentAdapter extends FragmentPagerAdapter {

    private String[] tabs;

    public TabsPagerFragmentAdapter(FragmentManager fm) {
        super(fm);

        tabs = new String[] {
                "Новости","Рассписание",
                "О Нас", "Контакты"
        };

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return ExampleFragment.getInstance();
            case 1: return ExampleFragment.getInstance();
            case 2: return ExampleFragment.getInstance();
            case 3: return ExampleFragment.getInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
