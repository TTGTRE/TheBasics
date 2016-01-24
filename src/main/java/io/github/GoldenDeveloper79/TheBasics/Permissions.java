package io.github.GoldenDeveloper79.TheBasics;

import org.bukkit.OfflinePlayer;

import io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions;
import io.github.GoldenDeveloper79.TheBasics.Modules.ConfigModule;
import io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class Permissions implements BasicPermissions 
{
	public GroupModule getGroup(String groupName) 
	{
		if(Registery.groups.containsKey(groupName))
		{
			return Registery.groups.get(groupName);
		}
		
		return null;
	}

	public GroupModule getPlayerGroup(OfflinePlayer player)
	{
		if(player.isOnline())
		{
			return Registery.groups.get(BasicUtils.getData(player.getName()).get("Group"));
		}else
		{
			return getGroup(BasicUtils.getConfig(player.getName()).getString("Group"));
		}
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
	
	public boolean addPlayerToGroup(OfflinePlayer player, GroupModule group) 
	{
		if(!playerInGroup(player, group))
		{
			group.getPlayers().add(player.getName());
			removePlayerFromGroup(player, getGroup(BasicUtils.getConfig(player.getName()).getString("Group")));
			
			if(player.isOnline())
			{
				PlayerData data = BasicUtils.getData(player.getName());
				data.set("Group", group.getGroupName());
			}else
			{
				BasicUtils.getConfig(player.getName()).set("Group", group.getGroupName());
				BasicUtils.saveFile(player.getName());
			}
			
			return true;
		}
		
		return false;
	}

	public boolean removePlayerFromGroup(OfflinePlayer player, GroupModule group) 
	{
		if(playerInGroup(player, group))
		{
			group.getPlayers().remove(player.getName());
			
			if(player.isOnline())
			{
				PlayerData data = BasicUtils.getData(player.getName());
				data.set("Group", TheBasics.getPermissions().getDefaultGroup().getGroupName());
			}else
			{
				BasicUtils.getConfig(player.getName()).set("Group", TheBasics.getPermissions().getDefaultGroup().getGroupName());
				BasicUtils.saveFile(player.getName());
			}
			
			return true;
		}
		
		return false;
	}

	public boolean playerInGroup(OfflinePlayer player, GroupModule group) 
	{
		if(player.isOnline())
		{
			return BasicUtils.getData(player.getName()).getString("Group").equalsIgnoreCase(group.getGroupName());
		}else
		{
			return BasicUtils.getConfig(player.getName()).getString("Group").equalsIgnoreCase(group.getGroupName());
		}
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
			
			new GroupModule(name);
			
			return true;
		}
		
		return false;
	}
	
	public void setInheritance(GroupModule to, GroupModule from) 
	{
		to.getPermissions().addAll(from.getPermissions());
	}
}
