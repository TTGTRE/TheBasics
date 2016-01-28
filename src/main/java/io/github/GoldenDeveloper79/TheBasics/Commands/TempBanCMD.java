package io.github.GoldenDeveloper79.TheBasics.Commands;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class TempBanCMD extends CommandModule
{
	public TempBanCMD() 
	{
		super(new String[] {"tempban"}, 2, Integer.MAX_VALUE, MultiPlayer.ALWAYS);
	}

	public void performCommand(Player player, String[] args) 
	{
		PlayerData data = BasicUtils.getData(args[0]);
		
		String reason = "&cYou have been tempbanned by &7" + player.getName() + "&6!";
		
		if(args.length > 2)
		{
			reason = BasicUtils.combineString(2, args);
		}
		
		Date expiry = getExpiry(args[1]);
		
		if(expiry != null)
		{
			Bukkit.getBanList(Type.NAME).addBan(args[0], reason, expiry, player.getName());
			data.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
			
			BasicUtils.notify("TheBasics.Tempban.Notify", "&6The player &7" + player.getName() + " &6has tempbanned the player &7" + args[0] + " &6for " + reason + "&6!");
			BasicUtils.sendMessage(player, "&6You have tempbanned  &7" + args[0] + "!");
		}else
		{
			BasicUtils.sendMessage(player, "&cPlease specify a valid time in years, weeks, days, hours, minutes, or seconds.");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		PlayerData data = BasicUtils.getData(args[0]);
		
		String reason = "&cYou have been tempbanned by &7console&6!";
		
		if(args.length > 2)
		{
			reason = BasicUtils.combineString(2, args);
		}
		
		Date expiry = getExpiry(args[1]);
		
		if(expiry != null)
		{
			Bukkit.getBanList(Type.NAME).addBan(args[0], reason, expiry, "console");
			data.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
			
			BasicUtils.notify("TheBasics.Tempban.Notify", "&6The player &7console &6has tempbanned the player &7" + args[0] + " &6for " + reason + "&6!");
			BasicUtils.sendMessage(console, "&6You have tempbanned  &7" + args[0] + "!");
		}else
		{
			BasicUtils.sendMessage(console, "&cPlease specify a valid time in years, weeks, days, hours, minutes, or seconds.");
		}
	}
	
	public Date getExpiry(String date)
	{
		try
		{
			double seconds = Double.parseDouble(date.replaceAll("[^0-9]", ""));
			String timeUnit = date.replaceAll("[^A-Za-z]", "");
			
			if(timeUnit.equalsIgnoreCase("y") || timeUnit.equalsIgnoreCase("year") || timeUnit.equalsIgnoreCase("years"))
			{
				seconds *= 3.154e+7;
			}else if(timeUnit.equalsIgnoreCase("w") || timeUnit.equalsIgnoreCase("week") || timeUnit.equalsIgnoreCase("weeks"))
			{
				seconds *= 604876;
			}else if(timeUnit.equalsIgnoreCase("d") || timeUnit.equalsIgnoreCase("day") || timeUnit.equalsIgnoreCase("days"))
			{
				seconds *= 86410;
			}else if(timeUnit.equalsIgnoreCase("h") || timeUnit.equalsIgnoreCase("hour") || timeUnit.equalsIgnoreCase("hours"))
			{
				seconds *= 3600;
			}else if(timeUnit.equalsIgnoreCase("m") || timeUnit.equalsIgnoreCase("minute") || timeUnit.equalsIgnoreCase("minutes"))
			{
				seconds *= 60;
			}else if(timeUnit.equalsIgnoreCase("s") || timeUnit.equalsIgnoreCase("second") || timeUnit.equalsIgnoreCase("seconds"))
			{
				seconds *= 1;
			}else
			{
				return null;
			}
			
			return DateUtils.addSeconds(new Date(), (int) seconds);
		}catch(NumberFormatException e)
		{
			return null;
		}
	}
}
