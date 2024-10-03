package com.techelevator.controller;

import com.techelevator.dao.ImageDao;
import com.techelevator.model.Image;
import com.techelevator.model.ImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ImageController {

    @Autowired
    private ImageDao imageDao;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{bandName}/gallery")
    public List<Image> getImagesForGallery(@PathVariable String bandName) {
        return imageDao.getGallery(bandName);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/band/gallery-upload", method = RequestMethod.POST)
    public void uploadImageToGallery(@RequestBody ImageDto url, Principal principal){
        imageDao.uploadImage(url, principal);
    }
}
