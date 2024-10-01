package com.techelevator.controller;

import com.techelevator.dao.MessageDao;
import com.techelevator.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class MessageController {

    @Autowired
    private MessageDao messageDao;

    @GetMapping("/inbox")
    public List<Message> getMyMessages(Principal principal) {
        return messageDao.getInboxMessages(principal);
    }
}
