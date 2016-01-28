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
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
		File file = getFile(offlinePlayer);

		if(file != null)
		{
			if(file.exists())
			{
				return YamlConfiguration.loadConfiguration(file);
			}
		}

		return null;
	}

	/*
	 * Gets the file for offline players.
	 */
	public static File getFile(String offlinePlayer)
	{
		if(TheBasics.getDataConfig().contains("Players." + offlinePlayer))
		{
			return new File("plugins/TheBasics/Players/" + TheBasics.getDataConfig().getString("Players." + offlinePlayer) + ".yml");
		}

		return null;
	}

	/*
	 * Saves the file for offline players.
	 */
	public static boolean saveFile(String offlinePlayer)
	{
		try
		{
			getConfig(offlinePlayer).save(getFile(offlinePlayer));
			return true;
		}catch(Exception e)
		{
			TheBasics.getLog().severe("Could not save the config for the offline player " + offlinePlayer + "!");
			return false;
		}
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

	/*
	 * Makes a string an itemstack magically.
	 */
	public static ItemStack getItem(String item, String quantity)
	{
		int amount = 0;

		try
		{
			amount = Integer.parseInt(quantity);
		}catch(NumberFormatException e)
		{
			return null;
		}

		try
		{
			if(item.contains(":"))
			{
				String[] itemSplit = item.split(":");
				byte data = Byte.parseByte(itemSplit[1]);

				return new ItemStack(Material.matchMaterial(itemSplit[0]), amount, data);
			}else
			{
				return new ItemStack(Material.matchMaterial(item), amount);
			}
		}catch(NumberFormatException e)
		{
			return new ItemStack(Material.matchMaterial(item), amount);
		}catch(NullPointerException e)
		{
			return null;
		}
	}

	/*
	 * Adds an item to a players inventory safely.
	 */
	public static void addItem(Player player, ItemStack item)
	{
		if(player.getInventory().firstEmpty() != -1)
		{
			player.getInventory().addItem(item);
		}else
		{
			player.getWorld().dropItemNaturally(player.getLocation(), item);
		}
	}

	/*
	 * Updates the config values.
	 */
	public static void updateConfig(FileConfiguration config, File file) 
	{
		HashMap<String, Object> newConfig = getConfigValues(file);
		
		for(String var : config.getKeys(false))
		{
			newConfig.remove(var);
		}
		
		if(newConfig.size() != 0)
		{
			for(String key : newConfig.keySet())
			{
				config.set(key, newConfig.get(key));
			}
			
			try 
			{
				config.save(file);
			} catch(IOException e) 
			{
				TheBasics.getLog().severe("Could not save " + file.getName() + ".");
			}
		}
	}
	
	/*
	 * Gets the content of that file as a hashmap.
	 */
	public static HashMap<String, Object> getConfigValues(File file) 
	{
		HashMap<String, Object> var = new HashMap<>();
		YamlConfiguration config = new YamlConfiguration();
		
		try 
		{
			config.loadFromString(stringFromInputStream(file.getName()));
		} catch(Exception e) {} //Just ignore it.
		
		for(String key : config.getKeys(false)) 
		{
			var.put(key, config.get(key));
		}
		
		return var;
	}
	
	/*
	 * Gets the contents of that file as a string.
	 */
	@SuppressWarnings("resource")
	public static String stringFromInputStream(String fileName) throws URISyntaxException, IOException
	{
		InputStream stream = TheBasics.getResourceURL(fileName).openStream();
		return new Scanner(stream).useDelimiter("\\A").next();
	}
}
