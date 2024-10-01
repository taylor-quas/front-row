package com.techelevator.controller;

import com.techelevator.dao.BandDao;
import com.techelevator.model.Band;
import com.techelevator.model.BandGenreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class BandController {

    @Autowired
    private BandDao bandDao;

    @GetMapping("/bands")
    public List<Band> getAllBands() {
        return bandDao.getAllBands();
    }


//    @PostMapping("/search/filter/{searchTerm}")
//    public List<Band> getSearchFilterBands(@PathVariable String searchTerm, @RequestBody List<Long> genreIds) {
//        List<Band> searchedBands = bandDao.getBandsBySearchTerm(searchTerm);
//
//
//
//    }

    @GetMapping("/search-bands/{searchTerm}")
    public List<BandGenreDto> searchBands(@PathVariable String searchTerm) {
        return bandDao.searchBandGenre(searchTerm);
    }


    @GetMapping("/my-bands")
    public List<Band> getSubscribedBands(Principal principal) {
        return bandDao.getSubscribedBands(principal);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-band")
    public void createBand(@RequestBody @Valid Band band) {
        bandDao.createBand(band);
    }

    //TODO: Get a single band for bandview
    @GetMapping("/{bandName}")
    public Band getBandByName(@PathVariable String bandName) {
        return bandDao.getBandByBandName(bandName);
    }

}
