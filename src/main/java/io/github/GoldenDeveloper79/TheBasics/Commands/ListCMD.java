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

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Registery;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class ListCMD extends CommandModule
{
	public ListCMD()
	{
		super(new String[] {"list"}, 0, 1, MultiPlayer.OTHER);
	}

	public void performCommand(final Player player, final String[] args)
	{
		int page = 0;
		
		if(args.length == 1)
		{
			try
			{
				page = Integer.parseInt(args[0]);
				
				if(page > getMaxPages())
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("ListPage"));
					return;
				}
			}catch(NumberFormatException e)
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("ListPage"));
				return;
			}
		}
		
		for(String msg : formatList(page))
		{
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
		}
	}

	public void performCommand(final ConsoleCommandSender console, final String[] args)
	{
		int page = 0;
		
		if(args.length == 1)
		{
			try
			{
				page = Integer.parseInt(args[0]);
				
				if(page > getMaxPages())
				{
					BasicUtils.sendMessage(console, BasicUtils.getMessage("ListPage"));
					return;
				}
			}catch(NumberFormatException e)
			{
				BasicUtils.sendMessage(console, BasicUtils.getMessage("ListPage"));
				return;
			}
		}
		
		for(String msg : formatList(page))
		{
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
		}
	}
	
	public void performCommand(final CommandSender sender, final String[] args){}
	
	private List<String> formatList(int page)
	{
		List<String> formattedList = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		
		formattedList.add(TheBasics.getTextConfig().getString("List.Format.Top",  "&6&l----------[List - %p]----------").replace("%p", String.valueOf(page + 1)));
		
		for(PlayerData data : Registery.players.values())
		{
			sb.append(", " + TheBasics.getPermissions().getPlayerGroup(data.getPlayer()).getPrefix() + " " + data.getPlayer().getDisplayName());
		}

		formattedList.add(sb.toString().replaceFirst(", ", "") + ".");
		formattedList.add(TheBasics.getTextConfig().getString("List.Format.Bottom",  "&6&l---------------------------"));
		
		return formattedList;
	}
	
	private int getMaxPages()
	{
		int maxPlayersPerPage = TheBasics.getTextConfig().getInt("List.PerPage", 15);
		int playersOnline = Registery.players.size();
		
		if(playersOnline != 0)
		{
			return (int) (playersOnline / maxPlayersPerPage);
		}else
		{
			return 1;
		}
	}
}
