package com.playerauction.team.service;

import com.playerauction.team.entity.Team;
import com.playerauction.team.model.RequiredResponse;

public interface ITeamService {
public Team addTeam(Team team);
public Team checkTeam(String teamName);
public long getTotalBudget(String teamName);
public void updateBudget(long biddingPrice,String teamName);
public RequiredResponse getAllDetailsBasedOnTeamName(String teamName);
}
