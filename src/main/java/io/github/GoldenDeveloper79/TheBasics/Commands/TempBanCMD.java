/*******************************************************************************
 *  Copyright (C) 2016  Levi P. (GoldenDeveloper69)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package io.github.GoldenDeveloper79.TheBasics.Commands;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;
import io.github.GoldenDeveloper79.TheBasics.Utils.BasicUtils;

public class TempBanCMD extends CommandModule
{
	public TempBanCMD() 
	{
		super(new String[] {"tempban"}, 2, Integer.MAX_VALUE, MultiPlayer.ALWAYS);
	}

	public void performCommand(Player player, String[] args) 
	{
		PlayerData data = BasicUtils.getData(args[0]);
		
		String reason = BasicUtils.getMessage("TempBanDefault").replace("%p", player.getName());
		
		if(args.length > 2)
		{
			reason = BasicUtils.combineString(2, args);
		}
		
		Date expiry = getExpiry(args[1]);
		
		if(expiry != null)
		{
			Bukkit.getBanList(Type.NAME).addBan(args[0], reason, expiry, player.getName());
			data.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
			
			BasicUtils.notify("TheBasics.Tempban.Notify", BasicUtils.getMessage("TempBanNotify").replace("%p", player.getName()).replace("%p2", args[0]).replace("%r", reason));
			BasicUtils.sendMessage(player, BasicUtils.getMessage("TempBanSender").replace("%p", args[0]));
		}else
		{
			BasicUtils.sendMessage(player, BasicUtils.getMessage("TempBanInvalidTime"));
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		PlayerData data = BasicUtils.getData(args[0]);
		
		String reason = BasicUtils.getMessage("TempBanDefault").replace("%p", console.getName());
		
		if(args.length > 2)
		{
			reason = BasicUtils.combineString(2, args);
		}
		
		Date expiry = getExpiry(args[1]);
		
		if(expiry != null)
		{
			Bukkit.getBanList(Type.NAME).addBan(args[0], reason, expiry, console.getName());
			data.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
			
			BasicUtils.notify("TheBasics.Tempban.Notify", BasicUtils.getMessage("TempBanNotify").replace("%p", console.getName()).replace("%p2", args[0]).replace("%r", reason));
			BasicUtils.sendMessage(console, BasicUtils.getMessage("TempBanSender").replace("%p", args[0]));
		}else
		{
			BasicUtils.sendMessage(console, BasicUtils.getMessage("TempBanInvalidTime"));
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
