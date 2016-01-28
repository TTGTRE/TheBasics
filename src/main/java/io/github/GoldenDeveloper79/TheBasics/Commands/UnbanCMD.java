package io.github.GoldenDeveloper79.TheBasics.Commands;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.GoldenDeveloper79.TheBasics.BasicUtils;
import io.github.GoldenDeveloper79.TheBasics.Enums.MultiPlayer;
import io.github.GoldenDeveloper79.TheBasics.Modules.CommandModule;

public class UnbanCMD extends CommandModule
{
	public UnbanCMD() 
	{
		super(new String[] {"unban"}, 1, 1, MultiPlayer.OTHER);
	}

	@SuppressWarnings("deprecation")
	public void performCommand(Player player, String[] args) 
	{
		OfflinePlayer player2 = Bukkit.getOfflinePlayer(args[0]);
		
		if(player2 != null)
		{
			if(player2.isBanned())
			{
				Bukkit.getBanList(Type.NAME).pardon(args[0]);
				
				BasicUtils.sendMessage(player, "&6You have unbanned the player &7" + args[0] + "&6.");
				BasicUtils.notify("TheBasics.Unban.Notify", "&6The player &7" + player.getName() + " &6has unbanned the player &7" + args[0] + "&6.");
			}else
			{
				BasicUtils.sendMessage(player, "&cThat player is not banned!");
			}
		}else
		{
			BasicUtils.sendMessage(player, "&cThat player hasn't played on the server before!");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void performCommand(ConsoleCommandSender console, String[] args)
	{
		OfflinePlayer player2 = Bukkit.getOfflinePlayer(args[0]);
		
		if(player2 != null)
		{
			if(player2.isBanned())
			{
				Bukkit.getBanList(Type.NAME).pardon(args[0]);
				
				BasicUtils.sendMessage(console, "&6You have unbanned the player &7" + args[0] + "&6.");
				BasicUtils.notify("TheBasics.Unban.Notify", "&6The player &7console &6has unbanned the player &7" + args[0] + "&6.");
			}else
			{
				BasicUtils.sendMessage(console, "&cThat player is not banned!");
			}
		}else
		{
			BasicUtils.sendMessage(console, "&cThat player hasn't played on the server before!");
		}
	}
}
