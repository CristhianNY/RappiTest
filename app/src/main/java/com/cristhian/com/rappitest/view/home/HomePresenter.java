package com.cristhian.com.rappitest.view.home;

import android.content.Context;
import android.support.annotation.NonNull;

import com.cristhian.com.rappitest.Utils;
import com.cristhian.com.rappitest.api.ConstantsServices;
import com.cristhian.com.rappitest.model.MovieResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {

    private HomeView view;

    public HomePresenter(HomeView view) {
        this.view = view;
    }

    public void getMoviesByCategory(Context context){

        view.showLoading();

        Utils.getApi(context).getMovies(ConstantsServices.CATEGORY,
                                 ConstantsServices.API_KEY,
                                 ConstantsServices.LANGUAGE,
                                 ConstantsServices.PAGE).enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(@NonNull Call<MovieResults> call, @NonNull Response<MovieResults> response) {
                view.hideLoading();

                if(response.isSuccessful()&& response.body()!=null){
                    MovieResults results = response.body();
                    List<MovieResults.ResultsBean> listOfMovies = results.getResults();
                    view.setMovie(listOfMovies);
                }else {
                    view.onErrorLoading(response.message());
                }


            }

            @Override
            public void onFailure(@NonNull Call<MovieResults> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}

