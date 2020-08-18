package com.example.movietimesuas.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.movietimesuas.Model.MovieList;
import com.example.movietimesuas.Database.MovieDatabase;
import com.example.movietimesuas.MovieDetail;
import com.example.movietimesuas.R;
import java.util.ArrayList;

public class AdapterSearchMovie extends RecyclerView.Adapter<AdapterSearchMovie.ViewHolder>
{
    private Context context;
    private ArrayList<MovieList> movieListes;
    MovieDatabase movieDatabase;
    private String posterTemp,namaTemp,tahunTemp,idTemp;

    public AdapterSearchMovie(Context context, ArrayList<MovieList> movieLists)
    {
        this.context = context;
        this.movieListes = movieLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(context).inflate(R.layout.item_movie_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        final MovieList movie = movieListes.get(position);
        if(movie.getPosterMovieList().equals("N/A"))
        {
            Glide.with(context).load(context.getResources().getDrawable(R.drawable.no_image)).into(holder.posterMovieRecView);
        }
        else
        {
            Glide.with(context).load(movie.getPosterMovieList()).into(holder.posterMovieRecView);
        }
        holder.namaMovieRecView.setText(movie.getNamaMovieList());
        holder.tahunMovieRecView.setText(movie.getTahunMovieList());
        holder.idMovieRecView.setText(movie.getIDMovieList());
        holder.urlMovieRecView.setText(movie.getPosterMovieList());
        holder.linearLayoutMovieList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, MovieDetail.class);
                intent.putExtra("urlPoster",movie.getPosterMovieList());
                intent.putExtra("namaMovie",movie.getNamaMovieList());
                intent.putExtra("tahunMovie",movie.getTahunMovieList());
                intent.putExtra("IDMovie",movie.getIDMovieList());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        if(movieListes == null)
        {
            return 0;
        }
        else
        {
            return movieListes.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, View.OnClickListener, MenuItem.OnMenuItemClickListener
    {
        ImageView posterMovieRecView;
        TextView namaMovieRecView;
        TextView tahunMovieRecView;
        TextView idMovieRecView;
        TextView urlMovieRecView;
        LinearLayout linearLayoutMovieList;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            posterMovieRecView = itemView.findViewById(R.id.movieImageItem);
            namaMovieRecView = itemView.findViewById(R.id.movieNameItem);
            tahunMovieRecView = itemView.findViewById(R.id.movieYearItem);
            idMovieRecView = itemView.findViewById(R.id.movieIDItem);
            urlMovieRecView = itemView.findViewById(R.id.movieURLItem);
            linearLayoutMovieList = itemView.findViewById(R.id.linearLayoutMovieList);
            linearLayoutMovieList.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
        {
            movieDatabase = new MovieDatabase(context);

            posterTemp = urlMovieRecView.getText().toString();
            namaTemp = namaMovieRecView.getText().toString();
            tahunTemp = tahunMovieRecView.getText().toString();
            idTemp = idMovieRecView.getText().toString();

            if(!movieDatabase.cekMovieID(idTemp))
            {
                MenuItem menuItem = menu.add("Save");
                menuItem.setOnMenuItemClickListener(this);
            }
            else
            {
                MenuItem menuItem = menu.add("Delete");
                menuItem.setOnMenuItemClickListener(this);
            }
        }

        @Override
        public boolean onMenuItemClick(MenuItem item)
        {
            if(item.getTitle().equals("Save"))
            {
                movieDatabase.saveMovie(posterTemp,namaTemp,tahunTemp,idTemp);
            }
            else if(item.getTitle().equals("Delete"))
            {
                movieDatabase.deleteMovieID(idTemp);
            }
            return false;
        }

        @Override
        public void onClick(View v)
        {

        }
    }
}
