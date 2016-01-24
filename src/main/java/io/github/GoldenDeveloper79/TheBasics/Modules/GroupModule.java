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
			this.config = TheBasics.getGroupConfig().getConfig().getConfigurationSection("Groups." + name);
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
