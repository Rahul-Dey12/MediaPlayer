package com.example.kiit.mediaplayer;



public class Songs {
    String name,no,movie;

    public Songs(String no, String name, String movie) {
        this.name = name;
        this.no = no;
        this.movie = movie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }
}
