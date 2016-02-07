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
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class TeleportPositionCMD extends CommandModule
{
	//tppos x y z
	//tppos world x y z
	public TeleportPositionCMD()
	{
		super(new String[] {"teleportposition", "tppos"}, 3, 4, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args)
	{
		World world = player.getWorld();
		double x = 0, y = 0, z = 0;
		
		if(args.length == 3)
		{
			try
			{
				x = Double.parseDouble(args[0]);
				y = Double.parseDouble(args[1]);
				z = Double.parseDouble(args[2]);
			}catch(NumberFormatException e)
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("InvalidValue"));
				return;
			}
		}else if(args.length == 4)
		{
			world = Bukkit.getWorld(args[0]);
			
			if(world == null)
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("InvalidValue"));
				return;
			}
			
			try
			{
				x = Double.parseDouble(args[1]);
				y = Double.parseDouble(args[2]);
				z = Double.parseDouble(args[3]);
			}catch(NumberFormatException e)
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("InvalidValue"));
				return;
			}
		}
		
		Location loc = new Location(world, x, y, z);
		
		if(loc != null)
		{
			if(!BasicUtils.getData(player).initTeleport(loc, "world: " + world.getName() + " x: " + x + " y: " + y + " z: " + z))
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("CombatTagNoTeleport"));
			}
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
	}
}
