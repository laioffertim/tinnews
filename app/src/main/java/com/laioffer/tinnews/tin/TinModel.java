package com.laioffer.tinnews.tin;

import android.database.sqlite.SQLiteConstraintException;

import com.laioffer.tinnews.TinApplication;
import com.laioffer.tinnews.database.AppDatabase;
import com.laioffer.tinnews.retrofit.NewsRequestApi;
import com.laioffer.tinnews.retrofit.RetrofitClient;
import com.laioffer.tinnews.retrofit.response.News;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TinModel implements TinContract.Model {

    private TinContract.Presenter presenter;

    private final NewsRequestApi newsRequestApi;
    private final AppDatabase database;
    private Disposable disposable;

    public TinModel() {
        newsRequestApi = RetrofitClient.getInstance().create(NewsRequestApi.class);
        database = TinApplication.getDatabase();
    }

    @Override
    public void setPresenter(TinContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void fetchData(String country) {
        Disposable disposable = newsRequestApi.getNewsByCountry(country) //de cn
                .doOnNext(baseResponse -> { System.out.println("Test " + Thread.currentThread().getName());})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(baseResponse -> { System.out.println("Test " +  Thread.currentThread().getName());})
                .filter(baseResponse -> baseResponse != null && baseResponse.articles != null)
                .subscribe(baseResponse -> {
                    presenter.showNewsCard(baseResponse.articles);
                }, error -> {
                });
    }

    @Override
    public void saveFavoriteNews(News news) {
        Disposable disposable = Completable.fromAction(() -> database.newsDao().insertNews(news)).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
              System.out.println("tim");
        }, error -> {
              if (error instanceof SQLiteConstraintException) {
                  presenter.onError();
              }
        });

    }
}
