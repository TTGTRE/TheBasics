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
		super(new String[] {"time"}, 0, 2, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args) 
	{
		if(args.length < 1)
		{
			BasicUtils.sendMessage(player, "&6The current time is " + formatTime(player.getWorld().getTime()));
		}else if(args.length == 2 && args[0].equalsIgnoreCase("set"))
		{
			if(player.hasPermission("TheBasics.Time.Set"))
			{
				try
				{
					long time = Long.parseLong(args[1]);
					player.getWorld().setTime(time);
					BasicUtils.sendMessage(player, "&6You have changed the time to " + formatTime(time));
				}catch(NumberFormatException e)
				{
					if(args[1].equalsIgnoreCase("Night"))
					{
						player.getWorld().setTime(12300);
						BasicUtils.sendMessage(player, "&6You have changed the time to night.");
					}else if(args[1].equalsIgnoreCase("Day"))
					{
						player.getWorld().setTime(0);
						BasicUtils.sendMessage(player, "&6You have set the time to day.");
					}else
					{
						BasicUtils.sendMessage(player, "&cPlease specify a valid time!");
					}
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
	
	private String formatTime(long time)
	{
		long hours = time / 1000 + 6;
		long minutes = (time % 1000) * 60 / 1000; 
		String ampm = "AM";
		
		if(hours >= 12)
		{ 
			hours = hours - 12;
			ampm = "PM"; 
		}
		
		if(hours == 0) hours = 12;
		
		String mm = "0" + minutes; 
		mm = mm.substring(mm.length() - 2, mm.length());
		
		return hours + ":" + mm + ampm + ".";
	}
}
