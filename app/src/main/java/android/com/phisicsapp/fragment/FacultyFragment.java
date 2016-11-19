package android.com.phisicsapp.fragment;

import android.com.phisicsapp.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by root on 19.11.16.
 */
public class FacultyFragment  extends Fragment {

    private static final int LAYOUT = R.layout.fragment_faculty;



    public static FacultyFragment getInstance() {
        Bundle args = new Bundle();
        FacultyFragment fragment = new FacultyFragment();
        fragment.setArguments(args);

        return fragment;
    }


    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);


        return view;
    }
}

