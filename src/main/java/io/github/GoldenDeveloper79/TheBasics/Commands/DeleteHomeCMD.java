package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class DeleteHomeCMD extends CommandModule
{
	public DeleteHomeCMD() 
	{
		super(new String[] {"deletehome", "delhome"}, 1, 1, MultiPlayer.OTHER);
	}

	public void performCommand(Player player, String[] args)
	{
		if(!args[0].equalsIgnoreCase("Default"))
		{
			if(BasicUtils.getData(player).removeHome(args[0]))
			{
				BasicUtils.sendMessage(player, "&6You have deleted the home called &7" + args[0] + "&6.");
			}else
			{
				BasicUtils.sendMessage(player, "&cThat home does not exist!");
			}
		}else
		{
			BasicUtils.sendMessage(player, "&cYou cannot delete your default home!");
		}
	}

	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		BasicUtils.sendMessage(console, "&cYou must be a player to perform this command!");
	}
}
