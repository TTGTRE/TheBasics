/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Levi Pawlak (GoldenDeveloper79)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.on
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
					BasicUtils.sendMessage(player, "&cThat home does not exist!");
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
			
			data.initTeleport(loc, "&6your home &7" + home);
		}else
		{
			String homes = data.getConfig().getConfigurationSection("Home").getKeys(false)
					.toString().replace("[", "").replace("]", "");
			
			BasicUtils.sendMessage(player, "&6Homes: &7" + homes + "&6.");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}
}
