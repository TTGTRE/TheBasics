package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class SetWarpCMD extends CommandModule
{
	public SetWarpCMD()
	{
		super(new String[] {"setwarp"}, 1, 1, MultiPlayer.OTHER);
	}
	
	public void performCommand(Player player, String[] args) 
	{
		Location loc = player.getLocation();
		String root = "Servers.Warps." + args[0].toLowerCase();
		
		TheBasics.getDataConfig().set(root + ".World", loc.getWorld().getName());
		TheBasics.getDataConfig().set(root + ".X", loc.getX());
		TheBasics.getDataConfig().set(root + ".Y", loc.getY());
		TheBasics.getDataConfig().set(root + ".Z", loc.getZ());
		TheBasics.getDataConfig().set(root + ".Yaw", loc.getYaw());
		TheBasics.getDataConfig().set(root + ".Pitch", loc.getPitch());
		
		BasicUtils.sendMessage(player, "&6You have set the warp &7" + args[0] + "&6 at your location.");
	}
	
	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}
}
