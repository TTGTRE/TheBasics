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

public class HealCMD extends CommandModule
{
	public HealCMD() 
	{
		super(new String[] {"heal"}, 0, 1, MultiPlayer.SOMETIMES);
	}

	public void performCommand(Player player, String[] args) 
	{
		if(args.length < 1)
		{
			player.setHealth(player.getMaxHealth());
			player.setFoodLevel(20);
			player.setFireTicks(0);
			
			BasicUtils.sendMessage(player, BasicUtils.getMessage("Heal"));
		}else
		{
			Player player2 = Bukkit.getPlayer(args[0]);
			
			player2.setHealth(player2.getMaxHealth());
			player2.setFoodLevel(20);
			player2.setFireTicks(0);
				
			BasicUtils.sendMessage(player, BasicUtils.getMessage("HealSender").replace("%p", args[0]));
			BasicUtils.sendMessage(player2, BasicUtils.getMessage("HealReceiver").replace("%p", player.getName()));
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		Player player2 = Bukkit.getPlayer(args[0]);

		player2.setHealth(player2.getMaxHealth());
		player2.setFoodLevel(20);
		player2.setFireTicks(0);
			
		BasicUtils.sendMessage(console, BasicUtils.getMessage("HealSender").replace("%p", args[0]));
		BasicUtils.sendMessage(player2, BasicUtils.getMessage("HealReceiver").replace("%p", console.getName()));
	}
}
