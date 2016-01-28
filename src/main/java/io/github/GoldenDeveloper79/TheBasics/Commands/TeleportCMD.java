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
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class TeleportCMD extends CommandModule
{
	public TeleportCMD()
	{
		super(new String[] {"teleport", "tp"}, 1, 1, MultiPlayer.ALWAYS);
	}

	public void performCommand(Player player, String[] args) 
	{
		PlayerData data = BasicUtils.getData(player);
		Player player2 = Bukkit.getPlayer(args[0]);
		
		data.initTeleport(player2.getLocation(), "&7" + args[0]);
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}
}
