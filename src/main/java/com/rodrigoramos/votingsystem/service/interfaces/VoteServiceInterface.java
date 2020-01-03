package com.rodrigoramos.votingsystem.service.interfaces;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public interface VoteServiceInterface {

    LocalTime EXPIRATION_TIME = LocalTime.of(11, 0);
    ZoneId ZONE_ID = ZoneId.of("America/Sao_Paulo");

     void checkVotingAvailability(LocalTime localTime);
    void vote(Integer rest_id, LocalDateTime localDateTime);

}
