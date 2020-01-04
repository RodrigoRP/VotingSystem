package com.rodrigoramos.votingsystem.utils;

import com.rodrigoramos.votingsystem.service.impl.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledResetWinners {

    @Autowired
    private VoteService voteService;

    @Scheduled(cron = "0 30 11 * * 1-5")
    public void resetListWinners() {
        voteService.countWinnerRestaurant();
    }

}
