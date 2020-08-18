package com.example.movietimesuas.Model;

public class MovieSaved
{
    private String posterMovieSaved;
    private String namaMovieSaved;
    private String tahunMovieSaved;
    private String IDMovieSaved;

    public MovieSaved(String posterMovieSaved, String namaMovieSaved, String tahunMovieSaved, String IDMovieSaved)
    {
        this.posterMovieSaved = posterMovieSaved;
        this.namaMovieSaved = namaMovieSaved;
        this.tahunMovieSaved = tahunMovieSaved;
        this.IDMovieSaved = IDMovieSaved;
    }

    public String getPosterMovieSaved() {
        return posterMovieSaved;
    }

    public void setPosterMovieSaved(String posterMovieSaved) {
        this.posterMovieSaved = posterMovieSaved;
    }

    public String getNamaMovieSaved() {
        return namaMovieSaved;
    }

    public void setNamaMovieSaved(String namaMovieSaved) {
        this.namaMovieSaved = namaMovieSaved;
    }

    public String getTahunMovieSaved() {
        return tahunMovieSaved;
    }

    public void setTahunMovieSaved(String tahunMovieSaved) {
        this.tahunMovieSaved = tahunMovieSaved;
    }

    public String getIDMovieSaved() {
        return IDMovieSaved;
    }

    public void setIDMovieSaved(String IDMovieSaved) {
        this.IDMovieSaved = IDMovieSaved;
    }

}
