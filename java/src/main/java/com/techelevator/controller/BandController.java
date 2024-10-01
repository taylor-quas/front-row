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
    public List<BandGenreDto> getAllBands() {
        return bandDao.getAllBands();
    }

    @GetMapping("/search-bands/{searchTerm}")
    public List<BandGenreDto> searchBands(@PathVariable String searchTerm) {
        return bandDao.searchBandGenre(searchTerm);
    }

    @PutMapping("/{bandId}/update")
    public void updateBand(@RequestBody @Valid Band band, @PathVariable long bandId) {
        if (bandId != band.getBandId()) {
            throw new IllegalArgumentException("Band name in the path does not match the request body");
        }
        bandDao.updateBand(band);
    }

    @GetMapping("/my-bands")
    public List<Band> getSubscribedBands(Principal principal) {
        return bandDao.getSubscribedBands(principal);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-band")
    public void createBand(@RequestBody @Valid Band band, Principal principal) {
        bandDao.createBand(band, principal);
    }

    @GetMapping("/{bandName}")
    public BandGenreDto getBandByName(@PathVariable String bandName) {
        return bandDao.getBandByBandName(bandName);
    }

    @PostMapping("/subscribe/{bandId}")
    public void subscribeToBand(@PathVariable long bandId, Principal principal) {
        bandDao.subscribe(bandId, principal);
    }

}
