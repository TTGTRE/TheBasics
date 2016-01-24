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

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class BanCMD extends CommandModule
{
	public BanCMD() 
	{
		super(new String[] {"ban"}, 1, Integer.MAX_VALUE, MultiPlayer.ALWAYS);
	}

	public void performCommand(Player player, String[] args)
	{
		PlayerData data = BasicUtils.getData(args[0]);

		String reason = "&cYou have been banned by " + player.getName() + "!";
		
		if(args.length > 1)
		{
			reason = BasicUtils.combineString(1, args);
		}
		
		data.set("Banned.Is", true);
		data.set("Banned.Time", -1);
		data.set("Banned.Reason", reason);
		
		Bukkit.getBanList(Type.NAME).addBan(args[0], reason, null, player.getName());
		data.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
		
		BasicUtils.notify("TheBasics.Ban.Notify", "&6The player " + player.getName() + " has banned the player " + args[0] + " for " + reason + "!");
		BasicUtils.sendMessage(player, "&6You have banned " + args[0] + "!");
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		PlayerData data = BasicUtils.getData(args[0]);
		String reason = "&cYou have been banned by " + console.getName() + "!";
		
		if(args.length > 1)
		{
			reason = BasicUtils.combineString(1, args);
		}
		
		data.set("Banned.Is", true);
		data.set("Banned.Time", -1);
		data.set("Banned.Reason", reason);
		
		Bukkit.getBanList(Type.NAME).addBan(args[0], reason, null, console.getName());
		data.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
		
		BasicUtils.notify("TheBasics.Ban.Notify", "&6The player " + console.getName() + " has banned the player " + args[0] + " for " + reason + "!");
		BasicUtils.sendMessage(console, "You have banned " + args[0] + "!");
	}
}
