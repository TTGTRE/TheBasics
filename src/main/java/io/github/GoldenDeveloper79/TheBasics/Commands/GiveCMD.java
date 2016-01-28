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
import org.bukkit.inventory.ItemStack;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class GiveCMD extends CommandModule
{
	public GiveCMD() 
	{
		super(new String[] {"give"}, 1, 3, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args)
	{
		ItemStack item = null;
		Player receiver = null;
		
		if(args.length == 1)
		{
			item = BasicUtils.getItem(args[0], "64");
			receiver = player;
		}else if(args.length == 2)
		{
			if(Bukkit.getPlayer(args[0]) == null)
			{
				item = BasicUtils.getItem(args[0], args[1]);
				receiver = player;
			}else
			{
				item = BasicUtils.getItem(args[1], "64");
				receiver = Bukkit.getPlayer(args[0]);
			}
		}else if(args.length == 3)
		{
			if(Bukkit.getPlayer(args[0]) != null)
			{
				item = BasicUtils.getItem(args[1], args[2]);
				receiver = Bukkit.getPlayer(args[0]);
			}else
			{
				BasicUtils.sendMessage(player, "&cThat player is not online!");
				return;
			}
		}
		
		if(item != null && receiver != null)
		{
			if(receiver == player)
			{
				BasicUtils.sendMessage(player, "&6You received &7" + item.getAmount() + "x " + item.getType().toString().toLowerCase() + "&6.");
			}else
			{
				BasicUtils.sendMessage(player, "&6You gave &7" + args[0] + item.getAmount() + "x " + item.getType().toString().toLowerCase() + "&6.");
				BasicUtils.sendMessage(receiver, "&6You received &7" + item.getAmount() + "x " + item.getType().toString().toLowerCase() + " &6from&7 " + player.getName() + "&6.");
			}
			
			BasicUtils.addItem(receiver, item);
		}else
		{
			BasicUtils.sendMessage(player, "&cPlease specify a valid itemid/name and amount!");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		ItemStack item = null;
		Player player = Bukkit.getPlayer(args[0]);
		
		if(player != null)
		{
			if(args.length == 2)
			{
				item = BasicUtils.getItem(args[1], "64");
			}else if(args.length == 3)
			{
				item = BasicUtils.getItem(args[1], args[2]);
			}
			
			if(item != null)
			{
				BasicUtils.sendMessage(console, "&6You gave &7" + args[0] + item.getAmount() + "x " + item.getType().toString().toLowerCase() + "&6.");
				BasicUtils.sendMessage(player, "&6You received &7" + item.getAmount() + "x " + item.getType().toString().toLowerCase() + " &6from&7 " + player.getName() + "&6.");
				BasicUtils.addItem(player, item);
			}else
			{
				BasicUtils.sendMessage(console, "Please specify a valid itemid/name and amount!");
			}
		}else
		{
			BasicUtils.sendMessage(console, "That player is not online!");
		}
	}
}
