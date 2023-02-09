package com.playerauction.team.model;

import java.util.List;

import com.playerauction.team.entity.Player;
import com.playerauction.team.entity.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequiredResponse {
private Team team;
private List<Player> players;
}
