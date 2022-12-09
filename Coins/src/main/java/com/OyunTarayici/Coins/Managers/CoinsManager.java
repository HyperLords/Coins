package com.OyunTarayici.Coins.Managers;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import com.OyunTarayici.Coins.EcoCoins;
import com.OyunTarayici.Coins.Profiles.CoinsProfile;

public class CoinsManager {

	public static void createAccounts(Player player) {
		File file=new File(EcoCoins.getEcoCoins().getDataFolder(),"coins.yml");
		FileConfiguration c=YamlConfiguration.loadConfiguration(file);		
		if (!player.hasPlayedBefore()) {
			CoinsProfile.getEcoCoins().put(player.getUniqueId(), new CoinsProfile(0));
			c.set("coins."+player.getUniqueId()+".eco", 0);
			try {
				c.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		return;}else {
			int coins=c.getInt("coins."+player.getUniqueId()+".eco");
			CoinsProfile.getEcoCoins().put(player.getUniqueId(), new CoinsProfile(coins));
		}
	}
	
	public static void loadAccounts() {
		Bukkit.getOnlinePlayers().forEach(allPlayers->{
			CoinsManager.createAccounts(allPlayers);
		});
	}
	
	public static void reloadAccounts() {
		Bukkit.getOnlinePlayers().forEach(allPlayers->{
			CoinsManager.saveAccounts(allPlayers);
		});
	}
	
	public static void saveAccounts(Player player) {
		File file=new File(EcoCoins.getEcoCoins().getDataFolder(),"coins.yml");
		FileConfiguration c=YamlConfiguration.loadConfiguration(file);
		CoinsProfile coinsProfile=CoinsProfile.getEcoCoins().get(player.getUniqueId());
		if (CoinsProfile.getEcoCoins().containsKey(player.getUniqueId())) {
			c.set("coins."+player.getUniqueId()+".eco", coinsProfile.getCoins());
			try {
				c.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			CoinsProfile.getEcoCoins().remove(player.getUniqueId());
		}
	}
	
	public static void addCoins(CoinsProfile coinsProfile,int addCoin) {
		coinsProfile.setCoins(coinsProfile.getCoins()+addCoin);
	}

	public static void setCoins(CoinsProfile coinsProfile,int setCoin) {
		coinsProfile.setCoins(setCoin);
	}

	public static void deleteCoins(CoinsProfile coinsProfile,int deleteCoin) {
		coinsProfile.setCoins(coinsProfile.getCoins()-deleteCoin);
	}

	public static void resetCoins(CoinsProfile coinsProfile) {
		coinsProfile.setCoins(0);
	}
	
	public static Integer getCoins(CoinsProfile coinsProfile) {
		return coinsProfile.getCoins();
	}
	
}
