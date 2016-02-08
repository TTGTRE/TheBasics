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

import org.bukkit.WeatherType;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class WeatherCMD extends CommandModule
{
	public WeatherCMD() 
	{
		super(new String[] {"weather"}, 1, 1, MultiPlayer.OTHER);
	}

	public void performCommand(final Player player, final String[] args) 
	{
		if(args[0].equalsIgnoreCase("sun") || args[0].equalsIgnoreCase("s")|| args[0].equals("c") || args[0].equals("clear"))
		{
			player.getWorld().setWeatherDuration(0);
			player.getWorld().setThunderDuration(0);
			
			for(Player players : player.getWorld().getPlayers())
			{
				players.setPlayerWeather(WeatherType.CLEAR);
			}
			
			BasicUtils.sendMessage(player, BasicUtils.getMessage("WeatherSender").replace("%a", "clear"));
		}else if(args[0].equalsIgnoreCase("rain") || args[0].equalsIgnoreCase("r") || args[0].equals("d") || args[0].equals("downfall"))
		{
			player.getWorld().setWeatherDuration(Integer.MAX_VALUE);
			player.getWorld().setThunderDuration(Integer.MAX_VALUE);
			
			for(Player players : player.getWorld().getPlayers())
			{
				players.setPlayerWeather(WeatherType.DOWNFALL);
			}
			
			BasicUtils.sendMessage(player, BasicUtils.getMessage("WeatherSender").replace("%a", "downfall"));
		}else
		{
			BasicUtils.sendMessage(player, BasicUtils.getMessage("WeatherInvalid"));
		}
	}

	public void performCommand(final ConsoleCommandSender console, final String[] args)
	{
		BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
	}	
	
	public void performCommand(final CommandSender sender, final String[] args){}
}
