package com.cristhian.com.rappitest.view.home;

import com.cristhian.com.rappitest.model.MovieResults;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void setMovie(List<MovieResults.ResultsBean> movie);
    void onErrorLoading(String message);
}
