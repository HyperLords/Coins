package com.OyunTarayici.Coins;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.OyunTarayici.Coins.Commands.CommandCoins;
import com.OyunTarayici.Coins.Files.FileCoins;
import com.OyunTarayici.Coins.Listener.PlayerListener;
import com.OyunTarayici.Coins.Managers.CoinsManager;
import lombok.Getter;

public class EcoCoins extends JavaPlugin implements Listener {

	@Getter
	private static EcoCoins ecoCoins;
	
	@Override
	public void onEnable() {
		ecoCoins=this;
		
		CoinsManager.loadAccounts();
		CoinsManager.reloadAccounts();
		CoinsManager.loadAccounts();
		
		registerCommands();
		registerFiles();
		registerListener();
	}

	private void registerListener() {
		new PlayerListener(this,new PlayerListener(this,this));
	}

	private void registerFiles() {
		new FileCoins(ecoCoins, getFile());
	}

	private void registerCommands() {
		new CommandCoins(this,"coins");
	}

	@Override
	public void onDisable() {
		CoinsManager.reloadAccounts();
	}
	
}
