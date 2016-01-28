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
package io.github.GoldenDeveloper79.TheBasics.API;

import java.util.Collection;

import org.bukkit.OfflinePlayer;

import io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule;

public interface BasicPermissions 
{
	/*
	 * Gets a group by its name.
	 */
	GroupModule getGroup(String groupName);
	
	/*
	 * Gets a the group a player is in.
	 */
	GroupModule getPlayerGroup(OfflinePlayer player);
	
	/*
	 * Gets the default group.
	 */
	GroupModule getDefaultGroup();
	
	/*
	 * Gets a list of the groups.
	 */
	Collection<GroupModule> getGroups();
	
	/*
	 * Adds a player to a group.
	 */
	boolean addPlayerToGroup(OfflinePlayer player, GroupModule group);
	
	/*
	 * Removes a player from a group.
	 */
	boolean removePlayerFromGroup(OfflinePlayer player, GroupModule group);
	
	/*
	 * Checks if a player is in a group.
	 */
	boolean playerInGroup(OfflinePlayer player, GroupModule group);
	
	/*
	 * Checks if a group has a certain permission.
	 */
	boolean groupHasPermission(GroupModule group, String permission);
	
	/*
	 * Adds a permission to a certain group.
	 */
	boolean addPermissionToGroup(GroupModule group, String permission);
	
	/*
	 * Removes a permission from a certain group.
	 */
	boolean removePermissionFromGroup(GroupModule group, String permission);
	
	/*
	 * Checks if a group exist.
	 */
	boolean groupExist(String name);
	
	/*
	 * Creates a group.
	 */
	boolean createGroup(String name);
	
	/*
	 * Inheritats the permissions from a group.
	 */
	void setInheritance(GroupModule to, GroupModule from);
}
