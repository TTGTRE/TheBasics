package io.github.GoldenDeveloper79.TheBasics;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
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
