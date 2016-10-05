package com.kalpvaig.blogreaderapp.Model;

/**
 * Created by akshay on 5/10/16.
 */

public class Post {

    private int id;
    private String url;
    private String title;
    private String date;
    private String author;
    private String thubnail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getThubnail() {
        return thubnail;
    }

    public void setThubnail(String thubnail) {
        this.thubnail = thubnail;
    }
}
