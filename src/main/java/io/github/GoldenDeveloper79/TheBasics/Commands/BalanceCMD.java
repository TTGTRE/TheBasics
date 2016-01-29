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

			BasicUtils.sendMessage(player, BasicUtils.getMessage("BalanceGet").replace("%a", String.valueOf(balance)));
		}else if(args.length == 1)
		{
			if(player.hasPermission("TheBasics.Balance.Others"))
			{
				Player player2 = Bukkit.getPlayer(args[0]);

				if(player2 != null)
				{
					double balance = TheBasics.getEconomy().getBalance(player2);

					BasicUtils.sendMessage(player, BasicUtils.getMessage("BalanceGetOthers").replace("%p", args[0]).replace("%a", String.valueOf(balance)));
				}else
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("PlayerOffline"));
				}
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("NoPermission"));
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
								BasicUtils.sendMessage(player, BasicUtils.getMessage("BalanceSetSender").replace("%p", args[1]).replace("%a", String.valueOf(amount)));
								BasicUtils.sendMessage(player, BasicUtils.getMessage("BalanceSetReceiver").replace("%p", player.getName()).replace("%a", String.valueOf(amount)));
							}else if(args[0].equalsIgnoreCase("add"))
							{
								TheBasics.getEconomy().depositBalance(player2, amount);
								BasicUtils.sendMessage(player, BasicUtils.getMessage("BalanceGaveSender").replace("%p", args[1]).replace("%a", String.valueOf(amount)));
								BasicUtils.sendMessage(player2, BasicUtils.getMessage("BalanceGaveReceiver").replace("%p", player.getName()).replace("%a", String.valueOf(amount)));
							}else if(args[0].equalsIgnoreCase("take"))
							{
								if(TheBasics.getEconomy().withdrawBalance(player2, amount))
								{
									BasicUtils.sendMessage(player, BasicUtils.getMessage("BalanceTakeSender").replace("%p", args[1]).replace("%a", String.valueOf(amount)));
									BasicUtils.sendMessage(player2, BasicUtils.getMessage("BalanceTakeReceiver").replace("%p", player.getName()).replace("%a", String.valueOf(amount)));
								}else
								{
									BasicUtils.sendMessage(player, BasicUtils.getMessage("BalanceLackOfFunds"));
								}
							}else
							{
								BasicUtils.sendMessage(player, BasicUtils.getMessage("Usage").replace("%u", getUsage()));
							}
						}else
						{
							BasicUtils.sendMessage(player, BasicUtils.getMessage("InvalidValue"));
						}
					}catch(NumberFormatException e)
					{
						BasicUtils.sendMessage(player, BasicUtils.getMessage("InvalidValue"));
					}
				}else
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("PlayerOffline"));
				}
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("NoPermission"));
			}
		}else
		{
			BasicUtils.sendMessage(player, BasicUtils.getMessage("Usage").replace("%u", getUsage()));
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		if(args.length == 1)
		{
			if(console.hasPermission("TheBasics.Balance.Others"))
			{
				Player player2 = Bukkit.getPlayer(args[0]);

				if(player2 != null)
				{
					double balance = TheBasics.getEconomy().getBalance(player2);

					BasicUtils.sendMessage(console, BasicUtils.getMessage("BalanceGetOthers").replace("%p", args[0]).replace("%a", String.valueOf(balance)));
				}else
				{
					BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerOffline"));
				}
			}else
			{
				BasicUtils.sendMessage(console, BasicUtils.getMessage("NoPermission"));
			}
		}else if(args.length == 3)
		{
			if(console.hasPermission("TheBasics.Balance." + args[0].toLowerCase()))
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
								BasicUtils.sendMessage(console, BasicUtils.getMessage("BalanceSetSender").replace("%p", args[1]).replace("%a", String.valueOf(amount)));
								BasicUtils.sendMessage(player2, BasicUtils.getMessage("BalanceSetReceiver").replace("%p", console.getName()).replace("%a", String.valueOf(amount)));
							}else if(args[0].equalsIgnoreCase("add"))
							{
								TheBasics.getEconomy().depositBalance(player2, amount);
								BasicUtils.sendMessage(console, BasicUtils.getMessage("BalanceGaveSender").replace("%p", args[1]).replace("%a", String.valueOf(amount)));
								BasicUtils.sendMessage(player2, BasicUtils.getMessage("BalanceGaveReceiver").replace("%p", console.getName()).replace("%a", String.valueOf(amount)));
							}else if(args[0].equalsIgnoreCase("take"))
							{
								if(TheBasics.getEconomy().withdrawBalance(player2, amount))
								{
									BasicUtils.sendMessage(console, BasicUtils.getMessage("BalanceTakeSender").replace("%p", args[1]).replace("%a", String.valueOf(amount)));
									BasicUtils.sendMessage(player2, BasicUtils.getMessage("BalanceTakeReceiver").replace("%p", console.getName()).replace("%a", String.valueOf(amount)));
								}else
								{
									BasicUtils.sendMessage(console, BasicUtils.getMessage("BalanceLackOfFunds"));
								}
							}else
							{
								BasicUtils.sendMessage(console, BasicUtils.getMessage("Usage").replace("%u", getUsage()));
							}
						}else
						{
							BasicUtils.sendMessage(console, BasicUtils.getMessage("InvalidValue"));
						}
					}catch(NumberFormatException e)
					{
						BasicUtils.sendMessage(console, BasicUtils.getMessage("InvalidValue"));
					}
				}else
				{
					BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerOffline"));
				}
			}else
			{
				BasicUtils.sendMessage(console, BasicUtils.getMessage("NoPermission"));
			}
		}else
		{
			BasicUtils.sendMessage(console, BasicUtils.getMessage("Usage").replace("%u", getUsage()));
		}
	}
}