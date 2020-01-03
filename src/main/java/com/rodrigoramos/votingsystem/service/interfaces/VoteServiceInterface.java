package com.rodrigoramos.votingsystem.service.interfaces;

import com.rodrigoramos.votingsystem.model.Vote;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

public interface VoteServiceInterface {

    LocalTime EXPIRATION_TIME = LocalTime.of(23, 50);
    ZoneId ZONE_ID = ZoneId.of("America/Sao_Paulo");

    void checkVotingAvailability(LocalTime localTime);

    void vote(Integer rest_id, LocalDateTime localDateTime);

    long countAllByRestaurantId(Integer restaurantId);

    long countAll();

    List<Vote> findByVotingDate(Date votingDate);

    //List<Vote> countDistinctByRestaurant_id();
}
