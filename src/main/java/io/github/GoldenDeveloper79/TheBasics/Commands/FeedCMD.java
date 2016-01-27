package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class FeedCMD extends CommandModule
{
	public FeedCMD() 
	{
		super(new String[] {"feed"}, 0, 1, MultiPlayer.SOMETIMES);
	}

	public void performCommand(Player player, String[] args) 
	{
		if(args.length < 1)
		{
			player.setFoodLevel(20);
			
			BasicUtils.sendMessage(player, "&6You have been fed.");
		}else
		{
			Player player2 = Bukkit.getPlayer(args[0]);
			player2.setFoodLevel(20);
			
			BasicUtils.sendMessage(player, "&6You have fed &7" + args[0] + "&6.");
			BasicUtils.sendMessage(player2, "&6You have been fed by &7" + player.getName() + "&6.");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		Player player2 = Bukkit.getPlayer(args[0]);
		
		player2.setFoodLevel(20);
		
		BasicUtils.sendMessage(console, "You have fed &7" + args[0] + "&6.");
		BasicUtils.sendMessage(player2, "&6You have been fed by &7console.&6");
	}
}