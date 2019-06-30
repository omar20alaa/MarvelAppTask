package com.example.marvel.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public  class Item implements Serializable {

    @Expose
    @SerializedName("thumbnail")
    private Thumbnail thumbnail;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("id")
    private int id;

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public  class Thumbnail {
        @Expose
        @SerializedName("extension")
        private String extension;
        @Expose
        @SerializedName("path")
        private String path;

        public String getExtension() {
            return extension;
        }

        public String getPath() {
            return path;
        }
    }
}
