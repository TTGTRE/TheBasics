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
