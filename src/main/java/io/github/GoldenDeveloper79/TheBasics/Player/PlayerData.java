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
		Registery.players.put(name, this);
		TheBasics.getDataConfig().update("Players." + name, player.getUniqueId().toString());
		
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
		}
		
		//Only will update on quit/join.
		update("Name", player.getName());
		update("IP", player.getAddress().toString());
		update("LastLogin", System.currentTimeMillis());
		
		update("LastLocation.World", loc.getWorld().getName());
		update("LastLocation.X", loc.getX());
		update("LastLocation.Y", loc.getY());
		update("LastLocation.Z", loc.getZ());
		update("LastLocation.Yaw", loc.getYaw());
		update("LastLocation.Pitch", loc.getPitch());
		
		loadPermissions();
	}
	
	public void loadPermissions()
	{
		GroupModule group = TheBasics.getPermissions().getGroup(getString("Group"));
		group.getPlayers().add(name);
		
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
