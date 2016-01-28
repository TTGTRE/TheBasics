/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Levi Pawlak (GoldenDeveloper79)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.on
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
			
			BasicUtils.sendMessage(player, "&6You have played on the server for &7" + formatTime + "&6.");
			
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
						
						BasicUtils.sendMessage(player, "&6You have ranked up to &7" + groupName + "&6.");	
					}
				}
			}
		}else
		{
			Player player2 = Bukkit.getPlayer(args[0]);
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
			
			BasicUtils.sendMessage(player, "&6The player &7" + args[0] + " &6has played on the server for&7 " + formatTime + "&6.");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		Player player = Bukkit.getPlayer(args[0]);
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
		
		BasicUtils.sendMessage(console, "&6The player &7" + args[0] + " &6has played on the server for&7 " + formatTime + "&6.");
	}
}
