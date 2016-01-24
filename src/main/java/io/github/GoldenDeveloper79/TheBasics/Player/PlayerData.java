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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.Registery;

public class PlayerData extends PlayerBase
{
	private Player player;
	private String name;
	private UUID uuid;
	
	public PlayerData(Player player)
	{
		super(player);
		
		this.player = player;
	
		this.name = player.getName();
		this.uuid = player.getUniqueId();
		
		Registery.players.put(name, this);
		
		Location loc = player.getLocation();
		
		if(!Bukkit.getOfflinePlayer(player.getUniqueId()).hasPlayedBefore())
		{
			set("Name", player.getName());
			set("LastLogin", System.currentTimeMillis());
			set("LastLogOut", 0);
			set("Balance", getStartingBalance());

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
			
			set("Banned.Is", false);
			set("Banned.Time", 0);
			set("Banned.Reason", "none");
			
			set("Group", getDefaultGroup());
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
		
		getPlayerGroup(player).getPlayers().add(name);
	}
	
	public void quit()
	{
		getPlayerGroup(player).getPlayers().remove(name);
		update("LastLogOut", System.currentTimeMillis());
		
		Location loc = player.getLocation();
		
		update("LastLocation.World", loc.getWorld().getName());
		update("LastLocation.X", loc.getX());
		update("LastLocation.Y", loc.getY());
		update("LastLocation.Z", loc.getZ());
		update("LastLocation.Yaw", loc.getYaw());
		update("LastLocation.Pitch", loc.getPitch());
		
		Registery.players.remove(name);
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
