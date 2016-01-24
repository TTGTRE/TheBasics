package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class BanCMD extends CommandModule
{
	public BanCMD() 
	{
		super(new String[] {"ban"}, 1, Integer.MAX_VALUE, MultiPlayer.ALWAYS);
	}

	public void performCommand(Player player, String[] args)
	{
		PlayerData data = BasicUtils.getData(args[0]);
		String reason = "&cYou have been banned by " + player.getName() + "!";
		
		if(args.length > 1)
		{
			reason = BasicUtils.combineString(1, args);
		}
		
		data.set("Banned.Is", true);
		data.set("Banned.Time", -1);
		data.set("Banned.Reason", reason);
		
		Bukkit.getBanList(Type.NAME).addBan(args[0], reason, null, player.getName());
		data.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		PlayerData data = BasicUtils.getData(args[0]);
		String reason = "&cYou have been banned by " + console.getName() + "!";
		
		if(args.length > 1)
		{
			reason = BasicUtils.combineString(1, args);
		}
		
		data.set("Banned.Is", true);
		data.set("Banned.Time", -1);
		data.set("Banned.Reason", reason);
		
		Bukkit.getBanList(Type.NAME).addBan(args[0], reason, null, console.getName());
		data.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
	}
}
