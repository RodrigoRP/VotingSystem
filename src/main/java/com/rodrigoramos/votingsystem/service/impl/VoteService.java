package com.rodrigoramos.votingsystem.service.impl;

import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.model.Vote;
import com.rodrigoramos.votingsystem.repository.EmployeeRepository;
import com.rodrigoramos.votingsystem.repository.VoteRepository;
import com.rodrigoramos.votingsystem.service.exception.VotingIsUnavailableException;
import com.rodrigoramos.votingsystem.service.interfaces.VoteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class VoteService implements VoteServiceInterface {

    private VoteRepository voteRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository, EmployeeRepository employeeRepository) {
        this.voteRepository = voteRepository;
        this.employeeRepository = employeeRepository;
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


}
