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
import org.bukkit.scheduler.BukkitRunnable;

import io.github.GoldenDeveloper79.TheBasics.Registery;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Utils.BasicUtils;

public class TeleportRequestCMD extends CommandModule
{
	public TeleportRequestCMD()
	{
		super(new String[] {"teleportrequest", "tr"}, 1, 1, MultiPlayer.ALWAYS);
	}

	public void performCommand(Player player, String[] args)
	{
		Player player2 = Bukkit.getPlayer(args[0]);
		
		BasicUtils.sendMessage(player2, BasicUtils.getMessage("TeleportRequestReceiver").replace("%p", player.getName()));
		
		if(Registery.teleportRequest.containsKey(player.getName()))
		{
			BasicUtils.sendMessage(player, BasicUtils.getMessage("TeleportRequestTimeout"));
			Registery.teleportRequest.remove(player.getName());
		}
		
		Registery.teleportRequest.put(player.getName(), args[0]);
		
		new BukkitRunnable()
		{
			public void run()
			{
				if(Registery.teleportRequest.containsKey(player.getName()))
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("TeleportRequestTimeout"));
					Registery.teleportRequest.remove(player.getName());
					this.cancel();
				}
			}
		}.runTaskLater(TheBasics.getPlugin(), (long) (20 * TheBasics.getGeneralConfig().getDouble("TeleportRequestTime")));
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
	}
}
