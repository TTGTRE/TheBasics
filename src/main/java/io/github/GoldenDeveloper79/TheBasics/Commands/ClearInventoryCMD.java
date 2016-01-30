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
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class ClearInventoryCMD extends CommandModule
{
	public ClearInventoryCMD() 
	{
		super(new String[] {"clearinventory", "ci"}, 0, 1, MultiPlayer.SOMETIMES);
	}

	public void performCommand(Player player, String[] args) 
	{
		if(args.length < 1)
		{
			player.getInventory().clear();	
			BasicUtils.sendMessage(player, BasicUtils.getMessage("ClearInventory"));
		}else
		{
			Player player2 = Bukkit.getPlayer(args[0]);
			
			player2.getInventory().clear();
			BasicUtils.sendMessage(player, BasicUtils.getMessage("ClearInventorySender").replace("%p", args[0]));
			BasicUtils.sendMessage(player2, BasicUtils.getMessage("ClearInventoryReceiver").replace("%p", player.getName()));
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		Player player2 = Bukkit.getPlayer(args[0]);
		
		player2.getInventory().clear();
		BasicUtils.sendMessage(console, BasicUtils.getMessage("ClearInventorySender").replace("%p", args[0]));
		BasicUtils.sendMessage(player2, BasicUtils.getMessage("ClearInventoryReceiver").replace("%p", console.getName()));
	}
}
