package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class WarpCMD extends CommandModule
{
	public WarpCMD()
	{
		super(new String[] {"warp"}, 0, 1, MultiPlayer.OTHER);
	}
	
	public void performCommand(Player player, String[] args) 
	{
		if(args.length < 1)
		{
			String warps = TheBasics.getDataConfig().getConfig().getConfigurationSection("Servers.Warps").getKeys(false)
					.toString().replace("[", "").replace("]", "");
			
			BasicUtils.sendMessage(player, "&6Warps: &7" + warps + "&6.");
		}else if(args.length == 1)
		{
			String root = "Servers.Warps." + args[0].toLowerCase();
			
			if(TheBasics.getDataConfig().contains(root))
			{
				World world = Bukkit.getWorld(TheBasics.getDataConfig().getString(root + ".World"));
				double x = TheBasics.getDataConfig().getDouble(root + ".X");
				double y = TheBasics.getDataConfig().getDouble(root + ".Y");
				double z = TheBasics.getDataConfig().getDouble(root + ".Z");
				double yaw = TheBasics.getDataConfig().getDouble(root + ".Yaw");
				double pitch = TheBasics.getDataConfig().getDouble(root + ".Pitch");
				
				Location loc = new Location(world, x, y, z, (float) yaw, (float) pitch);
				
				BasicUtils.getData(player).initTeleport(loc, "&6the warp &7" + args[0]);
			}
		}
	}
	
	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}
}