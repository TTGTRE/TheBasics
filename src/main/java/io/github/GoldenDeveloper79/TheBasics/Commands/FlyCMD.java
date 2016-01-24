package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class FlyCMD extends CommandModule
{
	public FlyCMD()
	{
		super(new String[] {"fly"}, 0, 1, MultiPlayer.SOMETIMES);
	}

	public void performCommand(Player player, String[] args) 
	{
		if(args.length < 1)
		{
			if(player.getAllowFlight())
			{
				player.setAllowFlight(false);
				player.setFlying(false);
				
				BasicUtils.sendMessage(player, "&6You have disabled flying for yourself.");
			}else
			{
				player.setAllowFlight(true);
				player.setFlying(true);
				
				BasicUtils.sendMessage(player, "&6You have enabled flying for yourself.");
			}
		}else 
		{
			if(player.hasPermission("TheBasics.Fly.Others"))
			{
				Player player2 = Bukkit.getPlayer(args[0]);
				
				if(player2.getAllowFlight())
				{
					player2.setAllowFlight(false);
					player2.setFlying(false);
					
					BasicUtils.sendMessage(player, "&6You have disabled flying for " + args[0] + ".");
					BasicUtils.sendMessage(player2, "&6Flying has been disabled for you by " + player.getName() + ".");
				}else
				{
					player2.setAllowFlight(true);
					player2.setFlying(true);
					
					BasicUtils.sendMessage(player, "&6You have enabled flying for " + args[0] + ".");
					BasicUtils.sendMessage(player2, "&6Flying has been enabled for you by " + player.getName() + ".");
				}
			}else
			{
				BasicUtils.sendMessage(player, "&cYou do not have enough permission to perform this command!");
			}
		}
		
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		if(console.hasPermission("TheBasics.Fly.Others"))
		{
			Player player2 = Bukkit.getPlayer(args[0]);
			
			if(player2.getAllowFlight())
			{
				player2.setAllowFlight(false);
				player2.setFlying(false);
				
				BasicUtils.sendMessage(console, "You have disabled flying for " + args[0] + ".");
				BasicUtils.sendMessage(player2, "&6Flying has been disabled for you by " + console.getName() + ".");
			}else
			{
				player2.setAllowFlight(true);
				player2.setFlying(true);
				
				BasicUtils.sendMessage(console, "You have enabled flying for " + args[0] + ".");
				BasicUtils.sendMessage(player2, "&6Flying has been enabled for you by " + console.getName() + ".");
			}
		}else
		{
			BasicUtils.sendMessage(console, "You do not have enough permission to perform this command!");
		}
	}
}
