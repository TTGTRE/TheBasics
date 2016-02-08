package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class BroadcastCMD extends CommandModule
{
	public BroadcastCMD()
	{
		super(new String[] {"broadcast"}, 1, Integer.MAX_VALUE, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args) {}
	public void performCommand(ConsoleCommandSender console, String[] args) {}

	public void performCommand(CommandSender sender, String[] args)
	{
		BasicUtils.broadcast(ChatColor.translateAlternateColorCodes('&', BasicUtils.combineString(0, args)));
	}
}
