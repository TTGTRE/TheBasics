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

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import io.github.GoldenDeveloper79.TheBasics.Enums.EnchantType;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class BasicUtils
{
	/*
	 * Sends a message to the player with a prefix.
	 */
	public static void sendMessage(CommandSender sender, String message)
	{
		if(message != null && sender != null)
		{
			String prefix = TheBasics.getGeneralConfig().getString("Prefix");
	
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message.replace("CONSOLE", "console")));
		}
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
	 * Gets a message from the language config
	 */
	public static String getMessage(String key)
	{
		if(TheBasics.getLanguageConfig().contains(key))
		{
			return TheBasics.getLanguageConfig().getString(key);
		}
		
		return null;
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
		if(Registery.players.containsKey(player.getName().toLowerCase()))
		{
			return Registery.players.get(player.getName().toLowerCase());
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
	 * Checks if an item can be repaired or enchanted.
	 */
	public static boolean isModifiable(ItemStack item)
	{
		for(Material material : Registery.modifiableItems)
		{
			if(item.getType().equals(material))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * Gets an enchantment by its name.
	 */
	public static Enchantment getEnchantment(String name)
	{
		//Bukkit
		for(Enchantment enchant : Enchantment.values())
		{
			if(enchant.getName().equalsIgnoreCase(name))
			{
				return enchant;
			}else if(enchant.getName().replaceAll("_", "").equalsIgnoreCase(name))
			{
				return enchant;
			}
		}
		
		//TheBasics
		for(EnchantType enchantType : EnchantType.values())
		{
			if(enchantType.name().equalsIgnoreCase(name))
			{
				return enchantType.getEnchantment();
			}else if(enchantType.name().replaceAll("_", "").equalsIgnoreCase(name))
			{
				return enchantType.getEnchantment();
			}
		}
		
		return null;
	}
	
	/*
	 * Gets a potion effect type by its name.
	 */
	public static PotionEffectType getPotionEffectType(String name)
	{
		for(PotionEffectType type : PotionEffectType.values())
		{
			if(type.getName().equalsIgnoreCase(name) || type.getName().replace("_", "").equalsIgnoreCase(name))
			{
				return type;
			}
		}
		
		return null;
	}
	
	/*
	 * Gets the index of an array by a word.
	 */
	public static int getIndex(List<String> list, String word)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).equalsIgnoreCase(word))
			{
				return i;
			}
		}
		
		return 0;
	}
}
