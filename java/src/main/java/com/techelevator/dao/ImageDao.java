package com.techelevator.dao;

import com.techelevator.model.Image;

import java.security.Principal;
import java.util.List;

public interface ImageDao {

    List<Image> getGallery(String bandName);

    Image getImage(long imageId);

    void uploadImage(String url, Principal principal);


}
