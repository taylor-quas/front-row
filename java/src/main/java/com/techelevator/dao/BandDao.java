package com.techelevator.dao;

import com.techelevator.model.Band;
import com.techelevator.model.BandGenreDto;
import com.techelevator.model.Genre;

import java.security.Principal;
import java.util.List;

public interface BandDao {

    List<Band> getSubscribedBands(Principal principal);

    Band getBandByBandId(long bandId);
    Band getBandByBandName(String bandName);
    // but what if there are
    // multiple bands with the same name???
    List<Band> getAllBands();

    List<Band> getBandsBySearchTerm(String searchTerm, List<Long> genreIds);

    void updateBand(Band updatedBand);

    void createBand(Band newBand);

    List<BandGenreDto> searchBandGenre(String searchTerm);

}
