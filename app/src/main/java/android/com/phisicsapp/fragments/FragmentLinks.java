package android.com.phisicsapp.fragments;

import android.com.phisicsapp.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Created by root on 20.03.17.
 */

public class FragmentLinks extends Fragment {

    private static final int LAYOUT = R.layout.fragment_direction;

    private ListView lv;


    String[] names = { "Южный федеральный университет", "Физический факультет", " Студенческий спортивный клуб", "Центр студенческой мобильности ЮФУ" ,"Объединенный совет обучающихся ЮФУ","Студенческий совет ФФ ЮФУ"};

    String[] arrayLinks = { "https://www.sfedu.ru ", "http://www.phys.sfedu.ru", "https://vk.com/ssc_isir", "https://vk.com/sfedustudentsmobility" ,"https://vk.com/oso.sfedu ","https://vk.com/studsovetphysfac "};

    private View view;

    public static FragmentLinks newInstance(String param1, String param2) {
        FragmentLinks fragment = new FragmentLinks();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        lv =(ListView) view.findViewById(android.R.id.list);

        final ArrayAdapter<String> arrayAdapterLink = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_selectable_list_item, arrayLinks);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, names);
        // присваиваем адаптер списку
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0,
                                    View arg1, int position, long arg3)
            {
                chooserBrowserClick(arrayAdapterLink, position);
            }
        });

        return view;
    }



    public void chooserBrowserClick (ArrayAdapter<String> s, int position){
        Uri webpage = Uri.parse(s.getItem(position).toString());
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        String title = getResources().getString(R.string.chooser_title);
        Intent chooser = Intent.createChooser(webIntent, title);
        startActivity(chooser);
    }

}
