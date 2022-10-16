package com.OyunTarayici.Coins.Configuration;

import java.io.File;
import java.io.IOException;
import org.BukkitApi.main.BukkitUtiles.MulticonfigurationUtils.MultiConfigurationVarient;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class GameBackup extends MultiConfigurationVarient{

	public GameBackup(JavaPlugin plugin, File file) {
		super(plugin, file);
		onFile(file);
	}

	@Override
	public void configObjectText(File file) {
	}

	@Override
	public File newFileCreate(File file) {
		return new File(getPlugin().getDataFolder(),"account.yml");
	}

	@Override
	protected void onFile(File file) {
		newFileCreate(file);
		saveFile(file);
	}

	@Override
	public void saveFile(File file) {
		file=new File(getPlugin().getDataFolder(),"account.yml");
		FileConfiguration c=YamlConfiguration.loadConfiguration(file);
		try {
			c.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
