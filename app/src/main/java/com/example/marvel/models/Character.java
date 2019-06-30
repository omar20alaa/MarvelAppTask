package com.example.marvel.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Character implements Serializable {

    @Expose
    @SerializedName("resourceURI")
    private String resourceuri;
    @Expose
    @SerializedName("thumbnail")
    private Thumbnail thumbnail;
    @Expose
    @SerializedName("modified")
    private String modified;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private int id;

    public String getResourceuri() {
        return resourceuri;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public String getModified() {
        return modified;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public class Thumbnail implements Serializable {
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
