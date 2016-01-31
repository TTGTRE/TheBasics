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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import net.md_5.bungee.api.ChatColor;

public class InfoCMD extends CommandModule
{
	public InfoCMD()
	{
		super(new String[] {"info"}, 0, 1, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args) 
	{
		if(args.length < 1)
		{
			List<String> information = getInfo(player.getName());
			
			if(!information.isEmpty())
			{
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', TheBasics.getTextConfig().getString("Info.Format.Top")));
				
				for(String info : information)
				{
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', info));
				}
				
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', TheBasics.getTextConfig().getString("Info.Format.Bottom")));
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("PlayerNeverPlayed"));
			}
		}else
		{
			if(player.hasPermission("TheBasics.Info.Others"))
			{
				List<String> information = getInfo(args[0]);
				
				if(!information.isEmpty())
				{
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', TheBasics.getTextConfig().getString("Info.Format.Top")));
					
					for(String info : information)
					{
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', info));
					}
					
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', TheBasics.getTextConfig().getString("Info.Format.Bottom")));
				}else
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("PlayerNeverPlayed"));
				}
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("NoPermission"));
			}
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		if(args.length == 1)
		{
			List<String> information = getInfo(args[0]);
			
			if(!information.isEmpty())
			{
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', TheBasics.getTextConfig().getString("Info.Format.Top")));
				
				for(String info : information)
				{
					console.sendMessage(ChatColor.translateAlternateColorCodes('&', info));
				}
				
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', TheBasics.getTextConfig().getString("Info.Format.Bottom")));
			}else
			{
				BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerNeverPlayed"));
			}
		}else
		{
			BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
		}
	}
	
	public List<String> getInfo(String player)
	{
		FileConfiguration config = BasicUtils.getConfig(player);
		List<String> information = new ArrayList<String>();
		DecimalFormat format = new DecimalFormat("###.#");
		
		if(config != null)
		{
			for(String info : TheBasics.getTextConfig().getStringList("Info.List"))
			{
				information.add(info
					.replace("%n", config.getString("Name"))
					.replace("%d", config.getString("Displayname"))
					.replace("%a", config.getString("IP").replace("/", ""))
					.replace("%b", String.valueOf(config.getDouble("Balance")))
					.replace("%g", config.getString("Group"))
					.replace("%p", String.valueOf(config.getDouble("PlayTime") + "m"))
					.replace("%l", String.valueOf(format.format(((System.currentTimeMillis() - config.getDouble("LastLogin")) / 1000) / 60)) + "m")
				);
			}
		}
		
		return information;
	}
}
