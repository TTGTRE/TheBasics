package io.github.GoldenDeveloper79.TheBasics.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;
import net.md_5.bungee.api.ChatColor;

public class BasicPlayerQuitEvent implements Listener
{
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		PlayerData data = BasicUtils.getData(player);
		data.quit();
		
		if(!player.hasPermission("TheBasics.SilentQuit") || !TheBasics.getTextConfig().getBoolean("Quit.SilentQuit"))
		{
			event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', TheBasics.getTextConfig().getString("Quit.Message")));
		}
	}
	
	//Just need to included it somewhere.
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event)
	{
		Player player = event.getPlayer();
		PlayerData data = BasicUtils.getData(player);
		data.quit();
		
		if(!player.hasPermission("TheBasics.SilentQuit") || !TheBasics.getTextConfig().getBoolean("Quit.SilentQuit"))
		{
			event.setLeaveMessage(ChatColor.translateAlternateColorCodes('&', TheBasics.getTextConfig().getString("Quit.Message")));
		}
	}
}
