package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.TheBasics;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class PayCMD extends CommandModule
{
	public PayCMD() 
	{
		super(new String[] {"pay"}, 2, 2, MultiPlayer.ALWAYS);
	}

	public void performCommand(Player player, String[] args) 
	{
		Player player2 = Bukkit.getPlayer(args[0]);
		
		try
		{
			double amount = Double.parseDouble(args[1]);
			
			if(TheBasics.getEconomy().withdrawBalance(player, amount))
			{
				TheBasics.getEconomy().depositBalance(player2, amount);
				
				BasicUtils.sendMessage(player, "&6You gave &7" + args[0] + " $" + amount + "&6.");
				BasicUtils.sendMessage(player2, "&6You received &7$" + amount + " &6from &7" + player.getName() + "&6.");
			}else
			{
				BasicUtils.sendMessage(player, "&cYou do not have enough money!");
			}
		}catch(NumberFormatException e)
		{
			BasicUtils.sendMessage(player, "&cPlease specify a valid amount!");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}	
}