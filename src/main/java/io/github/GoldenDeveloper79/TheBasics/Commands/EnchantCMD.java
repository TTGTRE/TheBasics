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

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class EnchantCMD extends CommandModule
{
	public EnchantCMD()
	{
		super(new String[] {"enchant"}, 1, 2, MultiPlayer.OTHER);
	}

	public void performCommand(final Player player, final String[] args)
	{
		ItemStack item = player.getItemInHand();
		int level = 1;
		Enchantment enchant = null;
		
		if(item != null)
		{
			if(BasicUtils.isModifiable(item))
			{
				enchant = BasicUtils.getEnchantment(args[0]);
				
				if(enchant != null)
				{
					if(args.length == 2)
					{
						try
						{
							level = Integer.parseInt(args[1]);
						}catch(NumberFormatException e)
						{
							BasicUtils.sendMessage(player, BasicUtils.getMessage("EnchantInvalid"));
							return;
						}
					}
					
					if(!player.hasPermission("TheBasics.Enchant.Unsafe") && (enchant.getMaxLevel() >= level))
					{
						item.addEnchantment(enchant, level);
						BasicUtils.sendMessage(player, BasicUtils.getMessage("Enchant").replace("%a", enchant.getName().toLowerCase() + " " + level));
					}else if(player.hasPermission("TheBasics.Enchant.Unsafe"))
					{
						item.addUnsafeEnchantment(enchant, level);
						BasicUtils.sendMessage(player, BasicUtils.getMessage("Enchant").replace("%a", enchant.getName().toLowerCase() + " " + level));
					}else
					{
						BasicUtils.sendMessage(player, BasicUtils.getMessage("EnchantInvalid"));
					}
				}else
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("EnchantInvalid"));
				}
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("EnchantInvalidItem"));
			}
		}else
		{
			BasicUtils.sendMessage(player, BasicUtils.getMessage("EnchantInvalidItem"));
		}
	}

	public void performCommand(final ConsoleCommandSender console, final String[] args)
	{
		BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
	}
	
	public void performCommand(final CommandSender sender, final String[] args){}
}
