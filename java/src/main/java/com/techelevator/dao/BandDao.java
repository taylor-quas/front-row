package com.techelevator.dao;

import com.techelevator.model.Band;
import com.techelevator.model.Genre;

import java.security.Principal;
import java.util.List;

public interface BandDao {

    List<Band> getSubscribedBands(Principal principal);

    Band getBandByBandId(long bandId);

    List<Band> getBandsBySearchTerm(String searchTerm);

    List<Band> getBandsByGenres(List<Genre> genres);

    void updateBand(Band updatedBand);

    Band createBand(Band newBand);

}
