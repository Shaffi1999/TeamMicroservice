package com.playerauction.team.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
	@Id
private String playerName;
private String teamName;
private long biddingPrice;
}
