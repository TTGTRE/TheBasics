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
package io.github.GoldenDeveloper79.TheBasics;

import java.util.Collection;
import java.util.Collections;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.API.BasicPermissions;
import io.github.GoldenDeveloper79.TheBasics.Configs.BasicConfig;
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
			return getGroup(BasicUtils.getData((Player) player).getString("Group"));
		}else
		{
			return getGroup(BasicUtils.getConfig(player.getName()).getString("Group"));
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
			removePlayerFromGroup(player, getGroup(BasicUtils.getConfig(player.getName()).getString("Group")));
			
			if(player.isOnline())
			{
				group.getPlayers().add(player.getName());
				PlayerData data = BasicUtils.getData(player.getName());
				data.set("Group", group.getGroupName());
				
				data.loadPermissions();
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
				data.set("Group", null);
			}else
			{
				BasicUtils.getConfig(player.getName()).set("Group", null);
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
			BasicConfig conf = TheBasics.getGroupConfig();
			
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
