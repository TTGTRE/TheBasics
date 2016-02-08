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

public class FlyCMD extends CommandModule
{
	public FlyCMD()
	{
		super(new String[] {"fly"}, 0, 1, MultiPlayer.SOMETIMES);
	}

	public void performCommand(final Player player, final String[] args) 
	{
		if(args.length < 1)
		{
			if(player.getAllowFlight())
			{
				player.setAllowFlight(false);
				player.setFlying(false);
				
				BasicUtils.sendMessage(player, BasicUtils.getMessage("Fly").replace("%a", "disabled"));
			}else
			{
				player.setAllowFlight(true);
				player.setFlying(true);
				
				BasicUtils.sendMessage(player, BasicUtils.getMessage("Fly").replace("%a", "enabled"));
			}
		}else 
		{
			if(player.hasPermission("TheBasics.Fly.Others"))
			{
				Player player2 = Bukkit.getPlayer(args[0]);
				
				if(player2.getAllowFlight())
				{
					player2.setAllowFlight(false);
					player2.setFlying(false);
					
					BasicUtils.sendMessage(player, BasicUtils.getMessage("FlySender").replace("%p", args[0]).replace("%a", "disabled"));
					BasicUtils.sendMessage(player2, BasicUtils.getMessage("FlyReceiver").replace("%p", player.getName()).replace("%a", "disabled"));
				}else
				{
					player2.setAllowFlight(true);
					player2.setFlying(true);
					
					BasicUtils.sendMessage(player, BasicUtils.getMessage("FlySender").replace("%p", args[0]).replace("%a", "enabled"));
					BasicUtils.sendMessage(player2, BasicUtils.getMessage("FlyReceiver").replace("%p", player.getName()).replace("%a", "enabled"));
				}
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("NoPermission"));
			}
		}
		
	}

	public void performCommand(final ConsoleCommandSender console, final String[] args)
	{
		if(console.hasPermission("TheBasics.Fly.Others"))
		{
			Player player2 = Bukkit.getPlayer(args[0]);
			
			if(player2.getAllowFlight())
			{
				player2.setAllowFlight(false);
				player2.setFlying(false);
				
				BasicUtils.sendMessage(console, BasicUtils.getMessage("FlySender").replace("%p", args[0]).replace("%a", "disabled"));
				BasicUtils.sendMessage(player2, BasicUtils.getMessage("FlyReceiver").replace("%p", console.getName()).replace("%a", "disabled"));
			}else
			{
				player2.setAllowFlight(true);
				player2.setFlying(true);
				
				BasicUtils.sendMessage(console, BasicUtils.getMessage("FlySender").replace("%p", args[0]).replace("%a", "enabled"));
				BasicUtils.sendMessage(player2, BasicUtils.getMessage("FlyReceiver").replace("%p", console.getName()).replace("%a", "enabled"));
			}
		}else
		{
			BasicUtils.sendMessage(console, BasicUtils.getMessage("NoPermission"));
		}
	}
	
	public void performCommand(final CommandSender sender, final String[] args)
	{
		
	}
}
