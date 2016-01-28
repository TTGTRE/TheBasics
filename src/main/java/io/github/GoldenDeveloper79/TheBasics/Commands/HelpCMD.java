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

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class HelpCMD extends CommandModule
{
	public HelpCMD() 
	{
		super(new String[] {"help", "?"}, 0, 2, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args)
	{
		ArrayList<String> help = new ArrayList<String>();

		for(Plugin plugin : Bukkit.getPluginManager().getPlugins())
		{
			for(String cmdLabel : plugin.getDescription().getCommands().keySet())
			{
				PluginCommand command = Bukkit.getPluginCommand(cmdLabel.replace("/", ""));
	
				if(command != null)
				{
					if(player.hasPermission(command.getPermission()))
					{
						help.add("- &6/" + cmdLabel + " :&7 " + command.getDescription());
					}
				}
			}
		}
		
		if(args.length < 1)
		{
			for(String m : formatHelp(help, 0))
			{
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
			}
		}else if(args.length == 1)
		{
			try
			{
				int page = Integer.parseInt(args[0]) - 1;

				if(page >= 0 && page <= getMaxPages(help.size()))
				{
					for(String m : formatHelp(help, page))
					{
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
					}
				}else
				{
					BasicUtils.sendMessage(player, "&cPlease specify a valid page number.");
				}
			}catch(NumberFormatException e)
			{
				Player player2 = Bukkit.getPlayer(args[0]);

				if(player2 != null)
				{
					if(player.hasPermission("TheBasics.Help.Send"))
					{	
						for(String m : formatHelp(help, 0))
						{
							player2.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
						}

						BasicUtils.sendMessage(player, "&6You sent help to &7" + args[0] + "&6.");

					}else
					{
						BasicUtils.sendMessage(player, "&cYou do not have enough permission to perform this command!");
					}
				}else
				{
					BasicUtils.sendMessage(player, "&cPlease specify a valid page number.");
				}
			}
		}else if(args.length == 2)
		{
			try
			{
				int page = Integer.parseInt(args[1]) - 1;

				if(page >= 0 && page <= getMaxPages(help.size()))
				{
					Player player2 = Bukkit.getPlayer(args[0]);

					if(player2 != null)
					{
						if(player.hasPermission("TheBasics.Help.Send"))
						{	
							for(String m : formatHelp(help, page))
							{
								player2.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
							}
							BasicUtils.sendMessage(player, "&6You sent help to &7" + args[0] + "&6.");
						}else
						{
							BasicUtils.sendMessage(player, "&cYou do not have enough permission to perform this command!");
						}
					}else
					{
						BasicUtils.sendMessage(player, "&cThat player is not online!");
					}
				}else
				{
					BasicUtils.sendMessage(player, "&cPlease specify a valid page number.");
				}
			}catch(NumberFormatException e)
			{
				BasicUtils.sendMessage(player, "&cPlease specify a valid page number.");
			}
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		ArrayList<String> help = new ArrayList<String>();


		for(Plugin plugin : Bukkit.getPluginManager().getPlugins())
		{
			for(String cmdLabel : plugin.getDescription().getCommands().keySet())
			{
				PluginCommand command = Bukkit.getPluginCommand(cmdLabel.replace("/", ""));
	
				if(command != null)
				{
					if(command.testPermissionSilent(console))
					{
						help.add("- &6/" + cmdLabel + " :&7 " + command.getDescription());
					}
				}
			}
		}
		
		if(args.length == 1)
		{
			try
			{
				int page = Integer.parseInt(args[0]) - 1;

				if(page >= 0 && page <= getMaxPages(help.size()))
				{
					for(String m : formatHelp(help, page))
					{
						console.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
					}
				}else
				{
					BasicUtils.sendMessage(console, "&cPlease specify a valid page number.");
				}
			}catch(NumberFormatException e)
			{
				Player player2 = Bukkit.getPlayer(args[0]);

				if(player2 != null)
				{
					if(console.hasPermission("TheBasics.Help.Send"))
					{	
						for(String m : formatHelp(help, 0))
						{
							player2.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
						}

						BasicUtils.sendMessage(console, "&6You sent help to &7" + args[0] + "&6.");

					}else
					{
						BasicUtils.sendMessage(console, "&cYou do not have enough permission to perform this command!");
					}
				}else
				{
					BasicUtils.sendMessage(console, "&cPlease specify a valid page number.");
				}
			}
		}else if(args.length == 2)
		{
			try
			{
				int page = Integer.parseInt(args[1]) - 1;

				if(page >= 0 && page <= getMaxPages(help.size()))
				{
					Player player2 = Bukkit.getPlayer(args[0]);

					if(player2 != null)
					{
						if(console.hasPermission("TheBasics.Help.Send"))
						{	
							for(String m : formatHelp(help, page))
							{
								player2.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
							}
							BasicUtils.sendMessage(console, "&6You sent help to &7" + args[0] + "&6.");
						}else
						{
							BasicUtils.sendMessage(console, "&cYou do not have enough permission to perform this command!");
						}
					}else
					{
						BasicUtils.sendMessage(console, "&cThat player is not online!");
					}
				}else
				{
					BasicUtils.sendMessage(console, "&cPlease specify a valid page number.");
				}
			}catch(NumberFormatException e)
			{
				BasicUtils.sendMessage(console, "&cPlease specify a valid page number.");
			}
		}
	}

	private List<String> formatHelp(List<String> helpList, int page)
	{
		int perPage = TheBasics.getTextConfig().getInt("Help.PerPage");
		List<String> formatedHelp = new ArrayList<String>();
		String top = TheBasics.getTextConfig().getString("Help.Format.Top").replace("%p", String.valueOf(page + 1)).replace("%m", String.valueOf(getMaxPages(helpList.size()) + 1));
		String bottom = TheBasics.getTextConfig().getString("Help.Format.Bottom");

		formatedHelp.add(top);

		//Took me roughly 5 minutes to create this algorithm.
		for(int i = (perPage * page); i < ((perPage * page) + perPage); i++)
		{
			try
			{
				formatedHelp.add(helpList.get(i));
			}catch(Exception e) 
			{
				break;
			}
		}

		formatedHelp.add(bottom);

		return formatedHelp;
	}

	private int getMaxPages(int listAmount)
	{
		int perPage = TheBasics.getTextConfig().getInt("Help.PerPage");

		double maxPages = (listAmount) / perPage;

		return (int) maxPages;
	}
}
