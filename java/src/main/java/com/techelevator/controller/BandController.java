package com.techelevator.controller;

import com.techelevator.dao.BandDao;
import com.techelevator.model.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class BandController {

    @Autowired
    private BandDao bandDao;

    @GetMapping("/bands")
    public List<Band> getAllBands() {
        return bandDao.getBandsBySearchTerm("");
    }

    @GetMapping("/search/{searchTerm}")
    public List<Band> getSearchBands(@PathVariable String searchTerm) {
        return bandDao.getBandsBySearchTerm(searchTerm);
    }

    @GetMapping("/my-bands")
    public List<Band> getSubscribedBands(Principal principal) {
        return bandDao.getSubscribedBands(principal);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-band")
    public Band createBand(@RequestBody @Valid Band band) {
        return bandDao.createBand(band);
    }

    //TODO: Get a single band for bandview

}
