package com.rodrigoramos.votingsystem.repository;

import com.rodrigoramos.votingsystem.model.Winner;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnerRepository extends JpaRepository<Winner, Integer> {
}
