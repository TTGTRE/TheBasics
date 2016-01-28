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

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class BalanceCMD extends CommandModule
{
	public BalanceCMD()
	{
		super(new String[] {"balance", "bal"}, 0, 3, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args)
	{
		if(args.length < 1)
		{
			double balance = TheBasics.getEconomy().getBalance(player);
			
			BasicUtils.sendMessage(player, "&6Your balance is &7$" + balance + "&6.");
		}else if(args.length == 1)
		{
			if(player.hasPermission("TheBasics.Balance.Others"))
			{
				Player player2 = Bukkit.getPlayer(args[0]);
				
				if(player2 != null)
				{
					double balance = TheBasics.getEconomy().getBalance(player2);
					
					BasicUtils.sendMessage(player, "&6The balance of " + args[0] + " is &7$" + balance + "&6.");
				}else
				{
					BasicUtils.sendMessage(player, "&cThat player is not online!");
				}
			}else
			{
				BasicUtils.sendMessage(player, "&cYou do not have enough permission to perform this command!");
			}
		}else if(args.length == 3)
		{
			if(player.hasPermission("TheBasics.Balance." + args[0].toLowerCase()))
			{
				Player player2 = Bukkit.getPlayer(args[1]);
				
				if(player2 != null)
				{
					try
					{
						double amount = Double.parseDouble(args[2]);
						
						if(amount >= 0)
						{
							if(args[0].equalsIgnoreCase("set"))
							{
								TheBasics.getEconomy().setBalance(player2, amount);
								BasicUtils.sendMessage(player, "&6You have set the balance for " + args[1] + " to &7$" + amount + "&6.");
								BasicUtils.sendMessage(player2, "&6Your balance has been set to &7$" + amount + " &6by " + player.getName() + ".");
							}else if(args[0].equalsIgnoreCase("add"))
							{
								TheBasics.getEconomy().depositBalance(player2, amount);
								BasicUtils.sendMessage(player, "&6You have gave " + args[1] + " &7$" + amount + "&6.");
								BasicUtils.sendMessage(player2, "&6You have recieved &7$" + amount + " &6from " + player.getName() + ".");
							}else if(args[0].equalsIgnoreCase("take"))
							{
								if(TheBasics.getEconomy().withdrawBalance(player2, amount))
								{
									BasicUtils.sendMessage(player, "&6You have took" + args[1] + " &7$" + amount + "&6.");
									BasicUtils.sendMessage(player2, "&6You have lost &7$" + amount + " &6from " + player.getName() + ".");
								}else
								{
									BasicUtils.sendMessage(player, "&cThat player does not have enough money!");
								}
							}else
							{
								BasicUtils.sendMessage(player, "&cUsage: " + getUsage());
							}
						}else
						{
							BasicUtils.sendMessage(player, "&cPlease specify a valid amount!");
						}
					}catch(NumberFormatException e)
					{
						BasicUtils.sendMessage(player, "&cPlease specify a valid amount!");
					}
				}else
				{
					BasicUtils.sendMessage(player, "&cThat player is not online!");
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
		if(args.length == 1)
		{
			if(console.hasPermission("TheBasics.Balance.Others"))
			{
				Player player = Bukkit.getPlayer(args[0]);
				
				if(player != null)
				{
					double balance = TheBasics.getEconomy().getBalance(player);
					
					BasicUtils.sendMessage(console, "&6The balance of " + args[0] + " is &7$" + balance + "&6.");
				}else
				{
					BasicUtils.sendMessage(console, "&cThat console is not online!");
				}
			}else
			{
				BasicUtils.sendMessage(console, "&cYou do not have enough permission to perform this command!");
			}
		}else if(args.length == 3)
		{
			if(console.hasPermission("TheBasics.Balance." + args[0].toLowerCase()))
			{
				Player player = Bukkit.getPlayer(args[1]);
				
				if(player != null)
				{
					try
					{
						double amount = Double.parseDouble(args[2]);
						
						if(amount >= 0)
						{
							if(args[0].equalsIgnoreCase("set"))
							{
								TheBasics.getEconomy().setBalance(player, amount);
								BasicUtils.sendMessage(console, "&6You have set the balance for " + args[1] + " to &7$" + amount + "&6.");
								BasicUtils.sendMessage(player, "&6Your balance has been set to &7$" + amount + " &6by " + console.getName() + ".");
							}else if(args[0].equalsIgnoreCase("add"))
							{
								TheBasics.getEconomy().depositBalance(player, amount);
								BasicUtils.sendMessage(console, "&6You have gave " + args[1] + " &7$" + amount + "&6.");
								BasicUtils.sendMessage(player, "&6You have recieved &7$" + amount + " &6from " + console.getName() + ".");
							}else if(args[0].equalsIgnoreCase("take"))
							{
								if(TheBasics.getEconomy().withdrawBalance(player, amount))
								{
									BasicUtils.sendMessage(console, "&6You have took " + args[1] + " &7$" + amount + "&6.");
									BasicUtils.sendMessage(player, "&6You have lost &7$" + amount + " &6from " + console.getName() + ".");
								}else
								{
									BasicUtils.sendMessage(player, "&cThat player does not have enough money!");
								}
							}else
							{
								BasicUtils.sendMessage(console, "&cUsage: " + getUsage());
							}
						}else
						{
							BasicUtils.sendMessage(console, "&cPlease specify a valid amount!");
						}
					}catch(NumberFormatException e)
					{
						BasicUtils.sendMessage(console, "&cPlease specify a valid amount!");
					}
				}else
				{
					BasicUtils.sendMessage(console, "&cThat console is not online!");
				}
			}else
			{
				BasicUtils.sendMessage(console, "&cYou do not have enough permission to perform this command!");
			}
		}else
		{
			BasicUtils.sendMessage(console, "&cUsage: " + getUsage());
		}
	}
}