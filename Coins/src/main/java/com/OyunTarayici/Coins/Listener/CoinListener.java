package com.OyunTarayici.Coins.Listener;

import org.BukkitApi.main.BukkitUtiles.ListenerUtils.BukkitListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.OyunTarayici.Coins.Managers.CoinManager;
import com.OyunTarayici.Coins.Profiles.PlayerProfiles;

public class CoinListener extends BukkitListener{

	public CoinListener(JavaPlugin plugin) {
		super(plugin);
	}

	public CoinListener(JavaPlugin plugin, Listener listener) {
		super(plugin, listener);
	}

	@EventHandler
	public void event(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		PlayerProfiles playerProfiles=PlayerProfiles.getProfiles().get(e.getPlayer().getUniqueId());
		CoinManager.createAccount(e.getPlayer(), playerProfiles);
	}

	@EventHandler
	public void event(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		PlayerProfiles playerProfiles=PlayerProfiles.getProfiles().get(e.getPlayer().getUniqueId());
		CoinManager.saveAccount(e.getPlayer(), playerProfiles);
	}
}
