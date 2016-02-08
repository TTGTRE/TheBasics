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
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class VanishCMD extends CommandModule
{
	public VanishCMD() 
	{
		super(new String[] {"vanish", "v"}, 0, 1, MultiPlayer.SOMETIMES);
	}

	public void performCommand(final Player player, final String[] args) 
	{
		PlayerData receiver = null;
		String toggle;
		
		if(args.length < 1)
		{
			receiver = BasicUtils.getData(player);
		}else
		{
			receiver = BasicUtils.getData(Bukkit.getPlayer(args[0]));
		}
		
		if(receiver != null)
		{
			if(receiver.isVanished())
			{
				toggle = "unvanished";
				receiver.setVanished(false);
				
				for(Player players : Bukkit.getOnlinePlayers())
				{
					players.showPlayer(receiver.getPlayer());
				}
			}else
			{
				toggle = "vanished";
				receiver.setVanished(true);
				
				for(Player players : Bukkit.getOnlinePlayers())
				{
					if(!players.hasPermission("TheBasics.Vanish.See"))
					{
						players.hidePlayer(receiver.getPlayer());
					}
				}
			}
			
			if(receiver.getPlayer() == player)
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("Vanish").replace("%a", toggle));
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("VanishSender").replace("%p", args[0]).replace("%a", toggle));
				BasicUtils.sendMessage(receiver.getPlayer(), BasicUtils.getMessage("VanishReceiver").replace("%p", player.getName()).replace("%a", toggle));
			}
		}
	}

	public void performCommand(final ConsoleCommandSender console, final String[] args) 
	{
		if(args.length == 1)
		{
			PlayerData receiver = BasicUtils.getData(Bukkit.getPlayer(args[0]));
			String toggle;
			
			//Never hurts to double check.
			if(receiver != null)
			{
				if(receiver.isVanished())
				{
					toggle = "unvanished";
					receiver.setVanished(false);
					
					for(Player players : Bukkit.getOnlinePlayers())
					{
						players.showPlayer(receiver.getPlayer());
					}
				}else
				{
					toggle = "vanished";
					receiver.setVanished(true);
					
					for(Player players : Bukkit.getOnlinePlayers())
					{
						if(!players.hasPermission("TheBasics.Vanish.See"))
						{
							players.hidePlayer(receiver.getPlayer());
						}
					}
				}
				

				BasicUtils.sendMessage(console, BasicUtils.getMessage("VanishSender").replace("%p", args[0]).replace("%a", toggle));
				BasicUtils.sendMessage(receiver.getPlayer(), BasicUtils.getMessage("VanishReceiver").replace("%p", console.getName()).replace("%a", toggle));
			}
		}else
		{
			BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
		}
	}
	
	public void performCommand(final CommandSender sender, final String[] args){}
}
