package io.github.GoldenDeveloper79.TheBasics.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import net.md_5.bungee.api.ChatColor;

public class BasicServerPingEvent implements Listener
{
	@EventHandler
	public void onServerPing(ServerListPingEvent event)
	{
		String motd = ChatColor.translateAlternateColorCodes('&', TheBasics.getTextConfig().getString("ListMOTD"));
		event.setMotd(motd);
	}
}
