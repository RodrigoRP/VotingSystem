package com.rodrigoramos.votingsystem.service.exception;

import java.time.LocalTime;


public class VotingIsUnavailableException extends RuntimeException {
/*
    private String message = "Voting is available after " +
            LocalTime.of(11,0).toString() + " and until " + LocalTime.of(0, 0).toString() + "\n" + "Your current time: ";*/

    public VotingIsUnavailableException(String msg) {
        super(msg);
        //this.message += time;
    }


    public VotingIsUnavailableException(String msg, Throwable cause) {
        super(msg, cause);
        //this.message += time;
    }



}