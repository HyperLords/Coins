package com.OyunTarayici.Coins.Listener;

import org.BukkitApi.main.BukkitUtiles.ListenerUtils.BukkitListener;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import com.OyunTarayici.Coins.Managers.CoinsManager;

public class PlayerListener extends BukkitListener {

	public PlayerListener(JavaPlugin plugin) {
		super(plugin);
	}

	public PlayerListener(JavaPlugin plugin, Listener listener) {
		super(plugin, listener);
	}

	@EventHandler
	public void event(PlayerJoinEvent e) {
		CoinsManager.createAccounts(e.getPlayer());
		CoinsManager.saveAccounts(e.getPlayer());
		CoinsManager.createAccounts(e.getPlayer());
	}
	
	@EventHandler
	public void event(PlayerQuitEvent e) {
		OfflinePlayer offlinePlayer=(OfflinePlayer)e.getPlayer();
		CoinsManager.saveAccounts(e.getPlayer());
		CoinsManager.createAccounts(offlinePlayer.getPlayer());
	}
}
