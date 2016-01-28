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
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class WarpCMD extends CommandModule
{
	public WarpCMD()
	{
		super(new String[] {"warp"}, 0, 1, MultiPlayer.OTHER);
	}
	
	public void performCommand(Player player, String[] args) 
	{
		if(args.length < 1)
		{
			String warps = TheBasics.getDataConfig().getConfig().getConfigurationSection("Servers.Warps").getKeys(false)
					.toString().replace("[", "").replace("]", "");
			
			BasicUtils.sendMessage(player, "&6Warps: &7" + warps + "&6.");
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
				
				BasicUtils.getData(player).initTeleport(loc, "&6the warp &7" + args[0]);
			}
		}
	}
	
	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}
}