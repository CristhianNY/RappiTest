package com.cristhian.com.rappitest.view.details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.cristhian.com.rappitest.Utils.Utils;
import com.cristhian.com.rappitest.api.ConstantsServices;
import com.cristhian.com.rappitest.model.MovieDetail;
import com.cristhian.com.rappitest.model.VideoMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailInteractor {

    private DetailView view;

    public DetailInteractor(DetailView view) {
        this.view = view;
    }

    void getMovieByIdy(String id, Context context){

       view.showLoading();

        Utils.getApi(context).getMovieDetail(id,
                ConstantsServices.API_KEY,
                ConstantsServices.LANGUAGE)
                .enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetail> call, @NonNull Response<MovieDetail> response) {
                 view.hideLoading();
                 if(response.isSuccessful() && response.body()!=null){
                     MovieDetail movie = response.body();
                     view.setMovie(movie);

                 }else {
                    view.onErrorLoading(response.message());
                 }
            }

            @Override
            public void onFailure(@NonNull Call<MovieDetail> call, @NonNull  Throwable t) {

                Log.d("error",t.getMessage());
            }
        });
    }
    void getVideos(String id, Context context){

        view.showLoading();

        Utils.getApi(context).getMovieVideo(id,
                ConstantsServices.API_KEY,
                ConstantsServices.LANGUAGE)
                .enqueue(new Callback<VideoMovie>() {
                    @Override
                    public void onResponse(@NonNull Call<VideoMovie> call, @NonNull Response<VideoMovie> response) {
                        view.hideLoading();
                        if(response.isSuccessful() && response.body()!=null){
                            VideoMovie video = response.body();
                            List<VideoMovie.ResultsBean> videos = video.getResults();
                            view.setVideo(videos);

                        }else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<VideoMovie> call, @NonNull  Throwable t) {

                        Log.d("error",t.getMessage());
                    }
                });
    }
}
