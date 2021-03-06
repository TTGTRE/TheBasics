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
import org.bukkit.command.CommandSender;
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

	public void performCommand(final Player player, final String[] args)
	{
		if(TheBasics.getGeneralConfig().getBoolean("Nickname.Enabled"))
		{
			String name = args[0];
			
			if(name.toCharArray().length <= TheBasics.getGeneralConfig().getDouble("Nickname.MaxLength") && TheBasics.getGeneralConfig().getDouble("Nickname.MinLength") <= name.toCharArray().length)
			{
				player.setDisplayName(ChatColor.translateAlternateColorCodes('&', args[0]));
				BasicUtils.sendMessage(player, BasicUtils.getMessage("Nick").replace("%a", args[0]));
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("NickInvalid"));
			}
		}else
		{
			BasicUtils.sendMessage(player, BasicUtils.getMessage("NickDisabled"));
		}
	}

	public void performCommand(final ConsoleCommandSender console, final String[] args) 
	{
		BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
	}
	
	public void performCommand(final CommandSender sender, final String[] args){}
}
