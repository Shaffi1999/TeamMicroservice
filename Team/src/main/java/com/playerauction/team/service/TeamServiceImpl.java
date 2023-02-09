package com.playerauction.team.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.playerauction.team.entity.Player;
import com.playerauction.team.entity.Team;
import com.playerauction.team.model.RequiredResponse;
import com.playerauction.team.repository.TeamRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class TeamServiceImpl implements ITeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	Logger logger=LoggerFactory.getLogger(TeamServiceImpl.class);

	@Override
	public Team addTeam(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public Team checkTeam(String teamName) {

		return teamRepository.findByTeamName(teamName);
	}

	@Override
	public long getTotalBudget(String teamName) {

		return teamRepository.findBudgetByTeamName(teamName);
	}

	@Override
	public void updateBudget(long biddingPrice, String teamName) {
		teamRepository.updateTeamBudget(biddingPrice, teamName);

	}

	@Override
	//@Retry(name="player-api",fallbackMethod = "handlePlayerDownTime")
//	@CircuitBreaker(name="default",fallbackMethod = "handlePlayerDownTime")
	public RequiredResponse getAllDetailsBasedOnTeamName(String teamName) {
		logger.info("Called");
		RequiredResponse requiredResponse = new RequiredResponse();
		Team team = teamRepository.findByTeamName(teamName);
		requiredResponse.setTeam(team);
		List<Player> players = restTemplate.getForObject("http://localhost:8083/player/getAll/"+ teamName, List.class);
		requiredResponse.setPlayers(players);
		return requiredResponse;
	}
	
//	public RequiredResponse handlePlayerDownTime(String teamName,Exception ex) {
//		RequiredResponse requiredResponse = new RequiredResponse();
//		Team team = teamRepository.findByTeamName(teamName);
//		requiredResponse.setTeam(team);
//		return requiredResponse;
//	} 

}
