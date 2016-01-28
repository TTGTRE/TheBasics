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