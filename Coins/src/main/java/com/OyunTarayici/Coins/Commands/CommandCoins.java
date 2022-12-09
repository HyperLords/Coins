package com.OyunTarayici.Coins.Commands;

import java.util.List;
import org.BukkitApi.main.BukkitUtiles.CommandUtils.CommandCreator;
import org.BukkitApi.main.BukkitUtiles.MesageUtils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.OyunTarayici.Coins.Managers.CoinsManager;
import com.OyunTarayici.Coins.Profiles.CoinsProfile;
import lombok.Getter;

public class CommandCoins extends CommandCreator {

	@Getter
	private CoinsProfile coinsProfile=null;
	
	@Getter  
	private OfflinePlayer offlinePlayer=null;
	
	public CommandCoins(JavaPlugin plugin, String commandName) {
		super(plugin, "coins");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void executeCommand(Player player, String arg1, String[] arg) {
		command=(Command)getPlugin().getCommand(arg1);
		if (player.hasPermission("coins.player.commands")) {
			if (command.getName().equals("coins")) {
			
			if (arg.length==0) {
				coinsProfile=CoinsProfile.getEcoCoins().get(player.getUniqueId());
				PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&aYour accounts: &e"+CoinsManager.getCoins(coinsProfile)));
			return;}
			
			if (arg.length>=0) {
				if (arg[0].equalsIgnoreCase("help")) {
					PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&a/coins <username> &cLook player coins"));
					PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&a/coins send <username> &cPlayer send coins"));
					if (player.hasPermission("coins.player.admin")) {
						PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&a/coins add <username> <amount> &cPlayer add coins"));
						PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&a/coins set <username> <amount> &cPlayer set coins"));
						PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&a/coins delete <username> <amount> &cPlayer delete coins"));
						PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&a/coins reset <username> &cPlayer reset coins"));
						PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&a/coins delete account <username> &cPlayer delete account"));
					}
			return;}}
			
			if (arg.length<=1) {
					offlinePlayer=Bukkit.getOfflinePlayer(arg[0]);
					coinsProfile=CoinsProfile.getEcoCoins().get(offlinePlayer.getUniqueId());
					
					if (!offlinePlayer.isOnline()) {
						PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cPlayer not online!"));
					return;}else if (offlinePlayer==null) {
						try {
							PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cPlayer not online!"));
						} catch (NullPointerException e) {
							return;
						}
					return;}else {
						PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&e"+offlinePlayer.getName()+" &aAccounts: &c"+CoinsManager.getCoins(coinsProfile)));
					return;}}
			
			if (arg.length>=0) {
				if (arg[0].equals("send")) {
					offlinePlayer=Bukkit.getOfflinePlayer(arg[1]);
					coinsProfile=CoinsProfile.getEcoCoins().get(offlinePlayer.getUniqueId());
					CoinsProfile playerProfile=CoinsProfile.getEcoCoins().get(player.getUniqueId());
					int coins=Integer.parseInt(arg[2]);
					
					if (!offlinePlayer.isOnline()) {
						PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cPlayer not online!"));
					return;}else if (offlinePlayer==null) {
						try {
							PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cPlayer not online!"));
						} catch (NullPointerException e) {
							return;
						}
					return;}else if (offlinePlayer==player) {
						PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cSorry your account not have send coin"));
					return;}else {
						
						if (coins<0||playerProfile.getCoins()<0||playerProfile.getCoins()<=0) {
							PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cInvalid number"));
						return;}else {
							
							CoinsManager.addCoins(coinsProfile, coins);
							CoinsManager.deleteCoins(playerProfile, coins);
							
							CoinsManager.saveAccounts(player);
							CoinsManager.createAccounts(player);
							
							CoinsManager.saveAccounts(offlinePlayer.getPlayer());
							CoinsManager.createAccounts(offlinePlayer.getPlayer());
							
							PlayerUtils.getPlayerMessage(offlinePlayer.getPlayer(), PlayerUtils.getColoredMessage("&aYour account sended &e"+player.getName()+" &acoin &e"+coins+" &aamount"));
							PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&aSuccessfull sended coin &e"+offlinePlayer.getName()+" &aamount &e"+coins));
						return;}}}}

			if (player.hasPermission("coins.player.admin")) {
				
				if (arg.length>=2) {
					if (arg[0].equals("add")) {
						offlinePlayer=Bukkit.getOfflinePlayer(arg[1]);
						coinsProfile=CoinsProfile.getEcoCoins().get(offlinePlayer.getUniqueId());
						int coins=Integer.parseInt(arg[2]);
						
						if (!offlinePlayer.isOnline()) {
							PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cPlayer not online!"));
						return;}else if (offlinePlayer==null) {
							try {
								PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cPlayer not online!"));
							} catch (IllegalArgumentException e) {
								return;
							}
						return;}else if (coins==0||coins<0) {
								PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cInvalid number"));
						return;}else {
									CoinsManager.addCoins(coinsProfile, coins);
									CoinsManager.saveAccounts(offlinePlayer.getPlayer());
									CoinsManager.createAccounts(offlinePlayer.getPlayer());
									PlayerUtils.getPlayerMessage(offlinePlayer.getPlayer(), PlayerUtils.getColoredMessage("&aYour account added &e"+coins+" &acoins"));
									PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&aSuccessfull added coins with &e"+offlinePlayer.getName()+" &aaccount &e"+coins+" &aamount"));
						return;}}}

				if (arg.length>=2) {
					if (arg[0].equals("set")) {
						offlinePlayer=Bukkit.getOfflinePlayer(arg[1]);
						coinsProfile=CoinsProfile.getEcoCoins().get(offlinePlayer.getUniqueId());
						int coins=Integer.parseInt(arg[2]);
						
						if (offlinePlayer==null) {
							try {
								PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cPlayer not online!"));
							} catch (NullPointerException e) {
								return;
							}
						return;}else {
							if (!offlinePlayer.isOnline()) {
								PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cPlayer not online!"));
							return;}else {
								if (coins<0) {
									PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cInvalid number"));
								return;}else {
									CoinsManager.setCoins(coinsProfile, coins);
									CoinsManager.saveAccounts(offlinePlayer.getPlayer());
									CoinsManager.createAccounts(offlinePlayer.getPlayer());
									PlayerUtils.getPlayerMessage(offlinePlayer.getPlayer(), PlayerUtils.getColoredMessage("&aYour account arranged &e"+coins+" &acoins"));
									PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&aSuccessfull arranged coins with &e"+offlinePlayer.getName()+" &aaccount &e"+coins+" &aamount"));
					return;}}}}}

				if (arg.length>=2) {
					if (arg[0].equals("delete")) {
						offlinePlayer=Bukkit.getOfflinePlayer(arg[1]);
						coinsProfile=CoinsProfile.getEcoCoins().get(offlinePlayer.getUniqueId());
						int coins=Integer.parseInt(arg[2]);
						
						if (offlinePlayer==null) {
							try {
								PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cPlayer not online!"));
							} catch (NullPointerException e) {
								return;
							}
						return;}else {
							if (!offlinePlayer.isOnline()) {
								PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cPlayer not online!"));
								return;}else {
									if (coinsProfile.getCoins()<=0&&coins<0||coinsProfile.getCoins()<coins&&coinsProfile.getCoins()<=coins||coinsProfile.getCoins()<=0) {
										PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cInvalid argument"));
									return;}else {
										CoinsManager.deleteCoins(coinsProfile, coins);
										CoinsManager.saveAccounts(offlinePlayer.getPlayer());
										CoinsManager.createAccounts(offlinePlayer.getPlayer());
										PlayerUtils.getPlayerMessage(offlinePlayer.getPlayer(), PlayerUtils.getColoredMessage("&aYour account delete &e"+coins+" &acoins"));
										PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&aSuccessfull delete coins with &e"+offlinePlayer.getName()+" &aaccount &e"+coins+" &aamount"));
										return;}}}}}
				
				if (arg.length>=2) {
					if (arg[0].equals("reset")) {
						offlinePlayer=Bukkit.getOfflinePlayer(arg[1]);
						coinsProfile=CoinsProfile.getEcoCoins().get(offlinePlayer.getUniqueId());
						
						if (!offlinePlayer.isOnline()) {
							PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cPlayer not online!"));
						return;}else if (offlinePlayer==null) {
							try {
								PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cPlayer not online!"));
							} catch (NullPointerException e) {
								return;
							}
						return;}else {
							CoinsManager.resetCoins(coinsProfile);
							CoinsManager.saveAccounts(offlinePlayer.getPlayer());
							CoinsManager.createAccounts(offlinePlayer.getPlayer());
							PlayerUtils.getPlayerMessage(offlinePlayer.getPlayer(), PlayerUtils.getColoredMessage("&aYour account has been reseted"));
							PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&aSuccessfull &e"+offlinePlayer.getName()+" &aAccount reseting"));
						return;}
					}
				}
				
			return;}else {
				PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cYou don't have the permission!"));
			}
			
			return;}
		}else {
			PlayerUtils.getPlayerMessage(player, PlayerUtils.getColoredMessage("&cYou don't have the permission!"));
		}
	}

	@Override
	public List<String> executeTabCompleter(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		return null;
	}

}
