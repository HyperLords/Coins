package com.OyunTarayici.Coins.Files;

import java.io.File;
import java.io.IOException;
import org.BukkitApi.main.BukkitUtiles.MulticonfigurationUtils.MultiConfigurationVarient;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FileCoins extends MultiConfigurationVarient{

	public FileCoins(JavaPlugin plugin, File file) {
		super(plugin, file);
		onFile(file);
	}

	@Override
	protected void onFile(File file) {
		newFileCreate(file);
		saveFile(file);
	}
	
	@Override
	public File newFileCreate(File file) {
		return new File(getPlugin().getDataFolder(),"coins.yml");
	}

	@Override
	public File saveFile(File file) {
		file=new File(getPlugin().getDataFolder(),"coins.yml");
		FileConfiguration c=YamlConfiguration.loadConfiguration(file);
		try {
			c.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	
}
