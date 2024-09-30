package com.techelevator.controller;

import com.techelevator.dao.BandDao;
import com.techelevator.model.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    //TODO: Get a single band for bandview

}
