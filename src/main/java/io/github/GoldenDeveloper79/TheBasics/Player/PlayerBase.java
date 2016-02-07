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

import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
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
	private boolean muted = false;
	private boolean combatTagged = false;
	private int combatTagTimer;
	private Player lastMessaged;
	private Location lastLocation;
	private List<String> ignoredPlayers;

	public PlayerBase(Player player)
	{
		super("/Players/" + player.getUniqueId().toString() + ".yml");
		
		this.player = player;

		ignoredPlayers = getStringList("IgnoredPlayer");
	}

	public boolean initTeleport(final Location loc, final String locName)
	{
		if((!combatTagged && TheBasics.getGeneralConfig().getBoolean("CombatTag.Enabled")) || player.hasPermission("TheBasics.CombatTag.Never"))
		{
			if(loc != null)
			{
				lastLocation = player.getLocation();
				final int delay = TheBasics.getGeneralConfig().getInt("TeleportDelay");

				if(!loc.getChunk().isLoaded())
				{
					loc.getChunk().load();
				}
				
				if(player.hasPermission("TheBasics.Teleport.Override"))
				{
					player.teleport(loc);
					BasicUtils.sendMessage(player, BasicUtils.getMessage("TeleportToLocation").replace("%a", locName));
				}else
				{
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
				
				return true;
			}
		}
		
		return false;
	}
	
	public void setCombatTagged()
	{
		if(!player.hasPermission("TheBasics.CombatTag.Never"))
		{
			if(TheBasics.getGeneralConfig().getBoolean("CombatTag.Enabled"))
			{
				if(combatTagged && combatTagTimer != 0)
				{
					Bukkit.getScheduler().cancelTask(combatTagTimer);
				}else
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("CombatTagged"));
				}
				
				combatTagged = true;
				
				combatTagTimer = new BukkitRunnable()
				{
					public void run()
					{
						combatTagged = false;
						BasicUtils.sendMessage(player, BasicUtils.getMessage("UnCombatTagged"));
					}
				}.runTaskLater(TheBasics.getPlugin(), TheBasics.getGeneralConfig().getInt("CombatTag.Time") * 20L).getTaskId();
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
		if(player.hasPermission("thebasics.sethome.unlimited"))
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

	public void setMuted(Date time, String reason) 
	{
		setIsMuted(true);
		
		set("Muted.Is", true);
		set("Muted.Time", time.getTime());
		set("Muted.Reason", reason);
	}
	
	public void setIsMuted(boolean muted)
	{
		this.muted = muted;
	}
	
	public void setUnMuted()
	{
		this.muted = false;
		
		set("Muted.Is", false);
		set("Muted.Time", null);
		set("Muted.Reason", null);
	}
	
	public Player getLastMessaged()
	{
		return lastMessaged;
	}

	public void setLastMessaged(Player lastMessaged)
	{
		this.lastMessaged = lastMessaged;
	}
	
	public List<String> getIgnoredPlayers()
	{
		return ignoredPlayers;
	}
	
	public Location getLastLocation()
	{
		return lastLocation;
	}
	
	//May use this eventually.
	public void loadDefaults() {}
}
