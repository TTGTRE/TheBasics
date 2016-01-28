package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class SetSpawnCMD extends CommandModule
{
	public SetSpawnCMD()
	{
		super(new String[] {"setspawn"}, 0, 0, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args) 
	{
		Location loc = player.getLocation();
		player.getWorld().setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	
		BasicUtils.sendMessage(player, "&6You have set spawn for the world &7" + loc.getWorld().getName() + " &6at your location.");
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");	
	}	
}
