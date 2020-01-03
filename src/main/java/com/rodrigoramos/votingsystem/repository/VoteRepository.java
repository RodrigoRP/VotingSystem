package com.rodrigoramos.votingsystem.repository;

import com.rodrigoramos.votingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {


    @Query("SELECT COUNT(v) FROM Vote v WHERE v.restaurant_id =:restaurantId")
    long countAllByRestaurantId(@Param("restaurantId") int restaurantId);
}
