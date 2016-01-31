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

public class GroupCMD extends CommandModule
{
	/*
	 * group set <player> <group>
	 * group create <name>
	 * group get <player>
	 */
	public GroupCMD()
	{
		super(new String[] {"group"}, 2, 3, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args) 
	{
		if(player.hasPermission("TheBasics.Group." + args[0]))
		{
			if(args[0].equalsIgnoreCase("set"))
			{
				Player player2 = Bukkit.getPlayer(args[1]);
				
				if(player2 != null)
				{
					if(TheBasics.getPermissions().groupExist(args[2]))
					{
						TheBasics.getPermissions().addPlayerToGroup(player2, TheBasics.getPermissions().getGroup(args[2]));
						BasicUtils.sendMessage(player, BasicUtils.getMessage("GroupSetSender").replace("%p", args[1]).replace("%a", args[2]));
						BasicUtils.sendMessage(player2, BasicUtils.getMessage("GroupSetReceiver").replace("%p", player.getName()).replace("%a", args[2]));
					}else
					{
						BasicUtils.sendMessage(player, BasicUtils.getMessage("GroupExist"));
					}
				}else
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("PlayerOffline"));
				}
			}else if(args[0].equalsIgnoreCase("get"))
			{
				Player player2 = Bukkit.getPlayer(args[1]);
				
				if(player2 != null)
				{
					String groupName = TheBasics.getPermissions().getPlayerGroup(player2).getGroupName();
					
					BasicUtils.sendMessage(player, BasicUtils.getMessage("GroupGet").replace("%p", args[1]).replace("%a", groupName));
				}else
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("PlayerOffline"));
				}
			}else if(args[0].equalsIgnoreCase("create"))
			{
				if(!TheBasics.getPermissions().groupExist(args[1]))
				{
					TheBasics.getPermissions().createGroup(args[1]);
					
					BasicUtils.sendMessage(player, BasicUtils.getMessage("GroupCreate").replace("%a", args[1]));
				}else
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("GroupExistTwo"));
				}
			}else if(args[0].equalsIgnoreCase("prefix"))
			{
				if(TheBasics.getPermissions().groupExist(args[1]))
				{
					TheBasics.getPermissions().getGroup(args[1]).setPrefix(args[2]);
					
					BasicUtils.sendMessage(player, BasicUtils.getMessage("GroupPrefix").replace("%a", args[1]).replace("%p", args[2]));
				}else
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("GroupExist"));
				}
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("Usage").replace("%u", getUsage()));
			}
		}else
		{
			BasicUtils.sendMessage(player, BasicUtils.getMessage("NoPermission"));
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		if(console.hasPermission("TheBasics.Group." + args[0]))
		{
			if(args[0].equalsIgnoreCase("set"))
			{
				Player player2 = Bukkit.getPlayer(args[1]);
				
				if(player2 != null)
				{
					if(TheBasics.getPermissions().groupExist(args[2]))
					{
						TheBasics.getPermissions().addPlayerToGroup(player2, TheBasics.getPermissions().getGroup(args[2]));
						BasicUtils.sendMessage(console, BasicUtils.getMessage("GroupSetSender").replace("%p", args[1]).replace("%a", args[2]));
						BasicUtils.sendMessage(player2, BasicUtils.getMessage("GroupSetReceiver").replace("%p", console.getName()).replace("%a", args[2]));
					}else
					{
						BasicUtils.sendMessage(console, BasicUtils.getMessage("GroupExist"));
					}
				}else
				{
					BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerOffline"));
				}
			}else if(args[0].equalsIgnoreCase("get"))
			{
				Player player2 = Bukkit.getPlayer(args[1]);
				
				if(player2 != null)
				{
					String groupName = TheBasics.getPermissions().getPlayerGroup(player2).getGroupName();
					
					BasicUtils.sendMessage(console, BasicUtils.getMessage("GroupGet").replace("%p", args[1]).replace("%a", groupName));
				}else
				{
					BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerOffline"));
				}
			}else if(args[0].equalsIgnoreCase("create"))
			{
				if(!TheBasics.getPermissions().groupExist(args[1]))
				{
					TheBasics.getPermissions().createGroup(args[1]);
					
					BasicUtils.sendMessage(console, BasicUtils.getMessage("GroupCreate").replace("%a", args[1]));
				}else
				{
					BasicUtils.sendMessage(console, BasicUtils.getMessage("GroupExistTwo"));
				}
			}else
			{
				BasicUtils.sendMessage(console, BasicUtils.getMessage("Usage").replace("%u", getUsage()));
			}
		}else
		{
			BasicUtils.sendMessage(console, BasicUtils.getMessage("NoPermission"));
		}
	}
}

