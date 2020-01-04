package com.rodrigoramos.votingsystem.service;

import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.model.Restaurant;
import com.rodrigoramos.votingsystem.model.Vote;
import com.rodrigoramos.votingsystem.repository.VoteRepository;
import com.rodrigoramos.votingsystem.service.impl.VoteService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VoteServiceTest {

    private static final String mockId = UUID.randomUUID().toString();

    @Mock
    VoteRepository voteRepository;

    @InjectMocks
    VoteService voteService;

    @Mock
    Vote mockVote;

    @Before
    public void setup() throws Exception {

        LocalDate votingDate = LocalDate.now();

       final Employee input = new Employee(1, "John", "John", "howtodoinjava@gmail.com", "04496322021", "123456");

        Restaurant restaurant = new Restaurant(1, "Carlo e Camilla in Segheria", "Barbecue", Boolean.FALSE);

        //mockVote = new Vote(mockId, votingDate, input, restaurant);
        mockVote = new Vote(1,input,votingDate,1);

    }



}
