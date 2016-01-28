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
package io.github.GoldenDeveloper79.TheBasics.Modules;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.Registery;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;

public abstract class CommandModule 
{
	private String[] labels;
	private String description;
	private String usage;
	private int minArgs;
	private int maxArgs;
	private MultiPlayer multiPlayer;

	/*
	 * The constructor the creates the commands. Will always be called on super();
	 */
	public CommandModule(String[] labels, int minArgs, int maxArgs, MultiPlayer multiPlayer)
	{
		this.labels = labels;
		this.minArgs = minArgs;
		this.maxArgs = maxArgs;
		this.multiPlayer = multiPlayer;
		
		Registery.commands.put(labels[0], this);
		
		PluginCommand cmd = TheBasics.getPlugin().getCommand(labels[0]);
		cmd.setPermission("TheBasics." + labels[0]);
		cmd.setExecutor(TheBasics.getCommandExecutor());
		
		this.description = cmd.getDescription();
		this.usage = cmd.getUsage();
	}
	
	/*
	 * Will perform the command if its a player sent command.
	 */
	public abstract void performCommand(Player player, String[] args);
	
	/*
	 * Will perform the command if its a console sent command.
	 */
	public abstract void performCommand(ConsoleCommandSender console, String[] args);
	
	/*
	 * The labels/aliases of the command.
	 */
	public String[] getLabels()
	{
		return labels;
	}
	
	/*
	 * The description of the command.
	 */
	public String getDescription()
	{
		return description;
	}

	/*
	 * The usage of the command.
	 */
	public String getUsage() 
	{
		return usage;
	}

	/*
	 * The minimum arguments of the command.
	 */
	public int getMinArgs()
	{
		return minArgs;
	}

	/*
	 * The maximum arguments of the command.
	 */
	public int getMaxArgs()
	{
		return maxArgs;
	}

	/*
	 * If the command is to include multiple players and when.
	 */
	public MultiPlayer getMultiPlayer()
	{
		return multiPlayer;
	}
}
