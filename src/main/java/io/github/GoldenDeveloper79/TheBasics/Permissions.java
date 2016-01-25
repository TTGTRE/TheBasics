package io.github.GoldenDeveloper79.TheBasics;

import java.util.Collection;
import java.util.Collections;

import org.bukkit.OfflinePlayer;

import io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions;
import io.github.GoldenDeveloper79.TheBasics.Modules.ConfigModule;
import io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class Permissions implements BasicPermissions 
{
	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions#getGroup(java.lang.String)
	 */
	public GroupModule getGroup(String groupName) 
	{
		if(Registery.groups.containsKey(groupName))
		{
			return Registery.groups.get(groupName);
		}
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions#getPlayerGroup(org.bukkit.OfflinePlayer)
	 */
	public GroupModule getPlayerGroup(OfflinePlayer player)
	{
		if(player.isOnline())
		{
			return getGroup(BasicUtils.getData(player.getName()).getString("Group").toLowerCase());
		}else
		{
			return getGroup(BasicUtils.getConfig(player.getName()).getString("Group").toLowerCase());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions#getDefaultGroup()
	 */
	public GroupModule getDefaultGroup()
	{
		for(GroupModule group : getGroups())
		{
			if(group.isDefault())
			{
				return group;
			}
		}
		
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions#getGroups()
	 */
	public Collection<GroupModule> getGroups()
	{
		return Collections.unmodifiableCollection(Registery.groups.values());
	}
	
	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions#addPlayerToGroup(org.bukkit.OfflinePlayer, io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule)
	 */
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

	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions#removePlayerFromGroup(org.bukkit.OfflinePlayer, io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule)
	 */
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

	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions#playerInGroup(org.bukkit.OfflinePlayer, io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule)
	 */
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
	
	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions#groupHasPermission(io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule, java.lang.String)
	 */
	public boolean groupHasPermission(GroupModule group, String permission)
	{
		return group.getPermissions().contains(permission);
	}
	
	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions#addPermissionToGroup(io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule, java.lang.String)
	 */
	public boolean addPermissionToGroup(GroupModule group, String permission)
	{
		if(!groupHasPermission(group, permission))
		{
			group.addPermission(permission);
			
			return true;
		}
		
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions#removePermissionFromGroup(io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule, java.lang.String)
	 */
	public boolean removePermissionFromGroup(GroupModule group, String permission) 
	{
		if(groupHasPermission(group, permission))
		{
			group.removePermission(permission);
			
			return true;
		}
		
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions#groupExist(java.lang.String)
	 */
	public boolean groupExist(String name)
	{
		return Registery.groups.containsKey(name);
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions#createGroup(java.lang.String)
	 */
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
	
	/*
	 * (non-Javadoc)
	 * @see io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions#setInheritance(io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule, io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule)
	 */
	public void setInheritance(GroupModule to, GroupModule from) 
	{
		to.getPermissions().addAll(from.getPermissions());
	}
}
