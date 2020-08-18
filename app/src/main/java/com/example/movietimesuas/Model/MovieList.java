package com.example.movietimesuas.Model;

public class MovieList
{
    private String posterMovieList;
    private String namaMovieList;
    private String tahunMovieList;
    private String IDMovieList;

    public MovieList(String posterMovieList, String namaMovieList, String tahunMovieList, String IDMovieList)
    {
        this.posterMovieList = posterMovieList;
        this.namaMovieList = namaMovieList;
        this.tahunMovieList = tahunMovieList;
        this.IDMovieList = IDMovieList;
    }

    public String getPosterMovieList() {
        return posterMovieList;
    }

    public void setPosterMovieList(String posterMovieList) {
        this.posterMovieList = posterMovieList;
    }

    public String getNamaMovieList() {
        return namaMovieList;
    }

    public void setNamaMovieList(String namaMovieList) {
        this.namaMovieList = namaMovieList;
    }

    public String getTahunMovieList()
    {
        return tahunMovieList;
    }

    public void setTahunMovieList(String tahunMovieList)
    {
        this.tahunMovieList = tahunMovieList;
    }

    public String getIDMovieList()
    {
        return IDMovieList;
    }

    public void setIDMovieList(String IDMovieList)
    {
        this.IDMovieList = IDMovieList;
    }
}
