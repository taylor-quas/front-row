package com.techelevator.dao;

import com.techelevator.model.Band;

import java.security.Principal;
import java.util.List;

public interface BandDao {

    List<Band> getSubscribedBands(Principal principal);

    Band getBandByBandId(long bandId);

    List<Band> getBandsBySearchTerm(String searchTerm);

    void updateBand(Band updatedBand);

}
