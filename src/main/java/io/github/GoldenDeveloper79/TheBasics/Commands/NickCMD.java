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

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class NickCMD extends CommandModule
{
	public NickCMD() 
	{
		super(new String[] {"nick"}, 1, 1, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args)
	{
		if(TheBasics.getGeneralConfig().getBoolean("Nickname.Enabled"))
		{
			String name = args[0];
			
			if(name.length() <= TheBasics.getGeneralConfig().getDouble("Nickname.MaxLength") && TheBasics.getGeneralConfig().getDouble("Nickname.MinLength") >= name.length())
			{
				player.setDisplayName(ChatColor.translateAlternateColorCodes('&', args[0]));
				BasicUtils.sendMessage(player, "&6You changed your displayname to " + args[0] + "&6.");
			}else
			{
				BasicUtils.sendMessage(player, "&cPlease specify a valid nickname!");
			}
		}else
		{
			BasicUtils.sendMessage(player, "&cChanging your nickname is disabled on this server!");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}
}
