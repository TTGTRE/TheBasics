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

public class IgnoreCMD extends CommandModule
{
	public IgnoreCMD() 
	{
		super(new String[] {"ignore"}, 1, 1, MultiPlayer.ALWAYS);
	}

	public void performCommand(final Player player, final String[] args)
	{
		PlayerData sender = BasicUtils.getData(player);
		Player ignored = Bukkit.getPlayer(args[0]);
		
		if(!ignored.equals(player))
		{
			if(!sender.getIgnoredPlayers().contains(ignored.getUniqueId().toString()))
			{
				sender.getIgnoredPlayers().add(ignored.getUniqueId().toString());
				sender.set("IgnoredPlayers", sender.getIgnoredPlayers());
				
				BasicUtils.sendMessage(player, BasicUtils.getMessage("IgnoredSender").replace("%p", args[0]));
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("AlreadyIgnored"));
			}
		}else
		{
			BasicUtils.sendMessage(player, BasicUtils.getMessage("IgnoreSelf"));
		}
	}

	public void performCommand(final ConsoleCommandSender console, final String[] args)
	{
		BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
	}
	
	public void performCommand(final CommandSender sender, final String[] args){}
}
