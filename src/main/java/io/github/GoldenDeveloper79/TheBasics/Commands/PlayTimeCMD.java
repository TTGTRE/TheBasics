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

import java.math.BigDecimal;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class PlayTimeCMD extends CommandModule
{
	public PlayTimeCMD() 
	{
		super(new String[] {"playtime", "ptime"}, 0, 1, MultiPlayer.SOMETIMES);
	}

	public void performCommand(Player player, String[] args) 
	{
		if(args.length < 1)
		{
			String formatTime;
			double time = BasicUtils.getData(player).getDouble("PlayTime");
			
			if(time < 60)
			{
				formatTime = String.valueOf(time) + "m";
			}else
			{
				BigDecimal bd = new BigDecimal(Double.toString(time / 60));
				bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
				
				formatTime = String.valueOf(bd.doubleValue()) + "h";
			}
			
			BasicUtils.sendMessage(player, BasicUtils.getMessage("PlayTimeGet").replace("%a", formatTime));
			
			if(TheBasics.getGroupConfig().getString("Ranking.Method").equalsIgnoreCase("TIME"))
			{
				PlayerData data = BasicUtils.getData(player);
				String groupName = null;
				
				for(String group : TheBasics.getGroupConfig().getConfig().getConfigurationSection("Ranking.Ranks").getKeys(false))
				{
					if(TheBasics.getPermissions().groupExist(group))
					{
						if(TheBasics.getGroupConfig().getDouble("Ranking.Ranks." + group) <= data.getDouble("PlayTime"))
						{
							groupName = group;
						}
					}
				}
				
				if(groupName != null)
				{	
					if(!TheBasics.getPermissions().getPlayerGroup(player).getGroupName().equalsIgnoreCase(groupName))
					{
						GroupModule group = TheBasics.getPermissions().getGroup(groupName);
						TheBasics.getPermissions().addPlayerToGroup(player.getPlayer(), group);
						
						BasicUtils.sendMessage(player, BasicUtils.getMessage("PlayTimeRankup").replace("%a", groupName));	
					}
				}
			}
		}else
		{
			Player player2 = Bukkit.getPlayer(args[0]);
			
			if(player2 != null)
			{
				String formatTime;
				double time = BasicUtils.getData(player2).getDouble("PlayTime");
				
				if(time < 60)
				{
					formatTime = String.valueOf(time) + "m";
				}else
				{
					BigDecimal bd = new BigDecimal(Double.toString(time));
					bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
					
					formatTime = String.valueOf(bd.doubleValue()) + "h";
				}
				
				BasicUtils.sendMessage(player, BasicUtils.getMessage("PlayTimeGetOther").replace("%p", args[0]).replace("%a", formatTime));
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("OfflinePlayer"));
			}
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		if(args.length == 1)
		{
			Player player = Bukkit.getPlayer(args[0]);
			
			if(player != null)
			{
				String formatTime;
				double time = BasicUtils.getData(player).getDouble("PlayTime");
				
				if(time < 60)
				{
					formatTime = String.valueOf(time) + "m";
				}else
				{
					BigDecimal bd = new BigDecimal(Double.toString(time));
					bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
					
					formatTime = String.valueOf(bd.doubleValue()) + "h";
				}
				
				BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayTimeGetOther").replace("%p", args[0]).replace("%a", formatTime));
			}else
			{
				BasicUtils.sendMessage(console, BasicUtils.getMessage("OfflinePlayer"));
			}
		}else
		{
			BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
		}
	}
}
