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
import org.bukkit.GameMode;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class GamemodeCMD extends CommandModule
{
	public GamemodeCMD()
	{
		super(new String[] {"gamemode", "gm"}, 0, 2, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args) 
	{
		Player retriever = null;
		GameMode mode = null;

		if(args.length < 0)
		{	
			retriever = player;

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
				retriever = player;

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
				retriever = Bukkit.getPlayer(args[0]);

				switch(retriever.getGameMode())
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
				retriever = Bukkit.getPlayer(args[0]);

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
				BasicUtils.sendMessage(player, "&cThat player is not online!");
			}
		}

		if(mode != null && retriever != null)
		{
			retriever.setGameMode(mode);

			if(retriever == player)
			{
				BasicUtils.sendMessage(player, "&6You have set your gamemode to &7" + mode.toString().toLowerCase() + "&6.");
			}else
			{
				BasicUtils.sendMessage(player, "&6You have changed the gamemode for &7" + args[0] + "&6 to &7" + mode.toString().toLowerCase() + "&6.");
				BasicUtils.sendMessage(retriever, "&6Your gamemode has been changed to &7" + mode.toString().toLowerCase() + " &6by &7" + player.getName() + ".");
			}
		}else
		{
			BasicUtils.sendMessage(player, "&cUsage: &7" + getUsage());
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		if(args.length >= 1)
		{		
			Player retriever = Bukkit.getPlayer(args[0]);
			GameMode mode = null;

			if(retriever != null)
			{
				if(args.length == 1)
				{
					switch(retriever.getGameMode())
					{
					case SURVIVAL:
						mode = GameMode.CREATIVE;
					default:
						mode = GameMode.SURVIVAL;
					}
				}else if(args.length == 2)
				{
					switch(args[1].toLowerCase())
					{
					case "s":
					case "survival":
						mode = GameMode.SURVIVAL;
					case "c":
					case "creative":
						mode = GameMode.CREATIVE;
					case "a":
					case "adventure":
						mode = GameMode.ADVENTURE;
					case "spec":
					case "spectator":
						mode = GameMode.SPECTATOR;
					}
				}
				
				if(mode != null)
				{
					retriever.setGameMode(mode);

					BasicUtils.sendMessage(console, "&6You have changed the gamemode for &7" + args[0] + "&6 to &7" + mode.toString().toLowerCase() + "&6.");
					BasicUtils.sendMessage(retriever, "&6Your gamemode has been changed to &7" + mode.toString().toLowerCase() + " &6by &7console&6.");
				}else
				{
					BasicUtils.sendMessage(console, "&cUsage: &7" + getUsage());
				}
			}else
			{
				BasicUtils.sendMessage(console, "&cThat player is not online!");
			}
		}else
		{
			BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
		}
	}
}