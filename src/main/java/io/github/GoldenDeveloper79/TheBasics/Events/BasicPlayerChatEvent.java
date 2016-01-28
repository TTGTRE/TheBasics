package io.github.GoldenDeveloper79.TheBasics.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Modules.GroupModule;

public class BasicPlayerChatEvent implements Listener	
{
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		Player player = event.getPlayer();
		GroupModule group = TheBasics.getPermissions().getPlayerGroup(event.getPlayer());
		
		if(TheBasics.getGeneralConfig().contains("ChatFormat." + group.getGroupName()))
		{
			event.setCancelled(true);
			
			String message = event.getMessage();
			
			if(!player.hasPermission("TheBasics.Chat.Color"))
			{
				message = ChatColor.stripColor(message);
			}
			
			String format = TheBasics.getGeneralConfig().getString("ChatFormat." + group.getGroupName());
			String worldName = player.getWorld().getName();
			format = format.replaceAll("<World>", worldName.substring(0, 1).toUpperCase() + worldName.substring(1));
			format = format.replaceAll("<Prefix>", group.getPrefix());
			format = format.replaceAll("<Name>", player.getDisplayName());
			format = format.replaceAll("<Message>", message);
			
			Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', format));
		}
	}
}
