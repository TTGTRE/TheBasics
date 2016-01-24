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

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;
import net.md_5.bungee.api.ChatColor;

public class BasicUtils
{
	/*
	 * Sends a message to the player with a prefix.
	 */
	public static void sendMessage(CommandSender sender, String message)
	{
		String prefix = TheBasics.getGeneralConfig().getString("Prefix");
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
	}

	/*
	 * Notifies all players on the server a message if they have a permission. Ex: Punishment Notifications.
	 */
	public static void notify(String permission, String message)
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if(player.hasPermission(permission))
			{
				sendMessage(player, message);
			}
		}
	}
	
	/*
	 * Broadcast a message to all players on the server with a prefix.
	 */
	public static void broadcast(String message)
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			sendMessage(player, message);
		}
	}
	
	/*
	 * Gets a player data by the player.
	 */
	public static PlayerData getData(Player player)
	{
		if(Registery.players.containsKey(player.getName()))
		{
			return Registery.players.get(player.getName());
		}
		
		return null;
	}
	
	/*
	 * Gets a player data by the player name.
	 * Use only if your 100% confident it won't return null.
	 */
	public static PlayerData getData(String player)
	{
		if(Registery.players.containsKey(player))
		{
			return Registery.players.get(player);
		}
		return null;
	}
	
	/*
	 * Gets the config for offline players.
	 */
	public static FileConfiguration getConfig(String offlinePlayer)
	{
		if(TheBasics.getDataConfig().contains("Players." + offlinePlayer))
		{
			File file = new File("plugins/TheBasics/Players/" + TheBasics.getDataConfig().getString("Players." + offlinePlayer) + ".yml");
			
			if(file.exists())
			{
				return YamlConfiguration.loadConfiguration(file);
			}
		}
		
		return null;
	}
	
	/*
	 * Combines an an array of strings. Used mostly for punishment reasons.
	 */
	public static String combineString(int start, String[] strings)
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i = start; i < strings.length; i++)
		{
			sb.append(strings[i] + " ");
		}
		
		return sb.toString();
	}
}
