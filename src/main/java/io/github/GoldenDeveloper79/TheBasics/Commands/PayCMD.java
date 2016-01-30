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

import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Utils.BasicUtils;

public class PayCMD extends CommandModule
{
	public PayCMD() 
	{
		super(new String[] {"pay"}, 2, 2, MultiPlayer.ALWAYS);
	}

	public void performCommand(Player player, String[] args) 
	{
		Player player2 = Bukkit.getPlayer(args[0]);
		
		try
		{
			double amount = Double.parseDouble(args[1]);
			
			if(TheBasics.getEconomy().withdrawBalance(player, amount))
			{
				TheBasics.getEconomy().depositBalance(player2, amount);
				
				BasicUtils.sendMessage(player, BasicUtils.getMessage("PaySender").replace("%p", args[0]).replace("%a", String.valueOf(amount)));
				BasicUtils.sendMessage(player2, BasicUtils.getMessage("PayReceiver").replace("%p", player.getName()).replace("%a", String.valueOf(amount)));
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("PayLackOfFunds"));
			}
		}catch(NumberFormatException e)
		{
			BasicUtils.sendMessage(player, BasicUtils.getMessage("InvalidAmount"));
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
	}	
}