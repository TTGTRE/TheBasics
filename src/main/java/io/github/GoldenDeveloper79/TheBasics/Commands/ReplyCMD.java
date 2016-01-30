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

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;
import net.md_5.bungee.api.ChatColor;

public class ReplyCMD extends CommandModule
{
	public ReplyCMD()
	{
		super(new String[] {"reply", "r"}, 1, Integer.MAX_VALUE, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args) 
	{
		PlayerData sender = BasicUtils.getData(player);
		
		if(sender.getLastMessaged() != null)
		{
			PlayerData receiver = BasicUtils.getData(sender.getLastMessaged());
			
			String message = BasicUtils.combineString(0, args);
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
		}else
		{
			sender.setLastMessaged(null);
			BasicUtils.sendMessage(player, BasicUtils.getMessage("NoReply"));
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
	}
}
