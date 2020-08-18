package com.example.movietimesuas.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.movietimesuas.Adapters.AdapterSavedMovie;
import com.example.movietimesuas.Database.MovieDatabase;
import com.example.movietimesuas.R;

public class SavedMovieTab extends Fragment
{
    private RecyclerView recyclerViewSavedMovie;
    private TextView ketEmpty;
    private AdapterSavedMovie adapterSavedMovie;
    MovieDatabase movieDatabase;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_saved_movie, container, false);

        recyclerViewSavedMovie = v.findViewById(R.id.recViewListSaved);
        ketEmpty = v.findViewById(R.id.ketEmpty);
        movieDatabase = new MovieDatabase(getContext());

        getData();
        cekData();

        swipeRefreshLayout = v.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                if(adapterSavedMovie.getItemCount() < 1)
                {
                    ketEmpty.setText(R.string.noitem);
                    swipeRefreshLayout.setRefreshing(false);
                }
                else if(adapterSavedMovie.getItemCount() > 0)
                {
                    getData();
                    if(adapterSavedMovie.getItemCount() == 0)
                    {
                        ketEmpty.setText(R.string.noitem);
                    }
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        if(isVisibleToUser)
        {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

    private void getData()
    {
        recyclerViewSavedMovie.setHasFixedSize(true);
        recyclerViewSavedMovie.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapterSavedMovie = new AdapterSavedMovie(getContext(),movieDatabase.getData());
        adapterSavedMovie.notifyDataSetChanged();
        recyclerViewSavedMovie.setAdapter(adapterSavedMovie);
    }

    private void cekData()
    {
        if(adapterSavedMovie.getItemCount() == 0)
        {
            ketEmpty.setText(R.string.noitem);
        }
        else
        {
            ketEmpty.setText("");
        }
    }
}
