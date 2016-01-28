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

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class NickCMD extends CommandModule
{
	public NickCMD() 
	{
		super(new String[] {"nick"}, 1, 1, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args)
	{
		if(TheBasics.getGeneralConfig().getBoolean("Nickname.Enabled"))
		{
			String name = args[0];
			
			if(name.length() <= TheBasics.getGeneralConfig().getDouble("Nickname.MaxLength") && TheBasics.getGeneralConfig().getDouble("Nickname.MinLength") >= name.length())
			{
				player.setDisplayName(ChatColor.translateAlternateColorCodes('&', args[0]));
				BasicUtils.sendMessage(player, "&6You changed your displayname to " + args[0] + "&6.");
			}else
			{
				BasicUtils.sendMessage(player, "&cPlease specify a valid nickname!");
			}
		}else
		{
			BasicUtils.sendMessage(player, "&cChanging your nickname is disabled on this server!");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}
}
