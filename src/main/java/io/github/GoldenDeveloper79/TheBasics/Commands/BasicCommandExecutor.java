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
