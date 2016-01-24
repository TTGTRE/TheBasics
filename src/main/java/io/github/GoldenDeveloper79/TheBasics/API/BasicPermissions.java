package io.github.GoldenDeveloper79.TheBasics.API;

import org.bukkit.entity.Player;

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
	GroupModule getPlayerGroup(Player player);
	
	/*
	 * Gets the default group.
	 */
	GroupModule getDefaultGroup();
	
	/*
	 * Adds a player to a group.
	 */
	boolean addPlayerToGroup(Player player, GroupModule group);
	
	/*
	 * Removes a player from a group.
	 */
	boolean removePlayerFromGroup(Player player, GroupModule group);
	
	/*
	 * Checks if a player is in a group.
	 */
	boolean playerInGroup(Player player, GroupModule group);
}
