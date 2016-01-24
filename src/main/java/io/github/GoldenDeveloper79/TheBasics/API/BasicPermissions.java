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
