package com.cristhian.com.rappitest.Utils;

import android.widget.Filter;

import com.cristhian.com.rappitest.adapter.RecyclerviewMovieByCategoryAdapter;
import com.cristhian.com.rappitest.model.MovieResults;

import java.util.ArrayList;
import java.util.List;

public class CustomFilter extends Filter{

    RecyclerviewMovieByCategoryAdapter adapter;
    List<MovieResults.ResultsBean> filterList;

    public CustomFilter(List<MovieResults.ResultsBean> filterList, RecyclerviewMovieByCategoryAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        if(constraint != null && constraint.length() > 0)
        {
            constraint=constraint.toString().toUpperCase();

            ArrayList<MovieResults.ResultsBean> filteredPlayers=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                if(filterList.get(i).getTitle().toUpperCase().contains(constraint))
                {
                    filteredPlayers.add(filterList.get(i));
                }
            }

            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.movies= (ArrayList<MovieResults.ResultsBean>) results.values;
        adapter.notifyDataSetChanged();
    }
}