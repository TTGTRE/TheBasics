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
import org.bukkit.inventory.ItemStack;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class RepairCMD extends CommandModule
{
	public RepairCMD() 
	{
		super(new String[] {"repair"}, 0, 0, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args) 
	{
		ItemStack item = player.getItemInHand();
		
		if(item != null)
		{
			if(BasicUtils.isRepairable(item))
			{
				item.setDurability((short) 0);
			
				BasicUtils.sendMessage(player, BasicUtils.getMessage("Repair"));
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("RepairInvalid"));
			}
		}else
		{
			BasicUtils.sendMessage(player, BasicUtils.getMessage("RepairInvalidItem"));
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
	}
}

