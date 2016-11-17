package android.com.phisicsapp.fragment;

import android.com.phisicsapp.MainActivity;
import android.com.phisicsapp.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by root on 29.10.16.
 */
public class ExampleFragment extends Fragment  {

    private static final int LAYOUT = R.layout.fragment_example;

    private  ListView listView;
    private  TextView textNewsView;

    public static ExampleFragment getInstance() {
        Bundle args = new Bundle();
        ExampleFragment fragment = new ExampleFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        listView =(ListView) view.findViewById(R.id.listView);
        textNewsView = (TextView) view.findViewById(R.id.textNewsView);

        final ParsesTitle parsesTitle = new ParsesTitle();
        parsesTitle.execute();

        try {
            final HashMap<String, String> hashMap = parsesTitle.get();
            final ArrayList<String> arrayList = new ArrayList<>();
            for(Map.Entry entry: hashMap.entrySet()){
                arrayList.add(entry.getKey().toString());
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    ParseText parseText = new ParseText();
                    parseText.execute(hashMap.get(arrayList.get(position)));

                    try {
                        listView.setVisibility(View.GONE);
                        textNewsView.setText(parseText.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return view;
    }




    class ParseText extends AsyncTask<String,Void, String>{

        String str = " ";

        @Override
        protected String doInBackground(String... params) {

            try {
                Document document = Jsoup.connect(params[0]).get();
                Element element = document.select("div.wrapper").last(); // dont normal work
                str = element.text();

            } catch (IOException e) {
                e.printStackTrace();
            }


            return str;
        }


    }

    class ParsesTitle extends AsyncTask<Void,Void, HashMap<String, String>>{

        HashMap<String, String> hashMap = new HashMap<>();

        @Override
        protected HashMap<String, String> doInBackground(Void... voids) {

            try{
                Document document = Jsoup.connect("http://sfedu.ru/").get();
                Elements elements = document.select(".new");
                for(Element element : elements){
                    Element element1 = element.select("a[href]").first();
                    hashMap.put(element.text(), element1.attr("abs:href"));

                }

            } catch(IOException e){
                e.printStackTrace();
            }

            return hashMap;
        }
    }
}
