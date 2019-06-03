package com.cristhian.com.rappitest.view.details;

import com.cristhian.com.rappitest.model.MovieDetail;
import com.cristhian.com.rappitest.model.VideoMovie;

public interface DetailView {

    void showLoading();
    void hideLoading();
    void setMovie(MovieDetail movie);
    void setVideo(VideoMovie video);
    void onErrorLoading(String message);
}

