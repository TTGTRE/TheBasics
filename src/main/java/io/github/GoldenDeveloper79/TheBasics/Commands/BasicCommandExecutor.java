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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Registery;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class BasicCommandExecutor implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		//If the command is one of ours.
		if(Registery.commands.containsKey(cmd.getLabel()))
		{
			boolean hasPermission = false;
			CommandModule mod = Registery.commands.get(cmd.getLabel());
		
			for(String labels : mod.getLabels())
			{
				if(sender.hasPermission("thebasics." + labels))
				{
					hasPermission = true;
					break;
				}
			}
			
			//If the sender has the permission.
			if(hasPermission)
			{
				//If the arguments are within the modules defined amounts.
				if(args.length <= mod.getMaxArgs() && mod.getMinArgs() <= args.length)
				{
					if(args.length >= 1)
					{
						//If the command is to be multiplayer always and if the player is online.
						if(mod.getMultiPlayer().equals(MultiPlayer.ALWAYS)  && Bukkit.matchPlayer(args[0]) == null)
						{
							BasicUtils.sendMessage(sender, "&cThat player it not online!");
							return true;
						//If the command is to be multiplayer when the args are greater or equal to 1 and the player is online.
						}else if(mod.getMultiPlayer().equals(MultiPlayer.SOMETIMES) && Bukkit.matchPlayer(args[0]) == null)
						{
							BasicUtils.sendMessage(sender, "&cThat player is not online!");
							return true;
						}
					}
					
					//If the sender is a player.
					if(sender instanceof Player)
					{
						mod.performCommand((Player) sender, args);
					//If the sender is a console.
					}else if(sender instanceof ConsoleCommandSender && args.length >= 1)
					{
						mod.performCommand((ConsoleCommandSender) sender, args);
					}else
					{
						BasicUtils.sendMessage(sender, "&cYou must be a player to perform this command!");
					}
				}else
				{
					BasicUtils.sendMessage(sender, "&cUsage: " + mod.getUsage());
				}
			}else
			{
				BasicUtils.sendMessage(sender, "&cYou do not have enough permission to perform this command!");
			}
		}
		
		return true;
	}
}
