package com.cristhian.com.rappitest.view.details;

import com.cristhian.com.rappitest.model.MovieDetail;

public interface DetailView {

    void showLoading();
    void hideLoading();
    void setMovie(MovieDetail movie);
    void onErrorLoading(String message);
}

