package android.com.phisicsapp.fragments.NewsTab;

import android.com.phisicsapp.model.News;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedHashMap;

public class ParsesTitle extends AsyncTask<Void, Void, LinkedHashMap<String, News>> {
    private OnProgressListener progressListener;
    private LinkedHashMap<String, News> linkedHashMap = new LinkedHashMap<>();

    public ParsesTitle(OnProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (progressListener != null)
            progressListener.onStart();
    }

    @Override
    protected LinkedHashMap<String, News> doInBackground(Void... voids) {
        try {
            Document document = Jsoup.connect("http://sfedu.ru/").get();
            Elements elements = document.select("div.img_with_header a[href]");
            //Elements link = document.select("div.img_with_header img[src$=jpg");

            for (Element item : elements) {
                Elements imgElements = item.select("img[src$=jpg]");
                if (imgElements != null && !imgElements.isEmpty()) {
                    String alt = imgElements.get(0).attr("alt");
                    linkedHashMap.put(alt, new News(alt, imgElements.get(0).attr("src"), item.attr("href")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linkedHashMap;
    }


    @Override
    protected void onPostExecute(LinkedHashMap<String, News> linkedHashMap) {
        super.onPostExecute(linkedHashMap);
        if (progressListener != null)
            progressListener.onStop(linkedHashMap);

    }

    public interface OnProgressListener {
        void onStart();

        void onStop(LinkedHashMap<String, News> linkedHashMap);
    }
}