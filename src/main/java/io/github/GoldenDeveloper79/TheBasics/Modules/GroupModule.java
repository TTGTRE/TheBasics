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
	private boolean isDefault = false;
	
	public GroupModule(String name)
	{
		try
		{
			this.groupName = name;
			this.config = TheBasics.getGroupConfig().getConfig().getConfigurationSection("Groups." + name);
			this.prefix = config.getString("Prefix");
			this.players = new ArrayList<String>();
			this.permissions = config.getStringList("Permissions");
		
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
