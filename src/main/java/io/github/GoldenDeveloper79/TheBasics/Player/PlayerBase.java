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
package io.github.GoldenDeveloper79.TheBasics.Player;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Registery;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Modules.ConfigModule;

public abstract class PlayerBase extends ConfigModule
{
	private Player player;
	private boolean afk = false;
	private boolean vanished = false;
	
	public PlayerBase(Player player)
	{
		super(new File("plugins/TheBasics/Players/" + player.getUniqueId().toString() + ".yml"));
		
		this.player = player;
	}
	
	public void initTeleport(Location loc, String locName)
	{
		if(loc != null)
		{
			if(player.hasPermission("TheBasics.Teleport.Override"))
			{
				player.teleport(loc);
				BasicUtils.sendMessage(player, "&6You have teleported to " + locName + ".");
			}else
			{
				int delay = TheBasics.getGeneralConfig().getInt("TeleportDelay");
				
				if(delay > 0)
				{
					BasicUtils.sendMessage(player, "&6You will teleport in &7" + delay + "s&6.");
					Registery.teleportQue.add(player.getName());
					
					new BukkitRunnable()
					{
						int counter = 0;
						
						public void run()
						{
							if(Registery.teleportQue.contains(player.getName()))
							{
								counter++;
								
								if((delay - counter) <= 0)
								{
									player.teleport(loc);
									BasicUtils.sendMessage(player, "&6You have teleported to " + locName + ".");;
									
									this.cancel();
									return;
								}else
								{
									BasicUtils.sendMessage(player, "&7" + (delay-counter) + "s&6...");
								}
							}
						}
					}.runTaskTimer(TheBasics.getPlugin(), 20L, 20L);
				}else
				{
					player.teleport(loc);
					BasicUtils.sendMessage(player, "&6You have teleported to "  + locName + ".");
				}
			}
		}
	}
	
	public boolean homeExist(String name)
	{
		if(contains("Home." + name))
		{
			return true;
		}
		
		return false;
	}
	
	public void createHome(String name)
	{
		Location loc = player.getLocation();
		
		set("Home." + name + ".World", loc.getWorld().getName());
		set("Home." + name + ".X", loc.getX());
		set("Home." + name + ".Y", loc.getY());
		set("Home." + name + ".Z", loc.getZ());
		set("Home." + name + ".Yaw", loc.getYaw());
		set("Home." + name + ".Pitch", loc.getPitch());
	}
	
	public boolean removeHome(String name)
	{
		if(homeExist(name))
		{
			set("Home." + name + ".World", null);
			set("Home." + name + ".X", null);
			set("Home." + name + ".Y", null);
			set("Home." + name + ".Z", null);
			set("Home." + name + ".Yaw", null);
			set("Home." + name + ".Pitch", null);
			set("Home." + name, null);
			
			return true;
		}
		
		return false;
	}
	
	public double getHomes()
	{
		return getConfig().getConfigurationSection("Home").getKeys(false).size();
	}
	
	public double getMaxHomes()
	{
		if(player.hasPermission("thebasics.home.unlimited"))
		{
			return Double.MAX_VALUE;
		}else if(TheBasics.getGeneralConfig().contains("Homes." + TheBasics.getPermissions().getPlayerGroup(player).getGroupName()))
		{
			double maxHomes = TheBasics.getGeneralConfig().getDouble("Homes." + TheBasics.getPermissions().getPlayerGroup(player).getGroupName());
			
			return maxHomes;
		}else
		{
			return Double.MAX_VALUE;
		}
	}

	public boolean isAfk()
	{
		return afk;
	}

	public void setAfk(boolean afk) 
	{
		this.afk = afk;
	}

	public boolean isVanished()
	{
		return vanished;
	}

	public void setVanished(boolean vanished) 
	{
		this.vanished = vanished;
	}
}
