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

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class KitCMD extends CommandModule
{
	public KitCMD()
	{
		super(new String[] {"kit"}, 0, 1, MultiPlayer.OTHER);
	}

	public void performCommand(final Player player, final String[] args)
	{
		if(args.length == 1)
		{
			String kitName = args[0];
			
			if(TheBasics.getGeneralConfig().contains("Kits." + kitName))
			{
				if(player.hasPermission("TheBasics.Kit." + kitName))
				{
					PlayerData data = BasicUtils.getData(player);
					
					try
					{
						if(data.contains("Kits." + kitName))
						{
							double time = data.getDouble("Kits." + kitName);
							
							if(time <= System.currentTimeMillis())
							{
								data.set("Kits." + kitName, null);
							}else
							{					
								DecimalFormat format = new DecimalFormat("#.#");
								String formatedTime = String.valueOf(time);
								String unit;
								
								time = time - System.currentTimeMillis();
								
								if((time / 1000) <= 60)
								{
									unit = "s";
									formatedTime = format.format(time / 1000);	
								}else if(((time / 1000) / 60) <= 60)
								{
									unit = "m";
									formatedTime = format.format((time / 1000) / 60);
								}else
								{
									unit = "h";
									formatedTime = format.format(((time / 1000) / 60) / 60);
								}		
								
								BasicUtils.sendMessage(player, BasicUtils.getMessage("KitTime").replace("%a",  formatedTime + unit));						
								return;
							}					
						}
						
						for(String items : TheBasics.getGeneralConfig().getStringList("Kits." + kitName + ".Items"))
						{
							String[] split = items.split(" ");
							List<String> list = Arrays.asList(split);
							ItemStack item = BasicUtils.getItem(split[0], split[1]);
							
							if(items.contains("Name:"))
							{
								StringBuilder sb = new StringBuilder();
								
								for(int i = BasicUtils.getIndex(list, "Name:") + 1; i < split.length; i++)
								{
									if(split[i].equalsIgnoreCase("Enchant:"))
									{
										break;
									}else
									{
										sb.append(split[i] + " ");
									}
								}
								
								ItemMeta meta = item.getItemMeta();
								meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', sb.toString()));
								item.setItemMeta(meta);
							}
							
							if(items.contains("Enchant:"))
							{
								int index = BasicUtils.getIndex(list, "Enchant:") + 1;
								Enchantment enchant = BasicUtils.getEnchantment(split[index]);
								int level = Integer.parseInt(split[index + 1]);
								
								item.addUnsafeEnchantment(enchant, level);
							}
							
							BasicUtils.addItem(player, item);
						}
						
						for(String command : TheBasics.getGeneralConfig().getStringList("Kits." + kitName + ".Commands"))
						{
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%p", player.getName()));
						}
						
						data.set("Kits." + kitName, System.currentTimeMillis() + (1000 * TheBasics.getGeneralConfig().getDouble("Kits." + kitName + ".Time")));
						BasicUtils.sendMessage(player, BasicUtils.getMessage("KitUse").replace("%a", args[0]));
					}catch(Exception e)
					{
						TheBasics.getLog().severe("The kit " + kitName + " has an error. Please make sure all values are valid.");
						BasicUtils.sendMessage(player, BasicUtils.getMessage("KitError"));
					}
				}else
				{
					BasicUtils.sendMessage(player, BasicUtils.getMessage("NoPermission"));
				}
			}else
			{
				BasicUtils.sendMessage(player, BasicUtils.getMessage("KitExist"));
			}
		}else
		{
			StringBuilder sb = new StringBuilder();
			
			for(String kit : TheBasics.getGeneralConfig().getConfigurationSection("Kits").getKeys(false))
			{
				if(player.hasPermission("TheBasics.Kit." + kit))
				{
					sb.append(", " + kit);
				}
			}
			
			BasicUtils.sendMessage(player, BasicUtils.getMessage("KitList").replace("%a", sb.toString().replaceFirst(", ", "")));
		}
	}

	public void performCommand(final ConsoleCommandSender console, final String[] args)
	{
		BasicUtils.sendMessage(console, BasicUtils.getMessage("PlayerCommand"));
	}
	
	public void performCommand(final CommandSender sender, final String[] args){}
}
