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

		String reason = "&cYou have been banned by &7" + player.getName() + "&6!";
		
		if(args.length > 1)
		{
			reason = BasicUtils.combineString(1, args);
		}
		
		data.set("Banned.Is", true);
		data.set("Banned.Time", -1);
		data.set("Banned.Reason", reason);
		
		Bukkit.getBanList(Type.NAME).addBan(args[0], reason, null, player.getName());
		data.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
		
		BasicUtils.notify("TheBasics.Ban.Notify", "&6The player &7" + player.getName() + " &6has banned the player &7" + args[0] + " &6for " + reason + "&6!");
		BasicUtils.sendMessage(player, "&6You have banned " + args[0] + "!");
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		PlayerData data = BasicUtils.getData(args[0]);
		String reason = "&cYou have been banned by &7consle&6!";
		
		if(args.length > 1)
		{
			reason = BasicUtils.combineString(1, args);
		}
		
		data.set("Banned.Is", true);
		data.set("Banned.Time", -1);
		data.set("Banned.Reason", reason);
		
		Bukkit.getBanList(Type.NAME).addBan(args[0], reason, null, console.getName());
		data.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
		
		BasicUtils.notify("TheBasics.Ban.Notify", "&7Consle&6 has banned the player &7" + args[0] + " &6for " + reason + "&6!");
		BasicUtils.sendMessage(console, "You have banned &7" + args[0] + "&6!");
	}
}
