package com.laioffer.tinnews.profile;

import android.view.View;

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;
    private ProfileContract.Model model;

    ProfilePresenter() {
        model = new ProfileModel();
        model.setPresenter(this);
    }

    @Override
    public void onCacheCleared() {
        if (view != null) {
            view.onCacheCleared();
        }
    }

    @Override
    public View.OnClickListener getCacheClearListener() {
        return v -> {
            model.deleteAllNewsCache();
        };
    }

    @Override
    public View.OnClickListener getOnCountryChangeListener(String country) {
        return v -> {
            model.setDefaultCountry(country);
        };
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onViewAttached(ProfileContract.View view) {
        this.view = view;
        this.view.setView();
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void onDestroy() {

    }
}
