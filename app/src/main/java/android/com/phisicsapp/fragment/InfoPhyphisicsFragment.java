package android.com.phisicsapp.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.com.phisicsapp.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import android.widget.Toast;



/**
 * Created by root on 16.03.17.
 */

public class InfoPhyphisicsFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_direction_button;
    private View view;

    Button button;

    public static InfoPhyphisicsFragment getInstance() {
        Bundle args = new Bundle();
        InfoPhyphisicsFragment fragment1 = new InfoPhyphisicsFragment();
        fragment1.setArguments(args);

        return fragment1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        button = (Button)view.findViewById(R.id.buttonDirecList);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        return view;
    }
}

