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
import org.bukkit.scheduler.BukkitRunnable;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Registery;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class TeleportRequestCMD extends CommandModule
{
	public TeleportRequestCMD()
	{
		super(new String[] {"teleportrequest", "tr"}, 1, 1, MultiPlayer.ALWAYS);
	}

	public void performCommand(Player player, String[] args)
	{
		Player player2 = Bukkit.getPlayer(args[0]);
		
		BasicUtils.sendMessage(player2, "&6The player &7" + player.getName() + "&6 would like to teleport to you. Do /traccept to accept or /trdeny to deny.");
		
		if(Registery.teleportRequest.containsKey(player.getName()))
		{
			BasicUtils.sendMessage(player, "&cYour teleport request for &7" + Registery.teleportRequest.get(player.getName()) + " &6has timed out.");
			Registery.teleportRequest.remove(player.getName());
		}
		
		Registery.teleportRequest.put(player.getName(), args[0]);
		
		new BukkitRunnable()
		{
			public void run()
			{
				if(Registery.teleportRequest.containsKey(player.getName()))
				{
					BasicUtils.sendMessage(player, "&cYour teleport request for &7" + args[0] + " &6has timed out.");
					Registery.teleportRequest.remove(player.getName());
					this.cancel();
				}
			}
		}.runTaskLater(TheBasics.getPlugin(), (long) (20 * TheBasics.getGeneralConfig().getDouble("TeleportRequestTime")));
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}
}
