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
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.Registery;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.API.BasicEconomy;
import io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions;
import io.github.GoldenDeveloper79.TheBasics.Modules.ConfigModule;
import io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule;

public abstract class PlayerBase extends ConfigModule implements BasicEconomy, BasicPermissions
{
	private Player player;
	
	public PlayerBase(Player player)
	{
		super(new File("plugins/TheBasics/Players/" + player.getUniqueId().toString() + ".yml"));
		
		this.player = player;
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
	
	public double getBalance(OfflinePlayer player)
	{
		return getDouble("Balance");
	}

	public double getStartingBalance() 
	{
		return TheBasics.getGeneralConfig().getDouble("StartingBalance");
	}

	public double getMaxLoanAmount()
	{
		return TheBasics.getGeneralConfig().getDouble("Loaning.MaxAmount");
	}
	
	public void setBalance(OfflinePlayer player, double amount) 
	{
		set("Balance", amount);
	}

	public void resetBalance(OfflinePlayer player) 
	{
		set("Balance", getStartingBalance());
	}

	public void depositBalance(OfflinePlayer player, double amount)
	{
		set("Balance", getBalance(player) + amount);
	}

	public boolean withdrawBalance(OfflinePlayer player, double amount) 
	{
		if(hasBalance(player, amount))
		{
			set("Balance", getBalance(player) - amount);
			
			return true;
		}
		
		return false;
	}

	public boolean hasBalance(OfflinePlayer player, double amount) 
	{
		if(canLoan())
		{
			if((getBalance(player) - amount) >= getMaxLoanAmount())
			{
				return true;
			}
		}else 
		{
			if((getBalance(player) - amount) >= 0)
			{
				return true;
			}
		}
		
		return false;
	}

	public boolean loanAmount(OfflinePlayer player, double amount)
	{
		return withdrawBalance(player, amount);
	}
	
	public boolean canLoan()
	{	
		return TheBasics.getGeneralConfig().getBoolean("Loaning.Enabled");
	}
	
	public GroupModule getGroup(String groupName) 
	{
		if(Registery.groups.containsKey(groupName))
		{
			return Registery.groups.get(groupName);
		}
		
		return null;
	}

	public GroupModule getPlayerGroup(Player player)
	{
		return getGroup(getString("Group"));
	}
	
	public GroupModule getDefaultGroup()
	{
		for(GroupModule group : Registery.groups.values())
		{
			if(group.isDefault())
			{
				return group;
			}
		}
		
		return null;
	}
	
	public boolean addPlayerToGroup(Player player, GroupModule group) 
	{
		if(!playerInGroup(player, group))
		{
			group.getPlayers().add(player.getName());
			
			return true;
		}
		
		return false;
	}

	public boolean removePlayerFromGroup(Player player, GroupModule group) 
	{
		if(playerInGroup(player, group))
		{
			group.getPlayers().remove(player.getName());
			
			return true;
		}
		
		return false;
	}

	public boolean playerInGroup(Player player, GroupModule group) 
	{
		return group.getPlayers().contains(player.getName());
	}
	
	public boolean groupHasPermission(GroupModule group, String permission)
	{
		return group.getPermissions().contains(permission);
	}
	
	public boolean addPermissionToGroup(GroupModule group, String permission)
	{
		if(!groupHasPermission(group, permission))
		{
			group.addPermission(permission);
			
			return true;
		}
		
		return false;
	}

	public boolean removePermissionFromGroup(GroupModule group, String permission) 
	{
		if(groupHasPermission(group, permission))
		{
			group.removePermission(permission);
			
			return true;
		}
		
		return false;
	}

	public boolean groupExist(String name)
	{
		return Registery.groups.containsKey(name);
	}

	public boolean createGroup(String name) 
	{
		if(!groupExist(name))
		{
			ConfigModule conf = TheBasics.getGroupConfig();
			
			conf.set("Groups." + name + ".Default", false);
			conf.set("Groups." + name + ".Prefix", "[" + name + "] ");
			conf.set("Groups." + name + ".Permissions", new String[] {});
			
			return true;
		}
		
		return false;
	}
	
	public void setInheritance(GroupModule to, GroupModule from) 
	{
		to.getPermissions().addAll(from.getPermissions());
	}
}
