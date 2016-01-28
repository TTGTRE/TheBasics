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
						BasicUtils.sendMessage(player, "&6You added &7" + args[1] + " &6to the group &7" + args[2] + "&6.");
						BasicUtils.sendMessage(player2, "&6You have been added to the group &7" + args[2] + "&6 by &7" + player.getName() + "&6.");
					}else
					{
						BasicUtils.sendMessage(player, "&cThat group does not exist!");
					}
				}else
				{
					BasicUtils.sendMessage(player, "&cThat player is not online!");
				}
			}else if(args[0].equalsIgnoreCase("get"))
			{
				Player player2 = Bukkit.getPlayer(args[1]);
				
				if(player2 != null)
				{
					String groupName = TheBasics.getPermissions().getPlayerGroup(player2).getGroupName();
					
					BasicUtils.sendMessage(player, "&6The player &7" + args[1] + " &6is in the group &7" + groupName + "&6.");
				}else
				{
					BasicUtils.sendMessage(player, "&cThat player is not online!");
				}
			}else if(args[0].equalsIgnoreCase("create"))
			{
				if(!TheBasics.getPermissions().groupExist(args[1]))
				{
					TheBasics.getPermissions().createGroup(args[1]);
					
					BasicUtils.sendMessage(player, "&6You have created the group called &7" + args[1] + "&6.");
				}else
				{
					BasicUtils.sendMessage(player, "&cThat group already exist!");
				}
			}else
			{
				BasicUtils.sendMessage(player, "&cUsage: &7" + getUsage());
			}
		}else
		{
			BasicUtils.sendMessage(player, "&cYou do not have enough permission to perform this command!");
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
						BasicUtils.sendMessage(console, "&6You added &7" + args[1] + " &6to the group &7" + args[2] + "&6.");
						BasicUtils.sendMessage(player2, "&6You have been added to the group &7" + args[2] + "&6 by &7console&6.");
					}else
					{
						BasicUtils.sendMessage(console, "&cThat group does not exist!");
					}
				}else
				{
					BasicUtils.sendMessage(console, "&cThat player is not online!");
				}
			}else if(args[0].equalsIgnoreCase("get"))
			{
				Player player2 = Bukkit.getPlayer(args[1]);
				
				if(player2 != null)
				{
					String groupName = TheBasics.getPermissions().getPlayerGroup(player2).getGroupName();
					
					BasicUtils.sendMessage(console, "&6The player &7" + args[1] + " &6is in the group &7" + groupName + "&6.");
				}else
				{
					BasicUtils.sendMessage(console, "&cThat player is not online!");
				}
			}else if(args[0].equalsIgnoreCase("create"))
			{
				if(!TheBasics.getPermissions().groupExist(args[1]))
				{
					TheBasics.getPermissions().createGroup(args[1]);
					
					BasicUtils.sendMessage(console, "&6You have created the group called &7" + args[1] + "&6.");
				}else
				{
					BasicUtils.sendMessage(console, "&cThat group already exist!");
				}
			}else
			{
				BasicUtils.sendMessage(console, "&cUsage: &7" + getUsage());
			}
		}else
		{
			BasicUtils.sendMessage(console, "&cYou do not have enough permission to perform this command!");
		}
	}
}

