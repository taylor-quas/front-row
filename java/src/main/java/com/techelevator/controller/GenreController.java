package com.techelevator.controller;

import com.techelevator.dao.GenreDao;
import com.techelevator.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class GenreController {

    @Autowired
    private GenreDao genreDao;

    @GetMapping("/genres")
    public List<String> getAllGenres() {
        return genreDao.getAllGenreNames();
    }

}
