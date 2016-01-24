package io.github.GoldenDeveloper79.TheBasics;

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
}
