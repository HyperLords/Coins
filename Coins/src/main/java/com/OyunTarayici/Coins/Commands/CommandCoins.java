package com.OyunTarayici.Coins.Commands;

import org.BukkitApi.main.BukkitUtiles.CommandUtils.CommandCreator;
import org.BukkitApi.main.BukkitUtiles.MesageUtils.GetColoredMessage;
import org.BukkitApi.main.BukkitUtiles.MesageUtils.GetPlayerMessage;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.OyunTarayici.Coins.Managers.CoinManager;
import com.OyunTarayici.Coins.Profiles.PlayerProfiles;

public class CommandCoins extends CommandCreator{

	public CommandCoins(JavaPlugin plugin, String commandName) {
		super(plugin, "coin");
	}

	@Override
	@SuppressWarnings({"deprecation", "null"})
	public boolean executeCommand(Player player, String arg1, String[] arg) {
		
		Command command=(Command)getPlugin().getCommand(arg1);
		if (player.hasPermission("coin.player.command")) {
			if (command.getName().equals("coin")) {
				
				CoinManager coinManager=new CoinManager();
				
				if (arg.length==0) {
					PlayerProfiles playerProfiles=PlayerProfiles.getProfiles().get(player.getUniqueId());
					GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&aYour account&0&l&r &e"+coinManager.getPlayerCoin(playerProfiles)));
				return true;}

				if (arg[0].equalsIgnoreCase("help")) {
					GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&c&l&m-------------------->&r"));
					GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&a/coins show <username> &eCoin show to player"));
					GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&a/coins send <username> <amount> &eCoin send to player"));
					if (player.hasPermission("coin.admin.command")) {
						GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&a/coins add <username> <amount> &eCoin add to player"));
						GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&a/coins set <username> <amount> &eCoin set to player"));
						GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&a/coins remove <username> <amount> &eCoin remove to player"));
					}
					GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&c&l&m-------------------->&r"));
				return true;}
				
				if (arg[0].equalsIgnoreCase("send")) {
					OfflinePlayer offlinePlayer=Bukkit.getOfflinePlayer(arg[1]);
					if (offlinePlayer!=null||!(offlinePlayer.equals(player.getName()))) {
						PlayerProfiles playerProfiles=PlayerProfiles.getProfiles().get(player.getUniqueId());
						PlayerProfiles senderPlayer=PlayerProfiles.getProfiles().get(offlinePlayer.getUniqueId());
						int argumnetCoin=Integer.parseInt(arg[2]);
							coinManager.deleteCoin(playerProfiles, argumnetCoin);
							coinManager.addCoin(senderPlayer, argumnetCoin);
							GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&aSuccessfull send &e"+offlinePlayer.getName()+" &acoin &e"+argumnetCoin+" &aamount"));
							GetPlayerMessage.getPlayerMessage(offlinePlayer.getPlayer(), GetColoredMessage.getColoredMessage("&aSuccesfull your account &e"+player.getName()+" &asender coin"));
					return true;}else if (!offlinePlayer.equals(player.getName())) {
						GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&c&lError cannot be send your name"));
					}else {
						GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&c&lPlayer not online!"));
					}
				}
				
				if (arg[0].equalsIgnoreCase("show")) {
					OfflinePlayer offlinePlayer=Bukkit.getOfflinePlayer(arg[1]);
					
					if (offlinePlayer!=null) {
						PlayerProfiles playerProfiles=PlayerProfiles.getProfiles().get(offlinePlayer.getUniqueId());
						GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&e"+offlinePlayer.getName()+" &aCoin: &e"+coinManager.getPlayerCoin(playerProfiles)));
					return true;}else {
						GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&c&lPlayer not online!"));
					}
				}

				if (player.hasPermission("coin.admin.command")) {	
				int argumnetCoin=Integer.parseInt(arg[2]);
				
				if (arg[0].equalsIgnoreCase("add")) {
					OfflinePlayer offlinePlayer=Bukkit.getOfflinePlayer(arg[1]);
					
					if (offlinePlayer!=null) {
						PlayerProfiles playerProfiles=PlayerProfiles.getProfiles().get(offlinePlayer.getUniqueId());
						coinManager.addCoin(playerProfiles, argumnetCoin);
						GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&aSuccessfull added &e"+offlinePlayer.getName()+" &acoin &e"+argumnetCoin+" &aamount"));
						GetPlayerMessage.getPlayerMessage(offlinePlayer.getPlayer(), GetColoredMessage.getColoredMessage("&aSuccesfull your account &e"+player.getName()+" &aadded coin"));
					}else {
						GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&c&lPlayer not online!"));
					}
				}

				if (arg[0].equalsIgnoreCase("set")) {
					OfflinePlayer offlinePlayer=Bukkit.getOfflinePlayer(arg[1]);
					
					if (offlinePlayer!=null) {
						PlayerProfiles playerProfiles=PlayerProfiles.getProfiles().get(offlinePlayer.getUniqueId());
						coinManager.setCoin(playerProfiles, argumnetCoin);
						GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&aSuccessfull arranged &e"+offlinePlayer.getName()+" &acoin &e"+argumnetCoin+" &aamount"));
						GetPlayerMessage.getPlayerMessage(offlinePlayer.getPlayer(), GetColoredMessage.getColoredMessage("&aSuccesfull your account &e"+player.getName()+" &aarranged coin"));
					}else {
						GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&c&lPlayer not online!"));
				}
				}

				if (arg[0].equalsIgnoreCase("remove")) {
					OfflinePlayer offlinePlayer=Bukkit.getOfflinePlayer(arg[1]);
					
					if (offlinePlayer!=null) {
						PlayerProfiles playerProfiles=PlayerProfiles.getProfiles().get(offlinePlayer.getUniqueId());
						coinManager.deleteCoin(playerProfiles, argumnetCoin);
						GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&aSuccessfull removed &e"+offlinePlayer.getName()+" &acoin &e"+argumnetCoin+" &aamount"));
						GetPlayerMessage.getPlayerMessage(offlinePlayer.getPlayer(), GetColoredMessage.getColoredMessage("&aSuccesfull your account &e"+player.getName()+" &aremoved coin"));
					}else {
						GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&c&lPlayer not online!"));
					}
				}
				
				}else {
					GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&c&lYou don't have the admin"));
				}
				
			return true;}
		}else {
			GetPlayerMessage.getPlayerMessage(player, GetColoredMessage.getColoredMessage("&c&lYou don't have the permission"));
		}
		return true;
	}

}
