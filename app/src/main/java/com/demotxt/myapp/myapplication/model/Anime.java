package com.demotxt.myapp.myapplication.model;

import java.util.Date;

/**
 * Created by Aws on 11/03/2018.
 */

public class Anime {

    private String name ;
    private String Description;
    private String rating;
    private String nb_episode;
    private String categorie;
    private String studio ;
    private String image_url;
    private String dia;
    public Date presenca;

    public Anime() {
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Anime(String name, String description, String rating, String nb_episode, String categorie, String studio, String image_url, String dia, Date presenca) {
        this.name = name;
        Description = description;
        this.rating = rating;
        this.nb_episode = nb_episode;
        this.categorie = categorie;
        this.studio = studio;
        this.image_url = image_url;
        this.dia = dia;
        this.presenca = presenca;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public String getRating() {
        return rating;
    }

    public String getNb_episode() {
        return nb_episode;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getStudio() {
        return studio;
    }

    public String getImage_url() {
        return image_url;
    }

    public Date getPresenca() {
        return presenca;
    }




    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setNb_episode(String nb_episode) {
        this.nb_episode = nb_episode;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setPresenca(Date presenca) {
        this.presenca = presenca;
    }
}
