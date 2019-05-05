package com.cristhian.com.rappitest.view.details;

public class DetailPresenter {

    private DetailView view;

    public DetailPresenter(DetailView view) {
        this.view = view;
    }
    void getMovieByIdy(String id){

       /* view.showLoading();

        Utils.getApi().getMovieDetail("299534",
                ConstantsServices.API_KEY,
                ConstantsServices.LANGUAGE)
                .enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetail> call, @NonNull Response<MovieDetail> response) {
                 view.hideLoading();
                 if(response.isSuccessful() && response.body()!=null){
                     MovieDetail results = response.body();

                     List<MovieResults.ResultsBean> listOfMovies = results.getResults();
                     MovieResults.ResultsBean firstMovie = listOfMovies.get(0);


                     MovieDetail MovieDetail = results.getHomepage()
                    MovieDetail firstMovie = getMovieDetail.get(0);

                 }
            }

            @Override
            public void onFailure(@NonNull Call<MovieDetail> call, @NonNull  Throwable t) {

            }
        });**/
    }
}
