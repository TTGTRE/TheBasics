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
package io.github.GoldenDeveloper79.TheBasics.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.GoldenDeveloper79.TheBasics.Registery;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Utils.BasicUtils;

public abstract class PlayerBase
{
	private Player player;
	private File file;
	private FileConfiguration config;
	private boolean afk = false;
	private boolean vanished = false;
	private boolean muted = false;
	
	public PlayerBase(Player player)
	{
		this.player = player;
		
		file = new File("plugins/TheBasics/Players/" + player.getUniqueId().toString() + ".yml");
	
		if(!file.exists())
		{
			try 
			{
				file.createNewFile();
			} catch (IOException e) {}
		}
		
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public void initTeleport(Location loc, String locName)
	{
		if(loc != null)
		{
			if(player.hasPermission("TheBasics.Teleport.Override"))
			{
				player.teleport(loc);
				BasicUtils.sendMessage(player, BasicUtils.getMessage("TeleportToLocation").replace("%a", locName));
			}else
			{
				int delay = TheBasics.getGeneralConfig().getInt("TeleportDelay");
				
				if(delay > 0)
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("TeleportInitialize").replace("%t", String.valueOf(delay)));
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
									BasicUtils.sendMessage(player, BasicUtils.getMessage("TeleportToLocation").replace("%a", locName));;
									
									this.cancel();
									return;
								}else
								{
									BasicUtils.sendMessage(player, BasicUtils.getMessage("TeleportTimeRemaing").replace("%t", String.valueOf(delay - counter)));
								}
							}
						}
					}.runTaskTimer(TheBasics.getPlugin(), 20L, 20L);
				}else
				{
					player.teleport(loc);
					BasicUtils.sendMessage(player, BasicUtils.getMessage("TeleportToLocation").replace("%a", locName));
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

	public boolean isMuted() 
	{
		return muted;
	}

	public void setMuted(boolean muted) 
	{
		this.muted = muted;
	}
	
	/*
	 * Sets a value to the specified key.
	 */
	public void set(String key, Object value)
	{
		config.set(key, value);

		try 
		{
			config.save(file);
		} catch (IOException e) 
		{
			TheBasics.getLog().severe("Could not save the config for " + file.getName() + "!");
		}
	}

	/*
	 * Sets a value to the specified key when the value does not equal the same in the config.
	 */
	public void update(String key, Object value)
	{
		if(!contains(key) || !get(key).equals(value))
		{
			set(key, value);
		}
	}

	/*
	 * Gets an object from a specified key.
	 */
	public Object get(String key)
	{
		return config.get(key);
	}

	/*
	 * Gets a string from a specified key.
	 */
	public String getString(String key)
	{
		return config.getString(key);
	}

	/*
	 * Gets an integer from a specified key.
	 */
	public int getInt(String key)
	{
		return config.getInt(key);
	}

	/*
	 * Gets a double from a specified key.
	 */
	public double getDouble(String key)
	{
		return config.getDouble(key);
	}

	/*
	 * Gets a boolean from a specified key.
	 */
	public boolean getBoolean(String key)
	{
		return config.getBoolean(key);
	}

	/*
	 * Checks if a path exist from a specified key.
	 */
	public boolean contains(String key)
	{
		return config.contains(key);
	}

	/*
	 * Gets a string list from a specified key.
	 */
	public List<String> getStringList(String key)
	{
		return config.getStringList(key);
	}

	/*
	 * Gets the file associated with the configuration.
	 */
	public File getFile()
	{
		return file;
	}

	/*
	 * Gets the configuration.
	 */
	public FileConfiguration getConfig() 
	{
		return config;
	}
}
