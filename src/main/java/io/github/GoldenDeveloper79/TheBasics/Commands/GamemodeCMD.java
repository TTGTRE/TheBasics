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
import org.bukkit.GameMode;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Utils.BasicUtils;

public class GamemodeCMD extends CommandModule
{
	//gamemode <name> <mode>
	public GamemodeCMD()
	{
		super(new String[] {"gamemode", "gm"}, 0, 2, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args) 
	{
		Player receiver = null;
		GameMode mode = null;

		if(args.length < 0)
		{	
			receiver = player;

			switch(player.getGameMode())
			{
				case SURVIVAL:
					mode = GameMode.CREATIVE;
				default:
					mode = GameMode.SURVIVAL;
			}
		}else if(args.length == 1)
		{
			if(Bukkit.getPlayer(args[0]) == null)
			{
				receiver = player;

				if(args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0"))
				{
					mode = GameMode.SURVIVAL;
				}else if(args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1"))
				{
					mode = GameMode.CREATIVE;
				}else if(args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2"))
				{
					mode = GameMode.ADVENTURE;
				}else if(args[0].equalsIgnoreCase("spec") || args[0].equalsIgnoreCase("specator") || args[0].equalsIgnoreCase("3"))
				{
					mode = GameMode.SPECTATOR;
				}	
			}else
			{
				receiver = Bukkit.getPlayer(args[0]);

				switch(receiver.getGameMode())
				{
				case SURVIVAL:
					mode = GameMode.CREATIVE;
				default:
					mode = GameMode.SURVIVAL;
				}
			}
		}else if(args.length == 2)
		{
			if(Bukkit.getPlayer(args[0]) != null)
			{
				receiver = Bukkit.getPlayer(args[0]);

				if(args[1].equalsIgnoreCase("s") || args[1].equalsIgnoreCase("survival") || args[1].equalsIgnoreCase("0"))
				{
					mode = GameMode.SURVIVAL;
				}else if(args[1].equalsIgnoreCase("c") || args[1].equalsIgnoreCase("creative") || args[1].equalsIgnoreCase("1"))
				{
					mode = GameMode.CREATIVE;
				}else if(args[1].equalsIgnoreCase("a") || args[1].equalsIgnoreCase("adventure") || args[1].equalsIgnoreCase("2"))
				{
					mode = GameMode.ADVENTURE;
				}else if(args[1].equalsIgnoreCase("spec") || args[1].equalsIgnoreCase("specator") || args[1].equalsIgnoreCase("3"))
				{
					mode = GameMode.SPECTATOR;
				}	
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("PlayerOffline"));
			}
		}

		if(mode != null && receiver != null)
		{
			receiver.setGameMode(mode);

			if(receiver == player)
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("Gamemode").replace("%a", mode.name().toLowerCase()));
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("GamemodeSender").replace("%p", receiver.getName()).replace("%a", mode.name().toLowerCase()));
				BasicUtils.sendMessage(receiver, BasicUtils.getMessage("GamemodeReceiver").replace("%p", player.getName()).replace("%a", mode.name().toLowerCase()));
			}
		}else
		{
			BasicUtils.sendMessage(player, BasicUtils.getMessage("Usage").replace("%u", getUsage()));
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		Player receiver = null;
		GameMode mode = null;
		
		if(args.length == 1)
		{
			if(Bukkit.getPlayer(args[0]) != null)
			{
				receiver = Bukkit.getPlayer(args[0]);

				switch(receiver.getGameMode())
				{
				case SURVIVAL:
					mode = GameMode.CREATIVE;
				default:
					mode = GameMode.SURVIVAL;
				}
			}else
			{
				BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerOffline"));
			}
		}else if(args.length == 2)
		{
			if(Bukkit.getPlayer(args[0]) != null)
			{
				receiver = Bukkit.getPlayer(args[0]);

				if(args[1].equalsIgnoreCase("s") || args[1].equalsIgnoreCase("survival") || args[1].equalsIgnoreCase("0"))
				{
					mode = GameMode.SURVIVAL;
				}else if(args[1].equalsIgnoreCase("c") || args[1].equalsIgnoreCase("creative") || args[1].equalsIgnoreCase("1"))
				{
					mode = GameMode.CREATIVE;
				}else if(args[1].equalsIgnoreCase("a") || args[1].equalsIgnoreCase("adventure") || args[1].equalsIgnoreCase("2"))
				{
					mode = GameMode.ADVENTURE;
				}else if(args[1].equalsIgnoreCase("spec") || args[1].equalsIgnoreCase("specator") || args[1].equalsIgnoreCase("3"))
				{
					mode = GameMode.SPECTATOR;
				}		
			}else
			{
				BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerOffline"));
			}
		}

		if(mode != null && receiver != null)
		{
			receiver.setGameMode(mode);

			BasicUtils.sendMessage(console, BasicUtils.getMessage("GamemodeSender").replace("%p", receiver.getName()).replace("%a", mode.name().toLowerCase()));
			BasicUtils.sendMessage(receiver, BasicUtils.getMessage("GamemodeReceiver").replace("%p", console.getName()).replace("%a", mode.name().toLowerCase()));
		}else
		{
			BasicUtils.sendMessage(console, BasicUtils.getMessage("Usage").replace("%u", getUsage()));
		}
	}
}
