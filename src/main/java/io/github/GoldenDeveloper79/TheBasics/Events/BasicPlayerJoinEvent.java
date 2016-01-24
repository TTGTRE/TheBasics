package io.github.GoldenDeveloper79.TheBasics.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;
import net.md_5.bungee.api.ChatColor;

public class BasicPlayerJoinEvent implements Listener
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		new PlayerData(player);
		
		if(!player.hasPermission("TheBasics.SilentJoin") || !TheBasics.getTextConfig().getBoolean("Join.SilentJoin"))
		{
			event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', TheBasics.getTextConfig().getString("Join.Message")));
		}
		
		BasicUtils.sendMessage(player, TheBasics.getTextConfig().getString("JoinMOTD"));
	}
}
