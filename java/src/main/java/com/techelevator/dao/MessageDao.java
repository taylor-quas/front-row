package com.techelevator.dao;

import com.techelevator.model.Message;

import java.security.Principal;
import java.util.List;

public interface MessageDao {

    List<Message> getInboxMessages(Principal principal);
    // messages from the bands that the principal is subscribed to

    Message sendMessage(Message message);

}
