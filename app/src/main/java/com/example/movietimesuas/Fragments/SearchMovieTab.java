package com.example.movietimesuas.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.movietimesuas.Adapters.AdapterSearchMovie;
import com.example.movietimesuas.Model.MovieList;
import com.example.movietimesuas.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Objects;

public class SearchMovieTab extends Fragment
{
    private EditText searchBox;
    private Button btnSearch;
    private RecyclerView recyclerViewMovieList;
    private AdapterSearchMovie adapterSearchMovie;
    private ArrayList<MovieList> movieLists;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_search_movie, container, false);
        searchBox = v.findViewById(R.id.searchBar);
        btnSearch = v.findViewById(R.id.btnSearch);

        recyclerViewMovieList = v.findViewById(R.id.recViewListMovie);
        btnSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                parsingJS0N();
            }
        });

        recyclerViewMovieList.setHasFixedSize(true);
        recyclerViewMovieList.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapterSearchMovie = new AdapterSearchMovie(getContext(),movieLists);
        recyclerViewMovieList.setAdapter(adapterSearchMovie);

        return v;
    }

    private void parsingJS0N()
    {
        movieLists = new ArrayList<>();
        String title = searchBox.getText().toString();
        if(title.isEmpty())
        {
            Toast.makeText(getActivity(), "Please fill movie title",Toast.LENGTH_SHORT).show();
        }
        else {
            final String url = "https://www.omdbapi.com/?apikey=d0440216&s=" + title;
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>()
                    {
                        @Override
                        public void onResponse(JSONObject response)
                        {
                            if(response != null && response.length() > 0)
                            {
                                try
                                {
                                    String responsed = response.getString("Response");
                                    if(responsed.equals("False"))
                                    {
                                        Toast.makeText(getActivity(), "No such title exists",Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        try
                                        {
                                            JSONArray jsonArray = response.getJSONArray("Search");
                                            for (int i = 0; i < jsonArray.length(); i++)
                                            {
                                                JSONObject object = jsonArray.getJSONObject(i);
                                                String name = object.getString("Title");
                                                String poster = object.getString("Poster");
                                                String year = object.getString("Year");
                                                String id = object.getString("imdbID");
                                                MovieList movieList = new MovieList(poster,name,year, id);
                                                movieLists.add(movieList);

                                            }
                                            recyclerViewMovieList.setHasFixedSize(true);
                                            recyclerViewMovieList.setLayoutManager(new GridLayoutManager(getContext(),2));
                                            adapterSearchMovie = new AdapterSearchMovie(getContext(),movieLists);
                                            recyclerViewMovieList.setAdapter(adapterSearchMovie);
                                        }
                                        catch (JSONException e)
                                        {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }
                        }, new Response.ErrorListener()
            {
                @Override public void onErrorResponse(VolleyError error)
                {
                    Toast.makeText(getActivity(), "No connection. Please connect to the internet",Toast.LENGTH_SHORT).show();
                }
            });
            Cache cache = new DiskBasedCache(Objects.requireNonNull(getActivity()).getCacheDir(), 10000*10000);
            Network network = new BasicNetwork(new HurlStack());
            RequestQueue requestQueue = new RequestQueue(cache, network);
            requestQueue.start();
            requestQueue.add(request);

            InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null)
            {
                inputMethodManager.hideSoftInputFromWindow(btnSearch.getWindowToken(), 0);
            }
        }
    }

}
