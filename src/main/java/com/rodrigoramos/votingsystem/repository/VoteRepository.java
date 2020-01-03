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


   // @Query("SELECT COUNT(v) FROM Vote v WHERE v.restaurant_id =:restaurantId")
    //@Query ("SELECT distinct m.restaurant_id AS restaurant_id, COUNT(m) AS total FROM Vote AS m GROUP BY m.restaurant_id ORDER BY m.restaurant_id ASC")
  //  long countAllByRestaurantId(@Param("restaurantId") int restaurantId);

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.restaurant_id =:restaurantId")
    long countAllByRestaurantId(@Param("restaurantId") int restaurantId);

    @Query(value = "select * from votes v where v.votingDate = ?1", nativeQuery = true)
    List<Vote> findByVotingDate(Date votingDate);

    //List<Vote> countDistinctByRestaurant_id();
}
