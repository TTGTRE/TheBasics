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
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class KickCMD extends CommandModule
{
	public KickCMD() 
	{
		super(new String[] {"kick"}, 1, Integer.MAX_VALUE, MultiPlayer.ALWAYS);
	}

	public void performCommand(Player player, String[] args)
	{
		Player player2 = Bukkit.getPlayer(args[0]);

		String reason = "&cYou have been kicked by &7" + player.getName() + "&6!";
		
		if(args.length > 1)
		{
			reason = BasicUtils.combineString(1, args);
		}
		
		player2.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
		BasicUtils.notify("TheBasics.Kick.Notify", "&6The player &7" + player.getName() + " &6has kicked the player &7" + args[0] + " &6for " + reason + "&6!");
		BasicUtils.sendMessage(player, "&6You have kicked &7" + args[0] + "&6!");
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		Player player2 = Bukkit.getPlayer(args[0]);
	
		String reason = "&cYou have been kicked by &7console&6!";
		
		if(args.length > 1)
		{
			reason = BasicUtils.combineString(1, args);
		}

		player2.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
		
		BasicUtils.notify("TheBasics.Kick.Notify", "&6The player &7console&6 has kicked the player &7" + args[0] + " &6for " + reason + "&6!");
		BasicUtils.sendMessage(console, "&6You have kicked &7" + args[0] + "&6!");
	}
}