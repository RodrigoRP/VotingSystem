package com.rodrigoramos.votingsystem.service.impl;

import com.google.common.collect.ImmutableList;
import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.model.Restaurant;
import com.rodrigoramos.votingsystem.model.Vote;
import com.rodrigoramos.votingsystem.model.Winner;
import com.rodrigoramos.votingsystem.repository.EmployeeRepository;
import com.rodrigoramos.votingsystem.repository.RestaurantRepository;
import com.rodrigoramos.votingsystem.repository.VoteRepository;
import com.rodrigoramos.votingsystem.repository.WinnerRepository;
import com.rodrigoramos.votingsystem.service.exception.VotingIsUnavailableException;
import com.rodrigoramos.votingsystem.service.interfaces.VoteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class VoteService implements VoteServiceInterface {

    private VoteRepository voteRepository;
    private EmployeeRepository employeeRepository;
    private WinnerRepository winnerRepository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository, EmployeeRepository employeeRepository, WinnerRepository winnerRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.employeeRepository = employeeRepository;
        this.winnerRepository = winnerRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void checkVotingAvailability(LocalTime currentTime) {
        if (currentTime.isAfter(VoteServiceInterface.EXPIRATION_TIME)) {
            throw new VotingIsUnavailableException("Horario fora");
        }
    }

    @Override
    public void vote(Integer rest_id, LocalDateTime localDateTime) {
        this.checkVotingAvailability(LocalTime.now(VoteServiceInterface.ZONE_ID));

        Employee employee = employeeRepository.findByEmail(UserService.authenticated().getUsername());
        if (hasAlreadyVotedToday(employee.getVote())) {
            updateVote(rest_id, employee);
        } else {
            createVote(rest_id, employee);
        }

    }


    private boolean hasAlreadyVotedToday(Vote vote) {
        return vote.getDateUpdated().isEqual(LocalDate.now(VoteServiceInterface.ZONE_ID));
    }

    private void updateVote(Integer rest_id, Employee employee) {
        Vote vote = employee.getVote();
        vote.setRestaurant_id(rest_id);
        voteRepository.save(vote);
    }

    private void createVote(Integer rest_id, Employee employee) {
        Vote vote = new Vote(employee);
        vote.setRestaurant_id(rest_id);
        vote.setDateUpdated(LocalDate.now(VoteServiceInterface.ZONE_ID));

        employee.setVote(vote);
        employeeRepository.save(employee);
    }

    @Override
    public long countAll() {
        return voteRepository.count();
    }

    @Override
    public long countAllByRestaurantId(Integer restaurantId) {
        return voteRepository.countAllByRestaurantId(restaurantId);
    }

    public Integer countWinnerRestaurant() {
        Winner winner;
        Integer win;
        Iterable<Vote> itrVote = voteRepository.findAll();
        List<Vote> listVote = ImmutableList.copyOf(itrVote);
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        for (Vote vote : listVote) {
            long result = countAllByRestaurantId(vote.getId());
            countMap.put(Math.toIntExact(vote.getId()), (int) result);
        }
        win = countMap.entrySet().stream().
                max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();

        winner = new Winner();
        winner.setLunchDate(new Date(Calendar.getInstance().getTimeInMillis()));
        winner.setRestaurant(win);

        winnerRepository.save(winner);

        updateRestaurant(win);

        return win;
    }


    private void updateRestaurant(Integer rest_id) {
        Restaurant rest = restaurantRepository.findRestaurantById(rest_id);
        rest.setAlreadyVoted(Boolean.TRUE);
        restaurantRepository.save(rest);
    }


}
