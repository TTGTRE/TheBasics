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
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class FeedCMD extends CommandModule
{
	public FeedCMD() 
	{
		super(new String[] {"feed"}, 0, 1, MultiPlayer.SOMETIMES);
	}

	public void performCommand(final Player player, final String[] args) 
	{
		if(args.length < 1)
		{
			player.setFoodLevel(20);
			
			BasicUtils.sendMessage(player, BasicUtils.getMessage("Feed"));
		}else
		{
			Player player2 = Bukkit.getPlayer(args[0]);
			
			if(player.hasPermission("TheBasics.Feed.Others"))
			{
				player2.setFoodLevel(20);
				
				BasicUtils.sendMessage(player, BasicUtils.getMessage("FeedSender").replace("%p", args[0]));
				BasicUtils.sendMessage(player2, BasicUtils.getMessage("FeedReceiver").replace("%p", player.getName()));
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("NoPermission"));
			}
		}
	}

	public void performCommand(final ConsoleCommandSender console, final String[] args) 
	{
		Player player2 = Bukkit.getPlayer(args[0]);
	
		player2.setFoodLevel(20);
			
		BasicUtils.sendMessage(console, BasicUtils.getMessage("FeedSender").replace("%p", args[0]));
		BasicUtils.sendMessage(player2, BasicUtils.getMessage("FeedReceiver").replace("%p", console.getName()));
	}
	
	public void performCommand(final CommandSender sender, final String[] args){}
}