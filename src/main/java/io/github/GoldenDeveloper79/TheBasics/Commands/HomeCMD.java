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
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class HomeCMD extends CommandModule
{
	public HomeCMD() 
	{
		super(new String[] {"home"}, 0, 1, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args) 
	{
		PlayerData data = BasicUtils.getData(player);
		
		if(data.getHomes() == 1 || (data.getHomes() > 1 && args.length == 1))
		{
			String home = "Default";
			
			if(args.length ==  1)
			{
				if(data.homeExist(args[0]))
				{
					home = args[0];
				}else
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("HomeExist"));
					return;
				}
			}
		
			World world = Bukkit.getWorld(data.getString("Home." + home + ".World"));
			double x = data.getDouble("Home." + home + ".X");
			double y = data.getDouble("Home." + home + ".Y");
			double z = data.getDouble("Home." + home + ".Z");
			double yaw = data.getDouble("Home." + home + ".Yaw");
			double pitch = data.getDouble("Home." + home + ".Pitch");
			
			Location loc = new Location(world, x, y, z, (float) yaw, (float) pitch);
			
			if(!data.initTeleport(loc, "&6your home &7" + home))
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("CombatTagNoTeleport"));
			}
		}else
		{
			String homes = data.getConfig().getConfigurationSection("Home").getKeys(false)
					.toString().replace("[", "").replace("]", "");
			
			BasicUtils.sendMessage(player, BasicUtils.getMessage("Homes").replace("%a", homes));
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
	}
}
