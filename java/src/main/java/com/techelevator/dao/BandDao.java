package com.techelevator.dao;

import com.techelevator.model.Band;
import com.techelevator.model.BandGenreDto;
import com.techelevator.model.Genre;

import java.security.Principal;
import java.util.List;

public interface BandDao {

    List<Band> getSubscribedBands(Principal principal);

    Band getBandByBandId(long bandId);
    BandGenreDto getBandByBandName(String bandName);
    // but what if there are
    // multiple bands with the same name???
    List<BandGenreDto> getAllBands();

    void updateBand(Band updatedBand);

    void createBand(Band newBand, Principal principal);

    List<BandGenreDto> searchBandGenre(String searchTerm);

    void subscribe(long bandId, Principal principal);

}
