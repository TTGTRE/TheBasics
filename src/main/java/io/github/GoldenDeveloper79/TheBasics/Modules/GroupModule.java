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
package io.github.GoldenDeveloper79.TheBasics.Modules;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

import io.github.GoldenDeveloper79.TheBasics.Registery;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;

public class GroupModule
{
	private String groupName;
	private ConfigurationSection config;
	private String prefix;
	private List<String> permissions;
	private List<String> players;
	private List<String> inheritance;
	private boolean isDefault = false;
	
	public GroupModule(String name)
	{
		try
		{
			this.groupName = name;
			this.config = TheBasics.getGroupConfig().getConfigurationSection("Groups." + name);
			this.prefix = config.getString("Prefix");
			this.permissions = config.getStringList("Permissions");
			this.players = new ArrayList<String>();
			
			if(config.contains("Inheritance"))
			{
				this.inheritance = config.getStringList("Inheritance");
			}else
			{
				this.inheritance = new ArrayList<String>();
			}
			
			if(config.contains("Default") && config.getBoolean("Default"))
			{
				this.isDefault = true;
			}
			
			Registery.groups.put(name, this);
		}catch(Exception e)
		{
			TheBasics.getLog().severe("Could not create the group " + name + "! Please check groups.yml!");
		}
	}
	
	public void loadInheritance()
	{
		for(String inherit : inheritance)
		{
			if(Registery.groups.containsKey(inherit))
			{
				GroupModule mod = Registery.groups.get(inherit);
				permissions.addAll(mod.getPermissions());
			}
		}
	}
	
	public void addPermission(String permission)
	{
		permissions.add(permission);
		
		TheBasics.getGroupConfig().set("Groups." + groupName + ".Permissions", permissions);
	}
	
	public void removePermission(String permission)
	{
		permissions.remove(permission);
		
		TheBasics.getGroupConfig().set("Groups." + groupName + ".Permissions", permissions);
	}
	
	public void setPrefix(String prefix)
	{
		this.prefix = prefix;
		
		TheBasics.getGroupConfig().set("Groups." + groupName + ".Prefix", prefix);
	}
	
	public String getGroupName() 
	{
		return groupName;
	}

	public ConfigurationSection getConfig() 
	{
		return config;
	}

	public String getPrefix() 
	{
		return prefix;
	}
	
	public List<String> getPlayers()
	{
		return players;
	}
	
	public List<String> getPermissions()
	{
		return permissions;
	}

	public boolean isDefault()
	{
		return isDefault;
		
	}
}
