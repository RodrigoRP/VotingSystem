package com.rodrigoramos.votingsystem.service.exception;

public class VotingIsUnavailableException extends RuntimeException {


    public VotingIsUnavailableException(String msg) {
        super(msg);
    }


    public VotingIsUnavailableException(String msg, Throwable cause) {
        super(msg, cause);
    }



}