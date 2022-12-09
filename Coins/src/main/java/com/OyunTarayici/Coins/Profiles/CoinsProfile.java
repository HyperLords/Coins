package com.OyunTarayici.Coins.Profiles;

import java.util.HashMap;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CoinsProfile {

	@Getter
	public static HashMap<UUID, CoinsProfile> EcoCoins=new HashMap<UUID, CoinsProfile>();
	
	private int coins=0;
	
	public CoinsProfile(int coins) {
		this.coins=coins;
	}
	
}
