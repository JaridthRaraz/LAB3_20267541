package com.example.lab3_20267541;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Pelicula implements Serializable {

    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}