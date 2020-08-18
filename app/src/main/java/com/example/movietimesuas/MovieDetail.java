package com.example.movietimesuas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.movietimesuas.Database.MovieDatabase;

public class MovieDetail extends AppCompatActivity
{
    private Button btnSaveDelete;
    private String poster, nama, tahun, id;
    MovieDatabase movieDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorStatusBar));

        Toolbar toolbarMovieDetail = findViewById(R.id.toolbarMovieDetail);
        toolbarMovieDetail.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbarMovieDetail.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        ImageView posterMovieDetail = findViewById(R.id.movieDetailPoster);
        TextView posterMovieNama = findViewById(R.id.detailNama);
        TextView posterMovieTahun = findViewById(R.id.detailTahun);
        TextView posterMovieID = findViewById(R.id.detailId);

        poster = getIntent().getStringExtra("urlPoster");
        nama = getIntent().getStringExtra("namaMovie");
        tahun = getIntent().getStringExtra("tahunMovie");
        id = getIntent().getStringExtra("IDMovie");

        if(poster.equals("N/A"))
        {
            Glide.with(this).load(this.getDrawable(R.drawable.no_image)).into(posterMovieDetail);
        }
        else
        {
            Glide.with(this).load(poster).into(posterMovieDetail);
        }
        posterMovieNama.setText(nama);
        posterMovieTahun.setText(tahun);
        posterMovieID.setText(id);

        movieDatabase = new MovieDatabase(this);

        btnSaveDelete = findViewById(R.id.btnSaveDelete);
        if(movieDatabase.cekMovieID(id))
        {
            btnSaveDelete.setText("DELETE");
        }
        else
        {
            btnSaveDelete.setText("SAVE");
        }
        btnSaveDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(btnSaveDelete.getText().toString().equals("SAVE"))
                {
                    movieDatabase.saveMovie(poster,nama,tahun,id);
                    btnSaveDelete.setText("DELETE");
                }
                else if(btnSaveDelete.getText().toString().equals("DELETE"))
                {
                    movieDatabase.deleteMovieID(id);
                    btnSaveDelete.setText("SAVE");
                }
            }
        });
    }
}