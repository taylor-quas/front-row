package com.techelevator.model;

import java.util.List;

public class BandGenreDto {

    private String bandName;
    private String bandHeroImage;
//    private List<String> genreNames;

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getBandHeroImage() {
        return bandHeroImage;
    }

    public void setBandHeroImage(String bandHeroImage) {
        this.bandHeroImage = bandHeroImage;
    }

//    public List<String> getGenreNames() {
//        return genreNames;
//    }
//
//    public void setGenreNames(List<String> genreNames) {
//        this.genreNames = genreNames;
//    }
}
