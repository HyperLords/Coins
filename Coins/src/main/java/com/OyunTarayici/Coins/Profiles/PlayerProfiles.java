package com.OyunTarayici.Coins.Profiles;

import java.util.HashMap;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class PlayerProfiles {

	@Getter
	public static HashMap<UUID, PlayerProfiles> profiles=new HashMap<>();
	
	private String name;

	private int coin=0;
	
	public PlayerProfiles(int coin) {
		this.coin=coin;
	}
}
