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

import java.util.ArrayList;
import java.util.HashMap;

import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class Registery
{
	/*
	 * Will store all the custom commands here.
	 * K: Command Label
	 * V: Command Module
	 */
	public static HashMap<String, CommandModule> commands = new HashMap<String, CommandModule>();
	
	/*
	 * Will store all player data here.
	 * K: Player Name
	 * V: Player Data
	 */
	public static HashMap<String, PlayerData> players = new HashMap<String, PlayerData>();
	
	/*
	 * Will store all the groups here.
	 * K: Group Name
	 * V: Group Module
	 */
	public static HashMap<String, GroupModule> groups = new HashMap<String, GroupModule>();
	
	/*
	 * WIll store all teleport request.
	 * K: Player Send
	 * V: Player Receive
	 */
	public static HashMap<String, String> teleportRequest = new HashMap<String, String>();
	
	/*
	 * Will store players in teleport que.
	 * K: Player Name
	 */
	public static ArrayList<String> teleportQue = new ArrayList<String>();
}
