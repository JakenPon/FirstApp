package com.example.firstapp.presentation.model;

import java.io.Serializable;
import java.util.List;



public class Character implements Serializable {

    public class Character_origin implements Serializable{
        private String name_planet;
        private String url_planet;

        public String getName_planet() {
            return name_planet;
        }
    }

    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Character_origin origin;
    private Object location;
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

    public Object getLocation() { return location; }

    public String getImage() {
        return image;
    }

    public List<String> getEpisode() {
        return episode;
    }

    public String getUrl() {
        return url;
    }

   /* public String getCreated() {
        return created;
    } */

}
