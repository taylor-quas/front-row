package com.techelevator.model;

public class MessageBandDto {

    private Message message;
    private String bandName;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }
}
