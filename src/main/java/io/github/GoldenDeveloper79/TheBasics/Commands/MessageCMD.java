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
package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;
import net.md_5.bungee.api.ChatColor;

public class MessageCMD extends CommandModule
{
	public MessageCMD()
	{
		super(new String[] {"message", "msg", "tell"}, 2, Integer.MAX_VALUE, MultiPlayer.ALWAYS);
	}

	public void performCommand(Player player, String[] args) 
	{
		PlayerData sender = BasicUtils.getData(player);
		PlayerData receiver = BasicUtils.getData(Bukkit.getPlayer(args[0]));
	
		if(!receiver.getIgnoredPlayers().contains(player.getUniqueId().toString()))
		{
			if(!sender.getIgnoredPlayers().contains(receiver.getUuid().toString()))
			{
				String message = BasicUtils.combineString(1, args);
				String messageFormatSender = TheBasics.getGeneralConfig().getString("MessageFormat.Sender", "&f[&6You &f-> &6%p&f] %m");
				String messageFormatReceiver = TheBasics.getGeneralConfig().getString("MessageFormat.Receiver", "&f[&6%p &f-> &6You&f] %m");
				
				if(!player.hasPermission("TheBasics.Message.Style"))
				{
					message = ChatColor.stripColor(message);
				}
				
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						messageFormatSender.replace("%p", args[0]).replace("%m", message)));
				
				receiver.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
						messageFormatReceiver.replace("%p", player.getName()).replace("%m", message)));
				
				sender.setLastMessaged(receiver.getPlayer());
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("IgnoredTo"));
			}
		}else
		{
			BasicUtils.sendMessage(player, BasicUtils.getMessage("IgnoredAt"));
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		Player receiver = Bukkit.getPlayer(args[0]);
		
		String message = BasicUtils.combineString(1, args);
		String messageFormatSender = TheBasics.getGeneralConfig().getString("MessageFormat.Sender", "&f[&6You &f-> &6%p&f] %m");
		String messageFormatReceiver = TheBasics.getGeneralConfig().getString("MessageFormat.Receiver", "&f[&6%p &f-> &6You&f] %m");
		
		console.sendMessage(messageFormatSender.replace("%p", args[0]).replace("%m", message));
		receiver.getPlayer().sendMessage(messageFormatReceiver.replace("%p", console.getName()).replace("%m", message));
	}
}
