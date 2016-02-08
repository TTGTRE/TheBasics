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

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class RulesCMD extends CommandModule
{
	public RulesCMD()
	{
		super(new String[] {"rules"}, 0, 2, MultiPlayer.OTHER);
	}

	public void performCommand(final Player player, final String[] args) 
	{
		if(args.length < 1)
		{
			for(String m : formatRules(0))
			{
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
			}
		}else if(args.length == 1)
		{
			try
			{
				int page = Integer.parseInt(args[0]) - 1;
			
				if(page >= 0 && page <= getMaxPages())
				{
					for(String m : formatRules(page))
					{
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
					}
				}else
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("RulesPage"));
				}
			}catch(NumberFormatException e)
			{
				Player player2 = Bukkit.getPlayer(args[0]);
				
				if(player2 != null)
				{
					if(player.hasPermission("TheBasics.Rules.Send"))
					{	
						for(String m : formatRules(0))
						{
							player2.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
						}
						
						BasicUtils.sendMessage(player, BasicUtils.getMessage("RulesSender").replace("%p", args[0]));
					}else
					{
						BasicUtils.sendMessage(player, BasicUtils.getMessage("NoPermission"));
					}
				}else
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("RulesPage"));
				}
			}
		}else if(args.length == 2)
		{
			try
			{
				int page = Integer.parseInt(args[1]) - 1;
			
				if(page >= 0 && page <= getMaxPages())
				{
					Player player2 = Bukkit.getPlayer(args[0]);
					
					if(player2 != null)
					{
						if(player.hasPermission("TheBasics.Rules.Send"))
						{	
							for(String m : formatRules(page))
							{
								player2.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
							}
							
							BasicUtils.sendMessage(player, BasicUtils.getMessage("RulesSender").replace("%p", args[0]));
						}else
						{
							BasicUtils.sendMessage(player, BasicUtils.getMessage("NoPermission"));
						}
					}else
					{
						BasicUtils.sendMessage(player, BasicUtils.getMessage("PlayerOffline"));
					}
				}else
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("RulesPage"));
				}
			}catch(NumberFormatException e)
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("RulesPage"));
			}
		}
	}

	public void performCommand(final ConsoleCommandSender console, final String[] args) 
	{
		 if(args.length == 1)
		 {
			try
			{
				int page = Integer.parseInt(args[0]) - 1;
			
				if(page > 0 && page <= getMaxPages())
				{
					for(String m : formatRules(page))
					{
						console.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
					}
				}else
				{
					BasicUtils.sendMessage(console, BasicUtils.getMessage("RulesPage"));
				}
			}catch(NumberFormatException e)
			{
				Player player2 = Bukkit.getPlayer(args[0]);
				
				if(player2 != null)
				{
					if(console.hasPermission("TheBasics.Rules.Send"))
					{	
						for(String m : formatRules(0))
						{
							player2.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
						}
						
						BasicUtils.sendMessage(console, BasicUtils.getMessage("RulesSender").replace("%p", args[0]));
					}else
					{
						BasicUtils.sendMessage(console, BasicUtils.getMessage("NoPermission"));
					}
				}else
				{
					BasicUtils.sendMessage(console, BasicUtils.getMessage("RulesPage"));
				}
			}
		}else if(args.length == 2)
		{
			try
			{
				int page = Integer.parseInt(args[1]) - 1;
			
				if(page > 0 && page <= getMaxPages())
				{
					Player player = Bukkit.getPlayer(args[0]);
					
					if(player != null)
					{
						if(console.hasPermission("TheBasics.Rules.Send"))
						{	
							for(String m : formatRules(page))
							{
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
							}
							
							BasicUtils.sendMessage(console, BasicUtils.getMessage("RulesSender").replace("%p", args[0]));
						}else
						{
							BasicUtils.sendMessage(console, BasicUtils.getMessage("NoPermission"));
						}
					}else
					{
						BasicUtils.sendMessage(player, BasicUtils.getMessage("PlayerOffline"));
					}
				}else
				{
					BasicUtils.sendMessage(console, BasicUtils.getMessage("RulesPage"));
				}
			}catch(NumberFormatException e)
			{
				BasicUtils.sendMessage(console, BasicUtils.getMessage("RulesPage"));
			}
		}
	}
	
	public void performCommand(final CommandSender sender, final String[] args){}
	
	private List<String> formatRules(int page)
	{
		int perPage = TheBasics.getTextConfig().getInt("Rules.PerPage");
		List<String> rulesList = TheBasics.getTextConfig().getStringList("Rules.List");
		List<String> formatedRules = new ArrayList<String>();
		String top = TheBasics.getTextConfig().getString("Rules.Format.Top").replace("%p", String.valueOf(page + 1)).replace("%m", String.valueOf(getMaxPages() + 1));;
		String bottom = TheBasics.getTextConfig().getString("Rules.Format.Bottom");
		
		formatedRules.add(top);
		
		//Took me roughly 5 minutes to create this algorithm.
		for(int i = (perPage * page); i < ((perPage * page) + perPage); i++)
		{
			try
			{
				formatedRules.add(rulesList.get(i));
			}catch(Exception e) 
			{
				break;
			}
		}
		
		formatedRules.add(bottom);
		
		return formatedRules;
	}
	
	private int getMaxPages()
	{
		int perPage = TheBasics.getTextConfig().getInt("Rules.PerPage");
		List<String> rulesList = TheBasics.getTextConfig().getStringList("Rules.List");
		
		double maxPages = rulesList.size() / perPage;
		
		return (int) maxPages;
	}
}
