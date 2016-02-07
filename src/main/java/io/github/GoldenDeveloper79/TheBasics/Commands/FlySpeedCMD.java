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
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class FlySpeedCMD extends CommandModule
{
	public FlySpeedCMD()
	{
		super(new String[] {"flyspeed"}, 1, 2, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args)
	{
		if(args.length == 1)
		{
			try
			{
				float speed = Float.parseFloat(args[0]);
				
				if(speed <= 20 && speed >= -20)
				{
					player.setFlySpeed(speed / 20);
					
					BasicUtils.sendMessage(player, BasicUtils.getMessage("FlySpeedChange").replace("%a", args[0]));
				}else
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("InvalidValue"));
				}
			}catch(NumberFormatException e)
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("InvalidValue"));
			}
		}else
		{
			Player receiver = Bukkit.getPlayer(args[0]);
			
			if(receiver != null)
			{
				try
				{
					float speed = Float.parseFloat(args[1]);
					
					if(speed <= 20 && speed >= -20)
					{
						receiver.setFlySpeed(speed / 20);
						
						BasicUtils.sendMessage(player, BasicUtils.getMessage("FlySpeedChangeSender").replace("%p", args[0]).replace("%a", args[1]));
						BasicUtils.sendMessage(player, BasicUtils.getMessage("FlySpeedChangeReceiver").replace("%p", player.getName()).replace("%a", args[1]));
					}else
					{
						BasicUtils.sendMessage(player, BasicUtils.getMessage("InvalidValue"));
					}
				}catch(NumberFormatException e)
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("InvalidValue"));
				}
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("PlayerOffline"));
			}
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
	}
}
