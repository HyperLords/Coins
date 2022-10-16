package com.OyunTarayici.Coins;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.OyunTarayici.Coins.Commands.CommandCoins;
import com.OyunTarayici.Coins.Configuration.GameBackup;
import com.OyunTarayici.Coins.Listener.CoinListener;
import com.OyunTarayici.Coins.Managers.CoinManager;
import com.OyunTarayici.Coins.Profiles.PlayerProfiles;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;

public class GameObject extends JavaPlugin implements Listener {

	@Getter
	private static GameObject gameObject;
	
	@Override
	public void onEnable() {
		gameObject=this;
		
		if (getServer().getPluginManager().getPlugin("BukkitApi")!=null) {
			getServer().getPluginManager().enablePlugin(this);
			for (Player allPlayer:getServer().getOnlinePlayers()) {
				PlayerProfiles playerProfiles=PlayerProfiles.getProfiles().get(allPlayer.getUniqueId());
				CoinManager.createAccount(allPlayer, playerProfiles);
				registerCommands();
				registerListener();
				registerFile();
			}
		}else {
			getServer().getPluginManager().disablePlugin(this);
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lSorry but not work plugin not search BukkitApi plugins"));
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lHow many about download BukkitApi download 1 links: github.com/HyperLords"));
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lHow many about download BukkitApi download 2 links: spigotmc.com/resources/BukkitApi"));
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lHow many about download BukkitApi download 3 links: spigotmc.com/resources/Coins"));
		return;}
	}
	
	private void registerFile() {
		new GameBackup(this, getFile());
	}

	private void registerListener() {
		new CoinListener(this,new CoinListener(this,this));
	}

	private void registerCommands() {
		new CommandCoins(gameObject, "coin");
	}

	@Override
	public void onDisable() {
		
		for (Player allPlayer:getServer().getOnlinePlayers()) {
			PlayerProfiles playerProfiles=PlayerProfiles.getProfiles().get(allPlayer.getUniqueId());
			CoinManager.saveAccount(allPlayer, playerProfiles);
		}
		
	}
	
}
