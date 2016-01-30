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
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Utils.BasicUtils;

public class UnbanCMD extends CommandModule
{
	public UnbanCMD() 
	{
		super(new String[] {"unban"}, 1, 1, MultiPlayer.OTHER);
	}

	@SuppressWarnings("deprecation")
	public void performCommand(Player player, String[] args) 
	{
		OfflinePlayer player2 = Bukkit.getOfflinePlayer(args[0]);
		
		if(player2 != null)
		{
			if(player2.isBanned())
			{
				Bukkit.getBanList(Type.NAME).pardon(args[0]);
				
				BasicUtils.sendMessage(player, BasicUtils.getMessage("UnbanSender").replace("%p", args[0]));
				BasicUtils.notify("TheBasics.Unban.Notify", BasicUtils.getMessage("UnbanNotify").replace("%p", player.getName()).replace("%p2", args[0]));
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("PlayerNotBanned"));
			}
		}else
		{
			BasicUtils.sendMessage(player, BasicUtils.getMessage("PlayerNeverPlayed"));
		}
	}
	
	@SuppressWarnings("deprecation")
	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		OfflinePlayer player2 = Bukkit.getOfflinePlayer(args[0]);
		
		if(player2 != null)
		{
			if(player2.isBanned())
			{
				Bukkit.getBanList(Type.NAME).pardon(args[0]);
				
				BasicUtils.sendMessage(console, BasicUtils.getMessage("UnbanSender").replace("%p", args[0]));
				BasicUtils.notify("TheBasics.Unban.Notify", BasicUtils.getMessage("UnbanNotify").replace("%p", console.getName()).replace("%p2", args[0]));
			}else
			{
				BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerNotBanned"));
			}
		}else
		{
			BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerNeverPlayed"));
		}
	}
}
