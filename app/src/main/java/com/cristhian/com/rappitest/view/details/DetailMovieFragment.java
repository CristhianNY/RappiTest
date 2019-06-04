package com.cristhian.com.rappitest.view.details;


import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.InflateException;
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
import com.cristhian.com.rappitest.Utils.Utils;
import com.cristhian.com.rappitest.api.ConstantsServices;
import com.cristhian.com.rappitest.model.MovieDetail;
import com.cristhian.com.rappitest.model.VideoMovie;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailMovieFragment extends DialogFragment implements  DetailView,  YouTubePlayer.OnInitializedListener {

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
    private YouTubePlayer.OnInitializedListener mOnInitializedListener;
    private YouTubePlayer youTubePlayer;
    private FragmentTransaction transaction;

    private YouTubePlayerSupportFragment youTubePlayerFragment;
    private  String videoKey;

    private static View view;

    public DetailMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getActivity(),getTheme()){

            @Override
            public void onBackPressed() {

              if(youTubePlayer!=null){
                  youTubePlayer.release();
              };
              dismiss();
            }
        };
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        return dialog;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_detail_movie, container, false);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }

        ButterKnife.bind(this, view);

        youTubePlayerFragment = (YouTubePlayerSupportFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.youtube_player_fragment);


        movieid = this.getArguments().getInt("movieId");
        detailPresenter = new DetailPresenter(this);
        detailPresenter.getMovieByIdy(Integer.toString(movieid),getActivity());
        detailPresenter.getVideos(Integer.toString(movieid),getActivity());

        return view;

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

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
    public void setVideo(List<VideoMovie.ResultsBean> videos) {

        videoKey =  videos.get(0).getKey();
        youTubePlayerFragment.initialize(ConstantsServices.GOOGLE_API,this);
    }


    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getActivity(), "Error ", message);
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            youTubePlayer = player;

            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

            youTubePlayer.cueVideo(videoKey);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Utils.showDialogMessage(getActivity(), "Error ", youTubeInitializationResult.toString());
    }


}
