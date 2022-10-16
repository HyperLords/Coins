package com.OyunTarayici.Coins.Managers;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import com.OyunTarayici.Coins.GameObject;
import com.OyunTarayici.Coins.Profiles.PlayerProfiles;

public class CoinManager {

	public static void createAccount(Player player,PlayerProfiles playerProfiles) {
		File file=new File(GameObject.getGameObject().getDataFolder(),"account.yml");
		FileConfiguration c=YamlConfiguration.loadConfiguration(file);
		if (!player.hasPlayedBefore()) {
			PlayerProfiles.getProfiles().put(player.getUniqueId(), new PlayerProfiles(0));
			c.set("account."+player.getUniqueId()+".Coins", 0);
			try {
				c.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			int coin=c.getInt("account."+player.getUniqueId()+".Coins");
			PlayerProfiles.getProfiles().put(player.getUniqueId(), new PlayerProfiles(coin));
		}
	}
	
	public static void saveAccount(Player player,PlayerProfiles playerProfiles) {
		File file=new File(GameObject.getGameObject().getDataFolder(),"account.yml");
		FileConfiguration c=YamlConfiguration.loadConfiguration(file);
		if (PlayerProfiles.getProfiles().containsKey(player.getUniqueId())) {
			c.set("account."+player.getUniqueId()+".Coins", playerProfiles.getCoin());
			try {
				c.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			PlayerProfiles.getProfiles().remove(player.getUniqueId());
		}
	}
	
	public void addCoin(PlayerProfiles playerProfiles,int addcoin) {
		playerProfiles.setCoin(playerProfiles.getCoin()+addcoin);
	}
	
	public void setCoin(PlayerProfiles playerProfiles,int setcoin) {
		playerProfiles.setCoin(setcoin);
	}
	
	public void deleteCoin(PlayerProfiles playerProfiles,int deletecoin) {
		playerProfiles.setCoin(playerProfiles.getCoin()-deletecoin);
	}

	public void resetCoin(PlayerProfiles playerProfiles) {
		playerProfiles.setCoin(0);
	}
	
	public String getPlayerName(PlayerProfiles playerProfiles) {
		return playerProfiles.getName();
	}
	
	public int getPlayerCoin(PlayerProfiles playerProfiles) {
		return playerProfiles.getCoin();
	}
	
}
