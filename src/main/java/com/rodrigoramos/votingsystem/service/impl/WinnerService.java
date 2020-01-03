package com.rodrigoramos.votingsystem.service.impl;

import com.rodrigoramos.votingsystem.model.Winner;
import com.rodrigoramos.votingsystem.repository.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WinnerService {

    private final WinnerRepository winnerRepository;

    @Autowired
    public WinnerService(WinnerRepository winnerRepository) {
        this.winnerRepository = winnerRepository;
    }

    public void save(Winner winner) {
        winnerRepository.save(winner);
    }
}
