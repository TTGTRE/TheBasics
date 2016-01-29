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

import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class SetWarpCMD extends CommandModule
{
	public SetWarpCMD()
	{
		super(new String[] {"setwarp"}, 1, 1, MultiPlayer.OTHER);
	}
	
	public void performCommand(Player player, String[] args) 
	{
		Location loc = player.getLocation();
		String root = "Servers.Warps." + args[0].toLowerCase();
		
		TheBasics.getDataConfig().set(root + ".World", loc.getWorld().getName());
		TheBasics.getDataConfig().set(root + ".X", loc.getX());
		TheBasics.getDataConfig().set(root + ".Y", loc.getY());
		TheBasics.getDataConfig().set(root + ".Z", loc.getZ());
		TheBasics.getDataConfig().set(root + ".Yaw", loc.getYaw());
		TheBasics.getDataConfig().set(root + ".Pitch", loc.getPitch());
		
		BasicUtils.sendMessage(player, BasicUtils.getMessage("SetWarp").replace("%a", args[0]));
	}
	
	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
	}
}
