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
package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

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
				
				BasicUtils.sendMessage(player, "&6You gave &7" + args[0] + " $" + amount + "&6.");
				BasicUtils.sendMessage(player2, "&6You received &7$" + amount + " &6from &7" + player.getName() + "&6.");
			}else
			{
				BasicUtils.sendMessage(player, "&cYou do not have enough money!");
			}
		}catch(NumberFormatException e)
		{
			BasicUtils.sendMessage(player, "&cPlease specify a valid amount!");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}	
}