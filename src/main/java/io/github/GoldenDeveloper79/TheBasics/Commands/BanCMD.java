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

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;
import io.github.GoldenDeveloper79.TheBasics.Utils.BasicUtils;

public class BanCMD extends CommandModule
{
	public BanCMD() 
	{
		super(new String[] {"ban"}, 1, Integer.MAX_VALUE, MultiPlayer.ALWAYS);
	}

	public void performCommand(Player player, String[] args)
	{
		PlayerData data = BasicUtils.getData(args[0]);

		String reason = BasicUtils.getMessage("BanDefault").replace("%p", player.getName());
		
		if(args.length > 1)
		{
			reason = BasicUtils.combineString(1, args);
		}
		
		Bukkit.getBanList(Type.NAME).addBan(args[0], reason, null, player.getName());
		data.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
		
		BasicUtils.notify("TheBasics.Ban.Notify", BasicUtils.getMessage("BanNotify").replace("%p", player.getName()).replace("%p2", args[0]).replace("%r", reason));
		BasicUtils.sendMessage(player, BasicUtils.getMessage("BanSender").replace("%p", args[0]));
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		PlayerData data = BasicUtils.getData(args[0]);

		String reason = BasicUtils.getMessage("BanDefault").replace("%p", console.getName());
		
		if(args.length > 1)
		{
			reason = BasicUtils.combineString(1, args);
		}
		
		Bukkit.getBanList(Type.NAME).addBan(args[0], reason, null, console.getName());
		data.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
		
		BasicUtils.notify("TheBasics.Ban.Notify", BasicUtils.getMessage("BanNotify").replace("%p", console.getName()).replace("%p2", args[0]).replace("%r", reason));
		BasicUtils.sendMessage(console, BasicUtils.getMessage("BanSender").replace("%p", args[0]));
	}
}
