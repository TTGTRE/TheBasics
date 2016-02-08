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
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class WarpCMD extends CommandModule
{
	public WarpCMD()
	{
		super(new String[] {"warp"}, 0, 1, MultiPlayer.OTHER);
	}
	
	public void performCommand(final Player player, final String[] args) 
	{
		if(args.length < 1)
		{
			if(TheBasics.getDataConfig().contains("Servers.Warps"))
			{
				String warps = TheBasics.getDataConfig().getConfigurationSection("Servers.Warps").getKeys(false)
						.toString().replace("[", "").replace("]", "");
				
				BasicUtils.sendMessage(player, "&6Warps: &7" + warps + "&6.");
			}else
			{
				BasicUtils.sendMessage(player, "&6Warps:.");
			}
		}else if(args.length == 1)
		{
			String root = "Servers.Warps." + args[0].toLowerCase();
			
			if(TheBasics.getDataConfig().contains(root))
			{
				World world = Bukkit.getWorld(TheBasics.getDataConfig().getString(root + ".World"));
				double x = TheBasics.getDataConfig().getDouble(root + ".X");
				double y = TheBasics.getDataConfig().getDouble(root + ".Y");
				double z = TheBasics.getDataConfig().getDouble(root + ".Z");
				double yaw = TheBasics.getDataConfig().getDouble(root + ".Yaw");
				double pitch = TheBasics.getDataConfig().getDouble(root + ".Pitch");
				
				Location loc = new Location(world, x, y, z, (float) yaw, (float) pitch);
				
				if(!BasicUtils.getData(player).initTeleport(loc, "&6the warp &7" + args[0]))
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("CombatTagNoTeleport"));
				}
			}
		}
	}
	
	public void performCommand(final ConsoleCommandSender console, final String[] args)
	{
		BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
	}
	
	public void performCommand(final CommandSender sender, final String[] args){}
}