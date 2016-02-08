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

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import io.github.GoldenDeveloper79.TheBasics.Registery;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule;

public class PlayerData extends PlayerBase
{
	private Player player;
	private String name;
	private UUID uuid;
	private PermissionAttachment perm;

	public PlayerData(Player player)
	{
		super(player);
		
		this.player = player;
		this.name = player.getName();
		this.uuid = player.getUniqueId();
		
		join();
	}
	
	public void join()
	{
		Registery.players.put(name.toLowerCase(), this);
	
		Location loc = player.getLocation();
		
		if(!contains("Name"))
		{
			set("Name", player.getName());
			set("LastLogin", System.currentTimeMillis());
			set("LastLogOut", 0);
			set("PlayTime", 0);
			set("Balance", TheBasics.getEconomy().getStartingBalance());

			set("LastLocation.World", loc.getWorld().getName());
			set("LastLocation.X", loc.getX());
			set("LastLocation.Y", loc.getY());
			set("LastLocation.Z", loc.getZ());
			set("LastLocation.Yaw", loc.getYaw());
			set("LastLocation.Pitch", loc.getPitch());
			
			//Should be the spawn when the player spawns.
			set("Home.Default.World", loc.getWorld().getName());
			set("Home.Default.X", loc.getX());
			set("Home.Default.Y", loc.getY());
			set("Home.Default.Z", loc.getZ());
			set("Home.Default.Yaw", loc.getYaw());
			set("Home.Default.Pitch", loc.getPitch());

			set("Group", TheBasics.getPermissions().getDefaultGroup().getGroupName());
			
			TheBasics.getDataConfig().set("Players." + name, player.getUniqueId().toString());
		}
		
		for(String name : TheBasics.getDataConfig().getConfigurationSection("Players").getKeys(false))
		{
			if(TheBasics.getDataConfig().getString("Players." + name).equalsIgnoreCase(player.getUniqueId().toString()))
			{
				if(!name.equals(player.getName()))
				{
					TheBasics.getDataConfig().set("Players." + name, null);
				}
			}
		}
		
		//Only will update on quit/join.
		update("Name", player.getName());
		update("Displayname", player.getDisplayName());
		update("IP", player.getAddress().toString());
		update("LastLogin", System.currentTimeMillis());
		
		update("LastLocation.World", loc.getWorld().getName());
		update("LastLocation.X", loc.getX());
		update("LastLocation.Y", loc.getY());
		update("LastLocation.Z", loc.getZ());
		update("LastLocation.Yaw", loc.getYaw());
		update("LastLocation.Pitch", loc.getPitch());
		
		if(!contains("Kits"))
		{
			set("Kits", "");
		}
		
		if(!contains("IgnoreList"))
		{
			set("IgnoreList", new String[] {});
		}
		
		if(contains("Muted.Is") && getBoolean("Muted.Is"))
		{
			setIsMuted(true);
		}
		
		loadPermissions();
	}
	
	public void loadPermissions()
	{
		GroupModule group = TheBasics.getPermissions().getGroup(getString("Group"));
		
		if(!group.getPlayers().contains(name))
		{
			group.getPlayers().add(name);
		}
		
		perm = player.addAttachment(TheBasics.getPlugin());
		
		for(String permission : group.getPermissions())
		{
			if(!permission.contains("-"))
			{
				perm.setPermission(permission.toLowerCase(), true);
			}else if(permission.contains("-"))
			{
				perm.setPermission(permission.toLowerCase(), false);
			}
		}
	}
	
	public void quit()
	{
		update("LastLogOut", System.currentTimeMillis());
		
		Location loc = player.getLocation();
		
		update("LastLocation.World", loc.getWorld().getName());
		update("LastLocation.X", loc.getX());
		update("LastLocation.Y", loc.getY());
		update("LastLocation.Z", loc.getZ());
		update("LastLocation.Yaw", loc.getYaw());
		update("LastLocation.Pitch", loc.getPitch());
	}
	
	public void addPermission(String permission)
	{
		perm.setPermission(permission.toLowerCase(), true);
	}
	
	public void removePermission(String permission)
	{
		perm.setPermission(permission.toLowerCase(), false);
	}
	
	public Player getPlayer()
	{
		return player;
	}

	public String getName() 
	{
		return name;
	}

	public UUID getUuid() 
	{
		return uuid;
	}
}
