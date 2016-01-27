package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class HomeCMD extends CommandModule
{
	public HomeCMD() 
	{
		super(new String[] {"home"}, 0, 1, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args) 
	{
		PlayerData data = BasicUtils.getData(player);
		
		if(data.getHomes() == 1 || (data.getHomes() > 1 && args.length == 1))
		{
			String home = "Default";
			
			if(args.length ==  1)
			{
				if(data.homeExist(args[0]))
				{
					home = args[0];
				}else
				{
					BasicUtils.sendMessage(player, "&cThat home does not exist!");
					return;
				}
			}
		
			World world = Bukkit.getWorld(data.getString("Home." + home + ".World"));
			double x = data.getDouble("Home." + home + ".X");
			double y = data.getDouble("Home." + home + ".Y");
			double z = data.getDouble("Home." + home + ".Z");
			double yaw = data.getDouble("Home." + home + ".Yaw");
			double pitch = data.getDouble("Home." + home + ".Pitch");
			
			Location loc = new Location(world, x, y, z, (float) yaw, (float) pitch);
			
			if(loc != null)
			{
				if(player.hasPermission("TheBasics.Teleport.Override"))
				{
					player.teleport(loc);
					BasicUtils.sendMessage(player, "&6You have teleported to your home.");
				}else
				{
					int delay = TheBasics.getGeneralConfig().getInt("TeleportDelay");
					
					if(delay > 0)
					{
						BasicUtils.sendMessage(player, "&6You will teleport in &7" + delay + "s&6.");
						
						new BukkitRunnable()
						{
							int counter = 0;
							
							public void run()
							{
								counter++;
								
								if((delay - counter) <= 0)
								{
									player.teleport(loc);
									BasicUtils.sendMessage(player, "&6You have teleported to your home.");
									
									this.cancel();
									return;
								}else
								{
									BasicUtils.sendMessage(player, "&7" + (delay-counter) + "s&6...");
								}
							}
						}.runTaskTimer(TheBasics.getPlugin(), 0L, 20L);
					}else
					{
						player.teleport(loc);
						BasicUtils.sendMessage(player, "&6You have teleported to your home.");
					}
				}
			}
		}else
		{
			String homes = data.getConfig().getConfigurationSection("Home").getKeys(false)
					.toString().replace("[", "").replace("]", "");
			
			BasicUtils.sendMessage(player, "&6Homes: &7" + homes + "&6.");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}
}
