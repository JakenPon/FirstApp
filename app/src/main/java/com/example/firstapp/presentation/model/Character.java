package com.example.firstapp.presentation.model;

import java.io.Serializable;
import java.util.List;



public class Character implements Serializable {


    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Character_origin origin;
    private Character_location location;
    private String image;
    private List<String> episode;
    private String url;
    private String created;




    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecies() {
        return species;
    }

    public String getType() {
        return type;
    }

    public String getGender() {
        return gender;
    }

    public Character_origin getOrigin() {
        return origin;
    }

    public Character_location getLocation() { return location; }

    public String getImage() {
        return image;
    }

    public List<String> getEpisode() {
        return episode;
    }

    public String getUrl() {
        return url;
    }


}
