package android.com.phisicsapp.fragment;

import android.com.phisicsapp.MainActivity;
import android.com.phisicsapp.R;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by root on 29.10.16.
 */
public class NewsFragment extends Fragment  {

    private static final int LAYOUT = R.layout.fragment_news;

    private  ListView listView;


    public static NewsFragment getInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        listView =(ListView) view.findViewById(R.id.listView);


        final ParsesTitle parsesTitle = new ParsesTitle();
        parsesTitle.execute();

        try {
            final HashMap<String, String> hashMap = parsesTitle.get();
            final ArrayList<String> arrayList = new ArrayList<>();

            for(Map.Entry entry: hashMap.entrySet()){
                if (entry.getKey() != null)
                arrayList.add(entry.getKey().toString());

            }
           

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_selectable_list_item, arrayList);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Uri webpage = Uri.parse("https://sfedu.ru/");
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    String title = getResources().getString(R.string.chooser_title);
//                     Create intent to show chooser
                    Intent chooser = Intent.createChooser(webIntent, title);
//                     Verify the intent will resolve to at least one activity
                    startActivity(chooser);


                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return view;
    }

    class ParsesTitle extends AsyncTask<Void,Void, HashMap<String, String>>{

        HashMap<String, String> hashMap = new HashMap<>();

        @Override
        protected HashMap<String, String> doInBackground(Void... voids) {

        try{
            Document document = Jsoup.connect("http://sfedu.ru/").get();
            Elements elements = document.select(".new");
//            Elements images = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

            for(Element element : elements) {
                Element imgEle = elements.select("a[href]").last();
                String url = element.absUrl("href");
                hashMap.put(element.select("span[class=name]").text(), url);
            }
        } catch(IOException e){
            e.printStackTrace();
        }

            return hashMap;
        }
    }
}

