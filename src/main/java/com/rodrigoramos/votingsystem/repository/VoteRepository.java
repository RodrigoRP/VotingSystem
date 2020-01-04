package com.rodrigoramos.votingsystem.repository;

import com.rodrigoramos.votingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.sql.Date;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.restaurant_id =:restaurantId")
    Integer countAllByRestaurantId(@Param("restaurantId") int restaurantId);



    //@Query("UPDATE restaurants r SET r.already_voted = 'TRUE' WHERE r.restaurant_id =:restaurantId")
   // long updateAlreadyVoted(@Param("restaurantId") int restaurantId);


}
