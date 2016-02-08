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
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class MuteCMD extends CommandModule
{
	public MuteCMD() 
	{
		super(new String[] {"mute"}, 2, Integer.MAX_VALUE, MultiPlayer.ALWAYS);
	}

	public void performCommand(final Player player, final String[] args) 
	{
		PlayerData data = BasicUtils.getData(Bukkit.getPlayer(args[0]));
		
		if(!data.isMuted())
		{
			String reason = BasicUtils.getMessage("MuteDefault").replace("%p", player.getName());
			
			if(args.length > 2)
			{
				reason = BasicUtils.combineString(3, args);
			}
			
			int seconds = getTimeInSeconds(args[1]);
			
			if(seconds != 0)
			{
				Date time = DateUtils.addSeconds(new Date(), seconds);
				data.setMuted(time, reason);
				
				BasicUtils.notify("TheBasics.Mute.Notify", BasicUtils.getMessage("MuteNofiy").replace("%p", player.getName()).replace("%p2", args[0]).replace("%r", reason));
				BasicUtils.sendMessage(player, BasicUtils.getMessage("MuteSender").replace("%p", args[0]));
				BasicUtils.sendMessage(data.getPlayer(), BasicUtils.getMessage("MuteReceiver").replace("%p", player.getName()).replace("%r", reason));
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("MuteInvalidTime"));
			}
		}else
		{
			BasicUtils.sendMessage(player, BasicUtils.getMessage("MuteAlready"));
		}
	}

	public void performCommand(final ConsoleCommandSender console, final String[] args)
	{
		PlayerData data = BasicUtils.getData(Bukkit.getPlayer(args[0]));
		
		if(!data.isMuted())
		{
			String reason = BasicUtils.getMessage("MuteDefault").replace("%p", console.getName());
			
			if(args.length > 2)
			{
				reason = BasicUtils.combineString(2, args);
			}
			
			int seconds = getTimeInSeconds(args[1]);
	
			if(seconds != 0)
			{
				Date time = DateUtils.addSeconds(new Date(), seconds);
				data.setMuted(time, reason);
				
				BasicUtils.notify("TheBasics.Mute.Notify", BasicUtils.getMessage("MuteNotify").replace("%p", console.getName()).replace("%s", args[0]).replace("%r", reason));
				BasicUtils.sendMessage(console, BasicUtils.getMessage("MuteSender").replace("%p", args[0]));
				BasicUtils.sendMessage(data.getPlayer(), BasicUtils.getMessage("MuteReceiver").replace("%p", console.getName()).replace("%r", reason));
			}else
			{
				BasicUtils.sendMessage(console, BasicUtils.getMessage("MuteInvalidTime"));
			}
		}else
		{
			BasicUtils.sendMessage(console, BasicUtils.getMessage("MuteAlready"));
		}
	}
	
	public void performCommand(final CommandSender sender, final String[] args){}
	
	public int getTimeInSeconds(String date)
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
				return 0;
			}
			
			return (int) seconds;
		}catch(NumberFormatException e)
		{
			return 0;
		}
	}
}

