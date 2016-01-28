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

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class BasicPlayerJoinEvent implements Listener
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		new PlayerData(player);
	
		if(player.hasPlayedBefore())
		{
			if(!player.hasPermission("TheBasics.SilentJoin") || !TheBasics.getTextConfig().getBoolean("Join.SilentJoin"))
			{
				event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', TheBasics.getTextConfig().getString("Join.Message").replace("%p", player.getName())));
			}else
			{
				event.setJoinMessage(null);
			}
		}else
		{
			event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', TheBasics.getTextConfig().getString("FirstJoinMOTD").replace("%p", player.getName())));
		}
		
		BasicUtils.sendMessage(player, TheBasics.getTextConfig().getString("JoinMOTD").replace("%p", player.getName()));
	}
}
