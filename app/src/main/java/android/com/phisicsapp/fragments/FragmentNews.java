package android.com.phisicsapp.fragments;


import android.com.phisicsapp.R;
import android.com.phisicsapp.adapter.NewsAdapter;
import android.com.phisicsapp.fragments.NewsTab.ParsesTitle;
import android.com.phisicsapp.model.News;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class FragmentNews extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private NewsAdapter mAdapter;
    private View emptyView;
    private SwipeRefreshLayout swipeContainer;
    private ParsesTitle parsesTitle;

    private View view;
    private static final int LAYOUT = R.layout.fragment_recyclerview;

    private ParsesTitle.OnProgressListener progressListener = new ParsesTitle.OnProgressListener() {
        @Override
        public void onStart() {
            swipeContainer.setRefreshing(true);
        }

        @Override
        public void onStop(LinkedHashMap<String, News> linkedHashMap) {
            swipeContainer.setRefreshing(false);
            setData(linkedHashMap);
        }
    };

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (parsesTitle != null)
                parsesTitle.cancel(false);
            parsesTitle = new ParsesTitle(progressListener);
            AsyncTaskCompat.executeParallel(parsesTitle);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        emptyView = view.findViewById(R.id.empty_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //   b = (Button) view.findViewById(R.id.testButton);
        swipeContainer.setOnRefreshListener(onRefreshListener);
        mAdapter = new NewsAdapter(getContext(), new ArrayList<News>());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        parsesTitle = new ParsesTitle(progressListener);
        AsyncTaskCompat.executeParallel(parsesTitle);
    }

    private void setData(LinkedHashMap<String, News> linkedHashMap) {
        emptyView.setVisibility(linkedHashMap.isEmpty() ? View.VISIBLE : View.GONE);
        if (linkedHashMap.isEmpty())
            return;
        mAdapter = new NewsAdapter(getContext(), new ArrayList<>(linkedHashMap.values()));
        mRecyclerView.setAdapter(mAdapter);
    }


    public static FragmentNews newInstance() {
        FragmentNews fragment = new FragmentNews();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}


