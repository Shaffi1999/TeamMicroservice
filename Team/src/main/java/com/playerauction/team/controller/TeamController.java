package com.playerauction.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.playerauction.team.entity.Team;
import com.playerauction.team.exception.TeamAlreadyExistException;
import com.playerauction.team.model.RequiredResponse;
import com.playerauction.team.service.TeamServiceImpl;

@RestController
@RequestMapping("team")
public class TeamController {
	@Autowired
	private TeamServiceImpl teamService;
	
	@PostMapping("/add")
	
	public Team createTeam(@RequestBody Team team)
	{
		String teamName = team.getTeamName();
		if (teamName != null && !"".equals(teamName)) {
			Team registerationObj = teamService.checkTeam(teamName);
			if (registerationObj != null) {
				
				throw new TeamAlreadyExistException("Team With This Team Name " + teamName + "Is Already Exist:");
			}
		}
		return teamService.addTeam(team);
	}
	
	@GetMapping("/getBudget/{teamName}")
	
	public long getBudget(@PathVariable("teamName") String teamName)
	{
		return teamService.getTotalBudget(teamName);
	}
	
	@GetMapping("/updateBudget/{biddingPrice}/{teamName}")
	public void updateTeamBudget(@PathVariable("biddingPrice") long biddingPrice,@PathVariable("teamName") String teamName)
	{
		 teamService.updateBudget(biddingPrice, teamName);
		
	}
	
	@GetMapping("/getAllPlayers/{teamName}")
	public RequiredResponse getAllPlayers(@PathVariable("teamName") String teamName)
	{
		return teamService.getAllDetailsBasedOnTeamName(teamName);
	}
	
	

}
