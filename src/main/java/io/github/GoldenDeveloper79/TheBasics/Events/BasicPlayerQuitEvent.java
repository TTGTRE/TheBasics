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
package io.github.GoldenDeveloper79.TheBasics.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;
import net.md_5.bungee.api.ChatColor;

public class BasicPlayerQuitEvent implements Listener
{
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		PlayerData data = BasicUtils.getData(player);
		data.quit();
		
		if(!player.hasPermission("TheBasics.SilentQuit") || !TheBasics.getTextConfig().getBoolean("Quit.SilentQuit"))
		{
			event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', TheBasics.getTextConfig().getString("Quit.Message").replace("%p", player.getName())));
		}else
		{
			event.setQuitMessage(null);
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
