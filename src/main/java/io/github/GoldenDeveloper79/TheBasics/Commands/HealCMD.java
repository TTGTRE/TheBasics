package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class HealCMD extends CommandModule
{
	public HealCMD() 
	{
		super(new String[] {"heal"}, 0, 1, MultiPlayer.SOMETIMES);
	}

	public void performCommand(Player player, String[] args) 
	{
		if(args.length < 1)
		{
			player.setHealth(player.getMaxHealth());
			player.setFoodLevel(20);
			player.setFireTicks(0);
			
			BasicUtils.sendMessage(player, "&6You have healed yourself.");
		}else
		{
			Player player2 = Bukkit.getPlayer(args[0]);
			
			player2.setHealth(player2.getMaxHealth());
			player2.setFoodLevel(20);
			player2.setFireTicks(0);
			
			BasicUtils.sendMessage(player, "&6You have healed " + args[0] + ".");
			BasicUtils.sendMessage(player2, "&6You have been healed by " + player.getName() + ".");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		Player player2 = Bukkit.getPlayer(args[0]);
		
		player2.setHealth(player2.getMaxHealth());
		player2.setFoodLevel(20);
		player2.setFireTicks(0);
		
		BasicUtils.sendMessage(console, "You have healed " + args[0] + ".");
		BasicUtils.sendMessage(player2, "&6You have been healed by " + console.getName() + ".");
	}
}
