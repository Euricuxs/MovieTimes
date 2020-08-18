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
import com.example.movietimesuas.Fragments.SavedMovieTab;
import com.example.movietimesuas.Model.MovieSaved;
import com.example.movietimesuas.Database.MovieDatabase;
import com.example.movietimesuas.MovieDetail;
import com.example.movietimesuas.R;

import java.util.ArrayList;

public class AdapterSavedMovie extends RecyclerView.Adapter<AdapterSavedMovie.ViewHolder>
{
    private Context context;
    private ArrayList<MovieSaved> movieListSave;
    MovieDatabase movieDatabase;
    private String idTemp;
    SavedMovieTab savedMovieTab;

    public AdapterSavedMovie(Context context, ArrayList<MovieSaved> movieListSave)
    {
        this.context = context;
        this.movieListSave = movieListSave;
    }

    @NonNull
    @Override
    public AdapterSavedMovie.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(context).inflate(R.layout.item_saved_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSavedMovie.ViewHolder holder, int position)
    {
        final MovieSaved save = movieListSave.get(position);
        if(save.getPosterMovieSaved().equals("N/A"))
        {
            Glide.with(context).load(context.getResources().getDrawable(R.drawable.no_image)).into(holder.savedPosterRecview);
        }
        else
        {
            Glide.with(context).load(save.getPosterMovieSaved()).into(holder.savedPosterRecview);
        }
        holder.savedNamaRecview.setText(save.getNamaMovieSaved());
        holder.savedTahunRecview.setText(save.getTahunMovieSaved());
        holder.savedIDRecview.setText(save.getIDMovieSaved());
        holder.linearLayoutMovieSaved.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, MovieDetail.class);
                intent.putExtra("urlPoster",save.getPosterMovieSaved());
                intent.putExtra("namaMovie",save.getNamaMovieSaved());
                intent.putExtra("tahunMovie",save.getTahunMovieSaved());
                intent.putExtra("IDMovie",save.getIDMovieSaved());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return movieListSave.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, View.OnClickListener, MenuItem.OnMenuItemClickListener
    {
        ImageView savedPosterRecview;
        TextView savedNamaRecview, savedTahunRecview, savedIDRecview;
        LinearLayout linearLayoutMovieSaved;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            savedPosterRecview = itemView.findViewById(R.id.savedPosterMovie);
            savedNamaRecview = itemView.findViewById(R.id.savedNameMovie);
            savedTahunRecview= itemView.findViewById(R.id.savedYearMovie);
            savedIDRecview = itemView.findViewById(R.id.savedIDMovie);
            linearLayoutMovieSaved = itemView.findViewById(R.id.savedLinearLayoutMovieList);
            linearLayoutMovieSaved.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
        {
            idTemp = savedIDRecview.getText().toString();
            MenuItem menuItem = menu.add("Delete");
            menuItem.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item)
        {
            boolean valid = false;
            movieDatabase = new MovieDatabase(context);
            if(movieDatabase.cekMovieID(idTemp))
            {
                movieDatabase.deleteMovieID(idTemp);
                valid = true;
            }
            else
                {
                    valid = false;
            }
            return valid;
        }

        @Override
        public void onClick(View v)
        {

        }
    }
}
