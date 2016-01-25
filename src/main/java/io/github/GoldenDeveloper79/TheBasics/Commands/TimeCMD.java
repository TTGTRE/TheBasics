package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class TimeCMD extends CommandModule
{
	public TimeCMD() 
	{
		super(new String[] {"time"}, 0, 2, MultiPlayer.NEVER);
	}

	public void performCommand(Player player, String[] args) 
	{
		if(args.length < 1)
		{
			BasicUtils.sendMessage(player, "&6The current time is " + player.getWorld().getTime() + ".");
		}else if(args.length == 2 && args[0].equalsIgnoreCase("set"))
		{
			if(player.hasPermission("TheBasics.Time.Set"))
			{
				switch(args[0].toUpperCase())
				{
					case "NIGHT":
						player.getWorld().setTime(12300);
						BasicUtils.sendMessage(player, "&6You have changed the time to night.");
					case "DAY":
						player.getWorld().setTime(0);
						BasicUtils.sendMessage(player, "&6You have set the time to day.");
					default:
						BasicUtils.sendMessage(player, "&cPlease specify a valid time!");
				}
			}else
			{
				BasicUtils.sendMessage(player, "&cYou do not have enough permission to perform this command!");
			}
		}else
		{
			BasicUtils.sendMessage(player, "&cUsage: " + getUsage());
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		BasicUtils.sendMessage(console, "You must be a player to perform this command!");
	}
}
