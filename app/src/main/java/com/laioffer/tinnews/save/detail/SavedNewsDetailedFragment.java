package com.laioffer.tinnews.save.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laioffer.tinnews.R;
import com.laioffer.tinnews.common.BaseViewModel;
import com.laioffer.tinnews.common.TinBasicFragment;
import com.laioffer.tinnews.common.Util;
import com.laioffer.tinnews.common.ViewModelAdapter;
import com.laioffer.tinnews.retrofit.response.News;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedNewsDetailedFragment extends TinBasicFragment {
    private static final String NEWS = "news";
    private ViewModelAdapter viewModelAdapter;

    public static SavedNewsDetailedFragment newInstance(News news) {
        Bundle args = new Bundle();
        args.putSerializable(NEWS, news);
        SavedNewsDetailedFragment fragment = new SavedNewsDetailedFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_news_detailed, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModelAdapter = new ViewModelAdapter();
        recyclerView.setAdapter(viewModelAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadNews((News) getArguments().getSerializable(NEWS));
    }

    private void loadNews(News news) {
        List<BaseViewModel> viewModelList = new LinkedList<>();

        if (!Util.isStringEmpty(news.title)) {
            viewModelList.add(new TitleViewModel(news.title));
        }
        if (!Util.isStringEmpty(news.author) || !Util.isStringEmpty(news.time)) {
            viewModelList.add(new AuthorViewModel(news.author, news.time));
        }

        if (!Util.isStringEmpty(news.image)) {
            viewModelList.add(new ImageViewModel(news.image));
        }

        if (!Util.isStringEmpty(news.description)) {
            viewModelList.add(new DescriptionViewModel(news.description +  news.description + news.description
            + news.description + news.description + news.description));
        }

        if (!Util.isStringEmpty(news.url)) {
            viewModelList.add(new ReadMoreViewModel(news.url, tinFragmentManager));
        }
        viewModelAdapter.addViewModels(viewModelList);
    }
}
