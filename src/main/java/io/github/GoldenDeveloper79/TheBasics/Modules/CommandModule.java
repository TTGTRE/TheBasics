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
		
		for(String label : labels)
		{
			Registery.commands.put(label, this);
			
			PluginCommand cmd = TheBasics.getPlugin().getCommand(label);
			cmd.setExecutor(TheBasics.getCommandExecutor());
			
			this.description = cmd.getDescription();
			this.usage = cmd.getUsage();
		}
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
