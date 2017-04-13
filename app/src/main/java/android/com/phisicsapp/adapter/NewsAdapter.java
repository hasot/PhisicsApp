package android.com.phisicsapp.adapter;

import android.com.phisicsapp.R;
import android.com.phisicsapp.model.News;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News, NewsAdapter.NewsHolder> {
    private final Context context;

    public NewsAdapter(Context context, @Nullable ArrayList<News> items) {
        super(items);
        this.context = context;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        NewsHolder vh = new NewsHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, final int position) {
        News news = getItem(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooserBrowserClick(position);
            }
        });
        holder.mTextView.setText("\t" + news.getAlt());
        ImageView mImageView = holder.mImageView;
        Picasso.with(mImageView.getContext()).load("http:" + news.getImg()).into(holder.mImageView);
    }

    private void chooserBrowserClick(int position) {
        Uri webpage = Uri.parse(getItem(position).getHref());
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        String title = context.getResources().getString(R.string.chooser_title);
        Intent chooser = Intent.createChooser(webIntent, title);
        context.startActivity(chooser);
    }

    public static class NewsHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
        private CardView cardView;

        public NewsHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.tv_recycler_item);
            mImageView = (ImageView) v.findViewById(R.id.img_item);
            cardView = (CardView) v.findViewById(R.id.card_view);
        }

    }
}