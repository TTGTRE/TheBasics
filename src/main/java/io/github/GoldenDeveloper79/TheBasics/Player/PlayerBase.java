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

import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Modules.ConfigModule;

public abstract class PlayerBase extends ConfigModule
{
	private Player player;
	private boolean afk = false;
	
	public PlayerBase(Player player)
	{
		super(new File("plugins/TheBasics/Players/" + player.getUniqueId().toString() + ".yml"));
		
		this.player = player;
		
		TheBasics.getLog().severe(getString("Group"));
	}
	
	public boolean homeExist(String name)
	{
		if(contains("Home." + name))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean createHome(String name)
	{
		if(!homeExist(name))
		{
			Location loc = player.getLocation();
			
			set("Home."+ name + ".World", loc.getWorld().getName());
			set("Home."+ name + ".X", loc.getX());
			set("Home."+ name + ".Y", loc.getY());
			set("Home."+ name + ".Z", loc.getZ());
			set("Home."+ name + ".Yaw", loc.getYaw());
			set("Home."+ name + ".Pitch", loc.getPitch());
			
			return true;
		}
		
		return false;
	}


	public boolean isAfk()
	{
		return afk;
	}

	public void setAfk(boolean afk) 
	{
		this.afk = afk;
	}
}
