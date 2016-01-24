package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class KickCMD extends CommandModule
{
	public KickCMD() 
	{
		super(new String[] {"kick"}, 1, Integer.MAX_VALUE, MultiPlayer.ALWAYS);
	}

	public void performCommand(Player player, String[] args)
	{
		Player player2 = Bukkit.getPlayer(args[0]);

		String reason = "&cYou have been kicked by " + player.getName() + "!";
		
		if(args.length > 1)
		{
			reason = BasicUtils.combineString(1, args);
		}
		
		player2.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
		BasicUtils.notify("TheBasics.Kick.Notify", "&6The player " + player.getName() + " has kicked the player " + args[0] + " for " + reason + "!");
		BasicUtils.sendMessage(player, "&6You have kicked " + args[0] + "!");
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		Player player2 = Bukkit.getPlayer(args[0]);
	
		String reason = "&cYou have been kicked by " + console.getName() + "!";
		
		if(args.length > 1)
		{
			reason = BasicUtils.combineString(1, args);
		}

		player2.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
		
		BasicUtils.notify("TheBasics.Kick.Notify", "&6The player " + console.getName() + " has kicked the player " + args[0] + " for " + reason + "!");
		BasicUtils.sendMessage(console, "&6You have kicked " + args[0] + "!");
	}
}