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

	public void performCommand(Player player, String[] args) 
	{
		if(args[0].equalsIgnoreCase("sun") || args[0].equalsIgnoreCase("s"))
		{
			player.getWorld().setWeatherDuration(0);
			player.getWorld().setThunderDuration(0);
			
			for(Player players : player.getWorld().getPlayers())
			{
				players.setPlayerWeather(WeatherType.CLEAR);
			}
			
			BasicUtils.sendMessage(player, "&6You changed the weather to &7sun&6.");
		}else if(args[0].equalsIgnoreCase("rain") || args[0].equalsIgnoreCase("r"))
		{
			player.getWorld().setWeatherDuration(Integer.MAX_VALUE);
			player.getWorld().setThunderDuration(Integer.MAX_VALUE);
			
			for(Player players : player.getWorld().getPlayers())
			{
				players.setPlayerWeather(WeatherType.DOWNFALL);
			}
			
			BasicUtils.sendMessage(player, "&6You changed the weather to &7rain&6.");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}	
}
