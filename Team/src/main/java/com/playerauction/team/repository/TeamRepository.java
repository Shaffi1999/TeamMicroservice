package com.playerauction.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.playerauction.team.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team,String>{
public Team findByTeamName(String teamName);
@Query("SELECT t.teamBudget FROM Team t WHERE t.teamName = :teamName")
long findBudgetByTeamName(@Param("teamName") String teamName);

@Transactional
@Modifying
@Query("update Team  set teamBudget = (teamBudget-:biddingPrice) where teamName = :teamName")
void updateTeamBudget(@Param("biddingPrice") long biddingPrice, @Param("teamName") String teamName);


}
