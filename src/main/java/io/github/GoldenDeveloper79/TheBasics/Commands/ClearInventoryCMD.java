package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class ClearInventoryCMD extends CommandModule
{
	public ClearInventoryCMD() 
	{
		super(new String[] {"clearinventory", "ci"}, 0, 1, MultiPlayer.SOMETIMES);
	}

	public void performCommand(Player player, String[] args) 
	{
		if(args.length < 1)
		{
			player.getInventory().clear();	
			BasicUtils.sendMessage(player, "&6You have cleared your inventory.");
		}else
		{
			Player player2 = Bukkit.getPlayer(args[0]);
			
			player2.getInventory().clear();
			BasicUtils.sendMessage(player, "&6You have cleared &7" + args[0] + "'s &6inventory.");
			BasicUtils.sendMessage(player2, "&6Your inventory has been cleared by &7" + player.getName() + "&6.");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args) 
	{
		Player player2 = Bukkit.getPlayer(args[0]);
		
		player2.getInventory().clear();
		BasicUtils.sendMessage(console, "You have cleared &7" + args[0] + "'s &6inventory.");
		BasicUtils.sendMessage(player2, "&6Your inventory has been cleared by &7console.");
	}
}
