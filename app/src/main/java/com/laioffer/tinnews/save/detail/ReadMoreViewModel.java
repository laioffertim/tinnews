package com.laioffer.tinnews.save.detail;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.laioffer.tinnews.R;
import com.laioffer.tinnews.WebViewActivity;
import com.laioffer.tinnews.common.BaseViewModel;
import com.laioffer.tinnews.common.TinFragmentManager;

import static com.laioffer.tinnews.WebViewActivity.URL;

public class ReadMoreViewModel extends BaseViewModel<ReadMoreViewModel.ReadMoreViewModelHolder> {

    private final String uri;
    private final TinFragmentManager fragmentManager;

    public ReadMoreViewModel(String uri, TinFragmentManager fragmentManager) {
        super(R.layout.readmore_layout);
        this.uri = uri;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public ReadMoreViewModelHolder createItemViewHolder(View view) {
        return new ReadMoreViewModelHolder(view);
    }

    @Override
    public void bindViewHolder(ReadMoreViewModelHolder holder) {
        holder.readMore.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        holder.readMore.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString(URL, uri);
            fragmentManager.startActivityWithBundle(WebViewActivity.class, false, bundle);
        });
    }

    static class ReadMoreViewModelHolder extends RecyclerView.ViewHolder {
        TextView readMore;

        ReadMoreViewModelHolder(View itemView) {
            super(itemView);
            readMore = itemView.findViewById(R.id.readmore);
        }
    }
}

