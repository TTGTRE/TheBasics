package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Registery;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class BasicCommandExecutor implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		//If the command is one of ours.
		if(Registery.commands.containsKey(label))
		{
			CommandModule mod = Registery.commands.get(label);
			
			//If the sender has the permission.
			if(sender.hasPermission("TheBasiscs." + label))
			{
				//If the arguments are within the modules defined amounts.
				if(args.length <= mod.getMaxArgs() && mod.getMinArgs() <= args.length)
				{
					//If the command is to be multiplayer always and if the player is online.
					if(mod.getMultiPlayer().equals(MultiPlayer.ALWAYS) && Bukkit.matchPlayer(args[0]) == null)
					{
						BasicUtils.sendMessage(sender, "&cThat player it not online!");
						return false;
					//If the command is to be multiplayer when the args are greater or equal to 1 and the player is online.
					}else if(mod.getMultiPlayer().equals(MultiPlayer.SOMETIMES) && args.length >= 1 && Bukkit.matchPlayer(args[0]) == null)
					{
						BasicUtils.sendMessage(sender, "&cThat player is not online!");
						return false;
					//If the command is to never be multiplayer when the args are greater or equal to 1 and the player is offline.
					}else if(mod.getMultiPlayer().equals(MultiPlayer.NEVER) && args.length >= 1 && Bukkit.matchPlayer(args[0]) != null)
					{
						BasicUtils.sendMessage(sender, "&cThis command does not required an additional player!");
						return false;
					}
					
					//If the sender is a player.
					if(sender instanceof Player)
					{
						mod.performCommand((Player) sender, args);
					//If the sender is a console.
					}else if(sender instanceof ConsoleCommandSender)
					{
						mod.performCommand((ConsoleCommandSender) sender, args);
					}
				}else
				{
					BasicUtils.sendMessage(sender, "&cUsage: " + mod.getUsage());
				}
			}else
			{
				BasicUtils.sendMessage(sender, "&cYou do not have enough permission to perform this command!");
			}
		}
		
		return false;
	}
}
