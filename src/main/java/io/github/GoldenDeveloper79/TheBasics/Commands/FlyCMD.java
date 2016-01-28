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

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class FlyCMD extends CommandModule
{
	public FlyCMD()
	{
		super(new String[] {"fly"}, 0, 1, MultiPlayer.SOMETIMES);
	}

	public void performCommand(Player player, String[] args) 
	{
		if(args.length < 1)
		{
			if(player.hasPermission("TheBasics.Fly"))
			{
				if(player.getAllowFlight())
				{
					player.setAllowFlight(false);
					player.setFlying(false);
					
					BasicUtils.sendMessage(player, "&6You have disabled flying for yourself.");
				}else
				{
					player.setAllowFlight(true);
					player.setFlying(true);
					
					BasicUtils.sendMessage(player, "&6You have enabled flying for yourself.");
				}
			}else
			{
				BasicUtils.sendMessage(player, "&cYou do not have enough permission to perform this command!");
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
					
					BasicUtils.sendMessage(player, "&6You have disabled flying for &7" + args[0] + "&6.");
					BasicUtils.sendMessage(player2, "&6Flying has been disabled for you by &7" + player.getName() + "&7.");
				}else
				{
					player2.setAllowFlight(true);
					player2.setFlying(true);
					
					BasicUtils.sendMessage(player, "&6You have enabled flying for &7" + args[0] + "&6.");
					BasicUtils.sendMessage(player2, "&6Flying has been enabled for you by &7" + player.getName() + "&6.");
				}
			}else
			{
				BasicUtils.sendMessage(player, "&cYou do not have enough permission to perform this command!");
			}
		}
		
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		if(console.hasPermission("TheBasics.Fly.Others"))
		{
			Player player2 = Bukkit.getPlayer(args[0]);
			
			if(player2.getAllowFlight())
			{
				player2.setAllowFlight(false);
				player2.setFlying(false);
				
				BasicUtils.sendMessage(console, "You have disabled flying for &7" + args[0] + "&6.");
				BasicUtils.sendMessage(player2, "&6Flying has been disabled for you by &7console.");
			}else
			{
				player2.setAllowFlight(true);
				player2.setFlying(true);
				
				BasicUtils.sendMessage(console, "You have enabled flying for &7" + args[0] + "&6.");
				BasicUtils.sendMessage(player2, "&6Flying has been enabled for you by &7console.");
			}
		}else
		{
			BasicUtils.sendMessage(console, "You do not have enough permission to perform this command!");
		}
	}
}
