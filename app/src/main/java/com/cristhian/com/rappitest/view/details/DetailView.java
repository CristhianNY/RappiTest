package com.cristhian.com.rappitest.view.details;

import com.cristhian.com.rappitest.model.MovieDetail;
import com.cristhian.com.rappitest.model.VideoMovie;

import java.util.List;

public interface DetailView {

    void showLoading();
    void hideLoading();
    void setMovie(MovieDetail movie);
    void setVideo(List<VideoMovie.ResultsBean> videos);
    void onErrorLoading(String message);
}

