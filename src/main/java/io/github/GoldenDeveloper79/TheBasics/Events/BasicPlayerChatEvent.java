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
