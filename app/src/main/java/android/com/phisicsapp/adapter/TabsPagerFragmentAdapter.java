package android.com.phisicsapp.adapter;



import android.com.phisicsapp.R;


import android.com.phisicsapp.fragment.InfoPhyphisicsFragment;
import android.com.phisicsapp.fragment.NewsFragment;
import android.com.phisicsapp.fragment.TimetableFragment;
import android.content.Context;
;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by root on 29.10.16.
 */
public class TabsPagerFragmentAdapter extends FragmentPagerAdapter {
    Context context;
    private String[] tabs;
    private int[] draw;


    public TabsPagerFragmentAdapter(FragmentManager fm) {
        super(fm);

        tabs = new String[] {
                "Новости","Расписание",
                "О нас"
        };
        draw = new int[] {
                R.drawable.ic_file_document_box,
                R.drawable.ic_file_document_box,
                R.drawable.ic_file_document_box
        };


    }




    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return NewsFragment.getInstance();
            case 1: return TimetableFragment.getInstance();
            case 2: return InfoPhyphisicsFragment.getInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
