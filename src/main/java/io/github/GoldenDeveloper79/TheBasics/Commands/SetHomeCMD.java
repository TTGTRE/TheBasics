package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;
import io.github.GoldenDeveloper79.TheBasics.Player.PlayerData;

public class SetHomeCMD extends CommandModule
{
	public SetHomeCMD()
	{
		super(new String[] {"sethome"}, 1, 1, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args)
	{
		PlayerData data = BasicUtils.getData(player);
		
		if((data.getHomes() + 1) <= data.getMaxHomes())
		{
			data.createHome(args[0]);
			BasicUtils.sendMessage(player, "&6You have created a home called &7" + args[0] + " &6at your location.");
		}else
		{
			BasicUtils.sendMessage(player, "&cYou have reached your max home limit!");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}
}
