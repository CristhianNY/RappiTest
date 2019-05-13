package com.cristhian.com.rappitest.view.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cristhian.com.rappitest.R;
import com.cristhian.com.rappitest.Utils;
import com.cristhian.com.rappitest.adapter.RecyclerviewMovieByCategory;
import com.cristhian.com.rappitest.model.MovieResults;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView {

    @BindView(R.id.list_movies)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    private HomePresenterLocal presenter;
    private String category;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);
        category = this.getArguments().getString("category");
        presenter = new HomePresenterLocal(this);
        presenter.getMoviesByCategory(getActivity(), category);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    public void setMovie(List<MovieResults.ResultsBean> movie) {

        RecyclerviewMovieByCategory adapter = new RecyclerviewMovieByCategory(getActivity(),movie);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new RecyclerviewMovieByCategory.ClickListener() {
            @Override
            public void onClick(View view, int position) {
        // TODO
            }
        });

    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getActivity(), "Error ", message);
    }
}
