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

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Registery;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class TeleportRequestDenyCMD extends CommandModule
{
	public TeleportRequestDenyCMD()
	{
		super(new String[] {"teleportrequestdeny", "trdeny"}, 0, 0, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args)
	{
		if(Registery.teleportRequest.containsValue(player.getName()))
		{
			String playerName = null;
			
			for(Entry<String, String> players : Registery.teleportRequest.entrySet())
			{
				if(players.getValue().equals(player.getName()))
				{
					playerName = players.getKey();
					break;
				}
			}
			
			Player player2 = Bukkit.getPlayer(playerName);
			
			if(player2 != null)
			{
				Registery.teleportRequest.remove(playerName);
				BasicUtils.sendMessage(player, "&6You have denied the request to be teleported too.");
				BasicUtils.sendMessage(player2, "&6Your request to teleport to &7" + player.getName() + "&6 has been denied.");
			}else
			{
				BasicUtils.sendMessage(player, "&cThere is no teleport request to you.");
			}
		}else
		{
			BasicUtils.sendMessage(player, "&cThere is no teleport request too you.");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}
}