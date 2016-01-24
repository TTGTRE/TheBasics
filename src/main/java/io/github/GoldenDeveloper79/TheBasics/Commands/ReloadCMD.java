package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class ReloadCMD extends CommandModule
{
	public ReloadCMD()
	{
		super(new String[] {"reload"}, 0, Integer.MAX_VALUE, MultiPlayer.NEVER);
	}

	public void performCommand(Player player, String[] args) 
	{
		BasicUtils.sendMessage(player, "&cReloading is not allowed on this server!");
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, "&Reloading is not allowed on this server!");
	}
}
