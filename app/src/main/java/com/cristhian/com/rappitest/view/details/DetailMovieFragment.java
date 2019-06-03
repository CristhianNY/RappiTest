package com.cristhian.com.rappitest.view.details;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Dialog;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cristhian.com.rappitest.R;
import com.cristhian.com.rappitest.Utils;
import com.cristhian.com.rappitest.model.MovieDetail;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailMovieFragment extends DialogFragment implements  DetailView {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.overview)
    TextView overView;

    @BindView(R.id.spoken_languages)
    TextView spokenLanguages;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.release_date)
    TextView releaseDate;

    @BindView(R.id.original_language)
    TextView originalLanguage;

    @BindView(R.id.imagenMovie)
    ImageView imagenMovie;

    private DetailPresenter detailPresenter;
    private int movieid;


    public DetailMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return dialog;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_detail_movie, container, false);

        ButterKnife.bind(this, view);
        movieid = this.getArguments().getInt("movieId");
        detailPresenter = new DetailPresenter(this);
        detailPresenter.getMovieByIdy(Integer.toString(movieid),getActivity());

        return  view;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setMovie(MovieDetail movie) {

        overView.setText(movie.getOverview());
        title.setText(movie.getTitle());
        releaseDate.setText(movie.getRelease_date());
        originalLanguage.setText(movie.getOriginal_language());
        String spokenLanguage="" ;

        for (MovieDetail.SpokenLanguagesBean s : movie.getSpoken_languages())
        {
            spokenLanguage  += s.getName()+ "\n";
        }

        spokenLanguages.setText(spokenLanguage);

        Picasso.get().load("http://image.tmdb.org/t/p/w500/"+movie.getBackdrop_path()).fit().centerCrop().
                placeholder(R.drawable.shadow_bottom_to_top).into(imagenMovie);

    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getActivity(), "Error ", message);
    }
}
